package com.calculadora.gui;

import javax.swing.*;
import com.calculadora.logica.CalculadoraLogica;
import java.awt.*;

public class InterfazBotones extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CalculadoraGUI calculadora;
    private static final String[] BOTONES = {
        "C", "√", "x²", "%",
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", ".", "=", "+"
    };

    public InterfazBotones(CalculadoraGUI calculadora) {
        this.calculadora = calculadora;
        setLayout(new GridLayout(6, 4, 5, 5));
        inicializarBotones();
    }

    private void inicializarBotones() {
        for (String textoBoton : BOTONES) {
            JButton boton = crearBoton(textoBoton);
            add(boton);
        }
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.addActionListener(e -> procesarBoton(texto));
        return boton;
    }

    private void procesarBoton(String boton) {
        CalculadoraLogica logica = calculadora.getLogica();
        try {
            switch (boton) {
                case "C":
                    logica.limpiar();
                    calculadora.actualizarDisplay("0");
                    break;

                case "=":
                    double resultado = logica.calcular(
                        Double.parseDouble(calculadora.getDisplayText())
                    );
                    calculadora.actualizarDisplay(String.valueOf(resultado));
                    logica.setOperacionPendiente("");
                    logica.setIniciarNumeroNuevo(true);
                    break;

                case "+":
                case "-":
                case "*":
                case "/":
                    procesarOperador(boton);
                    break;

                case "√":
                    procesarRaizCuadrada();
                    break;

                case "x²":
                    procesarCuadrado();
                    break;

                case "%":
                    procesarPorcentaje();
                    break;

                case ".":
                    procesarPunto();
                    break;

                default: // Números
                    procesarNumero(boton);
                    break;
            }
        } catch (ArithmeticException ex) {
            calculadora.actualizarDisplay("Error");
            logica.limpiar();
        } catch (NumberFormatException ex) {
            calculadora.actualizarDisplay("Error");
            logica.limpiar();
        }
    }

    private void procesarOperador(String operador) {
        CalculadoraLogica logica = calculadora.getLogica();
        if (!logica.getOperacionPendiente().isEmpty()) {
            double resultado = logica.calcular(
                Double.parseDouble(calculadora.getDisplayText())
            );
            calculadora.actualizarDisplay(String.valueOf(resultado));
        }
        logica.setMemoria(Double.parseDouble(calculadora.getDisplayText()));
        logica.setOperacionPendiente(operador);
        logica.setIniciarNumeroNuevo(true);
    }

    private void procesarRaizCuadrada() {
        CalculadoraLogica logica = calculadora.getLogica();
        double numero = Double.parseDouble(calculadora.getDisplayText());
        double resultado = logica.calcularRaizCuadrada(numero);
        calculadora.actualizarDisplay(String.valueOf(resultado));
        logica.setIniciarNumeroNuevo(true);
    }

    private void procesarCuadrado() {
        CalculadoraLogica logica = calculadora.getLogica();
        double numero = Double.parseDouble(calculadora.getDisplayText());
        double resultado = logica.calcularCuadrado(numero);
        calculadora.actualizarDisplay(String.valueOf(resultado));
        logica.setIniciarNumeroNuevo(true);
    }

    private void procesarPorcentaje() {
        CalculadoraLogica logica = calculadora.getLogica();
        double numero = Double.parseDouble(calculadora.getDisplayText());
        double resultado = logica.calcularPorcentaje(numero);
        calculadora.actualizarDisplay(String.valueOf(resultado));
        logica.setIniciarNumeroNuevo(true);
    }

    private void procesarPunto() {
        if (!calculadora.getDisplayText().contains(".")) {
            calculadora.actualizarDisplay(calculadora.getDisplayText() + ".");
        }
    }

    private void procesarNumero(String numero) {
        CalculadoraLogica logica = calculadora.getLogica();
        if (logica.debeIniciarNumeroNuevo()) {
            calculadora.actualizarDisplay(numero);
            logica.setIniciarNumeroNuevo(false);
        } else {
            calculadora.actualizarDisplay(calculadora.getDisplayText() + numero);
        }
    }
}
