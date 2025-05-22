package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.administrador.gerenteventas.IAdministradorGerenteVentas;
import edu.student.itson.dissof.megazarl.administrador.gerenteventas.excepciones.IdGerenteVentasInvalidoException;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import edu.student.itson.dissof.megazarl.negocio.FabricaSubsistemas;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ControlOrdenCompra.java
 * 
 * Clase de control de presentación del caso de uso realizar orden de compra
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class ControlOrdenCompra {

    private IMensaje mensaje;
    private IVista ordenCompra;
    private IVista proveedores;
    
    private static final Logger LOG = Logger.getLogger(ControlRegistroCliente.class.getName());
    private Color COLOR_MENSAJE_EXITOSO = new Color(204, 255, 190);
    private Color COLOR_MENSAJE_ERROR = new Color(255, 195, 195);
    private Color COLOR_MENSAJE_ADVERTENCIA = new Color(255, 253, 222);

    public void setVistas(IMensaje mensaje, IVista ordenCompra) {
        this.mensaje = mensaje;
        this.ordenCompra = ordenCompra;
        this.proveedores = proveedores;
    }

    public void iniciarOrdenCompra(){
//        // Se obtiene la lista de mapas que contienen la información de cada producto a mostrar.
//        List<Map<String, Object>> listaInformacionProveedores = obtenerProveedores();
//        // Se actualiza el encabezado de la pantalla a mostrar.
//        proveedores.actualizarDatosEncabezado();
//        
//        if(!listaInformacionProveedores.isEmpty()){    
//            // Se establecen los productos obtenidos a la ventana para que los muestre, si los hay.
//            ((IProveedor)proveedores).setProveedoresTodos(listaInformacionProveedores);
// 
//        } else{
//            // Si no hay existencias de productos, se ejecuta el metodo de la ventana de productos
//            // en venta que muestra un mensaje indicándolo.
//            ((IProveedor)proveedores).mostrarAvisoSinProveedoresDisponibles();
//        }
//
//        ordenCompra.actualizarDatosEncabezado();
//        ordenCompra.hacerVisible(true);
    }
    
    public void mostrarProductosOfrecidosBusquedaNombre(String nombreProductoOfrecido) {

    }
    
    /**
     * Método que permite obtener la información de productos ofrecidos por proveedores a partir de su nombre.
     * @param nombreProductoBuscado Objeto String que representa el nombre de producto a buscar.
     * @return {@literal List<Map<String, Object>>} Lista con los valores de los atributos
     * de los productos buscados.
     */
    public List<Map<String, Object>> obtenerProductosOfrecidosBusquedaNombre(String nombreProductoBuscado) {
        
        return null;
    }

    public void mostrarInformacionProducto(Long idProducto, IVista vistaActual) {
        
    }
    
    /**
     * Método que permite obtener el nombre o nombres y el apellido paterno del
     * Cliente con el ID del parámetro.
     * @param idGerenteVentas Objeto Integer que representa el ID del Gerente de Ventas del que 
     * se obtendrá su nombre o nombres y apellido paterno.
     * @return Objeto String[] que contiene el nombre o nombres y el apellido paterno
     * del Cliente.
     */
    public String[] obtenerNombreApellidoGerenteVentas(Long idGerenteVentas){
        NombresApellidoGerenteVentasDTO nombreApellidoGerenteVentasDTO;
        try {
            IAdministradorGerenteVentas administradorGerenteVentas = FabricaSubsistemas.obtenerAdministradorGerenteVentas();
            nombreApellidoGerenteVentasDTO = administradorGerenteVentas.obtenerNombresApellidoGerenteVentas(new IdGerenteVentasDTO(new IdEntidadGenerico(idGerenteVentas)));
            String[] nombreApellidoGerenteVentas = {nombreApellidoGerenteVentasDTO.getNombresGerenteVentas(), nombreApellidoGerenteVentasDTO.getApellidoPaternoGerenteVentas()};
            return nombreApellidoGerenteVentas;
            
        } catch (IdGerenteVentasInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        
        return null;
    }
    
//    public List<Map<String, Object>> obtenerProveedores() {
//        
//        IAdministradorProveedores administradorProveedores = FabricaSubsistemas.obtenerAdministradorProveedores();
//        
//        List<InformacionProductoInicioDTO> listaProductoInicioDTO = administradorProveedores.obtenerProductosVenta();
//
//        List<Map<String, Object>> listaInformacionProductosInicio = new LinkedList<>();
//
//        for (InformacionProductoInicioDTO informacionProductoInicioDTO : listaProductoInicioDTO) {
//            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();
//            mapaInformacionProductoInicio.put("Id", informacionProductoInicioDTO.getIdProducto());
//            mapaInformacionProductoInicio.put("Nombre", informacionProductoInicioDTO.getNombreProducto());
//            mapaInformacionProductoInicio.put("Variedad", informacionProductoInicioDTO.getVariedadProducto());
//            mapaInformacionProductoInicio.put("Precio", informacionProductoInicioDTO.getPrecioProducto());
//            mapaInformacionProductoInicio.put("MilesSemillas", informacionProductoInicioDTO.getMilesSemillasProducto());
//            mapaInformacionProductoInicio.put("DireccionImagenProducto", informacionProductoInicioDTO.getDireccionImagenProducto());
//            
//            String direccionImagenProveedor = obtenerDireccionImagenProveedor(informacionProductoInicioDTO.getIdProveedor());
//            informacionProductoInicioDTO.setDireccionImagenProveedor(direccionImagenProveedor);
//            
//            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);
//
//            listaInformacionProductosInicio.add(mapaInformacionProductoInicio);
//            
//            
//        }
//
//        return listaInformacionProductosInicio;
//    }
    
    public void mostrarMensaje(String mensajeMostrar, Color color){
        ((IMensaje)mensaje).setTexto(mensajeMostrar);
        ((IMensaje)mensaje).setColorFondo(color);
        mensaje.mostrarMensaje();
    }
    
    /**
     * Método que permite finalizar este Caso de Uso.
     * @param vistaActual Objeto Interface que representa el formulario actual
     * realizará la orden de compra al proveedor elegido con los productos seleccionados y sucursal de envío.
     */
    public void finalizarCasoUso(IVista vistaActual){
        vistaActual.cerrar();
        // El control se pasa al módulo principal.
    }
    
}
