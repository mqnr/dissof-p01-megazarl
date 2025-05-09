
package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.SucursalDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioSucursal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class RepositorioSucursalEnMemoria implements RepositorioSucursal{
    private final List<SucursalDTO> listaSucursales;
    
    public RepositorioSucursalEnMemoria() {
        listaSucursales = new ArrayList<>();
    }
    
    public RepositorioSucursalEnMemoria(Collection<SucursalDTO> sucursales) {
        listaSucursales = new ArrayList<>(sucursales);
    }
    
    @Override
    public SucursalDTO recuperarPorId(IdSucursalDTO idSucursalDTO) {
        return listaSucursales.stream()
                .filter(sucursal -> sucursal.getId().equals(idSucursalDTO.getIdSucursal()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdSucursalDTO idSucursalDTO) {
        return existe(sucursal -> sucursal.getId().equals(idSucursalDTO.getIdSucursal()));
    }

    @Override
    public Stream<SucursalDTO> stream() {
        return listaSucursales.stream();
    }

    @Override
    public void agregar(SucursalDTO sucursal) {
        listaSucursales.add(sucursal);
    }

    @Override
    public void agregar(Collection<SucursalDTO> sucursales) {
        listaSucursales.addAll(sucursales);
    }

    @Override
    public List<SucursalDTO> recuperarTodos() {
        return new ArrayList<>(listaSucursales);
    }
    
    @Override
    public SucursalDTO obtenerSucursalMatriz() {
        return listaSucursales.stream().filter(SucursalDTO::esMatriz).findFirst().orElse(null);
    }

    @Override
    public long cuenta() {
        return listaSucursales.size();
    }

    @Override
    public boolean existe(Predicate<SucursalDTO> criterio) {
        return listaSucursales.stream().anyMatch(criterio);
    }
    
}
