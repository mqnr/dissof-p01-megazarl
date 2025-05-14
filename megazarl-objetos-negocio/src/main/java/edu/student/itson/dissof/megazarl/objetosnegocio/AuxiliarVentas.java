
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.infraestructura.AuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdAuxiliarVentasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioAuxiliarVentas;
import edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones.RepositorioAuxiliarVentasEnMemoria;
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
    
    public static AuxiliarVentasDTO recuperarPorId(IdAuxiliarVentasDTO idAuxiliarVentasDTO) {
        return repositorio.recuperarPorId(idAuxiliarVentasDTO);
    }
    
    public static boolean existePorId(IdAuxiliarVentasDTO idAuxiliarVentasDTO) {
        return repositorio.existePorId(idAuxiliarVentasDTO);
    }
     
    public static void agregar(AuxiliarVentasDTO auxiliarVentas) {
        repositorio.agregar(auxiliarVentas);
    }
    
    public static void agregar(Collection<AuxiliarVentasDTO> auxiliaresVentas) {
        repositorio.agregar(auxiliaresVentas);
    }
     
    public static List<AuxiliarVentasDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static long cuenta() {
        return repositorio.cuenta();
    }
    
    public static boolean existe(Predicate<AuxiliarVentasDTO> criterio) {
        return repositorio.existe(criterio);
    }
    
}
