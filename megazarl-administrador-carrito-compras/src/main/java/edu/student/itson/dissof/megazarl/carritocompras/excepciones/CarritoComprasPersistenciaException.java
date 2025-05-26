
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;


public class CarritoComprasPersistenciaException extends Exception{
    /**
     * Método constructor por defecto
     */
    public CarritoComprasPersistenciaException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasPersistenciaException(String message) {
        super(message);
    }
}
