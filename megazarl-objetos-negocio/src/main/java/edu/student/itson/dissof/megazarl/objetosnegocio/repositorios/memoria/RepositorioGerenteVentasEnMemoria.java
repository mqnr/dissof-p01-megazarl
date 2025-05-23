package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioGerenteVentas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;
/**
 * RepositorioGerenteVentasEnMemoria.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class RepositorioGerenteVentasEnMemoria implements RepositorioGerenteVentas {

    private final List<GerenteVentasDTO> listaGerentesVentas;
    
    private static Long ID_GERENTE_VENTAS_ACTUAL = 1L;
    
    public RepositorioGerenteVentasEnMemoria() {
        listaGerentesVentas = new ArrayList<>();
    }

    public RepositorioGerenteVentasEnMemoria(Collection<GerenteVentasDTO> gerentes) {
        listaGerentesVentas = new ArrayList<>(gerentes);
    }
    
    @Override
    public GerenteVentasDTO recuperarPorId(IdGerenteVentasDTO idGerenteVentasDTO) {
        return listaGerentesVentas.stream()
                .filter(cliente -> cliente.getId().equals(idGerenteVentasDTO.getIdGerenteVentas()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdGerenteVentasDTO idGerenteVentasDTO) {
        return existe(gerente -> gerente.getId().equals(idGerenteVentasDTO.getIdGerenteVentas()));
    }

    @Override
    public Stream<GerenteVentasDTO> stream() {
        return listaGerentesVentas.stream();
    }

    @Override
    public void agregar(GerenteVentasDTO gerente) {
        gerente.setId(new IdEntidadGenerico(ID_GERENTE_VENTAS_ACTUAL++));
        listaGerentesVentas.add(gerente);
    }

    @Override
    public void agregar(Collection<GerenteVentasDTO> gerentes) {
        for(GerenteVentasDTO gerente: gerentes){
            gerente.setId(new IdEntidadGenerico(ID_GERENTE_VENTAS_ACTUAL++));
        }
        listaGerentesVentas.addAll(gerentes);

    }

    @Override
    public List<GerenteVentasDTO> recuperarTodos() {
        return new ArrayList<>(listaGerentesVentas);
    }

    @Override
    public boolean existe(Predicate<GerenteVentasDTO> criterio) {
        return listaGerentesVentas.stream().anyMatch(criterio);
    }
    
}