
package edu.student.itson.dissof.megazarl.administradorproductos.utils;

/**
 * Clase que permite realizar operaciones con objetos de tipo String.
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

public class CadenasTextoUtils {
    
    /**
     * Método que permite verificar si existe una subcadena común entre las
     * recibidas como parámetros.
     * @param cadena1 Objeto String que representa la primer cadena de texto a verificar.
     * @param cadena2 Objeto String que representa la segunda cadena de texto a verificar.
     * @return Valor booelano, true si existe una subcadena común, false en caso
     * contrario.
     */
    public static boolean verificarExisteSubcadenaComun(String cadena1, String cadena2) {
        
        int longitudCadena1 = cadena1.length();
        int longitudCadena2 = cadena2.length();
        
        int[][] coincidencias = new int[longitudCadena1 + 1][longitudCadena2 + 1];
        
        int longitudMaximaSubcadenaComun = 0;

        for (int i = 1; i <= longitudCadena1; i++) {
            for (int j = 1; j <= longitudCadena2; j++) {
                if (cadena1.charAt(i - 1) == cadena2.charAt(j - 1)) {
                    
                    // Se agrega uno al número de coincidencias actual
                    coincidencias[i][j] = coincidencias[i - 1][j - 1] + 1;
                    if (coincidencias[i][j] > longitudMaximaSubcadenaComun) {
                        longitudMaximaSubcadenaComun = coincidencias[i][j];
                    }
                } else {
                    coincidencias[i][j] = 0;
                }
            }
        }

        if (longitudMaximaSubcadenaComun < 3) {
            return false;
        }

        return true;
    }
    
}
