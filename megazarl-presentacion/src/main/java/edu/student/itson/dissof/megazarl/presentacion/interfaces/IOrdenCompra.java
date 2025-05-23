package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.List;
import java.util.Map;
/**
 * IOrdenCompra.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public interface IOrdenCompra {
    
    public abstract void setProveedoresTodos(List<Map<String, Object>> listaInformacionProveedores);
    
    public abstract void setSucursalesEnvio(List<Map<String, Object>> listaInformacionSucursales);
    
    public abstract void setProductosOfrecidosBusqueda(List<Map<String, Object>> listaInformacionProductosOfrecidos);
    
    public abstract void mostrarAvisoSinProveedoresDisponibles();
    
    public abstract void mostrarAvisoSinProductosOfrecidosDisponibles();
    
    public abstract void mostrarAvisoSinSucursalesDisponibles();
}