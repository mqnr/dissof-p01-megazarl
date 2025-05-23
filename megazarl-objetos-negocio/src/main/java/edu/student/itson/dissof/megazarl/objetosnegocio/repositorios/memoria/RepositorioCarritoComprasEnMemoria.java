
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CarritoComprasDatosCompletosRelacionesDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdCarritoComprasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCarritoCompras;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class RepositorioCarritoComprasEnMemoria implements RepositorioCarritoCompras{

    private final List<CarritoComprasDTONegocios> listaCarritosCompras;
    
    private Long ID_ACTUAL_CARIITO = 1L;
    
    public RepositorioCarritoComprasEnMemoria() {
        listaCarritosCompras = new ArrayList<>();
    }
    
    public RepositorioCarritoComprasEnMemoria(Collection<CarritoComprasDTONegocios> carritosCompras) {
        listaCarritosCompras = new ArrayList<>(carritosCompras);
    }
    
    @Override
    public CarritoComprasDTONegocios recuperarPorId(IdCarritoComprasDTONegocios idCarritoComprasDTO) {
        return listaCarritosCompras.stream()
                .filter(carritoCompras -> carritoCompras.getId().equals(idCarritoComprasDTO.getIdCarritoCompras()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdCarritoComprasDTONegocios idCarritoComprasDTO) {
        
        return listaCarritosCompras.stream().anyMatch(carritoCompras -> carritoCompras.getId().equals(idCarritoComprasDTO.getIdCarritoCompras()));

    }
    
    @Override
    public CarritoComprasDTONegocios actualizar(ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTO) {
        for (int i = 0; i < listaCarritosCompras.size(); i++) {
            CarritoComprasDTONegocios carritoCompras = listaCarritosCompras.get(i);
            if (carritoCompras.getId().getId().equals(actualizacionCarritoComprasDTO.getId().getId())) {
                CarritoComprasDTONegocios carritoComprasActualizado = aplicar(carritoCompras, actualizacionCarritoComprasDTO);
                listaCarritosCompras.set(i, carritoComprasActualizado);
                return carritoComprasActualizado;
            }
        }
        return null;
    }

    @Override
    public void agregar(CarritoComprasDTONegocios carritoCompras) {
        if(carritoCompras.getId() == null){
            carritoCompras.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_CARIITO++));
        }
        listaCarritosCompras.add(carritoCompras);
    }

    @Override
    public void agregar(Collection<CarritoComprasDTONegocios> carritosCompras) {
        for(CarritoComprasDTONegocios carritoCompras: carritosCompras){
            if(carritoCompras.getId() == null){
                carritoCompras.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_CARIITO++));
            }
        }
        listaCarritosCompras.addAll(carritosCompras);
    }

    @Override
    public List<CarritoComprasDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaCarritosCompras);
    }
    
    private CarritoComprasDTONegocios aplicar(CarritoComprasDTONegocios carritoComprasOriginal, ActualizacionCarritoComprasDTONegocios actualizacionCarritoComprasDTO) {
        
        return new CarritoComprasDatosCompletosRelacionesDTONegocios(
                new IdEntidadGenericoNegocios(actualizacionCarritoComprasDTO.getId().getId()),
                actualizacionCarritoComprasDTO.tieneEsVigente() ? actualizacionCarritoComprasDTO.getEsVigente() : carritoComprasOriginal.getEsVigente(),
                ((CarritoComprasDatosCompletosRelacionesDTONegocios)carritoComprasOriginal).getCliente(),
                actualizacionCarritoComprasDTO.tienePaqueteria() 
                        ? actualizacionCarritoComprasDTO.getPaqueteria() 
                        : ((CarritoComprasDatosCompletosRelacionesDTONegocios)carritoComprasOriginal).getPaqueteria(),
                ((CarritoComprasDatosCompletosRelacionesDTONegocios)carritoComprasOriginal).getProductosCarrito()
        );
        
    }

    
}
