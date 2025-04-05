
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * 
 * CarritoComprasIdProductoInvalidoException.java
 * 
 * Clase que representa una excepción lanzada cuando se determina en el subsistema
 * administradorCarritoCompras que el ID de un Producto recibido es inválido.
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
public class CarritoComprasIdProductoInvalidoException extends Exception{

    /**
     * Método constructor por defecto
     */
    public CarritoComprasIdProductoInvalidoException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasIdProductoInvalidoException(String message) {
        super(message);
    }
    
}
