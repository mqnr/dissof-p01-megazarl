package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import java.util.List;
import edu.student.itson.dissof.megazarl.objetosnegocio.Producto;

public interface ICarritoCompras {

    public abstract List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente);

    public abstract void agregarProducto(Integer idCliente, Producto producto);
}
