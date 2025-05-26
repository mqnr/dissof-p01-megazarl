
package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.AuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioAuxiliarVentas;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioAuxiliarVentasEnMongodb;
import java.util.Collection;
import java.util.List;
/**
 * AuxiliarVentas.java
 * 
 * Clase que representa el objeto de negocio Auxiliar de ventas.
 * 
 * @author Manuel Romo López
 */
public class AuxiliarVentas {
    
    private static final RepositorioAuxiliarVentas repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().auxiliarVentas()) {
            case MEMORIA -> new RepositorioAuxiliarVentasEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static AuxiliarVentasDTONegocios recuperarPorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException {
        
        return repositorio.recuperarPorId(idAuxiliarVentasDTO);
    }
    
    public static boolean existePorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        return repositorio.existePorId(idAuxiliarVentasDTO);
    }
     
    public static void agregar(AuxiliarVentasDTONegocios auxiliarVentas) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        repositorio.agregar(auxiliarVentas);
    }
    
    public static void agregar(Collection<AuxiliarVentasDTONegocios> auxiliaresVentas) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException, 
            ValorParametroInvalidoNegocioException {
        
        repositorio.agregar(auxiliaresVentas);
    }
     
    public static List<AuxiliarVentasDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
}
