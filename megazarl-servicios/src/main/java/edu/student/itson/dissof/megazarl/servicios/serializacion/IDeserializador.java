package edu.student.itson.dissof.megazarl.servicios.serializacion;

public interface IDeserializador {
    <T> T deserializar(String entrada, Class<T> tipo);
}
