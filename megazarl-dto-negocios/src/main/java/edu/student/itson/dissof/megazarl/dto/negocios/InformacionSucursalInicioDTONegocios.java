package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * InformacionSucursalInicioDTONegocios.java

 Clase que representa un objeto de transferencia de datos que contiene
 la información resumida de una sucursal para ser mostrada en la página
 del caso de uso orden de compra, incluyendo datos básicos de la sucrusal
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 *
 */
public class InformacionSucursalInicioDTONegocios {
    
    /**
     * Objeto Long que representa el id de la sucursal.
     */
    private IdEntidadGenericoNegocios idSucursal;
    
    /**
     * Objeto Boolean que determina si la sucursal es matriz o no.
     */
    private Boolean esMatrizSucursal;
    
    /**
     * Objeto Long que representa el id de la dirección que tiene la sucursal.
     */
    private IdEntidadGenericoNegocios idDireccionSucursal;

    /**
     * Constructor de la clase que recibe informacion de la sucursal a mostrar 
     * en la pantalla del caso de uso orden de compra.
     * 
     * @param idSucursal                Objeto IdEntidadGenerico que representa el id de la sucursal.
     * @param esMatrizSucursal          Objeto Boolean que determina si la sucursal es una matriz o no.
     * @param idDireccionSucursal       Objeto IdEntidadGenerico que representa el id de la dirección que tiene la sucursal.
     */
    public InformacionSucursalInicioDTONegocios(IdEntidadGenericoNegocios idSucursal, Boolean esMatrizSucursal, IdEntidadGenericoNegocios idDireccionSucursal) {
        this.idSucursal = idSucursal;
        this.esMatrizSucursal = esMatrizSucursal;
        this.idDireccionSucursal = idDireccionSucursal;
    }

    /**
     * 
     * @return 
     */
    public IdEntidadGenericoNegocios getIdSucursal() {
        return idSucursal;
    }

    /**
     * 
     * @return 
     */
    public Boolean getEsMatrizSucursal() {
        return esMatrizSucursal;
    }

    /**
     * 
     * @return 
     */
    public IdEntidadGenericoNegocios getIdDireccionSucursal() {
        return idDireccionSucursal;
    }

    /**
     * 
     * @param esMatrizSucursal 
     */
    public void setEsMatrizSucursal(Boolean esMatrizSucursal) {
        this.esMatrizSucursal = esMatrizSucursal;
    }

}