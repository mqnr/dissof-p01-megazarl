package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.dto.DireccionClientePesoTiempoProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.PaqueteriaON;

import java.util.LinkedList;
import java.util.List;

class AdministradorPaqueterias implements IAdministradorPaqueterias {
    private final List<PaqueteriaON> listaPaqueterias;


    public AdministradorPaqueterias(
            List<PaqueteriaON> listaPaqueterias) {
        this.listaPaqueterias = listaPaqueterias;

    }

    @Override
    public List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias() {
        List<InformacionSeleccionPaqueteriaDTO> listaInformacionSeleccionPaqueteriaDTO = new LinkedList<>();

        for (PaqueteriaON paqueteria: listaPaqueterias) {
            listaInformacionSeleccionPaqueteriaDTO.add(
                    new InformacionSeleccionPaqueteriaDTO(paqueteria.getId(), paqueteria.getDireccionImagenPaqueteria()));
        }

        return listaInformacionSeleccionPaqueteriaDTO;
    }

    @Override
    public Float obtenerCostoEnvioProducto(DireccionClientePesoTiempoProductoInventarioDTO direccionClienteProductosEnvioDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException{

        Integer idPaqueteria = direccionClienteProductosEnvioDTO.getCodigoPaqueteria();

        // Se valida el ID de Paqueteria:
        if (!validarPaqueteria(idPaqueteria)) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " no existe.");
        }

        PaqueteriaON paqueteriaRecuperada = obtenerPaqueteria(idPaqueteria);

        if (paqueteriaRecuperada == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " no existe.");
        }

        // Se recuperan los cobros por Kg y hora de envío de la paquetería recuperada.
        Float cobroKg = paqueteriaRecuperada.getCobroKg();
        Float cobroHora = paqueteriaRecuperada.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío a Matriz.
        Double pesoKgProducto = direccionClienteProductosEnvioDTO.getPesoKgProductoInventario();
        Float tiempoEnvioHoras = direccionClienteProductosEnvioDTO.getTiempoEnvioMatrizHorasProductoInventario();

        if (tiempoEnvioHoras == 0) {
            return 0F;
        }

        return (float)(cobroKg * pesoKgProducto) + (cobroHora * tiempoEnvioHoras);
    }

    @Override
    public boolean validarPaqueteria(Integer idPaqueteria){
        for (PaqueteriaON paqueteria: listaPaqueterias) {
            if (paqueteria.getId().equals(idPaqueteria)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public PaqueteriaON obtenerPaqueteria(Integer idPaqueteria){
        PaqueteriaON paqueteriaBuscar = null;

        for (PaqueteriaON paqueteria: listaPaqueterias) {
            if (paqueteria.getId().equals(idPaqueteria)) {
                paqueteriaBuscar = paqueteria;
            }
        }

        return paqueteriaBuscar;
    }
}
