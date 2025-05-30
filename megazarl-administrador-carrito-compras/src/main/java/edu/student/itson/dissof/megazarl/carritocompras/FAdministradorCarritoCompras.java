package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
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
    
    private IAdministradorCarritoCompras administradorCarritoCompras;
    
    public FAdministradorCarritoCompras(
            IAdministradorProductos administradorProductos,
            IAdministradorPedidos administradorPedidos,
            IAdministradorClientes administradorClientes,
            IAdministradorPaqueterias administradorPaqueterias,
            Double montoMinimoEnvioGratuito){
        
        this.administradorCarritoCompras = new AdministradorCarritoCompras(
                administradorProductos,
                administradorPedidos,
                administradorClientes,
                administradorPaqueterias,
                montoMinimoEnvioGratuito);
    }
    
   
    
    /**
     * Implementación del método obetenerProductos() de la interfaz {@link IAdministradorCarritoCompras}
     * Permite obtener la informacion de los productos contenidos en el carrito
     * del Cliente con el ID del parámetro.
     *
     * @param idClienteDTO                              Objeto IdClienteDTONegocios que contiene el ID del cliente 
                                                  del que se obtendrá la lista de productos.
     * @return                                          Objeto {@literal List<InformacionProductoCarritoDTONegocios>} de DTOs con la información de 
     *                                                  cada producto en el carrito del cliente. La lista estará vacía
     *                                                  si el Cliente no tiene un carrito vigente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza cuando el ID de cliente recibido es inválido.
     */
    @Override
    public List<InformacionProductoCarritoDTONegocios> obtenerProductos(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException {
        
        return administradorCarritoCompras.obtenerProductos(idClienteDTO);
    }

    /**
     * Implementación del método agregarProducto() de la interfaz {@link IAdministradorCarritoCompras},
     * permite añadir una cantidad de un producto al carrito activo del cliente.
     *
     * @param informacionProductoAgregarCarritoDTONegocios          InformacionProductoAgregarCarritoDTONegocios que contiene la
                                                      el ID de cliente que va a agregar el produco a su carrito, 
                                                      el ID del producto, y la cantidad a agregar.
     * @throws CarritoComprasIdClienteInvalidoException     Se lanza cuando se comprueba que
     *                                                      ID de cliente recibido es inválido, en este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException    Se lanza cuando se comprueba que
     *                                                      ID de producto es inválido, en este subsistema.
     * @throws CarritoComprasProductoSinInventarioException Se lanza cuando se comprueba que
     *                                                      un producto del carrito de compras no tiene inventario.
     */
    @Override
    public void agregarProducto(InformacionProductoAgregarCarritoDTONegocios informacionProductoAgregarCarritoDTONegocios) 
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException {
        
        administradorCarritoCompras.agregarProducto(informacionProductoAgregarCarritoDTONegocios);
    }

    /**
     * Implementación del método eliminarProducto(), de la interfaz {@link IAdministradorCarritoCompras},
     * que permite elminar la cantidad del parámetro del producto con ID del parámetro
     * del carrito del Cliente con el ID del parámetro.
     *
     * @param informacionProductoEliminarCarritoDTO            InformacionProductoEliminarCarritoDTONegocios que contiene la
                                                         el ID de cliente que va a eliminar el produco a su carrito, 
                                                         el ID del producto, y la cantidad a eliminar.
     * @throws CarritoComprasIdClienteInvalidoException        Se lanza cuando se verifica que
     *                                                         el ID del Cliente recibido es inválido, dentro de este subsitema.
     * @throws CarritoComprasIdProductoInvalidoException       Se lanza cuando se verifica
     *                                                         que el ID del Producto a eliminar es inválido, dentro de este subsistema.
     * @throws CarritoComprasCarritoSinProductoException       Se lanza cuando se
     *                                                         comprueba que el Cliente no tiene un Carrito de compras vigente.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza cuando
     *                                                         el Cliente no tiene ningún Carrito de compras vigente asociado.
     */
    @Override
    public void eliminarProducto(InformacionProductoEliminarCarritoDTONegocios informacionProductoEliminarCarritoDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasCarritoSinProductoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException {
        
        administradorCarritoCompras.eliminarProducto(informacionProductoEliminarCarritoDTO);
    }

    /**
     * Implementación del método obtenerNumeroProductos(), de la interfaz {@link IAdministradorProductos},
     * que permite obtener el número de artículos que tiene el Cliente con el ID del parámetro.
     *
     * @param idClienteDTO                              Objeto de tipo IdClienteDTONegocios que contiene el ID del cliente
                                                  de cuyo carrito se obtendrán los productos incluidos.
     * @return                                          Objeto Integer que representa el número de productos en el 
     *                                                  carrito del Cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza cuando se verifica que
     *                                                  el ID del Cliente recibido es inválido, dentro de este subsitema.
     */
    @Override
    public Integer obtenerNumeroProductos(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException {
        
        return administradorCarritoCompras.obtenerNumeroProductos(idClienteDTO);
    }

    /**
     * Implementación del método obtenerTiempoEstimadoPreparacionProductos() de la interfaz {@link IAdministradorProductos},
     * que permite obtener el tiempo estimado de preparación del envío en la matriz de la empresa, es decir, el tiempo
     * aproximado que el Cliente debe esperar hasta que se realice el envío desde la matriz a la dirección de envío.
     *
     * @param idClienteDTO  Objeto IdClienteDTONegocios que representa el ID del cliente a obtener el tiempo estimado de preparación de
                      prouctos del carrito.
     * @return              Objeto TiempoEstimadoPreparacionEnvioPedidoDTONegocios, que contiene el tiempo mínimo y máximo de preparación
                      del pedido.
     * @throws              CarritoComprasClienteSinCarritoVigenteException Se lanza cuando el Cliente no tiene asociado ningún carrito
     *                      vigente.
     */
    @Override
    public TiempoEstimadoPreparacionEnvioPedidoDTONegocios obtenerTiempoEstimadoPreparacionProductos(IdClienteDTONegocios idClienteDTO) 
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProveedorInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasIdProductoInventarioInvalidoException,
            CarritoComprasIdDireccionInvalidoException,
            CarritoComprasPersistenciaException{
        
        return administradorCarritoCompras.obtenerTiempoEstimadoPreparacionProductos(idClienteDTO);
    }

    /**
     * Implementación del método obtnerInformacionMontoEnvioMinimoGrautito() que
     * la interfaz {@link IAdministradorProductos} y permite obtener información
     * sobre el monto del carrito actual y el monto necesario para que el envío
     * sea gratuito, de un determinado Cliente.
     *
     * @param idClienteDTO  Objeto IdClienteDTONegocios que contiene el ID del cliente del que
                      se va a obtener la información del monto total.
     * @return              Objeto MontoMinimoEnvioGratuitoDTONegocios que contiene el monto mínimo necesario
                      para un envío gratuito y el monto actual del carrito del Cliente.
     */
    @Override
    public MontoMinimoEnvioGratuitoDTONegocios obtenerInformacionMontoEnvioMinimoGratuito(IdClienteDTONegocios idClienteDTO)
        throws CarritoComprasIdClienteInvalidoException, 
        CarritoComprasClienteSinCarritoVigenteException,
        CarritoComprasIdProductoInvalidoException,
        CarritoComprasIdProductoCarritoInvalidoException,
        CarritoComprasPersistenciaException{
        
        return administradorCarritoCompras.obtenerInformacionMontoEnvioMinimoGratuito(idClienteDTO);
    }

    /**
     * Implementación del método obtenerCostoEnvioProductos(), que implementa la interfaz {@link IAdministradorProductos},
     * y que permite obtener el costo total que tendría un pedido con los productos del carrito
     * de un Cliente, a partir de una paquetería seleccionada.
     *
     * @param idClientePaqueteriaDTO                           Objeto de tipo IdClientePaqueteriaDTONegocios que contiene el ID del cliente y el ID
                                                         de la paquetería seleccionado, para obtener el costo total de envío de los
                                                         productos.
     * @return                                                 Objeto de tipo Float que representa el costo total de envío de los productos del carrito,
     *                                                         considerando el costo de envío de la paquetería indicada.
     * @throws CarritoComprasIdClienteInvalidoException        Se lanza si se comprueba que
     *                                                         el ID del Carrito de compras recibido es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException     Se lanza si el ID de la
     *                                                         paquetería recibido es inválido, dentro de este subsistema.
     */
    @Override
    public float obtenerCostoEnvioProductos(IdClientePaqueteriaDTONegocios idClientePaqueteriaDTO) 
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProveedorInvalidoException,
            CarritoComprasIdDireccionInvalidoException,
            CarritoComprasIdProductoInventarioInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException {
        
        return administradorCarritoCompras.obtenerCostoEnvioProductos(idClientePaqueteriaDTO);
    }

    /**
     * Implementación del método asignarPaqueteriaCarritoCliente(), de la interfaz {@link IAdministradorProductos}.
     *
     * @param idClientePaqueteriaDTO                           Objeto IdClientePaqueteriaDTONegocios que contiene el ID del cliente
                                                         al que se le va a asignar la paquetería, además del ID de esta.
     * @throws CarritoComprasIdClienteInvalidoException        Se lanza si se comprueba que el
     *                                                         ID del Cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException     Se lanza si se comprueba que el
     *                                                         ID de la Paqueteria es inválido, dentro de este subsistema.
     * @throws Se lanza si el Cliente no tiene un Carrito de compras vigente.
     * @throws FormatoIdInvalidoNegocioException
     * @throws CarritoComprasAccesoDatosException
     */
    @Override
    public void asignarPaqueteriaCarritoCliente(IdClientePaqueteriaDTONegocios idClientePaqueteriaDTO) 
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdPaqueteriaInvalidoException, 
            CarritoComprasClienteSinCarritoVigenteException,
            FormatoIdInvalidoNegocioException,
            CarritoComprasPersistenciaException {
        
        administradorCarritoCompras.asignarPaqueteriaCarritoCliente(idClientePaqueteriaDTO);
    }

    /**
     * Implementación del método crearPedidoProductosCarritoCliente(), de la interfaz {@link IAdministradorProductos},
     * que permite crear un pedido con los productos que contiene el Carrito de compras
     * del Cliente con el ID recibido como parámetro.
     *
     * @param idClienteDTO                                     Objeto IdClienteDTONegocios que contiene el ID del cliente que va a hacer el pedido.
     * @throws CarritoComprasIdClienteInvalidoException        Se lanza si se comprueba que el ID de cliente recibido
     *                                                         es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se
     *                                                         comprueba que el Cliente con el ID del parámetro no cuenta con un Carrito
     *                                                         de Compras.
     * @throws CarritoComprasCarritoVacioException             Se lanza si se comprueba que el
     *                                                         Cliente con el ID del parámetro no tiene productos en su Carrito de compras.
     */
    @Override
    public void crearPedidoProductosCarritoCliente(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasCarritoVacioException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdDireccionInvalidoException,
            CarritoComprasIdProductoInventarioInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasPersistenciaException {
        
        administradorCarritoCompras.crearPedidoProductosCarritoCliente(idClienteDTO);
    }

    @Override
    public boolean validarProductoCarrito(IdProductoCarritoDTONegocios idProductoCarritoDTO) 
            throws CarritoComprasPersistenciaException {
        
        return administradorCarritoCompras.validarProductoCarrito(idProductoCarritoDTO);
    }

    @Override
    public ProductoCarritoDTONegocios obtenerProductoCarrito(IdProductoCarritoDTONegocios idProductoCarritoDTO) 
            throws CarritoComprasPersistenciaException {
        
        return administradorCarritoCompras.obtenerProductoCarrito(idProductoCarritoDTO);
    }

}
