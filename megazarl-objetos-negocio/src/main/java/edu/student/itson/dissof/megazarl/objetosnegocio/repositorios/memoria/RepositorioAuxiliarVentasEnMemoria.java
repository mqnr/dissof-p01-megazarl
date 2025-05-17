
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.infraestructura.AuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdAuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioAuxiliarVentas;
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
    private final List<AuxiliarVentasDTO> listaAuxiliaresVentas;
    
    private Long ID_ACTUAL_AUXILIAR_VENTAS = 1L;
    
    public RepositorioAuxiliarVentasEnMemoria() {
        listaAuxiliaresVentas = new ArrayList<>();
    }
    
    public RepositorioAuxiliarVentasEnMemoria(Collection<AuxiliarVentasDTO> auxiliaresVentas) {
        listaAuxiliaresVentas = new ArrayList<>(auxiliaresVentas);
    }
    
    @Override
    public AuxiliarVentasDTO recuperarPorId(IdAuxiliarVentasDTO idAuxiliarVentasDTO) {
        return listaAuxiliaresVentas.stream()
                .filter(auxiliarVentas -> auxiliarVentas.getId().equals(idAuxiliarVentasDTO.getIdAuxiliarVentas()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdAuxiliarVentasDTO idAuxiliarVentasDTO) {
        return existe(auxiliarVentas -> auxiliarVentas.getId().equals(idAuxiliarVentasDTO.getIdAuxiliarVentas()));
    }

    @Override
    public Stream<AuxiliarVentasDTO> stream() {
        return listaAuxiliaresVentas.stream();
    }

    @Override
    public void agregar(AuxiliarVentasDTO auxiliarVentas) {
        if(auxiliarVentas.getId() == null){
            auxiliarVentas.setId(ID_ACTUAL_AUXILIAR_VENTAS++);
        }
        listaAuxiliaresVentas.add(auxiliarVentas);
    }

    @Override
    public void agregar(Collection<AuxiliarVentasDTO> auxiliaresVentas) {
        for(AuxiliarVentasDTO auxiliarVentas: auxiliaresVentas){
            if(auxiliarVentas.getId() == null){
                auxiliarVentas.setId(ID_ACTUAL_AUXILIAR_VENTAS++);
            }
        }
        listaAuxiliaresVentas.addAll(auxiliaresVentas);
    }

    @Override
    public List<AuxiliarVentasDTO> recuperarTodos() {
        return new ArrayList<>(listaAuxiliaresVentas);
    }

    @Override
    public boolean existe(Predicate<AuxiliarVentasDTO> criterio) {
        return listaAuxiliaresVentas.stream().anyMatch(criterio);
    }
    
}
