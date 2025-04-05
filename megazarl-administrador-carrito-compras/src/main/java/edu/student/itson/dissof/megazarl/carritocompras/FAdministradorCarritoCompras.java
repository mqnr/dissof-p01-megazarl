package edu.student.itson.dissof.megazarl.carritocompras;

import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.IAdministradorPedidos;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.administradroproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoSinProductoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoVacioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasClienteSinCarritoVigenteException;
import edu.student.itson.dissof.megazarl.dto.IdClientePaqueteriaCalculoCostoEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.IdProductoCantidadCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionCalculoCostoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionCrearPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.TiempoEstimadoPreparacionEnvioPedidoDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.CarritoComprasON;
import edu.student.itson.dissof.megazarl.objetosnegocio.ClienteON;
import edu.student.itson.dissof.megazarl.objetosnegocio.PaqueteriaON;
import edu.student.itson.dissof.megazarl.objetosnegocio.ProductoON;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * FAdministradorCarritoCompras.java
 * 
 * Clase Fachada que representa la implementación de la interfaz {@link IAdministradroClientes}
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

    /**
     * Objeto que implementa la interfaz IAdministradorClientes, del subsitema administradorClientes.
     */
    private IAdministradorClientes administradorClientes;
    
    /**
     * Objeto que implementa la interfaz IAdministradorProductos, del subsistema administadorProductos.
     */
    private IAdministradorProductos administradorProductos;
    
    /**
     * Objeto que implementa la interfaz IAdministradorPedidos, del subsistema administradorProductos.
     */
    private IAdministradorPedidos administradorPedidos;
    
    /**
     * Es un objeto del tipo de la interfazObjeto que implementa la interfaz IAdministradorPaqueterias, del subsistema administradorPaqueterias.
     */
    private IAdministradorPaqueterias administradorPaqueterias;
    
    /**
     * Representa una lista de los carritos de compras almacenados, pertenecientes
     * a los clientes.
     */
    private List<CarritoComprasON> listaCarritosCompras = new LinkedList<>();
    
    /**
    Representa el monto mínimo para que el Cliente pueda obtener un envío gratuito.
    */
    private Double montoMinimoEnvioGratuito;
    

    /**
     * Constructor de la clase que recibe como parámetros objetos del tipo de
     * la interfaz de los subsitemas que utiliza este subsistema.
     * 
     * @param montoMinimoEnvioGratuito Representa el monto mínimo para que un Cliente
     * pueda obtener un envío gratuito.
     * @param administradorClientes Objeto del tipo IAdministradorClientes, la
     * interfaz del subsistema administradroClientes
     * @param administradorProductos
     * @param administradorPedidos
     * @param administradorPaqueterias 
     */
    public FAdministradorCarritoCompras(
            Double montoMinimoEnvioGratuito, 
            IAdministradorClientes administradorClientes, 
            IAdministradorProductos administradorProductos,
            IAdministradorPedidos administradorPedidos, 
            IAdministradorPaqueterias administradorPaqueterias) {
        
        this.montoMinimoEnvioGratuito = montoMinimoEnvioGratuito;
        this.administradorClientes = administradorClientes;
        this.administradorProductos = administradorProductos;
        this.administradorPedidos = administradorPedidos;
        this.administradorPaqueterias = administradorPaqueterias;
    }

    /**
     * Implementación del método obetenerProductos() de la interfaz {@link IAdministradorCarritoCompras}
     * Permite obtener la informacion de los productos contenidos en el carrito
     * del Cliente con el ID del parámetro.
     * @param idCliente ID del cliente a buscar los productos de su carrito.
     * @return Lista de DTOs con la información de cada producto en el carrito del cliente. La lista estará vacía
     * si el Cliente no tiene un carrito vigente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza cuando el ID de cliente recibido es inválido.
     */
    @Override
    public List<InformacionProductoCarritoDTO> obtenerProductos(Integer idCliente) throws CarritoComprasIdClienteInvalidoException{

        // Se valida el ID del Cliente recibido.
        if(!administradorClientes.validarIdCliente(idCliente)){
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

    /**
     * Implementación del método agregarProducto() de la interfaz {@link IAdministradorCarritoCompras},
     * permite añadir una cantidad de un producto al carrito activo del cliente.
     * @param idCliente Objeto Integer que representa el ID del cliente al que se agregará el producto.
     * @param idProducto Objeto Integer que representa el ID del producto a agregar.
     * @param cantidad Dato int que representa la cantidad a agregar del producto.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza cuando se comprueba que
     * ID de cliente recibido es inválido, en este subsistema.
     * @throws CarritoComprasIdProductoInvalidoException Se lanza cuando se comprueba que
     * ID de producto es inválido, en este subsistema.
     * @throws CarritoComprasProductoSinInventarioException Se lanza cuando se comprueba que
     * un producto del carrito de compras no tiene inventario.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el
     * ID de un producto es inválido, dentro del subsistema administradorProductos.
     * @throws ProductosProductoSinInventarioException Se lanza si se comprueba que el
     * un producto no tiene inventario, dentro del subsistema administradorProductos.
     */
    @Override
    public void agregarProducto(Integer idCliente, Integer idProducto, int cantidad) 
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasProductoSinInventarioException,
            ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException{
        
        // Se valida el ID del Cliente recibido.
        if(!administradorClientes.validarIdCliente(idCliente)){
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }
        
        ClienteON clienteCarrito = administradorClientes.obtenerCliente(idCliente);
        
        if(clienteCarrito == null){
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }
        
        // Se valida el ID del Producto recibido.
        if(!administradorProductos.validarProducto(idProducto)){
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        ProductoON productoAgregar = administradorProductos.obtenerProducto(idProducto);
        
        if(productoAgregar == null){
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        // Se verifica que haya existencias de los productos.
        if(administradorProductos.cosultarInventarioProducto(idProducto) <= 0){
            throw new CarritoComprasProductoSinInventarioException("No hay existencias del producto " + idProducto + " que se intentó agregar.");
        }
        
        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);

        // Se crea un nuevo carrito si el cliente no tenía uno.
        if(carritoComprasCliente == null){
            HashMap <ProductoON, Integer> productosCantidadesNuevoCarrito = new HashMap<>();
            
            productosCantidadesNuevoCarrito.put(productoAgregar, cantidad);
            carritoComprasCliente = new CarritoComprasON(clienteCarrito, false, productosCantidadesNuevoCarrito);
            listaCarritosCompras.add(carritoComprasCliente);

        } else{
            // Si ya tenía uno, se determina si ya contenía el producto del parámetro para agregarlo con cantidad de 0.
            HashMap <ProductoON, Integer> productosCantidadesCarrito = carritoComprasCliente.getProductosCantidades();
             if(productosCantidadesCarrito.get(productoAgregar) == null){
                productosCantidadesCarrito.put(productoAgregar, cantidad);
            } else{
                // Si ya estaba el producto en el carrito, se actualiza su cantidad.
                int cantidadPreviaProductos = productosCantidadesCarrito.get(productoAgregar);
                productosCantidadesCarrito.put(productoAgregar, cantidadPreviaProductos + cantidad);
            }
        }

        // Se aparta la cantidad del producto agregado.
        administradorProductos.apartarProductoInventario(idProducto, cantidad);
        
    }
    

    /**
     * Implementación del método eliminarProducto(), de la interfaz {@link IAdministradorCarritoCompras},
     * que permite elminar la cantidad del parámetro del producto con ID del parámetro
     * del carrito del Cliente con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID de un Cliente.
     * @param idProducto Objeto Integer que representa el ID de un Producto.
     * @param cantidad Dato int que representa la cantidad a elminar del producto.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza cuando se verifica que
     * el ID del Cliente recibido es inválido, dentro de este subsitema.
     * @throws CarritoComprasIdProductoInvalidoException Se lanza cuando se verifica
     * que el ID del Producto a eliminar es inválido, dentro de este subsistema.
     * @throws CarritoComprasCarritoSinProductoException Se lanza cuando se 
     * comprueba que el Cliente no tiene un Carrito de compras vigente.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza cuando 
     * el Cliente no tiene ningún Carrito de compras vigente asociado.
     * @throws ProductosIdProductoInvalidoException Se lanza si se comprueba que el
     * ID de un producto es inválido, dentro del subsistema administradorProductos.
     * @throws ProductosProductoSinInventarioException Se lanza si se comprueba que el
     * un producto no tiene inventario, dentro del subsistema administradorProductos.
     */
    @Override
    public void eliminarProducto(Integer idCliente, Integer idProducto, int cantidad) 
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdProductoInvalidoException,
            CarritoComprasCarritoSinProductoException, 
            CarritoComprasClienteSinCarritoVigenteException,
            ProductosIdProductoInvalidoException,
            ProductosProductoSinInventarioException{
         
        // Se valida el ID del Cliente recibido.
        if(!administradorClientes.validarIdCliente(idCliente)){
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }
        
        ClienteON clienteEliminarProducto = administradorClientes.obtenerCliente(idCliente);
        
        if(clienteEliminarProducto == null){
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        // Se valida el ID del Producto recibido.
        if(!administradorProductos.validarProducto(idProducto)){
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        ProductoON productoEliminar = administradorProductos.obtenerProducto(idProducto);
        
        if(productoEliminar == null){
            throw new CarritoComprasIdProductoInvalidoException("El ID de producto: " + idProducto + " es inválido.");
        }
        
        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);
            
        // Se valida que el Cliente tenga un carrito de compras.
        if (carritoComprasCliente == null){
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }
        
        Integer cantidadPrevia = carritoComprasCliente.getProductosCantidades().get(productoEliminar);
        
        // Se valida que el carrito del Cliente tenga artículos del producto a eliminar.
        if(cantidadPrevia == null || cantidadPrevia == 0){
            
            throw new CarritoComprasCarritoSinProductoException("El cliente con el ID: " + idCliente + " no tiene artículos del producto con ID: "
                    + idProducto + " en su CarritoCompras.");
        }

        HashMap <ProductoON, Integer> productosCantidadesCarrito = carritoComprasCliente.getProductosCantidades();


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

    /**
     * Implementación del método obtenerNumeroProductos(), de la interfaz {@link IAdminsitradorProductos},
     * que permite obtener el número de artículos que tiene el Cliente con el ID del parámetro.
     * @param idCliente ID del Cliente a obtener el total de productos.
     * @return Objeto Integer que representa el número de productos en el carrito del Cliente.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza cuando se verifica que
     * el ID del Cliente recibido es inválido, dentro de este subsitema.
     */
    @Override
    public Integer obtenerNumeroProductos(Integer idCliente) 
            throws CarritoComprasIdClienteInvalidoException{
        
        // Se determina si el ID del Cliente es inválido.
        if (!administradorClientes.validarIdCliente(idCliente)) {
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
    
    /**
     * Implementación del método obtenerTiempoEstimadoPreparacionProductos() de la interfaz {@link IAdminsitradorProductos},
     * que permite obtener el tiempo estimado de preparación del envío en la matriz de la empresa, es decir, el tiempo
     * aproximado que el Cliente debe esperar hasta que se realice el envío desde la matriz a la dirección de envío.
     * @param idCliente Objeto Integer que representa el ID del Cliente a obtener el tiempo estimado de preparación de
     * prouctos del carrito.
     * @return Objeto TiempoEstimadoPreparacionEnvioPedidoDTO, que contiene el tiempo mínimo y máximo de preparación
     * del pedido.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza cuando el Cliente no tiene asociado ningún carrito
     * vigente.
     */
    @Override
    public TiempoEstimadoPreparacionEnvioPedidoDTO obtenerTiempoEstimadoPreparacionProductos(Integer idCliente) 
            throws CarritoComprasClienteSinCarritoVigenteException{
        
        TiempoEstimadoPreparacionEnvioPedidoDTO tiempoEstimadoPreparacionEnvioPedidoDTO = null;
 
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

    /**
     * Implementación del método obtenerMontoTotalCarrito() que implementa la interfaz interfaz {@link IAdminsitradorProductos}
     * y permite obener el monto total de los productos en el carrito.
     * @param idCliente Objeto Integer que representa el ID del cliente a obtener el monto total
     * de productos en el carrito.
     * @return Dato double que representa el monto total de productos en el carrito del Cliente.
     */
    private double obtenerMontoTotalCarrito(Integer idCliente) {
        double montoTotal = 0;
        
        CarritoComprasON carritoCompras = obtenerCarritoComprasCliente(idCliente);
        
        // Se suma el costo de cada producto registrado dentro del carrito si 
        // el Cliente lo tiene.
        if(carritoCompras != null){
            
            HashMap<ProductoON, Integer> mapaProductosCantidad = carritoCompras.getProductosCantidades();
            
            for (HashMap.Entry<ProductoON, Integer> productoCantidad : mapaProductosCantidad.entrySet()) {
                double costoProducto = productoCantidad.getKey().getPrecio();
                double costoTotalProducto = costoProducto * productoCantidad.getValue();
                montoTotal += costoTotalProducto;
            }
        }
        

        return montoTotal;
    }
    
    /**
     * Implementación del método obtnerInformacionMontoEnvioMinimoGrautito() que 
     * la interfaz {@link IAdminsitradorProductos} y permite obtener información 
     * sobre el monto del carrito actual y el monto necesario para que el envío 
     * sea gratuito, de un determinado Cliente.
     * @param idCliente Objeto Integer que representa el ID del cliente del que 
     * se va a obtener la información del monto total.
     * @return Objeto MontoMinimoEnvioGratuitoDTO que contiene el monto mínimo necesario
     * para un envío gratuito y el monto actual del carrito del Cliente.
     */
    @Override
    public MontoMinimoEnvioGratuitoDTO obtenerInformacionMontoEnvioMinimoGratuito(Integer idCliente) {
        
        // Se crea un objeto MontoMinimoEnvioGratuitoDTO con la información del monto 
        // actual del carrito y del monto mínimo necesario.
        MontoMinimoEnvioGratuitoDTO montoMinimoEnvioGratuitoDTO
                = new MontoMinimoEnvioGratuitoDTO(montoMinimoEnvioGratuito, obtenerMontoTotalCarrito(idCliente));

        return montoMinimoEnvioGratuitoDTO;
    }

    /**
     * Implementación del método obtenerCostoEnvioProductos(), que implementa la interfaz {@link IAdminsitradorProductos},
     * y que permite obtener el costo total que tendría un pedido con los productos del carrito
     * de un Cliente, a partir de una paquetería seleccionada.
     * @param idClientePaqueteriaCalculoCostoEnvioDTO
     * @return Objeto de tipo Float que representa el costo total de envío de los productos del carrito,
     * considerando el costo de envío de la paquetería indicada.
     * 
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que
     * el ID del Carrito de compras recibido es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException Se lanza si el ID de la
     * paquetería recibido es inválido, dentro de este subsistema.
     * @throws PedidosIdClienteInvalidoException Se lanza si se comprueba que el ID
     * del Carrito de compras recibido es inválido, dentro del subsistema administradorPedidos.
     * @throws PedidosIdProductoInvalidoException Se lanza si se comprueba que el ID
     * de un producto dentro del carrito de un cliente es inválido, dentro del
     * subsistema administradorPedidos.
     * @throws PedidosIdPaqueteriaInvalidoException Se lanza si se comprueba que el ID
     * de la paquetería recibida es inválido, dentro del subsistma administradorPedidos.
     * @throws PaqueteriasIdPaqueteriaInvalidoException Se lanza si se comprueba que
     * el ID de la paqueteria recibida es inválido, dentro del susbistema administradorPaqueterias.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si el Cliente del
     * que se va a obtener el costo de envío de los productos de su carrito no tiene un Carrito
     * de compras.
     * @throws ProductosIdProductoInvalidoException Se lanza cuando se comprueba que
     * el ID de un producto es inválido, en el subsistema, administradorProdctos.
     */
    @Override
    public float obtenerCostoEnvioProductos(IdClientePaqueteriaCalculoCostoEnvioDTO idClientePaqueteriaCalculoCostoEnvioDTO)
            throws CarritoComprasIdClienteInvalidoException, 
            CarritoComprasIdPaqueteriaInvalidoException,
            PedidosIdClienteInvalidoException, 
            PedidosIdProductoInvalidoException, 
            PedidosIdPaqueteriaInvalidoException,
            PaqueteriasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            ProductosIdProductoInvalidoException{
        
        // Se valida el ID del Cliente.
        Integer idCliente = idClientePaqueteriaCalculoCostoEnvioDTO.getIdCliente();
        
        if(!administradorClientes.validarIdCliente(idCliente)){
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }
        
        // Se valida el ID de la Paquetería.
        Integer idPaqueteria = idClientePaqueteriaCalculoCostoEnvioDTO.getIdPaqueteria();
        
        if(!administradorPaqueterias.validarPaqueteria(idPaqueteria)){
            throw new CarritoComprasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }
        
        // Se verifica que el Cliente tiene un Carrito de compras.
        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);
        
        if(carritoComprasCliente == null){
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
        
        for(Map.Entry<ProductoON, Integer> entradaMapaProductoCantidad: mapaProductosCantidadCliente.entrySet()){
            mapaIdsProductosCantidadCliente.put(entradaMapaProductoCantidad.getKey().getId(), entradaMapaProductoCantidad.getValue());
        }
        InformacionCalculoCostoPedidoDTO informacionCalculoCostoPedidoDTO =
                new InformacionCalculoCostoPedidoDTO(idCliente, idPaqueteria, mapaIdsProductosCantidadCliente);

        
        // Se obtiene el costo de envío de productos calculado, utilizando el método 
        // calcularCostoEnvioProductosPaqueteria() del subsistema administradorPedidos.
        Float costoEnvioProductos = administradorPedidos.calcularCostoEnvioProductosPaqueteria(informacionCalculoCostoPedidoDTO);
        
        return costoEnvioProductos;

        
    }

    /**
     * Implementación del método asignarPaqueteriaCarritoCliente(), de la interfaz {@link IAdminsitradorProductos}
     * @param idCliente Objeto Integer que representa el ID del Cliente a cuyo carrito se le asignará la Paquetería
     * con el ID del parámetro.
     * @param idPaqueteria Objeto Integer que representa el ID de la Paquetería que se va a asociar al carrito
     * del Cliente con el ID del parémetro.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el 
     * ID del Cliente es inválido, dentro de este subsistema.
     * @throws CarritoComprasIdPaqueteriaInvalidoException Se lanza si se comprueba que el 
     * ID de la Paqueteria es inválido, dentro de este subsistema.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si el
     * Cliente no tiene un Carrito de compras vigente.
     */
    @Override
    public void asignarPaqueteriaCarritoCliente(Integer idCliente, Integer idPaqueteria) 
            throws CarritoComprasIdClienteInvalidoException,
            CarritoComprasIdPaqueteriaInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException{
        
        // Se valida el ID del Cliente.
        if(!administradorClientes.validarIdCliente(idCliente)){
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }

        // Se valida el ID de la Paquetería.
        if(!administradorPaqueterias.validarPaqueteria(idPaqueteria)){
            throw new CarritoComprasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }
        
        PaqueteriaON paqueteriaAsignar = administradorPaqueterias.obtenerPaqueteria(idPaqueteria);
        
        if(paqueteriaAsignar == null){
            throw new CarritoComprasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " es inválido.");
        }
        
        CarritoComprasON carritoComprasCliente  = obtenerCarritoComprasCliente(idCliente);
        
        // Se valida que el Ciente tenga un Carrito de compras.
        if(carritoComprasCliente == null){
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }
        
        carritoComprasCliente.setPaqueteria(paqueteriaAsignar);
        
        
        
    }
    
    /**
     * Implementación del método crearPedidoProductosCarritoCliente(), de la interfaz {@link IAdminsitradorProductos},
     * que permite crear un pedido con los productos que contiene el Carrito de compras
     * del Cliente con el ID recibido como parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente que va a hacer el pedido.
     * @throws CarritoComprasIdClienteInvalidoException Se lanza si se comprueba que el ID de cliente recibido
     * es inválido, dentro de este subsistema.
     * @throws PedidosIdProductoInvalidoException Se lanza si se comprueba que el 
     * ID de algún Producto dentro del carrito del Cliente es inválido, dentro
     * del susbsistema administradorPedidos.
     * @throws CarritoComprasClienteSinCarritoVigenteException Se lanza si se 
     * comprueba que el Cliente con el ID del parámetro no cuenta con un Carrito
     * de Compras.
     * @throws CarritoComprasCarritoVacioException Se lanza si se comprueba que el
     * Cliente con el ID del parámetro no tiene productos en su Carrito de compras.
     * @throws ProductosIdProductoInvalidoException Se lanza cuando se comprueba 
     * que el ID de producto es inválido, dentro del subsistema administradorProductos.
     */
    @Override
    public void crearPedidoProductosCarritoCliente(Integer idCliente)
            throws CarritoComprasIdClienteInvalidoException,
            PedidosIdProductoInvalidoException,
            CarritoComprasClienteSinCarritoVigenteException,
            CarritoComprasCarritoVacioException,
            ProductosIdProductoInvalidoException{

        // Se valida el ID del Cliente.
        if(!administradorClientes.validarIdCliente(idCliente)){
            throw new CarritoComprasIdClienteInvalidoException("El ID de cliente: " + idCliente + " es inválido.");
        }
        
        // Se verifica que el Cliente tiene un Carrito de compras.
        CarritoComprasON carritoComprasCliente = obtenerCarritoComprasCliente(idCliente);
        
        if(carritoComprasCliente == null){
            throw new CarritoComprasClienteSinCarritoVigenteException("El cliente con el ID: " + idCliente + " no cuenta con un carrito de compras.");
        }
        
        HashMap<ProductoON, Integer> mapaProductosCantidadCliente = carritoComprasCliente.getProductosCantidades();
        
        // Si el carrito no tiene productos, se devuelve 0 como monto.
        if(mapaProductosCantidadCliente.isEmpty()){
            throw new CarritoComprasCarritoVacioException("El carrito de compras del cliente con el ID: " + idCliente + " no contiene productos.");
        }
        
        // Se obtiene el ID de la paquetería asociada al carrito del cliente.
        Integer idPaqueteria = carritoComprasCliente.getPaqueteria().getId();

        // Se crea un nuevo HashMap que asigna como clave el ID de los poductos del
        // carrito, en vez de los propios Productos.
        HashMap<Integer, Integer> mapaIdsProductosCantidadCliente = new HashMap<>();
        
        for(Map.Entry<ProductoON, Integer> entradaMapaProductoCantidad: mapaProductosCantidadCliente.entrySet()){
            mapaIdsProductosCantidadCliente.put(entradaMapaProductoCantidad.getKey().getId(), entradaMapaProductoCantidad.getValue());
        }
            
        InformacionCrearPedidoDTO informacionClientePaqueteriaProductosCantidadPedidoDTO =
                new InformacionCrearPedidoDTO(idCliente, idPaqueteria, mapaIdsProductosCantidadCliente);

        // Se realiza el pedido utilizando el método realziarPedido(), del subsistema administradorPedidos.
        administradorPedidos.realizarPedido(informacionClientePaqueteriaProductosCantidadPedidoDTO);

        
    }
    
    
    /**
     * Método que permite obtener el Carrito de Compras del cliente con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID del cliente a buscar el carrito.
     * @return Objeto CarritoCompras si el Cliente cuenta con uno vigente, null en caso contrario.
     */
    private CarritoComprasON obtenerCarritoComprasCliente(Integer idCliente){
        
        CarritoComprasON carritoComprasCliente = null;
        
        for(CarritoComprasON carritoCompras: listaCarritosCompras){
            if(carritoCompras.getCliente().getId().equals(idCliente) 
                    && !carritoCompras.getPedidoRealizado() 
                    && carritoCompras.getProductosCantidades() != null){
                carritoComprasCliente = carritoCompras;
            }
        }
        
        return carritoComprasCliente;
    }
    
}
