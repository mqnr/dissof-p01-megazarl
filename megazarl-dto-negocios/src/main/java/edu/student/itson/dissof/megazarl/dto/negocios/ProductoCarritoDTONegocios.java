
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * ProductoCarritoDTONegocios.java

 Clase DTO que representa un producto en un carrito de compras de un cliente.
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
public abstract class ProductoCarritoDTONegocios {
    
    /**
     * Objeto Long que representa el ID del producto en el carrito.
     */
    private IdEntidadGenericoNegocios id;
    
    /**
     * Objeto Integer que representa la cantidad del producto en el carrito.
     */
    private Integer cantidad;
    

    /**
     * Constructor de la clase que recibe los valores de todos sus atributos.
     * @param id Objeto Long que representa el ID del producto en carrito.
     * @param cantidad Objeto Integer que representa la cantidad del producto en el carrito.
     */
    public ProductoCarritoDTONegocios(
            IdEntidadGenericoNegocios id, 
            Integer cantidad) {
        
        this.id = id;
        this.cantidad = cantidad;
    }
    
    /**
     * Constructor de la clase que recibe los valores de todos sus atributos, excepto el ID.
     * @param cantidad Objeto Integer que representa la cantidad del producto en el carrito.
     */
    public ProductoCarritoDTONegocios(
            Integer cantidad) {
        
        this.cantidad = cantidad;
    }
    

    /**
     * Método que permite obtener el ID del producto en carrito.
     * @return Objeto Long que representa el ID del producto en carrito.
     */
    public IdEntidadGenericoNegocios getId() {
        return id;
    }

    /**
     * Método que permite obtener la cantidad del producto en carrito.
     * @return Objeto Integer que representa la cantidad del producto en el carrito.
     */
    public Integer getCantidad() {
        return cantidad;
    }


    public abstract IdEntidadGenericoNegocios getIdCarritoCompras();

    public abstract IdEntidadGenericoNegocios getIdProducto();

    /**
     * Método que permite obtener el ID del producto en carrito.
     * @param id Objeto Long que representa el ID del producto en carrito.
     */
    public void setId(IdEntidadGenericoNegocios id) {
        this.id = id;
    }
    
    
    
}
