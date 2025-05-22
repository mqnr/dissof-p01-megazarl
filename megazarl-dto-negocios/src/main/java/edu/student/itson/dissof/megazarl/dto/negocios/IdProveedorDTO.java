
package edu.student.itson.dissof.megazarl.dto.negocios;

/**
 * Clase DTO que contiene el ID de un proveedor registrado.
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
public class IdProveedorDTO {
    
    /**
     * Objeto Long que representa el ID de un proveedor.
     */
    private Long idProveedor;

    /**
     * Constructor de la clase que recibe el ID de proveedor.
     * @param idProveedor Objeto Long que representa el ID de un proveedor.
     */
    public IdProveedorDTO(Long idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    /**
     * Método que permite obtener el ID de un proveedor.
     * @return Objeto Long que representa el ID de un proveedor
     */
    public Long getIdProveedor() {
        return idProveedor;
    }
    
    
}
