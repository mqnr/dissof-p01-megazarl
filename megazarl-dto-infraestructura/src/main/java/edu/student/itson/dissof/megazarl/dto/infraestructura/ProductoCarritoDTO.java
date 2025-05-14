
package edu.student.itson.dissof.megazarl.dto.infraestructura;

/**
 * ProductoCarritoDTO.java
 *
 * Clase DTO que representa un producto en un carrito de compras de un cliente.
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
public class ProductoCarritoDTO {
    
    /**
     * Objeto Long que representa el ID del producto en el carrito.
     */
    private Long id;
    
    /**
     * Objeto Integer que representa la cantidad del producto en el carrito.
     */
    private Integer cantidad;
    
    /**
     * Objeto CarritoComprasDTO que representa el carrito compras al que pertenece el
     * producto.
     */
    private CarritoComprasDTO carritoCompras;
    
    /**
     * Objeto ProductoDTO que representa el producto dentro del carrito.
     */
    private ProductoDTO producto;

    /**
     * Constructor de la clase que recibe los valores de todos sus atributos.
     * @param id Objeto Long que representa el ID del producto en carrito.
     * @param cantidad Objeto Integer que representa la cantidad del producto en el carrito.
     * @param carritoCompras Objeto CarritoComprasDTO que representa el carrito compras al que pertenece el
     * producto.
     * @param producto Objeto ProductoDTO que representa el producto dentro del carrito.
     */
    public ProductoCarritoDTO(
            Long id, 
            Integer cantidad,
            CarritoComprasDTO carritoCompras,
            ProductoDTO producto) {
        
        this.id = id;
        this.cantidad = cantidad;
        this.carritoCompras = carritoCompras;
        this.producto = producto;
    }
    
    /**
     * Constructor de la clase que recibe los valores de todos sus atributos, excepto el ID.
     * @param cantidad Objeto Integer que representa la cantidad del producto en el carrito.
     * @param carritoCompras Objeto CarritoComprasDTO que representa el carrito compras al que pertenece el
     * producto.
     * @param producto Objeto ProductoDTO que representa el producto dentro del carrito.
     */
    public ProductoCarritoDTO(
            Integer cantidad,
            CarritoComprasDTO carritoCompras,
            ProductoDTO producto) {
        
        this.cantidad = cantidad;
        this.carritoCompras = carritoCompras;
        this.producto = producto;
    }
    

    /**
     * Método que permite obtener el ID del producto en carrito.
     * @return Objeto Long que representa el ID del producto en carrito.
     */
    public Long getId() {
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
     * @return Objeto CarritoComprasDTO que representa el carrito compras al que pertenece el
     * producto.
     */
    public CarritoComprasDTO getCarritoCompras() {
        return carritoCompras;
    }

    /**
     * Método que permite obtener el producto dentro del carrito de compras.
     * @return 
     */
    public ProductoDTO getProducto() {
        return producto;
    }

    /**
     * Método que permite obtener el ID del producto en carrito.
     * @param id Objeto Long que representa el ID del producto en carrito.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
     
}
