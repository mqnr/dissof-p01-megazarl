
package edu.student.itson.dissof.megazarl.administradorclientes.excepciones;

/**
 * ClientesIdDireccionInvalidoException.java
 * 
 * Clase que representa una excepción lanzada cuando el ID de la dirección de 
 * un cliente es inválida.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 */
public class ClientesIdDireccionInvalidoException extends Exception{
    
    /**
     * Constrctor por defecto
     */
    public ClientesIdDireccionInvalidoException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ClientesIdDireccionInvalidoException(String message) {
        super(message);
    }
    
}
