
package com.mycompany.megazarl.administrador.mongodb.excepciones;


public class FormatoInvalidoIdConversionException extends Exception{
    /**
     * Constructor por defecto.
     */
    public FormatoInvalidoIdConversionException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public FormatoInvalidoIdConversionException(String message) {
        super(message);
    }
}
