package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.TiempoEstimadoPreparacionEnvioPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoCarritoCantidadIdDTO;
import java.util.List;

public interface ICarritoCompras {

    public abstract List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente);

    public abstract boolean agregarProducto(Integer idCliente, Integer idProducto, int cantidad);
    
    public abstract boolean eliminarProducto(Integer idCliente, Integer idProducto, int cantidad);
    
    public abstract Integer obtenerNumeroProductos(Integer idCliente);

    public abstract MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito();
    
    public abstract TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(Integer idCliente);
    
    public abstract ProductoCarritoCantidadIdDTO obtenerIdsProductosInventario(Integer idCliente);
    
}
