package edu.student.itson.dissof.megazarl.administradorpedidos;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.negocios.PedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCantidadCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionPedidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionCrearPedidoDTO;
import edu.student.itson.dissof.megazarl.mapas.IAdministradorMapas;
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

    private final AdministradorPedidos administradorPedidos;
    
    
    public FAdministradorPedidos(
            IAdministradorProductos administradorProductos,
            IAdministradorSucursales administradorSucursales,
            IAdministradorClientes administradorClientes,
            IAdministradorPaqueterias administradorPaqueterias,
            IAdministradorProveedores administradorProveedores,
            IAdministradorDirecciones administradorDirecciones,
            IAdministradorMapas administradorMapas){
        
        this.administradorPedidos = new AdministradorPedidos(
                administradorProductos,
                administradorSucursales,
                administradorClientes,
                administradorPaqueterias,
                administradorProveedores,
                administradorDirecciones,
                administradorMapas);
    }
    
    /**
     * Implementación del método obtenerTiempoEstimadoPreparacion(), de la interfaz 
     * {@link IAdministradorPedidos} que permite obtener el tiempo estimado de preparación para una lista
     * de productos en un carrito de compras.
     * @param listaIdProductoCantidadCarritoDTO Objeto {@literal List<IdProductoCantidadCarritoDTO>}
     *                                          que contiene los productos y sus cantidades para calcular el tiempo de preparación.
     * @return                                  Dato float que representa el tiempo estimado de preparación en horas.
     * @throws                                  PedidosIdProductoInvalidoException Se lanza si el id de uno de los productos
     *                                          recibidos es inválido, dentro del subsistema adminsitradorPedidos
     */
    @Override
    public float obtenerTiempoEstimadoPreparacion(List<IdProductoCantidadCarritoDTO> listaIdProductoCantidadCarritoDTO) 
            throws PedidosIdProductoInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosIdProductoInventarioInvalidoException,
            PedidosIdDireccionInvalidoException {
        
        return administradorPedidos.obtenerTiempoEstimadoPreparacion(listaIdProductoCantidadCarritoDTO);
    }

    /**
     * Implementación del método calcularCostoEnvioProductosPaqueteria(), de la interfaz {@link IAdministradorPedidos},
     * que permite obtener el costot total del envío de productos tomando en cuenta una Paquetería.
     * @param informacionCalculoCostoPedidoDTO Objeto InformacionPedidoClienteDTO que contiene los IDs 
     *                                         de Producto y Paqueteria, así como la cantidad solicitadade cada uno.
     * @return                                 Dato float que representa el costo total de envío.
     * @throws                                 PedidosIdClienteInvalidoException Se lanza si se comprueba que el ID de 
     *                                         Cliente es inválido, dentor del subsistema administradorPedidos.
     * @throws                                 PedidosIdProductoInvalidoException Se lanza si se comprueba que el 
     *                                         ID de Producto es inválido, dentro del subsistema administradorPedidos.
     * @throws                                 PedidosIdPaqueteriaInvalidoException Se lanza si se comprueba que 
     *                                         el id de Paquetería es inválido, dentro del subsitema administradorPedidos.
     * 
     */
    @Override
    public float calcularCostoEnvioProductosPaqueteria(InformacionPedidoClienteDTO informacionCalculoCostoPedidoDTO) 
            throws PedidosIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdPaqueteriaInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdDireccionInvalidoException,
            PedidosIdProductoInventarioInvalidoException {
        
        return administradorPedidos.calcularCostoEnvioProductosPaqueteria(informacionCalculoCostoPedidoDTO);
    }

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
     */
    @Override
    public PedidoDTO realizarPedido(InformacionCrearPedidoDTO informacionCrearPedidoDTO) 
            throws PedidosIdProductoInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdProductoInvalidoException,
            ProductosIdProductoInvalidoException,
            PedidosIdProductoInventarioInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosIdDireccionInvalidoException {
        
        return administradorPedidos.realizarPedido(informacionCrearPedidoDTO);
    }

}
