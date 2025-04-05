
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * CarritoComprasClienteSinCarritoVigenteException.java
 * 
 * Clase que representa una excepción lanzada cuando un Cliente no tiene
 * un carrito vigente, dentro del subsistema administradorCarritoCompras.
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
public class CarritoComprasClienteSinCarritoVigenteException extends Exception{

    /**
     * Método constructor por defecto
     */
    public CarritoComprasClienteSinCarritoVigenteException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasClienteSinCarritoVigenteException(String message) {
        super(message);
    }
    
}
