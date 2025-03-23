package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.List;
import java.util.Map;

public interface IProductosVenta {

    void setProductosTodos(List<Map<String, Object>> listaInformacionProductos);

    void setProductosBusqueda(List<Map<String, Object>> listaInformacionProductos);

    void mostrarInformacionProducto(Integer idProducto);

}
