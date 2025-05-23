
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.CoordinadorLogisticaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCoordinadorLogisticaDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCoordinadorLogistica;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class RepositorioCoordinadorLogisticaEnMemoria implements RepositorioCoordinadorLogistica{
    
    private final List<CoordinadorLogisticaDTONegocios> listaCoordinadoresLogistica;
    
    private static Long ID_COORDINADOR_LOGISTICA_ACTUAL = 1L;
    
    
    public RepositorioCoordinadorLogisticaEnMemoria() {
        listaCoordinadoresLogistica = new ArrayList<>();
    }

    public RepositorioCoordinadorLogisticaEnMemoria(Collection<CoordinadorLogisticaDTONegocios> clientes) {
        listaCoordinadoresLogistica = new ArrayList<>(clientes);
    }

    @Override
    public CoordinadorLogisticaDTONegocios recuperarPorId(IdCoordinadorLogisticaDTONegocios idCoordinadorLogisticaDTO) {
        return listaCoordinadoresLogistica.stream()
                .filter(coordinadorLogistica -> coordinadorLogistica.getId().equals(idCoordinadorLogisticaDTO.getIdCoordinadorLogistica()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdCoordinadorLogisticaDTONegocios idCoordinadorLogisticaDTO) {
        return listaCoordinadoresLogistica.stream().anyMatch(coordinadorLogistica -> coordinadorLogistica.getId().equals(idCoordinadorLogisticaDTO.getIdCoordinadorLogistica()));
    }

    @Override
    public void agregar(CoordinadorLogisticaDTONegocios coordinadorLogistica) {
        coordinadorLogistica.setId(ID_COORDINADOR_LOGISTICA_ACTUAL++);
        listaCoordinadoresLogistica.add(coordinadorLogistica);
    }

    @Override
    public void agregar(Collection<CoordinadorLogisticaDTONegocios> coordinadoresLogistica) {
        for(CoordinadorLogisticaDTONegocios coordinadorLogistica: coordinadoresLogistica){
            coordinadorLogistica.setId(ID_COORDINADOR_LOGISTICA_ACTUAL++);
        }
        listaCoordinadoresLogistica.addAll(coordinadoresLogistica);
    }

    @Override
    public List<CoordinadorLogisticaDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaCoordinadoresLogistica);
    }
}
