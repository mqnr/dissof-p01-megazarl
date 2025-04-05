package edu.student.itson.dissof.megazarl.dto;

/**
 * DireccionMatrizDTO.java
 *
 * Clase que representa un objeto de transferencia de datos que contiene
 * la información de la dirección de la sucursal matriz de la empresa,
 * incluyendo código postal, calle y número.
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
public class DireccionMatrizDTO {
    private String codigoPostalMatriz;
    private String calleMatriz;
    private String numeroMatriz;

    public DireccionMatrizDTO(String codigoPostalMatriz, String calleMatriz, String numeroMatriz) {
        this.codigoPostalMatriz = codigoPostalMatriz;
        this.calleMatriz = calleMatriz;
        this.numeroMatriz = numeroMatriz;
    }

    public String getCodigoPostalMatriz() {
        return codigoPostalMatriz;
    }

    public String getCalleMatriz() {
        return calleMatriz;
    }

    public String getNumeroMatriz() {
        return numeroMatriz;
    }
}
