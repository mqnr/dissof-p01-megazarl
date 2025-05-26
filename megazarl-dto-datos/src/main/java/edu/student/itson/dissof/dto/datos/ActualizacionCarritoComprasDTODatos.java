
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import java.util.List;


/**
 * Clase DTO que contiene la información necesaria para actualizar un carrito de 
 * compras vigente de un cliente.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class ActualizacionCarritoComprasDTODatos {
    
    /**
     * Objeto Long que representa el ID del carrito de compras a actualizar.
     */
    private IdEntidadGenericoDatos id;
    
    private Boolean esVigente;
    
    /**
     * Objeto PaqueteriaDTO que representa la nueva paquetería del carrito de
     * compras.
     */
    private IdEntidadGenericoDatos idPaqueteria;
    
    /**
     * Constructor de la clase que recibe el ID del carrito de compras a actualizar.
     * @param id Objeto Long que representa el ID del carrito de compras a actualizar.
     */
    public ActualizacionCarritoComprasDTODatos(IdEntidadGenericoDatos id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el ID del carrito de compras a actualizar.
     * @return Objeto Long que representa el ID del carrito de compras a actualizar.
     */
    public IdEntidadGenericoDatos getId() {
        return id;
    }

    /**
     * Método que permite obetner la nueva paquetería del carrito de compras.
     * @return Objeto PaqueteriaDTO que representa la nueva paquetería del carrito de
     * compras.
     */
    public IdEntidadGenericoDatos getIdPaqueteria() {
        return idPaqueteria;
    }

    /**
     * Método que permite establecer un nuevo valor para el atributo paqueteria
     * del carrito de compras.
     * @param idPaqueteria Objeto Long que representa la nueva paquetería del carrito de
     * compras.
     */
    public void setIdPaqueteria(IdEntidadGenericoDatos idPaqueteria) {
        this.idPaqueteria = idPaqueteria;
    }
    
    public Boolean getEsVigente() {
        return esVigente;
    }

    public void setEsVigente(Boolean esVigente) {
        this.esVigente = esVigente;
    }

    
    /**
     * Método que permite determinar si este objeto DTO tiene paqueteria.
     * @return Valor true, si el valor del atributo paqueteria no es nulo, false en caso
     * contrario.
     */
    public boolean tieneIdPaqueteria(){
        return idPaqueteria != null;
    }
    
    public boolean tieneEsVigente(){
        return esVigente != null;
    }
    
    
    
}
