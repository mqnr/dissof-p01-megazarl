
package edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones;

/**
 * PaqueteriasIdClienteInvalidoException.java

 Clase que representa una excepción lanzada cuando se intenta utilizar un ID
 de una dirección que no existe o no es válido en el sistema, dentro del subsistema
 * administadorPaqueterias.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 *
 */
public class PaqueteriasIdDireccionInvalidoException extends Exception{
    /**
     * Constructor por defecto.
     */
    public PaqueteriasIdDireccionInvalidoException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public PaqueteriasIdDireccionInvalidoException(String message) {
        super(message);
    }
}
