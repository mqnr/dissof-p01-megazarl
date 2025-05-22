
package edu.student.itson.dissof.megazarl.dto.negocios;


import java.util.HashMap;
import java.util.List;
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
public abstract class PedidoDTO {

    /**
     * Objeto Long que representa el ID del pedido.
     */
    private Long id;

    
    /**
     * Objeto String que representa el estado actual del pedido.
     */
    private String estado;

    /**
     * Constructor que permite instanciar un objeto de tipo PedidoDTO.
     * @param id                      Objeto Long que representa el ID del pedido.
     *                                que representa los productos en inventario requeridos por el pedido.
     * @param estado                  Objeto EstadoPedido que representa el estado actual del pedido
     */
    public PedidoDTO(
            Long id, 
            String estado) {
        
        this.id = id;
        this.estado = estado;
    }
    
    /**
     * Constructor que permite instanciar un objeto de tipo PedidoDTO.
     * @param estado                  Objeto EstadoPedido que representa el estado actual del pedido.
     */
    public PedidoDTO(String estado) {
        
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
     * Método que permite obtener el estado actual del pedido.
     * @return Objeto EstadoPedido que representa el estado actual del pedido.
     */
    public String getEstado() {
        return estado;
    }
    
    /**
     * Método que permite obtener el ID del cliente que realiza el pedido.
     * @return Objeto Long que representa el ID del cliente que realiza el pedido.
     */
    public abstract Long getIdCliente();

    public abstract Long getIdPaqueteria();
    
    public abstract List<Long> getIdsProductosPedido();
    
    public abstract void setIdCliente(Long idClienteDTO);
    
    
    
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