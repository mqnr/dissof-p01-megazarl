
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;


import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoInventarioDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoInventarioDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProductoInventario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;


public class RepositorioProductoInventarioEnMemoria implements RepositorioProductoInventario{
    
    private final List<ProductoInventarioDTONegocios> listaProductosInventario;
    
    private static Long ID_ACTUAL_PRODUCTO_INVENTARIO = 1L;
    
    public RepositorioProductoInventarioEnMemoria() {
        listaProductosInventario = new ArrayList<>();
    }
    
    public RepositorioProductoInventarioEnMemoria(Collection<ProductoInventarioDTONegocios> productosInventario) {
        listaProductosInventario = new ArrayList<>(productosInventario);
    }
    
    @Override
    public ProductoInventarioDTONegocios recuperarPorId(IdProductoInventarioDTONegocios idProductoInventarioDTO) {
        return listaProductosInventario.stream()
                .filter(productoInventario -> productoInventario.getId().equals(idProductoInventarioDTO.getIdProductoInventario()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProductoInventarioDTONegocios idProductoInventarioDTO) {
        
        return listaProductosInventario.stream().anyMatch(productoInventario 
                -> productoInventario.getId().equals(idProductoInventarioDTO.getIdProductoInventario()));

    }

    @Override
    public void agregar(ProductoInventarioDTONegocios productoInventario) {
        
        productoInventario.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_PRODUCTO_INVENTARIO++));
        
        ProductoInventarioDatosCompletosRelacionesDTONegocios productoInventarioDatosCompletosRelacionesDTO 
                    = (ProductoInventarioDatosCompletosRelacionesDTONegocios) productoInventario;
            
            ProductoDatosCompletosRelacionesDTONegocios productoDatosCompletosRelacionesDTO 
                    = ((ProductoDatosCompletosRelacionesDTONegocios)productoInventarioDatosCompletosRelacionesDTO.getProducto());
            
            
            productoDatosCompletosRelacionesDTO.getProductosInventario().add(productoInventario);
            
        listaProductosInventario.add(productoInventario);
        
    }

    @Override
    public void agregar(Collection<ProductoInventarioDTONegocios> productosInventario) {
        
        for(ProductoInventarioDTONegocios productoInventario: productosInventario){
            
            productoInventario.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_PRODUCTO_INVENTARIO++));
            
            ProductoInventarioDatosCompletosRelacionesDTONegocios productoInventarioDatosCompletosRelacionesDTO 
                    = (ProductoInventarioDatosCompletosRelacionesDTONegocios) productoInventario;
            
            ProductoDatosCompletosRelacionesDTONegocios productoDatosCompletosRelacionesDTO 
                    = ((ProductoDatosCompletosRelacionesDTONegocios)productoInventarioDatosCompletosRelacionesDTO.getProducto());
            
            
            productoDatosCompletosRelacionesDTO.getProductosInventario().add(productoInventario);
            
            
        }
        
        listaProductosInventario.addAll(productosInventario);
    }
    
    @Override
    public ProductoInventarioDTONegocios actualizar(ActualizacionProductoInventarioDTONegocios actualizacionProductoInventarioDTO) {
        
        for (int i = 0; i < listaProductosInventario.size(); i++) {
            ProductoInventarioDTONegocios productoInventario = listaProductosInventario.get(i);
            
            if (productoInventario.getId().equals(actualizacionProductoInventarioDTO.getId())) {
                ProductoInventarioDTONegocios productoInventarioActualizado = aplicar(productoInventario, actualizacionProductoInventarioDTO);
                listaProductosInventario.set(i, productoInventarioActualizado);
                
                ProductoInventarioDatosCompletosRelacionesDTONegocios productoInventarioDatosCompletosRelacionesDTO = 
                        (ProductoInventarioDatosCompletosRelacionesDTONegocios) productoInventarioActualizado;
                
                ProductoDatosCompletosRelacionesDTONegocios productoDatosCompletosDTO 
                        = (ProductoDatosCompletosRelacionesDTONegocios) productoInventarioDatosCompletosRelacionesDTO.getProducto();
                
                IntStream.range(0, productoDatosCompletosDTO.getProductosInventario().size())
                    .filter(j -> productoDatosCompletosDTO.getProductosInventario().get(j).getId().equals(productoInventarioActualizado.getId()))
                    .findFirst()
                    .ifPresent(j -> productoDatosCompletosDTO.getProductosInventario().set(j, productoInventarioActualizado));
                
                SucursalDatosCompletosRelacionesDTONegocios sucursalDatosCompletosDTO 
                        = (SucursalDatosCompletosRelacionesDTONegocios) productoInventarioDatosCompletosRelacionesDTO.getSucursal();
                
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
    public List<ProductoInventarioDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaProductosInventario);
    }

    private ProductoInventarioDTONegocios aplicar(ProductoInventarioDTONegocios productoInventarioOriginal, ActualizacionProductoInventarioDTONegocios actualizacionProductoInventarioDTO) {
        
        return new ProductoInventarioDatosCompletosRelacionesDTONegocios(
                productoInventarioOriginal.getId(),
                actualizacionProductoInventarioDTO.tieneApartado() ? actualizacionProductoInventarioDTO.getApartado() : productoInventarioOriginal.getApartado(),
                ((ProductoInventarioDatosCompletosRelacionesDTONegocios)productoInventarioOriginal).getProducto(),
                ((ProductoInventarioDatosCompletosRelacionesDTONegocios)productoInventarioOriginal).getSucursal()
        );
        
    }
    
    
}
