package com.calculadora.main;

import com.calculadora.gui.CalculadoraGUI;

public class CalculadoraMain {
	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            CalculadoraGUI calculadora = new CalculadoraGUI();
            calculadora.setVisible(true);
        });
    }
}
