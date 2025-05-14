
package edu.student.itson.dissof.megazarl.direcciones;

import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesAccesoArchivoCodigosPostalesFallidoException;
import edu.student.itson.dissof.megazarl.direcciones.excepciones.DireccionesArchivoCodigosPostalesVacioException;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.CodigoPostalDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdDireccionDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionDerivadaCPDireccionDTO;

/**
 * FAdministradorDirecciones.java

 Clase Fachada que representa la implementación de la interfaz {@link IAdministradorDirecciones}
 * del subsistema Direcciones.
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 *
 */
public class FAdministradorDirecciones implements IAdministradorDirecciones{
    
    private AdministradorDirecciones administradorDirecciones;
            
    public FAdministradorDirecciones(){
        this.administradorDirecciones = new AdministradorDirecciones();
    }
   
    @Override
    public DireccionDTO obtenerDireccion(IdDireccionDTO idDireccionDTO) {
        return administradorDirecciones.obtenerDireccion(idDireccionDTO);
    }

    @Override
    public boolean validarDireccion(IdDireccionDTO idDireccionDTO) {
        return administradorDirecciones.validarDireccion(idDireccionDTO);
    }

    @Override
    public DireccionDTO registrarDireccion(DireccionDTO direccionDTO) 
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException, 
            DireccionesArchivoCodigosPostalesVacioException {
        return administradorDirecciones.registrarDireccion(direccionDTO);
    }
    
    /**
     * Implementación del método obtenerDatosDireccionDerivados() de la interfaz {@link IAdministradorDirecciones},
     * permite obtener el Estado, Ciudad y Colonia asociados al Código Postal del parámetro.
     * @param codigoPostalDTO Objeto CodigoPostalDTO que contiene el Código Postal a buscar.
     * @return Objeto InformacionDerivadaCPDireccionEnvioDTO, un DTO que contiene el Estado, Ciudad
    y Colonia asociados al Código Postal del parámetro.
     * @throws DireccionesAccesoArchivoCodigosPostalesFallidoException Se lanza si ocurre un error
     * al acceder al archivo que contiene la información de los Códigos Postales y sus datos asociados.
     * @throws DireccionesArchivoCodigosPostalesVacioException Se lanza si el archivo que contiene
     * la información de los Códigos Postales y sus datos asociados está vacío.
     */
    @Override
    public InformacionDerivadaCPDireccionDTO obtenerDatosDireccionDerivados(CodigoPostalDTO codigoPostalDTO)
            throws DireccionesAccesoArchivoCodigosPostalesFallidoException, 
            DireccionesArchivoCodigosPostalesVacioException{

        return administradorDirecciones.obtenerDatosDireccionDerivados(codigoPostalDTO);
    }

}
