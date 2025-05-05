
package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.ProveedorDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProveedor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class RepositorioProveedorEnMemoria implements RepositorioProveedor{
    
    private final List<ProveedorDTO> listaProveedores;
    
    public RepositorioProveedorEnMemoria() {
        listaProveedores = new ArrayList<>();
    }
    
    public RepositorioProveedorEnMemoria(Collection<ProveedorDTO> proveedores) {
        listaProveedores = new ArrayList<>(proveedores);
    }
    
    @Override
    public ProveedorDTO recuperarPorId(IdProveedorDTO idProveedorDTO) {
        return listaProveedores.stream()
                .filter(proveedor -> proveedor.getId().equals(idProveedorDTO.getIdProveedor()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProveedorDTO idProveedorDTO) {
        return existe(proveedor -> proveedor.getId().equals(idProveedorDTO.getIdProveedor()));
    }

    @Override
    public Stream<ProveedorDTO> stream() {
        return listaProveedores.stream();
    }

    @Override
    public void agregar(ProveedorDTO proveedor) {
        listaProveedores.add(proveedor);
    }

    @Override
    public void agregar(Collection<ProveedorDTO> proveedores) {
        listaProveedores.addAll(proveedores);
    }

    @Override
    public List<ProveedorDTO> recuperarTodos() {
        return new ArrayList<>(listaProveedores);
    }

    @Override
    public long cuenta() {
        return listaProveedores.size();
    }

    @Override
    public boolean existe(Predicate<ProveedorDTO> criterio) {
        return listaProveedores.stream().anyMatch(criterio);
    }
}
