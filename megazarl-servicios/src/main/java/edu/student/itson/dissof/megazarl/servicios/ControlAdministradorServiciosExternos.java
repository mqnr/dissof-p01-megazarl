package edu.student.itson.dissof.megazarl.servicios;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;

/**
 * ControlAdministradorServiciosExternos.java
 *
 * Clase que implementa IAdministradorServiciosExternos. Actúa como el componente
 * de control principal encargado de orquestar el proceso de obtención de
 * tiempo de traslado utilizando un conector (actualmente, ConectorMapas).
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 */
 public class ControlAdministradorServiciosExternos implements IAdministradorServiciosExternos {
    // TODO: abstraer a configuración
    private static final String RUTA_SCRIPT_MAPAS = "scripts/servicio_mapas_mock.py";

    @Override
    public TiempoTrasladoDTO enviarDatosTiempoTrasladoUbicacionesDTO(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO) {
        IConectorMapas conector = new ConectorMapas(RUTA_SCRIPT_MAPAS);
        return conector.enviarDatosTiempoTrasladoUbicacionesDTO(datosTiempoTrasladoUbicacionesDTO);
    }
}
