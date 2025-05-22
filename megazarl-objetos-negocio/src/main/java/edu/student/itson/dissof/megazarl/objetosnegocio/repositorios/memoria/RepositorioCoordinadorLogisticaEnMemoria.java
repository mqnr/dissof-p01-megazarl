
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.CoordinadorLogisticaDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCoordinadorLogisticaDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioCoordinadorLogistica;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class RepositorioCoordinadorLogisticaEnMemoria implements RepositorioCoordinadorLogistica{
    
    private final List<CoordinadorLogisticaDTO> listaCoordinadoresLogistica;
    
    private static Long ID_COORDINADOR_LOGISTICA_ACTUAL = 1L;
    
    
    public RepositorioCoordinadorLogisticaEnMemoria() {
        listaCoordinadoresLogistica = new ArrayList<>();
    }

    public RepositorioCoordinadorLogisticaEnMemoria(Collection<CoordinadorLogisticaDTO> clientes) {
        listaCoordinadoresLogistica = new ArrayList<>(clientes);
    }

    @Override
    public CoordinadorLogisticaDTO recuperarPorId(IdCoordinadorLogisticaDTO idCoordinadorLogisticaDTO) {
        return listaCoordinadoresLogistica.stream()
                .filter(coordinadorLogistica -> coordinadorLogistica.getId().equals(idCoordinadorLogisticaDTO.getIdCoordinadorLogistica()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdCoordinadorLogisticaDTO idCoordinadorLogisticaDTO) {
        return existe(coordinadorLogistica -> coordinadorLogistica.getId().equals(idCoordinadorLogisticaDTO.getIdCoordinadorLogistica()));
    }

    @Override
    public Stream<CoordinadorLogisticaDTO> stream() {
        return listaCoordinadoresLogistica.stream();
    }

    @Override
    public void agregar(CoordinadorLogisticaDTO coordinadorLogistica) {
        coordinadorLogistica.setId(ID_COORDINADOR_LOGISTICA_ACTUAL++);
        listaCoordinadoresLogistica.add(coordinadorLogistica);
    }

    @Override
    public void agregar(Collection<CoordinadorLogisticaDTO> coordinadoresLogistica) {
        for(CoordinadorLogisticaDTO coordinadorLogistica: coordinadoresLogistica){
            coordinadorLogistica.setId(ID_COORDINADOR_LOGISTICA_ACTUAL++);
        }
        listaCoordinadoresLogistica.addAll(coordinadoresLogistica);
    }
    
    @Override
    public boolean existe(Predicate<CoordinadorLogisticaDTO> criterio) {
        return listaCoordinadoresLogistica.stream().anyMatch(criterio);
    }

    @Override
    public List<CoordinadorLogisticaDTO> recuperarTodos() {
        return new ArrayList<>(listaCoordinadoresLogistica);
    }
}
