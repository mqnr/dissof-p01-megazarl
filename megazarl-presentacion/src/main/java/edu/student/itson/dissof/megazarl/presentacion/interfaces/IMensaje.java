package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.awt.Color;

public interface IMensaje {

    public void setColorFondo(Color colorFondo);
            
    public void setImagen(String direccionImagen);
    
    public void setTexto(String texto);
    
    public abstract void hacerVisible(boolean visible);
}
