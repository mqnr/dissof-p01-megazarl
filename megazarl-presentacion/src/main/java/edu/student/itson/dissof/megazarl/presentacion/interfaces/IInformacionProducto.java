package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.Map;

public interface IInformacionProducto {

    public abstract void setProducto(Map<String, Object> informacionProducto);

    public abstract void actualizarBtnCarritoEncabezado();

    public abstract void mostrarNombreApellidoClienteEncabezado();

    public abstract void hacerVisible(boolean visible);
}
