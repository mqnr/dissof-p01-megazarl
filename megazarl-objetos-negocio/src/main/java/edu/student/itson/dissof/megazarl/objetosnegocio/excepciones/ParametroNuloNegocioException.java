
package edu.student.itson.dissof.megazarl.objetosnegocio.excepciones;


public class ParametroNuloNegocioException extends Exception{
    /**
     * Constructor por defecto.
     */
    public ParametroNuloNegocioException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ParametroNuloNegocioException(String message) {
        super(message);
    }
}
