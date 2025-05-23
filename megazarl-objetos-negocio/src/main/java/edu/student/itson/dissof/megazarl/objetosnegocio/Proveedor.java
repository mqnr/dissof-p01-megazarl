
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProveedor;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioProveedorEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Proveedor {
    private static final RepositorioProveedor repositorio;
    
    static {
            repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().proveedor()) {
            case MEMORIA -> new RepositorioProveedorEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static ProveedorDTONegocios recuperarPorId(IdProveedorDTONegocios idProveedorDTO) {
        return repositorio.recuperarPorId(idProveedorDTO);
    }
    
     public static boolean existePorId(IdProveedorDTONegocios idProveedorDTO) {
        return repositorio.existePorId(idProveedorDTO);
    }
     
    public static void agregar(ProveedorDTONegocios proveedorDTO) {
        repositorio.agregar(proveedorDTO);
    }
    
    public static void agregar(Collection<ProveedorDTONegocios> proveedores) {
        repositorio.agregar(proveedores);
    }
     
    public static List<ProveedorDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
}
