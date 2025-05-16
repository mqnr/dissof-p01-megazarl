
package edu.student.itson.dissof.megazarl.administradorsucursales.excepciones;


/**
 * SucursalesIdSucursalInvalidoException.java

 Clase que representa una excepción lanzada cuando se comprueba que el ID
 de una Sucursal es inválido, dentro del subsitema administradorSucursales.
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
public class SucursalesIdSucursalInvalidoException extends Exception{
    
    /**
     * Constrctor por defecto
     */
    public SucursalesIdSucursalInvalidoException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public SucursalesIdSucursalInvalidoException(String message) {
        super(message);
    }
}
