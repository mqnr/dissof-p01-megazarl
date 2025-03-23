package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.List;
import java.util.Map;

public interface ICarrito {

    void setProductos(List<Map<String, Object>> listaInformacionProductosCarrito);

    void actualizarBtnCarritoEncabezado();

    void mostrarNombreApellidoClienteEncabezado();

    void hacerVisible(boolean visible);
}
