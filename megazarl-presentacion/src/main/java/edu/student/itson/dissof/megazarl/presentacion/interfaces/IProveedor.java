package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.List;
import java.util.Map;
/**
 * @author Usuario
 */
public interface IProveedor {
    
    public abstract void setProveedoresTodos(List<Map<String, Object>> listaInformacionProveedores);
    
    public abstract void mostrarAvisoSinProveedoresDisponibles();
    
}
