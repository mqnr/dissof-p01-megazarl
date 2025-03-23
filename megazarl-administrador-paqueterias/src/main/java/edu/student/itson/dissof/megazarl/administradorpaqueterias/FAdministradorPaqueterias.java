package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;
import java.util.LinkedList;
import java.util.List;

public class FAdministradorPaqueterias implements IAdministradorPaqueterias {

    private List<Paqueteria> paqueterias;

    @Override
    public List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias() {
        List<InformacionSeleccionPaqueteriaDTO> listaInformacionSeleccionPaqueteriaDTO = new LinkedList<>();

        for (Paqueteria paqueteria : paqueterias) {
            listaInformacionSeleccionPaqueteriaDTO.add(
                    new InformacionSeleccionPaqueteriaDTO(
                            paqueteria.getId(),
                            paqueteria.getDireccionImagenPaqueteria()
                    )
            );
        }

        return listaInformacionSeleccionPaqueteriaDTO;
    }
}
