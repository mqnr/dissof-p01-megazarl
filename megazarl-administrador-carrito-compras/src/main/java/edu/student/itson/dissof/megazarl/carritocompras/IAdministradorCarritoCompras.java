package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoSinProductoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoVacioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasClienteSinCarritoVigenteException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClientePaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoAgregarCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoEliminarCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.TiempoEstimadoPreparacionEnvioPedidoDTO;
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
     * @param idClienteDTO Objeto IdClienteDTO que cotntiene el ID del cliente.
     * @return Objeto List de InformacionProductoCarritoDTO que contiene la información
     * de los productos en el carrito del cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException
     */
    public abstract List<InformacionProductoCarritoDTO> obtenerProductos(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException;

    /**
     * Método que permite agregar un producto al carrito de un cliente.
     *
     * @param informacionProductoAgregarCarritoDTO Objeto InformacionProductoAgregarCarritoDTO,
     * que contiene la informacion necesaria para agregar un producto al carrito de 
     * compras de un cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException Se lanza si se comprueba que el ID
     * del producto es inválido, dentro de este subsistema.
     * @throws CarritoComprasProductoSinInventarioException Se lanza si se comprueba que no
     * hay suficiente inventario del producto, dentro de este subsistema.
     */
    public abstract void agregarProducto(InformacionProductoAgregarCarritoDTO informacionProductoAgregarCarritoDTO) 
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException;

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
    public abstract void eliminarProducto(InformacionProductoEliminarCarritoDTO informacionProductoEliminarCarritoDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasCarritoSinProductoException,
            CarritoComprasClienteSinCarritoVigenteException;

    /**
     * Método que permite obtener el número total de productos en el carrito de un cliente.
     *
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del cliente.
     * @return Objeto Integer que representa el número total de productos en el carrito.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     */
    public abstract Integer obtenerNumeroProductos(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException;

    /**
     * Método que permite obtener el tiempo estimado de preparación de los productos en
     * el carrito de un cliente.
     *
     * @param idClienteDTO Objeto IdClienteDTO que representa el ID del cliente.
     * @return Objeto TiempoEstimadoPreparacionEnvioPedidoDTO que contiene el rango de días
     * estimados para la preparación y envío del pedido.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el
     * ID del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     * @throws edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProveedorInvalidoException
     * @throws edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdSucursalInvalidoException
     * @throws edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInvalidoException
     */
    public abstract TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProveedorInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProductoInvalidoException;

    /**
     * Método que permite obtener información sobre el monto mínimo requerido para envío
     * gratuito y el monto actual del carrito.
     *
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del cliente.
     * @return Objeto MontoMinimoEnvioGratuitoDTO que contiene el monto mínimo para envío
     * gratuito y el monto actual del carrito.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el
     * ID del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     * @throws edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInvalidoException
     */
    public abstract MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException;
    
    
    /**
     * Método que permite calcular el costo de envío para los productos en el carrito
     * de un cliente con una paquetería específica.
     *
     * @param idClientePaqueteriaDTO Objeto IdClientePaqueteriaDTO
     * que contiene el ID del cliente y el ID de la paquetería.
     * @return Valor float que representa el costo de envío calculado.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException
     * @throws CarritoComprasIdProductoInvalidoException
     * @throws CarritoComprasIdSucursalInvalidoException
     * @throws CarritoComprasIdProveedorInvalidoException
     */
    public abstract float obtenerCostoEnvioProductos(IdClientePaqueteriaDTO idClientePaqueteriaDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProveedorInvalidoException;

    /**
     * Método que permite asignar una paquetería al carrito de un cliente.
     *
     * @param idClientePaqueteriaDTO Objeto IdClientePaqueteriaDTO
     * que contiene el ID del cliente y el ID de la paquetería.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     */
    public abstract void asignarPaqueteriaCarritoCliente(IdClientePaqueteriaDTO idClientePaqueteriaDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException;

    /**
     * Método que permite crear un pedido a partir de los productos en el carrito de un cliente.
     *
     * @param idClienteDTO Objeto IdClienteDTO que contiene el ID del cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se comprueba que
     * el cliente no tiene un carrito de compras vigente.
     * @throws CarritoComprasCarritoVacioException Se lanza si se comprueba que el carrito
     * del cliente está vacío.
     * @throws CarritoComprasIdProductoInvalidoException
     */
    public abstract void crearPedidoProductosCarritoCliente(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasCarritoVacioException,
            CarritoComprasIdProductoInvalidoException;
    
}
