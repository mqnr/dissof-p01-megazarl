
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioSucursal;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioSucursalEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public class Sucursal {
    private static final RepositorioSucursal repositorio;
    
    static {
            repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().sucursal()) {
            case MEMORIA -> new RepositorioSucursalEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static SucursalDTO recuperarPorId(IdSucursalDTO idSucursalDTO) {
        return repositorio.recuperarPorId(idSucursalDTO);
    }
    
     public static boolean existePorId(IdSucursalDTO idSucursalDTO) {
        return repositorio.existePorId(idSucursalDTO);
    }
     
    public static void agregar(SucursalDTO sucursal) {
        repositorio.agregar(sucursal);
    }

    public static void agregar(Collection<SucursalDTO> sucursales) {
        repositorio.agregar(sucursales);
    }
     
    public static List<SucursalDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static SucursalDTO obtenerSucursalMatriz(){
        return repositorio.obtenerSucursalMatriz();
    }
    
    public static boolean existe(Predicate<SucursalDTO> criterio) {
        return repositorio.existe(criterio);
    }
    
}
