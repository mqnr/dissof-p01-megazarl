package edu.student.itson.dissof.megazarl.mapas;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;
import edu.student.itson.dissof.megazarl.servicios.IConector;

import java.io.IOException;

/**
 * IConectorMapas.java
 *
 * Interfaz que define el contrato para los conectores de servicios externos.
 * Su propósito es permitir la implementación de múltiples conectores con una
 * estructura común, facilitando la comunicación con servicios como el de mapas.
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
public interface IConectorMapas extends IConector {
    String calcularTiempoTraslado(String datosTiempoTrasladoUbicaciones) throws IOException;
}
