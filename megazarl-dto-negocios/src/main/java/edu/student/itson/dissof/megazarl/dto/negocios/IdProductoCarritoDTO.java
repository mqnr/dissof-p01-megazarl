
package edu.student.itson.dissof.megazarl.dto.negocios;

/**
 * IdProductoCarritoDTO.java
 *
 * Clase DTO que contiene el ID de un producto en venta.
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
public class IdProductoCarritoDTO {
    
    /**
     * Objeto Long que representa el ID del producto en el carrito de compras.
     */
    private Long idProductoCarrito;

    /**
     * Construtor que recibe el ID del producto en el carrito de compras.
     * @param idProductoCarrito Objeto Long que representa el ID del producto en el carrito de compras.
     */
    public IdProductoCarritoDTO(Long idProductoCarrito) {
        this.idProductoCarrito = idProductoCarrito;
    }

    /**
     * Método que permite obtener el ID del producto en carrito.
     * @return Objeto Long que representa el ID del producto en el carrito de compras.
     */
    public Long getIdProductoCarrito() {
        return idProductoCarrito;
    }
    
    
    
    
}
