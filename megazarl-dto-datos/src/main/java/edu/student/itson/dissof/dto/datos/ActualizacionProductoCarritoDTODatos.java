
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;


public class ActualizacionProductoCarritoDTODatos {

    /**
     * Objeto IdEntidadGenericoDatos que representa el ID del producto en el carrito.
     */
    private IdEntidadGenericoDatos id;
    
    /**
     * Objeto Integer que representa la cantidad del producto en el carrito.
     */
    private Integer cantidad;
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el carrito compras al que pertenece el
 producto.
     */
    private IdEntidadGenericoDatos idCarritoCompras;
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el producto dentro del carrito.
     */
    private IdEntidadGenericoDatos idProducto;

    /**
     * Constructor de la clase que recibe el ID.
     * @param id Objeto Long que representa el ID del producto en el carrito.
     */
    public ActualizacionProductoCarritoDTODatos(IdEntidadGenericoDatos id) {
        this.id = id;
    }
    
    /**
     * Método que permite obtener el ID del producto en carrito.
     * @return Objeto Long que representa el ID del producto en carrito.
     */
    public IdEntidadGenericoDatos getId() {
        return id;
    }

    /**
     * Método que permite obtener la cantidad del producto en carrito.
     * @return Objeto Integer que representa la cantidad del producto en el carrito.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Método que permite obtener el carrto de compras al que pertence el producto.
     * @return Objeto Long que representa el carrito compras al que pertenece el
     * producto.
     */
    public IdEntidadGenericoDatos getIdCarritoCompras() {
        return idCarritoCompras;
    }

    /**
     * Método que permite obtener el producto dentro del carrito de compras.
     * @return Objeto Integer que representa la cantidad del producto en el carrito.
     */
    public IdEntidadGenericoDatos getIdProducto() {
        return idProducto;
    }
    
    /**
     * Método que permite establecer el id del pedido.
     * @param id Objeto Long que representa el ID del pedido.
     */
    public void setId(IdEntidadGenericoDatos id) {
        this.id = id;
    }

    /**
     * Método que permite establecer una cantidad para el producto.
     * @param cantidad 
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * Método que permite determinar si el producto en carrito de compras tiene cantidad
     * @return 
     */
    public boolean tieneCantidad(){
        return cantidad != null;
    }
}
