
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.Collection;
import java.util.List;


public interface RepositorioProducto {
    
    public abstract ProductoDTONegocios recuperarPorId(IdProductoDTONegocios idProductoDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;
    
    public abstract boolean existePorId(IdProductoDTONegocios idProductoDTO) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException;

    public abstract void agregar(ProductoDTONegocios producto)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;
    
    public abstract void agregar(Collection<ProductoDTONegocios> productos)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException;
     
    public abstract List<ProductoDTONegocios> recuperarTodos();
    
}
