package edu.student.itson.dissof.megazarl.dto;

/**
 * TiempoEstimadoPreparacionEnvioPedidoDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * el rango de días estimados para la preparación y envío de un pedido,
 * indicando un límite inferior y superior para informar al cliente.
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
public class TiempoEstimadoPreparacionEnvioPedidoDTO {
    private int diasLimiteInferior;
    private int diasLimiteSuperior;

    public TiempoEstimadoPreparacionEnvioPedidoDTO(int diasLimiteInferior, int diasLimiteSuperior) {
        this.diasLimiteInferior = diasLimiteInferior;
        this.diasLimiteSuperior = diasLimiteSuperior;
    }

    public int getDiasLimiteInferior() {
        return diasLimiteInferior;
    }

    public int getDiasLimiteSuperior() {
        return diasLimiteSuperior;
    }
}
