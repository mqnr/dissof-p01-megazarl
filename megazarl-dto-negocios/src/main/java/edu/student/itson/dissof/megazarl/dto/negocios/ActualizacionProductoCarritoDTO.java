
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;


public class ActualizacionProductoCarritoDTO {

    /**
     * Objeto Long que representa el ID del producto en el carrito.
     */
    private IdEntidadGenerico id;
    
    /**
     * Objeto Integer que representa la cantidad del producto en el carrito.
     */
    private Integer cantidad;
    
    /**
     * Objeto Long que representa el carrito compras al que pertenece el
     * producto.
     */
    private IdEntidadGenerico idCarritoCompras;
    
    /**
     * Objeto Long que representa el producto dentro del carrito.
     */
    private IdEntidadGenerico idProducto;

    /**
     * Constructor de la clase que recibe el ID.
     * @param id Objeto Long que representa el ID del producto en el carrito.
     */
    public ActualizacionProductoCarritoDTO(IdEntidadGenerico id) {
        this.id = id;
    }
    
    /**
     * Método que permite obtener el ID del producto en carrito.
     * @return Objeto Long que representa el ID del producto en carrito.
     */
    public IdEntidadGenerico getId() {
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
    public IdEntidadGenerico getIdCarritoCompras() {
        return idCarritoCompras;
    }

    /**
     * Método que permite obtener el producto dentro del carrito de compras.
     * @return Objeto Integer que representa la cantidad del producto en el carrito.
     */
    public IdEntidadGenerico getIdProducto() {
        return idProducto;
    }
    
    /**
     * Método que permite establecer el id del pedido.
     * @param id Objeto Long que representa el ID del pedido.
     */
    public void setId(IdEntidadGenerico id) {
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
