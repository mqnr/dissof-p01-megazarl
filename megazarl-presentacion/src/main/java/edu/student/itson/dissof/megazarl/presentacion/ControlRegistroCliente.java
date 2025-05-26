
package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas.IAdministradorAuxiliaresVentas;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesCorreoElectronicoYaExisteException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesTelefonoNuevoClienteYaExisteException;
import edu.student.itson.dissof.megazarl.dto.negocios.AuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.negocios.FabricaSubsistemas;
import edu.student.itson.dissof.megazarl.objetosnegocio.AuxiliarVentas;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ControlRegistroCliente.java
 * 
 * Clase que representa la interfaz de usuario mostrada tanto a los clientes como
 * a los auxilaires de venta al registrar a un cliente.
 * 
 * @author Manuel Romo López
 * 
 */

public class ControlRegistroCliente {
    
    private IMensaje mensaje;
    private IVista registroCliente;
    private boolean usuarioEsAuxiliarVentas;

    /**
     * Consutructor de la clase
     * @param usuarioEsAuxiliarVentas Valor booleano que determina si quien registra
     * al cliente es un nuevo usuario o un auxiliar de ventas.
     */
    public ControlRegistroCliente(boolean usuarioEsAuxiliarVentas) {
        this.usuarioEsAuxiliarVentas = usuarioEsAuxiliarVentas;
    }
    
    private final String MENSAJE_REGISTRO_EXITOSO_CLIENTE = "Registro exitoso";
    private final String MENSAJE_REGISTRO_EXITOSO_AUXILIAR_VENTAS = "El cliente se ha registrado con éxito";
    private final String MENSAJE_NUMERO_TELEFONO_EN_USO = "El número de teléfono está en uso por otro cliente";
    private final String MENSAJE_CORREO_ELECTRONICO_EN_USO = "El corre electrónico está en uso por otro cliente";
    
    private final String MENSAJE_ERROR_INICIO_CASO_USO = "Ha ocurrido un error al acceder a este apartado.";
    
    private Color COLOR_MENSAJE_EXITOSO = new Color(204, 255, 190);
    private Color COLOR_MENSAJE_ERROR = new Color(255, 195, 195);
    private Color COLOR_MENSAJE_ADVERTENCIA = new Color(255, 253, 222);
    
    private static final Logger LOG = Logger.getLogger(ControlRegistroCliente.class.getName());

    /**
     * Método que permite asignar las vistas o interfaces de usuario al control de este
     * caso de uso.
     * @param mensaje Objeto que implementa la interfaz {@link IMensaje}, representa una
     * ventana para mostrar información al usuario.
     * @param registroCliente Objeto que implementa la interfaz {@link IVista} que
     * representa la ventana en la que se ingresan los datos del nuevo cliente.
     */
    public void setVistas(
            IMensaje mensaje,
            IVista registroCliente) {
        this.mensaje = mensaje;
        this.registroCliente = registroCliente;
    }
    
    /**
     * Método que inicia la ejecución de este Caso de Uso.
     * @param usuarioAuxiliarVentas Valor booleano que determina si quien registra
     * al cliente es él mismo o un auxiliar de ventas de la empresa.
     */
    public void iniciarRegistroCliente(boolean usuarioAuxiliarVentas){
        
        if(usuarioAuxiliarVentas){
            
            AuxiliarVentasDTONegocios auxiliarVentas1 = new AuxiliarVentasDTONegocios(
                    "María", 
                    "González",
                    "Juárez");
            
            try {
                AuxiliarVentas.agregar(auxiliarVentas1);
            } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | 
                    ValorParametroInvalidoNegocioException | ParametroNuloNegocioException ex) {
                mostrarMensaje(MENSAJE_ERROR_INICIO_CASO_USO, COLOR_MENSAJE_ERROR);
                LOG.log(Level.SEVERE, ex.getMessage());
            }
         
            
        }
        registroCliente.hacerVisible(true);
        registroCliente.actualizarDatosEncabezado();
        
        
    }
    
    /**
     * Método que permite almacenar los datos del nuevo cliente a registrar.
     * @param datosCliente Objeto {@literal List<Object>} que representa los datos ingresados
     * del cliente.
     */
    public void registrarCliente(
            List<Object> datosCliente){
        
        String nombres = (String)datosCliente.get(0);
        String apellidoPaterno = (String)datosCliente.get(1);
        String apellidoMaterno = (String)datosCliente.get(2);
        String telefono = (String)datosCliente.get(3);
        String correoElectronico = (String)datosCliente.get(4);
        
        ClienteDTONegocios nuevoCliente = new ClienteDTONegocios(
                nombres, 
                apellidoPaterno,
                apellidoMaterno, 
                telefono, 
                correoElectronico, 
                null);
        
        IAdministradorClientes administradorClientes = FabricaSubsistemas.obtenerAdministradorClientes();
        
        if(usuarioEsAuxiliarVentas){
            mostrarMensaje(MENSAJE_REGISTRO_EXITOSO_AUXILIAR_VENTAS, COLOR_MENSAJE_EXITOSO);
        } else{
            mostrarMensaje(MENSAJE_REGISTRO_EXITOSO_CLIENTE, COLOR_MENSAJE_EXITOSO);
        }
       
        try {
            administradorClientes.registrarCliente(nuevoCliente);
        } catch (ClientesTelefonoNuevoClienteYaExisteException ex) {
            
            mostrarMensaje(MENSAJE_NUMERO_TELEFONO_EN_USO, COLOR_MENSAJE_ADVERTENCIA);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (ClientesCorreoElectronicoYaExisteException ex) {
            mostrarMensaje(MENSAJE_CORREO_ELECTRONICO_EN_USO, COLOR_MENSAJE_ADVERTENCIA);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (ClientesPersistenciaException ex) {
            Logger.getLogger(ControlRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    /**
     * Método que permite obtener el nombre y el apellido del auxiliar de ventas que 
     * registra al cliente, en caso de que sea él quien lo haga.
     * @param idAuxiliarVentas Objeto Object que representa el identificador del
     * auxiliar de ventas.
     * @return Objeto {@literal String[]} que representa un arreglo con el nombre
     * y el apellido del auxiliar de ventas.
     */
    public String[] obtenerNombreApellidoAuxiliarVentas(Object idAuxiliarVentas){
        
        IAdministradorAuxiliaresVentas administradorAuxiliaresVentas = FabricaSubsistemas.obtenerAdministradorAuxiliaresVentas();
        
        NombresApellidoAuxiliarVentasDTONegocios nombresApellidoAuxiliarVentasDTO
                = administradorAuxiliaresVentas.obtenerNombreApellidoAuxiliarVentas(new IdAuxiliarVentasDTONegocios((new IdEntidadGenericoNegocios(idAuxiliarVentas))));
        
        String[] arregloNombresApellidos = {
            nombresApellidoAuxiliarVentasDTO.getNombreAuxiliarVentas(),
            nombresApellidoAuxiliarVentasDTO.getApellidoPaternoAuxiliarVentas()};
        
        return arregloNombresApellidos;
        
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
    
    public void finalizarCasoUso(IVista vistaActual){
        vistaActual.cerrar();
    }    
    
}
