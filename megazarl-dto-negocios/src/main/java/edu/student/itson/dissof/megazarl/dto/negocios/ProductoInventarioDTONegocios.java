
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import java.util.Objects;

/**
 * ProveedorInventarioDTO.java
 * 
 * Clase DTO representa una unidad de un producto en el inventario de una sucursal
 * específica

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

public class ProductoInventarioDTONegocios {

    /**
     * Objeto Long que representa el id del producto en inventario
     */
    private IdEntidadGenericoNegocios id;
    
    private Boolean apartado;
    
    private IdEntidadGenericoNegocios idProducto;
    
    private IdEntidadGenericoNegocios idSucursal;

    /**
     * Método que permite obtener el id del producto en inventario
     * @return Objeto Integer que representa el id del producto en inventario
     */
    public IdEntidadGenericoNegocios getId() {
        return id;
    }
    
    public ProductoInventarioDTONegocios(
            IdEntidadGenericoNegocios id, 
            Boolean apartado,
            IdEntidadGenericoNegocios idProducto,
            IdEntidadGenericoNegocios idSucursal){
        
        this.id = id;
        this.apartado = apartado;
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
    }
    
    public ProductoInventarioDTONegocios(
            Boolean apartado,
            IdEntidadGenericoNegocios idProducto,
            IdEntidadGenericoNegocios idSucursal){
        
        this.apartado = apartado;
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
    }
    
    /**
     * Mètodo que permite establecer el ID del producto en inventario.
     * @param id Objeto Long que representa el ID del producto en inventario.
     */
    public void setId(IdEntidadGenericoNegocios id) {
        this.id = id;
    }

    public Boolean getApartado() {
        return apartado;
    }

    public IdEntidadGenericoNegocios getIdProducto() {
        return idProducto;
    }

    public IdEntidadGenericoNegocios getIdSucursal() {
        return idSucursal;
    }

    /**
     * Método que permite obtener el hash code del producto en inventario, a partir de su Id
     * @return Dato int que representa el hash code del producto en inventario
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a este producto en inventario, basándose en su id
     * @param obj Objeto a determinar si es igual a este producto en inventario
     * @return Dato boolean, true si el objeto es igual al producto en inventario, false en
     * caso contrario
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
        final ProductoInventarioDTONegocios other = (ProductoInventarioDTONegocios) obj;
        return Objects.equals(this.id, other.id);
    }
}
