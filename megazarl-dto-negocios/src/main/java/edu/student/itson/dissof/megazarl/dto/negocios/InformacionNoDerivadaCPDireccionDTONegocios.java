package edu.student.itson.dissof.megazarl.dto.negocios;

/**
 * InformacionNoDerivadaCPDireccionDTONegocios.java

 Clase que representa un objeto de transferencia de datos que contiene
 la información no derivada de una dirección, incluyendo el número, calle
 y código postal.
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
public class InformacionNoDerivadaCPDireccionDTONegocios {
    
    /**
     * Objeto String que representa el número de la dirección.
     */
    private String numero;
    
    /**
     * Objeto String que representa la calle de la dirección.
     */
    private String calle;
    
    /**
     * Objeto String que representa la colonia de la dirección.
     */
    private String colonia;
    
    /**
     * Objeto String que representa el Código Postal de la dirección.
     */
    private String codigoPostal;

    /**
     * Constructor de la clase que recibe el número, calle, colonua y Código Póstal de la dirección.
     * @param numero        Objeto String que representa el número de la dirección.
     * @param calle         Objeto String que representa la calle de la dirección.
     * @param colonia       Objeto String que representa la colonia de la dirección.
     * @param codigoPostal  Objeto String que representa el Código Postal de la dirección.
     */
    public InformacionNoDerivadaCPDireccionDTONegocios(
            String numero,
            String calle,
            String colonia, 
            String codigoPostal) {
        
        this.numero = numero;
        this.calle = calle;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
    }

    /**
     * Método que premite obetner el número de la dirección.
     * @return Objeto String que representa el número de la dirección.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Método que permite obtener la calle de la dirección.
     * @return Objeto String que representa la calle de la dirección.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Método que permite obtener la colonia de la dirección.
     * @return Objeto String que representa la colonia de la dirección.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Método que permite obetner el Código Postal de la dirección.
     * @return Objeto String que representa el Código Postal de la dirección.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }
    
}
