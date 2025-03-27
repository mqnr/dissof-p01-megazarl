package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.dto.DetallesDerivadosDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.DireccionEntradaDTO;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;

public interface IAdministradorClientes {

    public abstract boolean validarIdCliente(Integer idCliente);

    public abstract NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer idCliente);
    
    public abstract String obtenerCodigoPostalCliente(Integer idCliente);
    
    public abstract String obtenerCalleCliente(Integer idCliente);
    
    public abstract String obtenerNumeroCliente(Integer idCliente);
    
    public abstract String obtenerColoniaCliente(Integer idCliente);
    
    public abstract String obtenerCiudadCliente(Integer idCliente);
    
    public abstract String obtenerEstadoCliente(Integer idCliente);
    
    public abstract boolean actualizarDireccionCliente(DireccionEntradaDTO direccionEntradaDTO);
    
    public abstract DetallesDerivadosDireccionDTO obtenerDatosDireccionDerivado(String codigoPostal);
}
