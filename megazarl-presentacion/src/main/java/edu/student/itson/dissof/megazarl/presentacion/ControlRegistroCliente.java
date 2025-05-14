
package edu.student.itson.dissof.megazarl.presentacion;

import edu.student.itson.dissof.megazarl.administrador.auxiliares.ventas.IAdministradorAuxiliaresVentas;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesCorreoElectronicoYaExisteException;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesTelefonoNuevoClienteYaExisteException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.AuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdAuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.NombresApellidoAuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.negocio.FabricaSubsistemas;
import edu.student.itson.dissof.megazarl.objetosnegocio.AuxiliarVentas;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IMensaje;
import edu.student.itson.dissof.megazarl.presentacion.interfaces.IVista;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ControlRegistroCliente {
    
    private IMensaje mensaje;
    private IVista registroCliente;
    private boolean usuarioEsAuxiliarVentas;

    public ControlRegistroCliente(boolean usuarioEsAuxiliarVentas) {
        this.usuarioEsAuxiliarVentas = usuarioEsAuxiliarVentas;
    }
    
    private static final Logger LOG = Logger.getLogger(ControlRegistroCliente.class.getName());
    private Color COLOR_MENSAJE_EXITOSO = new Color(204, 255, 190);
    private Color COLOR_MENSAJE_ERROR = new Color(255, 195, 195);
    private Color COLOR_MENSAJE_ADVERTENCIA = new Color(255, 253, 222);

    public void setVistas(
            IMensaje mensaje,
            IVista registroCliente) {
        this.mensaje = mensaje;
        this.registroCliente = registroCliente;
    }
    
    public void iniciarRegistroCliente(boolean usuarioAuxiliarVentas){
        
        if(usuarioAuxiliarVentas){
            
            AuxiliarVentasDTO auxiliarVentas1 = new AuxiliarVentasDTO(
                    "María", 
                    "González",
                    "Juárez");
            
            AuxiliarVentas.agregar(auxiliarVentas1);
         
            
        }
        registroCliente.hacerVisible(true);
        registroCliente.actualizarDatosEncabezado();
        
        
    }
    
    public void registrarCliente(
            List<Object> datosCliente,
            IVista vistaActual){
        
        String nombres = (String)datosCliente.get(0);
        String apellidoPaterno = (String)datosCliente.get(1);
        String apellidoMaterno = (String)datosCliente.get(2);
        String telefono = (String)datosCliente.get(3);
        String correoElectronico = (String)datosCliente.get(4);
        
        ClienteDTO nuevoCliente = new ClienteDTO(nombres, apellidoPaterno, apellidoMaterno, telefono, correoElectronico);
        
        IAdministradorClientes administradorClientes = FabricaSubsistemas.obtenerAdministradorClientes();
        
        if(usuarioEsAuxiliarVentas){
            mostrarMensaje("Registro exitoso", COLOR_MENSAJE_EXITOSO);
        } else{
            mostrarMensaje("El cliente ha sido registrado con éxito", COLOR_MENSAJE_EXITOSO);
        }
       
        try {
            administradorClientes.registrarCliente(nuevoCliente);
        } catch (ClientesTelefonoNuevoClienteYaExisteException ex) {
            
            mostrarMensaje("El número de teléfono está en uso por otro cliente", COLOR_MENSAJE_ADVERTENCIA);
            LOG.log(Level.SEVERE, ex.getMessage());
        } catch (ClientesCorreoElectronicoYaExisteException ex) {
            mostrarMensaje("Ya existe un cliente con el correo electrónico ingresado", COLOR_MENSAJE_ADVERTENCIA);
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        
        
        
    }
    
    public String[] obtenerNombreApellidoAuxiliarVentas(Long idAuxiliarVentas){
        
        IAdministradorAuxiliaresVentas administradorAuxiliaresVentas = FabricaSubsistemas.obtenerAdministradorAuxiliaresVentas();
        
        NombresApellidoAuxiliarVentasDTO nombresApellidoAuxiliarVentasDTO
                = administradorAuxiliaresVentas.obtenerNombreApellidoAuxiliarVentas(new IdAuxiliarVentasDTO(idAuxiliarVentas));
        
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
