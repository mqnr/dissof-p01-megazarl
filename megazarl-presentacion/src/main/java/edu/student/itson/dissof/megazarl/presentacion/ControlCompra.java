package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.carritocompras.ICarritoCompras;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.MontoMinimoEnvioGratuitoDTO;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import edu.student.itson.dissof.megazarl.dto.TiempoEstimadoPreparacionEnvioPedidoDTO;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IInformacionProducto;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IProductosVenta;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ISeleccionPaqueteria;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ICarrito;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class ControlCompra {

    private IProductosVenta productosVenta;
    private IInformacionProducto informacionProducto;
    private ISeleccionPaqueteria seleccionPaqueteria;
    private ICarrito carrito;
    private IMensaje mensaje;
    private IAdministradorClientes administradorClientes;
    private IAdministradorProductos administradorProductos;
    private ICarritoCompras carritoCompras;

    public ControlCompra() {
    }

    public void setVistas(IProductosVenta productosVenta, IInformacionProducto informacionProducto, ISeleccionPaqueteria seleccionPaqueteria,
            ICarrito carrito, IMensaje mensaje, IAdministradorProductos administradorProductos, ICarritoCompras carritoCompras, 
            IAdministradorClientes administradorClientes) {
        this.productosVenta = productosVenta;
        this.informacionProducto = informacionProducto;
        this.seleccionPaqueteria = seleccionPaqueteria;
        this.carrito = carrito;
        this.administradorProductos = administradorProductos;
        this.carritoCompras = carritoCompras;
        this.administradorClientes = administradorClientes;
        this.mensaje = mensaje;
    }

    public void iniciarCompra() {
        List<Map<String, Object>> listaInformacionProductosInicio = obtenerProductosVenta();
        productosVenta.actualizarBtnCarritoEncabezado();
        productosVenta.mostrarNombreApellidoClienteEncabezado();
        productosVenta.setProductosTodos(listaInformacionProductosInicio);
        productosVenta.hacerVisible(true);
    }

    public void mostrarProductosVenta(JFrame frameActual) {
        frameActual.dispose();
        List<Map<String, Object>> listaInformacionProductosInicio = obtenerProductosVenta();
        productosVenta.actualizarBtnCarritoEncabezado();
        productosVenta.mostrarNombreApellidoClienteEncabezado();
        productosVenta.setProductosTodos(listaInformacionProductosInicio);
        productosVenta.hacerVisible(true);
    }
    
    public void mostrarProductosBusqueda(String nombreProducto) {
        List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusqueda(nombreProducto);
        if(listaInformacionProductosBusqueda.isEmpty()){
            mensaje.setTexto("Búsqueda inválida");
            mensaje.setImagen("/lupaBusquedaInvalida.png");
            mensaje.setColorFondo(new Color(255, 191, 169));
            mensaje.hacerVisible(true);
        } else{
            productosVenta.actualizarBtnCarritoEncabezado();
            productosVenta.mostrarNombreApellidoClienteEncabezado();
            productosVenta.setProductosBusqueda(listaInformacionProductosBusqueda);
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
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", productoInicioDTO.getDireccionImagenProveedor());

            listaInformacionProductosBusqueda.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosBusqueda;
    }
    
    public void mostrarProductosBusqueda(String nombreProducto,String nombreVariedad) {
        List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusqueda(nombreProducto, nombreVariedad);
        productosVenta.actualizarBtnCarritoEncabezado();
        productosVenta.mostrarNombreApellidoClienteEncabezado();
        productosVenta.setProductosBusqueda(listaInformacionProductosBusqueda);
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
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", productoInicioDTO.getDireccionImagenProveedor());

            listaInformacionProductosBusqueda.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosBusqueda;
    }
    
    public void mostrarProductosBusqueda(String nombreProducto,String nombreVariedad, String nombreProveeedor) {
        List<Map<String, Object>> listaInformacionProductosBusqueda = obtenerProductosBusqueda(nombreProducto, nombreVariedad, nombreProveeedor);
        productosVenta.actualizarBtnCarritoEncabezado();
        productosVenta.mostrarNombreApellidoClienteEncabezado();
        productosVenta.setProductosBusqueda(listaInformacionProductosBusqueda);
        productosVenta.hacerVisible(true);
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
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", productoInicioDTO.getDireccionImagenProveedor());

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
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", productoInicioDTO.getDireccionImagenProveedor());

            listaInformacionProductosInicio.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosInicio;
    }

    public void mostrarInformacionProducto(Integer idProducto, JFrame frameActual) {
        Map<String, Object> mapaInformacionProducto = this.obtenerInformacionProducto(idProducto);
        informacionProducto.actualizarBtnCarritoEncabezado();
        informacionProducto.mostrarNombreApellidoClienteEncabezado();
        informacionProducto.setProducto(mapaInformacionProducto);
        frameActual.dispose();
        informacionProducto.hacerVisible(true);
    }

    public Map<String, Object> obtenerInformacionProducto(Integer idProducto) {

        InformacionProductoDTO informacionProductoDTO = administradorProductos.obtenerInformacionProducto(idProducto);

        Map<String, Object> mapaInformacionProducto = new HashMap<>();

        if (informacionProductoDTO != null) {
            mapaInformacionProducto.put("Id", informacionProductoDTO.getId());
            mapaInformacionProducto.put("Nombre", informacionProductoDTO.getNombre());
            mapaInformacionProducto.put("Variedad", informacionProductoDTO.getVariedad());
            mapaInformacionProducto.put("Descripcion", informacionProductoDTO.getDescripcion());
            mapaInformacionProducto.put("Precio", informacionProductoDTO.getPrecio());
            mapaInformacionProducto.put("MilesSemillas", informacionProductoDTO.getMilesSemillas());
            mapaInformacionProducto.put("NombreProveedor", informacionProductoDTO.getNombreProveedor());
            mapaInformacionProducto.put("DireccionImagenProducto", informacionProductoDTO.getDireccionImagenProducto());
            mapaInformacionProducto.put("DireccionImagenProveedor", informacionProductoDTO.getDireccionImagenProveedor());
        }

        return mapaInformacionProducto;

    }

    public void mostrarCarritoCompras(Integer idCliente, JFrame frameActual) {
        List<Map<String, Object>> listaInformacionProductosCarrito = this.obtenerInformacionProductosCarrito(idCliente);
        carrito.actualizarBtnCarritoEncabezado();
        carrito.mostrarNombreApellidoClienteEncabezado();
        carrito.setProductos(listaInformacionProductosCarrito);
        frameActual.dispose();
        carrito.hacerVisible(true);
    }

    public List<Map<String, Object>> obtenerInformacionProductosCarrito(Integer idCliente) {

        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = carritoCompras.obtenerProductos(idCliente);
        
        
        for(InformacionProductoCarritoDTO informacionProductoCarritoDTO: listaInformacionProductoCarritoDTO){
            informacionProductoCarritoDTO = administradorProductos.obtenerInformacionProductoCarrito(informacionProductoCarritoDTO);
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

    
    public void agregarProductoCarrito(Integer idCliente, Integer idProducto, int cantidad) {

        carritoCompras.agregarProducto(idCliente, idProducto, cantidad);
        
    }
    
    public void eliminarProductoCarrito(Integer idCliente, Integer idProducto, int cantidad) {

        carritoCompras.eliminarProducto(idCliente, idProducto, cantidad);
        
    }
    
    
    public Integer obtenerNumeroProductosCarrito(Integer idCliente){

        try {
            return carritoCompras.obtenerNumeroProductos(idCliente);
        } catch (ClienteNoExisteException ex) {
            // TODO: Modificar manejo de excepción
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }   
    }
    
    public Double[] obtenerInformacionMontoEnvioGratuito(){
        MontoMinimoEnvioGratuitoDTO montoMinimoEnvioGratuitoDTO = carritoCompras.obtenerInformacionMontoEnvioMinimoGratuito();
        Double[] informacionMontoEnvioGratuito = {montoMinimoEnvioGratuitoDTO.getMontoActual(), montoMinimoEnvioGratuitoDTO.getMontoMinimo()};
        return informacionMontoEnvioGratuito;
    }
    
    public String[] obtenerNombreApellidoCliente(Integer idCliente){
        try {
            NombreApellidoClienteDTO nombreApellidoClienteDTO = this.administradorClientes.obtenerNombreApellidoPaternoCliente(idCliente);
            String[] nombreApellidoCliente = {nombreApellidoClienteDTO.getNombresCliente(), nombreApellidoClienteDTO.getApellidoMaternoCliente()};
            return nombreApellidoCliente;
        } catch (ClienteNoExisteException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    } 

    public int[] obtenerRangoDiasFechaEstimadaPreparacion(Integer idCliente){
        TiempoEstimadoPreparacionEnvioPedidoDTO tiempoEstimadoPreparacionEnvioPedidoDTO = carritoCompras.obtenerTiempoEstimadoPreparacionProductos(idCliente);
        
        int[] rangoDiasEstimadoPreparacion = {tiempoEstimadoPreparacionEnvioPedidoDTO.getDiasLimiteInferior(), 
            tiempoEstimadoPreparacionEnvioPedidoDTO.getDiasLimiteSuperior()};
        
        return rangoDiasEstimadoPreparacion;
    }
    
    public void mostrarSeleccionPaqueteria(JFrame frameActual) {
        List<String> direccionesImagenesPaqueteria = this.obtenerPaqueterias();
        seleccionPaqueteria.setPaqueterias(direccionesImagenesPaqueteria);
        frameActual.dispose();
        seleccionPaqueteria.hacerVisible(true);
    }
    

    public List<String> obtenerPaqueterias() {
        // TODO: Obtener de subsistema
        return Arrays.asList("/dhl.png", "/fedex.png", "/pcp.png", "/ups.png", "/estafeta.png");
    }

    public void mostrarConfirmacionPedido(JFrame frameActual) {
        frameActual.dispose();
        mensaje.hacerVisible(true);
    }
}
