
package edu.student.itson.dissof.megazarl.objetosnegocio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.objetosnegocio.DireccionDTO;
import edu.student.itson.dissof.megazarl.interfaces.RepositorioDireccion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioDireccionEnMemoria implements RepositorioDireccion{
    private final List<DireccionDTO> listaDirecciones;

     private Long ID_ACTUAL_DIRECCION = 1L;
    
    public RepositorioDireccionEnMemoria() {
        listaDirecciones = new ArrayList<>();
    }

    public RepositorioDireccionEnMemoria(Collection<DireccionDTO> direcciones) {
        listaDirecciones = new ArrayList<>(direcciones);
    }

    @Override
    public DireccionDTO recuperarPorId(IdDireccionDTO idDireccionDTO) {
        return listaDirecciones.stream()
                .filter(direccion -> direccion.getId().equals(idDireccionDTO.getIdDireccion()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdDireccionDTO idDireccionDTO) {
        return existe(direccion -> direccion.getId().equals(idDireccionDTO.getIdDireccion()));
    }

    @Override
    public DireccionDTO actualizar(ActualizacionDireccionDTO actualizacionDireccionDTO) {
        
        for (int i = 0; i < listaDirecciones.size(); i++) {
            DireccionDTO direccion = listaDirecciones.get(i);
            if (direccion.getId().equals(actualizacionDireccionDTO.getId())) {
                DireccionDTO direccionActualizada = aplicar(direccion, actualizacionDireccionDTO);
                listaDirecciones.set(i, direccionActualizada);
                return direccionActualizada;
            }
        }
        return null;
    }

    @Override
    public Stream<DireccionDTO> stream() {
        return listaDirecciones.stream();
    }

    @Override
    public void agregar(DireccionDTO direccion) {
        direccion.setId(ID_ACTUAL_DIRECCION++);
        listaDirecciones.add(direccion);
    }

    @Override
    public void agregar(Collection<DireccionDTO> direcciones) {
         for(DireccionDTO direccion: direcciones){
            if(direccion.getId() == null){
                direccion.setId(ID_ACTUAL_DIRECCION++);
            }
        }
        listaDirecciones.addAll(direcciones);
    }

    @Override
    public List<DireccionDTO> recuperarTodos() {
        return new ArrayList<>(listaDirecciones);
    }

    @Override
    public long cuenta() {
        return listaDirecciones.size();
    }

    @Override
    public boolean existe(Predicate<DireccionDTO> criterio) {
        return listaDirecciones.stream().anyMatch(criterio);
    }

    private DireccionDTO aplicar(DireccionDTO direccionOriginal, ActualizacionDireccionDTO actualizacionDireccion) {
        return new DireccionDTO(
                actualizacionDireccion.getId(),
                actualizacionDireccion.tieneEstado() ? actualizacionDireccion.getEstado(): direccionOriginal.getEstado(),
                actualizacionDireccion.tieneCiudad() ? actualizacionDireccion.getCiudad() : direccionOriginal.getCiudad(),
                actualizacionDireccion.tieneCodigoPostal() ? actualizacionDireccion.getCodigoPostal(): direccionOriginal.getCodigoPostal(),
                actualizacionDireccion.tieneColonia() ? actualizacionDireccion.getColonia(): direccionOriginal.getColonia(),
                actualizacionDireccion.tieneCalle() ? actualizacionDireccion.getCalle() : direccionOriginal.getCalle(),
                actualizacionDireccion.tieneNumero() ? actualizacionDireccion.getNumero() : direccionOriginal.getNumero()
        );
    }
}
