package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.List;
import java.util.Map;

public interface ICarrito {

    public abstract void setProductos(List<Map<String, Object>> listaInformacionProductosCarrito);

    public abstract void actualizarBtnCarritoEncabezado();
    

    public abstract void mostrarNombreApellidoClienteEncabezado();
    
    public abstract void hacerVisible(boolean visible);
}
