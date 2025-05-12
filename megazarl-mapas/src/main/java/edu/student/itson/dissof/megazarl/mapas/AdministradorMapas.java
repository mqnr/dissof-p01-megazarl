package edu.student.itson.dissof.megazarl.mapas;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;

public class AdministradorMapas implements IAdministradorMapas{
    // TODO: abstraer a configuración
    private static final String RUTA_SCRIPT_MAPAS = "scripts/servicio_mapas_mock.py";

    @Override
    public TiempoTrasladoDTO calcularTiempoTraslado(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO) {
        IConectorMapas conector = new ConectorMapas(RUTA_SCRIPT_MAPAS);
        return conector.calcularTiempoTraslado(datosTiempoTrasladoUbicacionesDTO);
    }
}
