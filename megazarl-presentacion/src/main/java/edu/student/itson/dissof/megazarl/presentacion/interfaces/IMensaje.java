package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.awt.Color;

public interface IMensaje {

    public abstract void setColorFondo(Color colorFondo);

    public abstract void setImagen(String direccionImagen);

    public abstract void setTexto(String texto);
    
    public abstract void mostrarMensaje();
    
    public abstract void cerrarMensaje();

}
