package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.dto.DireccionClientePesoTiempoProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;
import java.util.List;

public interface IAdministradorPaqueterias {

    public abstract List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias();
    
    public abstract Float obtenerCostoEnvioProducto(DireccionClientePesoTiempoProductoInventarioDTO direccionClienteProductosEnvioDTO) 
            throws PaqueteriasIdPaqueteriaInvalidoException;
    
    public abstract boolean validarPaqueteria(Integer idPaqueteria);
    
    public abstract Paqueteria obtenerPaqueteria(Integer idPaqueteria);
}
