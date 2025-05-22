
package com.mycompany.megazarl.administrador.mongodb.utils;

import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;


public class ConversorIds {
    
    public static String convertirIdLongAHex(Long idLong) throws FormatoInvalidoIdConversionException{
        
        if (idLong == null) {
            throw new FormatoInvalidoIdConversionException("El valor del ID de tipo Long es nulo.");
        }

        return String.format("%016x", idLong);
    }
    
    public static Long covertirIdHexALong(String idHex) throws FormatoInvalidoIdConversionException{
        
        if (idHex == null) {
            throw new FormatoInvalidoIdConversionException("El valor del ID en formato hexadecimal es nulo.");
        }
        if (idHex.length() != 16) {
            throw new FormatoInvalidoIdConversionException("La cadena hexadecimal debe tener 16 dígitos, pero se recibió: " + idHex.length());
        }

         try {
            return Long.parseUnsignedLong(idHex.toLowerCase(), 16); 
            
        } catch (NumberFormatException ex) {
            throw new FormatoInvalidoIdConversionException("La cadena no es un hexadecimal válido: " + idHex);
        
        }
    }
}
