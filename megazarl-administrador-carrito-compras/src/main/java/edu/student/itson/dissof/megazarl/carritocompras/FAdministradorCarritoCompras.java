package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
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

/**
 * FAdministradorCarritoCompras.java
 * 
 * Clase Fachada que representa la implementación de la interfaz {@link IAdministradorClientes}
 * del subsistema AdministradorCarritoCompras.
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
public class FAdministradorCarritoCompras implements IAdministradorCarritoCompras {
    private final AdministradorCarritoCompras administrador;

    /**
     * Constructor de la clase que recibe como parámetros objetos del tipo de
     * la interfaz de los subsitemas que utiliza este subsistema.
     *
     * @param montoMinimoEnvioGratuito Representa el monto mínimo para que un Cliente
     * pueda obtener un envío gratuito.
     * @param administradorProductos
     * @param administradorPedidos
     */
    public FAdministradorCarritoCompras(
            Double montoMinimoEnvioGratuito,
            IAdministradorProductos administradorProductos,
            IAdministradorPedidos administradorPedidos) {
        administrador = new AdministradorCarritoCompras(
                montoMinimoEnvioGratuito,
                administradorProductos,
                administradorPedidos
        );
    }

    /**
     * Implementación del método obetenerProductos() de la interfaz {@link IAdministradorCarritoCompras}
     * Permite obtener la informacion de los productos contenidos en el carrito
     * del Cliente con el ID del parámetro.
     *
     * @param idCliente ID del cliente a buscar los productos de su carrito.
     * @return Lista de DTOs con la información de cada producto en el carrito del cliente. La lista estará vacía
     * si el Cliente no tiene un carrito vigente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza cuando el ID de cliente recibido es inválido.
     */
    @Override
    public List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente) throws CarritoComprasIdClienteInvalidoException {
        return administrador.obtenerProductos(idCliente);
    }

    /**
     * Implementación del método agregarProducto() de la interfaz {@link IAdministradorCarritoCompras},
     * permite añadir una cantidad de un producto al carrito activo del cliente.
     *
     * @param idCliente  Objeto Integer que representa el ID del cliente al que se agregará el producto.
     * @param idProducto Objeto Integer que representa el ID del producto a agregar.
     * @param cantidad   Dato int que representa la cantidad a agregar del producto.
     * @throws CarritoComprasIdClienteInvalidoException     Se lanza cuando se comprueba que
     *                                                      ID de cliente recibido es inválido, en este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException    Se lanza cuando se comprueba que
     *                                                      ID de producto es inválido, en este subsistema.
     * @throws CarritoComprasProductoSinInventarioException Se lanza cuando se comprueba que
     *                                                      un producto del carrito de compras no tiene inventario.
     * @throws ProductosIdProductoInvalidoException         Se lanza si se comprueba que el
     *                                                      ID de un producto es inválido, dentro del subsistema administradorProductos.
     * @throws ProductosProductoSinInventarioException      Se lanza si se comprueba que el
     *                                                      un producto no tiene inventario, dentro del subsistema administradorProductos.
     */
    @Override
    public void agregarProducto(Integer idCliente, Integer idProducto, int cantidad) throws CarritoComprasIdClienteInvalidoException, CarritoComprasIdProductoInvalidoException, CarritoComprasProductoSinInventarioException, ProductosIdProductoInvalidoException, ProductosProductoSinInventarioException {
        administrador.agregarProducto(idCliente, idProducto, cantidad);
    }

    /**
     * Implementación del método eliminarProducto(), de la interfaz {@link IAdministradorCarritoCompras},
     * que permite elminar la cantidad del parámetro del producto con ID del parámetro
     * del carrito del Cliente con el ID del parámetro.
     *
     * @param idCliente  Objeto Integer que representa el ID de un Cliente.
     * @param idProducto Objeto Integer que representa el ID de un Producto.
     * @param cantidad   Dato int que representa la cantidad a elminar del producto.
     * @throws CarritoComprasIdClienteInvalidoException        Se lanza cuando se verifica que
     *                                                         el ID del Cliente recibido es inválido, dentro de este subsitema.
     * @throws CarritoComprasIdProductoInvalidoException       Se lanza cuando se verifica
     *                                                         que el ID del Producto a eliminar es inválido, dentro de este subsistema.
     * @throws CarritoComprasCarritoSinProductoException       Se lanza cuando se
     *                                                         comprueba que el Cliente no tiene un Carrito de compras vigente.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza cuando
     *                                                         el Cliente no tiene ningún Carrito de compras vigente asociado.
     * @throws ProductosIdProductoInvalidoException            Se lanza si se comprueba que el
     *                                                         ID de un producto es inválido, dentro del subsistema administradorProductos.
     * @throws ProductosProductoSinInventarioException         Se lanza si se comprueba que el
     *                                                         un producto no tiene inventario, dentro del subsistema administradorProductos.
     */
    @Override
    public void eliminarProducto(Integer idCliente, Integer idProducto, int cantidad) throws CarritoComprasIdClienteInvalidoException, CarritoComprasIdProductoInvalidoException, CarritoComprasCarritoSinProductoException, CarritoComprasClienteSinCarritoVigenteException, ProductosIdProductoInvalidoException, ProductosProductoSinInventarioException {
        administrador.eliminarProducto(idCliente, idProducto, cantidad);
    }

    /**
     * Implementación del método obtenerNumeroProductos(), de la interfaz {@link IAdministradorProductos},
     * que permite obtener el número de artículos que tiene el Cliente con el ID del parámetro.
     *
     * @param idCliente ID del Cliente a obtener el total de productos.
     * @return Objeto Integer que representa el número de productos en el carrito del Cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza cuando se verifica que
     *                                                  el ID del Cliente recibido es inválido, dentro de este subsitema.
     */
    @Override
    public Integer obtenerNumeroProductos(Integer idCliente) throws CarritoComprasIdClienteInvalidoException {
        return administrador.obtenerNumeroProductos(idCliente);
    }

    /**
     * Implementación del método obtenerTiempoEstimadoPreparacionProductos() de la interfaz {@link IAdministradorProductos},
     * que permite obtener el tiempo estimado de preparación del envío en la matriz de la empresa, es decir, el tiempo
     * aproximado que el Cliente debe esperar hasta que se realice el envío desde la matriz a la dirección de envío.
     *
     * @param idCliente Objeto Integer que representa el ID del Cliente a obtener el tiempo estimado de preparación de
     *                  prouctos del carrito.
     * @return Objeto TiempoEstimadoPreparacionEnvioPedidoDTO, que contiene el tiempo mínimo y máximo de preparación
     * del pedido.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza cuando el Cliente no tiene asociado ningún carrito
     *                                                         vigente.
     */
    @Override
    public TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(Integer idCliente) throws CarritoComprasClienteSinCarritoVigenteException {
        return administrador.obtenerTiempoEstimadoPreparacionProductos(idCliente);
    }

    /**
     * Implementación del método obtnerInformacionMontoEnvioMinimoGrautito() que
     * la interfaz {@link IAdministradorProductos} y permite obtener información
     * sobre el monto del carrito actual y el monto necesario para que el envío
     * sea gratuito, de un determinado Cliente.
     *
     * @param idCliente Objeto Integer que representa el ID del cliente del que
     *                  se va a obtener la información del monto total.
     * @return Objeto MontoMinimoEnvioGratuitoDTO que contiene el monto mínimo necesario
     * para un envío gratuito y el monto actual del carrito del Cliente.
     */
    @Override
    public MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito(Integer idCliente) {
        return administrador.obtenerInformacionMontoEnvioMinimoGratuito(idCliente);
    }

    /**
     * Implementación del método obtenerCostoEnvioProductos(), que implementa la interfaz {@link IAdministradorProductos},
     * y que permite obtener el costo total que tendría un pedido con los productos del carrito
     * de un Cliente, a partir de una paquetería seleccionada.
     *
     * @param idClientePaqueteriaCalculoCostoEnvioDTO
     * @return Objeto de tipo Float que representa el costo total de envío de los productos del carrito,
     * considerando el costo de envío de la paquetería indicada.
     * @throws CarritoComprasIdClienteInvalidoException        Se lanza si se comprueba que
     *                                                         el ID del Carrito de compras recibido es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException     Se lanza si el ID de la
     *                                                         paquetería recibido es inválido, dentro de este subsistema.
     * @throws PedidosIdClienteInvalidoException               Se lanza si se comprueba que el ID
     *                                                         del Carrito de compras recibido es inválido, dentro del subsistema administradorPedidos.
     * @throws PedidosIdProductoInvalidoException              Se lanza si se comprueba que el ID
     *                                                         de un producto dentro del carrito de un cliente es inválido, dentro del
     *                                                         subsistema administradorPedidos.
     * @throws PedidosIdPaqueteriaInvalidoException            Se lanza si se comprueba que el ID
     *                                                         de la paquetería recibida es inválido, dentro del subsistma administradorPedidos.
     * @throws PaqueteriasIdPaqueteriaInvalidoException        Se lanza si se comprueba que
     *                                                         el ID de la paqueteria recibida es inválido, dentro del susbistema administradorPaqueterias.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si el Cliente del
     *                                                         que se va a obtener el costo de envío de los productos de su carrito no tiene un Carrito
     *                                                         de compras.
     * @throws ProductosIdProductoInvalidoException            Se lanza cuando se comprueba que
     *                                                         el ID de un producto es inválido, en el subsistema, administradorProdctos.
     */
    @Override
    public float obtenerCostoEnvioProductos(IdClientePaqueteriaCalculoCostoEnvioDTO idClientePaqueteriaCalculoCostoEnvioDTO) throws CarritoComprasIdClienteInvalidoException, CarritoComprasIdPaqueteriaInvalidoException, PedidosIdClienteInvalidoException, PedidosIdProductoInvalidoException, PedidosIdPaqueteriaInvalidoException, PaqueteriasIdPaqueteriaInvalidoException, CarritoComprasClienteSinCarritoVigenteException, ProductosIdProductoInvalidoException {
        return administrador.obtenerCostoEnvioProductos(idClientePaqueteriaCalculoCostoEnvioDTO);
    }

    /**
     * Implementación del método asignarPaqueteriaCarritoCliente(), de la interfaz {@link IAdministradorProductos}
     *
     * @param idCliente    Objeto Integer que representa el ID del Cliente a cuyo carrito se le asignará la Paquetería
     *                     con el ID del parámetro.
     * @param idPaqueteria Objeto Integer que representa el ID de la Paquetería que se va a asociar al carrito
     *                     del Cliente con el ID del parémetro.
     * @throws CarritoComprasIdClienteInvalidoException        Se lanza si se comprueba que el
     *                                                         ID del Cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException     Se lanza si se comprueba que el
     *                                                         ID de la Paqueteria es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si el
     *                                                         Cliente no tiene un Carrito de compras vigente.
     */
    @Override
    public void asignarPaqueteriaCarritoCliente(Integer idCliente, Integer idPaqueteria) throws CarritoComprasIdClienteInvalidoException, CarritoComprasIdPaqueteriaInvalidoException, CarritoComprasClienteSinCarritoVigenteException {
        administrador.asignarPaqueteriaCarritoCliente(idCliente, idPaqueteria);
    }

    /**
     * Implementación del método crearPedidoProductosCarritoCliente(), de la interfaz {@link IAdministradorProductos},
     * que permite crear un pedido con los productos que contiene el Carrito de compras
     * del Cliente con el ID recibido como parámetro.
     *
     * @param idCliente Objeto Integer que representa el ID del Cliente que va a hacer el pedido.
     * @throws CarritoComprasIdClienteInvalidoException        Se lanza si se comprueba que el ID de cliente recibido
     *                                                         es inválido, dentro de este subsistema.
     * @throws PedidosIdProductoInvalidoException              Se lanza si se comprueba que el
     *                                                         ID de algún Producto dentro del carrito del Cliente es inválido, dentro
     *                                                         del susbsistema administradorPedidos.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se
     *                                                         comprueba que el Cliente con el ID del parámetro no cuenta con un Carrito
     *                                                         de Compras.
     * @throws CarritoComprasCarritoVacioException             Se lanza si se comprueba que el
     *                                                         Cliente con el ID del parámetro no tiene productos en su Carrito de compras.
     * @throws ProductosIdProductoInvalidoException            Se lanza cuando se comprueba
     *                                                         que el ID de producto es inválido, dentro del subsistema administradorProductos.
     */
    @Override
    public void crearPedidoProductosCarritoCliente(Integer idCliente) throws CarritoComprasIdClienteInvalidoException, PedidosIdProductoInvalidoException, CarritoComprasClienteSinCarritoVigenteException, CarritoComprasCarritoVacioException, ProductosIdProductoInvalidoException {
        administrador.crearPedidoProductosCarritoCliente(idCliente);
    }

    @Override
    public void caducarCarritoCompras(Integer idCliente) throws CarritoComprasIdClienteInvalidoException{
        administrador.caducarCarritoCompras(idCliente);
    }
}
