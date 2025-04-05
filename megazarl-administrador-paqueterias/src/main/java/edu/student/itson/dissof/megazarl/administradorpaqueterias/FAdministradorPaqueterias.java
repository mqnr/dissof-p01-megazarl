package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.dto.DireccionClientePesoTiempoProductoInventarioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;
import java.util.LinkedList;
import java.util.List;

public class FAdministradorPaqueterias implements IAdministradorPaqueterias {

    private List<Paqueteria> listaPaqueterias;
    
    private IAdministradorClientes administradorClientes;
    private IAdministradorSucursales administradorSucursales;
    private IAdministradorProductos administradorProductos;

    public FAdministradorPaqueterias(List<Paqueteria> listaPaqueterias, IAdministradorClientes administradorClientes, IAdministradorSucursales administradorSucursales, IAdministradorProductos administradorProductos) {
        this.listaPaqueterias = listaPaqueterias;
        this.administradorClientes = administradorClientes;
        this.administradorSucursales = administradorSucursales;
        this.administradorProductos = administradorProductos;
    }

    
    @Override
    public List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias() {
        List<InformacionSeleccionPaqueteriaDTO> listaInformacionSeleccionPaqueteriaDTO = new LinkedList<>();
        
        for(Paqueteria paqueteria: listaPaqueterias){
            listaInformacionSeleccionPaqueteriaDTO.add(
                    new InformacionSeleccionPaqueteriaDTO(paqueteria.getId(), paqueteria.getDireccionImagenPaqueteria()));
        }

        return listaInformacionSeleccionPaqueteriaDTO;
    }
    
    @Override
    public Float obtenerCostoEnvioProducto(DireccionClientePesoTiempoProductoInventarioDTO direccionClienteProductosEnvioDTO) 
            throws PaqueteriasIdPaqueteriaInvalidoException{
        
        Integer idPaqueteria = direccionClienteProductosEnvioDTO.getCodigoPaqueteria();
        
        // Se valida el ID de Paquteria:
        if(!validarPaqueteria(idPaqueteria)){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " no existe.");
        }
        
        Paqueteria paqueteriaRecuperada = obtenerPaqueteria(idPaqueteria);
        
        if(paqueteriaRecuperada == null){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería: " + idPaqueteria + " no existe.");
        }
        
        // Se recuperan los cobros por Kg y hora de envío de la paquetería recuperada.
        Float cobroKg = paqueteriaRecuperada.getCobroKg();
        Float cobroHora = paqueteriaRecuperada.getCobroHora();
        
        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío a Matriz.
        Double pesoKgProducto = direccionClienteProductosEnvioDTO.getPesoKgProductoInventario();
        Float tiempoEnvioHoras = direccionClienteProductosEnvioDTO.getTiempoEnvioMatrizHorasProductoInventario();
        
        if(tiempoEnvioHoras == 0){
            return 0F;
        }

        return (float)(cobroKg * pesoKgProducto) + (cobroHora * tiempoEnvioHoras);
             
        
    }
    
    @Override
    public boolean validarPaqueteria(Integer idPaqueteria){
        for(Paqueteria paqueteria: listaPaqueterias){
            if(paqueteria.getId() == idPaqueteria){
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public Paqueteria obtenerPaqueteria(Integer idPaqueteria){
        Paqueteria paqueteriaBuscar = null;
        
        for(Paqueteria paqueteria: listaPaqueterias){
            if(paqueteria.getId() == idPaqueteria){
                paqueteriaBuscar = paqueteria;
            }
        }
        
        return paqueteriaBuscar;
    }
    
    
}
