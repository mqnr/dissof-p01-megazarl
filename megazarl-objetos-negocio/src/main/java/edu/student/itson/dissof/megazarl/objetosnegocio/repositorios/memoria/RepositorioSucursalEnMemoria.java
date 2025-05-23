
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioSucursal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RepositorioSucursalEnMemoria implements RepositorioSucursal{
    
    private final List<SucursalDTONegocios> listaSucursales;
    
    private static Long ID_SUCURSAL_ACTUAL = 1L;
    
    public RepositorioSucursalEnMemoria() {
        listaSucursales = new ArrayList<>();
    }
    
    public RepositorioSucursalEnMemoria(Collection<SucursalDTONegocios> sucursales) {
        listaSucursales = new ArrayList<>(sucursales);
    }
    
    @Override
    public SucursalDTONegocios recuperarPorId(IdSucursalDTONegocios idSucursalDTO) {
        return listaSucursales.stream()
                .filter(sucursal -> sucursal.getId().equals(idSucursalDTO.getIdSucursal()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdSucursalDTONegocios idSucursalDTO) {
        
        return listaSucursales.stream().anyMatch(sucursal -> sucursal.getId().equals(idSucursalDTO.getIdSucursal()));

    }

    @Override
    public void agregar(SucursalDTONegocios sucursal) {
        sucursal.setId(new IdEntidadGenericoNegocios(ID_SUCURSAL_ACTUAL++));
        listaSucursales.add(sucursal);
    }

    @Override
    public void agregar(Collection<SucursalDTONegocios> sucursales) {
        
        for(SucursalDTONegocios sucursal: sucursales){
            sucursal.setId(new IdEntidadGenericoNegocios(ID_SUCURSAL_ACTUAL++));
        }
        
        listaSucursales.addAll(sucursales);
    }

    @Override
    public List<SucursalDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaSucursales);
    }
    
    @Override
    public SucursalDTONegocios obtenerSucursalMatriz() {
        return listaSucursales.stream().filter(SucursalDTONegocios::esMatriz).findFirst().orElse(null);
    }
    
}
