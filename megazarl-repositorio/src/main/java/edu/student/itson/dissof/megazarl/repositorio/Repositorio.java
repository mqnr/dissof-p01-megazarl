package edu.student.itson.dissof.megazarl.repositorio;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface Repositorio<T> {
    void guardar(T entidad);

    void guardarMuchos(Collection<T> entidades);

    List<T> encontrarTodos();

    long cuenta();

    boolean existe(Predicate<T> criterio);
}
