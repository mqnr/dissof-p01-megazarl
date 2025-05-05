
package edu.student.itson.dissof.administradorproveedores.excepciones;

/**
 * 
 * ProveedoresIdPaqueteriaInvalidoException.java
 * 
 * Clase que representa una excepción lanzada cuando se comprueba que el ID
 * de una Paquetería es inválido, dentro de subsistema administradorProveedores.
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
public class ProveedoresIdPaqueteriaInvalidoException extends Exception{
    
    /**
    * Contructor por defecto.
    */
    public ProveedoresIdPaqueteriaInvalidoException() {
    }
    
    /**
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ProveedoresIdPaqueteriaInvalidoException(String message) {
        super(message);
    }
}
