
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * InformacionProductoAgregarCarritoDTONegocios.java
 
 Clase DTO que contiene la información necesaria para agregar una cierta cantidad
 de un producto del carrito de compras de un cliente.
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
public class InformacionProductoAgregarCarritoDTONegocios {
    /**
     * Objeto Long que representa el ID del cliente del que se va a agregar el
     * producto.
     */
    private IdEntidadGenericoNegocios idCliente;
    
    /**
     * Objeto Long que representa el ID del producto a agregar.
     */
    private IdEntidadGenericoNegocios idProducto;
    
    /**
     * Objeto int que representa la cantidad del producto a agregar.
     */
    private int cantidad;
    
    /**
     * Constructor de la clase que recibe la información necesaria para agregar
     * el producto.
     * @param idCliente     Objeto Long que representa el ID del cliente.
     * @param idProducto    Objeto Long que representa el ID del producto a eliminar.
     * @param cantidad      Objeto int que representa la cantidad del producto a eliminar.
     */
    public InformacionProductoAgregarCarritoDTONegocios(
            IdEntidadGenericoNegocios idCliente, 
            IdEntidadGenericoNegocios idProducto,
            int cantidad){
        
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        
    }

    /**
     * Método que permite obtener el ID del cliente del que se va a agregar el
     * producto. 
     * @return Objeto Long que representa el ID del cliente.
     */
    public IdEntidadGenericoNegocios getIdCliente() {
        return idCliente;
    }

    /**
     * Método que permite obtener el ID del producto a agregar.
     * @return Objeto Long que representa el ID del producto a agregar.
     */
    public IdEntidadGenericoNegocios getIdProducto() {
        return idProducto;
    }

    /**
     * Método que permite obtener la cantidad del producto a agregar.
     * @return Objeto int que representa la cantidad del producto a agregar.
     */
    public int getCantidad() {
        return cantidad;
    }
}
