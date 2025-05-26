package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioGerenteVentas;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioGerenteVentasEnMongodb;
import java.util.Collection;
import java.util.List;

/**
 * GerenteVentas.java
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * 
 */
public class GerenteVentas {
    
    private static final RepositorioGerenteVentas repositorio;
    
    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().gerenteVentas()){
            case MEMORIA -> new RepositorioGerenteVentasEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }
    
    public static GerenteVentasDTONegocios recuperarPorId(IdGerenteVentasDTONegocios idGerenteVentasDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException{
        
        return repositorio.recuperarPorId(idGerenteVentasDTO);
    }
    
    public static boolean existePorId(IdGerenteVentasDTONegocios idGerenteVentasDTO) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException{
        
        return repositorio.existePorId(idGerenteVentasDTO);
    }
    
    public static void agregar(GerenteVentasDTONegocios gerente)
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException{
        
        repositorio.agregar(gerente);
    }
    
    public static void agregar(Collection<GerenteVentasDTONegocios> gerentes) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException{
        
        repositorio.agregar(gerentes);
    }
    
    public static List<GerenteVentasDTONegocios> recuperarTodos(){
        return repositorio.recuperarTodos();
    }
    
    
}