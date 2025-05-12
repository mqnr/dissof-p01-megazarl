package edu.student.itson.dissof.megazarl.servicios.serializacion;

import com.google.gson.Gson;

public class SerializadorJson implements ISerializador {
    @Override
    public <T> String serializar(T objecto) {
        return new Gson().toJson(objecto);
    }
}
