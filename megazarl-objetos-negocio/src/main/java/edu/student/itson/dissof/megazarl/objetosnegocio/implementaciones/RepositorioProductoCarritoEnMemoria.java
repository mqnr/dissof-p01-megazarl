
package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProductoCarrito;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class RepositorioProductoCarritoEnMemoria implements RepositorioProductoCarrito{
    
    private final List<ProductoCarritoDTO> listaProductosCarrito;
    
    private static Long ID_PAQUETERIA_ACTUAL = 1L;

    
    public RepositorioProductoCarritoEnMemoria() {
        listaProductosCarrito = new ArrayList<>();
    }

    public RepositorioProductoCarritoEnMemoria(Collection<ProductoCarritoDTO> productosCarrito) {
        this.listaProductosCarrito = new ArrayList<>(productosCarrito);
    }

    @Override
    public ProductoCarritoDTO recuperarPorId(IdProductoCarritoDTO idProductoCarritoDTO) {
        return listaProductosCarrito.stream()
                .filter(productoCarrito -> productoCarrito.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProductoCarritoDTO idProductoCarritoDTO) {
        return existe(paqueteria -> paqueteria.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()));
    }

    @Override
    public Stream<ProductoCarritoDTO> stream() {
        return listaProductosCarrito.stream();
    }
    
    @Override
    public ProductoCarritoDTO actualizar(ActualizacionProductoCarritoDTO actualizacionProductoCarritoDTO) {
        
        
        for (int i = 0; i < listaProductosCarrito.size(); i++) {
                
            ProductoCarritoDTO productoCarrito = listaProductosCarrito.get(i);
            
            if (productoCarrito.getId().equals(actualizacionProductoCarritoDTO.getId())) {
                
                ProductoCarritoDTO productoCarritoActualizado = aplicar(productoCarrito, actualizacionProductoCarritoDTO);
                
                
                List<ProductoCarritoDTO> listaProductosCarritoCarritoCompras = productoCarritoActualizado.getCarritoCompras().getProductosCarrito();
                
                IntStream.range(0, listaProductosCarritoCarritoCompras.size())
                    .filter(j -> listaProductosCarritoCarritoCompras.get(j).getId().equals(productoCarritoActualizado.getId()))
                    .findFirst()
                    .ifPresent(j -> listaProductosCarritoCarritoCompras.set(j, productoCarritoActualizado));
          
                List<ProductoCarritoDTO> listaProductosCarritoProducto = productoCarritoActualizado.getProducto().getProductosCarrito();
                
                IntStream.range(0, listaProductosCarritoProducto.size())
                    .filter(j -> listaProductosCarritoProducto.get(j).getId().equals(productoCarritoActualizado.getId()))
                    .findFirst()
                    .ifPresent(j -> listaProductosCarritoProducto.set(j, productoCarritoActualizado));
                
                listaProductosCarrito.set(i, productoCarritoActualizado);
                
                return productoCarritoActualizado;
            }
        }
        return null;
    }

    @Override
    public void agregar(ProductoCarritoDTO productoCarrito) {
        productoCarrito.setId(ID_PAQUETERIA_ACTUAL++);
        productoCarrito.getCarritoCompras().getProductosCarrito().add(productoCarrito);
        productoCarrito.getProducto().getProductosCarrito().add(productoCarrito);
        listaProductosCarrito.add(productoCarrito);
        
        
    }
    private static final Logger LOG = Logger.getLogger(RepositorioProductoCarritoEnMemoria.class.getName());

    @Override
    public void agregar(Collection<ProductoCarritoDTO> productosCarrito) {
        
        for(ProductoCarritoDTO productoCarrito: productosCarrito){
            productoCarrito.setId(ID_PAQUETERIA_ACTUAL++);
            productoCarrito.getCarritoCompras().getProductosCarrito().add(productoCarrito);
            productoCarrito.getProducto().getProductosCarrito().add(productoCarrito);
        }
        this.listaProductosCarrito.addAll(productosCarrito);
    }
    
    @Override
    public void removerPorId(IdProductoCarritoDTO idProductoCarritoDTO) {
        
        ProductoCarritoDTO productoCarritoDTO = listaProductosCarrito.stream()
            .filter(productoCarrito -> productoCarrito.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()))
            .findFirst()
            .orElse(null);
        
        productoCarritoDTO.getCarritoCompras().getProductosCarrito().removeIf(productoCarrito ->
            productoCarrito.getProducto() != null &&
            productoCarrito.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()));
        
        productoCarritoDTO.getProducto().getProductosCarrito().removeIf(productoCarrito ->
            productoCarrito.getProducto() != null &&
            productoCarrito.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()));
        
        listaProductosCarrito.removeIf(productoCarrito ->
            productoCarrito.getProducto() != null &&
            productoCarrito.getId().equals(idProductoCarritoDTO.getIdProductoCarrito())
        );
        
        
    }

    @Override
    public List<ProductoCarritoDTO> recuperarTodos() {
        return new ArrayList<>(listaProductosCarrito);
    }

    @Override
    public long cuenta() {
        return listaProductosCarrito.size();
    }

    @Override
    public boolean existe(Predicate<ProductoCarritoDTO> criterio) {
        return listaProductosCarrito.stream().anyMatch(criterio);
    }
    
    private ProductoCarritoDTO aplicar(ProductoCarritoDTO productoCarritoOriginal, ActualizacionProductoCarritoDTO actualizacionProductoCarritoDTO) {
        
        return new ProductoCarritoDTO(
                actualizacionProductoCarritoDTO.getId(),
                actualizacionProductoCarritoDTO.tieneCantidad() ? actualizacionProductoCarritoDTO.getCantidad() : productoCarritoOriginal.getCantidad(),
                productoCarritoOriginal.getCarritoCompras(),
                productoCarritoOriginal.getProducto()    
        );
        
    }

}
