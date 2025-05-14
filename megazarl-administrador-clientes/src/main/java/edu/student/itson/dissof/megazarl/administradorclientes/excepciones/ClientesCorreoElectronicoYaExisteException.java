
package edu.student.itson.dissof.megazarl.administradorclientes.excepciones;

/**
 *
 * ClientesCorreoElectronicoYaExisteException.java
 * 
 * Clase que representa una excepción lanzada cuando se comprueba que
 * existe otro cliente registrado con el mismo correo electrónico al recibido 
 * del nuevo cliente a registrar.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class ClientesCorreoElectronicoYaExisteException extends Exception{
    /**
     * Constrctor por defecto
     */
    public ClientesCorreoElectronicoYaExisteException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ClientesCorreoElectronicoYaExisteException(String message) {
        super(message);
    }
}
