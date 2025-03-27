package edu.student.itson.dissof.megazarl.administradorclientes;
import edu.student.itson.dissof.megazarl.dto.DetallesDerivadosDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.DireccionEntradaDTO;
import edu.student.itson.dissof.megazarl.dto.NombreApellidoClienteDTO;
import java.awt.Color;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class FAdministradorClientes implements IAdministradorClientes {

    String[] datosCliente = {"Juan", "Pérez", "85000", "Guerrero", "1586"};
    
    @Override
    public boolean validarIdCliente(Integer idCliente) {
        if(idCliente == 3){
            return true;
        }
        return false;
    }

    @Override
    public NombreApellidoClienteDTO obtenerNombreApellidoPaternoCliente(Integer idCliente) {

        if(idCliente == 3){
            return new NombreApellidoClienteDTO(
                datosCliente[0], 
                datosCliente[1]
            );
        }
        return null;
        
    }
    
    
    @Override
    public String obtenerCodigoPostalCliente(Integer idCliente){
        if(idCliente == 3){
            return datosCliente[2];
        } else{
            return null;
        }
    }
    
    @Override
    public String obtenerCalleCliente(Integer idCliente){
        if(idCliente == 3){
            return datosCliente[3];
        } else{
            return null;
        }
    }
    
    @Override
    public String obtenerNumeroCliente(Integer idCliente){
        if(idCliente == 3){
            return datosCliente[4];
        } else{
            return null;
        }
    }
    
    @Override
    public String obtenerColoniaCliente(Integer idCliente){
        if(idCliente == 3){
            
            String codigoPostalCliente  = obtenerCodigoPostalCliente(idCliente);
            DetallesDerivadosDireccionDTO detallesDerivadosDireccionDTO = obtenerDatosDireccionDerivado(codigoPostalCliente);
            
            if(detallesDerivadosDireccionDTO != null){
                return detallesDerivadosDireccionDTO.getColonia();
            }
        } 
        
        return null;
    }
    
    @Override
    public String obtenerCiudadCliente(Integer idCliente){
        if(idCliente == 3){
            
            String codigoPostalCliente  = obtenerCodigoPostalCliente(idCliente);
            DetallesDerivadosDireccionDTO detallesDerivadosDireccionDTO = obtenerDatosDireccionDerivado(codigoPostalCliente);
            
            if(detallesDerivadosDireccionDTO != null){
                return detallesDerivadosDireccionDTO.getCiudad();
            }

            
        }
        return null;
    }
    
    @Override
    public String obtenerEstadoCliente(Integer idCliente){
        if(idCliente == 3){
            
            String codigoPostalCliente  = obtenerCodigoPostalCliente(idCliente);
            DetallesDerivadosDireccionDTO detallesDerivadosDireccionDTO = obtenerDatosDireccionDerivado(codigoPostalCliente);
            
            if(detallesDerivadosDireccionDTO != null){
                return detallesDerivadosDireccionDTO.getEstado();
            }

            
        }
        return null;
    }
    
    
    @Override
    public DetallesDerivadosDireccionDTO obtenerDatosDireccionDerivado(String codigoPostalBuscar){
        
        
        DetallesDerivadosDireccionDTO detallesDerivadosDireccionDTO = null;

        String colonia;
        String ciudad;
        String estado;
        URL resource = FAdministradorClientes.class.getResource("/codigosPostalesMexico.txt");

        try {
            Path path = Paths.get(resource.toURI());
            List<String> lineasArchivo = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            lineasArchivo.remove(0);
            lineasArchivo.remove(1);

            for (int i = 0; i < lineasArchivo.size(); i++) {

                String linea = lineasArchivo.get(i);

                String[] partes = linea.split("\\|");


                String codigoPostal = partes[0];

                if (codigoPostal.equals(codigoPostalBuscar)) {

                    colonia = partes[1];
                    estado = partes[4];
                    ciudad = "";

                    if(partes[5].isBlank()){
                        ciudad = partes[3];
                    } else{
                        ciudad = partes[5];
                    }

                    detallesDerivadosDireccionDTO = new DetallesDerivadosDireccionDTO(colonia, ciudad, estado);

                    return detallesDerivadosDireccionDTO;
                }
            }

        } catch (URISyntaxException|IOException ex) {
            return null;  
        }

        return null;
    }
    
    @Override
    public boolean actualizarDireccionCliente(DireccionEntradaDTO direccionEntradaDTO){
        if(direccionEntradaDTO.getIdCliente() == 3){
            datosCliente[2] = direccionEntradaDTO.getCodigoPostal();
            datosCliente[3] = direccionEntradaDTO.getCalle();
            datosCliente[4] = direccionEntradaDTO.getNumero();
            
            return true;
        }
        return false;
    }
    
}
