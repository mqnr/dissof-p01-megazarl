
package edu.student.itson.dissof.megazarl.dto.negocios;

import java.util.List;

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
public abstract class CarritoComprasDTO {

    /**
     * Objeto Long que representa el ID del carrito de compras.
     */
    private Long id;
    
    private Boolean esVigente;



    public CarritoComprasDTO(
            Long id,
            Boolean esVigente) {
        
        this.id = id;
        this.esVigente = esVigente;
    }
    
    public CarritoComprasDTO(Long id) {  
        this.id = id;
    }
    
    public CarritoComprasDTO(Boolean esVigente) {
        
        this.esVigente = esVigente;
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

    public Boolean getEsVigente() {
        return esVigente;
    }

    public void setEsVigente(Boolean esVigente) {
        this.esVigente = esVigente;
    }

    /**
     * Método que permite obtener el cliente asociado a este carrito de compras.
     * @return Objeto ClienteDTO que representa el cliente asociado a este carrito de compras.
     */
    public abstract Long getIdCliente();

    /**
     * Método que permite obtener la paquetería asociada a este carrito de compras.
     * @return Objeto PaqueteriaDTO que representa la paquetería asociada a este carrito de compras.
     */
    public abstract Long getIdPaqueteria();

    /**
     * Método que permite obtener el objeto {@literal List<ProductoCarritoDTO>} que representa los productos dentro de este carrito.
     * @return Objeto {@literal List<ProductoCarritoDTO>} que representa los productos dentro de este carrito.
     */
    public abstract List<Long> getIdsProductosCarrito();

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