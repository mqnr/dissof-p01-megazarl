package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.dto.DireccionEntradaDTO;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;

public interface IAdministradorClientes {

    boolean validarIdCliente(Integer idCliente);

    public abstract NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer idCliente);
    
    public abstract String obtenerCodigoPostalCliente(Integer idCliente);
    
    public abstract String obtenerCalleCliente(Integer idCliente);
    
    public abstract String obtenerNumeroCliente(Integer idCliente);
    
    public String obtenerColoniaCliente(Integer idCliente);
    
    public String obtenerCiudadCliente(Integer idCliente);
    
    public String obtenerEstadoCliente(Integer idCliente);
    
    public abstract boolean registrarCliente(DireccionEntradaDTO direccionEntradaDTO);
}
