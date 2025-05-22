
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;


import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenerico;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProductoInventario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;
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
        
        productoInventario.setId(new IdEntidadGenerico(ID_ACTUAL_PRODUCTO_INVENTARIO++));
        
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
            
            productoInventario.setId(new IdEntidadGenerico(ID_ACTUAL_PRODUCTO_INVENTARIO++));
            
            ProductoInventarioDatosCompletosRelacionesDTO productoInventarioDatosCompletosRelacionesDTO 
                    = (ProductoInventarioDatosCompletosRelacionesDTO) productoInventario;
            
            ProductoDatosCompletosRelacionesDTO productoDatosCompletosRelacionesDTO 
                    = ((ProductoDatosCompletosRelacionesDTO)productoInventarioDatosCompletosRelacionesDTO.getProducto());
            
            
            productoDatosCompletosRelacionesDTO.getProductosInventario().add(productoInventario);
            
            
        }
        
        listaProductosInventario.addAll(productosInventario);
    }
    
    @Override
    public ProductoInventarioDTO actualizar(ActualizacionProductoInventarioDTO actualizacionProductoInventarioDTO) {
        
        for (int i = 0; i < listaProductosInventario.size(); i++) {
            ProductoInventarioDTO productoInventario = listaProductosInventario.get(i);
            
            if (productoInventario.getId().equals(actualizacionProductoInventarioDTO.getId())) {
                ProductoInventarioDTO productoInventarioActualizado = aplicar(productoInventario, actualizacionProductoInventarioDTO);
                listaProductosInventario.set(i, productoInventarioActualizado);
                
                ProductoInventarioDatosCompletosRelacionesDTO productoInventarioDatosCompletosRelacionesDTO = 
                        (ProductoInventarioDatosCompletosRelacionesDTO) productoInventarioActualizado;
                
                ProductoDatosCompletosRelacionesDTO productoDatosCompletosDTO 
                        = (ProductoDatosCompletosRelacionesDTO) productoInventarioDatosCompletosRelacionesDTO.getProducto();
                
                IntStream.range(0, productoDatosCompletosDTO.getProductosInventario().size())
                    .filter(j -> productoDatosCompletosDTO.getProductosInventario().get(j).getId().equals(productoInventarioActualizado.getId()))
                    .findFirst()
                    .ifPresent(j -> productoDatosCompletosDTO.getProductosInventario().set(j, productoInventarioActualizado));
                
                SucursalDatosCompletosRelacionesDTO sucursalDatosCompletosDTO 
                        = (SucursalDatosCompletosRelacionesDTO) productoInventarioDatosCompletosRelacionesDTO.getSucursal();
                
                IntStream.range(0, sucursalDatosCompletosDTO.getProductosInventario().size())
                    .filter(j -> sucursalDatosCompletosDTO.getProductosInventario().get(j).getId().equals(productoInventarioActualizado.getId()))
                    .findFirst()
                    .ifPresent(j -> productoDatosCompletosDTO.getProductosInventario().set(j, productoInventarioActualizado));
                
            
                return productoInventarioActualizado;
            }
        }
        return null;
    }
    
    @Override
    public List<ProductoInventarioDTO> recuperarTodos() {
        return new ArrayList<>(listaProductosInventario);
    }

    @Override
    public boolean existe(Predicate<ProductoInventarioDTO> criterio) {
        return listaProductosInventario.stream().anyMatch(criterio);
    }


    private ProductoInventarioDTO aplicar(ProductoInventarioDTO productoInventarioOriginal, ActualizacionProductoInventarioDTO actualizacionProductoInventarioDTO) {
        
        return new ProductoInventarioDatosCompletosRelacionesDTO(
                productoInventarioOriginal.getId(),
                actualizacionProductoInventarioDTO.tieneApartado() ? actualizacionProductoInventarioDTO.getApartado() : productoInventarioOriginal.getApartado(),
                ((ProductoInventarioDatosCompletosRelacionesDTO)productoInventarioOriginal).getProducto(),
                ((ProductoInventarioDatosCompletosRelacionesDTO)productoInventarioOriginal).getSucursal()
        );
        
    }
    
    
}
