
package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;


public interface IAdministradorClientes {
    
    public abstract boolean validarIdCliente(Integer idCliente);
    
    public abstract NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer idCliente) throws ClienteNoExisteException;
    
    
}
