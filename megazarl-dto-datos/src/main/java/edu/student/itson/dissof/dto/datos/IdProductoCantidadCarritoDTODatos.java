package edu.student.itson.dissof.dto.datos;

import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;

/**
 * IdProductoCantidadCarritoDTODatos.java

 Clase que representa un objeto de transferencia de datos que asocia
 un identificador de producto con la cantidad solicitada del mismo
 en el carrito de compras.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class IdProductoCantidadCarritoDTODatos {

    private IdEntidadGenericoDatos idProducto;
    private Integer cantidad;

    public IdProductoCantidadCarritoDTODatos(IdEntidadGenericoDatos idProducto, Integer cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public IdEntidadGenericoDatos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(IdEntidadGenericoDatos idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
