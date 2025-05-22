package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.List;
import java.util.Map;
/**
 * @author Usuario
 */
public interface ISucursal {
    
    public abstract void setSucursalesTodas(List<Map<String, Object>> listaInformacionProveedores);
    
    public abstract void mostrarAvisoSinPucursalesDisponibles();
    
}
