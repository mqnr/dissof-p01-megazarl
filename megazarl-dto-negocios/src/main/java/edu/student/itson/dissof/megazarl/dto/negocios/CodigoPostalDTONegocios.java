
package edu.student.itson.dissof.megazarl.dto.negocios;


public class CodigoPostalDTONegocios {
    
    /**
     * Objeto String que representa el Código Postal de una dirección.
     */
    private String codigoPostal;

    /**
     * Constructor de la clase que recibe el Código Postal.
     * @param codigoPostal Objeto String que representa el Código Postal de una dirección.
     */
    public CodigoPostalDTONegocios(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Método que permite obtener el Código Postal.
     * @return Objeto String que representa el Código Postal de una dirección.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }
    
    
}
