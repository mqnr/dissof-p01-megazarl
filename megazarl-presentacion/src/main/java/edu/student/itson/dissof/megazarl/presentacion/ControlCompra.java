package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.IAdministradorPaqueterias;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.carritocompras.ICarritoCompras;
import edu.student.itson.dissof.megazarl.dto.CodigosSucursalesDTO;
import edu.student.itson.dissof.megazarl.dto.DetallesDerivadosDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.DireccionClienteProductosEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.DireccionEntradaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoVentaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoCarritoCantidadIdDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
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
import javax.swing.JFrame;

public class ControlCompra {

    private IVista productosVenta;
    private IVista informacionProducto;
    private IVista seleccionPaqueteria;
    private IVista carrito;
    private IMensaje mensaje;
    private IVista direccion;
    private IAdministradorClientes administradorClientes;
    private IAdministradorProductos administradorProductos;
    private ICarritoCompras carritoCompras;
    private IAdministradorPaqueterias administradorPaqueterias;
    private IAdministradorSucursales adminisrtadorSucursales;
    private IAdministradorProveedores administradorProveedores;
    
    private Color COLOR_MENSAJE_EXITOSO = new Color(204, 255, 190);
    private Color COLOR_MENSAJE_ERROR = new Color(255, 195, 195);
    private Color COLOR_MENSAJE_ADVERTENCIA = new Color(255, 195, 195);

    // ¿Por qué pasarle únicamente el administrador de clientes en el constructor? Porque a todas las vistas se les pasa
    // un ControlCompra, y estas a su vez inicializan un Encabezado, el cual depende del ControlCompra (y
    // transitivamente del FAdministradorClientes) para obtener la dirección del cliente a mostrar. Sin embargo, ya que
    // no se agregaban los subsistemas como atributos hasta el final (en setVistas), esto resultaba en un
    // NullPointerException.
    // Pienso que deberíamos o inicializar todos los subsistemas en el constructor o ninguno; así
    // que en cualquier caso, esta es una solución temporal.
    public ControlCompra(
        IAdministradorClientes administradorClientes,
        IAdministradorProductos administradorProductos,
        ICarritoCompras carritoCompras,
        IAdministradorPaqueterias administradorPaqueterias,
        IAdministradorSucursales administradorSucursales,
        IAdministradorProveedores administradorProveedores){
        
        this.administradorClientes = administradorClientes;
        this.administradorProductos = administradorProductos;
        this.administradorPaqueterias = administradorPaqueterias;
        this.carritoCompras = carritoCompras;
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
    
    public void iniciarCompra() {
        List<Map<String, Object>> listaInformacionProductosInicio = obtenerProductosVenta();
        productosVenta.actualizarDatosEncabezado();
        ((IProductosVenta)productosVenta).setProductosTodos(listaInformacionProductosInicio);
        productosVenta.hacerVisible(true);
    }

    public void mostrarProductosVenta(IVista vistaActual) {
        vistaActual.cerrar();
        List<Map<String, Object>> listaInformacionProductosInicio = obtenerProductosVenta();
        productosVenta.actualizarDatosEncabezado();
        ((IProductosVenta)productosVenta).setProductosTodos(listaInformacionProductosInicio);
        productosVenta.hacerVisible(true);
    }

    public void mostrarProductosBusqueda(String nombreProducto) {
        List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusqueda(nombreProducto);
        if (listaInformacionProductosBusqueda.isEmpty()) {
            mostrarMensaje("Busqueda inválida", COLOR_MENSAJE_ADVERTENCIA);
        } else {
            productosVenta.actualizarDatosEncabezado();
            ((IProductosVenta)productosVenta).setProductosBusqueda(listaInformacionProductosBusqueda);
            productosVenta.hacerVisible(true);
        }
    }

    public List<Map<String, Object>> obtenerProductosBusqueda(String nombreProducto) {
        List<ProductoInicioDTO> listaProductoInicioDTO = administradorProductos.obtenerProductosBusqueda(nombreProducto);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (ProductoInicioDTO productoInicioDTO : listaProductoInicioDTO) {
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

    public void mostrarProductosBusqueda(String nombreProducto, String nombreVariedad) {
        List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusqueda(nombreProducto, nombreVariedad);
        productosVenta.actualizarDatosEncabezado();
        ((IProductosVenta)productosVenta).setProductosBusqueda(listaInformacionProductosBusqueda);
        productosVenta.hacerVisible(true);
    }

    public List<Map<String, Object>> obtenerProductosBusqueda(String nombreProducto, String nombreVariedad) {
        List<ProductoInicioDTO> listaProductoInicioDTO = administradorProductos.obtenerProductosBusqueda(nombreProducto, nombreVariedad);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (ProductoInicioDTO productoInicioDTO : listaProductoInicioDTO) {
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

    public void mostrarProductosBusqueda(String nombreProducto, String nombreVariedad, String nombreProveeedor) {
        List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusqueda(nombreProducto, nombreVariedad, nombreProveeedor);
        productosVenta.actualizarDatosEncabezado();
        ((IProductosVenta)productosVenta).setProductosBusqueda(listaInformacionProductosBusqueda);
        ((IVista)productosVenta).hacerVisible(true);
    }

    public List<Map<String, Object>> obtenerProductosBusqueda(String nombreProducto, String nombreVariedad, String nombreProveeedor) {
        List<ProductoInicioDTO> listaProductoInicioDTO = administradorProductos.obtenerProductosBusqueda(nombreProducto, nombreVariedad, nombreProveeedor);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (ProductoInicioDTO productoInicioDTO : listaProductoInicioDTO) {
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

    public List<Map<String, Object>> obtenerProductosVenta() {
        List<ProductoInicioDTO> listaProductoInicioDTO = administradorProductos.obtenerProductosVenta();

        List<Map<String, Object>> listaInformacionProductosInicio = new LinkedList<>();

        for (ProductoInicioDTO productoInicioDTO : listaProductoInicioDTO) {
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

    public void mostrarInformacionProducto(Integer idProducto, IVista vistaActual) {
        Map<String, Object> mapaInformacionProducto = this.obtenerInformacionProducto(idProducto);
        informacionProducto.actualizarDatosEncabezado();
        ((IInformacionProducto)informacionProducto).setProducto(mapaInformacionProducto);
        vistaActual.cerrar();
        informacionProducto.hacerVisible(true);
    }

    public Map<String, Object> obtenerInformacionProducto(Integer idProducto) {
        InformacionProductoVentaDTO informacionProductoDTO = administradorProductos.obtenerInformacionProducto(idProducto);

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
    }

    
    public void mostrarCarritoCompras(Integer idCliente, IVista vistaActual) {
        List<Map<String, Object>> listaInformacionProductosCarrito = this.obtenerInformacionProductosCarrito(idCliente);
        if(!listaInformacionProductosCarrito.isEmpty()){
            carrito.actualizarDatosEncabezado();
            ((ICarrito)carrito).setProductos(listaInformacionProductosCarrito);
            vistaActual.cerrar();
            carrito.hacerVisible(true);      
        } else{
            mostrarMensaje("No se han agregado productos al carrito.", new Color(255, 239, 178));
        }
        
    }
    
    public void mostrarCarritoCompras(Integer idCliente) {
        List<Map<String, Object>> listaInformacionProductosCarrito = this.obtenerInformacionProductosCarrito(idCliente);
        
        if(!listaInformacionProductosCarrito.isEmpty()){
            carrito.actualizarDatosEncabezado();
            ((ICarrito)carrito).setProductos(listaInformacionProductosCarrito);
            carrito.hacerVisible(true);  
        } else{
            mostrarMensaje("No se han agregado productos al carrito.", new Color(255, 239, 178));
        }
            
    }

    public List<Map<String, Object>> obtenerInformacionProductosCarrito(Integer idCliente) {
        
        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = carritoCompras.obtenerProductos(idCliente);

        for (InformacionProductoCarritoDTO informacionProductoCarritoDTO : listaInformacionProductoCarritoDTO) {
            
            InformacionProductoVentaDTO informacionProductoInicioDTO = 
                    administradorProductos.obtenerInformacionProducto(informacionProductoCarritoDTO.getId());
            

            informacionProductoCarritoDTO.setNombre(informacionProductoInicioDTO.getNombre());
            informacionProductoCarritoDTO.setVariedad(informacionProductoInicioDTO.getVariedad());
            informacionProductoCarritoDTO.setPrecio(informacionProductoInicioDTO.getPrecio());
            informacionProductoCarritoDTO.setMilesSemillas(informacionProductoInicioDTO.getMilesSemillas());
            informacionProductoCarritoDTO.setNombreProveedor(informacionProductoInicioDTO.getNombreProveedor());
            informacionProductoCarritoDTO.setDireccionImagenProducto(informacionProductoInicioDTO.getDireccionImagenProducto());
            
            
        }

        List<Map<String, Object>> listaInformacionProductosCarrito = new LinkedList<>();
        
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

        return listaInformacionProductosCarrito;
    }
    
    public String obtenerDireccionImagenProveedor(Integer idProveedor){
        
        String direccionImagenProveedor = administradorProveedores.obtenerDireccionImagenProveedor(idProveedor);
        
        return direccionImagenProveedor;
        
    }
    
    public String obtenerNombreProveedor(Integer idProveedor){
        
        String nombreProveedor = administradorProveedores.obtenerNombreProveedor(idProveedor);
        
        return nombreProveedor;
    }
    
    public int verificarExistenciasProducto(Integer idProducto){
        
        return administradorProductos.cosultarInventarioProducto(idProducto);
        
    }

    public boolean agregarProductoCarrito(Integer idCliente, Integer idProducto, int cantidad, IVista vistaActual) {

        boolean productoAgregado = false;
        if(carritoCompras.agregarProducto(idCliente, idProducto, cantidad)){
                if (administradorProductos.eliminarProductoInventario(idProducto, cantidad)){
                    mostrarCarritoCompras(idCliente, vistaActual);
                    productoAgregado = true;
                }
        }
                
        return productoAgregado;

    }

    public boolean eliminarProductoCarrito(Integer idCliente, Integer idProducto, int cantidad) {
        
        boolean productoEliminado = false;
        if(carritoCompras.eliminarProducto(idCliente, idProducto, cantidad)){
            
            productoEliminado = true;
            
            mostrarCarritoCompras(idCliente, (IVista)carrito);
            
   

        }
        
        return productoEliminado;

    }

    public Integer obtenerNumeroProductosCarrito(Integer idCliente) {

        return carritoCompras.obtenerNumeroProductos(idCliente);

    }

    public Double[] obtenerInformacionMontoEnvioGratuito() {
        MontoMinimoEnvioGratuitoDTO montoMinimoEnvioGratuitoDTO = carritoCompras.obtenerInformacionMontoEnvioMinimoGratuito();
        Double[] informacionMontoEnvioGratuito = {montoMinimoEnvioGratuitoDTO.getMontoActual(), montoMinimoEnvioGratuitoDTO.getMontoMinimo()};
        return informacionMontoEnvioGratuito;
    }

    public String[] obtenerNombreApellidoCliente(Integer idCliente) {

        NombreApellidoClienteDTO nombreApellidoClienteDTO = this.administradorClientes.obtenerNombreApellidoPaternoCliente(idCliente);
        String[] nombreApellidoCliente = {nombreApellidoClienteDTO.getNombresCliente(), nombreApellidoClienteDTO.getApellidoMaternoCliente()};
        return nombreApellidoCliente;

    }

    public int[] obtenerRangoDiasFechaEstimadaPreparacion(Integer idCliente) {
        TiempoEstimadoPreparacionEnvioPedidoDTO tiempoEstimadoPreparacionEnvioPedidoDTO = carritoCompras.obtenerTiempoEstimadoPreparacionProductos(idCliente);
  
        if(tiempoEstimadoPreparacionEnvioPedidoDTO != null){
            int[] rangoDiasEstimadoPreparacion = {tiempoEstimadoPreparacionEnvioPedidoDTO.getDiasLimiteInferior(),
            tiempoEstimadoPreparacionEnvioPedidoDTO.getDiasLimiteSuperior()};
            
            return rangoDiasEstimadoPreparacion;
        }
        return null;
        
        
    }

    public void mostrarSeleccionPaqueteria(Integer idCliente, boolean envioGratis, IVista vistaActual) {
        
        HashMap<Integer, String> datosPaqueterias = this.obtenerPaqueterias();
        String[] datosDireccionCliente = recuperarDatosDireccionCliente(Integer.SIZE);
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
    
    public String[] recuperarDatosDireccionCliente(Integer idCliente){
        String calleCliente = administradorClientes.obtenerCalleCliente(idCliente);
        String numeroCliente =  administradorClientes.obtenerNumeroCliente(idCliente);
        String coloniaCliente = administradorClientes.obtenerColoniaCliente(idCliente);
        String codigoPostalCliente = administradorClientes.obtenerCodigoPostalCliente(idCliente);
        
        String[] datosDireccionCliente = {calleCliente, numeroCliente,coloniaCliente, codigoPostalCliente};
        
        return datosDireccionCliente;
    }

    public HashMap<Integer, String> obtenerPaqueterias() {
        List<InformacionSeleccionPaqueteriaDTO> listaInformacionSeleccionPaqueteriaDTO = administradorPaqueterias.obtenerPaqueterias();
        
        HashMap<Integer, String> datosPaqueterias = new HashMap<>();
        
        for(InformacionSeleccionPaqueteriaDTO informacionSeleccionPaqueteriaDTO: listaInformacionSeleccionPaqueteriaDTO){
            datosPaqueterias.put(informacionSeleccionPaqueteriaDTO.getIdPaqueteria(), informacionSeleccionPaqueteriaDTO.getDireccionImagenPaqueteria());
        }
        
        return datosPaqueterias;
    }
    
    public double obtenerCostoEnvioPaqueteria(Integer idCliente, Integer idPqueteia){
        
        String codigoPostalCliente = administradorClientes.obtenerCodigoPostalCliente(idCliente);
        String calleCliente = administradorClientes.obtenerCalleCliente(idCliente);
        String numeroCliente =  administradorClientes.obtenerNumeroCliente(idCliente);

        ProductoCarritoCantidadIdDTO productoCarritoCantidadIdDTO = carritoCompras.obtenerIdsProductosInventario(idCliente);
        
        List<Integer> listaIdsProductosInventarioCarrito = productoCarritoCantidadIdDTO.getCodigosProductos();


        CodigosSucursalesDTO codigosSucursalesDTO = adminisrtadorSucursales.obtenerCodigosSucursales();
        List<Integer> codigosSucursales = codigosSucursalesDTO.getCodigosSucursales();
        
        
        HashMap<Integer, Double> idsPesosProductosKg = new HashMap<>();
        HashMap<Integer, Double> idsTiemposProductosMatriz = new HashMap<>();
        HashMap<Integer, String> idsCodigosPostalesSucursales = new HashMap<>();
                
        for(Integer idProductoInventarioCarrito : listaIdsProductosInventarioCarrito){
            
            idsPesosProductosKg.put(idProductoInventarioCarrito, 
                    administradorProductos.obtenerPesoProductoInventario(idProductoInventarioCarrito));
            idsTiemposProductosMatriz.put(idProductoInventarioCarrito, 
                    administradorProductos.obtenerTiempoMatrzProductoInventario(idProductoInventarioCarrito));
        }
        
        for(Integer idSucursal: codigosSucursales){
            idsCodigosPostalesSucursales.put(idSucursal, adminisrtadorSucursales.obtenerCodigoPostal(idSucursal));
        }
        
        
        
        DireccionClienteProductosEnvioDTO direccionClienteProductosEnvioDTO =
                new DireccionClienteProductosEnvioDTO(
                        idPqueteia, 
                        idCliente, 
                        codigoPostalCliente, 
                        calleCliente,
                        numeroCliente, 
                        idsPesosProductosKg, 
                        idsTiemposProductosMatriz, 
                        idsCodigosPostalesSucursales);
        
        return administradorPaqueterias.obtenerCostoEnvio(direccionClienteProductosEnvioDTO);
    }

    public void mostrarConfirmacionPedido(JFrame frameActual) {
        frameActual.dispose();
        mostrarMensaje("Se ha registrado su pedido", COLOR_MENSAJE_EXITOSO);
    }
    
    public void mostrarActualizacionDireccionEnvio(Integer idCliente, IVista vistaActual){
        String calleEnvio = administradorClientes.obtenerCalleCliente(idCliente);
        String numeroEnvio =  administradorClientes.obtenerNumeroCliente(idCliente);
        String codigoPostalEnvio = administradorClientes.obtenerCodigoPostalCliente(idCliente);
        String estadoEnvio = administradorClientes.obtenerEstadoCliente(idCliente);
        String ciudadEnvio =  administradorClientes.obtenerCiudadCliente(idCliente);
        ((IDireccion)direccion).setCodigoPostalEnvio(codigoPostalEnvio);
        ((IDireccion)direccion).setCalleEnvio(calleEnvio);
        ((IDireccion)direccion).setNumeroEnvio(numeroEnvio);
        ((IDireccion)direccion).setCiudadEnvio(ciudadEnvio);
        ((IDireccion)direccion).setEstadoEnvio(estadoEnvio);
        vistaActual.cerrar();
        direccion.actualizarDatosEncabezado();
        direccion.hacerVisible(true);
    }
    
    
    // Quitar recuperacion como responsabilidad del administrador de clientes.
    public String[] obtenerDatosDireccionDerivados(String codigoPostal){
        DetallesDerivadosDireccionDTO derivadosDireccionDTO = 
                administradorClientes.obtenerDatosDireccionDerivado(codigoPostal);
        
        if(derivadosDireccionDTO != null){
            String[] datosDireccionDerivadosCliente = {derivadosDireccionDTO.getColonia(), 
                derivadosDireccionDTO.getCiudad(), derivadosDireccionDTO.getEstado()};
            
            return datosDireccionDerivadosCliente;
        }
        
        return null;
        
    }
    
    public String[] obtenerDatosDireccionDerivados(Integer idCliente){
        
        String coloniaCliente = administradorClientes.obtenerColoniaCliente(idCliente);
        String estadoCliente = administradorClientes.obtenerEstadoCliente(idCliente);
        String ciudadCliente = administradorClientes.obtenerCiudadCliente(idCliente);
        
        if(coloniaCliente != null && estadoCliente != null && ciudadCliente != null){
            String[] datosDireccionDerivadosCliente = {coloniaCliente, ciudadCliente, estadoCliente};
            
            return datosDireccionDerivadosCliente;
        }
        
        return null;
        
        
    }
   
    public void actualizarDatosDireccionCliente(List<Object> datosCliente, IVista vistaActual){
        DireccionEntradaDTO direccionEntradaDTO = 
                new DireccionEntradaDTO(
                        (Integer)datosCliente.get(0), 
                        (String)datosCliente.get(1),
                        (String)datosCliente.get(2),
                        (String)datosCliente.get(3));
        
        if(administradorClientes.actualizarDireccionCliente(direccionEntradaDTO)){
            mostrarProductosVenta(vistaActual);
            mostrarMensaje("Se ha actualizado su dirección", COLOR_MENSAJE_EXITOSO);
        } else{
            mostrarMensaje("Ha ocurrido un error al momento de actualizar su dirección", COLOR_MENSAJE_ERROR);
        }
    }
    
    public void mostrarMensaje(String mensajeMostrar, Color color){
        ((IMensaje)mensaje).setTexto(mensajeMostrar);
        ((IMensaje)mensaje).setColorFondo(color);
        mensaje.mostrarMensaje();
    }
}
