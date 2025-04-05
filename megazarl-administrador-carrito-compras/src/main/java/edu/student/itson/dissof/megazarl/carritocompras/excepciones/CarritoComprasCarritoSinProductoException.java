
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * CarritoComprasCarritoSinProductoException.java

 Clase que representa una excepción lanzada cuando el Cliente no tiene artículos
 de un producto a eliminar en su CarritoCompras.
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
public class CarritoComprasCarritoSinProductoException extends Exception{

    /**
     * Constructor por defecto.
     */
    public CarritoComprasCarritoSinProductoException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasCarritoSinProductoException(String message) {
        super(message);
    }
    
}
