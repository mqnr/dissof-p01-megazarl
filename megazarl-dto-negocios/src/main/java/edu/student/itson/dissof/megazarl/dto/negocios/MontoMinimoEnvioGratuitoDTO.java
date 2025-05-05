package edu.student.itson.dissof.megazarl.dto.negocios;

/**
 * MontoMinimoEnvioGratuitoDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * información sobre el monto mínimo requerido para obtener envío gratuito
 * y el monto actual del carrito, para determinar si se aplica dicho beneficio.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
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
