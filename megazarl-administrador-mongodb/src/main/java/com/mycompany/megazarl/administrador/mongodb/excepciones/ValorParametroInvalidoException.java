
package com.mycompany.megazarl.administrador.mongodb.excepciones;




public class ValorParametroInvalidoException extends Exception{
    /**
     * Constructor por defecto.
     */
    public ValorParametroInvalidoException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ValorParametroInvalidoException(String message) {
        super(message);
    }
}
