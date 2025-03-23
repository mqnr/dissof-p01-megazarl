package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.Map;

public interface IInformacionProducto {

    void setProducto(Map<String, Object> informacionProducto);

    void actualizarBtnCarritoEncabezado();

    void mostrarNombreApellidoClienteEncabezado();

    void hacerVisible(boolean visible);
}
