//Caroline Ribeiro CB3021271 e Leticia Vitória Rodrigues CB3013138

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class CalculadoraForm extends Frame implements ActionListener {
    private TextField visor;          // Onde os números e resultados são exibidos
    private double numeroAnterior;    // Armazena o número anterior
    private String operacaoPendente;  // Armazena a operação pendente (+, -, *, /)

    public CalculadoraForm() {
        setSize(300, 300);
        setTitle("Calculadora Simples");
        setLayout(new BorderLayout());

        visor = new TextField();
        visor.setEditable(false);
        add(visor, BorderLayout.NORTH);

        Panel botoes = new Panel();
        botoes.setLayout(new GridLayout(5, 4));

        String[] labels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };

        for (String label : labels) {
            Button botao = new Button(label);
            botao.addActionListener(this);
            botoes.add(botao);
        }

        add(botoes, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        operacaoPendente = ""; // Inicializa operacaoPendente
    }

    public void actionPerformed(ActionEvent e) {
        String labelBotao = e.getActionCommand();

        try {
            if (labelBotao.equals("C")) {
                visor.setText("");
                numeroAnterior = 0;
                operacaoPendente = "";
            } else if (labelBotao.equals("=")) {
                if (!operacaoPendente.isEmpty()) {
                    double resultado = calcular();
                    visor.setText(Double.toString(resultado));
                    operacaoPendente = "";
                }
            } else if (labelBotao.matches("[0-9]+") || labelBotao.equals(".")) {
                visor.setText(visor.getText() + labelBotao);
            } else {
                if (!operacaoPendente.isEmpty()) {
                    double resultado = calcular();
                    visor.setText(Double.toString(resultado));
                }
                operacaoPendente = labelBotao;
                numeroAnterior = Double.parseDouble(visor.getText());
                visor.setText("");
            }
        } catch (NumberFormatException ex) {
            visor.setText("Erro");
        }
    }

    private double calcular() {
        double numeroAtual = Double.parseDouble(visor.getText());
        double resultado = numeroAnterior;

        switch (operacaoPendente) {
            case "+":
                resultado += numeroAtual;
                break;
            case "-":
                resultado -= numeroAtual;
                break;
            case "*":
                resultado *= numeroAtual;
                break;
            case "/":
                if (numeroAtual != 0) {
                    resultado /= numeroAtual;
                } else {
                    visor.setText("Erro");
                    resultado = 0;
                }
                break;
        }

        return resultado;
    }

    public static void main(String[] args) {
        CalculadoraForm calculadora = new CalculadoraForm();
        calculadora.setVisible(true);
    }
}
