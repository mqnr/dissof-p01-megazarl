
package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.HashMap;
import java.util.List;


public interface IProductosVenta {
    
    public abstract void setProductos(List<HashMap<String, Object>> listaProductos);
    
    public abstract void mostrarInformacionProducto(Integer idProducto);
    
    public abstract void hacerVisible(boolean visible);
    
}
