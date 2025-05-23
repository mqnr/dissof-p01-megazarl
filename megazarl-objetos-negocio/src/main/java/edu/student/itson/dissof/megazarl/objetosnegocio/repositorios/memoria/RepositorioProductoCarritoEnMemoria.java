
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoCarritoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoCarritoDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProductoCarrito;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;


public class RepositorioProductoCarritoEnMemoria implements RepositorioProductoCarrito{
    
    private final List<ProductoCarritoDTONegocios> listaProductosCarrito;
    
    private static Long ID_PAQUETERIA_ACTUAL = 1L;

    
    public RepositorioProductoCarritoEnMemoria() {
        listaProductosCarrito = new ArrayList<>();
    }

    public RepositorioProductoCarritoEnMemoria(Collection<ProductoCarritoDTONegocios> productosCarrito) {
        this.listaProductosCarrito = new ArrayList<>(productosCarrito);
    }

    @Override
    public ProductoCarritoDTONegocios recuperarPorId(IdProductoCarritoDTONegocios idProductoCarritoDTO) {
        return listaProductosCarrito.stream()
                .filter(productoCarrito -> productoCarrito.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProductoCarritoDTONegocios idProductoCarritoDTO) {
        
        return listaProductosCarrito.stream().anyMatch(paqueteria -> paqueteria.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()));
        
    }
    
    @Override
    public ProductoCarritoDTONegocios actualizar(ActualizacionProductoCarritoDTONegocios actualizacionProductoCarritoDTO) {
        
        
        for (int i = 0; i < listaProductosCarrito.size(); i++) {
                
            ProductoCarritoDTONegocios productoCarrito = listaProductosCarrito.get(i);
            
            if (productoCarrito.getId().equals(actualizacionProductoCarritoDTO.getId())) {
                
                // Se agrega el producto en carrito de compras al carrito asociado.
                ProductoCarritoDTONegocios productoCarritoActualizado = aplicar(productoCarrito, actualizacionProductoCarritoDTO);
                
                ProductoCarritoDatosCompletosRelacionesDTONegocios productoCarritoDatosCompletosRelacionesDTO 
                        = (ProductoCarritoDatosCompletosRelacionesDTONegocios) productoCarritoActualizado;
                
                CarritoComprasDatosCompletosRelacionesDTONegocios carritoComprasDatosCompletosRelacionesDTO
                        = (CarritoComprasDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras();
                
                List<ProductoCarritoDTONegocios> listaProductosCarritoCarritoCompras 
                        = carritoComprasDatosCompletosRelacionesDTO.getProductosCarrito();
                
                IntStream.range(0, listaProductosCarritoCarritoCompras.size())
                    .filter(j -> listaProductosCarritoCarritoCompras.get(j).getId().equals(productoCarritoActualizado.getId()))
                    .findFirst()
                    .ifPresent(j -> listaProductosCarritoCarritoCompras.set(j, productoCarritoActualizado));
          
                // Se agrega el producto en carrito de compras al producto asociado.
                ProductoDatosCompletosRelacionesDTONegocios productoDatosCompletosRelacionesDTO 
                        = (ProductoDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getProducto();
                
                List<ProductoCarritoDTONegocios> listaProductosCarritoProducto 
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
    public void agregar(ProductoCarritoDTONegocios productoCarrito) {
        productoCarrito.setId(new IdEntidadGenericoNegocios(ID_PAQUETERIA_ACTUAL++));
        
        ProductoCarritoDatosCompletosRelacionesDTONegocios productoCarritoDatosCompletosRelacionesDTO
                = (ProductoCarritoDatosCompletosRelacionesDTONegocios) productoCarrito;
        
        
        CarritoComprasDatosCompletosRelacionesDTONegocios carritoComprasDatosCompletosRelacionesDTO
                = (CarritoComprasDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras();
                
        ProductoDatosCompletosRelacionesDTONegocios productoDatosCompletosRelacionesDTO
                = (ProductoDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getProducto();
        
        
        carritoComprasDatosCompletosRelacionesDTO.getProductosCarrito().add(productoCarrito);
        
        productoDatosCompletosRelacionesDTO.getProductosCarrito().add(productoCarrito);
        
        listaProductosCarrito.add(productoCarrito);
        
        
    }

    @Override
    public void agregar(Collection<ProductoCarritoDTONegocios> productosCarrito) {
        
        for(ProductoCarritoDTONegocios productoCarrito: productosCarrito){
            productoCarrito.setId(new IdEntidadGenericoNegocios(ID_PAQUETERIA_ACTUAL++));
                
            ProductoCarritoDatosCompletosRelacionesDTONegocios productoCarritoDatosCompletosRelacionesDTO
                = (ProductoCarritoDatosCompletosRelacionesDTONegocios) productoCarrito;
            
            CarritoComprasDatosCompletosRelacionesDTONegocios carritoComprasDatosCompletosRelacionesDTO
                = (CarritoComprasDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras();
                
            ProductoDatosCompletosRelacionesDTONegocios productoDatosCompletosRelacionesDTO
                = (ProductoDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getProducto();
        
        
        carritoComprasDatosCompletosRelacionesDTO.getProductosCarrito().add(productoCarrito);
        
        productoDatosCompletosRelacionesDTO.getProductosCarrito().add(productoCarrito);
        }
        this.listaProductosCarrito.addAll(productosCarrito);
    }
    
    @Override
    public void removerPorId(IdProductoCarritoDTONegocios idProductoCarritoDTO) {
        
        ProductoCarritoDTONegocios productoCarritoDTO = listaProductosCarrito.stream()
            .filter(productoCarrito -> productoCarrito.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()))
            .findFirst()
            .orElse(null);
        
        ProductoCarritoDatosCompletosRelacionesDTONegocios productoCarritoDatosCompletosRelacionesDTO
                = (ProductoCarritoDatosCompletosRelacionesDTONegocios) productoCarritoDTO;
        
        ((CarritoComprasDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras())
        .getProductosCarrito()
        .remove(((CarritoComprasDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras())
                .getProductosCarrito()
                .stream()
                .filter(productoCarrito -> productoCarritoDatosCompletosRelacionesDTO.getCarritoCompras() != null &&
                    productoCarritoDatosCompletosRelacionesDTO.getId().equals(idProductoCarritoDTO.getIdProductoCarrito()))
                .findFirst()
                .orElse(null)
        );
        
        ((ProductoDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getProducto())
        .getProductosCarrito()
        .remove(((ProductoDatosCompletosRelacionesDTONegocios) productoCarritoDatosCompletosRelacionesDTO.getProducto())
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
    public List<ProductoCarritoDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaProductosCarrito);
    }
    
    private ProductoCarritoDTONegocios aplicar(ProductoCarritoDTONegocios productoCarritoOriginal, ActualizacionProductoCarritoDTONegocios actualizacionProductoCarritoDTO) {
        
        return new ProductoCarritoDatosCompletosRelacionesDTONegocios(
                productoCarritoOriginal.getId(),
                actualizacionProductoCarritoDTO.tieneCantidad() ? actualizacionProductoCarritoDTO.getCantidad() : productoCarritoOriginal.getCantidad(),
                ((ProductoCarritoDatosCompletosRelacionesDTONegocios)productoCarritoOriginal).getCarritoCompras(),
                ((ProductoCarritoDatosCompletosRelacionesDTONegocios)productoCarritoOriginal).getProducto()    
        );
        
    }

}
