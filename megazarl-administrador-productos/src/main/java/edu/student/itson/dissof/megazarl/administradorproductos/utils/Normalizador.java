
package edu.student.itson.dissof.megazarl.administradorproductos.utils;

import java.text.Normalizer;

/**
 * Clase que permite visualizar la información detallada de un producto, así como
 * seleccionar una cantidad de unidades para añadirlas al carrito de compras.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class Normalizador {
    
    public static String quitarAcentosCadenaTexto(String cadena){
        
        cadena = cadena.replace("ñ", "\001").replace("Ñ", "\002");
    
        String cadenaSinAcentos = Normalizer.normalize(cadena, Normalizer.Form.NFD);
        
        cadenaSinAcentos = cadenaSinAcentos.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        cadenaSinAcentos = cadenaSinAcentos.replace("\001", "ñ").replace("\002", "Ñ");
        
        return cadenaSinAcentos;
        
    }
}
