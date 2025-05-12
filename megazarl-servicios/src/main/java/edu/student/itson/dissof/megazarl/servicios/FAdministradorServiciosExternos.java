package edu.student.itson.dissof.megazarl.servicios;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;

/**
 * FAdministradorServiciosExternos.java
 *
 * Clase que implementa IAdministradorServiciosExternos y actúa como fachada
 * del sistema para la comunicación con servicios externos. Su propósito es
 * encapsular la complejidad del subsistema y exponer una interfaz simple y
 * unificada al resto del sistema.
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
public class FAdministradorServiciosExternos implements IAdministradorServiciosExternos {
    private final IAdministradorServiciosExternos administradorServiciosExternos;

    public FAdministradorServiciosExternos(IAdministradorServiciosExternos administradorServiciosExternos) {
        this.administradorServiciosExternos = administradorServiciosExternos;
    }

    @Override
    public TiempoTrasladoDTO enviarDatosTiempoTrasladoUbicacionesDTO(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO) {
        return administradorServiciosExternos.enviarDatosTiempoTrasladoUbicacionesDTO(datosTiempoTrasladoUbicacionesDTO);
    }
}
