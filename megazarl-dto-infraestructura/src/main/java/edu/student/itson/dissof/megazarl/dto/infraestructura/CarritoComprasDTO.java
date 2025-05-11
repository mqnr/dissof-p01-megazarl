
package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.HashMap;

/**
 * CarritoComprasDTO.java
 *
 * Clase DTO que representa un carrito de compras en el sistema, que pertenece a un Cliente
 * y contiene productos con sus respectivas cantidades, así como una paquetería
 * seleccionada para el envío.
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
public class CarritoComprasDTO {

    /**
     * Objeto Long que representa el ID del carrito de compras.
     */
    private Long id;

    /**
     * Objeto ClienteDTO que representa el cliente asociado a este carrito de compras.
     */
    private ClienteDTO cliente;

    /**
     * Objeto PaqueteriaDTO que representa la paquetería asociada a este carrito de compras.
     */
    private PaqueteriaDTO paqueteria;

    /**
     * Objeto PedidoDTO que representa el pedido de productos asociado a este carrito de compras.
     */
    private PedidoDTO pedido;

    /**
     * Objeto HashMap que representa los productos y sus cantidades, donde la llave es el ProductoDTO
     * y el valor es un Integer representando su cantidad correspondiente.
     */
    private HashMap<ProductoDTO, Integer> productosCantidades;

    /**
     * Constructor que permite instanciar un objeto de tipo CarritoComprasDTO.
     * @param id                   Objeto Long que representa el ID del carrito de compras.
     * @param cliente              Objeto ClienteDTO que representa el cliente asociado a este carrito de compras.
     * @param paqueteria           Objeto PaqueteriaDTO que representa la paquetería asociada a este carrito de compras.
     * @param pedido               Objeto PedidoDTO que representa el pedido asociado a este carrito de compras.
     * @param productosCantidades  Objeto HashMap que representa los productos y sus cantidades.
     */
    public CarritoComprasDTO(
            Long id, 
            ClienteDTO cliente, 
            PaqueteriaDTO paqueteria, 
            PedidoDTO pedido, 
            HashMap<ProductoDTO, Integer> productosCantidades) {
        
        this.id = id;
        this.cliente = cliente;
        this.paqueteria = paqueteria;
        this.pedido = pedido;
        this.productosCantidades = productosCantidades;
    }
    
    /**
     * Constructor que permite instanciar un objeto de tipo CarritoComprasDTO.
     * @param cliente              Objeto ClienteDTO que representa el cliente asociado a este carrito de compras.
     * @param pedido               Objeto PedidoDTO que representa el pedido asociado a este carrito de compras.
     * @param productosCantidades  Objeto HashMap que representa los productos y sus cantidades.
     */
    public CarritoComprasDTO(
            ClienteDTO cliente, 
            PedidoDTO pedido, 
            HashMap<ProductoDTO, Integer> productosCantidades) {
        
        this.cliente = cliente;
        this.pedido = pedido;
        this.productosCantidades = productosCantidades;
    }

    /**
     * Método que permite establecer el id del carrito de compras.
     * @param id Objeto Long que representa el nuevo ID del carrito de compras.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Método que permite obtener el id del carrito de compras.
     * @return Objeto Long que representa el ID del carrito de compras.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obtener el cliente asociado a este carrito de compras.
     * @return Objeto ClienteDTO que representa el cliente asociado a este carrito de compras.
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Método que permite obtener la paquetería asociada a este carrito de compras.
     * @return Objeto PaqueteriaDTO que representa la paquetería asociada a este carrito de compras.
     */
    public PaqueteriaDTO getPaqueteria() {
        return paqueteria;
    }

    /**
     * Método que permite obtener el pedido asociado a este carrito de compras.
     * @return Objeto PedidoDTO que representa el pedido asociado a este carrito de compras.
     */
    public PedidoDTO getPedido() {
        return pedido;
    }

    /**
     * Método que permite obtener el objeto HashMap de productos y sus cantidades del carrito de compras.
     * @return Objeto HashMap donde la llave es un ProductoDTO y el valor un Integer que representa su cantidad.
     */
    public HashMap<ProductoDTO, Integer> getProductosCantidades() {
        return productosCantidades;
    }

    /**
     * Método que permite obtener el hash code del carrito de compras, a partir de su ID.
     * @return Dato int que representa el hash code del carrito de compras.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + java.util.Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a este carrito de compras, basándose en su ID.
     * @param obj Objeto a determinar si es igual a este carrito de compras.
     * @return Dato boolean, true si el objeto es igual al carrito de compras, false en
     * caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CarritoComprasDTO other = (CarritoComprasDTO) obj;
        return java.util.Objects.equals(this.id, other.id);
    }
}