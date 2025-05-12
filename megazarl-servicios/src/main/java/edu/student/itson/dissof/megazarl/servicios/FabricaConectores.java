package edu.student.itson.dissof.megazarl.servicios;

import java.util.HashMap;
import java.util.Map;

public class FabricaConectores {
    private final Map<String, IConector> conectores = new HashMap<>();

    public void registrar(String clave, IConector conector) {
        conectores.put(clave, conector);
    }

    public <T extends IConector> T obtener(String clave, Class<T> tipo) {
        return tipo.cast(conectores.get(clave));
    }
}
