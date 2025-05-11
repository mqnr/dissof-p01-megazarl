
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO;


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
public class ActualizacionCarritoComprasDTO {
    
    /**
     * Objeto Long que representa el ID del carrito de compras a actualizar.
     */
    private Long id;
    
    /**
     * Objeto PaqueteriaDTO que representa la nueva paquetería del carrito de
     * compras.
     */
    private PaqueteriaDTO paqueteria;

    /**
     * Constructor de la clase que recibe el ID del carrito de compras a actualizar.
     * @param id Objeto Long que representa el ID del carrito de compras a actualizar.
     */
    public ActualizacionCarritoComprasDTO(Long id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el ID del carrito de compras a actualizar.
     * @return Objeto Long que representa el ID del carrito de compras a actualizar.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obetner la nueva paquetería del carrito de compras.
     * @return Objeto PaqueteriaDTO que representa la nueva paquetería del carrito de
     * compras.
     */
    public PaqueteriaDTO getPaqueteria() {
        return paqueteria;
    }

    /**
     * Método que permite establecer un nuevo valor para el atributo paqueteria
     * del carrito de compras.
     * @param paqueteria Objeto PaqueteriaDTO que representa la nueva paquetería del carrito de
     * compras.
     */
    public void setPaqueteria(PaqueteriaDTO paqueteria) {
        this.paqueteria = paqueteria;
    }
    
    /**
     * Método que permite determinar si este objeto DTO tiene paqueteria.
     * @return Valor true, si el valor del atributo paqueteria no es nulo, false en caso
     * contrario.
     */
    public boolean tienePaqueteria(){
        return paqueteria != null;
    }
    
    
}
