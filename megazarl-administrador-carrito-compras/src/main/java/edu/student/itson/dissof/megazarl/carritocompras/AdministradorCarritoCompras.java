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
import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCantidadCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.TiempoEstimadoPreparacionEnvioPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClientePaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionPedidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionCrearPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoAgregarCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoEliminarCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.CarritoCompras;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    public List<InformacionProductoCarritoDTO> obtenerProductos(IdClienteDTO idClienteDTO) throws CarritoComprasIdClienteInvalidoException{
        // Se valida el ID del Cliente recibido.
        if (!administradorClientes.validarCliente(idClienteDTO)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idClienteDTO.getIdCliente() + " es inválido.");
        }

        Long idCliente = idClienteDTO.getIdCliente();
        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = new LinkedList<>();

        // Se determina si el cliente cuenta con un carrito vigente.
        CarritoComprasDTO carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);
        
        // Se crean las DTOs con la información del id de los productos y su cantidad.
        if (carritoComprasCliente != null) {
            HashMap<ProductoDTO, Integer> mapaProductosCantidadesCarrito = carritoComprasCliente.getProductosCantidades();

            for (ProductoDTO producto: mapaProductosCantidadesCarrito.keySet()) {
                    Long idProducto = producto.getId();

                listaInformacionProductoCarritoDTO.add(
                        new InformacionProductoCarritoDTO(
                                idProducto,
                                mapaProductosCantidadesCarrito.get(producto)
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

        ProductoDTO productoAgregar;
        try {
            productoAgregar = administradorProductos.obtenerProducto(idProductoDTO);
        } catch (ProductosIdProductoInvalidoException ex) {
            throw new CarritoComprasIdProductoInvalidoException(ex.getMessage());
        }

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
            HashMap <ProductoDTO, Integer> productosCantidadesNuevoCarrito = new HashMap<>();

            productosCantidadesNuevoCarrito.put(productoAgregar, cantidad);
            carritoComprasCliente = new CarritoComprasDTO(clienteCarrito, null, productosCantidadesNuevoCarrito);
            CarritoCompras.agregar(carritoComprasCliente);
        } else {
            // Si ya tenía uno, se determina si ya contenía el producto del parámetro para agregarlo con cantidad de 0.
            HashMap <ProductoDTO, Integer> productosCantidadesCarrito = carritoComprasCliente.getProductosCantidades();
            if (productosCantidadesCarrito.get(productoAgregar) == null) {
                productosCantidadesCarrito.put(productoAgregar, cantidad);
            } else {
                // Si ya estaba el producto en el carrito, se actualiza su cantidad.
                int cantidadPreviaProductos = productosCantidadesCarrito.get(productoAgregar);
                productosCantidadesCarrito.put(productoAgregar, cantidadPreviaProductos + cantidad);
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

        ProductoDTO productoEliminar;
        try {
            productoEliminar = administradorProductos.obtenerProducto(idProductoDTO);
        } catch (ProductosIdProductoInvalidoException ex) {
            throw new CarritoComprasIdProductoInvalidoException(ex.getMessage());
        }

        if (productoEliminar == null) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto es inválido.");
        }

        CarritoComprasDTO carritoComprasCliente = obtenerCarritoComprasCliente(idClienteDTO);

        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID  " + idCliente +  "no cuenta con un carrito de compras.");
        }

        Integer cantidadPrevia = carritoComprasCliente.getProductosCantidades().get(productoEliminar);

        // Se valida que el carrito del Cliente tenga artículos del producto a eliminar.
        if(cantidadPrevia == null || cantidadPrevia == 0) {
            throw new CarritoComprasCarritoSinProductoException("El cliente con el ID: " + idCliente + " no tiene artículos del producto con ID: "
                    + idProducto + " en su CarritoCompras.");
        }

        int cantidad = informacionProductoEliminarCarritoDTO.getCantidad();
        
        HashMap<ProductoDTO, Integer> productosCantidadesCarrito = carritoComprasCliente.getProductosCantidades();

        // Se resta la cantidad del producto del parámetro.
        if (cantidad <= cantidadPrevia) {
            int nuevaCantidad = cantidadPrevia - cantidad;

            if(nuevaCantidad == 0){
                productosCantidadesCarrito.remove(productoEliminar);
            } else{
                productosCantidadesCarrito.put(productoEliminar, nuevaCantidad);
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
        HashMap<ProductoDTO, Integer> mapaProductosCliente = carritoComprasCliente.getProductosCantidades();

        for (HashMap.Entry<ProductoDTO, Integer> productoCantidad : mapaProductosCliente.entrySet()) {
            numeroProductos += productoCantidad.getValue();
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

        HashMap<ProductoDTO, Integer> mapaProductosCantidades = carritoComprasCliente.getProductosCantidades();

        // Se crean las DTOs TiempoEstimadoPreparacionEnvioPedidoDTO, que contienen cada par ID de producto
        // y cantidad, del carrito del Cliente.
        for (HashMap.Entry<ProductoDTO, Integer> productoCantidad : mapaProductosCantidades.entrySet()) {
            listaInformacionProductoCalculoTiempoPreparacionDTO.add(new IdProductoCantidadCarritoDTO(
                            productoCantidad.getKey().getId(),
                            productoCantidad.getValue()
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
            CarritoComprasClienteSinCarritoVigenteException
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
        HashMap<ProductoDTO, Integer> mapaProductosCantidad = carritoCompras.getProductosCantidades();

        for (HashMap.Entry<ProductoDTO, Integer> productoCantidad : mapaProductosCantidad.entrySet()) {
            double costoProducto = productoCantidad.getKey().getPrecio();
            double costoTotalProducto = costoProducto * productoCantidad.getValue();
            montoTotal += costoTotalProducto;
        }

        return montoTotal;
    }

    @Override
    public MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito(IdClienteDTO idClienteDTO) 
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException{
        
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

        HashMap<ProductoDTO, Integer> mapaProductosCantidadCliente = carritoComprasCliente.getProductosCantidades();

        // Si el carrito no tiene productos, se devuelve 0 como monto.
        if(mapaProductosCantidadCliente.isEmpty()){
            return 0F;
        }

        // Se crea un nuevo HashMap que asigna como clave el ID de los poductos del
        // carrito, en vez de los propios Productos.
        HashMap<Long, Integer> mapaIdsProductosCantidadCliente = new HashMap<>();

        for (Map.Entry<ProductoDTO, Integer> entradaMapaProductoCantidad: mapaProductosCantidadCliente.entrySet()) {
            mapaIdsProductosCantidadCliente.put(entradaMapaProductoCantidad.getKey().getId(), entradaMapaProductoCantidad.getValue());
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

        HashMap<ProductoDTO, Integer> mapaProductosCantidadCliente = carritoComprasCliente.getProductosCantidades();

        // Si el carrito no tiene productos, se devuelve 0 como monto.
        if (mapaProductosCantidadCliente.isEmpty()) {
            throw new CarritoComprasCarritoVacioException("El carrito de compras del cliente con el ID: " + idCliente + " no contiene productos.");
        }

        // Se obtiene el ID de la paquetería asociada al carrito del cliente.
        Long idPaqueteria = carritoComprasCliente.getPaqueteria().getId();

        // Se crea un nuevo HashMap que asigna como clave el ID de los poductos del
        // carrito, en vez de los propios Productos.
        HashMap<Long, Integer> mapaIdsProductosCantidadCliente = new HashMap<>();

        for (Map.Entry<ProductoDTO, Integer> entradaMapaProductoCantidad: mapaProductosCantidadCliente.entrySet()) {
            mapaIdsProductosCantidadCliente.put(entradaMapaProductoCantidad.getKey().getId(), entradaMapaProductoCantidad.getValue());
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
