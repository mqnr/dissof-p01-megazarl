package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.util.List;
import java.util.Map;

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

    public void iniciarOrdenCompra(){
        
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
     * @param idCliente Objeto Integer que representa el ID del Cliente del que 
     * se obtendrá su nombre o nombres y apellido paterno.
     * @return Objeto String[] que contiene el nombre o nombres y el apellido paterno
     * del Cliente.
     */
    public String[] obtenerNombreApellidoGerenteVentas(Long idGerenteVentas) {

//        NombresApellidoGerenteVentasDTO nombreApellidoGerenteVentasDTO;
//        try {
//            IAdministradorClientes administradorClientes = FabricaSubsistemas.obtenerAdministradorClientes();
//            nombreApellidoClienteDTO = administradorClientes.obtenerNombresApellidoCliente(new IdClienteDTO(idCliente));
//            String[] nombreApellidoCliente = {nombreApellidoClienteDTO.getNombresCliente(), nombreApellidoClienteDTO.getApellidoMaternoCliente()};
//            return nombreApellidoCliente;
//            
//        } catch (ClientesIdClienteInvalidoException ex) {
//            mostrarMensaje("Ha ocurrido un error con la sesión de usuario", COLOR_MENSAJE_ERROR);
//            LOG.log(Level.SEVERE, ex.getMessage());
//        }
        return null;
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
