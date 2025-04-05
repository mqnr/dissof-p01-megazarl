
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * CarritoComprasIdPaqueteriaInvalidoException.java
 * 
 * Clase que representa una excepción lanzada cuando el ID de una paquetería
 * recibido es inválido, dentro del subsistema administradorCarritoCompras.
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
 * 
 */
public class CarritoComprasIdPaqueteriaInvalidoException extends Exception{

    /**
     * Contructor por defecto.
     */
    public CarritoComprasIdPaqueteriaInvalidoException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasIdPaqueteriaInvalidoException(String message) {
        super(message);
    }
    
}
