
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
public abstract class ProductoCarritoDTO {
    
    /**
     * Objeto Long que representa el ID del producto en el carrito.
     */
    private Long id;
    
    /**
     * Objeto Integer que representa la cantidad del producto en el carrito.
     */
    private Integer cantidad;
    

    /**
     * Constructor de la clase que recibe los valores de todos sus atributos.
     * @param id Objeto Long que representa el ID del producto en carrito.
     * @param cantidad Objeto Integer que representa la cantidad del producto en el carrito.
     */
    public ProductoCarritoDTO(
            Long id, 
            Integer cantidad) {
        
        this.id = id;
        this.cantidad = cantidad;
    }
    
    /**
     * Constructor de la clase que recibe los valores de todos sus atributos, excepto el ID.
     * @param cantidad Objeto Integer que representa la cantidad del producto en el carrito.
     */
    public ProductoCarritoDTO(
            Integer cantidad) {
        
        this.cantidad = cantidad;
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


    public abstract IdCarritoComprasDTO getIdCarritoCompras();

    public abstract IdProductoDTO getIdProducto();

    /**
     * Método que permite obtener el ID del producto en carrito.
     * @param id Objeto Long que representa el ID del producto en carrito.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
