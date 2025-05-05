
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * 
 * CarritoComprasIdProveedorInvalidoException.java
 * 
 * Clase que representa una excepción lanzada cuando se determina en el subsistema
 * administradorCarritoCompras que el ID de un Proveedor recibido es inválido.
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
public class CarritoComprasIdProveedorInvalidoException extends Exception{
    /**
     * Método constructor por defecto
     */
    public CarritoComprasIdProveedorInvalidoException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasIdProveedorInvalidoException(String message) {
        super(message);
    }
}
