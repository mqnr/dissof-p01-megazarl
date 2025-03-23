/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import edu.student.itson.dissof.megazarl.presentacion.Encabezado;

/**
 *
 * @author romom
 */
public interface IVista {
    
    public abstract void mostrarNombreApellidoClienteEncabezado();
    
    public abstract void actualizarBtnCarritoEncabezado();
    
    public abstract void hacerVisible(boolean visible);
    
    public abstract void cerrar();
}
