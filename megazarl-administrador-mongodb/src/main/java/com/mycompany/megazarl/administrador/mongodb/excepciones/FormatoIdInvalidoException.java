
package com.mycompany.megazarl.administrador.mongodb.excepciones;


public class FormatoIdInvalidoException extends Exception{
    /**
     * Constructor por defecto.
     */
    public FormatoIdInvalidoException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public FormatoIdInvalidoException(String message) {
        super(message);
    }
}
