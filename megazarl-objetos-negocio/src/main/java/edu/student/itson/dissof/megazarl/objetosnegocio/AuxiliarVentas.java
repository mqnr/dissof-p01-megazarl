
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.AuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioAuxiliarVentas;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioAuxiliarVentasEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * AuxiliarVentas.java
 * 
 * Clase que representa el objeto de negocio Auxiliar de ventas.
 * 
 * @author Manuel Romo López
 */
public class AuxiliarVentas {
    
    private static final RepositorioAuxiliarVentas repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().auxiliarVentas()) {
            case MEMORIA -> new RepositorioAuxiliarVentasEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static AuxiliarVentasDTONegocios recuperarPorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO) {
        return repositorio.recuperarPorId(idAuxiliarVentasDTO);
    }
    
    public static boolean existePorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO) {
        return repositorio.existePorId(idAuxiliarVentasDTO);
    }
     
    public static void agregar(AuxiliarVentasDTONegocios auxiliarVentas) {
        repositorio.agregar(auxiliarVentas);
    }
    
    public static void agregar(Collection<AuxiliarVentasDTONegocios> auxiliaresVentas) {
        repositorio.agregar(auxiliaresVentas);
    }
     
    public static List<AuxiliarVentasDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
