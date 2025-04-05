package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoSinProductoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoVacioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasClienteSinCarritoVigenteException;
import edu.student.itson.dissof.megazarl.dto.IdClientePaqueteriaCalculoCostoEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.TiempoEstimadoPreparacionEnvioPedidoDTO;
import java.util.List;

public interface IAdministradorCarritoCompras {

    public abstract List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente)
            throws CarritoComprasIdClienteInvalidoException;

    public abstract void agregarProducto(Integer idCliente, Integer idProducto, int cantidad) 
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException;
    
    public abstract void eliminarProducto(Integer idCliente, Integer idProducto, int cantidad)
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasCarritoSinProductoException, 
            CarritoComprasClienteSinCarritoVigenteException,
            ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException;
    
    public abstract Integer obtenerNumeroProductos(Integer idCliente)
            throws CarritoComprasIdClienteInvalidoException;

    public abstract MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito(Integer idCliente);
    
    public abstract TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(Integer idCliente)
            throws CarritoComprasClienteSinCarritoVigenteException;
    
    public abstract float obtenerCostoEnvioProductos(IdClientePaqueteriaCalculoCostoEnvioDTO idClientePaqueteriaCalculoCostoEnvioDTO)
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdPaqueteriaInvalidoException,
            PedidosIdClienteInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdPaqueteriaInvalidoException,
            PaqueteriasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            ProductosIdProductoInvalidoException;
    
    public abstract void asignarPaqueteriaCarritoCliente(Integer idCliente, Integer idPaqueteria)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException;
    
    public abstract void crearPedidoProductosCarritoCliente(Integer idCliente)
            throws CarritoComprasIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasCarritoVacioException,
            ProductosIdProductoInvalidoException;
    
    
}
