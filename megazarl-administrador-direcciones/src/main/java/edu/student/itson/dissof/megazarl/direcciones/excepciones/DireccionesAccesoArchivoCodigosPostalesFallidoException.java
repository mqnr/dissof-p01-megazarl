
package edu.student.itson.dissof.megazarl.direcciones.excepciones;

/**
 * DireccionesAccesoArchivoCodigosPostalesFallidoException.java
 
 Clase que representa una excepción lanzada cuando no se pudo leer el archivo 
 que contiene la información relacionada con los Códigos Postales de los
 Estados Unidos Mexicanos.
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
public class DireccionesAccesoArchivoCodigosPostalesFallidoException extends Exception{
    
    /**
     * Constructor por defecto
     */
    public DireccionesAccesoArchivoCodigosPostalesFallidoException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public DireccionesAccesoArchivoCodigosPostalesFallidoException(String message) {
        super(message);
    }
}
