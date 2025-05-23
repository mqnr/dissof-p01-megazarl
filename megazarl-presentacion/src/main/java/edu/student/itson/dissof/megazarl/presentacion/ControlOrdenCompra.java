package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administrador.gerenteventas.IAdministradorGerenteVentas;
import edu.student.itson.dissof.megazarl.administrador.gerenteventas.excepciones.IdGerenteVentasInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoInicioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProveedorInicioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSucursalInicioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import edu.student.itson.dissof.megazarl.negocio.FabricaSubsistemas;
import edu.student.itson.dissof.megazarl.objetosnegocio.Direccion;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IOrdenCompra;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
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
    
    private static final Logger LOG = Logger.getLogger(ControlRegistroCliente.class.getName());
    private Color COLOR_MENSAJE_EXITOSO = new Color(204, 255, 190);
    private Color COLOR_MENSAJE_ERROR = new Color(255, 195, 195);
    private Color COLOR_MENSAJE_ADVERTENCIA = new Color(255, 253, 222);

    public void setVistas(IMensaje mensaje, IVista ordenCompra) {
        this.mensaje = mensaje;
        this.ordenCompra = ordenCompra;
    }

    public void iniciarOrdenCompra(){
        // Se obtiene la lista de mapas que contienen la información de cada proveedor a mostrar.
        List<Map<String, Object>> listaInformacionProveedores = obtenerProveedores();
        // Se actualiza el encabezado de la pantalla a mostrar.
        ordenCompra.actualizarDatosEncabezado();
        
        if(!listaInformacionProveedores.isEmpty()){    
            // Se establecen los proveedores obtenidos a la ventana para que los muestre, si los hay.
            ((IOrdenCompra)ordenCompra).setProveedoresTodos(listaInformacionProveedores);
        } else{
            // Si no hay proveedores registrados, se ejecuta el metodo de la ventana de proveedores
            // en venta que muestra un mensaje indicándolo.
            ((IOrdenCompra)ordenCompra).mostrarAvisoSinProveedoresDisponibles();
        }

        // Se obtiene la lista de mapas que contienen la información de cada sicirsal a mostrar.
        List<Map<String, Object>> listaInformacionSucursales = obtenerSucursales();

        if(!listaInformacionSucursales.isEmpty()){
            ((IOrdenCompra)ordenCompra).setSucursalesEnvio(listaInformacionSucursales);
        } else{
            ((IOrdenCompra)ordenCompra).mostrarAvisoSinSucursalesDisponibles();
        }
        
        ordenCompra.hacerVisible(true);
    }
    
    public void mostrarProductosOfrecidosBusquedaNombre(String nombreProductoOfrecido) {
        if(nombreProductoOfrecido.isBlank()){
            iniciarOrdenCompra();
        } else{
            // Se obtiene la lista de mapas que contienen la información de los productos a mostrar.
            List<Map<String, Object>> listaInformacionProductosOfrecidosBusqueda = obtenerProductosOfrecidosBusquedaNombre(nombreProductoOfrecido);
            if (listaInformacionProductosOfrecidosBusqueda.isEmpty()) {
                mostrarMensaje("No se encontraron coincidencias de nombres con: \"" + nombreProductoOfrecido + "\"", COLOR_MENSAJE_ADVERTENCIA);
            } else {
                ((IOrdenCompra)ordenCompra).setProductosOfrecidosBusqueda(listaInformacionProductosOfrecidosBusqueda);
                ordenCompra.hacerVisible(true);
            }
        }
    }
    
    /**
     * Método que permite obtener la información de productos ofrecidos por proveedores a partir de su nombre.
     * @param nombreProductoBuscado Objeto String que representa el nombre de producto a buscar.
     * @return {@literal List<Map<String, Object>>} Lista con los valores de los atributos
     * de los productos buscados.
     */
    public List<Map<String, Object>> obtenerProductosOfrecidosBusquedaNombre(String nombreProductoBuscado) {
        IAdministradorProductos administradorProductos = FabricaSubsistemas.obtenerAdministradorProductos();

        List<InformacionProductoInicioDTO> listaProductoInicioDTO 
                = administradorProductos.obtenerProductosBusquedaNombreProducto(nombreProductoBuscado);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (InformacionProductoInicioDTO informacionProductoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();

            // Nombre del producto
            mapaInformacionProductoInicio.put("Nombre", informacionProductoInicioDTO.getNombreProducto());

            // Obtener nombre del proveedor
            IdProveedorDTO idProveedorDTO = new IdProveedorDTO(informacionProductoInicioDTO.getIdProveedor());
            ProveedorDTO proveedorDTO = Proveedor.recuperarPorId(idProveedorDTO);
            String nombreProveedor = (proveedorDTO != null) ? proveedorDTO.getNombre() : "Desconocido";

            // Añadir nombre del proveedor al mapa
            mapaInformacionProductoInicio.put("Proveedor", nombreProveedor);

            // Imagen del producto
            mapaInformacionProductoInicio.put("DireccionImagenProducto", informacionProductoInicioDTO.getDireccionImagenProducto());

            // Imagen del proveedor
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(informacionProductoInicioDTO.getIdProveedor());
            informacionProductoInicioDTO.setDireccionImagenProveedor(direccionImagenProveedor);
            mapaInformacionProductoInicio.put("DireccionImagenProveedor", direccionImagenProveedor);

            listaInformacionProductosBusqueda.add(mapaInformacionProductoInicio);
        }

        return listaInformacionProductosBusqueda;
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
    public String[] obtenerNombreApellidoGerenteVentas(IdEntidadGenerico idGerenteVentas){
        NombresApellidoGerenteVentasDTO nombreApellidoGerenteVentasDTO;
        try {
            IAdministradorGerenteVentas administradorGerenteVentas = FabricaSubsistemas.obtenerAdministradorGerenteVentas();
            nombreApellidoGerenteVentasDTO = administradorGerenteVentas.obtenerNombresApellidoGerenteVentas(new IdGerenteVentasDTO(idGerenteVentas));
            String[] nombreApellidoGerenteVentas = {nombreApellidoGerenteVentasDTO.getNombresGerenteVentas(), nombreApellidoGerenteVentasDTO.getApellidoPaternoGerenteVentas()};
            return nombreApellidoGerenteVentas;
            
        } catch (IdGerenteVentasInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        
        return null;
    }
    
    public List<Map<String, Object>> obtenerProveedores() { 
        IAdministradorProveedores administradorProveedores = FabricaSubsistemas.obtenerAdministradorProveedores();
        
        List<InformacionProveedorInicioDTO> listaProveedorDTO = administradorProveedores.obtenerProveedores();

        List<Map<String, Object>> listaInformacionProveedoresInicio = new LinkedList<>();

        for (InformacionProveedorInicioDTO informacionProveedorInicioDTO : listaProveedorDTO) {
            Map<String, Object> mapaInformacionProveedorInicio = new HashMap<>();
            mapaInformacionProveedorInicio.put("Nombre", informacionProveedorInicioDTO.getNombreProveedor());
            mapaInformacionProveedorInicio.put("Telefono", informacionProveedorInicioDTO.getTelefonoProveedor());
            mapaInformacionProveedorInicio.put("Correo", informacionProveedorInicioDTO.getCorreoElectronicoProveedor());
            mapaInformacionProveedorInicio.put("DireccionImagenProveedor", informacionProveedorInicioDTO.getDireccionImagenProveedor());
            
            String direccionImagenProveedor = obtenerDireccionImagenProveedor(informacionProveedorInicioDTO.getIdProveedor());
            informacionProveedorInicioDTO.setDireccionImagenProveedor(direccionImagenProveedor);
            
            mapaInformacionProveedorInicio.put("DireccionImagenProveedor", direccionImagenProveedor);

            listaInformacionProveedoresInicio.add(mapaInformacionProveedorInicio);
        }

        return listaInformacionProveedoresInicio;
    }
    
    public List<Map<String, Object>> obtenerSucursales(){
        IAdministradorSucursales administradorSucursales = FabricaSubsistemas.obtenerAdministradorSucursales();
        List<InformacionSucursalInicioDTO> listaSucursalDTO = administradorSucursales.obtenerSucursales();

        List<Map<String, Object>> listaInformacionSucursalInicio = new LinkedList<>();

        for (InformacionSucursalInicioDTO informacionSucursalInicioDTO : listaSucursalDTO) {
            Map<String, Object> mapaInformacionSucursalInicio = new HashMap<>();

            IdDireccionDTO idDireccionDTO = new IdDireccionDTO(informacionSucursalInicioDTO.getIdDireccionSucursal());

            DireccionDTO direccion = Direccion.recuperarPorId(idDireccionDTO);

            if (direccion != null) {
                mapaInformacionSucursalInicio.put("Calle", direccion.getCalle());
                mapaInformacionSucursalInicio.put("Numero", direccion.getNumero());
                mapaInformacionSucursalInicio.put("CodigoPostal", direccion.getCodigoPostal());
                mapaInformacionSucursalInicio.put("Ciudad", direccion.getCiudad());
                mapaInformacionSucursalInicio.put("Estado", direccion.getEstado());
                String textoMatriz = Boolean.TRUE.equals(informacionSucursalInicioDTO.getEsMatrizSucursal()) ? "(Es Matriz)" : "";
                mapaInformacionSucursalInicio.put("EsMatriz", textoMatriz);
            }

            listaInformacionSucursalInicio.add(mapaInformacionSucursalInicio);
        }

        return listaInformacionSucursalInicio;
    }
    
    public String obtenerDireccionImagenProveedor(IdEntidadGenerico idProveedor){
        String direccionImagenProveedor = null;
        try {
            IAdministradorProveedores administradorProveedores = FabricaSubsistemas.obtenerAdministradorProveedores();
            direccionImagenProveedor = administradorProveedores.obtenerDireccionImagenProveedor(new IdProveedorDTO(idProveedor));
            
        } catch (ProveedoresIdProveedorInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error al obtener la información del proveedor.", COLOR_MENSAJE_ERROR);
        }   
        return direccionImagenProveedor;
    }
    
    public void mostrarMensaje(String mensajeMostrar, Color color){
        ((IMensaje)mensaje).setTexto(mensajeMostrar);
        ((IMensaje)mensaje).setColorFondo(color);
        mensaje.mostrarMensaje();
    }
    
    public void realizarOrdenCompra(){
        
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
