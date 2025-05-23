package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioPaqueteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioPaqueteriaEnMemoria implements RepositorioPaqueteria {
    
    private final List<PaqueteriaDTONegocios> paqueterias;
    
    private static Long ID_PAQUETERIA_ACTUAL = 1L;

    
    public RepositorioPaqueteriaEnMemoria() {
        paqueterias = new ArrayList<>();
    }

    public RepositorioPaqueteriaEnMemoria(Collection<PaqueteriaDTONegocios> paqueterias) {
        this.paqueterias = new ArrayList<>(paqueterias);
    }

    @Override
    public PaqueteriaDTONegocios recuperarPorId(IdPaqueteriaDTONegocios idPaqueteriaDTO) {
        return paqueterias.stream()
                .filter(paqueteria -> paqueteria.getId().getId().equals(idPaqueteriaDTO.getIdPaqueteria().getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdPaqueteriaDTONegocios idPaqueteriaDTO) {
        
        return paqueterias.stream().anyMatch(paqueteria -> paqueteria.getId().equals(idPaqueteriaDTO.getIdPaqueteria()));

    }

    @Override
    public void agregar(PaqueteriaDTONegocios paqueteria) {
        paqueteria.setId(new IdEntidadGenericoNegocios(ID_PAQUETERIA_ACTUAL++));
        paqueterias.add(paqueteria);
    }

    @Override
    public void agregar(Collection<PaqueteriaDTONegocios> paqueterias) {
        
        for(PaqueteriaDTONegocios paqueteria: paqueterias){
            paqueteria.setId(new IdEntidadGenericoNegocios(ID_PAQUETERIA_ACTUAL++));
        }
        this.paqueterias.addAll(paqueterias);
    }

    @Override
    public List<PaqueteriaDTONegocios> recuperarTodos() {
        return new ArrayList<>(paqueterias);
    }

}
