
package edu.student.itson.dissof.administradorproveedores.excepciones;


public class ProveedoresPersistenciaException extends Exception{
    /**
    * Contructor por defecto.
    */
    public ProveedoresPersistenciaException() {
    }
    
    /**
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ProveedoresPersistenciaException(String message) {
        super(message);
    }
}
