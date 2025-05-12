package edu.student.itson.dissof.megazarl.mapas;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;

public interface IAdministradorMapas {
    TiempoTrasladoDTO calcularTiempoTraslado(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO);
}
