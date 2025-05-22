
package com.mycompany.megazarl.administrador.mongodb.excepciones;


public class RegistroMismoIdExisteException extends Exception{
    /**
     * Constructor por defecto.
     */
    public RegistroMismoIdExisteException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public RegistroMismoIdExisteException(String message) {
        super(message);
    }
}
