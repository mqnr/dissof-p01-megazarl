
package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ProductoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProductoDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioProducto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class RepositorioProductoEnMemoria implements RepositorioProducto{

    private final List<ProductoDTO> listaProductos;
    
    public RepositorioProductoEnMemoria() {
        listaProductos = new ArrayList<>();
    }
    
    public RepositorioProductoEnMemoria(Collection<ProductoDTO> productos) {
        listaProductos = new ArrayList<>(productos);
    }
    
    @Override
    public ProductoDTO recuperarPorId(IdProductoDTO idProductoDTO) {
        return listaProductos.stream()
                .filter(producto -> producto.getId().equals(idProductoDTO.getIdProducto()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdProductoDTO idProductoDTO) {
        return existe(producto -> producto.getId().equals(idProductoDTO.getIdProducto()));
    }

    @Override
    public Stream<ProductoDTO> stream() {
        return listaProductos.stream();
    }

    @Override
    public void agregar(ProductoDTO producto) {
        listaProductos.add(producto);
    }

    @Override
    public void agregar(Collection<ProductoDTO> productos) {
        listaProductos.addAll(productos);
    }

    @Override
    public List<ProductoDTO> recuperarTodos() {
        return new ArrayList<>(listaProductos);
    }

    @Override
    public long cuenta() {
        return listaProductos.size();
    }

    @Override
    public boolean existe(Predicate<ProductoDTO> criterio) {
        return listaProductos.stream().anyMatch(criterio);
    }
    
}
