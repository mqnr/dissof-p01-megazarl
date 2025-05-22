
package edu.student.itson.dissof.megazarl.administrador.mysql.excepciones;

/**
 * InformacionActualizacionNuloException.java
 * 
 * 
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 * 
 */
public class AgregarInformacionNulaException extends Exception{
    /**
     * Constructor por defecto.
     */
    public AgregarInformacionNulaException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public AgregarInformacionNulaException(String message) {
        super(message);
    }
}
