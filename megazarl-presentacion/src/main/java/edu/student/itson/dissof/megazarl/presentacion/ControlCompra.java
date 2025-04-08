package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.FAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpedidos.excepciones.PedidosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.carritocompras.FAdministradorCarritoCompras;
import edu.student.itson.dissof.megazarl.carritocompras.IAdministradorCarritoCompras;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoSinProductoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoVacioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasClienteSinCarritoVigenteException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.direcciones.FAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.InformacionDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionNoDerivadaCPDireccionEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.IdClientePaqueteriaCalculoCostoEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoVentaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.NombresApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoInicioDTO;
import edu.student.itson.dissof.megazarl.dto.TiempoEstimadoPreparacionEnvioPedidoDTO;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ICarrito;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IDireccion;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IInformacionProducto;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IProductosVenta;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ISeleccionPaqueteria;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ControlCompra.java
 * 
 * Clase de control de presentación del caso de uso comprar.
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
public class ControlCompra {

    private IVista productosVenta;
    private IVista informacionProducto;
    private IVista seleccionPaqueteria;
    private IVista carrito;
    private IMensaje mensaje;
    private IVista direccion;
    private IAdministradorProductos administradorProductos;
    private IAdministradorCarritoCompras administradorCarritoCompras;
    private IAdministradorPaqueterias administradorPaqueterias;
    private IAdministradorSucursales adminisrtadorSucursales;
    private IAdministradorProveedores administradorProveedores;

    private Color COLOR_MENSAJE_EXITOSO = new Color(204, 255, 190);
    private Color COLOR_MENSAJE_ERROR = new Color(255, 195, 195);
    private Color COLOR_MENSAJE_ADVERTENCIA = new Color(255, 253, 222);
    
    private static final Logger LOG = Logger.getLogger(ControlCompra.class.getName());

    public ControlCompra(
        IAdministradorProductos administradorProductos,
        IAdministradorCarritoCompras administradorCarritoCompras,
        IAdministradorPaqueterias administradorPaqueterias,
        IAdministradorSucursales administradorSucursales,
        IAdministradorProveedores administradorProveedores) {

        this.administradorProductos = administradorProductos;
        this.administradorPaqueterias = administradorPaqueterias;
        this.administradorCarritoCompras = administradorCarritoCompras;
        this.administradorPaqueterias = administradorPaqueterias;
        this.adminisrtadorSucursales = administradorSucursales;
        this.administradorProveedores = administradorProveedores;
    }

    public void setVistas(
            IVista productosVenta,
            IVista informacionProducto,
            IVista seleccionPaqueteria,
            IVista carrito,
            IMensaje mensaje,
            IVista direccion) {

        this.productosVenta = productosVenta;
        this.informacionProducto = informacionProducto;
        this.seleccionPaqueteria = seleccionPaqueteria;
        this.carrito = carrito;
        this.mensaje = mensaje;
        this.direccion = direccion;
        
    }
    
    /**
     * Método que permite iniciar la compra de productos, mostrando al cliente
     * los productos en venta.
     */
    public void iniciarCompra() {
        // Se obtiene la lista de mapas que contienen la información de cada producto a mostrar.
        List<Map<String, Object>> listaInformacionProductosInicio = obtenerProductosVenta();
        // Se actualiza el encabezado de la pantalla a mostrar.
        productosVenta.actualizarDatosEncabezado();
        
        if(!listaInformacionProductosInicio.isEmpty()){    
            // Se establecen los productos obtenidos a la ventana para que los muestre, si los hay.
            ((IProductosVenta)productosVenta).setProductosTodos(listaInformacionProductosInicio);
 
        } else{
            // Si no hay existencias de productos, se ejecuta el metodo de la ventana de productos
            // en venta que muestra un mensaje indicándolo.
            ((IProductosVenta)productosVenta).mostrarAvisoSinProductosDisponibles();
        }
        // La ventana se hace visible.
        productosVenta.hacerVisible(true);
        
    }

    /**
     * Método que permite mostrar la ventana de productos en venta, permitiéndo
     * cerrar la ventana anterior.
     * @param vistaActual Objeto que implementa la interfaz {@link IVista},
     * es la ventana anterior.
     */
    public void mostrarProductosVenta(IVista vistaActual) {
        // Se cierra la ventana anterior.
        vistaActual.cerrar();
        // Se obtiene la lista de mapas que contienen la información de cada producto a mostrar.
        List<Map<String, Object>> listaInformacionProductosInicio = obtenerProductosVenta();
        // Se actualiza el encabezado de la pantalla a mostrar.
        productosVenta.actualizarDatosEncabezado();
        
        if(!listaInformacionProductosInicio.isEmpty()){    
            // Se establecen los productos obtenidos a la ventana para que los muestre, si los hay.
            ((IProductosVenta)productosVenta).setProductosTodos(listaInformacionProductosInicio);
 
        } else{
            // Si no hay existencias de productos, se ejecuta el metodo de la ventana de productos
            // en venta que muestra un mensaje indicándolo.
            ((IProductosVenta)productosVenta).mostrarAvisoSinProductosDisponibles();
        }
        // La ventana se hace visible.
        productosVenta.hacerVisible(true);
    }

    /**
     * Método que permite mostrar la información de los productos buscados por un cliente.
     * @param nombreProducto Objeto String que representa el nombre del producto buscado o
     * similar.
     */
    public void mostrarProductosBusqueda(String nombreProducto) {
        if(nombreProducto.isBlank()){
            iniciarCompra();
        } else{
            // Se obtiene la lista de mapas que contienen la información de los productos a mostrar.
            List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusqueda(nombreProducto);
            if (listaInformacionProductosBusqueda.isEmpty()) {
                mostrarMensaje("Busqueda inválida", COLOR_MENSAJE_ADVERTENCIA);
            } else {
                productosVenta.actualizarDatosEncabezado();
                ((IProductosVenta)productosVenta).setProductosBusqueda(listaInformacionProductosBusqueda);
                productosVenta.hacerVisible(true);
            }
        }
        
    }

    /**
     * Método que permite obtener la información de productos a partir de su nombre.
     * @param nombreProducto Objeto String que representa el nombre de producto a buscar.
     * @return {@literal List<Map<String, Object>>} lsita de mapas con la información de cada producto
     * que coincidió con la búsqueda.
     */
    public List<Map<String, Object>> obtenerProductosBusqueda(String nombreProducto) {
        List<InformacionProductoInicioDTO> listaProductoInicioDTO = administradorProductos.obtenerProductosBusqueda(nombreProducto);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (InformacionProductoInicioDTO productoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();
            mapaInformacionProductoInicio.put("Id", productoInicioDTO.getId());
            mapaInformacionProductoInicio.put("Nombre", productoInicioDTO.getNombre());
            mapaInformacionProductoInicio.put("Variedad", productoInicioDTO.getVariedad());
            mapaInformacionProductoInicio.put("Precio", productoInicioDTO.getPrecio());
            mapaInformacionProductoInicio.put("MilesSemillas", productoInicioDTO.getMilesSemillas());
            mapaInformacionProductoInicio.put("DireccionImagenProducto", productoInicioDTO.getDireccionImagenProducto());
            
            String nombreProveedor = obtenerNombreProveedor(productoInicioDTO.getIdProveedor());
            productoInicioDTO.setNombreProveedor(nombreProveedor);
            
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(productoInicioDTO.getIdProveedor()); 
            productoInicioDTO.setDireccionImagenProvedor(productoInicioDTO.getDireccionImagenProvedor());
            
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);
            mapaInformacionProductoInicio.put("NombreProveedor", nombreProveedor);

            listaInformacionProductosBusqueda.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosBusqueda;
    }

    /**
     * Método que permite obtener la información de productos a partir de su nombre.
     * @param nombreProducto Objeto String que representa el nombre de producto a buscar.
     * @param nombreVariedad Objeto String que representa la variedad del producto a buscar.
     */
    public void mostrarProductosBusqueda(String nombreProducto, String nombreVariedad) {
        List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusqueda(nombreProducto, nombreVariedad);
        productosVenta.actualizarDatosEncabezado();
        ((IProductosVenta)productosVenta).setProductosBusqueda(listaInformacionProductosBusqueda);
        productosVenta.hacerVisible(true);
    }

    public List<Map<String, Object>> obtenerProductosBusqueda(String nombreProducto, String nombreVariedad) {
        List<InformacionProductoInicioDTO> listaProductoInicioDTO = administradorProductos.obtenerProductosBusqueda(nombreProducto, nombreVariedad);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (InformacionProductoInicioDTO productoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();
            mapaInformacionProductoInicio.put("Id", productoInicioDTO.getId());
            mapaInformacionProductoInicio.put("Nombre", productoInicioDTO.getNombre());
            mapaInformacionProductoInicio.put("Variedad", productoInicioDTO.getVariedad());
            mapaInformacionProductoInicio.put("Precio", productoInicioDTO.getPrecio());
            mapaInformacionProductoInicio.put("MilesSemillas", productoInicioDTO.getMilesSemillas());
            mapaInformacionProductoInicio.put("DireccionImagenProducto", productoInicioDTO.getDireccionImagenProducto());
            
            String nombreProveedor = obtenerNombreProveedor(productoInicioDTO.getIdProveedor());
            productoInicioDTO.setNombreProveedor(nombreProveedor);
                   
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(productoInicioDTO.getIdProveedor()); 
            productoInicioDTO.setDireccionImagenProvedor(productoInicioDTO.getDireccionImagenProvedor());
            
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);
            mapaInformacionProductoInicio.put("NombreProveedor", nombreProveedor);

            listaInformacionProductosBusqueda.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosBusqueda;
    }

    /**
     * Método que permite mostrar la ventana que muestra los productos en venta, conteniendo
     * sólo las coincidencias del producto buscado por el Cliente a paritr de su nombre, variedad
     * y nombre de proveedor.
     * @param nombreProducto Objeto String que representa el nombre del producto a buscar.
     * @param nombreVariedad Objeto String que representa el nombre de la variedad del producto a buscar.
     * @param nombreProveeedor Objeto String que representa el nombre del proveedor del producto a buscar.
     */
    public void mostrarProductosBusqueda(String nombreProducto, String nombreVariedad, String nombreProveeedor) {
        List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusqueda(nombreProducto, nombreVariedad, nombreProveeedor);
        productosVenta.actualizarDatosEncabezado();
        ((IProductosVenta)productosVenta).setProductosBusqueda(listaInformacionProductosBusqueda);
        ((IVista)productosVenta).hacerVisible(true);
    }

    /**
     * Método que permite obtener la información de los productos en venta buscados
     * por el Cliente, a partir de su nombre, variedad y nombre de proveedor.
     * @param nombreProducto Objeto String que representa el nombre del producto a buscar.
     * @param nombreVariedad Objeto String que representa el nombre de la variedad del producto a buscar.
     * @param nombreProveeedor Objeto String que representa el nombre del proveedor del producto a buscar.
     * @return Objeto {@literal List<Map<String, Object>>} que contiene la información de los productos
     * que coinciden con la búsqueda, representando a cada producto con un mapa.
     */
    public List<Map<String, Object>> obtenerProductosBusqueda(String nombreProducto, String nombreVariedad, String nombreProveeedor) {
        List<InformacionProductoInicioDTO> listaProductoInicioDTO = administradorProductos.obtenerProductosBusqueda(nombreProducto, nombreVariedad, nombreProveeedor);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (InformacionProductoInicioDTO productoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();
            mapaInformacionProductoInicio.put("Id", productoInicioDTO.getId());
            mapaInformacionProductoInicio.put("Nombre", productoInicioDTO.getNombre());
            mapaInformacionProductoInicio.put("Variedad", productoInicioDTO.getVariedad());
            mapaInformacionProductoInicio.put("Precio", productoInicioDTO.getPrecio());
            mapaInformacionProductoInicio.put("MilesSemillas", productoInicioDTO.getMilesSemillas());
            mapaInformacionProductoInicio.put("DireccionImagenProducto", productoInicioDTO.getDireccionImagenProducto());
            
            String nombreProveedor = obtenerNombreProveedor(productoInicioDTO.getIdProveedor());
            productoInicioDTO.setNombreProveedor(nombreProveedor);
            
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(productoInicioDTO.getIdProveedor()); 
            productoInicioDTO.setDireccionImagenProvedor(productoInicioDTO.getDireccionImagenProvedor());
            
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);
            mapaInformacionProductoInicio.put("NombreProveedor", nombreProveedor);

            listaInformacionProductosBusqueda.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosBusqueda;
    }

    /**
     * Método que permite obtener la información de todos los productos en venta y con existencias.
     * @return Objeto {@literal List<Map<String, Object>>}, que contiene la información de los productos
     * en venta, representando a cada producto con un mapa.
     */
    public List<Map<String, Object>> obtenerProductosVenta() {
        List<InformacionProductoInicioDTO> listaProductoInicioDTO = administradorProductos.obtenerProductosVenta();

        List<Map<String, Object>> listaInformacionProductosInicio = new LinkedList<>();

        for (InformacionProductoInicioDTO productoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();
            mapaInformacionProductoInicio.put("Id", productoInicioDTO.getId());
            mapaInformacionProductoInicio.put("Nombre", productoInicioDTO.getNombre());
            mapaInformacionProductoInicio.put("Variedad", productoInicioDTO.getVariedad());
            mapaInformacionProductoInicio.put("Precio", productoInicioDTO.getPrecio());
            mapaInformacionProductoInicio.put("MilesSemillas", productoInicioDTO.getMilesSemillas());
            mapaInformacionProductoInicio.put("DireccionImagenProducto", productoInicioDTO.getDireccionImagenProducto());
            
            String nombreProveedor = obtenerNombreProveedor(productoInicioDTO.getIdProveedor());
            productoInicioDTO.setNombreProveedor(nombreProveedor);
                
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(productoInicioDTO.getIdProveedor()); 
            productoInicioDTO.setDireccionImagenProvedor(productoInicioDTO.getDireccionImagenProvedor());
            
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);
            mapaInformacionProductoInicio.put("NombreProveedor", nombreProveedor);

            listaInformacionProductosInicio.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosInicio;
    }

    /**
     * Método que permite mostrar la información de un product cuando este es seleccionado
     * desde la pantalla inicial, donde se muestran todos los productos en venta, o bien
     * cuando se selecciona una vez que se ha realizado una búsqueda.
     * @param idProducto Objeto Integer que representa el ID del producto del que
     * se mostrará su información detallada.
     * @param vistaActual Objeto que implementa la interfaz IVista, representa
     * la ventana actual que será cerrada.
     */
    public void mostrarInformacionProducto(Integer idProducto, IVista vistaActual) {
        Map<String, Object> mapaInformacionProducto = this.obtenerInformacionProducto(idProducto);
        informacionProducto.actualizarDatosEncabezado();
        ((IInformacionProducto)informacionProducto).setProducto(mapaInformacionProducto);
        vistaActual.cerrar();
        informacionProducto.hacerVisible(true);
    }

    /**
     * Método que permite obtener la información de un prducto que se mostrará en
     * la ventana que muestra la información detallada de un producto, una vez que se
     * seleccciona desde la ventana principal que incluye todos los productos en venta,
     * o cuando se ha realizado una búsqueda.
     * @param idProducto Objeto Integer que representa el ID del producto del que se 
     * obtendrá su información detallada.
     * @return Objeto {@literal Map<String, Object>}, representa la información detallada
     * del producto a mostrar.
     */
    public Map<String, Object> obtenerInformacionProducto(Integer idProducto) {
        try {
            InformacionProductoVentaDTO informacionProductoDTO = administradorProductos.obtenerInformacionProductoVenta(idProducto);
            
            Map<String, Object> mapaInformacionProducto = new HashMap<>();
            
            if (informacionProductoDTO != null) {
                mapaInformacionProducto.put("Id", informacionProductoDTO.getId());
                mapaInformacionProducto.put("Nombre", informacionProductoDTO.getNombre());
                mapaInformacionProducto.put("Variedad", informacionProductoDTO.getVariedad());
                mapaInformacionProducto.put("Descripcion", informacionProductoDTO.getDescripcion());
                mapaInformacionProducto.put("Precio", informacionProductoDTO.getPrecio());
                mapaInformacionProducto.put("MilesSemillas", informacionProductoDTO.getMilesSemillas());
                mapaInformacionProducto.put("DireccionImagenProducto", informacionProductoDTO.getDireccionImagenProducto());
                
                String nombreProveedor = obtenerNombreProveedor(informacionProductoDTO.getIdProveedor());
                informacionProductoDTO.setNombreProveedor(nombreProveedor);
                
                String direccionImagenProveedor = obtenerDireccionImagenProveedor(informacionProductoDTO.getIdProveedor());
                informacionProductoDTO.setDireccionImagenProveedor(direccionImagenProveedor);
                
                mapaInformacionProducto.put("NombreProveedor", informacionProductoDTO.getNombreProveedor());
                mapaInformacionProducto.put("DireccionImagenProveedor", informacionProductoDTO.getDireccionImagenProveedor());
            }
            
            return mapaInformacionProducto;
            
        } catch (ProductosIdProductoInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al mostrar la información del producto", COLOR_MENSAJE_ERROR);
            
            return null;
        }
    }

    
    /**
     * Método que permite mostrar el carrito de compras actual del Cliente con el ID
     * del parámetro, incluyendo los productos agregados, el importe total y por producto, el monto mínimo para envío gratis
     * y el tiempo estimado de preparación del pedido. Permitiéndo cerrar una ventana
     * previamente abierta.
     * @param idCliente Objeto Integer que representa el ID del Cliente del que 
     * se mostrará la información del carrito.
     * @param vistaActual Objeto que implementa la interfaz IVista, representa
     * la ventana actual que será cerrada.
     */
    public void mostrarCarritoCompras(Integer idCliente, IVista vistaActual) {
        List<Map<String, Object>> listaInformacionProductosCarrito = obtenerInformacionProductosCarrito(idCliente);
        if(!listaInformacionProductosCarrito.isEmpty()){
            carrito.actualizarDatosEncabezado();
            ((ICarrito)carrito).setProductos(listaInformacionProductosCarrito);
            vistaActual.cerrar();
            carrito.hacerVisible(true);      
        } else{
            mostrarProductosVenta(vistaActual);
            mostrarMensaje("No se han agregado productos al carrito.", COLOR_MENSAJE_ADVERTENCIA);        
        }
        
    }
    
    /**
     * Método que permite mostrar la información del carrito de compras del Cliente
     * con el ID del parámetro, incluyendo los productos agregados, el importe total y por producto,
     * el monto mínimo para envío gratis y el tiempo estimado de preparación del pedido.
     * @param idCliente Objeto Integer que representa el ID del Cliente del que se 
     * mostrará la información del carrito.
     */
    public void mostrarCarritoCompras(Integer idCliente) {
        List<Map<String, Object>> listaInformacionProductosCarrito = obtenerInformacionProductosCarrito(idCliente);
        
        if(!listaInformacionProductosCarrito.isEmpty()){
            carrito.actualizarDatosEncabezado();
            ((ICarrito)carrito).setProductos(listaInformacionProductosCarrito);
            carrito.hacerVisible(true);  
        } else{
            mostrarMensaje("No se han agregado productos al carrito.", COLOR_MENSAJE_ADVERTENCIA);
        }
            
    }

    /**
     * Método que permite obtener la información de los productos que ha agregado
     * el Cliente con el ID del parámetro a su carrito de compras. Incluyendo
     * su nombre, cantidad seleccionada, importe total por cada uno, su proveedor, 
     * e imagen asociada.
     * @param idCliente Objeto String que representa el ID del Cliente del que
     * se obtendrà la informaciòn de los productos asociados a su carrito.
     * @return Objeto {@literal List<Map<String, Object>>} que contiene la información
     * de los productos del carrito del Cliente con el ID del parámetro, cada producto
     * es representado con un mapa.
     */
    public List<Map<String, Object>> obtenerInformacionProductosCarrito(Integer idCliente) {
        
        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = new LinkedList<>();
        List<Map<String, Object>> listaInformacionProductosCarrito = new LinkedList<>();
        
        try {
            listaInformacionProductoCarritoDTO = administradorCarritoCompras.obtenerProductos(idCliente);
            
            for (InformacionProductoCarritoDTO informacionProductoCarritoDTO : listaInformacionProductoCarritoDTO) {

                InformacionProductoVentaDTO informacionProductoInicioDTO;

                informacionProductoInicioDTO = administradorProductos.obtenerInformacionProductoVenta(informacionProductoCarritoDTO.getId());

                informacionProductoCarritoDTO.setNombre(informacionProductoInicioDTO.getNombre());
                informacionProductoCarritoDTO.setVariedad(informacionProductoInicioDTO.getVariedad());
                informacionProductoCarritoDTO.setPrecio(informacionProductoInicioDTO.getPrecio());
                informacionProductoCarritoDTO.setMilesSemillas(informacionProductoInicioDTO.getMilesSemillas());
                informacionProductoCarritoDTO.setDireccionImagenProducto(informacionProductoInicioDTO.getDireccionImagenProducto());
                
                String nombreProveedor = obtenerNombreProveedor(informacionProductoInicioDTO.getIdProveedor());
                informacionProductoCarritoDTO.setNombreProveedor(nombreProveedor);
            }


            for (InformacionProductoCarritoDTO informacionProductoCarrito : listaInformacionProductoCarritoDTO) {


                Map<String, Object> mapaInformacionProductoCarrito = new HashMap<>();
                mapaInformacionProductoCarrito.put("Id", informacionProductoCarrito.getId());
                mapaInformacionProductoCarrito.put("Nombre", informacionProductoCarrito.getNombre());
                mapaInformacionProductoCarrito.put("Variedad", informacionProductoCarrito.getVariedad());
                mapaInformacionProductoCarrito.put("Precio", informacionProductoCarrito.getPrecio());
                mapaInformacionProductoCarrito.put("MilesSemillas", informacionProductoCarrito.getMilesSemillas());
                mapaInformacionProductoCarrito.put("Cantidad", informacionProductoCarrito.getCantidad());
                mapaInformacionProductoCarrito.put("NombreProveedor", informacionProductoCarrito.getNombreProveedor());
                mapaInformacionProductoCarrito.put("DireccionImagenProducto", informacionProductoCarrito.getDireccionImagenProducto());

                listaInformacionProductosCarrito.add(mapaInformacionProductoCarrito);


            }
        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
        } catch (ProductosIdProductoInvalidoException ex) {           
            mostrarMensaje("Ha ocurrido un error al mostrar los productos del carrito.", COLOR_MENSAJE_ADVERTENCIA);
        }

        

        return listaInformacionProductosCarrito;
    }
    
    /**
     * Método que permite obtener la dirección de la imagen del logotipo del Proveedor
     * con el ID del parámetro dentro de la aplicación.
     * @param idProveedor Objeto Integer que representa el ID del proveedor del que
     * se obtendrá la dirección de la imagen de su logotipo.
     * @return Objeto String que representa imagen del logotipo del proveedor con el
     * ID del parámetro dentro de la aplicación.
     */
    public String obtenerDireccionImagenProveedor(Integer idProveedor){
        
        String direccionImagenProveedor = null;
        try {
            direccionImagenProveedor = administradorProveedores.obtenerDireccionImagenProveedor(idProveedor);
        } catch (ProveedoresIdProveedorInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener la información del proveedor.", COLOR_MENSAJE_ERROR);
        }
        
        return direccionImagenProveedor;
        
    }
    
    /**
     * Método que permite obtener el nombre del Proveedor con el ID del parámetro.
     * @param idProveedor Objeto Integer que representa el ID del proveedor 
     * del que se obtendrá su nombre.
     * @return Objeto String que representa el nombre del Proveedor con el ID
     * del parámetro.
     */
    public String obtenerNombreProveedor(Integer idProveedor){

        try {
            return administradorProveedores.obtenerNombreProveedor(idProveedor);
            
        } catch (ProveedoresIdProveedorInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener la información del proveedor.", COLOR_MENSAJE_ERROR);
        }
        
        return null;
    }
    
    /**
     * Método que permite obtener la existencia actual de un producto en venta.
     * @param idProducto Objeto Integer que representa el ID del producto del que
     * se obtendrá su existencia.
     * @return Objeto Integer que representa el número de productos en inventario
     * disponibles del Producto con el ID del parámetro.
     */
    public Integer verificarExistenciasProducto(Integer idProducto){
        
        try {
            return administradorProductos.cosultarInventarioProducto(idProducto);
        } catch (ProductosIdProductoInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al verificar las exisencias de los productos.", COLOR_MENSAJE_ERROR);
        }
        return null;
    }

    /**
     * Método que permite añadir la cantidad del parámetro, del Producto con el 
     * ID del parámetro, al carrito del Cliente con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente al que
     * posee el carrito de compras al que se le agregará el Producto.
     * @param idProducto Objeto Integer que representa el ID del producto qe será
     * agregado al carrito de compras del cliente.
     * @param cantidad Dato int que representa la cantidad de unidades del producto
     * a agregar al carrito.
     * @param vistaActual Objeto que implementa la interfaz IVista, representa la ventana
     * actual que será cerrada, esto sirve para actualizar la ventana del carrito
     * según los productos seleccionados.
     * @return 
     */
    public boolean agregarProductoCarrito(Integer idCliente, Integer idProducto, int cantidad, IVista vistaActual) {

        boolean productoAgregado = false;
        
        try {
            administradorCarritoCompras.agregarProducto(idCliente, idProducto, cantidad);
            productoAgregado = true;
            mostrarCarritoCompras(idCliente, vistaActual);
            
        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (CarritoComprasIdProductoInvalidoException | ProductosIdProductoInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener el producto que seleccionó", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (CarritoComprasProductoSinInventarioException | ProductosProductoSinInventarioException ex) {
            mostrarMensaje("No hay disponibilidad del producto seleccionado", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
            
        }
      
        return productoAgregado;

    }

    /**
     * Método que permite eliminar la cantidad del parámetro, del Producto con el ID del parámetro,
     * del carrito de compras del Cliente con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente que posee
     * el carrito de compras del que se eliminará una cierta cantidad de un Producto.
     * @param idProducto Objeto Integer que representa el ID del Producto que será
     * eliminado del carrito de compras.
     * @param cantidad Dato int que representa la cantidad del Producto a elminar.
     * @return true si se pudo eliminar la cantidad especificada del Producto especificado,
     * false en caso contrario.
     */
    public boolean eliminarProductoCarrito(Integer idCliente, Integer idProducto, int cantidad) {
        
        boolean productoEliminado = false;
        
        try {
            administradorCarritoCompras.eliminarProducto(idCliente, idProducto, cantidad);
            productoEliminado = true;
            mostrarCarritoCompras(idCliente, (IVista)carrito);

        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (CarritoComprasIdProductoInvalidoException | CarritoComprasCarritoSinProductoException 
                | ProductosIdProductoInvalidoException | ProductosProductoSinInventarioException ex) {
            mostrarMensaje("Ha ocurrido un error al eliminar el producto que seleccionó", COLOR_MENSAJE_ERROR);
        } catch (CarritoComprasClienteSinCarritoVigenteException ex) {
            mostrarMensaje("Su carrito de compras está vacío", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
               
        return productoEliminado;

    }

    /**
     * Método que permite obtener el total de unidades de todos los Productos
     * añadidios al carrito del Cliente con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente del que
     * se obtedrá el total de unidades de todos los productos agregados a su carrito.
     * @return 
     */
    public int obtenerNumeroProductosCarrito(Integer idCliente) {

        int numeroProductosCarrito = 0;
        try {
            numeroProductosCarrito = administradorCarritoCompras.obtenerNumeroProductos(idCliente);
        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }

        return numeroProductosCarrito;
    }

    /**
     * Método que permite obtener el monto actual del carrito de compras del Cliente
     * con el ID del parámetro y el monto mínimo necesario para obtener un envío gratuito.
     * @param idCliente Objeto Integer que representa el ID del Cliente del que 
     * se obtendrá la información de monto total de su carrito y monto mínimo requerido
     * para que obtenga un envío gratuito.
     * @return Objeto Double[] que contiene tanto el monto actual del total de productos
     * del carrito del Cliente, como el monto mínomo necesario para obtener un envío
     * gratuito.
     */
    public Double[] obtenerInformacionMontoEnvioGratuito(Integer idCliente) {
        MontoMinimoEnvioGratuitoDTO montoMinimoEnvioGratuitoDTO = administradorCarritoCompras.obtenerInformacionMontoEnvioMinimoGratuito(idCliente);
        Double[] informacionMontoEnvioGratuito = {montoMinimoEnvioGratuitoDTO.getMontoActual(), montoMinimoEnvioGratuitoDTO.getMontoMinimo()};
        return informacionMontoEnvioGratuito;
    }

    /**
     * Método que permite obtener el nombre o nombres y el apellido paterno del
     * Cliente con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente del que 
     * se obtendrá su nombre o nombres y apellido paterno.
     * @return Objeto String[] que contiene el nombre o nombres y el apellido paterno
     * del Cliente.
     */
    public String[] obtenerNombreApellidoCliente(Integer idCliente) {

        NombresApellidoClienteDTO nombreApellidoClienteDTO;
        try {
            nombreApellidoClienteDTO = FAdministradorClientes.obtenerNombresApellidoCliente(idCliente);
            String[] nombreApellidoCliente = {nombreApellidoClienteDTO.getNombresCliente(), nombreApellidoClienteDTO.getApellidoMaternoCliente()};
            return nombreApellidoCliente;
        } catch (ClientesIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        
        return null;
        
    }

    /**
     * Método que permite obtener los números del mínimo y máximo de días estimados 
     * en que se enviarán todos los productos a la Matriz de la empresa para realizar
     * el envío al Cliente con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente del que 
     * se obtendrán el múnimo y máximo de días de preparáción de su pedido.
     * @return Objeto int[] que contiene el mínimo y máximo de días de preparación
     * del pedido del Cliente.
     */
    public int[] obtenerRangoDiasFechaEstimadaPreparacion(Integer idCliente) {
        TiempoEstimadoPreparacionEnvioPedidoDTO tiempoEstimadoPreparacionEnvioPedidoDTO;
        
        try {
            tiempoEstimadoPreparacionEnvioPedidoDTO = administradorCarritoCompras.obtenerTiempoEstimadoPreparacionProductos(idCliente);
            
            if(tiempoEstimadoPreparacionEnvioPedidoDTO != null){
            int[] rangoDiasEstimadoPreparacion = {tiempoEstimadoPreparacionEnvioPedidoDTO.getDiasLimiteInferior(),
            tiempoEstimadoPreparacionEnvioPedidoDTO.getDiasLimiteSuperior()};
            
                return rangoDiasEstimadoPreparacion;
            }
            
        } catch (CarritoComprasClienteSinCarritoVigenteException ex) {
            
        }
  
        
        return null;
        
        
    }

    /**
     * Método que permite mostrar la ventana con las paqueterías que el Cliente puede
     * seleccionar para hacer su pedido.
     * @param idCliente Objeto Integer que representa el ID del Cliente al que se
     * le mostrarán las paqueterías que puede seleccionar.
     * @param envioGratis Dato boolean que determina si el monto total de productos
     * dentro del carrito del Cliente es al menos el monto mínimo requerido para que
     * el envío de sus productos sea gratuito.
     * @param vistaActual Objeto que implementa la interfaz IVista, que representa
     * la ventana actual que será cerrada.
     */
    public void mostrarSeleccionPaqueteria(Integer idCliente, boolean envioGratis, IVista vistaActual) {
        
        HashMap<Integer, String> datosPaqueterias = this.obtenerPaqueterias();
        String[] datosDireccionCliente = recuperarDatosDireccionCliente(idCliente);
        ((ISeleccionPaqueteria)seleccionPaqueteria).setCalleEnvio(datosDireccionCliente[0]);
        ((ISeleccionPaqueteria)seleccionPaqueteria).setNumeroEnvio(datosDireccionCliente[1]);
        ((ISeleccionPaqueteria)seleccionPaqueteria).setColoniaEnvio(datosDireccionCliente[2]);
        ((ISeleccionPaqueteria)seleccionPaqueteria).setCodigoPostalEnvio(datosDireccionCliente[3]);
        ((ISeleccionPaqueteria)seleccionPaqueteria).setEnvioGratis(envioGratis);
        ((ISeleccionPaqueteria)seleccionPaqueteria).setPaqueterias(datosPaqueterias);
        vistaActual.cerrar();
        seleccionPaqueteria.actualizarDatosEncabezado();
        ((IVista)seleccionPaqueteria).hacerVisible(true);
    }
    
    /**
     * Método que permite obtener algunos datos que componen la dirección de envío
     * del Cliente con el ID del parámetro, estos son el número, calle, colonia
     * y Código Postal.
     * @param idCliente Objeto Integer que representa el ID del Cliente del que 
     * se obtendrán los datos de la dirección de envío.
     * @return Objeto String[] que contiene los datos de la dirección de envío
     * del Cliente.
     */
    public String[] recuperarDatosDireccionCliente(Integer idCliente){
        
        InformacionNoDerivadaCPDireccionEnvioDTO informacionNoDerivadaCPDireccionEnvioDTO;
        try {
            informacionNoDerivadaCPDireccionEnvioDTO = FAdministradorClientes.obtenerInformacionNoDerivadaCPDireccionEnvio(idCliente);
            
            String calleCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCalle();
            String numeroCliente =  informacionNoDerivadaCPDireccionEnvioDTO.getNumero();
            String codigoPostalCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCodigoPostal();
            
            String coloniaCliente = FAdministradorClientes.obtenerColoniaCliente(idCliente);
            String[] datosDireccionCliente = {calleCliente, numeroCliente,coloniaCliente, codigoPostalCliente};
            return datosDireccionCliente;
            
        } catch (ClientesIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (DireccionesAccesoArchivoCodigosPostalesFallidoException | DireccionesArchivoCodigosPostalesVacioException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener su dirección", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        
        return null;
        
    }

    /**
     * Método que permite obtener la información de las paqueterías a mostrar en la 
     * ventana de selección de paqueterías, incluyendo el nombre de cada Paquetería
     * y la dirección de la imagen de su logotipo dentro de la aplicación.
     * @return 
     */
    public HashMap<Integer, String> obtenerPaqueterias() {
        List<InformacionSeleccionPaqueteriaDTO> listaInformacionSeleccionPaqueteriaDTO = administradorPaqueterias.obtenerPaqueterias();
        
        HashMap<Integer, String> datosPaqueterias = new HashMap<>();
        
        for(InformacionSeleccionPaqueteriaDTO informacionSeleccionPaqueteriaDTO: listaInformacionSeleccionPaqueteriaDTO){
            datosPaqueterias.put(informacionSeleccionPaqueteriaDTO.getIdPaqueteria(), informacionSeleccionPaqueteriaDTO.getDireccionImagenPaqueteria());
        }
        
        return datosPaqueterias;
    }
    
    /**
     * Método que permite obtener el costo de envío del pedido del Cliente con el
     * ID del parámetro, considerando que será realizado con la Paquetería del ID
     * del parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente que 
     * del que se obtendrá el costo de envío.
     * @param idPqueteia Objeto Integer que representa el ID de la paquetería con 
     * la que se realizará el pedido.
     * @return 
     */
    public Float obtenerCostoEnvioPaqueteria(Integer idCliente, Integer idPqueteia){
        
        IdClientePaqueteriaCalculoCostoEnvioDTO idClientePaqueteriaCalculoCostoEnvioDTO =
                new IdClientePaqueteriaCalculoCostoEnvioDTO(idCliente, idPqueteia);
        
        Float costoEnvioPaqueteria = null;
        try {
            costoEnvioPaqueteria = administradorCarritoCompras.obtenerCostoEnvioProductos(idClientePaqueteriaCalculoCostoEnvioDTO);

        } catch (CarritoComprasIdClienteInvalidoException | PedidosIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
        } catch (CarritoComprasIdPaqueteriaInvalidoException | PedidosIdProductoInvalidoException | PedidosIdPaqueteriaInvalidoException 
                | PaqueteriasIdPaqueteriaInvalidoException | CarritoComprasClienteSinCarritoVigenteException | ProductosIdProductoInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al mostrar el costo de envío", COLOR_MENSAJE_ERROR);
        }
        
        return costoEnvioPaqueteria;
    }
    
    /**
     * Método que permite mostrar el formulario que permite al Cliente actualizar
     * su dirección de envío.
     * @param idCliente Objeto Integer que representa el ID del Cliente al que se
     * le mostrará el formulario para que actualice su dirección.
     * @param vistaActual Objeto que implementa la interfaz IVista, que representa
     * la ventana actual que será cerrada.
     */
    public void mostrarActualizacionDireccionEnvio(Integer idCliente, IVista vistaActual){
        
        InformacionNoDerivadaCPDireccionEnvioDTO informacionNoDerivadaCPDireccionEnvioDTO;
        try {
            informacionNoDerivadaCPDireccionEnvioDTO = FAdministradorClientes.obtenerInformacionNoDerivadaCPDireccionEnvio(idCliente);
            String calleEnvio = informacionNoDerivadaCPDireccionEnvioDTO.getCalle();
            String numeroEnvio =  informacionNoDerivadaCPDireccionEnvioDTO.getNumero();
            String codigoPostalEnvio = informacionNoDerivadaCPDireccionEnvioDTO.getCodigoPostal();
            
            String estadoEnvio = FAdministradorClientes.obtenerEstadoCliente(idCliente);
            String ciudadEnvio =  FAdministradorClientes.obtenerCiudadCliente(idCliente);
            
            ((IDireccion)direccion).setCodigoPostalEnvio(codigoPostalEnvio);
            ((IDireccion)direccion).setCalleEnvio(calleEnvio);
            ((IDireccion)direccion).setNumeroEnvio(numeroEnvio);
            ((IDireccion)direccion).setCiudadEnvio(ciudadEnvio);
            ((IDireccion)direccion).setEstadoEnvio(estadoEnvio);
            
            vistaActual.cerrar();
            direccion.actualizarDatosEncabezado();
            direccion.hacerVisible(true);
            
        } catch (ClientesIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (DireccionesAccesoArchivoCodigosPostalesFallidoException | DireccionesArchivoCodigosPostalesVacioException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener su dirección", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
    }
    
    /**
     * Método que permite obtener los datos de la dirección de envío del Cliente
     * con el ID del parámetro, que puede obtenerse a partir del Código Postal
     * de tal dirección. Estos son el estado, ciudad y colonia.
     * @param codigoPostal Objeto String que representa el Código Postal de la
     * dirección de envío del Cliente de la que se obtendrán los datos derivados
     * de la dirección.
     * @return Objeto String[] que contiene los datos de la dirección de envío
     * derivados de su Código Postal.
     */
    public String[] obtenerDatosDireccionEnvioDerivadosCP(String codigoPostal){
        
        InformacionDerivadaCPDireccionEnvioDTO derivadosDireccionDTO;
        try {
            derivadosDireccionDTO = FAdministradorDirecciones.obtenerDatosDireccionDerivados(codigoPostal);

            if (derivadosDireccionDTO != null) {
                String[] datosDireccionDerivadosCliente = {derivadosDireccionDTO.getColonia(), 
                derivadosDireccionDTO.getCiudad(), derivadosDireccionDTO.getEstado()};

                return datosDireccionDerivadosCliente;
            }
        } catch (DireccionesAccesoArchivoCodigosPostalesFallidoException | DireccionesArchivoCodigosPostalesVacioException ex) {
            mostrarMensaje("Ha ocurrido un error al validar el Código Postal", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
  
        return null;
        
    }
   
    /**
     * Método que permite actualizar los datos de la dirección de envío del Cliente
     * con el ID del parémtro.
     * Estos son el Código Postal, la calle y el número.
     * @param datosCliente Objeto {@literal List<Object>} que representa los nuevos datos
     * de la dirección de envío del Cliente.
     * @param vistaActual Objeto que implementa la interfaz IVista, que representa
     * la ventana actual que será cerrada.
     */
    public void actualizarDatosDireccionCliente(List<Object> datosCliente, IVista vistaActual){
        InformacionNoDerivadaCPDireccionEnvioDTO direccionEntradaDTO = 
                new InformacionNoDerivadaCPDireccionEnvioDTO(
                        (Integer)datosCliente.get(0), 
                        (String)datosCliente.get(1),
                        (String)datosCliente.get(2),
                        (String)datosCliente.get(3));
        
        try {
            FAdministradorClientes.actualizarDireccionCliente(direccionEntradaDTO);
            mostrarProductosVenta(vistaActual);
            mostrarMensaje("Se ha actualizado su dirección", COLOR_MENSAJE_EXITOSO);
        } catch (ClientesIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al momento de actualizar su dirección", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } 

    }
    
    /**
     * Método que permite asociar el carrito de compras del Cliente con el ID del parámetro
     * a la Paquetería con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente a cuyo carrito
     * de compras se le asignará la Paquetería.
     * @param idPaqueteria Objeto Integer que representa el ID de la paquetería a asociar
     * al carrito del Cliente.
     * @param vistaActual Objeto que implementa la interfaz IVista, que representa
     * la ventana actual que será cerrada.
     */
    public void asignarPaqueteriaCarritoCliente(Integer idCliente, Integer idPaqueteria, IVista vistaActual){
        try {
            administradorCarritoCompras.asignarPaqueteriaCarritoCliente(idCliente, idPaqueteria);
            ejecutarCasoUsoPagar(idCliente, vistaActual);
        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (CarritoComprasIdPaqueteriaInvalidoException | CarritoComprasClienteSinCarritoVigenteException ex) {
            mostrarMensaje("Ha ocurrido un error al seleccionar la paquetería", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
    }
    
    /**
     * Método que permite realizar el pedido de los productos del carrito de compras
     * del Cliente con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente al que se
     * realizará el pedido con los productos de su carrito.
     */
    public void realizarPedido(Integer idCliente){
        
        try {
            administradorCarritoCompras.crearPedidoProductosCarritoCliente(idCliente);
            administradorCarritoCompras.caducarCarritoCompras(idCliente);
            mostrarProductosVenta(direccion);
            mostrarConfirmacionPedido();
                 
        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (PedidosIdProductoInvalidoException | CarritoComprasClienteSinCarritoVigenteException | 
                CarritoComprasCarritoVacioException | ProductosIdProductoInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al realizar su pedido", COLOR_MENSAJE_ERROR);
        }
        
    }
    
    /**
     * Método que simula la ejecución del Caso de Uso Pagar, realizado una vez que
     * el Cliente ha seleccionado y confirmado la Paquetería que se utilizará para
     * realizar el envío de productos.
     * En ese caso se simula que el pago fue exitoso.
     * @param idCliente Objeto Integer que representa el ID del Cliente con el que
     * se ejecutará el Caso de Uso Pagar.
     * @param vistaActual Objeto Integer que representa el ID del Cliente al que se
     * realizará el pedido con los productos de su carrito.
     */
    public void ejecutarCasoUsoPagar(Integer idCliente, IVista vistaActual){
        
        vistaActual.cerrar();
        
        // Se ejecuta el Caso de Uso Pagar.
        boolean pagoExitoso = true;
        // Se supone que el pago fue exitoso, continua el Caso de Uso Comprar para
        // mostrar una confirmación de pedido realizado.
        
        if(pagoExitoso){
            vistaActual.cerrar();
            realizarPedido(idCliente);
        }
    }
    
    /**
     * Método que permite mostrar la confirmación de realización de un pedido.
     */
    public void mostrarConfirmacionPedido() {
        mostrarMensaje("Se ha registrado su pedido", COLOR_MENSAJE_EXITOSO);
    }
    
    /**
     * Método que permite mostrar una ventana emergente con el mensaje del parámetro,
     * en el color del parámetro.
     * @param mensajeMostrar Objeto String que representa el mensaje a mostrar.
     * @param color Objeto Color que representa el color con el que se mostrará 
     * la ventana del mensaje.
     */
    public void mostrarMensaje(String mensajeMostrar, Color color){
        ((IMensaje)mensaje).setTexto(mensajeMostrar);
        ((IMensaje)mensaje).setColorFondo(color);
        mensaje.mostrarMensaje();
    }
    
    /**
     * Método que permite finalizar este Caso de Uso.
     * @param vistaActual Objeto Integer que representa el ID del Cliente al que se
     * realizará el pedido con los productos de su carrito.
     */
    public void finalizarCasoUso(IVista vistaActual){
        vistaActual.cerrar();
        // El control se pasa al módulo principal.
    }
}
