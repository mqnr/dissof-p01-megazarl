package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioGerenteVentas;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioGerenteVentasEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
/**
 * GerenteVentas.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class GerenteVentas {
    
    private static final RepositorioGerenteVentas repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().gerenteVentas()){
            case MEMORIA -> new RepositorioGerenteVentasEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static GerenteVentasDTO recuperarPorId(IdGerenteVentasDTO idGerenteVentasDTO){
        return repositorio.recuperarPorId(idGerenteVentasDTO);
    }
    
    public static boolean existePorId(IdGerenteVentasDTO idGerenteVentasDTO){
        return repositorio.existePorId(idGerenteVentasDTO);
    }
    
    public static void agregar(GerenteVentasDTO gerente){
        repositorio.agregar(gerente);
    }
    
    public static void agregar(Collection<GerenteVentasDTO> gerentes){
        repositorio.agregar(gerentes);
    }
    
    public static List<GerenteVentasDTO> recuperarTodos(){
        return repositorio.recuperarTodos();
    }
    
    
    public static boolean existe(Predicate<GerenteVentasDTO> criterio){
        return repositorio.existe(criterio);
    }
    
}