package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.List;
import java.util.Map;

public interface IProductosVenta {

    public abstract void setProductosTodos(List<Map<String, Object>> listaInformacionProductos);

    public abstract void setProductosBusqueda(List<Map<String, Object>> listaInformacionProductos);

    public abstract void mostrarInformacionProducto(Object idProducto);
    
    public abstract void mostrarAvisoSinProductosDisponibles();

}
