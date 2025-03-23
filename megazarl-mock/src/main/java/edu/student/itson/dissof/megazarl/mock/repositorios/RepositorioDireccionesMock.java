package edu.student.itson.dissof.megazarl.mock.repositorios;

import edu.student.itson.dissof.megazarl.dto.modelos.Direccion;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import edu.student.itson.dissof.megazarl.repositorio.RepositorioDirecciones;
import java.util.ArrayList;

public class RepositorioDireccionesMock implements RepositorioDirecciones {

    private final List<Direccion> direcciones = new ArrayList<>();

    @Override
    public void guardar(Direccion entidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void guardarMuchos(Collection<Direccion> entidades) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Direccion> encontrarTodos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long cuenta() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean existe(Predicate<Direccion> criterio) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
