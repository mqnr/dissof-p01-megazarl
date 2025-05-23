package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioGerenteVentas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * RepositorioGerenteVentasEnMemoria.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class RepositorioGerenteVentasEnMemoria implements RepositorioGerenteVentas {

    private final List<GerenteVentasDTONegocios> listaGerentesVentas;
    
    private static Long ID_GERENTE_VENTAS_ACTUAL = 1L;
    
    public RepositorioGerenteVentasEnMemoria() {
        listaGerentesVentas = new ArrayList<>();
    }

    public RepositorioGerenteVentasEnMemoria(Collection<GerenteVentasDTONegocios> gerentes) {
        listaGerentesVentas = new ArrayList<>(gerentes);
    }
    
    @Override
    public GerenteVentasDTONegocios recuperarPorId(IdGerenteVentasDTONegocios idGerenteVentasDTO) {
        
        return listaGerentesVentas.stream()
                .filter(cliente -> cliente.getId().equals(idGerenteVentasDTO.getIdGerenteVentas()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdGerenteVentasDTONegocios idGerenteVentasDTO) {
        
        return listaGerentesVentas.stream().anyMatch(gerente -> gerente.getId().equals(idGerenteVentasDTO.getIdGerenteVentas()));

    }
    
    @Override
    public void agregar(GerenteVentasDTONegocios gerente) {
        gerente.setId(ID_GERENTE_VENTAS_ACTUAL++);
        listaGerentesVentas.add(gerente);
    }

    @Override
    public void agregar(Collection<GerenteVentasDTONegocios> gerentes) {
        for(GerenteVentasDTONegocios gerente: gerentes){
            gerente.setId(ID_GERENTE_VENTAS_ACTUAL++);
        }
        listaGerentesVentas.addAll(gerentes);

    }

    @Override
    public List<GerenteVentasDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaGerentesVentas);
    }
    
}