
package edu.student.itson.dissof.megazarl.objetosnegocio.interfaces;

import edu.student.itson.dissof.megazarl.dto.negocios.IdPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import java.util.Collection;
import java.util.List;


public interface RepositorioPedido{

    public abstract PedidoDTONegocios recuperarPorId(IdPedidoDTONegocios idPedidoDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException;
    
    public abstract boolean existePorId(IdPedidoDTONegocios idPedidoDTONegocios)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException;
    
    public abstract void agregar(PedidoDTONegocios pedidoDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException;
    
    public abstract void agregar(Collection<PedidoDTONegocios> pedidosDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException;
    
    public abstract List<PedidoDTONegocios> recuperarTodos();
    
    public abstract boolean existeProductoPedidoPorId(IdProductoPedidoDTONegocios idProductoPedidoDTONegocio)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException;
    
    public abstract void agregarProductoPedido(ProductoPedidoDTONegocios nuevoProductoPedido)
            throws ParametroNuloNegocioException, 
            RegistroInexistenteNegocioException,
            FormatoIdInvalidoNegocioException;
    
    public abstract void agregarProductoPedido(Collection<ProductoPedidoDTONegocios> nuevosProductosPedido)
            throws ParametroNuloNegocioException,
            RegistroInexistenteNegocioException,
            FormatoIdInvalidoNegocioException ;
    
    public abstract List<ProductoPedidoDTONegocios> recuperarTodosProductosPedido();

}
