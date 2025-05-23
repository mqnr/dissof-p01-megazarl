
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.ProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProducto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RepositorioProductoEnMemoria implements RepositorioProducto{

    private final List<ProductoDTONegocios> listaProductos;
    
    private static Long ID_PRODUCTO_ACTUAL = 1L;
    
    public RepositorioProductoEnMemoria() {
        listaProductos = new ArrayList<>();
    }
    
    public RepositorioProductoEnMemoria(Collection<ProductoDTONegocios> productos) {
        listaProductos = new ArrayList<>(productos);
    }
    
    @Override
    public ProductoDTONegocios recuperarPorId(IdProductoDTONegocios idProductoDTO) {
        return listaProductos.stream()
                .filter(producto -> producto.getId().equals(idProductoDTO.getIdProducto()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProductoDTONegocios idProductoDTO) {
        
        return listaProductos.stream().anyMatch(producto -> producto.getId().equals(idProductoDTO.getIdProducto()));

    }

    @Override
    public void agregar(ProductoDTONegocios producto) {
        
        producto.setId(new IdEntidadGenericoNegocios(ID_PRODUCTO_ACTUAL++));
        listaProductos.add(producto);
    }

    @Override
    public void agregar(Collection<ProductoDTONegocios> productos) {
        for(ProductoDTONegocios producto: productos){

            producto.setId(new IdEntidadGenericoNegocios(ID_PRODUCTO_ACTUAL++));
            
        }
        listaProductos.addAll(productos);
    }

    @Override
    public List<ProductoDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaProductos);
    }
    
}
