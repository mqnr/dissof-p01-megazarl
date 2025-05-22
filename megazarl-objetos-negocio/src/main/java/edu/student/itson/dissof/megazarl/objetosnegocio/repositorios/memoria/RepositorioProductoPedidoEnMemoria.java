
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoPedidoDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProductoPedido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class RepositorioProductoPedidoEnMemoria implements RepositorioProductoPedido{
    
    private final List<ProductoPedidoDTO> listaProductosPedido;
    
    private static Long ID_ACTUAL_PRODUCTO_PEDIDO = 1L;
    
    public RepositorioProductoPedidoEnMemoria() {
        listaProductosPedido = new ArrayList<>();
    }
    
    public RepositorioProductoPedidoEnMemoria(Collection<ProductoPedidoDTO> productosPedido) {
        listaProductosPedido = new ArrayList<>(productosPedido);
    }
    
    @Override
    public ProductoPedidoDTO recuperarPorId(IdProductoPedidoDTO idProductoPedidoDTO) {
        return listaProductosPedido.stream()
                .filter(productoPedido -> productoPedido.getId().equals(idProductoPedidoDTO.getIdProductoPedido()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProductoPedidoDTO idProductoPedidoDTO) {
        return existe(productoPedido -> productoPedido.getId().equals(idProductoPedidoDTO.getIdProductoPedido()));
    }

    @Override
    public Stream<ProductoPedidoDTO> stream() {
        return listaProductosPedido.stream();
    }

    @Override
    public void agregar(ProductoPedidoDTO productoPedido) {
        
        productoPedido.setId(ID_ACTUAL_PRODUCTO_PEDIDO++);
        
        ProductoPedidoDatosCompletosRelacionesDTO productoPedidoDatosCompletosDTO
                = (ProductoPedidoDatosCompletosRelacionesDTO)productoPedido;
        
        ProductoDatosCompletosRelacionesDTO productoDatosCompletosRelacionesDTO
                = (ProductoDatosCompletosRelacionesDTO) productoPedidoDatosCompletosDTO.getProducto();
      
        productoDatosCompletosRelacionesDTO.getProductosPedido().add(productoPedido);
        
        listaProductosPedido.add(productoPedido);
        
    }

    @Override
    public void agregar(Collection<ProductoPedidoDTO> productosPedido) {
        
        for(ProductoPedidoDTO productoPedido: productosPedido){
            
            productoPedido.setId(ID_ACTUAL_PRODUCTO_PEDIDO++);
            
            ProductoPedidoDatosCompletosRelacionesDTO productoPedidoDatosCompletosDTO
                = (ProductoPedidoDatosCompletosRelacionesDTO)productoPedido;
        
            ProductoDatosCompletosRelacionesDTO productoDatosCompletosRelacionesDTO
                    = (ProductoDatosCompletosRelacionesDTO) productoPedidoDatosCompletosDTO.getProducto();

            productoDatosCompletosRelacionesDTO.getProductosPedido().add(productoPedido);
            
        }
        
        listaProductosPedido.addAll(productosPedido);
    }

    @Override
    public List<ProductoPedidoDTO> recuperarTodos() {
        return new ArrayList<>(listaProductosPedido);
    }

    @Override
    public boolean existe(Predicate<ProductoPedidoDTO> criterio) {
        return listaProductosPedido.stream().anyMatch(criterio);
    }
}
