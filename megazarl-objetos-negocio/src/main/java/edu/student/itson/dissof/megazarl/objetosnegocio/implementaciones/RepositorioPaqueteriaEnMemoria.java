package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioPaqueteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioPaqueteriaEnMemoria implements RepositorioPaqueteria {
    private final List<PaqueteriaDTO> paqueterias;

    public RepositorioPaqueteriaEnMemoria() {
        paqueterias = new ArrayList<>();
    }

    public RepositorioPaqueteriaEnMemoria(Collection<PaqueteriaDTO> paqueterias) {
        this.paqueterias = new ArrayList<>(paqueterias);
    }

    @Override
    public PaqueteriaDTO recuperarPorId(IdPaqueteriaDTO idPaqueteriaDTO) {
        return paqueterias.stream()
                .filter(paqueteria -> paqueteria.getId().equals(idPaqueteriaDTO.getIdPaqueteria()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdPaqueteriaDTO idPaqueteriaDTO) {
        return existe(paqueteria -> paqueteria.getId().equals(idPaqueteriaDTO.getIdPaqueteria()));
    }

    @Override
    public Stream<PaqueteriaDTO> stream() {
        return paqueterias.stream();
    }

    @Override
    public void agregar(PaqueteriaDTO paqueteria) {
        paqueterias.add(paqueteria);
    }

    @Override
    public void agregar(Collection<PaqueteriaDTO> paqueterias) {
        this.paqueterias.addAll(paqueterias);
    }

    @Override
    public List<PaqueteriaDTO> recuperarTodos() {
        return new ArrayList<>(paqueterias);
    }

    @Override
    public long cuenta() {
        return paqueterias.size();
    }

    @Override
    public boolean existe(Predicate<PaqueteriaDTO> criterio) {
        return paqueterias.stream().anyMatch(criterio);
    }
}
