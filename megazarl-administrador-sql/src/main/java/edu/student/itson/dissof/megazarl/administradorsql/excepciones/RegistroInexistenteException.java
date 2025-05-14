
package edu.student.itson.dissof.megazarl.administradorsql.excepciones;

/**
 * RegistroInexistenteException.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 * 
 */
public class RegistroInexistenteException extends Exception{
    /**
     * Constructor por defecto.
     */
    public RegistroInexistenteException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public RegistroInexistenteException(String message) {
        super(message);
    }
}
