package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.*;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCantidadCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.TiempoEstimadoPreparacionEnvioPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClientePaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionPedidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionCrearPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoAgregarCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoEliminarCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.CarritoCompras;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoCarrito;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
    public List<InformacionProductoCarritoDTO> obtenerProductos(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException{
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = new LinkedList<>();

        // Se determina si el cliente cuenta con un carrito vigente.
        CarritoComprasDTO carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);
        
        // Se crean las DTOs con la información del id de los productos y su cantidad.
        if (carritoComprasCliente != null) {
            List<ProductoCarritoDTO> listaProductosCarrito = carritoComprasCliente.getProductosCarrito();

            for (ProductoCarritoDTO productoCarrito: listaProductosCarrito) {
                    Long idProducto = productoCarrito.getProducto().getId();
                    
                if(!administradorProductos.validarProducto(new IdProductoDTO(idProducto))){
                    throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
                }
                
                Integer cantidadProductoCarrito = productoCarrito.getCantidad();

                listaInformacionProductoCarritoDTO.add(
                        new InformacionProductoCarritoDTO(
                                idProducto,
                                cantidadProductoCarrito
                        )
                );
            }
        }

        return listaInformacionProductoCarritoDTO;
    }

    @Override
    public void agregarProducto(InformacionProductoAgregarCarritoDTO informacionProductoAgregarCarrito)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException {
        
        Long idCliente = informacionProductoAgregarCarrito.getIdCliente();
        IdClienteDTO idClienteDTO = new IdClienteDTO(idCliente);
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        ClienteDTO clienteCarrito = administradorClientes.obtenerCliente(idClienteDTO);

        if (clienteCarrito == null) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        // Se valida el ID del Producto recibido.
        Long idProducto = informacionProductoAgregarCarrito.getIdProducto();
        IdProductoDTO idProductoDTO = new IdProductoDTO(idProducto);
        
        if (!administradorProductos.validarProducto(idProductoDTO)) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
        }

        ProductoDTO productoAgregar = administradorProductos.obtenerProducto(idProductoDTO);

        if (productoAgregar == null) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
        }

        try {
            // Se verifica que haya existencias de los productos.
            if (administradorProductos.cosultarInventarioProducto(idProductoDTO) <= 0) {
                throw new CarritoComprasProductoSinInventarioException("No hay existencias del producto que se intentó agregar.");
            }
        } catch (ProductosIdProductoInvalidoException ex) {
            throw new CarritoComprasIdProductoInvalidoException(ex.getMessage());
        }

        CarritoComprasDTO carritoComprasCliente = obtenerCarritoComprasCliente(new IdClienteDTO(idCliente));
        
        int cantidad = informacionProductoAgregarCarrito.getCantidad();
        
        // Se crea un nuevo carrito si el cliente no tenía uno.
        if (carritoComprasCliente == null) {
            
            carritoComprasCliente = new CarritoComprasDTO(clienteCarrito, null, new LinkedList<ProductoCarritoDTO>());
            
            CarritoCompras.agregar(carritoComprasCliente);
            
            ProductoCarritoDTO productoCarrito = new ProductoCarritoDTO(cantidad, carritoComprasCliente, productoAgregar);
            
            ProductoCarrito.agregar(productoCarrito);

                    
        } else {
            // Si ya tenía uno, se determina si ya contenía el producto del parámetro para agregarlo con cantidad de 0.
            List<ProductoCarritoDTO> listaProductosCarrito = carritoComprasCliente.getProductosCarrito();
            
            
            ProductoCarritoDTO productoCarritoEncontrado = listaProductosCarrito.stream()
                    .filter(p -> p.getProducto() != null && p.getProducto().equals(productoAgregar))
                    .findFirst()
                    .orElse(null);

            if (productoCarritoEncontrado == null) {
                
                ProductoCarritoDTO productoCarrito = new ProductoCarritoDTO(cantidad, carritoComprasCliente, productoAgregar);
            
                ProductoCarrito.agregar(productoCarrito);
                
            } else {
                
                // Si ya estaba el producto en el carrito, se actualiza su cantidad.
                int cantidadPreviaProductos = productoCarritoEncontrado.getCantidad();
            
                ActualizacionProductoCarritoDTO actualizacaionProductoCarritoDTO
                        = new ActualizacionProductoCarritoDTO(productoCarritoEncontrado.getId());
                
                actualizacaionProductoCarritoDTO.setCantidad(cantidadPreviaProductos + cantidad);
                
                ProductoCarrito.actualizar(actualizacaionProductoCarritoDTO);

            }
        }

    }
    
    

    @Override
    public void eliminarProducto(InformacionProductoEliminarCarritoDTO informacionProductoEliminarCarritoDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasCarritoSinProductoException,
            CarritoComprasClienteSinCarritoVigenteException {

        Long idCliente = informacionProductoEliminarCarritoDTO.getIdCliente();
        IdClienteDTO idClienteDTO = new IdClienteDTO(idCliente);
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        ClienteDTO clienteCarrito = administradorClientes.obtenerCliente(idClienteDTO);

        if (clienteCarrito == null) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        // Se valida el ID del Producto recibido.
        Long idProducto = informacionProductoEliminarCarritoDTO.getIdProducto();
        IdProductoDTO idProductoDTO = new IdProductoDTO(idProducto);
        
        if (!administradorProductos.validarProducto(idProductoDTO)) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
        }

        ProductoDTO productoCarritoEliminar;
        productoCarritoEliminar = administradorProductos.obtenerProducto(idProductoDTO);

        if (productoCarritoEliminar == null) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
        }

        CarritoComprasDTO carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);

        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID  " + idCliente +  "no cuenta con un carrito de compras.");
        }

        List<ProductoCarritoDTO> listaProductosCarrito = carritoComprasCliente.getProductosCarrito();
        
        ProductoCarritoDTO productoCarritoEncontrado = listaProductosCarrito.stream()
                    .filter(p -> p.getProducto() != null && p.getProducto().equals(productoCarritoEliminar))
                    .findFirst()
                    .orElse(null);
        
        if(productoCarritoEncontrado == null){
            throw new CarritoComprasCarritoSinProductoException("El cliente con el ID: " + idCliente + " no tiene artículos del producto con ID: "
                    + idProducto + " en su CarritoCompras.");
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
                
                ProductoCarrito.removerPorId(new IdProductoCarritoDTO(productoCarritoEncontrado.getId()));

            } else{
                
                
                ActualizacionProductoCarritoDTO actualizacaionProductoCarritoDTO
                        = new ActualizacionProductoCarritoDTO(productoCarritoEncontrado.getId());
                
                actualizacaionProductoCarritoDTO.setCantidad(nuevaCantidad);
                
                ProductoCarrito.actualizar(actualizacaionProductoCarritoDTO);
                
            }
            
        }
    }

    @Override
    public Integer obtenerNumeroProductos(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException{

        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        int numeroProductos = 0;

        CarritoComprasDTO carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);

        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null){
            return 0;
        }

        // Se suman las cantidades de productos en el carrito si el cliente tiene uno.
        List<ProductoCarritoDTO> listaProductosCarrito = carritoComprasCliente.getProductosCarrito();

        for (ProductoCarritoDTO productoCarritoDTO : listaProductosCarrito) {
            numeroProductos += productoCarritoDTO.getCantidad();
        }
        return numeroProductos;
    }

    @Override
    public TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProveedorInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProductoInvalidoException{
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        CarritoComprasDTO carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);

        Long idCliente = idClienteDTO.getIdCliente();
        
        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null){
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        List<IdProductoCantidadCarritoDTO> listaInformacionProductoCalculoTiempoPreparacionDTO = new LinkedList<>();

        List<ProductoCarritoDTO> listaProductoCarrito = carritoComprasCliente.getProductosCarrito();

        // Se crean las DTOs TiempoEstimadoPreparacionEnvioPedidoDTO, que contienen cada par ID de producto
        // y cantidad, del carrito del Cliente.
        for (ProductoCarritoDTO productoCarritoDTO: listaProductoCarrito) {
            listaInformacionProductoCalculoTiempoPreparacionDTO.add(new IdProductoCantidadCarritoDTO(
                            productoCarritoDTO.getProducto().getId(),
                            productoCarritoDTO.getCantidad()
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
        }

        // Se calcula el tiempo en días.
        double diasEstimados = horasEstimadas / 24;

        // Se crea una nueva DTO que contiene el tiempo mínimo y máximo en días para la preparación del pedido, el tiempo
        // máximo se calcula sumando 3 días al tiempo mínimo.
        TiempoEstimadoPreparacionEnvioPedidoDTO tiempoEstimadoPreparacionEnvioPedidoDTO
                = new TiempoEstimadoPreparacionEnvioPedidoDTO((int) Math.ceil(diasEstimados), (int) Math.ceil(diasEstimados + 3));

        return tiempoEstimadoPreparacionEnvioPedidoDTO;
    }

    private double obtenerMontoTotalCarrito(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException
            {
        
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        double montoTotal = 0;

        Long idCliente = idClienteDTO.getIdCliente();
        
        CarritoComprasDTO carritoCompras = obtenerCarritoComprasCliente(idClienteDTO);
        
        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoCompras == null){
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        // Se suma el costo de cada producto registrado dentro del carrito si
        // el Cliente lo tiene.
        List<ProductoCarritoDTO> listaProductosCarrito = carritoCompras.getProductosCarrito();

        for (ProductoCarritoDTO productoCarritoDTO : listaProductosCarrito) {
            Long idProduco = productoCarritoDTO.getId();
            
            IdProductoDTO idProductoDTO = new IdProductoDTO(idProduco);
            
            if (!administradorProductos.validarProducto(new IdProductoDTO(idProduco))) {
                throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
            }
            
            ProductoDTO producto = administradorProductos.obtenerProducto(idProductoDTO);
            
            if(producto == null){
                throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
            }              
            
            Double costoProducto = producto.getPrecio();
            Integer cantidad = productoCarritoDTO.getCantidad();
            
            double costoTotalProducto = costoProducto * cantidad;
            montoTotal += costoTotalProducto;
            
        }

        return montoTotal;
    }

    @Override
    public MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito(IdClienteDTO idClienteDTO) 
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException{
        
        // Se crea un objeto MontoMinimoEnvioGratuitoDTO con la información del monto
        // actual del carrito y del monto mínimo necesario.
        return new MontoMinimoEnvioGratuitoDTO(montoMinimoEnvioGratuito, obtenerMontoTotalCarrito(idClienteDTO));
    }

    @Override
    public float obtenerCostoEnvioProductos(IdClientePaqueteriaDTO idClientePaqueteriaCalculoCostoEnvioDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasIdSucursalInvalidoException,
            CarritoComprasIdProveedorInvalidoException{

        Long idCliente = idClientePaqueteriaCalculoCostoEnvioDTO.getIdCliente();
        IdClienteDTO idClienteDTO = new IdClienteDTO(idCliente);
        
        // Se valida el ID del Cliente.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        Long idPaqueteria = idClientePaqueteriaCalculoCostoEnvioDTO.getIdPaqueteria();
        // Se valida el ID de la Paquetería.
        IdPaqueteriaDTO idPaqueteriaDTO = new IdPaqueteriaDTO(idPaqueteria);

        if (!administradorPaqueterias.validarPaqueteria(idPaqueteriaDTO)) {
            throw new CarritoComprasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }

        // Se verifica que el Cliente tiene un Carrito de compras.
        CarritoComprasDTO carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);

        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        List<ProductoCarritoDTO> listaProductosCarrito = carritoComprasCliente.getProductosCarrito();

        // Si el carrito no tiene productos, se devuelve 0 como monto.
        if(listaProductosCarrito.isEmpty()){
            return 0F;
        }

        // Se crea un nuevo HashMap que asigna como clave el ID de los poductos del
        // carrito, en vez de los propios Productos.
        HashMap<Long, Integer> mapaIdsProductosCantidadCliente = new HashMap<>();

        for (ProductoCarritoDTO productoCarritoDTO : listaProductosCarrito) {
            
            Long idProducto = productoCarritoDTO.getProducto().getId();
            
            if(!administradorProductos.validarProducto(new IdProductoDTO(idProducto))){
                throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
            }
            
            Integer cantidad = productoCarritoDTO.getCantidad();
            
            mapaIdsProductosCantidadCliente.put(idProducto, cantidad);
        }
        
        InformacionPedidoClienteDTO informacionCalculoCostoPedidoDTO =
                new InformacionPedidoClienteDTO(idCliente, idPaqueteria, mapaIdsProductosCantidadCliente);


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
        }
    }

    @Override
    public void asignarPaqueteriaCarritoCliente(IdClientePaqueteriaDTO idClientePaqueteriaDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException{

        Long idCliente = idClientePaqueteriaDTO.getIdCliente();
        IdClienteDTO idClienteDTO = new IdClienteDTO(idCliente);
        
        // Se valida el ID del Cliente.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente es inválido.");
        }

        Long idPaqueteria = idClientePaqueteriaDTO.getIdPaqueteria();
        IdPaqueteriaDTO idPaqueteriaDTO = new IdPaqueteriaDTO(idPaqueteria);
        
        PaqueteriaDTO paqueteriaAsignar = administradorPaqueterias.obtenerPaqueteria(idPaqueteriaDTO);
        if (paqueteriaAsignar == null) {
            throw new CarritoComprasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }

        CarritoComprasDTO carritoComprasCliente  = obtenerCarritoComprasCliente(idClienteDTO);

        // Se valida que el Cliente tenga un Carrito de compras.
        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }
        
        ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO = new ActualizacionCarritoComprasDTO(carritoComprasCliente.getId());
        
        actualizacionCarritoComprasDTO.setPaqueteria(paqueteriaAsignar);
        
        CarritoCompras.actualizar(actualizacionCarritoComprasDTO);
    }

    @Override
    public void crearPedidoProductosCarritoCliente(IdClienteDTO idClienteDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasCarritoVacioException,
            CarritoComprasIdProductoInvalidoException{

        // Se valida el ID del Cliente.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: es inválido.");
        }

        Long idCliente = idClienteDTO.getIdCliente();
        
        // Se verifica que el Cliente tiene un Carrito de compras.
        CarritoComprasDTO carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);
        
        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        List<ProductoCarritoDTO> listaProductosCarritoDTO = carritoComprasCliente.getProductosCarrito();

        // Si el carrito no tiene productos, se devuelve 0 como monto.
        if (listaProductosCarritoDTO.isEmpty()) {
            throw new CarritoComprasCarritoVacioException("El carrito de compras del cliente con el ID: " + idCliente + " no contiene productos.");
        }

        // Se obtiene el ID de la paquetería asociada al carrito del cliente.
        Long idPaqueteria = carritoComprasCliente.getPaqueteria().getId();

        // Se crea un nuevo HashMap que asigna como clave el ID de los poductos del
        // carrito, en vez de los propios Productos.
        HashMap<Long, Integer> mapaIdsProductosCantidadCliente = new HashMap<>();

        for (ProductoCarritoDTO productoCarritoDTO: listaProductosCarritoDTO) {
            
            Long idProducto = productoCarritoDTO.getId();
            
            if(!administradorProductos.validarProducto(new IdProductoDTO(idProducto))){
                throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
            }
            
            Integer cantidad = productoCarritoDTO.getCantidad();
            
            mapaIdsProductosCantidadCliente.put(idProducto, cantidad);
        }

        InformacionCrearPedidoDTO informacionClientePaqueteriaProductosCantidadPedidoDTO =
                new InformacionCrearPedidoDTO(idCliente, idPaqueteria, mapaIdsProductosCantidadCliente);

        try {
            // Se realiza el pedido utilizando el método realziarPedido(), del subsistema administradorPedidos.
            administradorPedidos.realizarPedido(informacionClientePaqueteriaProductosCantidadPedidoDTO);
        } catch (PedidosIdProductoInvalidoException | ProductosIdProductoInvalidoException ex) {
            throw new CarritoComprasIdProductoInvalidoException(ex.getMessage());
        }
    }

    private CarritoComprasDTO obtenerCarritoComprasCliente(IdClienteDTO idClienteDTO){
        
        Long idCliente = idClienteDTO.getIdCliente();
        
        CarritoComprasDTO carritoComprasRecuperado = null;
        
        for(CarritoComprasDTO carritoCompras: CarritoCompras.recuperarTodos()){
            if(carritoCompras.getCliente().getId().equals(idCliente)){
                carritoComprasRecuperado = carritoCompras;
            }
        }
        
        return carritoComprasRecuperado;
       
    }


}
