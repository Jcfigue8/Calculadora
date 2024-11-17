package com.calculadora.logica;

public class CalculadoraLogica {

	private double numeroActual;
    private double memoria;
    private String operacionPendiente;
    private boolean iniciarNumeroNuevo;

    public CalculadoraLogica() {
        limpiar();
    }

    public void limpiar() {
        numeroActual = 0;
        memoria = 0;
        operacionPendiente = "";
        iniciarNumeroNuevo = true;
    }

    public boolean debeIniciarNumeroNuevo() {
        return iniciarNumeroNuevo;
    }

    public void setIniciarNumeroNuevo(boolean valor) {
        iniciarNumeroNuevo = valor;
    }

    public String getOperacionPendiente() {
        return operacionPendiente;
    }

    public void setOperacionPendiente(String operacion) {
        this.operacionPendiente = operacion;
    }

    public double calcularRaizCuadrada(double numero) {
        if (numero >= 0) {
            return Math.sqrt(numero);
        }
        throw new ArithmeticException("No se puede calcular la raíz cuadrada de un número negativo");
    }

    public double calcularCuadrado(double numero) {
        return numero * numero;
    }

    public double calcularPorcentaje(double numero) {
        return numero / 100;
    }

    public double calcular(double numeroActual) {
        double resultado = memoria;
        
        switch (operacionPendiente) {
            case "+":
                resultado += numeroActual;
                break;
            case "-":
                resultado -= numeroActual;
                break;
            case "*":
                resultado *= numeroActual;
                break;
            case "/":
                if (numeroActual == 0) {
                    throw new ArithmeticException("División por cero");
                }
                resultado /= numeroActual;
                break;
            default:
                resultado = numeroActual;
        }
        
        memoria = resultado;
        return resultado;
    }

    public void setMemoria(double valor) {
        this.memoria = valor;
    }
	
}
