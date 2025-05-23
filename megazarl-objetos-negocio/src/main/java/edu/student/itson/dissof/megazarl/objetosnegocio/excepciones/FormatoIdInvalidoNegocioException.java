
package edu.student.itson.dissof.megazarl.objetosnegocio.excepciones;


public class FormatoIdInvalidoNegocioException extends Exception{
    
    /**
     * Constructor por defecto.
     */
    public FormatoIdInvalidoNegocioException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public FormatoIdInvalidoNegocioException(String message) {
        super(message);
    }
    
}
