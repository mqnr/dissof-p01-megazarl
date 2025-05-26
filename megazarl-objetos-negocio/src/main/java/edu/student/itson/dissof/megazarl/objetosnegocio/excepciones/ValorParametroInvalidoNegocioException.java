
package edu.student.itson.dissof.megazarl.objetosnegocio.excepciones;


public class ValorParametroInvalidoNegocioException extends Exception{
    /**
     * Constructor por defecto.
     */
    public ValorParametroInvalidoNegocioException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public ValorParametroInvalidoNegocioException(String message) {
        super(message);
    }
}
