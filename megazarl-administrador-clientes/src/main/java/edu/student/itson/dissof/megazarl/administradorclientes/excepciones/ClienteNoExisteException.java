package edu.student.itson.dissof.megazarl.administradorclientes.excepciones;

public class ClienteNoExisteException extends Exception {

    public ClienteNoExisteException() {
    }

    public ClienteNoExisteException(String message) {
        super(message);
    }
}
