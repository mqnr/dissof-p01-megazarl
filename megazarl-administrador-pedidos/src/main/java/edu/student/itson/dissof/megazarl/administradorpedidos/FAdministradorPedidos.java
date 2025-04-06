package edu.student.itson.dissof.megazarl.administradorpedidos;

import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.dto.IdProductoCantidadCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionCalculoCostoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionCrearPedidoDTO;
import java.util.List;

/**
 * FAdministradorCarritoCompras.java
 * 
 * Clase Fachada que representa la implementación de la interfaz {@link IAdministradorPedidos}
 * del subsistema AdministradorPedidos.
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
public class FAdministradorPedidos implements IAdministradorPedidos {
    private final AdministradorPedidos administrador;

    /**
     * Constructor de la clase.
     * @param administradorProductos Objeto que implementa la interfaz {@link IAdministradorProductos}
     * @param administradorClientes Objeto que implementa la interfaz {@link IAdministradorClientes}
     * @param administradorPaqueterias Objeto que implementa la interfaz {@link IAdministradorPaqueterias}
     * @param administradorSucursales Objeto que implementa la interfaz {@link IAdministradorPaqueterias}
     */
    public FAdministradorPedidos(
            IAdministradorProductos administradorProductos,
            IAdministradorClientes administradorClientes,
            IAdministradorPaqueterias administradorPaqueterias,
            IAdministradorSucursales administradorSucursales) {
        administrador = new AdministradorPedidos(
                administradorProductos,
                administradorClientes,
                administradorPaqueterias,
                administradorSucursales
        );
    }

    /**
     * Implementación del método obtenerTiempoEstimadoPreparacion(), que permite obtener el tiempo estimado en
     * que los productos de un carrito llegarán a la Matriz.
     * @param listaIdProductoCantidadCarritoDTO
     * Objeto List{@literal <IdProductoCantidadCarritoDTO>} que contiene objetos IdProductoCantidadCarritoDTO
     * que incuyen IDs de Productos y su cantidad en un carito de compras.
     * @return Dato float que representa el tiempo estimado de envío de los Productos del carrito.
     * En este caso, es un mock y se asume que de todos los productos que deben trasladarse
     * a la matriz, el producto con un tiempo mayor de traslado es de 4.5 hrs.
     */
    @Override
    public float obtenerTiempoEstimadoPreparacion(List<IdProductoCantidadCarritoDTO> listaIdProductoCantidadCarritoDTO) {
        return administrador.obtenerTiempoEstimadoPreparacion(listaIdProductoCantidadCarritoDTO);
    }

    /**
     * Implementación del método calcularCostoEnvioProductosPaqueteria(), de la interfaz {@link IAdministradorPedidos},
     * que permite obtener el costot total del envío de productos tomando en cuenta una Paquetería.
     * @param informacionCalculoCostoPedidoDTO Objeto InformacionCalculoCostoPedidoDTO que contiene los IDs de Producto y Paqueteria, así
     * como la cantidad solicitadade cada uno
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
    @Override
    public float calcularCostoEnvioProductosPaqueteria(InformacionCalculoCostoPedidoDTO informacionCalculoCostoPedidoDTO) throws PedidosIdClienteInvalidoException, PedidosIdProductoInvalidoException, PedidosIdPaqueteriaInvalidoException, PaqueteriasIdPaqueteriaInvalidoException, ProductosIdProductoInvalidoException {
        return administrador.calcularCostoEnvioProductosPaqueteria(informacionCalculoCostoPedidoDTO);
    }

    /**
     * Método que permite realizar un pedido con los productos especificados,
     * el cliente y la paquetería seleccionada.
     *
     * @param informacionCrearPedidoDTO Objeto InformacionCrearPedidoDTO que contiene
     * la información necesaria para crear un pedido.
     * @return Valor boolean que indica si el pedido fue realizado exitosamente (true)
     * o no (false).
     * @throws PedidosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * de producto o cliente o paquetería es inválido, dentro del subsistema administradorPedidos.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el
     * ID de producto es inválido, dentro del subsistema administradorProductos.
     */
    @Override
    public boolean realizarPedido(InformacionCrearPedidoDTO informacionCrearPedidoDTO) throws PedidosIdProductoInvalidoException, PedidosIdProductoInvalidoException, PedidosIdProductoInvalidoException, ProductosIdProductoInvalidoException {
        return administrador.realizarPedido(informacionCrearPedidoDTO);
    }
}
