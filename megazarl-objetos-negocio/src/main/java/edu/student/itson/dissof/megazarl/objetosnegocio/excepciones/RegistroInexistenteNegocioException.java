
package edu.student.itson.dissof.megazarl.objetosnegocio.excepciones;


public class RegistroInexistenteNegocioException extends Exception{
    /**
     * Constructor por defecto.
     */
    public RegistroInexistenteNegocioException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public RegistroInexistenteNegocioException(String message) {
        super(message);
    }
}
