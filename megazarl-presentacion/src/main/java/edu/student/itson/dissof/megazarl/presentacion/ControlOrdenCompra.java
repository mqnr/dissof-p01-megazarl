package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administrador.gerenteventas.IAdministradorGerenteVentas;
import edu.student.itson.dissof.megazarl.administrador.gerenteventas.excepciones.IdGerenteVentasInvalidoException;

import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.negocios.FabricaSubsistemas;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProductoInicioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionProveedorInicioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSucursalInicioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.Direccion;
import edu.student.itson.dissof.megazarl.objetosnegocio.Proveedor;

import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IOrdenCompra;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/**
 * ControlOrdenCompra.java
 * 
 * Clase de control de presentación del caso de uso realizar orden de compra.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class ControlOrdenCompra {

    private IMensaje mensaje;
    private IVista ordenCompra;
    
    private List<Map<String, Object>> listaProductosOfrecidos = new ArrayList<>();
    
    private static final Logger LOG = Logger.getLogger(ControlRegistroCliente.class.getName());
    
    private Color COLOR_MENSAJE_EXITOSO = new Color(204, 255, 190);
    private Color COLOR_MENSAJE_ERROR = new Color(255, 195, 195);
    private Color COLOR_MENSAJE_ADVERTENCIA = new Color(255, 253, 222);

    /**
     * 
     * @param mensaje
     * @param ordenCompra 
     */
    public void setVistas(IMensaje mensaje, IVista ordenCompra) {
        this.mensaje = mensaje;
        this.ordenCompra = ordenCompra;
    }

    /**
     * 
     */
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
            // Se establecen las sucursales obtenidas a la ventana para que las muestre, si las hay.
            ((IOrdenCompra)ordenCompra).setSucursalesEnvio(listaInformacionSucursales);
        } else{
            // Si no hay sucursales registradas, se ejecuta el método de la ventana de sucursales
            // que muestra un mensaje indicándolo.
            ((IOrdenCompra)ordenCompra).mostrarAvisoSinSucursalesDisponibles();
        }
        
        //
        ((IOrdenCompra)ordenCompra).setProductosOfrecidosBusqueda(new ArrayList<>());
        
        // Se hace visible la pantalla OrdenCompra
        ordenCompra.hacerVisible(true);
    }
    
    /**
     * 
     * @param nombreProductoOfrecido 
     */
    public void mostrarProductosOfrecidosBusquedaNombre(String nombreProductoOfrecido) {
        if (nombreProductoOfrecido.isBlank()) {
            ((IOrdenCompra)ordenCompra).setProductosOfrecidosBusqueda(new ArrayList<>());
        } else {
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
     * 
     * @param nombreProductoBuscado Objeto String que representa el nombre de producto a buscar.
     * 
     * @return {@literal List<Map<String, Object>>} Lista con los valores de los atributos
     * de los productos buscados.
     */
    public List<Map<String, Object>> obtenerProductosOfrecidosBusquedaNombre(String nombreProductoBuscado) {
        IAdministradorProductos administradorProductos = FabricaSubsistemas.obtenerAdministradorProductos();

        List<InformacionProductoInicioDTONegocios> listaProductoInicioDTO 
                = administradorProductos.obtenerProductosBusquedaNombreProducto(nombreProductoBuscado);

        List<Map<String, Object>> listaInformacionProductosBusqueda = new LinkedList<>();

        for (InformacionProductoInicioDTONegocios informacionProductoInicioDTO : listaProductoInicioDTO) {
            Map<String, Object> mapaInformacionProductoInicio = new HashMap<>();

            // Nombre del producto
            mapaInformacionProductoInicio.put("Nombre", informacionProductoInicioDTO.getNombreProducto());
            
            // Variedad del producto
            mapaInformacionProductoInicio.put("Variedad", informacionProductoInicioDTO.getVariedadProducto());

            // Obtener nombre del proveedor
            IdProveedorDTONegocios idProveedorDTO = new IdProveedorDTONegocios(informacionProductoInicioDTO.getIdProveedor());
            ProveedorDTONegocios proveedorDTO = Proveedor.recuperarPorId(idProveedorDTO);
            String nombreProveedor = (proveedorDTO != null) ? proveedorDTO.getNombre() : "Desconocido";

            // Añadir nombre del proveedor al mapa
            mapaInformacionProductoInicio.put("Proveedor", nombreProveedor);

            // Imagen del producto
            mapaInformacionProductoInicio.put("DireccionImagenProducto", informacionProductoInicioDTO.getDireccionImagenProducto());

            listaInformacionProductosBusqueda.add(mapaInformacionProductoInicio);
        }
        this.listaProductosOfrecidos = listaInformacionProductosBusqueda;
        return listaInformacionProductosBusqueda;
    }

    public void mostrarInformacionProducto(Long idProducto, IVista vistaActual) {
        
    }
    
    /**
     * Método que permite obtener el nombre o nombres y el apellido paterno del
     * Gerente de Ventas con el ID del parámetro.
     * 
     * @param idGerenteVentas Objeto IdEntidadGenerico que representa el ID del Gerente de Ventas del que 
     * se obtendrá su nombre o nombres y apellido paterno.
     * 
     * @return Arreglo String[] que contiene el nombre o nombres y el apellido paterno
     * del Gerente de ventas.
     */

    public String[] obtenerNombreApellidoGerenteVentas(IdEntidadGenericoNegocios idGerenteVentas){
        NombresApellidoGerenteVentasDTONegocios nombreApellidoGerenteVentasDTO;
        try {
            IAdministradorGerenteVentas administradorGerenteVentas = FabricaSubsistemas.obtenerAdministradorGerenteVentas();
            nombreApellidoGerenteVentasDTO = administradorGerenteVentas.obtenerNombresApellidoGerenteVentas(new IdGerenteVentasDTONegocios(idGerenteVentas));

            String[] nombreApellidoGerenteVentas = {nombreApellidoGerenteVentasDTO.getNombresGerenteVentas(), nombreApellidoGerenteVentasDTO.getApellidoPaternoGerenteVentas()};
            return nombreApellidoGerenteVentas;
            
        } catch (IdGerenteVentasInvalidoException ex) {
            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        
        return null;
    }
    
    /**
     * 
     * @return 
     */
    public List<Map<String, Object>> obtenerProveedores() { 
        IAdministradorProveedores administradorProveedores = FabricaSubsistemas.obtenerAdministradorProveedores();
        
        List<InformacionProveedorInicioDTONegocios> listaProveedorDTO = administradorProveedores.obtenerProveedores();

        List<Map<String, Object>> listaInformacionProveedoresInicio = new LinkedList<>();

        for (InformacionProveedorInicioDTONegocios informacionProveedorInicioDTO : listaProveedorDTO) {
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
    
    /**
     * 
     * @return 
     */
    public List<Map<String, Object>> obtenerSucursales(){
        IAdministradorSucursales administradorSucursales = FabricaSubsistemas.obtenerAdministradorSucursales();
        List<InformacionSucursalInicioDTONegocios> listaSucursalDTO = administradorSucursales.obtenerSucursales();

        List<Map<String, Object>> listaInformacionSucursalInicio = new LinkedList<>();

        for (InformacionSucursalInicioDTONegocios informacionSucursalInicioDTO : listaSucursalDTO) {
            Map<String, Object> mapaInformacionSucursalInicio = new HashMap<>();

            IdDireccionDTONegocios idDireccionDTO = new IdDireccionDTONegocios(informacionSucursalInicioDTO.getIdDireccionSucursal());

            DireccionDTONegocios direccion = Direccion.recuperarPorId(idDireccionDTO);

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
    
    public List<Map<String, Object>> obtenerProductosOfrecidosPorProveedor(String nombreProveedor) {
        return listaProductosOfrecidos.stream()
            .filter(p -> p.get("Proveedor").equals(nombreProveedor))
            .collect(Collectors.toList());
    }
    
    /**
     * 
     * @param idProveedor
     * @return 
     */
    public String obtenerDireccionImagenProveedor(IdEntidadGenericoNegocios idProveedor){
        String direccionImagenProveedor = null;
        try {
            IAdministradorProveedores administradorProveedores = FabricaSubsistemas.obtenerAdministradorProveedores();
            direccionImagenProveedor = administradorProveedores.obtenerDireccionImagenProveedor(new IdProveedorDTONegocios(idProveedor));
            
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
     * 
     * @param vistaActual Objeto Interface que representa el formulario actual
     * realizará la orden de compra al proveedor elegido con los productos seleccionados y sucursal de envío.
     */
    public void finalizarCasoUso(IVista vistaActual){
        vistaActual.cerrar();
        // El control se pasa al módulo principal.
    }
    
}
