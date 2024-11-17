package com.calculadora.gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.calculadora.logica.CalculadoraLogica;

	public class CalculadoraGUI extends JFrame {
	    private JTextField display;
	    private CalculadoraLogica logica;
	    private InterfazBotones interfazBotones;

	    public CalculadoraGUI() {
	        configurarVentana();
	        logica = new CalculadoraLogica();
	        inicializarComponentes();
	    }

	    private void configurarVentana() {
	        setTitle("Calculadora");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());
	        setSize(300, 400);
	        setLocationRelativeTo(null);
	    }

	    private void inicializarComponentes() {
	        // Inicializar display
	        display = new JTextField("0");
	        configurarDisplay();
	        add(display, BorderLayout.NORTH);

	        // Inicializar panel de botones
	        interfazBotones = new InterfazBotones(this);
	        add(interfazBotones, BorderLayout.CENTER);
	    }

	    private void configurarDisplay() {
	        display.setHorizontalAlignment(JTextField.RIGHT);
	        display.setEditable(false);
	        display.setFont(new Font("Arial", Font.BOLD, 20));
	    }

	    public void actualizarDisplay(String texto) {
	        display.setText(texto);
	    }

	    public String getDisplayText() {
	        return display.getText();
	    }

	    public CalculadoraLogica getLogica() {
	        return logica;
	    }
	}
