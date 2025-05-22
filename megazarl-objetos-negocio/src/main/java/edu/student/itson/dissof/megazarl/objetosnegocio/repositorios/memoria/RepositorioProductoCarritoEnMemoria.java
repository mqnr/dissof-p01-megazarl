
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProductoCarrito;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
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
                
                // Se agrega el producto en carrito de compras al carrito asociado.
                ProductoCarritoDTO productoCarritoActualizado = aplicar(productoCarrito, actualizacionProductoCarritoDTO);
                
                ProductoCarritoDatosCompletosRelacionesDTO productoCarritoDatosCompletosRelacionesDTO 
                        = (ProductoCarritoDatosCompletosRelacionesDTO) productoCarritoActualizado;
                
                CarritoComprasDatosCompletosRelacionesDTO carritoComprasDatosCompletosRelacionesDTO
                        = (CarritoComprasDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras();
                
                List<ProductoCarritoDTO> listaProductosCarritoCarritoCompras 
                        = carritoComprasDatosCompletosRelacionesDTO.getProductosCarrito();
                
                IntStream.range(0, listaProductosCarritoCarritoCompras.size())
                    .filter(j -> listaProductosCarritoCarritoCompras.get(j).getId().equals(productoCarritoActualizado.getId()))
                    .findFirst()
                    .ifPresent(j -> listaProductosCarritoCarritoCompras.set(j, productoCarritoActualizado));
          
                // Se agrega el producto en carrito de compras al producto asociado.
                ProductoDatosCompletosRelacionesDTO productoDatosCompletosRelacionesDTO 
                        = (ProductoDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getProducto();
                
                List<ProductoCarritoDTO> listaProductosCarritoProducto 
                        = productoDatosCompletosRelacionesDTO.getProductosCarrito();
                
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
        
        ProductoCarritoDatosCompletosRelacionesDTO productoCarritoDatosCompletosRelacionesDTO
                = (ProductoCarritoDatosCompletosRelacionesDTO) productoCarrito;
        
        
        CarritoComprasDatosCompletosRelacionesDTO carritoComprasDatosCompletosRelacionesDTO
                = (CarritoComprasDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras();
                
        ProductoDatosCompletosRelacionesDTO productoDatosCompletosRelacionesDTO
                = (ProductoDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getProducto();
        
        
        carritoComprasDatosCompletosRelacionesDTO.getProductosCarrito().add(productoCarrito);
        
        productoDatosCompletosRelacionesDTO.getProductosCarrito().add(productoCarrito);
        
        listaProductosCarrito.add(productoCarrito);
        
        
    }

    @Override
    public void agregar(Collection<ProductoCarritoDTO> productosCarrito) {
        
        for(ProductoCarritoDTO productoCarrito: productosCarrito){
            productoCarrito.setId(ID_PAQUETERIA_ACTUAL++);
                
            ProductoCarritoDatosCompletosRelacionesDTO productoCarritoDatosCompletosRelacionesDTO
                = (ProductoCarritoDatosCompletosRelacionesDTO) productoCarrito;
            
            CarritoComprasDatosCompletosRelacionesDTO carritoComprasDatosCompletosRelacionesDTO
                = (CarritoComprasDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras();
                
            ProductoDatosCompletosRelacionesDTO productoDatosCompletosRelacionesDTO
                = (ProductoDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getProducto();
        
        
        carritoComprasDatosCompletosRelacionesDTO.getProductosCarrito().add(productoCarrito);
        
        productoDatosCompletosRelacionesDTO.getProductosCarrito().add(productoCarrito);
        }
        this.listaProductosCarrito.addAll(productosCarrito);
    }
    
    @Override
    public void removerPorId(IdProductoCarritoDTO idProductoCarritoDTO) {
        
        ProductoCarritoDTO productoCarritoDTO = listaProductosCarrito.stream()
            .filter(productoCarrito -> productoCarrito.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()))
            .findFirst()
            .orElse(null);
        
        ProductoCarritoDatosCompletosRelacionesDTO productoCarritoDatosCompletosRelacionesDTO
                = (ProductoCarritoDatosCompletosRelacionesDTO) productoCarritoDTO;
        
        ((CarritoComprasDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras())
        .getProductosCarrito()
        .remove(
            ((CarritoComprasDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras())
                .getProductosCarrito()
                .stream()
                .filter(productoCarrito -> productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras() != null &&
                    productoCarritoDatosCompletosRelacionesDTO.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()))
                .findFirst()
                .orElse(null)
        );
        
        ((ProductoDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getProducto())
        .getProductosCarrito()
        .remove(
            ((ProductoDatosCompletosRelacionesDTO) productoCarritoDatosCompletosRelacionesDTO.getProducto())
                .getProductosCarrito()
                .stream()
                .filter(productoCarrito -> productoCarritoDatosCompletosRelacionesDTO.getProducto() != null &&
                    productoCarritoDatosCompletosRelacionesDTO.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()))
                .findFirst()
                .orElse(null)
        );
        
        listaProductosCarrito.remove(
            listaProductosCarrito.stream()
                .filter(productoCarrito ->
                    productoCarritoDatosCompletosRelacionesDTO.getProducto() != null &&
                    productoCarritoDatosCompletosRelacionesDTO.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()))
                .findFirst()
                .orElse(null)
        );
        
        
    }

    @Override
    public List<ProductoCarritoDTO> recuperarTodos() {
        return new ArrayList<>(listaProductosCarrito);
    }

    @Override
    public boolean existe(Predicate<ProductoCarritoDTO> criterio) {
        return listaProductosCarrito.stream().anyMatch(criterio);
    }
    
    private ProductoCarritoDTO aplicar(ProductoCarritoDTO productoCarritoOriginal, ActualizacionProductoCarritoDTO actualizacionProductoCarritoDTO) {
        
        return new ProductoCarritoDatosCompletosRelacionesDTO(
                productoCarritoOriginal.getId(),
                actualizacionProductoCarritoDTO.tieneCantidad() ? actualizacionProductoCarritoDTO.getCantidad() : productoCarritoOriginal.getCantidad(),
                ((ProductoCarritoDatosCompletosRelacionesDTO)productoCarritoOriginal).getCarritoCompras(),
                ((ProductoCarritoDatosCompletosRelacionesDTO)productoCarritoOriginal).getProducto()    
        );
        
    }

}
