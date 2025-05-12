package edu.student.itson.dissof.megazarl.servicios.serializacion;

import com.google.gson.Gson;

public class DeserializadorJson implements IDeserializador {
    @Override
    public <T> T deserializar(String entrada, Class<T> tipo) {
        return new Gson().fromJson(entrada, tipo);
    }
}
