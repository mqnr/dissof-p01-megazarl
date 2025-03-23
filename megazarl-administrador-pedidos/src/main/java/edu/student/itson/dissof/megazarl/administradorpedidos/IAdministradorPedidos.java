package edu.student.itson.dissof.megazarl.administradorpedidos;

import edu.student.itson.dissof.megazarl.dto.InformacionProductoCalculoTiempoPreparacionDTO;
import java.util.List;

public interface IAdministradorPedidos {

    float obtenerTiempoEstimadoPreparacion(List<InformacionProductoCalculoTiempoPreparacionDTO> listaInformacionProductoCalculoTiempoPreparacionDTO);
}
