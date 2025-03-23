package edu.student.itson.dissof.megazarl.dto;

public class MontoMinimoEnvioGratuitoDTO {

    private double montoMinimo;
    private double montoActual;

    public MontoMinimoEnvioGratuitoDTO(double montoMinimo, double montoActual) {
        this.montoMinimo = montoMinimo;
        this.montoActual = montoActual;
    }

    public double getMontoMinimo() {
        return montoMinimo;
    }

    public double getMontoActual() {
        return montoActual;
    }
}
