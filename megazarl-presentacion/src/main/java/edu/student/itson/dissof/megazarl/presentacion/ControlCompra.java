package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorproductos.excepciones.ProductosIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.IAdministradorCarritoCompras;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoSinProductoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasCarritoVacioException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasClienteSinCarritoVigenteException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProductoInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.carritocompras.excepciones.CarritoComprasProductoSinInventarioException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigoPostalDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionNoDerivadaCPDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdClientePaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoAgregarCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoDetalladaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoEliminarCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoInicioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.TiempoEstimadoPreparacionEnvioPedidoDTO;
import edu.student.itson.dissof.megazarl.negocio.FabricaSubsistemas;
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
 * ControlCompraDTO.java
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
    private Double montoMinimoEnvioGratuito;
    private DireccionDTO direccionDefectoPaquetería;

    private Color COLOR_MENSAJE_EXITOSO = new Color(204, 255, 190);
    private Color COLOR_MENSAJE_ERROR = new Color(255, 195, 195);
    private Color COLOR_MENSAJE_ADVERTENCIA = new Color(255, 253, 222);
    
    private static final Logger LOG = Logger.getLogger(ControlCompra.class.getName());


    public void setVistas(
            IVista productosVenta,
            IVista informacionProducto,
            IVista seleccionPaqueteria,
            IVista carrito,
            IMensaje mensaje,
            IVista direccion,
            Double montoMinimoEnvioGratuito,
            DireccionDTO direccionDefectoPaqueteria) {

        this.productosVenta = productosVenta;
        this.informacionProducto = informacionProducto;
        this.seleccionPaqueteria = seleccionPaqueteria;
        this.carrito = carrito;
        this.mensaje = mensaje;
        this.direccion = direccion;
        this.montoMinimoEnvioGratuito = montoMinimoEnvioGratuito;
        this.direccionDefectoPaquetería = direccionDefectoPaqueteria;
        
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
    public void mostrarProductosBusquedaNombre(String nombreProducto) {
        if(nombreProducto.isBlank()){
            iniciarCompra();
        } else{
            // Se obtiene la lista de mapas que contienen la información de los productos a mostrar.
            List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusquedaNombre(nombreProducto);
            if (listaInformacionProductosBusqueda.isEmpty()) {
                mostrarMensaje("No se encontraron coincidencias de nombres con: \"" + nombreProducto + "\"", COLOR_MENSAJE_ADVERTENCIA);
            } else {
                ((IProductosVenta)productosVenta).setProductosBusqueda(listaInformacionProductosBusqueda);
                productosVenta.hacerVisible(true);
            }
        }
        
    }

    /**
     * Método que permite obtener la información de productos a partir de su nombre.
     * @param nombreProductoBuscado Objeto String que representa el nombre de producto a buscar.
     * @return {@literal List<Map<String, Object>>} Lista con los valores de los atributos
     * de los productos buscados.
     */
    public List<Map<String, Object>> obtenerProductosBusquedaNombre(String nombreProductoBuscado) {
        
        IAdministradorProductos administradorProductos = FabricaSubsistemas.obtenerAdministradorProductos();
        
        List<InformacionProductoInicioDTO> listaProductoInicioDTO 
                = administradorProductos.obtenerProductosBusquedaNombreProducto(nombreProductoBuscado);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (InformacionProductoInicioDTO informacionProductoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();
            mapaInformacionProductoInicio.put("Id", informacionProductoInicioDTO.getIdProducto());
            mapaInformacionProductoInicio.put("Nombre", informacionProductoInicioDTO.getNombreProducto());
            mapaInformacionProductoInicio.put("Variedad", informacionProductoInicioDTO.getVariedadProducto());
            mapaInformacionProductoInicio.put("Precio", informacionProductoInicioDTO.getPrecioProducto());
            mapaInformacionProductoInicio.put("MilesSemillas", informacionProductoInicioDTO.getMilesSemillasProducto());
            mapaInformacionProductoInicio.put("DireccionImagenProducto", informacionProductoInicioDTO.getDireccionImagenProducto());
            
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(informacionProductoInicioDTO.getIdProveedor()); 
            informacionProductoInicioDTO.setDireccionImagenProveedor(direccionImagenProveedor);
            
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);
            
            String nombreProveedor = obtenerNombreProveedor(informacionProductoInicioDTO.getIdProveedor());
            informacionProductoInicioDTO.setProveedorProducto(nombreProveedor);
            
            mapaInformacionProductoInicio.put("NombreProveedor", nombreProveedor);

            listaInformacionProductosBusqueda.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosBusqueda;
    }

    /**
     * Método que permite obtener la información de productos a partir de su nombre.
     * @param nombreProductoBuscado Objeto String que representa el nombre de producto a buscar.
     * @param nombreVariedadBuscado Objeto String que representa la variedad del producto a buscar.
     */
    public void mostrarProductosBusquedaNombreVariedad(String nombreProductoBuscado, String nombreVariedadBuscado) {
        List<Map<String, Object>> listaInformacionProductosBusqueda 
                = obtenerProductosBusquedaNombreVariedad(nombreProductoBuscado, nombreVariedadBuscado);
        ((IProductosVenta)productosVenta).setProductosBusqueda(listaInformacionProductosBusqueda);
        productosVenta.hacerVisible(true);
    }

    /**
     * Método que permite obtener una lista con la información de los productos
     * que tengan el nombre de su variedad, recibido como parámetro.
     * @param nombreProductoBuscado Objeto String que representa el nombre del
     * producto buscado.
     * @param nombreVariedadBuscado Objeto String que representa el nombre
     * de la variedad de los productos buscados.
     * @return Objeto {@literal List<Map<String, Object>>} que representa la lista de los
     * valores de cada atributo de los productos buscados a mostrar.
     */
    public List<Map<String, Object>> obtenerProductosBusquedaNombreVariedad(String nombreProductoBuscado, String nombreVariedadBuscado) {
        
        IAdministradorProductos administradorProductos = FabricaSubsistemas.obtenerAdministradorProductos();
        
        List<InformacionProductoInicioDTO> listaProductoInicioDTO
                = administradorProductos.obtenerProductosBusquedaNombreProductoVariedad(nombreProductoBuscado, nombreVariedadBuscado);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (InformacionProductoInicioDTO productoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();
            mapaInformacionProductoInicio.put("Id", productoInicioDTO.getIdProveedor());
            mapaInformacionProductoInicio.put("Nombre", productoInicioDTO.getNombreProducto());
            mapaInformacionProductoInicio.put("Variedad", productoInicioDTO.getVariedadProducto());
            mapaInformacionProductoInicio.put("Precio", productoInicioDTO.getPrecioProducto());
            mapaInformacionProductoInicio.put("MilesSemillas", productoInicioDTO.getMilesSemillasProducto());
            mapaInformacionProductoInicio.put("DireccionImagenProducto", productoInicioDTO.getDireccionImagenProducto());
              
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(productoInicioDTO.getIdProveedor()); 
            productoInicioDTO.setDireccionImagenProveedor(direccionImagenProveedor); 
            
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);

            listaInformacionProductosBusqueda.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosBusqueda;
    }
    
    /**
     * Método que permite obtener la información de productos a partir de su proveedor.
     * @param nombreProductoBuscado Objeto String que representa el nombre de producto a buscar.
     * @param nombreProveedorBuscado Objeto String que representa el nombre del proveedor del producto a buscar.
     */
    public void mostrarProductosBusquedaNombreProveedor(String nombreProductoBuscado, String nombreProveedorBuscado) {
        
        List<Map<String, Object>> listaInformacionProductosBusqueda 
                = obtenerProductosBusquedaNombreProveedor(nombreProductoBuscado, nombreProveedorBuscado);
        
        ((IProductosVenta)productosVenta).setProductosBusqueda(listaInformacionProductosBusqueda);
        productosVenta.hacerVisible(true);
    }

    /**
     * Método que permite obtener una lista con la información de los productos
     * que tengan el nombre del proveedor recibido como parámetro.
     * @param nombreProductoBuscado Objeto String que representa el nombre del
     * producto buscado.
     * @param nombreProveedorBuscado Objeto String que representa el nombre
     * del proveedor de los productos buscados.
     * @return Objeto {@literal List<Map<String, Object>>} que representa la lista de los
     * valores de cada atributo de los productos buscados a mostrar.
     */
    public List<Map<String, Object>> obtenerProductosBusquedaNombreProveedor(String nombreProductoBuscado, String nombreProveedorBuscado) {
        
        IAdministradorProductos administradorProductos = FabricaSubsistemas.obtenerAdministradorProductos();
        
        List<InformacionProductoInicioDTO> listaProductoInicioDTO 
                = administradorProductos.obtenerProductosBusquedaNombreProductoProveedor(nombreProductoBuscado, nombreProveedorBuscado);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (InformacionProductoInicioDTO informacionProductoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();
            mapaInformacionProductoInicio.put("Id", informacionProductoInicioDTO.getIdProducto());
            mapaInformacionProductoInicio.put("Nombre", informacionProductoInicioDTO.getNombreProducto());
            mapaInformacionProductoInicio.put("Variedad", informacionProductoInicioDTO.getVariedadProducto());
            mapaInformacionProductoInicio.put("Precio", informacionProductoInicioDTO.getPrecioProducto());
            mapaInformacionProductoInicio.put("MilesSemillas", informacionProductoInicioDTO.getMilesSemillasProducto());
            mapaInformacionProductoInicio.put("DireccionImagenProducto", informacionProductoInicioDTO.getDireccionImagenProducto());
            
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(informacionProductoInicioDTO.getIdProveedor()); 
            informacionProductoInicioDTO.setDireccionImagenProveedor(direccionImagenProveedor);
            
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);

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
        
        IAdministradorProductos administradorProductos = FabricaSubsistemas.obtenerAdministradorProductos();
        
        List<InformacionProductoInicioDTO> listaProductoInicioDTO = administradorProductos.obtenerProductosVenta();

        List<Map<String, Object>> listaInformacionProductosInicio = new LinkedList<>();

        for (InformacionProductoInicioDTO informacionProductoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();
            mapaInformacionProductoInicio.put("Id", informacionProductoInicioDTO.getIdProducto());
            mapaInformacionProductoInicio.put("Nombre", informacionProductoInicioDTO.getNombreProducto());
            mapaInformacionProductoInicio.put("Variedad", informacionProductoInicioDTO.getVariedadProducto());
            mapaInformacionProductoInicio.put("Precio", informacionProductoInicioDTO.getPrecioProducto());
            mapaInformacionProductoInicio.put("MilesSemillas", informacionProductoInicioDTO.getMilesSemillasProducto());
            mapaInformacionProductoInicio.put("DireccionImagenProducto", informacionProductoInicioDTO.getDireccionImagenProducto());
            
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(informacionProductoInicioDTO.getIdProveedor());
            informacionProductoInicioDTO.setDireccionImagenProveedor(direccionImagenProveedor);
            
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);

            listaInformacionProductosInicio.add(mapaInformacionProductoInicio);
            
            
        }

        return listaInformacionProductosInicio;
    }

    /**
     * Método que permite mostrar la información de un product cuando este es seleccionado
     * desde la pantalla inicial, donde se muestran todos los productos en venta, o bien
     * cuando se selecciona una vez que se ha realizado una búsqueda.
     * @param idProducto Objeto Long que representa el ID del producto del que
     * se mostrará su información detallada.
     * @param vistaActual Objeto que implementa la interfaz IVista, representa
     * la ventana actual que será cerrada.
     */
    public void mostrarInformacionProducto(Long idProducto, IVista vistaActual) {
        
        Map<String, Object> mapaInformacionProducto = obtenerInformacionProducto(idProducto);
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
    public Map<String, Object> obtenerInformacionProducto(Long idProducto) {

        IAdministradorProductos administradorProductos = FabricaSubsistemas.obtenerAdministradorProductos();
        
        try {
            InformacionProductoDetalladaDTO informacionProductoDTO = administradorProductos.obtenerInformacionProductoVenta(new IdProductoDTO(idProducto));
            Map<String, Object> mapaInformacionProducto = new HashMap<>();
            
            if (informacionProductoDTO != null) {
                mapaInformacionProducto.put("Id", informacionProductoDTO.getIdProducto());
                mapaInformacionProducto.put("Nombre", informacionProductoDTO.getNombreProducto());
                mapaInformacionProducto.put("Variedad", informacionProductoDTO.getVariedadProducto());
                mapaInformacionProducto.put("Descripcion", informacionProductoDTO.getDescripcionProducto());
                mapaInformacionProducto.put("Precio", informacionProductoDTO.getPrecioProducto());
                mapaInformacionProducto.put("MilesSemillas", informacionProductoDTO.getMilesSemillasProducto());
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
    public void mostrarCarritoCompras(Long idCliente, IVista vistaActual) {
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
    public void mostrarCarritoCompras(Long idCliente) {
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
    public List<Map<String, Object>> obtenerInformacionProductosCarrito(Long idCliente) {
        
        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = new LinkedList<>();
        List<Map<String, Object>> listaInformacionProductosCarrito = new LinkedList<>();
        
        try {
            
            IAdministradorCarritoCompras administradorCarritoCompras 
                    = FabricaSubsistemas.obtenerAdministradorCarritoCompras(montoMinimoEnvioGratuito, direccionDefectoPaquetería);
            
            listaInformacionProductoCarritoDTO = administradorCarritoCompras.obtenerProductos(new IdClienteDTO(idCliente));
            
            IAdministradorProductos administradorProductos = FabricaSubsistemas.obtenerAdministradorProductos();
            
            for (InformacionProductoCarritoDTO informacionProductoCarritoDTO : listaInformacionProductoCarritoDTO) {

                InformacionProductoDetalladaDTO informacionProductoInicioDTO;

                informacionProductoInicioDTO 
                        = administradorProductos.obtenerInformacionProductoVenta(new IdProductoDTO(informacionProductoCarritoDTO.getId()));

                informacionProductoCarritoDTO.setNombre(informacionProductoInicioDTO.getNombreProducto());
                informacionProductoCarritoDTO.setVariedad(informacionProductoInicioDTO.getVariedadProducto());
                informacionProductoCarritoDTO.setPrecio(informacionProductoInicioDTO.getPrecioProducto());
                informacionProductoCarritoDTO.setMilesSemillas(informacionProductoInicioDTO.getMilesSemillasProducto());
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
        } catch (ProductosIdProductoInvalidoException | CarritoComprasIdProductoInvalidoException ex) {
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
    public String obtenerDireccionImagenProveedor(Long idProveedor){
        
        String direccionImagenProveedor = null;
        try {
            IAdministradorProveedores administradorProveedores = FabricaSubsistemas.obtenerAdministradorProveedores();
            direccionImagenProveedor = administradorProveedores.obtenerDireccionImagenProveedor(new IdProveedorDTO(idProveedor));
            
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
    public String obtenerNombreProveedor(Long idProveedor){

        try {
            
            IAdministradorProveedores administradorProveedores = FabricaSubsistemas.obtenerAdministradorProveedores();   
            return administradorProveedores.obtenerNombreProveedor(new IdProveedorDTO(idProveedor));
            
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
    public Integer verificarExistenciasProducto(Long idProducto){
        
        try {
            
            IAdministradorProductos administradorProductos = FabricaSubsistemas.obtenerAdministradorProductos();
            return administradorProductos.cosultarInventarioProducto(new IdProductoDTO(idProducto));
            
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
    public boolean agregarProductoCarrito(
            Long idCliente,
            Long idProducto, 
            int cantidad, 
            IVista vistaActual) {

        boolean productoAgregado = false;
        
        try {
            
            IAdministradorCarritoCompras administradorCarritoCompras 
                    = FabricaSubsistemas.obtenerAdministradorCarritoCompras(montoMinimoEnvioGratuito, direccionDefectoPaquetería);
            
            administradorCarritoCompras.agregarProducto(new InformacionProductoAgregarCarritoDTO(
                    idCliente,
                    idProducto,
                    cantidad));
            
            productoAgregado = true;
            
            mostrarCarritoCompras(idCliente, vistaActual);
            
        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (CarritoComprasIdProductoInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener el producto que seleccionó", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (CarritoComprasProductoSinInventarioException ex) {
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
    public boolean eliminarProductoCarrito(Long idCliente, Long idProducto, int cantidad) {
        
        boolean productoEliminado = false;
        
        try {
            IAdministradorCarritoCompras administradorCarritoCompras 
                    = FabricaSubsistemas.obtenerAdministradorCarritoCompras(montoMinimoEnvioGratuito, direccionDefectoPaquetería);
            
            administradorCarritoCompras.eliminarProducto(new InformacionProductoEliminarCarritoDTO(
                    idCliente, 
                    idProducto,
                    cantidad));
            
            productoEliminado = true;
            mostrarCarritoCompras(idCliente, (IVista)carrito);

        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (CarritoComprasIdProductoInvalidoException | CarritoComprasCarritoSinProductoException ex) {
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
    public int obtenerNumeroProductosCarrito(Long idCliente) {

        int numeroProductosCarrito = 0;
        try {
            
            IAdministradorCarritoCompras administradorCarritoCompras 
                    = FabricaSubsistemas.obtenerAdministradorCarritoCompras(montoMinimoEnvioGratuito, direccionDefectoPaquetería);
            numeroProductosCarrito = administradorCarritoCompras.obtenerNumeroProductos(new IdClienteDTO(idCliente));
            
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
     * gratuito. Null si ocurre una excepción.
     */
    public Double[] obtenerInformacionMontoEnvioGratuito(Long idCliente) {
        
        IAdministradorCarritoCompras administradorCarritoCompras
                = FabricaSubsistemas.obtenerAdministradorCarritoCompras(montoMinimoEnvioGratuito, direccionDefectoPaquetería);
        
        try {
            MontoMinimoEnvioGratuitoDTO montoMinimoEnvioGratuitoDTO 
                    = administradorCarritoCompras.obtenerInformacionMontoEnvioMinimoGratuito(new IdClienteDTO(idCliente));
            
            Double[] informacionMontoEnvioGratuito = {montoMinimoEnvioGratuitoDTO.getMontoActual(), montoMinimoEnvioGratuitoDTO.getMontoMinimo()};
            
            return informacionMontoEnvioGratuito;
            
        } catch (CarritoComprasIdClienteInvalidoException | CarritoComprasIdProductoInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
        } catch (CarritoComprasClienteSinCarritoVigenteException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener el carrito del cliente", COLOR_MENSAJE_ERROR);
        }
        
        return null;
    }

    /**
     * Método que permite obtener el nombre o nombres y el apellido paterno del
     * Cliente con el ID del parámetro.
     * @param idCliente Objeto Integer que representa el ID del Cliente del que 
     * se obtendrá su nombre o nombres y apellido paterno.
     * @return Objeto String[] que contiene el nombre o nombres y el apellido paterno
     * del Cliente.
     */
    public String[] obtenerNombreApellidoCliente(Long idCliente) {

        NombresApellidoClienteDTO nombreApellidoClienteDTO;
        try {
            
            IAdministradorClientes administradorClientes = FabricaSubsistemas.obtenerAdministradorClientes();
            nombreApellidoClienteDTO = administradorClientes.obtenerNombresApellidoCliente(new IdClienteDTO(idCliente));
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
    public int[] obtenerRangoDiasFechaEstimadaPreparacion(Long idCliente) {
        TiempoEstimadoPreparacionEnvioPedidoDTO tiempoEstimadoPreparacionEnvioPedidoDTO;
        
        try {
            IAdministradorCarritoCompras administradorCarritoCompras 
                    = FabricaSubsistemas.obtenerAdministradorCarritoCompras(montoMinimoEnvioGratuito, direccionDefectoPaquetería);
            tiempoEstimadoPreparacionEnvioPedidoDTO = administradorCarritoCompras.obtenerTiempoEstimadoPreparacionProductos(new IdClienteDTO(idCliente));
            
            if(tiempoEstimadoPreparacionEnvioPedidoDTO != null){
            int[] rangoDiasEstimadoPreparacion = {tiempoEstimadoPreparacionEnvioPedidoDTO.getDiasLimiteInferior(),
            tiempoEstimadoPreparacionEnvioPedidoDTO.getDiasLimiteSuperior()};
            
                return rangoDiasEstimadoPreparacion;
            }
            
        } catch (CarritoComprasClienteSinCarritoVigenteException ex) {
            mostrarMensaje("Ha ocurrido un error al obtenre la información de su carrito de compras", COLOR_MENSAJE_ERROR);
        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error en la sesión del usuario", COLOR_MENSAJE_ERROR);
        } catch (CarritoComprasIdProveedorInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al obtenre la información de proveedor", COLOR_MENSAJE_ERROR);
        } catch (CarritoComprasIdSucursalInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al obtenre la información de sucursal", COLOR_MENSAJE_ERROR);
        } catch (CarritoComprasIdProductoInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener la información de los productos de su carrito de compras", COLOR_MENSAJE_ERROR);
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
    public void mostrarSeleccionPaqueteria(Long idCliente, boolean envioGratis, IVista vistaActual) {
        
        HashMap<Long, String> datosPaqueterias = this.obtenerPaqueterias();
        
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
    public String[] recuperarDatosDireccionCliente(Long idCliente){
        
        InformacionNoDerivadaCPDireccionDTO informacionNoDerivadaCPDireccionEnvioDTO;
        try {
            
            IAdministradorClientes administradorClientes = FabricaSubsistemas.obtenerAdministradorClientes();
            
            informacionNoDerivadaCPDireccionEnvioDTO = administradorClientes.obtenerInformacionNoDerivadaCPDireccionEnvio(new IdClienteDTO(idCliente));
            
            String calleCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCalle();
            String numeroCliente =  informacionNoDerivadaCPDireccionEnvioDTO.getNumero();
            String codigoPostalCliente = informacionNoDerivadaCPDireccionEnvioDTO.getCodigoPostal();
            String coloniaCliente = informacionNoDerivadaCPDireccionEnvioDTO.getColonia();
            
            String[] datosDireccionCliente = {calleCliente, numeroCliente, coloniaCliente, codigoPostalCliente};
            
            
            return datosDireccionCliente;
            
        } catch (ClientesIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
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
    public HashMap<Long, String> obtenerPaqueterias() {
        
        IAdministradorPaqueterias administradorPaqueterias = FabricaSubsistemas.obtenerAdministradorPaqueterias(direccionDefectoPaquetería);
        
        List<InformacionSeleccionPaqueteriaDTO> listaInformacionSeleccionPaqueteriaDTO = administradorPaqueterias.obtenerPaqueterias();
        
        HashMap<Long, String> datosPaqueterias = new HashMap<>();
        
        for(InformacionSeleccionPaqueteriaDTO informacionSeleccionPaqueteriaDTO: listaInformacionSeleccionPaqueteriaDTO){
            datosPaqueterias.put(informacionSeleccionPaqueteriaDTO.getIdPaqueteria(), informacionSeleccionPaqueteriaDTO.getDireccionImagenPaqueteria());
        }
        
        return datosPaqueterias;
    }
    
    /**
     * Método que permite obtener el costo de envío del pedido del Cliente con el
     * ID del parámetro, considerando que será realizado con la Paquetería del ID
     * del parámetro.
     * @param idCliente Objeto Long que representa el ID del Cliente que 
     * del que se obtendrá el costo de envío.
     * @param idPqueteia Objeto Long que representa el ID de la paquetería con 
     * la que se realizará el pedido.
     * @return 
     */
    public Float obtenerCostoEnvioPaqueteria(Long idCliente, Long idPqueteia){
        
        IdClientePaqueteriaDTO idClientePaqueteriaCalculoCostoEnvioDTO =
                new IdClientePaqueteriaDTO(idCliente, idPqueteia);
        
        Float costoEnvioPaqueteria = null;
        try {
            IAdministradorCarritoCompras administradorCarritoCompras 
                    = FabricaSubsistemas.obtenerAdministradorCarritoCompras(montoMinimoEnvioGratuito, direccionDefectoPaquetería);
            
            costoEnvioPaqueteria = administradorCarritoCompras.obtenerCostoEnvioProductos(idClientePaqueteriaCalculoCostoEnvioDTO);

        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
        } catch (CarritoComprasIdPaqueteriaInvalidoException | CarritoComprasClienteSinCarritoVigenteException 
                | CarritoComprasIdSucursalInvalidoException | CarritoComprasIdProveedorInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al mostrar el costo de envío", COLOR_MENSAJE_ERROR);
        } catch (CarritoComprasIdProductoInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener la información de los productos", COLOR_MENSAJE_ERROR);
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
    public void mostrarActualizacionDireccionEnvio(Long idCliente, IVista vistaActual){
        
        InformacionNoDerivadaCPDireccionDTO informacionNoDerivadaCPDireccionEnvioDTO;
        try {
            IAdministradorClientes administradorClientes = FabricaSubsistemas.obtenerAdministradorClientes();
            
            informacionNoDerivadaCPDireccionEnvioDTO = administradorClientes.obtenerInformacionNoDerivadaCPDireccionEnvio(new IdClienteDTO(idCliente));
            String calleEnvio = informacionNoDerivadaCPDireccionEnvioDTO.getCalle();
            String numeroEnvio =  informacionNoDerivadaCPDireccionEnvioDTO.getNumero();
            String coloniaEnvio = informacionNoDerivadaCPDireccionEnvioDTO.getColonia();
            String codigoPostalEnvio = informacionNoDerivadaCPDireccionEnvioDTO.getCodigoPostal();

            InformacionDerivadaCPDireccionDTO informacionDerivadaCPDirecciionDTO 
                    = administradorClientes.obtenerInformacionDerivadaCPDireccionEnvio(new IdClienteDTO(idCliente));
            
            String estadoEnvio = informacionDerivadaCPDirecciionDTO.getEstado();
            String ciudadEnvio =  informacionDerivadaCPDirecciionDTO.getCiudad();
            
            ((IDireccion)direccion).setCodigoPostalEnvio(codigoPostalEnvio);
            ((IDireccion)direccion).setCalleEnvio(calleEnvio);
            ((IDireccion)direccion).setNumeroEnvio(numeroEnvio);
            ((IDireccion)direccion).setCiudadEnvio(ciudadEnvio);
            ((IDireccion)direccion).setColoniaEnvio(coloniaEnvio);
            ((IDireccion)direccion).setEstadoEnvio(estadoEnvio);
            
            vistaActual.cerrar();
            direccion.actualizarDatosEncabezado();
            direccion.hacerVisible(true);
            
        } catch (ClientesIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (ClientesIdDireccionInvalidoException ex) {
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
     public Object[] obtenerDatosDireccionEnvioDerivadosCP(String codigoPostal){
        
        InformacionDerivadaCPDireccionDTO informacionDerivadaCPDireccionDTO;
        try {
            IAdministradorDirecciones administradorDirecciones = FabricaSubsistemas.obtenerAdministradorDirecciones();
            informacionDerivadaCPDireccionDTO = administradorDirecciones.obtenerDatosDireccionDerivados(new CodigoPostalDTO(codigoPostal));

            if (informacionDerivadaCPDireccionDTO != null) {
                Object[] datosDireccionDerivadosCliente = {
                    informacionDerivadaCPDireccionDTO.getColonias(), 
                    informacionDerivadaCPDireccionDTO.getCiudad(), 
                    informacionDerivadaCPDireccionDTO.getEstado()
                };

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
        InformacionDireccionEnvioActualizadaClienteDTO informacionDireccionEnvioActualizadaClienteDTO = 
                new InformacionDireccionEnvioActualizadaClienteDTO(
                        (Long)datosCliente.get(0), 
                        (String)datosCliente.get(1),
                        (String)datosCliente.get(2),
                        (String)datosCliente.get(3),
                        (String)datosCliente.get(4)
                );
        
        try {
            IAdministradorClientes administradorClientes = FabricaSubsistemas.obtenerAdministradorClientes();
            administradorClientes.actualizarDireccionCliente(informacionDireccionEnvioActualizadaClienteDTO);
            mostrarProductosVenta(vistaActual);
            mostrarMensaje("Se ha actualizado su dirección", COLOR_MENSAJE_EXITOSO);
        } catch (ClientesIdClienteInvalidoException | 
                ClientesAccesoArchivoCodigosPostalesFallidoException | 
                ClientesArchivoCodigosPostalesVacioException ex) {
            
            mostrarMensaje("Ha ocurrido un error al momento de actualizar su dirección", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }

    }
    
    /**
     * Método que permite asociar el carrito de compras del Cliente con el ID del parámetro
     * a la Paquetería con el ID del parámetro.
     * @param idCliente Objeto Long que representa el ID del Cliente a cuyo carrito
     * de compras se le asignará la Paquetería.
     * @param idPaqueteria Objeto Long que representa el ID de la paquetería a asociar
     * al carrito del Cliente.
     * @param vistaActual Objeto que implementa la interfaz IVista, que representa
     * la ventana actual que será cerrada.
     */
    public void asignarPaqueteriaCarritoCliente(
            Long idCliente,
            Long idPaqueteria, 
            IVista vistaActual){
        
        try {
            
            IAdministradorCarritoCompras administradorCarritoCompras 
                    = FabricaSubsistemas.obtenerAdministradorCarritoCompras(montoMinimoEnvioGratuito, direccionDefectoPaquetería);
            administradorCarritoCompras.asignarPaqueteriaCarritoCliente(new IdClientePaqueteriaDTO(idCliente, idPaqueteria));
            
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
    public void realizarPedido(Long idCliente){
        
        try {
            IAdministradorCarritoCompras administradorCarritoCompras 
                    = FabricaSubsistemas.obtenerAdministradorCarritoCompras(montoMinimoEnvioGratuito, direccionDefectoPaquetería);
            administradorCarritoCompras.crearPedidoProductosCarritoCliente(new IdClienteDTO(idCliente));
            mostrarProductosVenta(direccion);
            mostrarConfirmacionPedido();
                 
        } catch (CarritoComprasIdClienteInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (CarritoComprasClienteSinCarritoVigenteException | 
                CarritoComprasCarritoVacioException  ex) {
            mostrarMensaje("Ha ocurrido un error al realizar su pedido", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (CarritoComprasIdProductoInvalidoException ex) {
             mostrarMensaje("Ha ocurrido un error al obtener la información del usuario", COLOR_MENSAJE_ERROR);
             LOG.log(Level.SEVERE, ex.getMessage());
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
    public void ejecutarCasoUsoPagar(Long idCliente, IVista vistaActual){
        
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
