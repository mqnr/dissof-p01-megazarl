package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
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
 * IAdministradorCarritoCompras.java
 *
 * Interfaz del subsistema AdministradorCarritoCompras, que administra los carritos
 * de compras de los clientes, permitiendo agregar y eliminar productos, calcular
 * costos de envío, y realizar pedidos a partir del contenido del carrito.
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
public interface IAdministradorCarritoCompras {
    /**
     * Método que permite obtener la lista de productos en el carrito de un cliente.
     *
     * @param idCliente Objeto Integer que representa el ID del cliente.
     * @return Objeto List de InformacionProductoCarritoDTO que contiene la información
     * de los productos en el carrito del cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     */
    public abstract List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente)
            throws CarritoComprasIdClienteInvalidoException;

    /**
     * Método que permite agregar un producto al carrito de un cliente.
     *
     * @param idCliente Objeto Integer que representa el ID del cliente.
     * @param idProducto Objeto Integer que representa el ID del producto a agregar.
     * @param cantidad Valor int que representa la cantidad del producto a agregar.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     * @throws CarritoComprasProductoSinInventarioException Se lanza si se comprueba que no
     * hay suficiente inventario del producto, dentro de este subsistema.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID del
     * producto es inválido, dentro del subsistema de productos.
     * @throws ProductosProductoSinInventarioException Se lanza si se comprueba que no hay
     * suficiente inventario del producto, dentro del subsistema de productos.
     */
    public abstract void agregarProducto(Integer idCliente, Integer idProducto, int cantidad) 
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException;

    /**
     * Método que permite eliminar un producto del carrito de un cliente.
     *
     * @param idCliente Objeto Integer que representa el ID del cliente.
     * @param idProducto Objeto Integer que representa el ID del producto a eliminar.
     * @param cantidad Valor int que representa la cantidad del producto a eliminar.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     * @throws CarritoComprasCarritoSinProductoException Se lanza si se comprueba que el
     * carrito no contiene el producto especificado.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID del
     * producto es inválido, dentro del subsistema de productos.
     * @throws ProductosProductoSinInventarioException Se lanza si se comprueba que no hay
     * suficiente inventario del producto, dentro del subsistema de productos.
     */
    public abstract void eliminarProducto(Integer idCliente, Integer idProducto, int cantidad)
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasCarritoSinProductoException, 
            CarritoComprasClienteSinCarritoVigenteException,
            ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException;

    /**
     * Método que permite obtener el número total de productos en el carrito de un cliente.
     *
     * @param idCliente Objeto Integer que representa el ID del cliente.
     * @return Objeto Integer que representa el número total de productos en el carrito.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     */
    public abstract Integer obtenerNumeroProductos(Integer idCliente)
            throws CarritoComprasIdClienteInvalidoException;

    /**
     * Método que permite obtener información sobre el monto mínimo requerido para envío
     * gratuito y el monto actual del carrito.
     *
     * @param idCliente Objeto Integer que representa el ID del cliente.
     * @return Objeto MontoMinimoEnvioGratuitoDTO que contiene el monto mínimo para envío
     * gratuito y el monto actual del carrito.
     */
    public abstract MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito(Integer idCliente);

    /**
     * Método que permite obtener el tiempo estimado de preparación de los productos en
     * el carrito de un cliente.
     *
     * @param idCliente Objeto Integer que representa el ID del cliente.
     * @return Objeto TiempoEstimadoPreparacionEnvioPedidoDTO que contiene el rango de días
     * estimados para la preparación y envío del pedido.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     */
    public abstract TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(Integer idCliente)
            throws CarritoComprasClienteSinCarritoVigenteException;

    /**
     * Método que permite calcular el costo de envío para los productos en el carrito
     * de un cliente con una paquetería específica.
     *
     * @param idClientePaqueteriaCalculoCostoEnvioDTO Objeto IdClientePaqueteriaCalculoCostoEnvioDTO
     * que contiene el ID del cliente y el ID de la paquetería.
     * @return Valor float que representa el costo de envío calculado.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws PedidosIdClienteInvalidoException Se lanza si se comprueba que el ID del cliente
     * es inválido, dentro del subsistema de pedidos.
     * @throws PedidosIdProductoInvalidoException Se lanza si se comprueba que el ID del producto
     * es inválido, dentro del subsistema de pedidos.
     * @throws PedidosIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID de la
     * paquetería es inválido, dentro del subsistema de pedidos.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID de la
     * paquetería es inválido, dentro del subsistema de paqueterías.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID del
     * producto es inválido, dentro del subsistema de productos.
     */
    public abstract float obtenerCostoEnvioProductos(IdClientePaqueteriaCalculoCostoEnvioDTO idClientePaqueteriaCalculoCostoEnvioDTO)
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdPaqueteriaInvalidoException,
            PedidosIdClienteInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdPaqueteriaInvalidoException,
            PaqueteriasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            ProductosIdProductoInvalidoException;

    /**
     * Método que permite asignar una paquetería al carrito de un cliente.
     *
     * @param idCliente Objeto Integer que representa el ID del cliente.
     * @param idPaqueteria Objeto Integer que representa el ID de la paquetería a asignar.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     */
    public abstract void asignarPaqueteriaCarritoCliente(Integer idCliente, Integer idPaqueteria)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException;

    /**
     * Método que permite crear un pedido a partir de los productos en el carrito de un cliente.
     *
     * @param idCliente Objeto Integer que representa el ID del cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws PedidosIdProductoInvalidoException Se lanza si se comprueba que el ID del producto
     * es inválido, dentro del subsistema de pedidos.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     * @throws CarritoComprasCarritoVacioException Se lanza si se comprueba que el carrito
     * del cliente está vacío.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el ID del
     * producto es inválido, dentro del subsistema de productos.
     */
    public abstract void crearPedidoProductosCarritoCliente(Integer idCliente)
            throws CarritoComprasIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasCarritoVacioException,
            ProductosIdProductoInvalidoException;
}
