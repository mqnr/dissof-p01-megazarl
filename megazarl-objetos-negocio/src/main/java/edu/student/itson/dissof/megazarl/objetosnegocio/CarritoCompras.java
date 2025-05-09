
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.CarritoComprasDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioCarritoCompras;
import edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones.RepositorioCarritoComprasEnMemoria;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public class CarritoCompras {
    
    private static final RepositorioCarritoCompras repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().producto()) {
            case MEMORIA -> new RepositorioCarritoComprasEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static CarritoComprasDTO recuperarPorId(IdCarritoComprasDTO idCarritoComprasDTO) {
        return repositorio.recuperarPorId(idCarritoComprasDTO);
    }
    
    public static boolean existePorId(IdCarritoComprasDTO idCarritoComprasDTO) {
        return repositorio.existePorId(idCarritoComprasDTO);
    }
     
     public static CarritoComprasDTO actualizar(ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO) {
        return repositorio.actualizar(actualizacionCarritoComprasDTO);
    }
     
    public static void agregar(CarritoComprasDTO carritoCompras) {
        repositorio.agregar(carritoCompras);
    }
    
    public static void agregar(Collection<CarritoComprasDTO> carritosCompras) {
        repositorio.agregar(carritosCompras);
    }
     
    public static List<CarritoComprasDTO> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public static long cuenta() {
        return repositorio.cuenta();
    }
    
    public static boolean existe(Predicate<CarritoComprasDTO> criterio) {
        return repositorio.existe(criterio);
    }

}
