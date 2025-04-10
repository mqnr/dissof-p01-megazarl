package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.dto.DireccionClientePesoTiempoEnvioPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.modelos.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.repositorio.entidades.Paqueteria;

import java.util.List;

enum AdministradorPaqueterias implements IAdministradorPaqueterias {
    INSTANCIA;

    @Override
    public boolean validarId(Integer id) {
        return Paqueteria.existePorId(id);
    }

    @Override
    public PaqueteriaDTO obtenerPaqueteria(Integer id) {
        return Paqueteria.buscarPorId(id);
    }

    @Override
    public List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias() {
        return Paqueteria.stream().map(
                paqueteria ->
                        new InformacionSeleccionPaqueteriaDTO(
                                paqueteria.id(),
                                paqueteria.direccionImagenPaqueteria()
                        )
        ).toList();
    }

    @Override
    public Float obtenerCostoEnvioProducto(DireccionClientePesoTiempoEnvioPaqueteriaDTO direccionClientePesoTiempoEnvioPaqueteriaDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException{
        Integer id = direccionClientePesoTiempoEnvioPaqueteriaDTO.getCodigoPaqueteria();

        PaqueteriaDTO paqueteria = obtenerPaqueteria(id);
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("No se encontró una paquetería correspondiente a la ID: " + id);
        }

        Float cobroKg = paqueteria.cobroKg();
        Float cobroHora = paqueteria.cobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = direccionClientePesoTiempoEnvioPaqueteriaDTO.getPesoKgTotal();
        Float tiempoEnnvioDestino = direccionClientePesoTiempoEnvioPaqueteriaDTO.getTiempoEnvioMatrizHorasProductoInventario();

        // Se regresa el costo del envío del producto
        return (float) (cobroKg * pesoKgProducto) + (cobroHora * tiempoEnnvioDestino);
    }
}
