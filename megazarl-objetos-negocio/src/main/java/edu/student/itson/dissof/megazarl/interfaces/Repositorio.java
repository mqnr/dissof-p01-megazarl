package edu.student.itson.dissof.megazarl.interfaces;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface Repositorio<T> {
    void agregar(T entidad);

    void agregar(Collection<T> entidades);

    List<T> recuperarTodos();

    long cuenta();

    boolean existe(Predicate<T> criterio);
}
