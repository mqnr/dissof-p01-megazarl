
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenerico;
import java.util.List;
import java.util.Objects;

/**
 * 
 * SucursalDTO.java
 *
 * Clase DTO que representa una sucursal física de la empresa en el sistema,
 * con su dirección completa y datos de identificación para la gestión de inventario.
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
public abstract class SucursalDTO {
    
    /**
     * Objeto IdEntidadGenerico que representa el ID de la sucursal
     */
    private IdEntidadGenerico id;
    
    /**
     * Objeto Boolean que indica si la sucursal es la Matriz
     */
    private Boolean esMatriz;
    
    
    /**
     * Constructor que permite instanciar un objeto de tipo SucursalDTO.
     * @param id           Objeto IdEntidadGenerico que representa el ID de la sucursal.
     * @param esMatriz     Objeto Boolean que indica si la sucursal es la Matriz.
     */
    public SucursalDTO(
            IdEntidadGenerico id,
            Boolean esMatriz) {
        
        this.id = id;
        this.esMatriz = esMatriz;
    }
    
    /**
     * Constructor que permite instanciar un objeto de tipo SucursalDTO, sin ID
     * @param esMatriz     Objeto Boolean que indica si la sucursal es la Matriz.
     */
    public SucursalDTO(
            Boolean esMatriz) {
        
        this.esMatriz = esMatriz;
    }
    
    /**
     * Método que permite obtener el ID de la sucursal.
     * @return Objeto IdEntidadGenerico que representa el id de la sucursal.
     */
    public IdEntidadGenerico getId() {
        return id;
    }
    
    /**
     * Método que permite obtener si la sucursal es la Matriz.
     * @return Objeto Boolean que indica si la sucursal es la Matriz.
     */
    public Boolean esMatriz() {
        return esMatriz;
    }

    
    public abstract List<IdEntidadGenerico> getIdsProductosInventario();
    public abstract IdEntidadGenerico getIdDireccion();

    /**
     * Método que permite establecer el ID de la sucursal.
     * @param id Objeto IdEntidadGenerico que representa el ID de la sucursal.
     */
    public void setId(IdEntidadGenerico id) {
        this.id = id;
    }
    
    /**
     * Método que permite obtener el hash code de la sucursal, a partir de su ID.
     * @return Dato int que representa el hash code de la sucursal.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a esta sucursal, basándose en su id 
     * @param obj Objeto a determinar si es igual a esta sucursal
     * @return Dato boolean, true si el objeto es igual a la sucursal, false en
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
        final SucursalDTO other = (SucursalDTO) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
