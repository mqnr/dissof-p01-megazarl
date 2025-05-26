
package edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones;


public class PaqueteriasPersistenciaException extends Exception{
    /**
     * Constructor por defecto.
     */
    public PaqueteriasPersistenciaException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public PaqueteriasPersistenciaException(String message) {
        super(message);
    }
}
