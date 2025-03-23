package edu.student.itson.dissof.megazarl.dto;

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
