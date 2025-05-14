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
    
    public abstract void mostrarAvisoSinProductosOfrecidosDisponibles(); //TODO se necesita mostra aviso en caso de que el proveedor ya no tenga dichos productos, es decir hay un límite??
    
}