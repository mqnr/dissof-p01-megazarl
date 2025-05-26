
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.Collection;
import java.util.List;


public interface RepositorioProductoInventario {
    
    public abstract ProductoInventarioDTONegocios recuperarPorId(IdProductoInventarioDTONegocios idProductoInventarioDTODatos)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;
    
    public abstract boolean existePorId(IdProductoInventarioDTONegocios idProductoInventarioDTONegocios)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException;
    
    public abstract ProductoInventarioDTONegocios actualizar(ActualizacionProductoInventarioDTONegocios actualizacionProductoInventarioDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;
    
    public abstract void agregar(ProductoInventarioDTONegocios productoInventarioDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException;
    
    public abstract void agregar(Collection<ProductoInventarioDTONegocios> productosInventarioDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException;
    
    public abstract List<ProductoInventarioDTONegocios> recuperarPorIdProducto(IdProductoDTONegocios idProductoDTONegocios) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException;
    
    
    public abstract List<ProductoInventarioDTONegocios> recuperarTodos();
 
}
