package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.administradorproveedores.FAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigoPostalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDireccionEnvioActualizadaClienteDTONegocios;
import edu.student.itson.dissof.megazarl.negocios.FabricaSubsistemas;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Luis Rafael
 */
public class ControlRegistroProveedor<t> {
    
    private IVista registroProveedor;
    private IMensaje mensaje;
    
    private static final Logger LOG = Logger.getLogger(ControlCompra.class.getName());
    
    private Color COLOR_MENSAJE_ERROR = new Color(255, 195, 195);
    private Color COLOR_MENSAJE_EXITOSO = new Color(204, 255, 190);
    
    public void setVistas(
            IVista registroProveedor, 
            IMensaje mensaje){
        
        this.registroProveedor = registroProveedor;
        this.mensaje = mensaje;
    }
    
    public void iniciarRegistroProveedor(){
        
        registroProveedor.hacerVisible(true);
        registroProveedor.actualizarDatosEncabezado();
    }
    
    /**
     * metodo que guardar los datos de un proveedor para
     * ser registrado.
     * @param nombre objeto String que representa el nombre del proveedor
     * @param telefono objeto String que representa el nombre del proveedor
     * @param correoElectronico objeto String que representa el telefono del proveedor
     * @param direccionImagen objeto String que representa el logo del proveedor
     * @param codigoPostal objeto String que representa el codigo postal del proveedor
     * @param colonia objeto String que representa la colonia del proveedor
     * @param calle objeto String que representa la calle del proveedor
     * @param numero objeto String que representa el numero del proveedor
     */
    public void guardarDatosProveedor(String nombre, String telefono, String correoElectronico,
            String direccionImagen,String codigoPostal, String colonia, String calle, String numero){
        /**
        try{
            DireccionDTO direccionProveedor = new DireccionDTO( codigoPostal,colonia,calle, numero);
            ProveedorDTO proveedorDTO = new ProveedorDatosCompletosRelacionesDTO(
                    nombre, 
                    telefono, 
                    correoElectronico, 
                    direccionImagen,
                    new LinkedList<>(),
                    direccionProveedor); 
            IAdministradorProveedores  administradorProveedores = new FAdministradorProveedores();
            administradorProveedores.agregarProveedor(proveedorDTO);
            * */
            mostrarMensaje("Se ha registrado el proveedor correctamente", COLOR_MENSAJE_EXITOSO);
        /**
        } catch(ProveedorNoRegistradoException e){
            mostrarMensaje("No se pudo registrar el proveedor", COLOR_MENSAJE_ERROR);
        }
        * */
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
        
        InformacionDerivadaCPDireccionDTONegocios informacionDerivadaCPDireccionDTO;
        try {
            IAdministradorDirecciones administradorDirecciones = FabricaSubsistemas.obtenerAdministradorDirecciones();
            informacionDerivadaCPDireccionDTO = administradorDirecciones.obtenerDatosDireccionDerivados(new CodigoPostalDTONegocios(codigoPostal));

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
}
