package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.administradorclientes.FAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.FAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.*;
import edu.student.itson.dissof.megazarl.dto.*;
import edu.student.itson.dissof.megazarl.dto.modelos.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.modelos.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.CarritoComprasON;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoON;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class AdministradorCarritoCompras implements IAdministradorCarritoCompras {
    private final IAdministradorProductos administradorProductos;
    private final IAdministradorPedidos administradorPedidos;
    private final List<CarritoComprasON> listaCarritosCompras = new LinkedList<>();
    private final Double montoMinimoEnvioGratuito;

    public AdministradorCarritoCompras(
            Double montoMinimoEnvioGratuito,
            IAdministradorProductos administradorProductos,
            IAdministradorPedidos administradorPedidos) {
        this.montoMinimoEnvioGratuito = montoMinimoEnvioGratuito;
        this.administradorProductos = administradorProductos;
        this.administradorPedidos = administradorPedidos;
    }

    @Override
    public List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente) throws CarritoComprasIdClienteInvalidoException{
        // Se valida el ID del Cliente recibido.
        if (!FAdministradorClientes.validarIdCliente(idCliente)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = new LinkedList<>();

        // Se determina si el cliente cuenta con un carrito vigente.
        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);

        // Se crean las DTOs con la información del id de los productos y su cantidad.
        if (carritoComprasCliente != null) {
            HashMap<ProductoON, Integer> mapaProductosCantidadesCarrito = carritoComprasCliente.getProductosCantidades();

            for (ProductoON producto: mapaProductosCantidadesCarrito.keySet()) {
                Integer idProducto = producto.getId();

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
    public void agregarProducto(Integer idCliente, Integer idProducto, int cantidad)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException {
        // Se valida el ID del Cliente recibido.
        if (!FAdministradorClientes.validarIdCliente(idCliente)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        ClienteDTO clienteCarrito = FAdministradorClientes.obtenerCliente(idCliente);

        if (clienteCarrito == null) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        // Se valida el ID del Producto recibido.
        if (!administradorProductos.validarProducto(idProducto)) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        ProductoON productoAgregar = administradorProductos.obtenerProducto(idProducto);

        if (productoAgregar == null) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        // Se verifica que haya existencias de los productos.
        if (administradorProductos.cosultarInventarioProducto(idProducto) <= 0) {
            throw new CarritoComprasProductoSinInventarioException("No hay existencias del producto " + idProducto + " que se intentó agregar.");
        }

        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);

        // Se crea un nuevo carrito si el cliente no tenía uno.
        if (carritoComprasCliente == null) {
            HashMap <ProductoON, Integer> productosCantidadesNuevoCarrito = new HashMap<>();

            productosCantidadesNuevoCarrito.put(productoAgregar, cantidad);
            carritoComprasCliente = new CarritoComprasON(clienteCarrito, false, productosCantidadesNuevoCarrito);
            listaCarritosCompras.add(carritoComprasCliente);
        } else {
            // Si ya tenía uno, se determina si ya contenía el producto del parámetro para agregarlo con cantidad de 0.
            HashMap <ProductoON, Integer> productosCantidadesCarrito = carritoComprasCliente.getProductosCantidades();
            if (productosCantidadesCarrito.get(productoAgregar) == null) {
                productosCantidadesCarrito.put(productoAgregar, cantidad);
            } else {
                // Si ya estaba el producto en el carrito, se actualiza su cantidad.
                int cantidadPreviaProductos = productosCantidadesCarrito.get(productoAgregar);
                productosCantidadesCarrito.put(productoAgregar, cantidadPreviaProductos + cantidad);
            }
        }

        // Se aparta la cantidad del producto agregado.
        administradorProductos.apartarProductoInventario(idProducto, cantidad);
    }

    @Override
    public void eliminarProducto(Integer idCliente, Integer idProducto, int cantidad)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasCarritoSinProductoException,
            CarritoComprasClienteSinCarritoVigenteException,
            ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException {

        // Se valida el ID del Cliente recibido.
        if (!FAdministradorClientes.validarIdCliente(idCliente)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        if (!FAdministradorClientes.validarIdCliente(idCliente)) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        // Se valida el ID del Producto recibido.
        if (!administradorProductos.validarProducto(idProducto)) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        ProductoON productoEliminar = administradorProductos.obtenerProducto(idProducto);

        if (productoEliminar == null) {
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }

        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);

        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        Integer cantidadPrevia = carritoComprasCliente.getProductosCantidades().get(productoEliminar);

        // Se valida que el carrito del Cliente tenga artículos del producto a eliminar.
        if(cantidadPrevia == null || cantidadPrevia == 0) {
            throw new CarritoComprasCarritoSinProductoException("El cliente con el ID: " + idCliente + " no tiene artículos del producto con ID: "
                    + idProducto + " en su CarritoCompras.");
        }

        HashMap<ProductoON, Integer> productosCantidadesCarrito = carritoComprasCliente.getProductosCantidades();

        // Se resta la cantidad del producto del parámetro.
        if (cantidad <= cantidadPrevia) {
            int nuevaCantidad = cantidadPrevia - cantidad;

            if(nuevaCantidad == 0){
                productosCantidadesCarrito.remove(productoEliminar);
            } else{
                productosCantidadesCarrito.put(productoEliminar, nuevaCantidad);
            }

            // Se desaparta la cantidad de artículos previamente apartados.
            administradorProductos.desapartarProductoInventario(idProducto, cantidad);
        }
    }

    @Override
    public Integer obtenerNumeroProductos(Integer idCliente)
            throws CarritoComprasIdClienteInvalidoException{

        // Se determina si el ID del Cliente es inválido.
        if (!FAdministradorClientes.validarIdCliente(idCliente)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        int numeroProductos = 0;

        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);

        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null){
            return 0;
        }

        // Se suman las cantidades de productos en el carrito si el cliente tiene uno.
        HashMap<ProductoON, Integer> mapaProductosCliente = carritoComprasCliente.getProductosCantidades();

        for (HashMap.Entry<ProductoON, Integer> productoCantidad : mapaProductosCliente.entrySet()) {
            numeroProductos += productoCantidad.getValue();
        }
        return numeroProductos;
    }

    @Override
    public TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(Integer idCliente)
            throws CarritoComprasClienteSinCarritoVigenteException{

        TiempoEstimadoPreparacionEnvioPedidoDTO tiempoEstimadoPreparacionEnvioPedidoDTO;

        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);

        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null){
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        List<IdProductoCantidadCarritoDTO> listaInformacionProductoCalculoTiempoPreparacionDTO = new LinkedList<>();

        HashMap<ProductoON, Integer> mapaProductosCantidades = carritoComprasCliente.getProductosCantidades();

        // Se crean las DTOs TiempoEstimadoPreparacionEnvioPedidoDTO, que contienen cada par ID de producto
        // y cantidad, del carrito del Cliente.
        for (HashMap.Entry<ProductoON, Integer> productoCantidad : mapaProductosCantidades.entrySet()) {
            listaInformacionProductoCalculoTiempoPreparacionDTO.add(new IdProductoCantidadCarritoDTO(
                            productoCantidad.getKey().getId(),
                            productoCantidad.getValue()
                    )
            );
        }

        // Se obtienen las horas estimadas utilizando el método obtenerTiempoEstimadoPreparacion(), del
        // subsistema administradorPedidos.
        double horasEstimadas = administradorPedidos.obtenerTiempoEstimadoPreparacion(listaInformacionProductoCalculoTiempoPreparacionDTO);

        // Se calcula el tiempo en días.
        double diasEstimados = horasEstimadas / 24;

        // Se crea una nueva DTO que contiene el tiempo mínimo y máximo en días para la preparación del pedido, el tiempo
        // máximo se calcula sumando 5 días al tiempo mínimo.
        tiempoEstimadoPreparacionEnvioPedidoDTO
                = new TiempoEstimadoPreparacionEnvioPedidoDTO((int) Math.ceil(diasEstimados), (int) Math.ceil(diasEstimados + 5));

        return tiempoEstimadoPreparacionEnvioPedidoDTO;
    }

    private double obtenerMontoTotalCarrito(Integer idCliente) {
        double montoTotal = 0;

        CarritoComprasON carritoCompras = obtenerCarritoComprasCliente(idCliente);

        // Se suma el costo de cada producto registrado dentro del carrito si
        // el Cliente lo tiene.
        if (carritoCompras != null) {
            HashMap<ProductoON, Integer> mapaProductosCantidad = carritoCompras.getProductosCantidades();

            for (HashMap.Entry<ProductoON, Integer> productoCantidad : mapaProductosCantidad.entrySet()) {
                double costoProducto = productoCantidad.getKey().getPrecio();
                double costoTotalProducto = costoProducto * productoCantidad.getValue();
                montoTotal += costoTotalProducto;
            }
        }

        return montoTotal;
    }

    @Override
    public MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito(Integer idCliente) {
        // Se crea un objeto MontoMinimoEnvioGratuitoDTO con la información del monto
        // actual del carrito y del monto mínimo necesario.
        return new MontoMinimoEnvioGratuitoDTO(montoMinimoEnvioGratuito, obtenerMontoTotalCarrito(idCliente));
    }

    @Override
    public float obtenerCostoEnvioProductos(IdClientePaqueteriaCalculoCostoEnvioDTO idClientePaqueteriaCalculoCostoEnvioDTO)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            PedidosIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            PedidosIdPaqueteriaInvalidoException,
            PaqueteriasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            ProductosIdProductoInvalidoException {

        // Se valida el ID del Cliente.
        Integer idCliente = idClientePaqueteriaCalculoCostoEnvioDTO.getIdCliente();

        if (!FAdministradorClientes.validarIdCliente(idCliente)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        // Se valida el ID de la Paquetería.
        Integer idPaqueteria = idClientePaqueteriaCalculoCostoEnvioDTO.getIdPaqueteria();

        if (!FAdministradorPaqueterias.validarId(idPaqueteria)) {
            throw new CarritoComprasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }

        // Se verifica que el Cliente tiene un Carrito de compras.
        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);

        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        HashMap<ProductoON, Integer> mapaProductosCantidadCliente = carritoComprasCliente.getProductosCantidades();

        // Si el carrito no tiene productos, se devuelve 0 como monto.
        if(mapaProductosCantidadCliente.isEmpty()){
            return 0F;
        }

        // Se crea un nuevo HashMap que asigna como clave el ID de los poductos del
        // carrito, en vez de los propios Productos.
        HashMap<Integer, Integer> mapaIdsProductosCantidadCliente = new HashMap<>();

        for (Map.Entry<ProductoON, Integer> entradaMapaProductoCantidad: mapaProductosCantidadCliente.entrySet()) {
            mapaIdsProductosCantidadCliente.put(entradaMapaProductoCantidad.getKey().getId(), entradaMapaProductoCantidad.getValue());
        }
        InformacionCalculoCostoPedidoDTO informacionCalculoCostoPedidoDTO =
                new InformacionCalculoCostoPedidoDTO(idCliente, idPaqueteria, mapaIdsProductosCantidadCliente);


        // Se obtiene el costo de envío de productos calculado, utilizando el método
        // calcularCostoEnvioProductosPaqueteria() del subsistema administradorPedidos.
        return administradorPedidos.calcularCostoEnvioProductosPaqueteria(informacionCalculoCostoPedidoDTO);
    }

    @Override
    public void asignarPaqueteriaCarritoCliente(Integer idCliente, Integer idPaqueteria)
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException{

        // Se valida el ID del Cliente.
        if (!FAdministradorClientes.validarIdCliente(idCliente)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        PaqueteriaDTO paqueteriaAsignar = FAdministradorPaqueterias.obtenerPaqueteria(idPaqueteria);
        if (paqueteriaAsignar == null) {
            throw new CarritoComprasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }

        CarritoComprasON carritoComprasCliente  = obtenerCarritoComprasCliente(idCliente);

        // Se valida que el Cliente tenga un Carrito de compras.
        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        carritoComprasCliente.setPaqueteria(paqueteriaAsignar);
    }

    @Override
    public void crearPedidoProductosCarritoCliente(Integer idCliente)
            throws CarritoComprasIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasCarritoVacioException,
            ProductosIdProductoInvalidoException {

        // Se valida el ID del Cliente.
        if (!FAdministradorClientes.validarIdCliente(idCliente)) {
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        // Se verifica que el Cliente tiene un Carrito de compras.
        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);

        if (carritoComprasCliente == null) {
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }

        HashMap<ProductoON, Integer> mapaProductosCantidadCliente = carritoComprasCliente.getProductosCantidades();

        // Si el carrito no tiene productos, se devuelve 0 como monto.
        if (mapaProductosCantidadCliente.isEmpty()) {
            throw new CarritoComprasCarritoVacioException("El carrito de compras del cliente con el ID: " + idCliente + " no contiene productos.");
        }

        // Se obtiene el ID de la paquetería asociada al carrito del cliente.
        Integer idPaqueteria = carritoComprasCliente.getPaqueteria().id();

        // Se crea un nuevo HashMap que asigna como clave el ID de los poductos del
        // carrito, en vez de los propios Productos.
        HashMap<Integer, Integer> mapaIdsProductosCantidadCliente = new HashMap<>();

        for (Map.Entry<ProductoON, Integer> entradaMapaProductoCantidad: mapaProductosCantidadCliente.entrySet()) {
            mapaIdsProductosCantidadCliente.put(entradaMapaProductoCantidad.getKey().getId(), entradaMapaProductoCantidad.getValue());
        }

        InformacionCrearPedidoDTO informacionClientePaqueteriaProductosCantidadPedidoDTO =
                new InformacionCrearPedidoDTO(idCliente, idPaqueteria, mapaIdsProductosCantidadCliente);

        // Se realiza el pedido utilizando el método realziarPedido(), del subsistema administradorPedidos.
        administradorPedidos.realizarPedido(informacionClientePaqueteriaProductosCantidadPedidoDTO);
    }

    private CarritoComprasON obtenerCarritoComprasCliente(Integer idCliente){
        CarritoComprasON carritoComprasCliente = null;

        for (CarritoComprasON carritoCompras: listaCarritosCompras) {
            if(carritoCompras.getCliente().id().equals(idCliente)
                    && !carritoCompras.getPedidoRealizado()
                    && carritoCompras.getProductosCantidades() != null){
                carritoComprasCliente = carritoCompras;
            }
        }

        return carritoComprasCliente;
    }

    
    @Override
    public void caducarCarritoCompras(Integer idCliente) throws CarritoComprasIdClienteInvalidoException{
        
        // Se valida el ID del Cliente:
        if(!FAdministradorClientes.validarIdCliente(idCliente)){
            throw new CarritoComprasIdClienteInvalidoException("El ID de Cliente " + idCliente + " es inválido.");
        }
        
        CarritoComprasON carritoComprasClienteCaducar = obtenerCarritoComprasCliente(idCliente);
        
        if(carritoComprasClienteCaducar == null){
            throw new CarritoComprasIdClienteInvalidoException("El ID de Cliente " + idCliente + " es inválido.");
        }
        
        carritoComprasClienteCaducar.setPedidoRealizado(true);
        
    }
}
