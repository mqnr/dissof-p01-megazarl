
package edu.student.itson.dissof.megazarl.dto.negocios;

import java.util.List;

/**
 * InformacionDerivadaCPDireccionDTONegocios.java

 Clase DTO que contiene la información derivada del Código Postal de una dirección.
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
public class InformacionDerivadaCPDireccionDTONegocios {
    
    /**
     * Objeto {@literal List<String>} que representa la lista de colonias asocidas
     * a la dirección.
     */
    private List<String> colonias;
    /**
     * Objeto String que representa la ciudad asociada a la diección.
     */
    private String ciudad;
    
    /**
     * Objeto String que representa el estado asociado a la dirección.
     */
    private String estado;

    /**
     * Constructor que recibe las colonias, ciudad y es el estado de la dirección.
     * @param colonias  Objeto {@literal List<String>} que representa la lista de colonias asocidas
     *                  a la dirección.
     * @param ciudad    Objeto String que representa la ciudad de la dirección.
     * @param estado    Objeto String que representa el estado de la dirección.
     */
    public InformacionDerivadaCPDireccionDTONegocios(
            List<String> colonias,
            String ciudad, 
            String estado) {
        
        this.colonias = colonias;
        this.ciudad = ciudad;
        this.estado = estado;
    }
    
    /**
    * Constructor que recibe la ciudad y es el estado de la dirección.
    * @param ciudad    Objeto String que representa la ciudad de la dirección.
    * @param estado    Objeto String que representa el estado de la dirección.
    */
    public InformacionDerivadaCPDireccionDTONegocios(
            String ciudad, 
            String estado) {
        
        this.ciudad = ciudad;
        this.estado = estado;
    }

    /**
     * Método que permite obtener las colonias asociadas a la dirección.
     * @return Objeto {@literal List<String>} que representa la lista de 
     * colonias asocidas a la dirección.
     */
    public List<String> getColonias() {
        return colonias;
    }

    
    /**
     * Método que permite obtener la ciudad de la dirección.
     * @return Objeto String que representa la ciudad de la dirección.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Método que permite obtener el estado de la dirección.
     * @return Objeto String que representa el estado de la dirección.
     */
    public String getEstado() {
        return estado;
    }
    
    
    
}
