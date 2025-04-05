package edu.student.itson.dissof.megazarl.administradorpedidos;


import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.dto.IdProductoCantidadCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionCalculoCostoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionCrearPedidoDTO;
import java.util.List;

public interface IAdministradorPedidos {
    
    public abstract float obtenerTiempoEstimadoPreparacion(List<IdProductoCantidadCarritoDTO> listaInformacionProductoCalculoTiempoPreparacionDTO);
    
    /**
     * Método que permite obtener el costot total del envío de productos tomando en cuenta una Paquetería.
     * @param informacionCalculoCostoPedidoDTO Objeto InformacionCalculoCostoPedidoDTO que contiene los IDs 
     * de Producto y Paqueteria, así como la cantidad solicitadade cada uno.
     * @return Dato float que representa el costo total de envío.
     * @throws PedidosIdClienteInvalidoException Se lanza si se comprueba que el ID de 
     * Cliente es inválido, dentor del subsistema administradorPedidos.
     * @throws PedidosIdProductoInvalidoException Se lanza si se comprueba que el 
     * ID de Producto es inválido, dentro del subsistema administradorPedidos.
     * @throws PedidosIdPaqueteriaInvalidoException Se lanza si se comprueba que 
     * el id de Paquetería es inválido, dentro del subsitema administradorPedidos.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que
     * el ID de Paquetería es Inválido, dentro del subsistema administradorPaqueterias.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el
     * ID de Producto es inválido, dentro del administradorProductos.
     */
    public abstract float calcularCostoEnvioProductosPaqueteria(InformacionCalculoCostoPedidoDTO informacionCalculoCostoPedidoDTO)
            throws PedidosIdClienteInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdPaqueteriaInvalidoException,
            PaqueteriasIdPaqueteriaInvalidoException,
            ProductosIdProductoInvalidoException;
    
    public abstract boolean realizarPedido(InformacionCrearPedidoDTO informacionCrearPedidoDTO)
        throws PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException,
            ProductosIdProductoInvalidoException;
    
}
