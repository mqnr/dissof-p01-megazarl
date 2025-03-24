
package edu.student.itson.dissof.megazarl.presentacion.interfaces;


public interface IVista {
    
    public abstract void mostrarNombreApellidoClienteEncabezado();
    
    public abstract void actualizarBtnCarritoEncabezado();
    
    public abstract void hacerVisible(boolean visible);
    
    public abstract void cerrar();
    
    public abstract void actualizarDireccionCliente(String direccion);
}
