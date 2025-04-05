package edu.student.itson.dissof.megazarl.administradorpedidos;

import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.dto.DireccionClientePesoTiempoProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.DireccionMatrizDTO;
import edu.student.itson.dissof.megazarl.dto.IdProductoCantidadCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionCalculoCostoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionCrearPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionNoDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.EstadoPedido;
import edu.student.itson.dissof.megazarl.objetosnegocio.PaqueteriaON;
import edu.student.itson.dissof.megazarl.objetosnegocio.PedidoON;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoON;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventarioON;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
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

    /**
     * Objeto que implementa la interfaz {@link IAdministradorProductos}, es el
     * subsistema aministradorPedidos.
     */
    IAdministradorProductos administradorProductos;
    /**
     * Objeto que implementa la interfaz {@link IAdministradorClientes}, es el
     * subsistema aministradorClientes.
     */
    IAdministradorClientes administradorClientes;
    /**
     * Objeto que implementa la interfaz {@link IAdministradorPaqueterias}, es el
     * subsistema aministradorPaqueterias.
     */
    IAdministradorPaqueterias administradorPaqueterias;
    /**
     * Objeto que implementa la interfaz {@link IAdministradorPaqueterias}, es el
     * subsistema aministradorSucursales.
     */
    IAdministradorSucursales administradorSucursales;
    

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
        
        this.administradorProductos = administradorProductos;
        this.administradorClientes = administradorClientes;
        this.administradorPaqueterias = administradorPaqueterias;
        this.administradorSucursales = administradorSucursales;
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
    public float obtenerTiempoEstimadoPreparacion(List<IdProductoCantidadCarritoDTO> listaIdProductoCantidadCarritoDTO)  {

        return 4.5F;
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
    public float calcularCostoEnvioProductosPaqueteria(InformacionCalculoCostoPedidoDTO informacionCalculoCostoPedidoDTO) 
            throws PedidosIdClienteInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdPaqueteriaInvalidoException,
            PaqueteriasIdPaqueteriaInvalidoException,
            ProductosIdProductoInvalidoException{
        
        Integer idCliente = informacionCalculoCostoPedidoDTO.getIdCliente();
        
        if(!administradorClientes.validarIdCliente(idCliente)){
            throw new PedidosIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }
        
        Integer idPaqueteria = informacionCalculoCostoPedidoDTO.getIdPaqueteria();
        
        if(!administradorPaqueterias.validarPaqueteria(idPaqueteria)){
            throw new PedidosIdPaqueteriaInvalidoException("El ID de paqueteria: " + idPaqueteria + " es inválido.");
        }
        
        HashMap<Integer, Integer> mapaProductosCantidades = informacionCalculoCostoPedidoDTO.getMapaProductosCantidades();
        
        for(Integer idProducto: mapaProductosCantidades.keySet()){
            
            if(!administradorProductos.validarProducto(idProducto)){
                throw new PedidosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
            }
            
        }
        
        try {
            InformacionNoDerivadaCPDireccionEnvioDTO informacionNoDerivadaCPDireccionEnvioDTO
                    = administradorClientes.obtenerInformacionNoDerivadaCPDireccionEnvio(idCliente);
            
            String codigoPostalCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCodigoPostal();
            String calleCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCalle();
            String numeroCliente =  informacionNoDerivadaCPDireccionEnvioDTO.getNumero();

            DireccionMatrizDTO direccionMatrizDTO = administradorSucursales.obtenerDireccionMatriz();

            String codigoPostalMatriz = direccionMatrizDTO.getCodigoPostalMatriz();
            String calleMatriz = direccionMatrizDTO.getCalleMatriz();
            String numeroMatriz = direccionMatrizDTO.getNumeroMatriz();

            Float costoTotalEnvioProductos = 0F;

            for(Integer idProducto: mapaProductosCantidades.keySet()){

                int cantidadProductoSolicitado = mapaProductosCantidades.get(idProducto);

                ProductoON producto = administradorProductos.obtenerProducto(idProducto);

                List<ProductoInventarioON> listaProductosInventario = producto.getListaProductoInventario();

                Collections.sort(listaProductosInventario, 
                        (p1, p2) -> Float.compare(p1.getSucursal().getTiempoMatriz(), p2.getSucursal().getTiempoMatriz()));

                Double pesoKgProductoInventario = producto.getPesoKg();

                for(ProductoInventarioON productoInventario: listaProductosInventario){
                    
                    
                    
                    Float tiempoEnvioMatrizHorasProductoInventario = productoInventario.getSucursal().getTiempoMatriz();

                    if(tiempoEnvioMatrizHorasProductoInventario >= 0){
                        DireccionClientePesoTiempoProductoInventarioDTO direccionClientePesoTiempoProductoInventarioDTO =
                        new DireccionClientePesoTiempoProductoInventarioDTO(
                                idPaqueteria, 
                                codigoPostalCliente, 
                                calleCliente,
                                numeroCliente,
                                codigoPostalMatriz,
                                calleMatriz,
                                numeroMatriz,
                                pesoKgProductoInventario, 
                                tiempoEnvioMatrizHorasProductoInventario);
                        costoTotalEnvioProductos += 
                           administradorPaqueterias.obtenerCostoEnvioProducto(direccionClientePesoTiempoProductoInventarioDTO);
                        
                        
                        
                        cantidadProductoSolicitado--;
                    } 

                    if(cantidadProductoSolicitado <= 0){
                        break;
                    }

                }

            }
            return costoTotalEnvioProductos;
            
        } catch (ClientesIdClienteInvalidoException ex) {
            throw new PedidosIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

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
    public boolean realizarPedido(InformacionCrearPedidoDTO informacionCrearPedidoDTO) 
            throws PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException,
            ProductosIdProductoInvalidoException{
 
        Integer idCliente = informacionCrearPedidoDTO.getIdCliente();
        
        if(!administradorClientes.validarIdCliente(idCliente)){
            throw new PedidosIdProductoInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }
        
        Integer idPaqueteria = informacionCrearPedidoDTO.getIdPaqueteria();
        
        if(!administradorPaqueterias.validarPaqueteria(idCliente)){
            throw new PedidosIdProductoInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }
        
        PaqueteriaON paqueteria = administradorPaqueterias.obtenerPaqueteria(idPaqueteria);
        
        if(paqueteria == null){
            throw new PedidosIdProductoInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }
        
        HashMap<Integer,Integer> mapaProductosCantidadPedido = informacionCrearPedidoDTO.getMapaIdsProductosCantidad();
        
        for(Integer idProducto: mapaProductosCantidadPedido.keySet()){
            
            if(!administradorProductos.validarProducto(idProducto)){
                throw new PedidosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
            }
        }
        
        // Se obtienen los productos que se tomarán del inventario.
        List<ProductoInventarioON> productosSolicitados = new LinkedList<>();
        
        for(Integer idProducto: mapaProductosCantidadPedido.keySet()){
            
            int cantidadProductoSolicitado = mapaProductosCantidadPedido.get(idProducto);
            
            ProductoON producto = administradorProductos.obtenerProducto(idProducto);
            
            List<ProductoInventarioON> listaProductosInventario = producto.getListaProductoInventario();
            
            Collections.sort(listaProductosInventario,
                    (p1, p2) -> Float.compare(p1.getSucursal().getTiempoMatriz(), p2.getSucursal().getTiempoMatriz()));


            for(ProductoInventarioON productoInventario: listaProductosInventario){
   
                productosSolicitados.add(productoInventario);

                cantidadProductoSolicitado--;

                if(cantidadProductoSolicitado <= 0){
                    break;
                }

                
            }

        }
    
        // HashMap que contiene a cada ProductoInventario junto con un valor Booleano que 
        // indica si ya llegó a la matriz.
        HashMap<ProductoInventarioON, Boolean> mapaProductosRequeridos = new HashMap<>();
        
        for(ProductoInventarioON productoInventario: productosSolicitados){
            mapaProductosRequeridos.put(productoInventario, false);
        }
        
        
        // Se crea el objeto Pedido.
        PedidoON pedidoSucursalMatriz = new PedidoON(
                mapaProductosRequeridos,
                paqueteria,
                EstadoPedido.PENDIENTE
        );
        
  
        return true;
    }
}
