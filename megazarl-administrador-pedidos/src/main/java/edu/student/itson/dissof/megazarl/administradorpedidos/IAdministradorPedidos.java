package edu.student.itson.dissof.megazarl.administradorpedidos;


import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.PedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCantidadCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionPedidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionCrearPedidoDTO;
import java.util.List;

/**
 * IAdministradorPedidos.java
 *
 * Interfaz del subsistema AdministradorPedidos, que se encarga de gestionar los pedidos
 * realizados en el sistema, calculando tiempos de preparación, costos de envío y
 * procesando las órdenes para su posterior envío.
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
public interface IAdministradorPedidos {
    
    /**
     * Método que permite obtener el tiempo estimado de preparación para una lista
     * de productos en un carrito de compras.
     * @param listaIdProductoCantidadCarritoDTO         Objeto {@literal List<IdProductoCantidadCarritoDTO>}
     *                                                  que contiene los productos y sus cantidades para calcular el tiempo de preparación.
     * @return                                          Dato float que representa el tiempo estimado de preparación en horas.
     * @throws                                          PedidosIdProductoInvalidoException Se lanza si el id de uno de los productos
     *                                                  recibidos es inválido, dentro del subsistema adminsitradorPedidos
     * @throws PedidosIdProveedorInvalidoException
     * @throws PedidosIdSucursalInvalidoException
     * @throws PedidosIdProductoInventarioInvalidoException
     * @throws PedidosIdDireccionInvalidoException
     */
    public abstract float obtenerTiempoEstimadoPreparacion(List<IdProductoCantidadCarritoDTO> listaIdProductoCantidadCarritoDTO)
            throws PedidosIdProductoInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdProductoInventarioInvalidoException,
            PedidosIdDireccionInvalidoException,
            PedidosIdSucursalInvalidoException;
    
    /**
     * Método que permite obtener el costot total del envío de productos tomando en cuenta una Paquetería.
     * @param informacionCalculoCostoPedidoDTO Objeto InformacionPedidoClienteDTO que contiene los IDs 
     *                                         de Producto y Paqueteria, así como la cantidad solicitadade cada uno.
     * @return                                 Dato float que representa el costo total de envío.
     * @throws                                 PedidosIdClienteInvalidoException Se lanza si se comprueba que el ID de 
     *                                         Cliente es inválido, dentor del subsistema administradorPedidos.
     * @throws                                 PedidosIdProductoInvalidoException Se lanza si se comprueba que el 
     *                                         ID de Producto es inválido, dentro del subsistema administradorPedidos.
     * @throws                                 PedidosIdPaqueteriaInvalidoException Se lanza si se comprueba que 
     *                                         el id de Paquetería es inválido, dentro del subsitema administradorPedidos.
     * @throws PedidosIdSucursalInvalidoException
     * @throws PedidosIdProveedorInvalidoException
     * @throws PedidosIdDireccionInvalidoException
     * @throws PedidosIdProductoInventarioInvalidoException
     */
    public abstract float calcularCostoEnvioProductosPaqueteria(InformacionPedidoClienteDTO informacionCalculoCostoPedidoDTO)
            throws PedidosIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdPaqueteriaInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdDireccionInvalidoException,
            PedidosIdProductoInventarioInvalidoException;

    /**
     * Método que permite realizar un pedido con los productos especificados,
     * el cliente y la paquetería seleccionada.
     *
     * @param informacionCrearPedidoDTO Objeto InformacionCrearPedidoDTO que contiene
     *                                  la información necesaria para crear un pedido.
     * @return                          Objeto PedidoDTO que representa el pedido creado.
     * @throws                          PedidosIdProductoInvalidoException Se lanza si se comprueba que el ID
     *                                  de producto es inválido, dentro del subsistema administradorPedidos.
     * @throws                          ProductosIdProductoInvalidoException Se lanza si se comprueba que el
     *                                  ID de producto es inválido, dentro del subsistema administradorProductos.
     * @throws PedidosIdProductoInventarioInvalidoException
     * @throws PedidosIdSucursalInvalidoException
     * @throws PedidosIdDireccionInvalidoException
     */
    public abstract PedidoDTO realizarPedido(InformacionCrearPedidoDTO informacionCrearPedidoDTO)
        throws PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException,
            ProductosIdProductoInvalidoException,
            PedidosIdProductoInventarioInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosIdDireccionInvalidoException;
}
