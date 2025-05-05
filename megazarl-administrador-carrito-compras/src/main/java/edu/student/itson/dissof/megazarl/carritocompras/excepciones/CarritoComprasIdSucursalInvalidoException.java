
package edu.student.itson.dissof.megazarl.carritocompras.excepciones;

/**
 * 
 * CarritoComprasIdProveedorInvalidoException.java
 * 
 * Clase que representa una excepción lanzada cuando se determina en el subsistema
 * administradorCarritoCompras que el ID de un Sucursal recibido es inválido.
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
public class CarritoComprasIdSucursalInvalidoException extends Exception{
    /**
     * Método constructor por defecto
     */
    public CarritoComprasIdSucursalInvalidoException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public CarritoComprasIdSucursalInvalidoException(String message) {
        super(message);
    }
}
