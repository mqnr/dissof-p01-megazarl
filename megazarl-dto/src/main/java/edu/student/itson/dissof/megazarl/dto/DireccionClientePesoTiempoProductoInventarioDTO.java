
package edu.student.itson.dissof.megazarl.dto;

import java.util.HashMap;


public class DireccionClientePesoTiempoProductoInventarioDTO {
    
    private Integer codigoPaqueteria;
    private String codigoPostalCliente;
    private String calleCliente;
    private String numeroCliente;
    private String codigoPostalMatriz;
    private String calleMatriz;
    private String numeroMatriz;
    private Double pesoKgProductoInventario;
    private Float tiempoEnvioMatrizHorasProductoInventario;

    public DireccionClientePesoTiempoProductoInventarioDTO(Integer codigoPaqueteria, String codigoPostalCliente, String calleCliente, String numeroCliente, String codigoPostalMatriz, String calleMatriz, String numeroMatriz, Double pesoKgProductoInventario, Float tiempoEnvioMatrizHorasProductoInventario) {
        this.codigoPaqueteria = codigoPaqueteria;
        this.codigoPostalCliente = codigoPostalCliente;
        this.calleCliente = calleCliente;
        this.numeroCliente = numeroCliente;
        this.codigoPostalMatriz = codigoPostalMatriz;
        this.calleMatriz = calleMatriz;
        this.numeroMatriz = numeroMatriz;
        this.pesoKgProductoInventario = pesoKgProductoInventario;
        this.tiempoEnvioMatrizHorasProductoInventario = tiempoEnvioMatrizHorasProductoInventario;
    }

    public Integer getCodigoPaqueteria() {
        return codigoPaqueteria;
    }

    public String getCodigoPostalCliente() {
        return codigoPostalCliente;
    }

    public String getCalleCliente() {
        return calleCliente;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public String getCodigoPostalMatriz() {
        return codigoPostalMatriz;
    }

    public String getCalleMatriz() {
        return calleMatriz;
    }

    public String getNumeroMatriz() {
        return numeroMatriz;
    }

    public Double getPesoKgProductoInventario() {
        return pesoKgProductoInventario;
    }

    public Float getTiempoEnvioMatrizHorasProductoInventario() {
        return tiempoEnvioMatrizHorasProductoInventario;
    }

    
    
}