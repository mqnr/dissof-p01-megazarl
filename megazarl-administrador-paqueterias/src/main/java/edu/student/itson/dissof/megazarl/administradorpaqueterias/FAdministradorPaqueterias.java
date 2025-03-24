package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorproductos.IAdministradorProductos;
import edu.student.itson.dissof.megazarl.dto.DireccionClienteProductosEnvioDTO;
import edu.student.itson.dissof.megazarl.dto.InformacionSeleccionPaqueteriaDTO;
import java.util.Arrays;
import java.util.List;

public class FAdministradorPaqueterias implements IAdministradorPaqueterias {

    private IAdministradorClientes administradorClientes;
    private IAdministradorSucursales administradorSucursales;
    private IAdministradorProductos administradorProductos;

    public FAdministradorPaqueterias(IAdministradorClientes administradorClientes, IAdministradorSucursales administradorSucursales, IAdministradorProductos administradorProductos) {
        this.administradorClientes = administradorClientes;
        this.administradorSucursales = administradorSucursales;
        this.administradorProductos = administradorProductos;
    }
    
    
    @Override
    public List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias() {
        List<InformacionSeleccionPaqueteriaDTO> listaInformacionSeleccionPaqueteriaDTO = 
                Arrays.asList(
                        new InformacionSeleccionPaqueteriaDTO(1, "/dhl.png"), 
                        new InformacionSeleccionPaqueteriaDTO(2,"/fedex.png"), 
                        new InformacionSeleccionPaqueteriaDTO(3, "/pcp.png"),
                        new InformacionSeleccionPaqueteriaDTO(4, "/ups.png"),
                        new InformacionSeleccionPaqueteriaDTO(5, "/estafeta.png"));
        

        return listaInformacionSeleccionPaqueteriaDTO;
    }
    
    @Override
    public Double obtenerCostoEnvio(DireccionClienteProductosEnvioDTO direccionClienteProductosEnvioDTO){
        
        Integer idPaqueteria = direccionClienteProductosEnvioDTO.getCodigoPaqueteria();
        if(idPaqueteria == 1){
            return 2000d;
        } else if(idPaqueteria == 2){
            return 1500d;
        } else if(idPaqueteria == 3){
            return 2400d;
        } else if(idPaqueteria == 4){
            return 3000.50d;
        } else if(idPaqueteria == 5){
            return 2988.23d;
        } else{
            return null;
        }
             
        
    }
    
    
    
    
    @Override
    public boolean validarPaqueteria(Integer codigoPaqueteria){
        if(codigoPaqueteria == 1 || codigoPaqueteria == 2 || codigoPaqueteria == 3 || codigoPaqueteria == 4 || codigoPaqueteria == 5){
            return true;
        }
        return false;
    }
}
