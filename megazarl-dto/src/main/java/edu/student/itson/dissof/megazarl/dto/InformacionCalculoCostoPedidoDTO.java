
package edu.student.itson.dissof.megazarl.dto;

import java.util.HashMap;


public class InformacionCalculoCostoPedidoDTO {
    
    Integer idCliente;
    Integer idPaqueteria;
    HashMap<Integer, Integer> mapaProductosCantidades;

    public InformacionCalculoCostoPedidoDTO(Integer idCliente, Integer idPaqueteria, HashMap<Integer, Integer> mapaProductosCantidades) {
        this.idCliente = idCliente;
        this.idPaqueteria = idPaqueteria;
        this.mapaProductosCantidades = mapaProductosCantidades;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public Integer getIdPaqueteria() {
        return idPaqueteria;
    }

    public HashMap<Integer, Integer> getMapaProductosCantidades() {
        return mapaProductosCantidades;
    }

    

    
    
    
}
