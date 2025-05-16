package edu.student.itson.dissof.megazarl.administradorpedidos;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.enumeradores.EstadoPedido;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PedidoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.SucursalDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoMatrizClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoCantidadCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionPedidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionCrearPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdSucursalDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PedidoDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoPedidoDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoProveedorMatrizDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoSucursalMatrizDTO;
import edu.student.itson.dissof.megazarl.mapas.IAdministradorMapas;
import edu.student.itson.dissof.megazarl.objetosnegocio.Pedido;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoInventario;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class AdministradorPedidos implements IAdministradorPedidos {
    
    private final IAdministradorProductos administradorProductos;
    private final IAdministradorSucursales administradorSucursales;
    private final IAdministradorClientes administradorClientes;
    private final IAdministradorPaqueterias administradorPaqueterias;
    private final IAdministradorProveedores administradorProveedores;
    private final IAdministradorDirecciones administradorDirecciones;
    private final IAdministradorMapas administradorMapas;
   

    public AdministradorPedidos(
            IAdministradorProductos administradorProductos,
            IAdministradorSucursales administradorSucursales,
            IAdministradorClientes administradorClientes,
            IAdministradorPaqueterias administradorPaqueterias,
            IAdministradorProveedores administradorProveedores,
            IAdministradorDirecciones administradorDirecciones,
            IAdministradorMapas administradorMapas){
        
        this.administradorProductos = administradorProductos;
        this.administradorSucursales = administradorSucursales;
        this.administradorClientes = administradorClientes;
        this.administradorPaqueterias = administradorPaqueterias;
        this.administradorProveedores = administradorProveedores;
        this.administradorDirecciones = administradorDirecciones;
        this.administradorMapas = administradorMapas;
        
    }

    /**
     * Implementación del método obtenerTiempoEstimadoPreparacion(), de la interfaz 
     * {@link IAdministradorPedidos} que permite obtener el tiempo estimado de preparación para una lista
     * de productos en un carrito de compras.
     * @param listaIdProductoCantidadCarritoDTO Objeto {@literal List<IdProductoCantidadCarritoDTO>}
     * que contiene los productos y sus cantidades para calcular el tiempo de preparación.
     * @return Dato float que representa el tiempo estimado de preparación en horas.
     * @throws PedidosIdProductoInvalidoException Se lanza si el id de uno de los productos
     * recibidos es inválido, dentro del subsistema adminsitradorPedidos
     * @throws ProductosIdProductoInvalidoException Se lanza si el id de uno de los
     * productos recibidos es inválido, dentro del subsistema administradorProductos
     */
    @Override
    public float obtenerTiempoEstimadoPreparacion(List<IdProductoCantidadCarritoDTO> listaIdProductoCantidadCarritoDTO) 
            throws PedidosIdProductoInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdProductoInventarioInvalidoException,
            PedidosIdDireccionInvalidoException,
            PedidosIdSucursalInvalidoException{
        
        float tiempoPreparacionEstimado = 0;

        for(IdProductoCantidadCarritoDTO idProductoCantidadCarritoDTO: listaIdProductoCantidadCarritoDTO){
            
            Long idProducto = idProductoCantidadCarritoDTO.getIdProducto();
            
            IdProductoDTO idProductoDTO = new IdProductoDTO(idProducto);
            if (!administradorProductos.validarProducto(idProductoDTO)) {
                throw new PedidosIdProductoInvalidoException("El ID de producto es inválido.");
            }
            
            ProductoDTO producto = administradorProductos.obtenerProducto(idProductoDTO);
            
            if(producto == null){
                throw new PedidosIdProductoInvalidoException("El ID de producto es inválido.");
            }
                    
            List<IdProductoInventarioDTO> listaIdsProductosInventario = producto.getIdsProductosInventario();
 
            // Se crea un hashMap para almacenar los productos en inventario del producto
            // como clave, y su tiempo de traslado a matriz como valor
            HashMap<ProductoInventarioDTO, Float> mapaProductoInventarioTiempoTrasladoMatriz = new HashMap<>();
            
            // Se obtienen los datos de la dirección de la sucursal
            SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
            
            IdDireccionDTO idDireccionSucursalMatriz = sucursalMatriz.getIdDireccion();
            
            if(!administradorDirecciones.validarDireccion(idDireccionSucursalMatriz)){
                throw new PedidosIdDireccionInvalidoException("El ID de dirección de Matriz es inválido.");
            }
            
            DireccionDTO direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionSucursalMatriz);
            
            if(direccionMatriz == null){
                throw new PedidosIdDireccionInvalidoException("El ID de dirección de Matriz es inválido."); 
            }
            
            String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
            String coloniaMatriz = direccionMatriz.getColonia();
            String calleMatriz = direccionMatriz.getCalle();
            String numeroMatriz = direccionMatriz.getNumero();
            
            boolean todosProductosDisponibles = true;
                    
            for(IdProductoInventarioDTO idProductoInventario: listaIdsProductosInventario){
                
                if(!administradorProductos.validarProductoInventario(idProductoInventario)){
                    throw new PedidosIdProductoInventarioInvalidoException("El ID de producto en inventario es inválido.");
                }
                
                ProductoInventarioDTO productoInventario = administradorProductos.obtenerProductoInventario(idProductoInventario);
                
                if(productoInventario == null){
                    throw new PedidosIdProductoInventarioInvalidoException("El ID de producto en inventario es inválido.");
                }
                
                // Se obtienen los datos de la dirección de cada producto en inventario
                IdSucursalDTO idSucursalProductoInventario = productoInventario.getIdSucursal();
                
                if(!administradorSucursales.validarSucursal(idSucursalProductoInventario)){
                    throw new PedidosIdSucursalInvalidoException("El ID de la sucursal del producto en inventario es inválido.");
                }
     
                SucursalDTO sucursalProductoInventario = administradorSucursales.obtenerSucursal(idSucursalProductoInventario);
                
                if(sucursalProductoInventario == null){
                    throw new PedidosIdSucursalInvalidoException("El ID de la sucursal del producto en inventario es inválido.");
                }
                
                IdDireccionDTO idDireccionSucursalProductoInventario = sucursalProductoInventario.getIdDireccion();
                
                if(!administradorDirecciones.validarDireccion(idDireccionSucursalProductoInventario)){
                    throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la sucursal del producto en inventario es inválido.");
                }
                
                DireccionDTO direccionSucursalProductoInventario = administradorDirecciones.obtenerDireccion(idDireccionSucursalMatriz);
                
                if(direccionSucursalProductoInventario == null){
                    throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la sucursal del producto en inventario es inválido.");
                }
                
                String codigoPostalSucursalProductoInventario = direccionSucursalProductoInventario.getCodigoPostal();
                String coloniaSucursalProductoInventario = direccionSucursalProductoInventario.getColonia();
                String calleSucursalProductoInventario = direccionSucursalProductoInventario.getCalle();
                String numeroSucursalProductoInventario = direccionSucursalProductoInventario.getNumero();

                // Se crea el DTO que contiene los datos de las ubicaciones de la
                // sucursal Matriz y de la sucursal en la que se encuentra el 
                // producto en inventario para calcular el tiempo de traslado
                DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO 
                        = new DatosTiempoTrasladoUbicacionesDTO(
                                numeroSucursalProductoInventario, 
                                calleSucursalProductoInventario, 
                                coloniaSucursalProductoInventario, 
                                codigoPostalSucursalProductoInventario, 
                                numeroMatriz, 
                                calleMatriz, 
                                coloniaMatriz, 
                                codigoPostalMatriz);

                // Se obtiene el tiempo de traslado utilizando el subsistema 
                // administradorMapas, de la capa de infraestructura
                TiempoTrasladoDTO tiempoTrasladoDTO = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoUbicacionesDTO);
                
               
                float tiempoTraslado = tiempoTrasladoDTO.getTiempoTraslado();
                
                mapaProductoInventarioTiempoTrasladoMatriz.put(productoInventario, tiempoTraslado);
                
            }
            
            // Se ordenan los tiempo de traslado de los productos en inventario,
            // en orden ascendente
            List<Float> listaTiemposTrasladoProductosInventarioDisponibles = new ArrayList<>(mapaProductoInventarioTiempoTrasladoMatriz.values());
            
            Collections.sort(listaTiemposTrasladoProductosInventarioDisponibles);

            int cantidadProductosInventarioDisponibles = listaTiemposTrasladoProductosInventarioDisponibles.size();
            int cantidadProductosInventarioRequeridos = idProductoCantidadCarritoDTO.getCantidad();
            
            // Se obtiene una sublista con los tiempos de traslado de los productos 
            // en inventario requeridos
            if(cantidadProductosInventarioRequeridos <= cantidadProductosInventarioDisponibles){
                
                listaTiemposTrasladoProductosInventarioDisponibles
                    = listaTiemposTrasladoProductosInventarioDisponibles.subList(0, cantidadProductosInventarioRequeridos);
                
            } else{
 
                todosProductosDisponibles = false;
            }

            
            for(Float tiempoTrasladoProductoInventario: listaTiemposTrasladoProductosInventarioDisponibles){
                                    
                if(tiempoTrasladoProductoInventario > tiempoPreparacionEstimado){
                    tiempoPreparacionEstimado = tiempoTrasladoProductoInventario;
                    
                }
                    
            }

            // Si es necesario realizar compras de productos a proveedor, se obtiene 
            // el tiempo mayor de envío que realizan las paqueterías registradas, y se
            // determina si este tiempo es mayor al actual obtenido para considerarlo
            // o no
            if(!todosProductosDisponibles){

                IdProveedorDTO idProveedorDTO = producto.getIdProveedor();
                
                if(!administradorProveedores.validarProveedor(idProveedorDTO)){  
                    throw new PedidosIdProveedorInvalidoException("El ID de proveedor es inválido.");    
                }
                
                float tiempoEnvioMatrizMayor = 0;
                try {
                    tiempoEnvioMatrizMayor = administradorPaqueterias.obtenerTiempoEnvioMatrizEstimado(idProveedorDTO);
                } catch (PaqueteriasIdProveedorInvalidoException ex) {
                    throw new PedidosIdProveedorInvalidoException(ex.getMessage());
                } catch (PaqueteriasIdDireccionInvalidoException ex) {
                    throw new PedidosIdDireccionInvalidoException(ex.getMessage());
                }

                if(tiempoEnvioMatrizMayor > tiempoPreparacionEstimado){

                    tiempoPreparacionEstimado = tiempoEnvioMatrizMayor;

                }

            }
            
        }

        return tiempoPreparacionEstimado;
    }
    
    

    /**
     * Método que perite realizar el cálculo del costo de envío por paquetería 
     * de los productos seleccionados por el cliente, que varían según la paquetería
     * seleccionada.
     * @param informacionPedidoClienteDTO Objeto DTO que contiene la información
     * del pedido necesaria para realizar el cálculo del costo de envío.
     * @return Dato float que representa el costo de envío.
     * @throws PedidosIdClienteInvalidoException 
     * @throws PedidosIdProductoInvalidoException
     * @throws PedidosIdPaqueteriaInvalidoException
     * @throws PaqueteriasIdPaqueteriaInvalidoException
     * @throws ProductosIdProductoInvalidoException 
     */
    @Override
    public float calcularCostoEnvioProductosPaqueteria(InformacionPedidoClienteDTO informacionPedidoClienteDTO)
            throws PedidosIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdPaqueteriaInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdDireccionInvalidoException,
            PedidosIdProductoInventarioInvalidoException{

        Long idCliente = informacionPedidoClienteDTO.getIdCliente();
        
        IdClienteDTO idClienteDTO = new IdClienteDTO(idCliente);
        
        // Se valida el ID del cliente
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new PedidosIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        Long idPaqueteria = informacionPedidoClienteDTO.getIdPaqueteria();
        
        IdPaqueteriaDTO idPaqueteriaDTO = new IdPaqueteriaDTO(idPaqueteria);

        // Se valida el ID de la paquetería
        if (!administradorPaqueterias.validarPaqueteria(idPaqueteriaDTO)) {
            throw new PedidosIdPaqueteriaInvalidoException("El ID de paqueteria es inválido.");
        }
        
        PaqueteriaDTO paqueteria = administradorPaqueterias.obtenerPaqueteria(idPaqueteriaDTO);
        
        if(paqueteria == null){
            throw new PedidosIdPaqueteriaInvalidoException("El ID de paqueteria es inválido.");
        }

        // Se obtiene el mapa con la información de las cantidades solicitades por producto
        HashMap<Long, Integer> mapaProductosCantidades = informacionPedidoClienteDTO.getMapaProductosCantidades();
        
        // Se validan los IDs de los productos
        for (Long idProducto: mapaProductosCantidades.keySet()) {
            if (!administradorProductos.validarProducto(new IdProductoDTO(idProducto))) {
                throw new PedidosIdProductoInvalidoException("El ID de producto es inválido.");
            }
            
        }


        // Se obtiene la dirección de la sucursal Matriz
        SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        IdDireccionDTO idDireccionDTO = sucursalMatriz.getIdDireccion();
        
        if(!administradorDirecciones.validarDireccion(idDireccionDTO)){ 
            throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la sucursal matriz es inválido.");
        }
        
        DireccionDTO direccionMatriz = administradorDirecciones.obtenerDireccion(idDireccionDTO);
        
        if(direccionMatriz == null){
            throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la sucursal matriz es inválido.");
        }

        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        String coloniaMatriz = direccionMatriz.getColonia();
        String calleMatriz = direccionMatriz.getCalle();
        String numeroMatriz = direccionMatriz.getNumero();

        float costoTotalEnvio = 0;

        // Se usa un hashMap para almacenar las sucursales a las que es necesario
        // realizar un traslado de producto a la Matriz de la empresa junto con
        // el peso de los productos requeridos asociado
        HashMap<SucursalDTO, Double> mapaSucursalesPesosProductos = new HashMap<>();

        double sumaKgTotal = 0;

        // Se calcula el costo de envio de productos a matriz:
        for (Long idProducto: mapaProductosCantidades.keySet()) {

            // Se valida el ID del producto
            if(!administradorProductos.validarProducto(new IdProductoDTO(idProducto))){
                throw new PedidosIdProductoInvalidoException("El ID de producto es inválido.");
            }
            
            ProductoDTO producto = administradorProductos.obtenerProducto(new IdProductoDTO(idProducto));
            
            
            // Se valida el ID del proveedor
            IdProveedorDTO idProveedorDTO = producto.getIdProveedor();
            
            if(!administradorProveedores.validarProveedor(idProveedorDTO)){
                throw new PedidosIdProductoInvalidoException("El ID de proveedor es inválido.");
            }

            int cantidadProductoDisponible;
            try {
                cantidadProductoDisponible = administradorProductos.cosultarInventarioProducto(new IdProductoDTO(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {
                throw new PedidosIdProductoInvalidoException(ex.getMessage());
            }
            int cantidadProductoRequerido = mapaProductosCantidades.get(idProducto);

            HashMap<ProductoInventarioDTO, Float> mapaProductosInventarioTiempoTrasladoMatriz = new HashMap<>();

            // Se obtiene la lista de productos en inventario asociados al 
            // producto actual
            List<IdProductoInventarioDTO> listaIdsProductosInventario = producto.getIdsProductosInventario();

            for(IdProductoInventarioDTO idProductoInventario: listaIdsProductosInventario){
                
                if(!administradorProductos.validarProductoInventario(idProductoInventario)){
                    throw new PedidosIdProductoInventarioInvalidoException("El del producto en inventario es inválido.");
                }
                
                ProductoInventarioDTO productoInventario = administradorProductos.obtenerProductoInventario(idProductoInventario);

                // Se obtiene la dirección de la sucursal Matriz
                IdSucursalDTO idSucursalProductoInventario = productoInventario.getIdSucursal();
                
                if(!administradorSucursales.validarSucursal(idSucursalProductoInventario)){
                    throw new PedidosIdSucursalInvalidoException("El ID de la sucursal es inválido.");
                }
                
                SucursalDTO sucursalProductoInventario = administradorSucursales.obtenerSucursal(idSucursalProductoInventario);
                
                if(sucursalProductoInventario == null){
                    throw new PedidosIdSucursalInvalidoException("El ID de la sucursal es inválido.");
                }
                
                IdDireccionDTO idDireccionSucursalProductoInventario = sucursalProductoInventario.getIdDireccion();
                
                if(!administradorDirecciones.validarDireccion(idDireccionSucursalProductoInventario)){
                    throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la sucursal del producto en inventario es inválido.");
                }
                
                DireccionDTO direccionSucursalProductoInventario = administradorDirecciones.obtenerDireccion(idDireccionSucursalProductoInventario);
                
                if(direccionSucursalProductoInventario == null){
                    throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la sucursal del producto en inventario es inválido.");
                }

                String codigoPostalSucursalProductoInventario = direccionSucursalProductoInventario.getCodigoPostal();
                String coloniaSucursalProductoInventario = direccionSucursalProductoInventario.getColonia();
                String calleSucursalProductoInventario = direccionSucursalProductoInventario.getCalle();
                String numeroSucursalProductoInventario = direccionSucursalProductoInventario.getNumero();

                // Se crea el DTO que contiene los datos de las ubicaciones de la
                // sucursal Matriz y de la sucursal en la que se encuentra el 
                // producto en inventario para calcular el tiempo de traslado
                DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO 
                        = new DatosTiempoTrasladoUbicacionesDTO(
                                numeroSucursalProductoInventario, 
                                calleSucursalProductoInventario, 
                                coloniaSucursalProductoInventario, 
                                codigoPostalSucursalProductoInventario, 
                                numeroMatriz, 
                                calleMatriz, 
                                coloniaMatriz, 
                                codigoPostalMatriz);

                // Se obtiene el tiempo de traslado utilizando el subsistema 
                // administradorMapas, de la capa de infraestructura
                TiempoTrasladoDTO tiempoTrasladoDTO = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoUbicacionesDTO);

                float tiempoTraslado = tiempoTrasladoDTO.getTiempoTraslado();

                mapaProductosInventarioTiempoTrasladoMatriz.put(productoInventario, tiempoTraslado);

            }

            // Se ordena el HashMap con los productos en inventario y los tiempos
            // traslado a Matriz, para esto se obtiene una lista de sus entradas.
            List<Map.Entry<ProductoInventarioDTO, Float>> listaEntradas = new ArrayList<>(mapaProductosInventarioTiempoTrasladoMatriz.entrySet());

            // Se ordenada la lista por el tiempo de envio a Matriz, ascendentemente
            listaEntradas.sort(Comparator.comparing(Map.Entry::getValue));

            // La lista ordenada se guarda en un nuevo LinkedHashMap
            Map<ProductoInventarioDTO, Float> mapaProductosInventarioTiempoTrasladoMatrizOrdenado = new LinkedHashMap<>();
            listaEntradas.forEach(entrada -> 
                    mapaProductosInventarioTiempoTrasladoMatrizOrdenado.put(entrada.getKey(), entrada.getValue()));


            // Se crea un linkedHashMap para almacenar solo los productos en inventario 
            // requeridos por el pedido
            LinkedHashMap<ProductoInventarioDTO, Float> mapaProductosInventarioRequeridosTiempoTraslado = new LinkedHashMap<>();


            boolean todosProductosDisponibles = true;

            int indiceActual = 0;
            for(Map.Entry<ProductoInventarioDTO, Float> entrada: mapaProductosInventarioTiempoTrasladoMatrizOrdenado.entrySet()){

                if(indiceActual >= cantidadProductoRequerido){
                    break;
                }

                mapaProductosInventarioRequeridosTiempoTraslado.put(entrada.getKey(), entrada.getValue());

            }

            if(cantidadProductoDisponible < cantidadProductoRequerido){

                todosProductosDisponibles = false;

            }
            
            double pesoKgProducto = producto.getPesoKg();

            // Se recorre la lista de productos en inventario del producto para
            // sumar su costo de envío a sucursal
            for (Map.Entry<ProductoInventarioDTO, Float> entrada: mapaProductosInventarioRequeridosTiempoTraslado.entrySet()) {

                ProductoInventarioDTO productoInventario = entrada.getKey();

                // Se obtiene la dirección de la sucursal Matriz
                IdSucursalDTO idSucursalProductoInventario = productoInventario.getIdSucursal();
                
                if(!administradorSucursales.validarSucursal(idSucursalProductoInventario)){
                    throw new PedidosIdSucursalInvalidoException("El Id de la sucursal del producto en inventario es inválido.");
                }
                
                SucursalDTO sucursalProductoInventario = administradorSucursales.obtenerSucursal(idSucursalProductoInventario);
                
                if(sucursalProductoInventario == null){
                    throw new PedidosIdSucursalInvalidoException("El Id de la sucursal del producto en inventario es inválido.");
                }

                // Si el producto en inventario se encuentra en una sucursal que no
                // se ha verificado que tenga otros productos en inventario requeridos
                // se suma el costo de envío desde esa sucursal hacia la Matriz
                if(!mapaSucursalesPesosProductos.containsKey(sucursalProductoInventario)){
                    
                    mapaSucursalesPesosProductos.put(sucursalProductoInventario, 0D);

                }

                // Se suma el nuevo peso del producto en inventario al peso actual de su sucursal
                double nuevoPesoKgProductosSucursal 
                        = mapaSucursalesPesosProductos.get(sucursalProductoInventario) + pesoKgProducto;
                
                mapaSucursalesPesosProductos.put(sucursalProductoInventario, nuevoPesoKgProductosSucursal);
                        
                // Se suma el peso del producto en inventario
                sumaKgTotal += pesoKgProducto;

            }
            
               
            
            
            // Se obtiene el costo de envío de la compra de productos sin existencias
            if(!todosProductosDisponibles){
                
                int cantidadProductoCompraNecesaria = cantidadProductoRequerido - cantidadProductoDisponible;
                
                double pesoKgProductoComprar = pesoKgProducto * cantidadProductoCompraNecesaria;
                
                
                sumaKgTotal += pesoKgProductoComprar;
                
                Long idProveedor = idProveedorDTO.getIdProveedor();
                
                InformacionEnvioProductoProveedorMatrizDTO informacionEnvioProductoProveedorMatrizDTO
                        = new InformacionEnvioProductoProveedorMatrizDTO(
                                idPaqueteria, 
                                idProveedor,
                                pesoKgProductoComprar);

                
                float costoProductosComprar;
                try {
                   
                    costoProductosComprar = administradorPaqueterias.obtenerCostoEnvioProveedorMatriz(informacionEnvioProductoProveedorMatrizDTO);

                } catch (PaqueteriasIdPaqueteriaInvalidoException ex) {
                    throw new PedidosIdPaqueteriaInvalidoException(ex.getMessage());
                } catch (PaqueteriasIdProveedorInvalidoException ex) {
                    throw new PedidosIdProveedorInvalidoException(ex.getMessage());
                }catch (PaqueteriasIdDireccionInvalidoException ex) {
                        throw new PedidosIdDireccionInvalidoException(ex.getMessage());
                }

                costoTotalEnvio += costoProductosComprar;
                    
            }
            
        }   
        
        // Se recorren las sucursales que contienen los productos en inventario necesarios
        // para calcular el costo de envío desde cada una hasta Matriz
        for(Map.Entry<SucursalDTO, Double> entrada: mapaSucursalesPesosProductos.entrySet()){

            SucursalDTO sucursalProductosInventario = entrada.getKey();

            double pesoKgProductosSucursal = entrada.getValue();

            Long idSucursal = sucursalProductosInventario.getId();

            InformacionEnvioProductoSucursalMatrizDTO informacionEnvioProductoSucursalMatriz
                    = new InformacionEnvioProductoSucursalMatrizDTO(
                            idPaqueteria, 
                            idSucursal,
                            pesoKgProductosSucursal);


            try{

                float costoEnvioProductosSucursalMatriz;
                try {
                    costoEnvioProductosSucursalMatriz = administradorPaqueterias.obtenerCostoEnvioSucursalMatriz(informacionEnvioProductoSucursalMatriz);
                } catch (PaqueteriasIdDireccionInvalidoException ex) {
                    throw new PedidosIdDireccionInvalidoException(ex.getMessage());
                }

                costoTotalEnvio += costoEnvioProductosSucursalMatriz;


            } catch(PaqueteriasIdSucursalInvalidoException ex){
                throw new PedidosIdSucursalInvalidoException(ex.getMessage());

            } catch(PaqueteriasIdPaqueteriaInvalidoException ex){
                throw new PedidosIdPaqueteriaInvalidoException(ex.getMessage());
            }

        }
        
        if(sumaKgTotal > 0){
            
            // Se obtiene el costo de envío a la dirección del cliente  
            InformacionEnvioProductoMatrizClienteDTO informacionEnvioProductoPaqueteriaDTO =
                        new InformacionEnvioProductoMatrizClienteDTO(
                                idPaqueteria,
                                idCliente,
                                sumaKgTotal);

            float costoEnvioPedidoCliente;
            try {
                try {
                    costoEnvioPedidoCliente
                            = administradorPaqueterias.obtenerCostoEnvioMatrizCliente(informacionEnvioProductoPaqueteriaDTO);
                } catch (PaqueteriasIdDireccionInvalidoException ex) {
                    throw new PedidosIdDireccionInvalidoException(ex.getMessage());
                }
                
            } catch (PaqueteriasIdPaqueteriaInvalidoException ex) {
                throw new PedidosIdPaqueteriaInvalidoException(ex.getMessage());
            } catch (PaqueteriasIdClienteInvalidoException ex) {
                throw new PedidosIdClienteInvalidoException(ex.getMessage());
            }

            costoTotalEnvio += costoEnvioPedidoCliente;

            
        }
        
        return costoTotalEnvio;

    }

    @Override
    public PedidoDTO realizarPedido(InformacionCrearPedidoDTO informacionCrearPedidoDTO)
            throws PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException,
            ProductosIdProductoInvalidoException,
            PedidosIdProductoInventarioInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosIdDireccionInvalidoException{

        Long idCliente = informacionCrearPedidoDTO.getIdCliente();

        IdClienteDTO idClienteDTO = new IdClienteDTO(idCliente);
        
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new PedidosIdProductoInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTO cliente = administradorClientes.obtenerCliente(idClienteDTO);
        
        if(cliente == null){
            throw new PedidosIdProductoInvalidoException("El ID de cliente es inválido.");
        }

        Long idPaqueteria = informacionCrearPedidoDTO.getIdPaqueteria();
        
        IdPaqueteriaDTO idPaqueteriaDTO = new IdPaqueteriaDTO(idPaqueteria);

        PaqueteriaDTO paqueteria = administradorPaqueterias.obtenerPaqueteria(idPaqueteriaDTO);
        if (paqueteria == null) {
            throw new PedidosIdProductoInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }
        

        HashMap<Long,Integer> mapaProductosCantidadPedido = informacionCrearPedidoDTO.getMapaIdsProductosCantidad();

        for (Long idProducto: mapaProductosCantidadPedido.keySet()) {
            if (!administradorProductos.validarProducto(new IdProductoDTO(idProducto))) {
                throw new PedidosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
            }
        }

        HashMap<ProductoInventarioDTO, Float> mapaProductosInventarioTiempoTrasladoMatriz = new HashMap<>();

        for (Long idProducto: mapaProductosCantidadPedido.keySet()) {

            ProductoDTO producto = administradorProductos.obtenerProducto(new IdProductoDTO(idProducto));

            List<IdProductoInventarioDTO> listaIdsProductosInventario = producto.getIdsProductosInventario();

            for (IdProductoInventarioDTO idProductoInventario: listaIdsProductosInventario) {
                
                if(!administradorProductos.validarProductoInventario(idProductoInventario)){
                    throw new PedidosIdProductoInventarioInvalidoException("El ID de producto en inventario es inválido.");
                }
                
                ProductoInventarioDTO productoInventario = administradorProductos.obtenerProductoInventario(idProductoInventario);
                
                if(productoInventario == null){
                    throw new PedidosIdProductoInventarioInvalidoException("El ID de producto en inventario es inválido."); 
                }
                
                IdSucursalDTO idSucursalDTO = productoInventario.getIdSucursal();
                
                if(!administradorSucursales.validarSucursal(idSucursalDTO)){
                    throw new PedidosIdSucursalInvalidoException("El ID de la sucursal del producto en inventario es inválido.");
                }
                
                SucursalDTO sucursalProductoInventario = administradorSucursales.obtenerSucursal(idSucursalDTO);
                
                if(sucursalProductoInventario == null){
                    throw new PedidosIdSucursalInvalidoException("El ID de la sucursal del producto en inventario es inválido.");
                }
                
                IdDireccionDTO idDireccionSucursalProductoInventario = sucursalProductoInventario.getIdDireccion();
                
                if(!administradorDirecciones.validarDireccion(idDireccionSucursalProductoInventario)){
                    throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la sucursal del producto en inventario es inválido.");
                }
                
                DireccionDTO direccionSucursalProductoInventario = administradorDirecciones.obtenerDireccion(idDireccionSucursalProductoInventario);
                
                if(direccionSucursalProductoInventario == null){
                    throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la sucursal del producto en inventario es inválido.");
                }
                
                SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
                
                IdDireccionDTO idDireccionSucursalMatriz = sucursalMatriz.getIdDireccion();
                
                if(!administradorDirecciones.validarDireccion(idDireccionSucursalMatriz)){
                    throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la Matriz es inválido.");
                }
                
                DireccionDTO direccionSucursalMatriz = administradorDirecciones.obtenerDireccion(idDireccionSucursalMatriz);
                
                if(direccionSucursalMatriz == null){
                    throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la Matriz es inválido.");
                }
                
                Float tiempoTraslado = administradorMapas.calcularTiempoTraslado(
                        new DatosTiempoTrasladoUbicacionesDTO(
                                direccionSucursalProductoInventario.getNumero(), 
                                direccionSucursalProductoInventario.getCalle(),
                                direccionSucursalProductoInventario.getColonia(),
                                direccionSucursalProductoInventario.getCodigoPostal(),
                                direccionSucursalMatriz.getNumero(),
                                direccionSucursalMatriz.getCalle(),
                                direccionSucursalMatriz.getColonia(),
                                direccionSucursalMatriz.getCodigoPostal()))
                        .getTiempoTraslado();

                mapaProductosInventarioTiempoTrasladoMatriz.put(productoInventario, tiempoTraslado);
 
            }
        }
        
        
        List<Map.Entry<ProductoInventarioDTO, Float>> entradasProductosInvetarioTiemposTraslado 
                = new ArrayList<>(mapaProductosInventarioTiempoTrasladoMatriz.entrySet());

        // Se ordena la lista por tiempo de traslado
        Collections.sort(entradasProductosInvetarioTiemposTraslado, 
                (Map.Entry<ProductoInventarioDTO, Float> e1, Map.Entry<ProductoInventarioDTO, Float> e2) 
                        -> e1.getValue().compareTo(e2.getValue()));

        // Reconstruye el mapa ordenado (por ejemplo, usando LinkedHashMap)
        Map<ProductoInventarioDTO, Float> mapaProductosInventarioTiemposTrasladoOrdenado = new LinkedHashMap<>();
        
        for (Map.Entry<ProductoInventarioDTO, Float> entrada : entradasProductosInvetarioTiemposTraslado) {
            mapaProductosInventarioTiemposTrasladoOrdenado.put(entrada.getKey(), entrada.getValue());
        }
        
        // Lista de productos que tendrá el pedido.
        List<ProductoPedidoDTO> listaProductosPedido = new LinkedList<>();
        
        
        for(Map.Entry<Long, Integer> productoRequerido : mapaProductosCantidadPedido.entrySet()){
           
            ProductoDTO producto = administradorProductos.obtenerProducto(new IdProductoDTO(productoRequerido.getKey()));
            
            int cantidadProductoSolicitado = productoRequerido.getValue();
            
            int cantidadProductoDisponible = 0;
            
            for(ProductoInventarioDTO productoInventarioDTO: mapaProductosInventarioTiemposTrasladoOrdenado.keySet()){
                
                if(productoInventarioDTO.getIdProducto().getIdProducto().equals(productoRequerido.getKey()) && !productoInventarioDTO.getApartado()) {
                    cantidadProductoDisponible++;
                   
                    
                    try {

                        administradorProductos.apartarProductoInventarioPedido(new IdProductoInventarioDTO(productoInventarioDTO.getId()));
                        
                    } catch (ProductosIdProductoInventarioInvalidoException ex) {
                        throw new PedidosIdProductoInventarioInvalidoException(ex.getMessage());
                    }
                    
                }
                
                if(cantidadProductoDisponible >= cantidadProductoSolicitado){
                    break;
                }
                  
                    
            }
            
            listaProductosPedido.add(
                    new ProductoPedidoDatosCompletosRelacionesDTO( 
                            cantidadProductoSolicitado,
                            producto));
            

        }
        
        // Se crea el objeto PedidoDTO
        PedidoDTO pedido = new PedidoDatosCompletosRelacionesDTO(
                EstadoPedido.PENDIENTE.toString(),
                cliente,
                paqueteria,
                listaProductosPedido
        );
        
        
        Pedido.agregar(pedido);
        
        return pedido;
    }

}
