
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.AuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioAuxiliarVentas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * RepositorioAuxiliarVentasEnMemoria.java+
 * 
 * Clase que representa un repositorio en memoria para los objetos que representan
 * un Auxiliar de ventas en la empresa.
 * 
 * @author Manuel Romo López
 * ID: 00000253080
 */
public class RepositorioAuxiliarVentasEnMemoria implements RepositorioAuxiliarVentas{
    private final List<AuxiliarVentasDTONegocios> listaAuxiliaresVentas;
    
    private Long ID_ACTUAL_AUXILIAR_VENTAS = 1L;
    
    public RepositorioAuxiliarVentasEnMemoria() {
        listaAuxiliaresVentas = new ArrayList<>();
    }
    
    public RepositorioAuxiliarVentasEnMemoria(Collection<AuxiliarVentasDTONegocios> auxiliaresVentas) {
        listaAuxiliaresVentas = new ArrayList<>(auxiliaresVentas);
    }
    
    @Override
    public AuxiliarVentasDTONegocios recuperarPorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO) {
        return listaAuxiliaresVentas.stream()
                .filter(auxiliarVentas -> auxiliarVentas.getId().equals(idAuxiliarVentasDTO.getIdAuxiliarVentas()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO) {

        return listaAuxiliaresVentas.stream().anyMatch(auxiliarVentas -> auxiliarVentas.getId().equals(idAuxiliarVentasDTO.getIdAuxiliarVentas()));
        
    }


    @Override
    public void agregar(AuxiliarVentasDTONegocios auxiliarVentas) {
        if(auxiliarVentas.getId() == null){
            auxiliarVentas.setId(ID_ACTUAL_AUXILIAR_VENTAS++);
        }
        listaAuxiliaresVentas.add(auxiliarVentas);
    }

    @Override
    public void agregar(Collection<AuxiliarVentasDTONegocios> auxiliaresVentas) {
        for(AuxiliarVentasDTONegocios auxiliarVentas: auxiliaresVentas){
            if(auxiliarVentas.getId() == null){
                auxiliarVentas.setId(ID_ACTUAL_AUXILIAR_VENTAS++);
            }
        }
        listaAuxiliaresVentas.addAll(auxiliaresVentas);
    }

    @Override
    public List<AuxiliarVentasDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaAuxiliaresVentas);
    }
    
}
