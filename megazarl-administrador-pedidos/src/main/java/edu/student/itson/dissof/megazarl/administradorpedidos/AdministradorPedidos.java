package edu.student.itson.dissof.megazarl.administradorpedidos;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesPersistenciaException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoMatrizClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCantidadCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionPedidoClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionCrearPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoProveedorMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoSucursalMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.enumeradores.EstadoPedidoNegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.mapas.IAdministradorMapas;
import edu.student.itson.dissof.megazarl.objetosnegocio.Pedido;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
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
     * @param listaIdProductoCantidadCarritoDTO Objeto {@literal List<IdProductoCantidadCarritoDTONegocios>}
     * que contiene los productos y sus cantidades para calcular el tiempo de preparación.
     * @return Dato float que representa el tiempo estimado de preparación en horas.
     * @throws PedidosIdProductoInvalidoException Se lanza si el id de uno de los productos
     * recibidos es inválido, dentro del subsistema adminsitradorPedidos
     * @throws ProductosIdProductoInvalidoException Se lanza si el id de uno de los
     * productos recibidos es inválido, dentro del subsistema administradorProductos
     */
    @Override
    public float obtenerTiempoEstimadoPreparacion(List<IdProductoCantidadCarritoDTONegocios> listaIdProductoCantidadCarritoDTO) 
            throws PedidosIdProductoInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdProductoInventarioInvalidoException,
            PedidosIdDireccionInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosPersistenciaException,
            PaqueteriasPersistenciaException{
        
        float tiempoPreparacionEstimado = 0;

        for(IdProductoCantidadCarritoDTONegocios idProductoCantidadCarritoDTO: listaIdProductoCantidadCarritoDTO){
            
            IdEntidadGenericoNegocios idProducto = idProductoCantidadCarritoDTO.getIdProducto();
            
            IdProductoDTONegocios idProductoDTO = new IdProductoDTONegocios(idProducto);
            try {
                if (!administradorProductos.validarProducto(idProductoDTO)) {
                    throw new PedidosIdProductoInvalidoException("El ID de producto es inválido.");
                }
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
            
            ProductoDTONegocios producto;
            try {
                producto = administradorProductos.obtenerProducto(idProductoDTO);
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
            
            if(producto == null){
                throw new PedidosIdProductoInvalidoException("El ID de producto es inválido.");
            }
                    
            List<ProductoInventarioDTONegocios> listaProductosInventario;
            try {
                listaProductosInventario = administradorProductos.obtenerProductosInventarioIdProducto(idProductoDTO);
            } catch (ProductosIdProductoInvalidoException ex) {
                throw new PedidosIdProductoInvalidoException(ex.getMessage());
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
 
            // Se crea un hashMap para almacenar los productos en inventario del producto
            // como clave, y su tiempo de traslado a matriz como valor
            HashMap<ProductoInventarioDTONegocios, Float> mapaProductoInventarioTiempoTrasladoMatriz = new HashMap<>();
            
            // Se obtienen los datos de la dirección de la sucursal
            SucursalDTONegocios sucursalMatriz;
            try {
                sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
            } catch (SucursalesPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
            
            DireccionDTONegocios direccionMatriz = sucursalMatriz.getDireccion();

            String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
            String coloniaMatriz = direccionMatriz.getColonia();
            String calleMatriz = direccionMatriz.getCalle();
            String numeroMatriz = direccionMatriz.getNumero();
            
            boolean todosProductosDisponibles = true;
                    
            for(ProductoInventarioDTONegocios productoInventario: listaProductosInventario){
                
                
                // Se obtienen los datos de la dirección de cada producto en inventario
                IdSucursalDTONegocios idSucursalProductoInventario = new IdSucursalDTONegocios(productoInventario.getIdSucursal());
                
                try {
                    if(!administradorSucursales.validarSucursal(idSucursalProductoInventario)){
                        throw new PedidosIdSucursalInvalidoException("El ID de la sucursal del producto en inventario es inválido.");
                    }
                } catch (SucursalesPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }
     
                SucursalDTONegocios sucursalProductoInventario;
                try {
                    sucursalProductoInventario = administradorSucursales.obtenerSucursal(idSucursalProductoInventario);
                } catch (SucursalesPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }
                
                DireccionDTONegocios direccionSucursalProductoInventario = sucursalProductoInventario.getDireccion();
                
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

                IdProveedorDTONegocios idProveedorDTO = new IdProveedorDTONegocios(producto.getIdProveedor());
                
                try {
                    if(!administradorProveedores.validarProveedor(idProveedorDTO)){    
                        throw new PedidosIdProveedorInvalidoException("El ID de proveedor es inválido.");
                    }
                } catch (ProveedoresPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
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
    public float calcularCostoEnvioProductosPaqueteria(InformacionPedidoClienteDTONegocios informacionPedidoClienteDTO)
            throws PedidosIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdPaqueteriaInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosIdProveedorInvalidoException,
            PedidosIdDireccionInvalidoException,
            PedidosIdProductoInventarioInvalidoException,
            PedidosPersistenciaException,
            PedidosIdProductoInvalidoException{

        IdEntidadGenericoNegocios idCliente = informacionPedidoClienteDTO.getIdCliente();
        
        IdClienteDTONegocios idClienteDTO = new IdClienteDTONegocios(idCliente);
        
        try {
            // Se valida el ID del cliente
            if (!administradorClientes.validarCliente(idClienteDTO)) {
                throw new PedidosIdClienteInvalidoException("El ID de cliente es inválido.");
            }
        } catch (ClientesPersistenciaException ex) {
            throw new PedidosPersistenciaException(ex.getMessage());
        }

        IdEntidadGenericoNegocios idPaqueteria = informacionPedidoClienteDTO.getIdPaqueteria();
        
        IdPaqueteriaDTONegocios idPaqueteriaDTO = new IdPaqueteriaDTONegocios(idPaqueteria);

        try {
            // Se valida el ID de la paquetería
            if (!administradorPaqueterias.validarPaqueteria(idPaqueteriaDTO)) {
                throw new PedidosIdPaqueteriaInvalidoException("El ID de paqueteria es inválido.");
            }
        } catch (PaqueteriasPersistenciaException ex) {
            throw new PedidosPersistenciaException(ex.getMessage());
        }
        
        PaqueteriaDTONegocios paqueteria;
        try {
            paqueteria = administradorPaqueterias.obtenerPaqueteria(idPaqueteriaDTO);
        } catch (PaqueteriasPersistenciaException ex) {
            throw new PedidosPersistenciaException(ex.getMessage());
        }
        
        if(paqueteria == null){
            throw new PedidosIdPaqueteriaInvalidoException("El ID de paqueteria es inválido.");
        }

        // Se obtiene el mapa con la información de las cantidades solicitades por producto
        HashMap<IdEntidadGenericoNegocios, Integer> mapaProductosCantidades = informacionPedidoClienteDTO.getMapaProductosCantidades();
        
        // Se validan los IDs de los productos
        for (IdEntidadGenericoNegocios idProducto: mapaProductosCantidades.keySet()) {
            
            try {
                if (!administradorProductos.validarProducto(new IdProductoDTONegocios(idProducto))) {
                    throw new PedidosIdProductoInvalidoException("El ID de producto es inválido.");
                }
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
            
        }


        // Se obtiene la dirección de la sucursal Matriz
        SucursalDTONegocios sucursalMatriz;
        try {
            sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        } catch (SucursalesPersistenciaException ex) {
            throw new PedidosPersistenciaException(ex.getMessage());
        }
        
        DireccionDTONegocios direccionMatriz = sucursalMatriz.getDireccion();
        


        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        String coloniaMatriz = direccionMatriz.getColonia();
        String calleMatriz = direccionMatriz.getCalle();
        String numeroMatriz = direccionMatriz.getNumero();

        float costoTotalEnvio = 0;

        // Se usa un hashMap para almacenar las sucursales a las que es necesario
        // realizar un traslado de producto a la Matriz de la empresa junto con
        // el peso de los productos requeridos asociado
        HashMap<SucursalDTONegocios, Double> mapaSucursalesPesosProductos = new HashMap<>();

        double sumaKgTotal = 0;

        // Se calcula el costo de envio de productos a matriz:
        for (IdEntidadGenericoNegocios idProducto: mapaProductosCantidades.keySet()) {

            try {
                // Se valida el ID del producto
                if(!administradorProductos.validarProducto(new IdProductoDTONegocios(idProducto))){
                    throw new PedidosIdProductoInvalidoException("El ID de producto es inválido.");
                }
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
            
            ProductoDTONegocios producto;
            try {
                producto = administradorProductos.obtenerProducto(new IdProductoDTONegocios(idProducto));
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
            
            
            // Se valida el ID del proveedor
            IdProveedorDTONegocios idProveedorDTO = new IdProveedorDTONegocios(producto.getIdProveedor());
            
            try {
                if(!administradorProveedores.validarProveedor(idProveedorDTO)){
                    throw new PedidosIdProductoInvalidoException("El ID de proveedor es inválido.");
                }
            } catch (ProveedoresPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }

            int cantidadProductoDisponible;
            try {
                cantidadProductoDisponible = administradorProductos.cosultarInventarioProducto(new IdProductoDTONegocios(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {
                throw new PedidosIdProductoInvalidoException(ex.getMessage());
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
            int cantidadProductoRequerido = mapaProductosCantidades.get(idProducto);

            HashMap<ProductoInventarioDTONegocios, Float> mapaProductosInventarioTiempoTrasladoMatriz = new HashMap<>();

            // Se obtiene la lista de productos en inventario asociados al 
            // producto actual
            List<ProductoInventarioDTONegocios> listaProductosInventario;
            try {
                listaProductosInventario = administradorProductos.obtenerProductosInventarioIdProducto(new IdProductoDTONegocios(idProducto));
            } catch (ProductosIdProductoInvalidoException ex) {
                throw new PedidosIdProductoInvalidoException(ex.getMessage());
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }

            for(ProductoInventarioDTONegocios productoInventario: listaProductosInventario){

                // Se obtiene la dirección de la sucursal Matriz
                IdSucursalDTONegocios idSucursalProductoInventario = new IdSucursalDTONegocios(productoInventario.getIdSucursal());
                
                try {
                    if(!administradorSucursales.validarSucursal(idSucursalProductoInventario)){
                        throw new PedidosIdSucursalInvalidoException("El ID de la sucursal es inválido.");
                    }
                } catch (SucursalesPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }
                
                SucursalDTONegocios sucursalProductoInventario;
                try {
                    sucursalProductoInventario = administradorSucursales.obtenerSucursal(idSucursalProductoInventario);
                } catch (SucursalesPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }
                
                if(sucursalProductoInventario == null){
                    throw new PedidosIdSucursalInvalidoException("El ID de la sucursal es inválido.");
                }
                
                DireccionDTONegocios direccionSucursalProductoInventario = sucursalProductoInventario.getDireccion();
                
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
            List<Map.Entry<ProductoInventarioDTONegocios, Float>> listaEntradas = new ArrayList<>(mapaProductosInventarioTiempoTrasladoMatriz.entrySet());

            // Se ordenada la lista por el tiempo de envio a Matriz, ascendentemente
            listaEntradas.sort(Comparator.comparing(Map.Entry::getValue));

            // La lista ordenada se guarda en un nuevo LinkedHashMap
            Map<ProductoInventarioDTONegocios, Float> mapaProductosInventarioTiempoTrasladoMatrizOrdenado = new LinkedHashMap<>();
            listaEntradas.forEach(entrada -> 
                    mapaProductosInventarioTiempoTrasladoMatrizOrdenado.put(entrada.getKey(), entrada.getValue()));


            // Se crea un linkedHashMap para almacenar solo los productos en inventario 
            // requeridos por el pedido
            LinkedHashMap<ProductoInventarioDTONegocios, Float> mapaProductosInventarioRequeridosTiempoTraslado = new LinkedHashMap<>();


            boolean todosProductosDisponibles = true;

            int indiceActual = 0;
            for(Map.Entry<ProductoInventarioDTONegocios, Float> entrada: mapaProductosInventarioTiempoTrasladoMatrizOrdenado.entrySet()){

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
            for (Map.Entry<ProductoInventarioDTONegocios, Float> entrada: mapaProductosInventarioRequeridosTiempoTraslado.entrySet()) {

                ProductoInventarioDTONegocios productoInventario = entrada.getKey();

                // Se obtiene la dirección de la sucursal Matriz
                IdSucursalDTONegocios idSucursalProductoInventario = new IdSucursalDTONegocios(productoInventario.getIdSucursal());
                
                try {
                    if(!administradorSucursales.validarSucursal(idSucursalProductoInventario)){
                        throw new PedidosIdSucursalInvalidoException("El Id de la sucursal del producto en inventario es inválido.");
                    }
                } catch (SucursalesPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }
                
                SucursalDTONegocios sucursalProductoInventario;
                try {
                    sucursalProductoInventario = administradorSucursales.obtenerSucursal(idSucursalProductoInventario);
                } catch (SucursalesPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }
                
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
                
                IdEntidadGenericoNegocios idProveedor = idProveedorDTO.getIdProveedor();
                
                InformacionEnvioProductoProveedorMatrizDTONegocios informacionEnvioProductoProveedorMatrizDTO
                        = new InformacionEnvioProductoProveedorMatrizDTONegocios(
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
                } catch (PaqueteriasPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }

                costoTotalEnvio += costoProductosComprar;
                    
            }
            
        }   
        
        // Se recorren las sucursales que contienen los productos en inventario necesarios
        // para calcular el costo de envío desde cada una hasta Matriz
        for(Map.Entry<SucursalDTONegocios, Double> entrada: mapaSucursalesPesosProductos.entrySet()){

            SucursalDTONegocios sucursalProductosInventario = entrada.getKey();

            double pesoKgProductosSucursal = entrada.getValue();

            IdEntidadGenericoNegocios idSucursal = sucursalProductosInventario.getId();

            InformacionEnvioProductoSucursalMatrizDTONegocios informacionEnvioProductoSucursalMatriz
                    = new InformacionEnvioProductoSucursalMatrizDTONegocios(
                            idPaqueteria, 
                            idSucursal,
                            pesoKgProductosSucursal);


            try{

                float costoEnvioProductosSucursalMatriz;
                try {
                    costoEnvioProductosSucursalMatriz
                            = administradorPaqueterias.obtenerCostoEnvioSucursalMatriz(informacionEnvioProductoSucursalMatriz);
                } catch (PaqueteriasIdDireccionInvalidoException ex) {
                    throw new PedidosIdDireccionInvalidoException(ex.getMessage());
                } catch (PaqueteriasPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
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
            InformacionEnvioProductoMatrizClienteDTONegocios informacionEnvioProductoPaqueteriaDTO =
                        new InformacionEnvioProductoMatrizClienteDTONegocios(
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
                } catch (PaqueteriasPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
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
    public PedidoDTONegocios realizarPedido(InformacionCrearPedidoDTONegocios informacionCrearPedidoDTO)
            throws PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdProductoInvalidoException,
            ProductosIdProductoInvalidoException,
            PedidosIdProductoInventarioInvalidoException,
            PedidosIdSucursalInvalidoException,
            PedidosIdDireccionInvalidoException,
            PedidosPersistenciaException{

        IdEntidadGenericoNegocios idCliente = informacionCrearPedidoDTO.getIdCliente();

        IdClienteDTONegocios idClienteDTO = new IdClienteDTONegocios(idCliente);
        
        try {
            if (!administradorClientes.validarCliente(idClienteDTO)) {
                throw new PedidosIdProductoInvalidoException("El ID de cliente es inválido.");
            }
        } catch (ClientesPersistenciaException ex) {
            throw new PedidosPersistenciaException(ex.getMessage());
        }
        
        ClienteDTONegocios cliente;
        try {
            cliente = administradorClientes.obtenerCliente(idClienteDTO);
        } catch (ClientesPersistenciaException ex) {
            throw new PedidosPersistenciaException(ex.getMessage());
        }
        
        if(cliente == null){
            throw new PedidosIdProductoInvalidoException("El ID de cliente es inválido.");
        }

        IdEntidadGenericoNegocios idPaqueteria = informacionCrearPedidoDTO.getIdPaqueteria();
        
        IdPaqueteriaDTONegocios idPaqueteriaDTO = new IdPaqueteriaDTONegocios(idPaqueteria);

        PaqueteriaDTONegocios paqueteria;
        try {
            paqueteria = administradorPaqueterias.obtenerPaqueteria(idPaqueteriaDTO);
        } catch (PaqueteriasPersistenciaException ex) {
            throw new PedidosPersistenciaException(ex.getMessage());
        }
        
        if (paqueteria == null) {
            throw new PedidosIdProductoInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }
        

        HashMap<IdEntidadGenericoNegocios,Integer> mapaProductosCantidadPedido = informacionCrearPedidoDTO.getMapaIdsProductosCantidad();

        for (IdEntidadGenericoNegocios idProducto: mapaProductosCantidadPedido.keySet()) {
            try {
                if (!administradorProductos.validarProducto(new IdProductoDTONegocios(idProducto))) {
                    throw new PedidosIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
                }
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
        }

        HashMap<ProductoInventarioDTONegocios, Float> mapaProductosInventarioTiempoTrasladoMatriz = new HashMap<>();

        for (IdEntidadGenericoNegocios idProducto: mapaProductosCantidadPedido.keySet()) {

            try {
                ProductoDTONegocios producto = administradorProductos.obtenerProducto(new IdProductoDTONegocios(idProducto));
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }

            List<ProductoInventarioDTONegocios> listaProductosInventario;
            try {
                listaProductosInventario
                        = administradorProductos.obtenerProductosInventarioIdProducto(new IdProductoDTONegocios(idProducto));
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }

            for (ProductoInventarioDTONegocios productoInventario: listaProductosInventario) {
                
                IdSucursalDTONegocios idSucursalDTO = new IdSucursalDTONegocios(productoInventario.getIdSucursal());
                
                try {
                    if(!administradorSucursales.validarSucursal(idSucursalDTO)){
                        throw new PedidosIdSucursalInvalidoException("El ID de la sucursal del producto en inventario es inválido.");
                    }
                } catch (SucursalesPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }
                
                SucursalDTONegocios sucursalProductoInventario;
                try {
                    sucursalProductoInventario = administradorSucursales.obtenerSucursal(idSucursalDTO);
                } catch (SucursalesPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }
                
                if(sucursalProductoInventario == null){
                    throw new PedidosIdSucursalInvalidoException("El ID de la sucursal del producto en inventario es inválido.");
                }
                
                DireccionDTONegocios direccionSucursalProductoInventario = sucursalProductoInventario.getDireccion();
                
                if(direccionSucursalProductoInventario == null){
                    throw new PedidosIdDireccionInvalidoException("El ID de la dirección de la sucursal del producto en inventario es inválido.");
                }
                
                SucursalDTONegocios sucursalMatriz;
                try {
                    sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
                } catch (SucursalesPersistenciaException ex) {
                    throw new PedidosPersistenciaException(ex.getMessage());
                }
                
                DireccionDTONegocios direccionSucursalMatriz = sucursalMatriz.getDireccion();
                
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
        
        
        List<Map.Entry<ProductoInventarioDTONegocios, Float>> entradasProductosInvetarioTiemposTraslado 
                = new ArrayList<>(mapaProductosInventarioTiempoTrasladoMatriz.entrySet());

        // Se ordena la lista por tiempo de traslado
        Collections.sort(entradasProductosInvetarioTiemposTraslado, 
                (Map.Entry<ProductoInventarioDTONegocios, Float> e1, Map.Entry<ProductoInventarioDTONegocios, Float> e2) 
                        -> e1.getValue().compareTo(e2.getValue()));

        // Reconstruye el mapa ordenado (por ejemplo, usando LinkedHashMap)
        Map<ProductoInventarioDTONegocios, Float> mapaProductosInventarioTiemposTrasladoOrdenado = new LinkedHashMap<>();
        
        for (Map.Entry<ProductoInventarioDTONegocios, Float> entrada : entradasProductosInvetarioTiemposTraslado) {
            mapaProductosInventarioTiemposTrasladoOrdenado.put(entrada.getKey(), entrada.getValue());
        }
        
        // Lista de productos que tendrá el pedido.
        List<ProductoPedidoDTONegocios> listaProductosPedido = new LinkedList<>();
        
        
        for(Map.Entry<IdEntidadGenericoNegocios, Integer> productoRequerido : mapaProductosCantidadPedido.entrySet()){
           
            ProductoDTONegocios producto;
            try {
                producto = administradorProductos.obtenerProducto(new IdProductoDTONegocios(productoRequerido.getKey()));
            } catch (ProductosPersistenciaException ex) {
                throw new PedidosPersistenciaException(ex.getMessage());
            }
            
            int cantidadProductoSolicitado = productoRequerido.getValue();
            
            int cantidadProductoDisponible = 0;
            
            for(ProductoInventarioDTONegocios productoInventarioDTO: mapaProductosInventarioTiemposTrasladoOrdenado.keySet()){
                
                if(productoInventarioDTO.getIdProducto().equals(productoRequerido.getKey()) && !productoInventarioDTO.getApartado()) {
                    cantidadProductoDisponible++;
                   
                    
                    try {

                        administradorProductos.apartarProductoInventarioPedido(new IdProductoInventarioDTONegocios(productoInventarioDTO.getId()));
                        
                    } catch (ProductosIdProductoInventarioInvalidoException ex) {
                        throw new PedidosIdProductoInventarioInvalidoException(ex.getMessage());
                    } catch (ProductosPersistenciaException ex) {
                        throw new PedidosPersistenciaException(ex.getMessage());
                    }
                    
                }
                
                if(cantidadProductoDisponible >= cantidadProductoSolicitado){
                    break;
                }
  
            }
            
            listaProductosPedido.add(new ProductoPedidoDTONegocios(
                            producto.getId(),
                            cantidadProductoSolicitado));

        }
        
        // Se crea el objeto PedidoDTONegocios
        PedidoDTONegocios pedido = new PedidoDTONegocios(
                EstadoPedidoNegocios.PENDIENTE,
                cliente.getId(),
                paqueteria.getId(),
                listaProductosPedido
        );
        
        
        try {
            Pedido.agregar(pedido);
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                ParametroNuloNegocioException | ValorParametroInvalidoNegocioException ex) {
            
            throw new PedidosPersistenciaException(ex.getMessage());
        }
        
        return pedido;
    }

}
