
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.Collection;
import java.util.List;

public interface RepositorioCarritoCompras{
    
    public abstract CarritoComprasDTONegocios recuperarPorId(IdCarritoComprasDTONegocios idCarritoComprasDTO)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;
    
    public abstract boolean existePorId(IdCarritoComprasDTONegocios idCarritoComprasDTO) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException ;
    
    public abstract CarritoComprasDTONegocios actualizar(ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;
    
    public abstract void agregar(CarritoComprasDTONegocios carritoCompras) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException;
    
    public abstract void agregar(Collection<CarritoComprasDTONegocios> carritosCompras) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException;
     
    public abstract List<CarritoComprasDTONegocios> recuperarTodos();
    
    public abstract ProductoCarritoDTONegocios recuperarProductoCarritoPorId(IdProductoCarritoDTONegocios idProductoCarritoDTONegocios)
            throws RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException ;
    
    public abstract boolean existeProductoCarritoPorId(IdProductoCarritoDTONegocios idProductoCarritoDTONegocios)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException;
    
    public abstract ProductoCarritoDTONegocios actualizarProductoCarrito(ActualizacionProductoCarritoDTONegocios actualizacionProductoCarritoDTONegocios)
            throws RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException, 
            ValorParametroInvalidoNegocioException;
    
    public abstract void removerProductoCarritoPorId(IdProductoCarritoDTONegocios idProdutoCarritoDTONegocios) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException;
    
    public abstract void agregarProductoCarrito(ProductoCarritoDTONegocios nuevoProductoCarritoNegocios)
            throws ParametroNuloNegocioException, 
            RegistroInexistenteNegocioException,
            FormatoIdInvalidoNegocioException;
    
    public void agregarProductosCarrito(Collection<ProductoCarritoDTONegocios> nuevosProductosCarrito)  
            throws ParametroNuloNegocioException,
            RegistroInexistenteNegocioException,
            FormatoIdInvalidoNegocioException;
    
    public List<ProductoCarritoDTONegocios> recuperarTodosProductosCarrito();
  
}
