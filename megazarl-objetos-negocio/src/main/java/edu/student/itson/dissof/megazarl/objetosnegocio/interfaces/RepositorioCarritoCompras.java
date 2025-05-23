
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoInvalidoIdConversionException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.AgregarInformacionNulaNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import java.util.Collection;
import java.util.List;

public interface RepositorioCarritoCompras{
    
    public abstract CarritoComprasDTONegocios recuperarPorId(IdCarritoComprasDTONegocios idCarritoComprasDTO)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;
    
    public abstract boolean existePorId(IdCarritoComprasDTONegocios idCarritoComprasDTO) 
            throws ParametroNuloException,
            ParametroNuloException,
            FormatoInvalidoIdConversionException;
    
    public abstract CarritoComprasDTONegocios actualizar(ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTO) 
            throws AgregarInformacionNulaNegocioException,
            FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException;
    
    public abstract void agregar(CarritoComprasDTONegocios carritoCompras) 
            throws AgregarInformacionNulaNegocioException, 
            FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException;
    
    public abstract void agregar(Collection<CarritoComprasDTONegocios> carritosCompras) 
            throws AgregarInformacionNulaNegocioException,
            FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException;
     
    public abstract List<CarritoComprasDTONegocios> recuperarTodos();
  
}
