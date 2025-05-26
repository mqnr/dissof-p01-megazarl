
package edu.student.itson.dissof.megazarl.administradorclientes.excepciones;

/**
 * ClientesPersistenciaException.java

* 
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class ClientesPersistenciaException extends Exception{
    /**
     * Constrctor por defecto
     */
    public ClientesPersistenciaException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ClientesPersistenciaException(String message) {
        super(message);
    }
}
