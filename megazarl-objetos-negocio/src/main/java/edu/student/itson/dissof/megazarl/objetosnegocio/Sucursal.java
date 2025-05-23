
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioSucursal;
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
    
    public static SucursalDTONegocios recuperarPorId(IdSucursalDTONegocios idSucursalDTO) {
        return repositorio.recuperarPorId(idSucursalDTO);
    }
    
     public static boolean existePorId(IdSucursalDTONegocios idSucursalDTO) {
        return repositorio.existePorId(idSucursalDTO);
    }
     
    public static void agregar(SucursalDTONegocios sucursal) {
        repositorio.agregar(sucursal);
    }

    public static void agregar(Collection<SucursalDTONegocios> sucursales) {
        repositorio.agregar(sucursales);
    }
     
    public static List<SucursalDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static SucursalDTONegocios obtenerSucursalMatriz(){
        return repositorio.obtenerSucursalMatriz();
    }
    
}
