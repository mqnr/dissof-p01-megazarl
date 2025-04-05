
package edu.student.itson.dissof.megazarl.dto;


public class IdClientePaqueteriaCalculoCostoEnvioDTO {
    private Integer idCliente;
    private Integer idPaqueteria;

    public IdClientePaqueteriaCalculoCostoEnvioDTO(Integer idCliente, Integer idPaqueteria) {
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public Integer getIdPaqueteria() {
        return idPaqueteria;
    }
    
    
    
}
