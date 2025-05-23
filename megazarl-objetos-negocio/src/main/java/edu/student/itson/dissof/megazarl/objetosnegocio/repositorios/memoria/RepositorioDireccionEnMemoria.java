
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.memoria;

import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioDireccion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioDireccionEnMemoria implements RepositorioDireccion{
    private final List<DireccionDTONegocios> listaDirecciones;

     private static Long ID_ACTUAL_DIRECCION = 1L;
    
    public RepositorioDireccionEnMemoria() {
        listaDirecciones = new ArrayList<>();
    }

    public RepositorioDireccionEnMemoria(Collection<DireccionDTONegocios> direcciones) {
        listaDirecciones = new ArrayList<>(direcciones);
    }

    @Override
    public DireccionDTONegocios recuperarPorId(IdDireccionDTONegocios idDireccionDTO) {
        return listaDirecciones.stream()
                .filter(direccion -> direccion.getId().equals(idDireccionDTO.getIdDireccion()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(IdDireccionDTONegocios idDireccionDTO) {
        
        return listaDirecciones.stream().anyMatch(direccion -> direccion.getId().equals(idDireccionDTO.getIdDireccion()));

    }

    @Override
    public DireccionDTONegocios actualizar(ActualizacionDireccionDTONegocios actualizacionDireccionDTO) {
        
        for (int i = 0; i < listaDirecciones.size(); i++) {
            DireccionDTONegocios direccion = listaDirecciones.get(i);
            if (direccion.getId().equals(actualizacionDireccionDTO.getId())) {
                DireccionDTONegocios direccionActualizada = aplicar(direccion, actualizacionDireccionDTO);
                listaDirecciones.set(i, direccionActualizada);
                return direccionActualizada;
            }
        }
        return null;
    }

    @Override
    public void agregar(DireccionDTONegocios direccion) {
        direccion.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_DIRECCION++));
        listaDirecciones.add(direccion);
    }

    @Override
    public void agregar(Collection<DireccionDTONegocios> direcciones) {
         for(DireccionDTONegocios direccion: direcciones){
            if(direccion.getId() == null){
                direccion.setId(new IdEntidadGenericoNegocios(ID_ACTUAL_DIRECCION++));
            }
        }
        listaDirecciones.addAll(direcciones);
    }

    @Override
    public List<DireccionDTONegocios> recuperarTodos() {
        return new ArrayList<>(listaDirecciones);
    }

    private DireccionDTONegocios aplicar(DireccionDTONegocios direccionOriginal, ActualizacionDireccionDTONegocios actualizacionDireccion) {
        return new DireccionDTONegocios(
                new IdEntidadGenericoNegocios(actualizacionDireccion.getId()),
                actualizacionDireccion.tieneEstado() ? actualizacionDireccion.getEstado(): direccionOriginal.getEstado(),
                actualizacionDireccion.tieneCiudad() ? actualizacionDireccion.getCiudad() : direccionOriginal.getCiudad(),
                actualizacionDireccion.tieneCodigoPostal() ? actualizacionDireccion.getCodigoPostal(): direccionOriginal.getCodigoPostal(),
                actualizacionDireccion.tieneColonia() ? actualizacionDireccion.getColonia(): direccionOriginal.getColonia(),
                actualizacionDireccion.tieneCalle() ? actualizacionDireccion.getCalle() : direccionOriginal.getCalle(),
                actualizacionDireccion.tieneNumero() ? actualizacionDireccion.getNumero() : direccionOriginal.getNumero()
        );
    }
}
