
package edu.student.itson.dissof.megazarl.administradorsucursales.excepciones;


public class SucursalesPersistenciaException extends Exception{
    /**
    * Constrctor por defecto
    */
    public SucursalesPersistenciaException() {
    }

    /***
     * Método constructor que recibe un mensaje.
     * @param message Objeto String que representa el mensaje de la excepción.
     */
    public SucursalesPersistenciaException(String message) {
        super(message);
    }
}
