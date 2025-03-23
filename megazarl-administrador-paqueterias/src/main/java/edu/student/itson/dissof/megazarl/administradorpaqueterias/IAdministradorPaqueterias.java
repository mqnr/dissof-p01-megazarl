package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import java.util.List;

public interface IAdministradorPaqueterias {

    public abstract List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias();
}
