package edu.student.itson.dissof.megazarl.servicios;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;

/**
 * IAdministradorServiciosExternos.java
 *
 * Interfaz que define el comportamiento esperado de los administradores de
 * servicios externos. Es utilizada como punto de abstracción para permitir
 * distintas implementaciones del flujo de comunicación con servicios externos.
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
public interface IAdministradorServiciosExternos {
    TiempoTrasladoDTO enviarDatosTiempoTrasladoUbicacionesDTO(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO);
}
