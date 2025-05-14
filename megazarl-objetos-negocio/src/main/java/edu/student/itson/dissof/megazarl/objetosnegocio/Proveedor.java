
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProveedor;
import edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones.RepositorioProveedorEnMemoria;
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
    
    public static ProveedorDTO recuperarPorId(IdProveedorDTO idProveedorDTO) {
        return repositorio.recuperarPorId(idProveedorDTO);
    }
    
     public static boolean existePorId(IdProveedorDTO idProveedorDTO) {
        return repositorio.existePorId(idProveedorDTO);
    }
     
    public static void agregar(ProveedorDTO proveedorDTO) {
        repositorio.agregar(proveedorDTO);
    }
    
    public static void agregar(Collection<ProveedorDTO> proveedores) {
        repositorio.agregar(proveedores);
    }
     
    public static List<ProveedorDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static long cuenta() {
        return repositorio.cuenta();
    }
    
    public static boolean existe(Predicate<ProveedorDTO> criterio) {
        return repositorio.existe(criterio);
    }
}
