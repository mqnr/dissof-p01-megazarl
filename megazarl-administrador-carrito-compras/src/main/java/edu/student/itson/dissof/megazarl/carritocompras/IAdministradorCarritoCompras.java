package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoSinProductoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoVacioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasClienteSinCarritoVigenteException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoCarritoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasPersistenciaException;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClientePaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoAgregarCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoEliminarCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.MontoMinimoEnvioGratuitoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.TiempoEstimadoPreparacionEnvioPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
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
     * @param idClienteDTO Objeto IdClienteDTONegocios que cotntiene el ID del cliente.
     * @return Objeto List de InformacionProductoCarritoDTONegocios que contiene la información
 de los productos en el carrito del cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException
     * @throws CarritoComprasIdProductoCarritoInvalidoException
     */
    public abstract List<InformacionProductoCarritoDTONegocios> obtenerProductos(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException;

    /**
     * Método que permite agregar un producto al carrito de un cliente.
     *
     * @param informacionProductoAgregarCarritoDTO Objeto InformacionProductoAgregarCarritoDTONegocios,
 que contiene la informacion necesaria para agregar un producto al carrito de 
 compras de un cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     * @throws CarritoComprasProductoSinInventarioException Se lanza si se comprueba que no
     * hay suficiente inventario del producto, dentro de este subsistema.
     * @throws CarritoComprasIdProductoCarritoInvalidoException
     * @throws CarritoComprasAccesoDatosException
     */
    public abstract void agregarProducto(InformacionProductoAgregarCarritoDTONegocios informacionProductoAgregarCarritoDTO) 
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException;

    /**
     * Método que permite eliminar un producto del carrito de un cliente.
     *
     * @param informacionProductoEliminarCarritoDTO que contiene el ID del cliente
     * de cuyo carrito se va a eliminar el producto, el ID del producto que se va
     * a eliminar, y la cantidad a eliminar del producto.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     * @throws CarritoComprasCarritoSinProductoException Se lanza si se comprueba que el
     * carrito no contiene el producto especificado.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     */
    public abstract void eliminarProducto(InformacionProductoEliminarCarritoDTONegocios informacionProductoEliminarCarritoDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasCarritoSinProductoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException;

    /**
     * Método que permite obtener el número total de productos en el carrito de un cliente.
     *
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del cliente.
     * @return Objeto Integer que representa el número total de productos en el carrito.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdProductoCarritoInvalidoException
     */
    public abstract Integer obtenerNumeroProductos(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException;

    /**
     * Método que permite obtener el tiempo estimado de preparación de los productos en
     * el carrito de un cliente.
     *
     * @param idClienteDTO Objeto IdClienteDTONegocios que representa el ID del cliente.
     * @return Objeto TiempoEstimadoPreparacionEnvioPedidoDTONegocios que contiene el rango de días
 estimados para la preparación y envío del pedido.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el
     * ID del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     * @throws CarritoComprasIdProveedorInvalidoException
     * @throws CarritoComprasIdSucursalInvalidoException
     * @throws CarritoComprasIdProductoInvalidoException
     * @throws CarritoComprasIdProductoCarritoInvalidoException
     * @throws CarritoComprasIdProductoInventarioInvalidoException
     * @throws CarritoComprasIdDireccionInvalidoException
     */
    public abstract TiempoEstimadoPreparacionEnvioPedidoDTONegocios obtenerTiempoEstimadoPreparacionProductos(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProveedorInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasIdProductoInventarioInvalidoException,
            CarritoComprasIdDireccionInvalidoException,
            CarritoComprasPersistenciaException;

    /**
     * Método que permite obtener información sobre el monto mínimo requerido para envío
     * gratuito y el monto actual del carrito.
     *
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del cliente.
     * @return Objeto MontoMinimoEnvioGratuitoDTONegocios que contiene el monto mínimo para envío
 gratuito y el monto actual del carrito.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el
     * ID del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     * @throws CarritoComprasIdProductoInvalidoException
     * @throws CarritoComprasIdProductoCarritoInvalidoException
     */
    public abstract MontoMinimoEnvioGratuitoDTONegocios obtenerInformacionMontoEnvioMinimoGratuito(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException;
    
    
    /**
     * Método que permite calcular el costo de envío para los productos en el carrito
     * de un cliente con una paquetería específica.
     *
     * @param idClientePaqueteriaDTO Objeto IdClientePaqueteriaDTONegocios
 que contiene el ID del cliente y el ID de la paquetería.
     * @return Valor float que representa el costo de envío calculado.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException
     * @throws CarritoComprasIdProductoInvalidoException
     * @throws CarritoComprasIdSucursalInvalidoException
     * @throws CarritoComprasIdProveedorInvalidoException
     * @throws edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdDireccionInvalidoException
     * @throws edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInventarioInvalidoException
     * @throws edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoCarritoInvalidoException
     */
    public abstract float obtenerCostoEnvioProductos(IdClientePaqueteriaDTONegocios idClientePaqueteriaDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProveedorInvalidoException,
            CarritoComprasIdDireccionInvalidoException,
            CarritoComprasIdProductoInventarioInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException;

    /**
     * Método que permite asignar una paquetería al carrito de un cliente.
     *
     * @param idClientePaqueteriaDTO Objeto IdClientePaqueteriaDTONegocios
 que contiene el ID del cliente y el ID de la paquetería.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     */
    public abstract void asignarPaqueteriaCarritoCliente(IdClientePaqueteriaDTONegocios idClientePaqueteriaDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            FormatoIdInvalidoNegocioException,
            CarritoComprasPersistenciaException;

    /**
     * Método que permite crear un pedido a partir de los productos en el carrito de un cliente.
     *
     * @param idClienteDTO Objeto IdClienteDTONegocios que contiene el ID del cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     * @throws CarritoComprasCarritoVacioException Se lanza si se comprueba que el carrito
     * del cliente está vacío.
     * @throws CarritoComprasIdProductoInvalidoException
     * @throws CarritoComprasIdSucursalInvalidoException
     * @throws CarritoComprasIdDireccionInvalidoException
     * @throws CarritoComprasIdProductoInventarioInvalidoException
     * @throws CarritoComprasIdProductoCarritoInvalidoException
     * @throws CarritoComprasAccesoDatosException
     */
    public abstract void crearPedidoProductosCarritoCliente(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasCarritoVacioException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdDireccionInvalidoException,
            CarritoComprasIdProductoInventarioInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException;
    
    
    public abstract boolean validarProductoCarrito(IdProductoCarritoDTONegocios idProductoCarritoDTO) 
            throws CarritoComprasPersistenciaException;
    
    public abstract ProductoCarritoDTONegocios obtenerProductoCarrito(IdProductoCarritoDTONegocios idProductoCarritoDTO) 
            throws CarritoComprasPersistenciaException;
    
}
