package edu.student.itson.dissof.megazarl.direcciones;

import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigoPostalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.Direccion;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
class AdministradorDirecciones implements IAdministradorDirecciones {
    
    @Override
    public DireccionDTONegocios obtenerDireccion(IdDireccionDTONegocios idDireccionDTONegocios) {
        return Direccion.recuperarPorId(idDireccionDTONegocios);
    }

    @Override
    public boolean validarDireccion(IdDireccionDTONegocios idDireccionDTONegocios) {
        
        if (idDireccionDTONegocios == null || idDireccionDTONegocios.getIdDireccion()== null || !Direccion.existePorId(idDireccionDTONegocios)) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public InformacionDerivadaCPDireccionDTONegocios obtenerDatosDireccionDerivados(CodigoPostalDTONegocios codigoPostalDTONegocios)
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException, 
            DireccionesArchivoCodigosPostalesVacioException {

        List<String> colonias = new LinkedList<>();
        String ciudad = "";
        String estado = "";

        // Se obtiene la dirección del archivo.
        URL resource = AdministradorDirecciones.class.getResource("/codigosPostalesMexico.txt");

        try {
            
            // Se leen todas las líneas del archivo sin considerar aviso ni encabezado.
            Path path = Paths.get(resource.toURI());
            List<String> lineasArchivo = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
            lineasArchivo.remove(0);
            lineasArchivo.remove(1);

            // Se instancia un objeto informacionDerivadaDireccionEnvioDTO como null.
            InformacionDerivadaCPDireccionDTONegocios informacionDerivadaCPDireccionDTO = null;

            String codigoPostalBuscar = codigoPostalDTONegocios.getCodigoPostal();
            
            int i = 0;

            boolean codigoPostalExiste = false;
            
            // Se recorren las líneas del archivo.
            while (i < lineasArchivo.size()) {
                // Por cada línea se crea un arreglo para almacenar cada uno de los
                // valores de los diferentes campos.

                String linea = lineasArchivo.get(i);

                String[] partes = linea.split("\\|");

                String codigoPostal = partes[0];
                
                // Si el Código Postal recibido coincide con uno dentro del archivo,
                // se guardan sus datos asociados, en la misma fila.
                if (codigoPostal.equals(codigoPostalBuscar)) {
                    colonias.add(partes[1]);
                    estado = partes[4];
                    ciudad = "";

                    if(partes[5].isBlank()){
                        ciudad = partes[3];
                    } else{
                        ciudad = partes[5];
                    }
                    
                    codigoPostalExiste = true;

                } else if(codigoPostalExiste){
                    
                    // Se asignan los datos a un nuevo DTO de tipo InformacionDerivadaCPDireccionEnvioDTO;
                    informacionDerivadaCPDireccionDTO = new InformacionDerivadaCPDireccionDTONegocios(colonias, ciudad, estado);
                    
                    break;
                }

                i++;
            }

            if (i == 1) {
                // Se lanza una excepcion si el archivo no contiene líneas de texto, es decir, si está vacío.
                throw new DireccionesArchivoCodigosPostalesVacioException("El archivo que contiene la información"
                        + "relacionada con los Códigos Postales está vacío.");
            }

            return informacionDerivadaCPDireccionDTO;

            // Se lanza una excepción si no se pudo leer el archivo con los Códigos Postales.
        } catch (URISyntaxException | IOException ex) {
            throw new DireccionesAccesoArchivoCodigosPostalesFallidoException("No se pudo acceder al archivo que contiene la información relacionada"
                    + "con los Códigos Postales");
        }
    }
    
    @Override
    public DireccionDTONegocios registrarDireccion(DireccionDTONegocios direccionDTO) 
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException,
            DireccionesArchivoCodigosPostalesVacioException{
        
        String codigoPostalNuevaDireccion = direccionDTO.getCodigoPostal();
        
        InformacionDerivadaCPDireccionDTONegocios informacionDerivadaCPDireccionDTO
                = obtenerDatosDireccionDerivados(new CodigoPostalDTONegocios(codigoPostalNuevaDireccion));
        
        String estadoDireccion = informacionDerivadaCPDireccionDTO.getEstado();
        String ciudadDireccion = informacionDerivadaCPDireccionDTO.getCiudad();
        
        direccionDTO.setEstado(estadoDireccion);
        direccionDTO.setCiudad(ciudadDireccion);
        
        Direccion.agregar(direccionDTO);
        
        return direccionDTO;
        
    }

}
