
package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.infraestructura.ActualizacionCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.CarritoComprasDatosCompletosRelacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdCarritoComprasDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioCarritoCompras;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class RepositorioCarritoComprasEnMemoria implements RepositorioCarritoCompras{

    private final List<CarritoComprasDTO> listaCarritosCompras;
    
    private Long ID_ACTUAL_CARIITO = 1L;
    
    public RepositorioCarritoComprasEnMemoria() {
        listaCarritosCompras = new ArrayList<>();
    }
    
    public RepositorioCarritoComprasEnMemoria(Collection<CarritoComprasDTO> carritosCompras) {
        listaCarritosCompras = new ArrayList<>(carritosCompras);
    }
    
    @Override
    public CarritoComprasDTO recuperarPorId(IdCarritoComprasDTO idCarritoComprasDTO) {
        return listaCarritosCompras.stream()
                .filter(carritoCompras -> carritoCompras.getId().equals(idCarritoComprasDTO.getIdCarritoCompras()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdCarritoComprasDTO idCarritoComprasDTO) {
        return existe(carritoCompras -> carritoCompras.getId().equals(idCarritoComprasDTO.getIdCarritoCompras()));
    }
    
    @Override
    public CarritoComprasDTO actualizar(ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO) {
        for (int i = 0; i < listaCarritosCompras.size(); i++) {
            CarritoComprasDTO carritoCompras = listaCarritosCompras.get(i);
            if (carritoCompras.getId().equals(actualizacionCarritoComprasDTO.getId())) {
                CarritoComprasDTO carritoComprasActualizado = aplicar(carritoCompras, actualizacionCarritoComprasDTO);
                listaCarritosCompras.set(i, carritoComprasActualizado);
                return carritoComprasActualizado;
            }
        }
        return null;
    }

    @Override
    public Stream<CarritoComprasDTO> stream() {
        return listaCarritosCompras.stream();
    }

    @Override
    public void agregar(CarritoComprasDTO carritoCompras) {
        if(carritoCompras.getId() == null){
            carritoCompras.setId(ID_ACTUAL_CARIITO++);
        }
        listaCarritosCompras.add(carritoCompras);
    }

    @Override
    public void agregar(Collection<CarritoComprasDTO> carritosCompras) {
        for(CarritoComprasDTO carritoCompras: carritosCompras){
            if(carritoCompras.getId() == null){
                carritoCompras.setId(ID_ACTUAL_CARIITO++);
            }
        }
        listaCarritosCompras.addAll(carritosCompras);
    }

    @Override
    public List<CarritoComprasDTO> recuperarTodos() {
        return new ArrayList<>(listaCarritosCompras);
    }

    @Override
    public long cuenta() {
        return listaCarritosCompras.size();
    }

    @Override
    public boolean existe(Predicate<CarritoComprasDTO> criterio) {
        return listaCarritosCompras.stream().anyMatch(criterio);
    }
    
    private CarritoComprasDTO aplicar(CarritoComprasDTO carritoComprasOriginal, ActualizacionCarritoComprasDTO actualizacionCarritoComprasDTO) {
        
        return new CarritoComprasDatosCompletosRelacionesDTO(
                actualizacionCarritoComprasDTO.getId(),
                actualizacionCarritoComprasDTO.tieneEsVigente() ? actualizacionCarritoComprasDTO.getEsVigente() : carritoComprasOriginal.getEsVigente(),
                ((CarritoComprasDatosCompletosRelacionesDTO)carritoComprasOriginal).getCliente(),
                actualizacionCarritoComprasDTO.tienePaqueteria() 
                        ? actualizacionCarritoComprasDTO.getPaqueteria() 
                        : ((CarritoComprasDatosCompletosRelacionesDTO)carritoComprasOriginal).getPaqueteria(),
                ((CarritoComprasDatosCompletosRelacionesDTO)carritoComprasOriginal).getProductosCarrito()
        );
        
    }
    
}
