package edu.student.itson.dissof.megazarl.configuracion;

public class ConfiguracionFuentesIntermedia {
    public FuenteDatos cliente;
    public FuenteDatos paqueteria;

    public void todos(FuenteDatos fuente) {
        cliente = fuente;
        paqueteria = fuente;
    }
}
