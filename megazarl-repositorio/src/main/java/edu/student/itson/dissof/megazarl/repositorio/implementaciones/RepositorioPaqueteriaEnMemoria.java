package edu.student.itson.dissof.megazarl.repositorio.implementaciones;

import edu.student.itson.dissof.megazarl.dto.modelos.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.repositorio.actualizaciones.ActualizacionPaqueteria;
import edu.student.itson.dissof.megazarl.repositorio.entidades.RepositorioPaqueteria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class RepositorioPaqueteriaEnMemoria implements RepositorioPaqueteria {
    private final List<PaqueteriaDTO> paqueterias;

    public RepositorioPaqueteriaEnMemoria() {
        paqueterias = new ArrayList<>();
    }

    public RepositorioPaqueteriaEnMemoria(Collection<PaqueteriaDTO> paqueterias) {
        this.paqueterias = new ArrayList<>(paqueterias);
    }

    @Override
    public PaqueteriaDTO buscarPorId(Integer id) {
        return paqueterias.stream()
                .filter(paqueteria -> paqueteria.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean existePorId(Integer id) {
        return existe(paqueteria -> paqueteria.id().equals(id));
    }

    @Override
    public PaqueteriaDTO actualizar(Integer id, ActualizacionPaqueteria actualizacion) {
        for (int i = 0; i < paqueterias.size(); i++) {
            PaqueteriaDTO cliente = paqueterias.get(i);
            if (cliente.id().equals(id)) {
                PaqueteriaDTO clienteActualizado = aplicar(cliente, actualizacion);
                paqueterias.set(i, clienteActualizado);
                return clienteActualizado;
            }
        }
        return null;
    }

    public Stream<PaqueteriaDTO> stream() {
        return paqueterias.stream();
    }

    @Override
    public void guardar(PaqueteriaDTO paqueteria) {
        paqueterias.add(paqueteria);
    }

    @Override
    public void guardarMuchos(Collection<PaqueteriaDTO> paqueterias) {
        this.paqueterias.addAll(paqueterias);
    }

    @Override
    public List<PaqueteriaDTO> encontrarTodos() {
        return new ArrayList<>(paqueterias);
    }

    @Override
    public long cuenta() {
        return paqueterias.size();
    }

    @Override
    public boolean existe(Predicate<PaqueteriaDTO> criterio) {
        return paqueterias.stream().anyMatch(criterio);
    }

    private PaqueteriaDTO aplicar(PaqueteriaDTO paqueteriaOriginal, ActualizacionPaqueteria actualizacion) {
        return new PaqueteriaDTO(
                paqueteriaOriginal.id(),
                actualizacion.tieneNombre() ? actualizacion.getNombre() : paqueteriaOriginal.nombre(),
                actualizacion.tieneCobroKg() ? actualizacion.getCobroKg() : paqueteriaOriginal.cobroKg(),
                actualizacion.tieneCobroHora() ? actualizacion.getCobroHora() : paqueteriaOriginal.cobroHora(),
                actualizacion.tieneDireccionImagenPaqueteria() ? actualizacion.getDireccionImagenPaqueteria() : paqueteriaOriginal.direccionImagenPaqueteria()
        );
    }
}
