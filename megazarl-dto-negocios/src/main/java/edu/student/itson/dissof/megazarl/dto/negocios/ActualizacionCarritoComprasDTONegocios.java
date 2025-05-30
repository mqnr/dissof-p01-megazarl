
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;


/**
 * Clase DTO que contiene la información necesaria para actualizar un carrito de 
 * compras vigente de un cliente.
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
public class ActualizacionCarritoComprasDTONegocios {
    
    /**
     * Objeto Long que representa el ID del carrito de compras a actualizar.
     */
    private IdEntidadGenericoNegocios id;
    
    private Boolean esVigente;
    
    /**
     * Objeto PaqueteriaDTONegocios que representa la nueva paquetería del carrito de
 compras.
     */
    private IdEntidadGenericoNegocios idPaqueteria;
    
    private PaqueteriaDTONegocios paqueteria;

    public PaqueteriaDTONegocios getPaqueteria() {
        return paqueteria;
    }

    public void setPaqueteria(PaqueteriaDTONegocios paqueteria) {
        this.paqueteria = paqueteria;
    }

    
    
    
    /**
     * Constructor de la clase que recibe el ID del carrito de compras a actualizar.
     * @param id Objeto IdEntidadGenericoNegocios que representa el ID del carrito de compras a actualizar.
     */
    public ActualizacionCarritoComprasDTONegocios(IdEntidadGenericoNegocios id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el ID del carrito de compras a actualizar.
     * @return Objeto IdEntidadGenericoNegocios que representa el ID del carrito de compras a actualizar.
     */
    public IdEntidadGenericoNegocios getId() {
        return id;
    }

    /**
     * Método que permite obetner la nueva paquetería del carrito de compras.
     * @return Objeto IdEntidadGenericoNegocios que representa la nueva paquetería del carrito de
 compras.
     */
    public IdEntidadGenericoNegocios getIdPaqueteria() {
        return idPaqueteria;
    }

    /**
     * Método que permite establecer un nuevo valor para el atributo paqueteria
     * del carrito de compras.
     * @param idPaqueteria Objeto Long que representa la nueva paquetería del carrito de
     * compras.
     */
    public void setIdPaqueteria(IdEntidadGenericoNegocios idPaqueteria) {
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
    
    public boolean tienePaqueteria(){
        return paqueteria != null;
    }
    
    public boolean tieneEsVigente(){
        return esVigente != null;
    }
    
    
}
