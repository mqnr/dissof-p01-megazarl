
package edu.student.itson.dissof.megazarl.dto.negocios;

import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;

/**
 * IdDireccionDTONegocios.java

 Clase DTO que contiene el ID de una dirección.
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
public class IdDireccionDTONegocios {
    /**
     * Objeto Long que representa el ID de la dirección.
     */
    private IdEntidadGenericoNegocios idDireccion;

    /**
     * Consutructor de la clase que recibe el ID de la dirección.
     * @param idDireccion Objeto Long que representa el ID de la dirección.
     */
    public IdDireccionDTONegocios(IdEntidadGenericoNegocios idDireccion) {
        this.idDireccion = idDireccion;
    }

    /**
     * Método que permite obtener el ID de la dirección.
     * @return Objeto Long que representa el ID de la dirección.
     */
    public IdEntidadGenericoNegocios getIdDireccion() {
        return idDireccion;
    }
    
    
}
