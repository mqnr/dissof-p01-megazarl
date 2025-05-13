package edu.student.itson.dissof.megazarl.mapas;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;
import edu.student.itson.dissof.megazarl.servicios.serializacion.FabricaDeserializadores;
import edu.student.itson.dissof.megazarl.servicios.serializacion.FabricaSerializadores;
import edu.student.itson.dissof.megazarl.servicios.serializacion.IDeserializador;
import edu.student.itson.dissof.megazarl.servicios.serializacion.ISerializador;

import java.io.IOException;


public class FAdministradorMapas implements IAdministradorMapas {
    private final AdministradorMapas mapas;
    private final ISerializador serializador = FabricaSerializadores.crear("json");
    private final IDeserializador deserializador = FabricaDeserializadores.crear("json");

    public FAdministradorMapas() {
        this.mapas = new AdministradorMapas();
    }

    @Override
    public TiempoTrasladoDTO calcularTiempoTraslado(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO) {
        try {
            return deserializador.deserializar(
                    mapas.calcularTiempoTraslado(
                            serializador.serializar(datosTiempoTrasladoUbicacionesDTO)
                    ),
                    TiempoTrasladoDTO.class
            );
        } catch (IOException e) {
            throw new RuntimeException("Error al comunicarse con el servicio de mapas");
        }
    }
}
