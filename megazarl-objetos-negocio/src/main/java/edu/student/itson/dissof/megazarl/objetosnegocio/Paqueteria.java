package edu.student.itson.dissof.megazarl.objetosnegocio;

import edu.student.itson.dissof.megazal.datos.FabricaSubsistemasAccesoDatos;
import edu.student.itson.dissof.megazarl.configuracion.ConfiguracionApp;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioPaqueteria;
import edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb.RepositorioPaqueteriaEnMongodb;

import java.util.Collection;
import java.util.List;

public class Paqueteria {
    private static final RepositorioPaqueteria repositorio;

    static {
        repositorio = switch (ConfiguracionApp.INSTANCIA.fuentes().paqueteria()) {
            case MEMORIA -> new RepositorioPaqueteriaEnMongodb(FabricaSubsistemasAccesoDatos.obtenerAdministradorMongodb());
            default -> throw new UnsupportedOperationException("Not implemented");
        };
    }

    public static PaqueteriaDTONegocios recuperarPorId(IdPaqueteriaDTONegocios idPaqueteriaDTO) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException {
        
        return repositorio.recuperarPorId(idPaqueteriaDTO);
    }

    public static boolean existePorId(IdPaqueteriaDTONegocios idPaqueteriaDTO)
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        return repositorio.existePorId(idPaqueteriaDTO);
    }

    public static void agregar(PaqueteriaDTONegocios paqueteriaDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        repositorio.agregar(paqueteriaDTO);
    }

    public static void agregar(Collection<PaqueteriaDTONegocios> paqueterias) 
            throws FormatoIdInvalidoNegocioException, 
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        repositorio.agregar(paqueterias);
    }

    public static List<PaqueteriaDTONegocios> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
}
