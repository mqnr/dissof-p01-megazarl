
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProductoPedido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;


public class RepositorioProductoPedidoEnMemoria implements RepositorioProductoPedido{
    
    private final List<ProductoPedidoDTONegocios> listaProductosPedido;
    
    private static Long ID_ACTUAL_PRODUCTO_PEDIDO = 1L;
    
    public RepositorioProductoPedidoEnMemoria() {
        listaProductosPedido = new ArrayList<>();
    }
    
    public RepositorioProductoPedidoEnMemoria(Collection<ProductoPedidoDTONegocios> productosPedido) {
        listaProductosPedido = new ArrayList<>(productosPedido);
    }
    
    @Override
    public ProductoPedidoDTONegocios recuperarPorId(IdProductoPedidoDTONegocios idProductoPedidoDTO) {
        return listaProductosPedido.stream()
                .filter(productoPedido -> productoPedido.getId().equals(idProductoPedidoDTO.getIdProductoPedido()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProductoPedidoDTONegocios idProductoPedidoDTO) {
        
        return listaProductosPedido.stream().anyMatch(productoPedido -> productoPedido.getId().equals(idProductoPedidoDTO.getIdProductoPedido()));

    }

    @Override
    public void agregar(ProductoPedidoDTONegocios productoPedido) {
        
        productoPedido.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_PRODUCTO_PEDIDO++));
        
        ProductoPedidoDatosCompletosRelacionesDTONegocios productoPedidoDatosCompletosDTO
                = (ProductoPedidoDatosCompletosRelacionesDTONegocios)productoPedido;
        
        ProductoDatosCompletosRelacionesDTONegocios productoDatosCompletosRelacionesDTO
                = (ProductoDatosCompletosRelacionesDTONegocios) productoPedidoDatosCompletosDTO.getProducto();
      
        productoDatosCompletosRelacionesDTO.getProductosPedido().add(productoPedido);
        
        listaProductosPedido.add(productoPedido);
        
    }

    @Override
    public void agregar(Collection<ProductoPedidoDTONegocios> productosPedido) {
        
        for(ProductoPedidoDTONegocios productoPedido: productosPedido){
            
            productoPedido.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_PRODUCTO_PEDIDO++));
            
            ProductoPedidoDatosCompletosRelacionesDTONegocios productoPedidoDatosCompletosDTO
                = (ProductoPedidoDatosCompletosRelacionesDTONegocios)productoPedido;
        
            ProductoDatosCompletosRelacionesDTONegocios productoDatosCompletosRelacionesDTO
                    = (ProductoDatosCompletosRelacionesDTONegocios) productoPedidoDatosCompletosDTO.getProducto();

            productoDatosCompletosRelacionesDTO.getProductosPedido().add(productoPedido);
            
        }
        
        listaProductosPedido.addAll(productosPedido);
    }

    @Override
    public List<ProductoPedidoDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaProductosPedido);
    }

}
