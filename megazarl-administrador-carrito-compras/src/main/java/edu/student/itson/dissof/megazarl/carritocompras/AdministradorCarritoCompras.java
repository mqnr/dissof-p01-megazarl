package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInventarioInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.*;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCantidadCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.TiempoEstimadoPreparacionEnvioPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClientePaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionPedidoClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionCrearPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoAgregarCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoEliminarCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.MontoMinimoEnvioGratuitoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.CarritoCompras;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoCarrito;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.AgregarInformacionNulaNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


class AdministradorCarritoCompras implements IAdministradorCarritoCompras {
    
    private final IAdministradorProductos administradorProductos;
    private final IAdministradorPedidos administradorPedidos;
    private final IAdministradorClientes administradorClientes;
    private final IAdministradorPaqueterias administradorPaqueterias;
    private final Double montoMinimoEnvioGratuito;


    public AdministradorCarritoCompras(
            IAdministradorProductos administradorProductos,
            IAdministradorPedidos administradorPedidos,
            IAdministradorClientes administradorClientes,
            IAdministradorPaqueterias administradorPaqueterias,
            Double montoMinimoEnvioGratuito){
        
        this.administradorProductos = administradorProductos;
        this.administradorPedidos = administradorPedidos;
        this.administradorClientes = administradorClientes;
        this.administradorPaqueterias = administradorPaqueterias;
        this.montoMinimoEnvioGratuito = montoMinimoEnvioGratuito;
    }
    
    
    @Override
    public List<InformacionProductoCarritoDTONegocios> obtenerProductos(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException{
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        List<InformacionProductoCarritoDTONegocios> listaInformacionProductoCarritoDTO = new LinkedList<>();

        // Se determina si el cliente cuenta con un carrito vigente.
        CarritoComprasDTONegocios carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);
        
        // Se crean las DTOs con la información del id de los productos y su cantidad.
        if (carritoComprasCliente != null) {
            
            List<IdEntidadGenericoNegocios> listaIdsProductosCarrito = carritoComprasCliente.getIdsProductosCarrito();

            for (IdEntidadGenericoNegocios idProductoCarrito: listaIdsProductosCarrito) {
                    
                IdProductoCarritoDTONegocios idProductoCarritoDTO = new IdProductoCarritoDTONegocios(idProductoCarrito);
                
                if(!validarProductoCarrito(idProductoCarritoDTO)){
                    throw new CarritoComprasIdProductoCarritoInvalidoException("El ID del producto en carrito es inválido.");
                }
                
                ProductoCarritoDTONegocios productoCarrito = obtenerProductoCarrito(idProductoCarritoDTO);
                
                IdProductoDTONegocios idProductoDTO = new IdProductoDTONegocios(productoCarrito.getIdProducto());
                    
                if(!administradorProductos.validarProducto(idProductoDTO)){
                    throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
                }
                
                Integer cantidadProductoCarrito = productoCarrito.getCantidad();

                listaInformacionProductoCarritoDTO.add(new InformacionProductoCarritoDTONegocios(
                                idProductoDTO.getIdProducto(),
                                cantidadProductoCarrito
                        )
                );
            }
        }

        return listaInformacionProductoCarritoDTO;
    }

    @Override
    public void agregarProducto(InformacionProductoAgregarCarritoDTONegocios informacionProductoAgregarCarrito)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasAccesoDatosException{
        
        IdEntidadGenericoNegocios idCliente = informacionProductoAgregarCarrito.getIdCliente();
        IdClienteDTONegocios idClienteDTO = new IdClienteDTONegocios(idCliente);
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTONegocios clienteCarrito = administradorClientes.obtenerCliente(idClienteDTO);

        if (clienteCarrito == null) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        // Se valida el ID del Producto recibido.
        IdEntidadGenericoNegocios idProducto = informacionProductoAgregarCarrito.getIdProducto();
        IdProductoDTONegocios idProductoDTO = new IdProductoDTONegocios(idProducto);
        
        if (!administradorProductos.validarProducto(idProductoDTO)) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
        }

        ProductoDTONegocios productoAgregar = administradorProductos.obtenerProducto(idProductoDTO);

        if (productoAgregar == null) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
        }

        CarritoComprasDTONegocios carritoComprasCliente = obtenerCarritoComprasCliente(new IdClienteDTONegocios(idCliente));
        
        int cantidad = informacionProductoAgregarCarrito.getCantidad();
        
        // Se crea un nuevo carrito si el cliente no tenía uno.
        if (carritoComprasCliente == null) {
            
            carritoComprasCliente = new CarritoComprasDatosCompletosRelacionesDTONegocios(true, clienteCarrito, new LinkedList<>());
            
            try {
                CarritoCompras.agregar(carritoComprasCliente);
            } catch (AgregarInformacionNulaNegocioException | FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException ex) {
                throw new CarritoComprasAccesoDatosException(ex.getMessage());
            }
            
            ProductoCarritoDTONegocios productoCarrito = new ProductoCarritoDatosCompletosRelacionesDTONegocios(
                    cantidad, 
                    carritoComprasCliente, 
                    productoAgregar);
            
            ProductoCarrito.agregar(productoCarrito);

                    
        } else {
            // Si ya tenía uno, se determina si ya contenía el producto del parámetro para agregarlo con cantidad de 0.
            List<IdEntidadGenericoNegocios> listaIdsProductosCarrito = carritoComprasCliente.getIdsProductosCarrito();
            
            ProductoCarritoDTONegocios productoCarritoEncontrado = null;
            
            for(IdEntidadGenericoNegocios idProductoCarrito: listaIdsProductosCarrito){
                
                IdProductoCarritoDTONegocios idProductoCarritoDTO = new IdProductoCarritoDTONegocios(idProductoCarrito);
                
                if(!validarProductoCarrito(idProductoCarritoDTO)){
                    throw new CarritoComprasIdProductoCarritoInvalidoException("El ID de producto en carrito es inválido.");
                }
                
                ProductoCarritoDTONegocios productoCarrito = obtenerProductoCarrito(idProductoCarritoDTO);
                
                if(productoCarrito == null){
                    throw new CarritoComprasIdProductoCarritoInvalidoException("El ID de producto en carrito es inválido.");
                }
                
                if(productoCarrito.getIdProducto().equals(productoAgregar.getId())){
                    productoCarritoEncontrado = productoCarrito;
                    break;
                }
                
            }

            if (productoCarritoEncontrado == null) {
                
                ProductoCarritoDTONegocios productoCarrito = new ProductoCarritoDatosCompletosRelacionesDTONegocios(
                        cantidad, 
                        carritoComprasCliente, 
                        productoAgregar);
            
                ProductoCarrito.agregar(productoCarrito);
                
            } else {
                
                // Si ya estaba el producto en el carrito, se actualiza su cantidad.
                int cantidadPreviaProductos = productoCarritoEncontrado.getCantidad();
            
                ActualizacionProductoCarritoDTONegocios actualizacaionProductoCarritoDTO
                        = new ActualizacionProductoCarritoDTONegocios(productoCarritoEncontrado.getId());
                
                actualizacaionProductoCarritoDTO.setCantidad(cantidadPreviaProductos + cantidad);
                
                ProductoCarrito.actualizar(actualizacaionProductoCarritoDTO);

            }
        }

    }
    
    

    @Override
    public void eliminarProducto(InformacionProductoEliminarCarritoDTONegocios informacionProductoEliminarCarritoDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasCarritoSinProductoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoCarritoInvalidoException{

        IdEntidadGenericoNegocios idCliente = informacionProductoEliminarCarritoDTO.getIdCliente();
        IdClienteDTONegocios idClienteDTO = new IdClienteDTONegocios(idCliente);
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        ClienteDTONegocios clienteCarrito = administradorClientes.obtenerCliente(idClienteDTO);

        if (clienteCarrito == null) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        // Se valida que el Cliente tenga un carrito de compras.
        CarritoComprasDTONegocios carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);

        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID  " + idCliente +  "no cuenta con un carrito de compras.");
        }
        
        // Se valida el ID del Producto recibido.
        IdEntidadGenericoNegocios idProducto = informacionProductoEliminarCarritoDTO.getIdProducto();
        
        IdProductoDTONegocios idProductoDTO = new IdProductoDTONegocios(idProducto);
        
        if (!administradorProductos.validarProducto(idProductoDTO)) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
        }

        ProductoDTONegocios productoCarritoEliminar = administradorProductos.obtenerProducto(idProductoDTO);

        if (productoCarritoEliminar == null) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
        }

        List<IdEntidadGenericoNegocios> listaIdsProductosCarrito = carritoComprasCliente.getIdsProductosCarrito();
        
        
        ProductoCarritoDTONegocios productoCarritoEncontrado = null;
            
        for(IdEntidadGenericoNegocios idProductoCarrito: listaIdsProductosCarrito){

            IdProductoCarritoDTONegocios idProductoCarritoDTO = new IdProductoCarritoDTONegocios(idProductoCarrito);
            
            if(!validarProductoCarrito(idProductoCarritoDTO)){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID de producto en carrito es inválido.");
            }

            ProductoCarritoDTONegocios productoCarrito = obtenerProductoCarrito(idProductoCarritoDTO);

            if(productoCarrito == null){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID de producto en carrito es inválido.");
            }

            if(productoCarrito.getIdProducto().equals(productoCarritoEliminar.getId())){
                productoCarritoEncontrado = productoCarrito;
                break;
            }

        }
        
        if(productoCarritoEncontrado == null){
            throw new CarritoComprasIdProductoCarritoInvalidoException("El ID del producto en carrito es inválido.");
        }
        
        Integer cantidadPrevia = productoCarritoEncontrado.getCantidad();

        // Se valida que el carrito del Cliente tenga artículos del producto a eliminar.
        if(cantidadPrevia == null || cantidadPrevia == 0) {
            throw new CarritoComprasCarritoSinProductoException("El cliente con el ID: " + idCliente + " no tiene artículos del producto con ID: "
                    + idProducto + " en su CarritoCompras.");
        }

        int cantidad = informacionProductoEliminarCarritoDTO.getCantidad();

        // Se resta la cantidad del producto del parámetro.
        if (cantidad <= cantidadPrevia) {
            int nuevaCantidad = cantidadPrevia - cantidad;

            if(nuevaCantidad == 0){
                
                ProductoCarrito.removerPorId(new IdProductoCarritoDTONegocios(productoCarritoEncontrado.getId()));

            } else{
                
                
                ActualizacionProductoCarritoDTONegocios actualizacaionProductoCarritoDTO
                        = new ActualizacionProductoCarritoDTONegocios(productoCarritoEncontrado.getId());
                
                actualizacaionProductoCarritoDTO.setCantidad(nuevaCantidad);
                
                ProductoCarrito.actualizar(actualizacaionProductoCarritoDTO);
                
            }
            
        }
    }

    @Override
    public Integer obtenerNumeroProductos(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException{

        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        int numeroProductos = 0;

        CarritoComprasDTONegocios carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);

        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null){
            return 0;
        }

        // Se suman las cantidades de productos en el carrito si el cliente tiene uno.
        List<IdEntidadGenericoNegocios> listaIdsProductosCarrito = carritoComprasCliente.getIdsProductosCarrito();

        for (IdEntidadGenericoNegocios iProductoCarrito : listaIdsProductosCarrito) {
            
            IdProductoCarritoDTONegocios idProductoCarritoDTO = new IdProductoCarritoDTONegocios(iProductoCarrito);
            
            if(!validarProductoCarrito(idProductoCarritoDTO)){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID del producto en carrito es inválido.");
            }
            
            ProductoCarritoDTONegocios productoCarrito = obtenerProductoCarrito(idProductoCarritoDTO);
            
            numeroProductos += productoCarrito.getCantidad();
        }
        return numeroProductos;
    }

    @Override
    public TiempoEstimadoPreparacionEnvioPedidoDTONegocios obtenerTiempoEstimadoPreparacionProductos(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProveedorInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException,
            CarritoComprasIdProductoInventarioInvalidoException,
            CarritoComprasIdDireccionInvalidoException{
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        CarritoComprasDTONegocios carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);

        IdEntidadGenericoNegocios idCliente = idClienteDTO.getIdCliente();
        
        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null){
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        List<IdProductoCantidadCarritoDTONegocios> listaInformacionProductoCalculoTiempoPreparacionDTO = new LinkedList<>();

        List<IdEntidadGenericoNegocios> listaIdsProductoCarrito = carritoComprasCliente.getIdsProductosCarrito();

        // Se crean las DTOs TiempoEstimadoPreparacionEnvioPedidoDTONegocios, que contienen cada par ID de producto
        // y cantidad, del carrito del Cliente.
        for (IdEntidadGenericoNegocios idProductoCarrito: listaIdsProductoCarrito) {
            
            IdProductoCarritoDTONegocios idProductoCarritoDTO = new IdProductoCarritoDTONegocios(idProductoCarrito);
            
            if(!validarProductoCarrito(idProductoCarritoDTO)){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID del producto en carrito es inválido.");
            }
            
            ProductoCarritoDTONegocios productoCarrito = obtenerProductoCarrito(idProductoCarritoDTO);
            
            if(productoCarrito == null){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID del producto en carrito es inválido.");
            }

            listaInformacionProductoCalculoTiempoPreparacionDTO.add(new IdProductoCantidadCarritoDTONegocios(
                            productoCarrito.getIdProducto(),
                            productoCarrito.getCantidad()
                    )
            );
        }

        // Se obtienen las horas estimadas utilizando el método obtenerTiempoEstimadoPreparacion(), del
        // subsistema administradorPedidos.
        double horasEstimadas;
        try {
            horasEstimadas = administradorPedidos.obtenerTiempoEstimadoPreparacion(listaInformacionProductoCalculoTiempoPreparacionDTO);
            
        } catch (PedidosIdProveedorInvalidoException ex) {
            throw new CarritoComprasIdProveedorInvalidoException(ex.getMessage());
        } catch (PedidosIdSucursalInvalidoException ex) {
            throw new CarritoComprasIdSucursalInvalidoException(ex.getMessage());
        } catch (PedidosIdProductoInvalidoException ex) {
            throw new CarritoComprasIdProductoInvalidoException(ex.getMessage());
        } catch (PedidosIdProductoInventarioInvalidoException ex) {
            throw new CarritoComprasIdProductoInventarioInvalidoException(ex.getMessage());
        } catch (PedidosIdDireccionInvalidoException ex) {
            throw new CarritoComprasIdDireccionInvalidoException(ex.getMessage());
        }

        // Se calcula el tiempo en días.
        double diasEstimados = horasEstimadas / 24;

        // Se crea una nueva DTO que contiene el tiempo mínimo y máximo en días para la preparación del pedido, el tiempo
        // máximo se calcula sumando 3 días al tiempo mínimo.
        TiempoEstimadoPreparacionEnvioPedidoDTONegocios tiempoEstimadoPreparacionEnvioPedidoDTO
                = new TiempoEstimadoPreparacionEnvioPedidoDTONegocios((int) Math.ceil(diasEstimados), (int) Math.ceil(diasEstimados + 3));

        return tiempoEstimadoPreparacionEnvioPedidoDTO;
    }

    private double obtenerMontoTotalCarrito(IdClienteDTONegocios idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException{
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        double montoTotal = 0;

        IdEntidadGenericoNegocios idCliente = idClienteDTO.getIdCliente();
        
        CarritoComprasDTONegocios carritoCompras = obtenerCarritoComprasCliente(idClienteDTO);
        
        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoCompras == null){
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        // Se suma el costo de cada producto registrado dentro del carrito si
        // el Cliente lo tiene.
        List<IdEntidadGenericoNegocios> listaProductosCarrito = carritoCompras.getIdsProductosCarrito();

        for (IdEntidadGenericoNegocios idProductoCarrito : listaProductosCarrito) {
            
            IdProductoCarritoDTONegocios idProductoCarritoDTO = new IdProductoCarritoDTONegocios(idProductoCarrito);
            
            if(!validarProductoCarrito(idProductoCarritoDTO)){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID del producto en carrito es inválido.");
            }
            
            ProductoCarritoDTONegocios productoCarrito = obtenerProductoCarrito(idProductoCarritoDTO);
            
            if(productoCarrito == null){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID del producto en carrito es inválido.");
            }
            
            IdProductoDTONegocios idProductoDTO = new IdProductoDTONegocios(productoCarrito.getIdProducto());
            
            if (!administradorProductos.validarProducto(idProductoDTO)) {
                throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
            }
            
            ProductoDTONegocios producto = administradorProductos.obtenerProducto(idProductoDTO);
            
            if(producto == null){
                throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
            }              
            
            Double costoProducto = producto.getPrecio();
            Integer cantidad = productoCarrito.getCantidad();
            
            double costoTotalProducto = costoProducto * cantidad;
            montoTotal += costoTotalProducto;
            
        }

        return montoTotal;
    }

    @Override
    public MontoMinimoEnvioGratuitoDTONegocios obtenerInformacionMontoEnvioMinimoGratuito(IdClienteDTONegocios idClienteDTO) 
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException{
        
        // Se crea un objeto MontoMinimoEnvioGratuitoDTONegocios con la información del monto
        // actual del carrito y del monto mínimo necesario.
        return new MontoMinimoEnvioGratuitoDTONegocios(montoMinimoEnvioGratuito, obtenerMontoTotalCarrito(idClienteDTO));
    }

    @Override
    public float obtenerCostoEnvioProductos(IdClientePaqueteriaDTONegocios idClientePaqueteriaCalculoCostoEnvioDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProveedorInvalidoException,
            CarritoComprasIdDireccionInvalidoException,
            CarritoComprasIdProductoInventarioInvalidoException,
            CarritoComprasIdProductoCarritoInvalidoException{

        IdEntidadGenericoNegocios idCliente = idClientePaqueteriaCalculoCostoEnvioDTO.getIdCliente();
        IdClienteDTONegocios idClienteDTO = new IdClienteDTONegocios(idCliente);
        
        // Se valida el ID del Cliente.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        IdEntidadGenericoNegocios idPaqueteria = idClientePaqueteriaCalculoCostoEnvioDTO.getIdPaqueteria();
        // Se valida el ID de la Paquetería.
        IdPaqueteriaDTONegocios idPaqueteriaDTO = new IdPaqueteriaDTONegocios(idPaqueteria);

        if (!administradorPaqueterias.validarPaqueteria(idPaqueteriaDTO)) {
            throw new CarritoComprasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }

        // Se verifica que el Cliente tiene un Carrito de compras.
        CarritoComprasDTONegocios carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);

        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        List<IdEntidadGenericoNegocios> listaProductosCarrito = carritoComprasCliente.getIdsProductosCarrito();

        // Si el carrito no tiene productos, se devuelve 0 como monto.
        if(listaProductosCarrito.isEmpty()){
            return 0F;
        }

        // Se crea un nuevo HashMap que asigna como clave el ID de los poductos del
        // carrito, en vez de los propios Productos.
        HashMap<IdEntidadGenericoNegocios, Integer> mapaIdsProductosCantidadCliente = new HashMap<>();

        for (IdEntidadGenericoNegocios idProductoCarrito: listaProductosCarrito) {
            
            IdProductoCarritoDTONegocios idProductoCarritoDTO = new IdProductoCarritoDTONegocios(idProductoCarrito);
            
            if(!validarProductoCarrito(idProductoCarritoDTO)){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID de producto en carrito es inválido.");
            }
            
            ProductoCarritoDTONegocios productoCarrito = obtenerProductoCarrito(idProductoCarritoDTO);
            
            if(productoCarrito == null){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID de producto en carrito es inválido."); 
            }
            
            IdProductoDTONegocios idProductoDTO = new IdProductoDTONegocios(productoCarrito.getIdProducto());
            
            if(!administradorProductos.validarProducto(idProductoDTO)){
                throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
            }
              
            Integer cantidad = productoCarrito.getCantidad();
            
            mapaIdsProductosCantidadCliente.put(idProductoDTO.getIdProducto(), cantidad);
        }
        
        InformacionPedidoClienteDTONegocios informacionCalculoCostoPedidoDTO =
                new InformacionPedidoClienteDTONegocios(idCliente, idPaqueteria, mapaIdsProductosCantidadCliente);


        try {
            // Se obtiene el costo de envío de productos calculado, utilizando el método
            // calcularCostoEnvioProductosPaqueteria() del subsistema administradorPedidos.
            return administradorPedidos.calcularCostoEnvioProductosPaqueteria(informacionCalculoCostoPedidoDTO);
        } catch (PedidosIdClienteInvalidoException ex) {
            throw new CarritoComprasIdClienteInvalidoException(ex.getMessage());
        } catch (PedidosIdProductoInvalidoException ex) {
            throw new CarritoComprasIdProductoInvalidoException(ex.getMessage());
        } catch (PedidosIdPaqueteriaInvalidoException ex) {
            throw new CarritoComprasIdPaqueteriaInvalidoException(ex.getMessage());
        } catch (PedidosIdSucursalInvalidoException ex) {
            throw new CarritoComprasIdSucursalInvalidoException(ex.getMessage());
        } catch (PedidosIdProveedorInvalidoException ex) {
            throw new CarritoComprasIdProveedorInvalidoException(ex.getMessage());
        } catch (PedidosIdDireccionInvalidoException ex) {
            throw new CarritoComprasIdDireccionInvalidoException(ex.getMessage());
        } catch (PedidosIdProductoInventarioInvalidoException ex) {
            throw new CarritoComprasIdProductoInventarioInvalidoException(ex.getMessage());
        }
    }

    @Override
    public void asignarPaqueteriaCarritoCliente(IdClientePaqueteriaDTONegocios idClientePaqueteriaDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            FormatoIdInvalidoNegocioException,
            CarritoComprasAccesoDatosException{

        IdEntidadGenericoNegocios idCliente = idClientePaqueteriaDTO.getIdCliente();
        IdClienteDTONegocios idClienteDTO = new IdClienteDTONegocios(idCliente);
        
        // Se valida el ID del Cliente.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        IdEntidadGenericoNegocios idPaqueteria = idClientePaqueteriaDTO.getIdPaqueteria();
        IdPaqueteriaDTONegocios idPaqueteriaDTO = new IdPaqueteriaDTONegocios(idPaqueteria);
        
        PaqueteriaDTONegocios paqueteriaAsignar = administradorPaqueterias.obtenerPaqueteria(idPaqueteriaDTO);
        if (paqueteriaAsignar == null) {
            throw new CarritoComprasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }

        CarritoComprasDTONegocios carritoComprasCliente  = obtenerCarritoComprasCliente(idClienteDTO);

        // Se valida que el Cliente tenga un Carrito de compras.
        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }
        
        ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTO = new ActualizacionCarritoComprasDTONegocios(carritoComprasCliente.getId());
        
        actualizacionCarritoComprasDTO.setPaqueteria(paqueteriaAsignar);
        
        try {
            CarritoCompras.actualizar(actualizacionCarritoComprasDTO);
        } catch (AgregarInformacionNulaNegocioException | RegistroInexistenteNegocioException ex) {
            throw new CarritoComprasAccesoDatosException(ex.getMessage());
        }
        
    }
    
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
            CarritoComprasAccesoDatosException{

        // Se valida el ID del Cliente.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: es inválido.");
        }

        IdEntidadGenericoNegocios idCliente = idClienteDTO.getIdCliente();
        
        // Se verifica que el Cliente tiene un Carrito de compras.
        CarritoComprasDTONegocios carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);
        
        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        List<IdEntidadGenericoNegocios> listaIdsProductosCarritoDTO = carritoComprasCliente.getIdsProductosCarrito();

        // Si el carrito no tiene productos, se devuelve 0 como monto.
        if (listaIdsProductosCarritoDTO.isEmpty()) {
            throw new CarritoComprasCarritoVacioException("El carrito de compras del cliente con el ID: " + idCliente + " no contiene productos.");
        }

        // Se obtiene el ID de la paquetería asociada al carrito del cliente.
        IdPaqueteriaDTONegocios idPaqueteriaDTO = new IdPaqueteriaDTONegocios(carritoComprasCliente.getIdPaqueteria());

        // Se crea un nuevo HashMap que asigna como clave el ID de los poductos del
        // carrito, en vez de los propios Productos.
        HashMap<IdEntidadGenericoNegocios, Integer> mapaIdsProductosCantidadCliente = new HashMap<>();

        for (IdEntidadGenericoNegocios idProductoCarrito: listaIdsProductosCarritoDTO) {
            
            IdProductoCarritoDTONegocios idProductoCarritoDTO = new IdProductoCarritoDTONegocios(idProductoCarrito);
            
            if(!validarProductoCarrito(idProductoCarritoDTO)){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID de producto en inventario es inválido.");
            }
            
            ProductoCarritoDTONegocios productoCarrito = obtenerProductoCarrito(idProductoCarritoDTO);
            
            if(productoCarrito == null){
                throw new CarritoComprasIdProductoCarritoInvalidoException("El ID de producto en inventario es inválido.");
            }
            
            IdProductoDTONegocios IdProductoDTO = new IdProductoDTONegocios(productoCarrito.getIdProducto());
            
            if(!administradorProductos.validarProducto(IdProductoDTO)){
                throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
            }
            
            Integer cantidad = productoCarrito.getCantidad();
            
            mapaIdsProductosCantidadCliente.put(IdProductoDTO.getIdProducto(), cantidad);
        }

        InformacionCrearPedidoDTONegocios informacionCrearPedidoDTO =
                new InformacionCrearPedidoDTONegocios(idCliente, 
                        idPaqueteriaDTO.getIdPaqueteria(), mapaIdsProductosCantidadCliente);

        try {
            try {
                // Se realiza el pedido utilizando el método realziarPedido(), del subsistema administradorPedidos.
                administradorPedidos.realizarPedido(informacionCrearPedidoDTO);
                
                ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTO  
                        = new ActualizacionCarritoComprasDTONegocios(carritoComprasCliente.getId());
                
                actualizacionCarritoComprasDTO.setEsVigente(false);
                
                try {
                    CarritoCompras.actualizar(actualizacionCarritoComprasDTO);
                } catch (AgregarInformacionNulaNegocioException | RegistroInexistenteNegocioException | FormatoIdInvalidoNegocioException ex) {
                    throw new CarritoComprasAccesoDatosException(ex.getMessage());
                }
                
            } catch (PedidosIdProductoInventarioInvalidoException ex) {
                throw new CarritoComprasIdProductoInvalidoException(ex.getMessage());
            } catch (PedidosIdSucursalInvalidoException ex) {
                throw new CarritoComprasIdSucursalInvalidoException(ex.getMessage());
            } catch (PedidosIdDireccionInvalidoException ex) {
                throw new CarritoComprasIdDireccionInvalidoException(ex.getMessage());
            }
        } catch (PedidosIdProductoInvalidoException | ProductosIdProductoInvalidoException ex) {
            throw new CarritoComprasIdProductoInvalidoException(ex.getMessage());
        }
    }

    private CarritoComprasDTONegocios obtenerCarritoComprasCliente(IdClienteDTONegocios idClienteDTO){
        
        IdEntidadGenericoNegocios idCliente = idClienteDTO.getIdCliente();
        
        CarritoComprasDTONegocios carritoComprasRecuperado = null;
        
        for(CarritoComprasDTONegocios carritoCompras: CarritoCompras.recuperarTodos()){
            if(carritoCompras.getIdCliente().equals(idCliente) &&  carritoCompras.getEsVigente()){
                carritoComprasRecuperado = carritoCompras;
            }
        }
        
        return carritoComprasRecuperado;
       
    }
    private static final Logger LOG = Logger.getLogger(AdministradorCarritoCompras.class.getName());
    
    

    @Override
    public boolean validarProductoCarrito(IdProductoCarritoDTONegocios idProductoCarritoDTO) {
        if (idProductoCarritoDTO == null || idProductoCarritoDTO.getIdProductoCarrito() == null || !ProductoCarrito.existePorId(idProductoCarritoDTO)) {
            return false;
        }
        
        return true;
    }

    @Override
    public ProductoCarritoDTONegocios obtenerProductoCarrito(IdProductoCarritoDTONegocios idProductoCarritoDTO) {
        return ProductoCarrito.recuperarPorId(idProductoCarritoDTO);
    }


}
