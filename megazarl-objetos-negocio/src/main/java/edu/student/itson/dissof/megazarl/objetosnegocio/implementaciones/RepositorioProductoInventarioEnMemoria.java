
package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoInventarioDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProductoInventario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


public class RepositorioProductoInventarioEnMemoria implements RepositorioProductoInventario{
    
    private final List<ProductoInventarioDTO> listaProductosInventario;
    
    private static Long ID_ACTUAL_PRODUCTO_INVENTARIO = 1L;
    
    public RepositorioProductoInventarioEnMemoria() {
        listaProductosInventario = new ArrayList<>();
    }
    
    public RepositorioProductoInventarioEnMemoria(Collection<ProductoInventarioDTO> productosInventario) {
        listaProductosInventario = new ArrayList<>(productosInventario);
    }
    
    @Override
    public ProductoInventarioDTO recuperarPorId(IdProductoInventarioDTO idProductoInventarioDTO) {
        return listaProductosInventario.stream()
                .filter(productoInventario -> productoInventario.getId().equals(idProductoInventarioDTO.getIdProductoInventario()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProductoInventarioDTO idProductoInventarioDTO) {
        return existe(productoInventario -> productoInventario.getId().equals(idProductoInventarioDTO.getIdProductoInventario()));
    }

    @Override
    public Stream<ProductoInventarioDTO> stream() {
        return listaProductosInventario.stream();
    }

    @Override
    public void agregar(ProductoInventarioDTO productoInventario) {
        
        productoInventario.setId(ID_ACTUAL_PRODUCTO_INVENTARIO++);
        
        ProductoInventarioDatosCompletosRelacionesDTO productoInventarioDatosCompletosRelacionesDTO 
                    = (ProductoInventarioDatosCompletosRelacionesDTO) productoInventario;
            
            ProductoDatosCompletosRelacionesDTO productoDatosCompletosRelacionesDTO 
                    = ((ProductoDatosCompletosRelacionesDTO)productoInventarioDatosCompletosRelacionesDTO.getProducto());
            
            
            productoDatosCompletosRelacionesDTO.getProductosInventario().add(productoInventario);
            
        listaProductosInventario.add(productoInventario);
        
    }

    @Override
    public void agregar(Collection<ProductoInventarioDTO> productosInventario) {
        
        for(ProductoInventarioDTO productoInventario: productosInventario){
            
            productoInventario.setId(ID_ACTUAL_PRODUCTO_INVENTARIO++);
            
            ProductoInventarioDatosCompletosRelacionesDTO productoInventarioDatosCompletosRelacionesDTO 
                    = (ProductoInventarioDatosCompletosRelacionesDTO) productoInventario;
            
            ProductoDatosCompletosRelacionesDTO productoDatosCompletosRelacionesDTO 
                    = ((ProductoDatosCompletosRelacionesDTO)productoInventarioDatosCompletosRelacionesDTO.getProducto());
            
            
            productoDatosCompletosRelacionesDTO.getProductosInventario().add(productoInventario);
            
            
        }
        
        listaProductosInventario.addAll(productosInventario);
    }
    

    @Override
    public List<ProductoInventarioDTO> recuperarTodos() {
        return new ArrayList<>(listaProductosInventario);
    }

    @Override
    public long cuenta() {
        return listaProductosInventario.size();
    }

    @Override
    public boolean existe(Predicate<ProductoInventarioDTO> criterio) {
        return listaProductosInventario.stream().anyMatch(criterio);
    }
    
    
}
