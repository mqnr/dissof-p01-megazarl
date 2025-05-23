
package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * Clase DTO que contiene los IDs de un proveedor y una paquetería, necesarios
 * para realizar el cálculo de tiempo de envío de productos por una paquetería
 * desde un proveedor a la Matriz de la empresa.
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
public class IdProveedorPaqueteriaDTODatos {
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID de un proveedor.
     */
    private IdEntidadGenericoDatos idProveedor;
    
    /**
     * Objeto IdEntidadGenericoDatos que representa el ID de una paquetería.
     */
    private IdEntidadGenericoDatos idPaqueteria;

    /**
     * Constructor de la clase que recibe un ID de proveedor y un ID de paquetería.
     * @param idProveedor   Objeto IdEntidadGenericoDatos que representa el ID de un proveedor.
     * @param idPaqueteria  Objeto IdEntidadGenericoDatos que representa el ID de una paquetería.
     */
    public IdProveedorPaqueteriaDTODatos(
            IdEntidadGenericoDatos idProveedor, 
            IdEntidadGenericoDatos idPaqueteria){
        
        this.idProveedor = idPaqueteria;
        this.idPaqueteria = idPaqueteria;
    }
            
    /**
     * Método que permite obtener el ID del proveedor.
     * @return Objeto IdEntidadGenericoDatos que representa el ID de un proveedor.
     */
    public IdEntidadGenericoDatos getIdProveedor() {
        return idProveedor;
    }

    /**
     * Método que pemite obtener el ID de la paquetería.
     * @return Objeto IdEntidadGenericoDatos que representa el ID de una paquetería.s
     */
    public IdEntidadGenericoDatos getIdPaqueteria() {
        return idPaqueteria;
    }
     
}
