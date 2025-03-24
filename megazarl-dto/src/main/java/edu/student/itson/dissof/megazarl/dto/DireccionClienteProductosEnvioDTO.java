
package edu.student.itson.dissof.megazarl.dto;

import java.util.HashMap;


public class DireccionClienteProductosEnvioDTO {
    
    private Integer codigoPaqueteria;
    private Integer idCliente;
    private String codigoPostalCliente;
    private String calleCliente;
    private String numeroCliente;
    private HashMap<Integer, Double> idsPesosProductosKg;
    private HashMap<Integer, Double> idsTiemposProductosMatriz;
    private HashMap<Integer, String> idsCodigosPostalesSucursales;

    public DireccionClienteProductosEnvioDTO(Integer codigoPaqueteria, Integer idCliente, String codigoPostalCliente, String calleCliente, String numeroCliente, HashMap<Integer, Double> idsPesosProductosKg, HashMap<Integer, Double> idsTiemposProductosMatriz, HashMap<Integer, String> idsCodigosPostalesSucursales) {
        this.codigoPaqueteria = codigoPaqueteria;
        this.idCliente = idCliente;
        this.codigoPostalCliente = codigoPostalCliente;
        this.calleCliente = calleCliente;
        this.numeroCliente = numeroCliente;
        this.idsPesosProductosKg = idsPesosProductosKg;
        this.idsTiemposProductosMatriz = idsTiemposProductosMatriz;
        this.idsCodigosPostalesSucursales = idsCodigosPostalesSucursales;
    }

    
    
    public Integer getCodigoPaqueteria() {
        return codigoPaqueteria;
    }

    public Integer getIdCliente() {
        return idCliente;
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

    public HashMap<Integer, Double> getIdsPesosProductosKg() {
        return idsPesosProductosKg;
    }

    public HashMap<Integer, Double> getIdsTiemposProductosMatriz() {
        return idsTiemposProductosMatriz;
    }

    public HashMap<Integer, String> getIdsCodigosPostalesSucursales() {
        return idsCodigosPostalesSucursales;
    }

    
    
    
    
}