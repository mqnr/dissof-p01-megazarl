package edu.student.itson.dissof.megazarl.administradorclientes;

import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClienteNoExisteException;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Cliente;

public class FAdministradorClientes implements IAdministradorClientes {



    @Override
    public boolean validarIdCliente(Integer idCliente) {
        return true;
    }

    @Override
    public NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer id) throws ClienteNoExisteException {
//        Cliente cliente = validarIdCliente(id);
        
//        if (cliente == null) {
//            throw new ClienteNoExisteException();
//        }

        return new NombreApellidoClienteDTO(
                "Juan",
                "Pérez"
        );
    }
}
