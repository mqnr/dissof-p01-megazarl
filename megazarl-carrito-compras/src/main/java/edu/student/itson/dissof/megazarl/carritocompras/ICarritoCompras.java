package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.TiempoEstimadoPreparacionEnvioPedidoDTO;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;
import java.util.List;

public interface ICarritoCompras {

    public abstract List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente);

    public abstract void agregarProducto(Integer idCliente, Integer idProducto, int cantidad);
    
    public abstract void eliminarProducto(Integer idCliente, Integer idProducto, int cantidad);
    
    public abstract int obtenerNumeroProductos(Integer idCliente) throws ClienteNoExisteException;

    public abstract MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito();
    
    public abstract TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(Integer idCliente);
    
}
