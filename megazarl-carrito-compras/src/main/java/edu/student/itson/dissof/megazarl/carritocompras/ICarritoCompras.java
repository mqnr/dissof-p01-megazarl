package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.TiempoEstimadoPreparacionEnvioPedidoDTO;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;
import java.util.List;

public interface ICarritoCompras {

    List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente);

    void agregarProducto(Integer idCliente, Integer idProducto, int cantidad);
    
    void eliminarProducto(Integer idCliente, Integer idProducto, int cantidad);
    
    int obtenerNumeroProductos(Integer idCliente) throws ClienteNoExisteException;

    MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito();
    
    TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(Integer idCliente);
    
}
