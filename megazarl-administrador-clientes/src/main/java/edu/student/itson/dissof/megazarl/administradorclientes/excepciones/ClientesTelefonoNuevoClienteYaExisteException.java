
package edu.student.itson.dissof.megazarl.administradorclientes.excepciones;

/**
 *
 * ClientesTelefonoNuevoClienteYaExisteException.java
 * 
 * Clase que representa una excepción lanzada cuando se comprueba que existe
 * otro cliente con el mismo teléfono que el del ingresado por el nuevo que se
 * va a aregistrar.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class ClientesTelefonoNuevoClienteYaExisteException extends Exception {
    /**
     * Constrctor por defecto
     */
    public ClientesTelefonoNuevoClienteYaExisteException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ClientesTelefonoNuevoClienteYaExisteException(String message) {
        super(message);
    }
}
