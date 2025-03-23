package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.awt.Color;

public interface IMensaje {

    void setColorFondo(Color colorFondo);

    void setImagen(String direccionImagen);

    void setTexto(String texto);

    void hacerVisible(boolean visible);
}
