package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionProductoDTO;
import edu.student.itson.dissof.megazarl.dto.ProductoInicioDTO;
import edu.student.itson.dissof.megazarl.negocio.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.negocio.carritocompras.ICarritoCompras;
import edu.student.itson.dissof.megazarl.negocio.modelos.Producto;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ICarrito;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IInformacionProducto;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IProductosVenta;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.ISeleccionPaqueteria;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;

public class ControlCompra {

    private IProductosVenta productosVenta;
    private IInformacionProducto informacionProducto;
    private ISeleccionPaqueteria seleccionPaqueteria;
    private ICarrito carrito;
    private IMensaje mensaje;

    private IAdministradorProductos administradorProductos;
    private ICarritoCompras carritoCompras;

    public ControlCompra() {
    }

    public void setVistas(IProductosVenta productosVenta, IInformacionProducto informacionProducto, ISeleccionPaqueteria seleccionPaqueteria,
            ICarrito carrito, IMensaje mensaje, IAdministradorProductos administradorProductos, ICarritoCompras carritoCompras) {
        this.productosVenta = productosVenta;
        this.informacionProducto = informacionProducto;
        this.seleccionPaqueteria = seleccionPaqueteria;
        this.carrito = carrito;
        this.administradorProductos = administradorProductos;
        this.carritoCompras = carritoCompras;
        this.mensaje = mensaje;
    }

    public void iniciarCompra() {
        List<Map<String, Object>> listaInformacionProductosInicio = obtenerProductosVenta();
        productosVenta.setProductos(listaInformacionProductosInicio);
        productosVenta.hacerVisible(true);
    }

    public void mostrarProductosVenta(JFrame frameActual) {
        frameActual.dispose();
        List<Map<String, Object>> listaInformacionProductosInicio = obtenerProductosVenta();
        productosVenta.setProductos(listaInformacionProductosInicio);
        productosVenta.hacerVisible(true);
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
        carrito.setProductos(listaInformacionProductosCarrito);
        carrito.hacerVisible(true);
        frameActual.dispose();
    }

    public List<Map<String, Object>> obtenerInformacionProductosCarrito(Integer idCliente) {
        List<InformacionProductoCarritoDTO> listaInformacionProductoCarritoDTO = carritoCompras.obtenerProductos(idCliente);

        List<Map<String, Object>> listaInformacionProductosCarrito = new LinkedList<>();

        for (InformacionProductoCarritoDTO informacionProductoCarrito : listaInformacionProductoCarritoDTO) {
            Map<String, Object> mapaInformacionProductoCarrito = new HashMap<>();
            mapaInformacionProductoCarrito.put("Id", informacionProductoCarrito.getId());
            mapaInformacionProductoCarrito.put("Nombre", informacionProductoCarrito.getNombre());
            mapaInformacionProductoCarrito.put("Variedad", informacionProductoCarrito.getVariedad());
            mapaInformacionProductoCarrito.put("Precio", informacionProductoCarrito.getPrecio());
            mapaInformacionProductoCarrito.put("MilesSemillas", informacionProductoCarrito.getMilesSemillas());
            mapaInformacionProductoCarrito.put("DireccionImagenProducto", informacionProductoCarrito.getDireccionImagenProducto());

            listaInformacionProductosCarrito.add(mapaInformacionProductoCarrito);
        }

        return listaInformacionProductosCarrito;
    }

    public void agregarProductoCarrito(Integer idCliente, Integer idProducto) {
        Producto productoAgregar = administradorProductos.obtenerProductoPorId(idProducto);
        carritoCompras.agregarProducto(idCliente, productoAgregar);
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
