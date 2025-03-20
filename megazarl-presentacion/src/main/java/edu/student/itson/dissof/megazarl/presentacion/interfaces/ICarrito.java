package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.HashMap;
import java.util.List;

public interface ICarrito {

    public abstract void setProductos(List<HashMap<String, Object>> listaInformacionProductosCarrito);

    public abstract void hacerVisible(boolean visible);
}
