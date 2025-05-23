
package edu.student.itson.dissof.megazarl.objetosnegocio.excepciones;


public class AgregarInformacionNulaNegocioException extends Exception{
    /**
     * Constructor por defecto.
     */
    public AgregarInformacionNulaNegocioException() {
    }

    /**
     * Constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public AgregarInformacionNulaNegocioException(String message) {
        super(message);
    }
}
