
package edu.student.itson.dissof.megazarl.objetosnegocio;

import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.AgregarInformacionNulaNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCarritoCompras;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria.RepositorioCarritoComprasEnMemoria;
import java.util.Collection;
import java.util.List;


public class CarritoCompras {
    
    private static final RepositorioCarritoCompras repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().carritoCompras()) {
            case MEMORIA -> new RepositorioCarritoComprasEnMemoria();
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static CarritoComprasDTONegocios recuperarPorId(IdCarritoComprasDTONegocios idCarritoComprasDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException {
        
        return repositorio.recuperarPorId(idCarritoComprasDTO);
    }
    
    public static boolean existePorId(IdCarritoComprasDTONegocios idCarritoComprasDTO)
            throws ParametroNuloException,
            FormatoInvalidoIdConversionException {
        
        return repositorio.existePorId(idCarritoComprasDTO);
    }
    
    public static CarritoComprasDTONegocios actualizar(ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTO)
            throws AgregarInformacionNulaNegocioException, 
            FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException {
        
        return repositorio.actualizar(actualizacionCarritoComprasDTO);
    }
     
    public static void agregar(CarritoComprasDTONegocios carritoCompras) 
            throws AgregarInformacionNulaNegocioException,
            FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException {
        
        repositorio.agregar(carritoCompras);
    }
    
    public static void agregar(Collection<CarritoComprasDTONegocios> carritosCompras)
            throws AgregarInformacionNulaNegocioException, 
            FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException {
        
        repositorio.agregar(carritosCompras);
    }
     
    public static List<CarritoComprasDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }

}
