package edu.student.itson.dissof.megazarl.mapas;

import java.io.IOException;

public class AdministradorMapas {
    public String calcularTiempoTraslado(String datosTiempoTrasladoUbicaciones) throws IOException {
        return new ConectorMapas("127.0.0.1", 6010).calcularTiempoTraslado(datosTiempoTrasladoUbicaciones);
    }
}
