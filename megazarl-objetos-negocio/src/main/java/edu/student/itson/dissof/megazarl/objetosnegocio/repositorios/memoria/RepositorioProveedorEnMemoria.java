
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProveedor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class RepositorioProveedorEnMemoria implements RepositorioProveedor{
    
    private final List<ProveedorDTONegocios> listaProveedores;
    
    private static Long ID_PROVEEDOR_ACTUAL = 1L;
    
    public RepositorioProveedorEnMemoria() {
        listaProveedores = new ArrayList<>();
    }
    
    public RepositorioProveedorEnMemoria(Collection<ProveedorDTONegocios> proveedores) {
        listaProveedores = new ArrayList<>(proveedores);
    }
    
    @Override
    public ProveedorDTONegocios recuperarPorId(IdProveedorDTONegocios idProveedorDTO) {
        return listaProveedores.stream()
                .filter(proveedor -> proveedor.getId().equals(idProveedorDTO.getIdProveedor()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProveedorDTONegocios idProveedorDTO) {
        
        return listaProveedores.stream().anyMatch(proveedor -> proveedor.getId().equals(idProveedorDTO.getIdProveedor()));

    }

    @Override
    public void agregar(ProveedorDTONegocios proveedor) {
        proveedor.setId(new IdEntidadGenericoNegocios(ID_PROVEEDOR_ACTUAL++));
        listaProveedores.add(proveedor);
    }

    @Override
    public void agregar(Collection<ProveedorDTONegocios> proveedores) {
        
        for(ProveedorDTONegocios proveedor: proveedores){
            proveedor.setId(new IdEntidadGenericoNegocios(ID_PROVEEDOR_ACTUAL++));
        }
        
        listaProveedores.addAll(proveedores);
    }

    @Override
    public List<ProveedorDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaProveedores);
    }
}
