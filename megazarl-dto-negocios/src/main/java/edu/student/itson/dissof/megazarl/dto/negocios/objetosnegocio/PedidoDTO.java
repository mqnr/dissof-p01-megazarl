
package edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio;

import edu.student.itson.dissof.megazarl.dto.negocio.enumeradores.EstadoPedido;
import java.util.HashMap;
import java.util.Objects;

/**
 * PedidoDTO.java
 *
 * Clase DTO que representa un pedido realizado en el sistema, conteniendo los productos
 * solicitados, la paquetería seleccionada y el estado actual del envío.
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
public class PedidoDTO {

    /**
     * Objeto Long que representa el ID del pedido.
     */
    private Long id;
    
    /**
     * Objeto Long que representa el ID del cliente que realiza el pedido.
     */
    private Long idCliente;

    /**
     * Objeto {@literal HashMap<ProductoInventarioDTO, Boolean>} que representa los productos en inventario requeridos por el pedido,
     * donde la llave es el ProductoInventarioDTO y el valor es un Boolean que representa si el producto ya fue enviado a la Matriz.
     */
    private HashMap<ProductoInventarioDTO, Boolean> mapaProductosRequeridos;

    /**
     * Objeto PaqueteriaDTO que representa la paquetería utilizada para el pedido.
     */
    private PaqueteriaDTO paqueteria;

    /**
     * Objeto CarritoComprasDTO que representa el carrito de compras al que pertenece el pedido.
     */
    private CarritoComprasDTO carritoCompras;
    
    /**
     * Objeto EstadoPedido que representa el estado actual del pedido.
     */
    private EstadoPedido estado;

    /**
     * Constructor que permite instanciar un objeto de tipo PedidoDTO.
     * @param id                      Objeto Long que representa el ID del pedido.
     * @param idCliente               Objeto Long que representa el ID del cliente.
     * @param mapaProductosRequeridos Objeto {@literal HashMap<ProductoInventarioDTO, Boolean>}
     *                                que representa los productos en inventario requeridos por el pedido.
     * @param paqueteria              Objeto PaqueteriaDTO que representa la paquetería utilizada para el pedido
     * @param estado                  Objeto EstadoPedido que representa el estado actual del pedido
     */
    public PedidoDTO(
            Long id, 
            Long idCliente,
            HashMap<ProductoInventarioDTO, Boolean> mapaProductosRequeridos,
            PaqueteriaDTO paqueteria, 
            EstadoPedido estado) {
        
        this.id = id;
        this.idCliente = idCliente;
        this.mapaProductosRequeridos = mapaProductosRequeridos;
        this.paqueteria = paqueteria;
        this.estado = estado;
    }
    
    /**
     * Constructor que permite instanciar un objeto de tipo PedidoDTO.
     * @param idCliente               Objeto Long que representa el ID del cliente.
     * @param mapaProductosRequeridos Objeto {@literal HashMap<ProductoInventarioDTO, Boolean>}
     *                                que representa los productos en inventario requeridos por el pedido.
     * @param paqueteria              Objeto PaqueteriaDTO que representa la paquetería utilizada para el pedido.
     * @param estado                  Objeto EstadoPedido que representa el estado actual del pedido.
     */
    public PedidoDTO(
            Long idCliente,
            HashMap<ProductoInventarioDTO, Boolean> mapaProductosRequeridos,
            PaqueteriaDTO paqueteria,
            EstadoPedido estado) {
        
        this.idCliente = idCliente;
        this.mapaProductosRequeridos = mapaProductosRequeridos;
        this.paqueteria = paqueteria;
        this.estado = estado;
    }

    /**
     * Método que permite establecer el id del pedido.
     * @param id Objeto Long que representa el ID del pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Método que permite obtener el ID del pedido.
     * @return Objeto Long que representa el ID del pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obtener el ID del cliente que realiza el pedido.
     * @return Objeto Long que representa el ID del cliente que realiza el pedido.
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Método que permite establecer el ID del cliente que realiza el pedido.
     * @param idCliente Objeto Long que representa el ID del cliente que realiza el pedido.
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
    /**
     * Método que permite obtener los productos en inventario requeridos por el pedido.
     * @return Objeto {@literal HashMap<ProductoInventarioDTO, Boolean>} que representa los productos en inventario requeridos por el pedido.
     */
    public HashMap<ProductoInventarioDTO, Boolean> getMapaProductosRequeridos() {
        return mapaProductosRequeridos;
    }

    /**
     * Método que permite obtener la paquetería utilizada para el pedido.
     * @return Objeto PaqueteriaDTO que representa la paquetería utilizada para el pedido.
     */
    public PaqueteriaDTO getPaqueteria() {
        return paqueteria;
    }

    /**
     * Método que permite obtener el carrito de compras al que pertenece el pedido.
     * @return Objeto CarritoComprasDTO que representa el carrito de compras al que pertenece el pedido.
     */
    public CarritoComprasDTO getCarritoCompras() {
        return carritoCompras;
    }
    
    /**
     * Método que permite obtener el estado actual del pedido.
     * @return Objeto EstadoPedido que representa el estado actual del pedido.
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Método que permite obtener el hash code del pedido, a partir de su ID.
     * @return Dato int que representa el hash code del pedido.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual a este pedido,
     * basándose en su ID.
     * @param obj Objeto a determinar si es igual a este pedido.
     * @return Dato boolean, true si el objeto es igual al pedido, false en caso contrario.
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
        final PedidoDTO other = (PedidoDTO) obj;
        return Objects.equals(this.id, other.id);
    }
}