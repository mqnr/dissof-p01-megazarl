package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioGerenteVentas;
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
    
    public static GerenteVentasDTONegocios recuperarPorId(IdGerenteVentasDTONegocios idGerenteVentasDTO){
        return repositorio.recuperarPorId(idGerenteVentasDTO);
    }
    
    public static boolean existePorId(IdGerenteVentasDTONegocios idGerenteVentasDTO){
        return repositorio.existePorId(idGerenteVentasDTO);
    }
    
    public static void agregar(GerenteVentasDTONegocios gerente){
        repositorio.agregar(gerente);
    }
    
    public static void agregar(Collection<GerenteVentasDTONegocios> gerentes){
        repositorio.agregar(gerentes);
    }
    
    public static List<GerenteVentasDTONegocios> recuperarTodos(){
        return repositorio.recuperarTodos();
    }
    
    
}