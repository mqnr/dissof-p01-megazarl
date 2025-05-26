
package edu.student.itson.dissof.megazarl.administradorproductos.excepciones;


public class ProductosPersistenciaException extends Exception{
    /**
     * Constructor por defecto.
     */
    public ProductosPersistenciaException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ProductosPersistenciaException(String message) {
        super(message);
    }
}
