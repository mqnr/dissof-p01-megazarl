
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.GerenteVentasDTODatos;
import edu.student.itson.dissof.dto.datos.IdGerenteVentasDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.GerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdGerenteVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioGerenteVentas;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class RepositorioGerenteVentasEnMongodb implements RepositorioGerenteVentas{
    
    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioGerenteVentasEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    @Override
    public GerenteVentasDTONegocios recuperarPorId(IdGerenteVentasDTONegocios idGerenteVentasDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException{
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idGerenteVentasDTO.getIdGerenteVentas().getId());
        
        IdGerenteVentasDTODatos idGerenteVentasDTODatos = new IdGerenteVentasDTODatos(idEntidadGenericoDatos);
        
        GerenteVentasDTODatos gerenteVentasDTODatos;
        
        try {
            
            gerenteVentasDTODatos = administradorMongodb.recuperarGerenteVentasPorId(idGerenteVentasDTODatos);


            GerenteVentasDTONegocios gerenteVentasDTONegocios 
                    = new GerenteVentasDTONegocios(
                            new IdEntidadGenericoNegocios(gerenteVentasDTODatos.getId().getId()), 
                            gerenteVentasDTODatos.getNombres(),
                            gerenteVentasDTODatos.getApellidoPaterno(),
                            gerenteVentasDTODatos.getApellidoMaterno());

            return gerenteVentasDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
    }

    @Override
    public boolean existePorId(IdGerenteVentasDTONegocios idGerenteVentasDTO) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException {
        
        IdGerenteVentasDTODatos idGerenteVentasDTODatos = new IdGerenteVentasDTODatos(
                        new IdEntidadGenericoDatos(idGerenteVentasDTO.getIdGerenteVentas().getId()));
        
        try {
            return administradorMongodb.existeGerenteVentasPorId(idGerenteVentasDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
    }

    @Override
    public void agregar(GerenteVentasDTONegocios gerenteVentasDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        GerenteVentasDTODatos gerenteVentasDTODatos = new GerenteVentasDTODatos(
                gerenteVentasDTONegocios.getNombres(),
                gerenteVentasDTONegocios.getApellidoPaterno(),
                gerenteVentasDTONegocios.getApellidoMaterno());
        
        try {
            administradorMongodb.agregarGerenteVentas(gerenteVentasDTODatos);
            
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ValorParametroInvalidoException ex) {
            throw new ValorParametroInvalidoNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
        
    }

    @Override
    public void agregar(Collection<GerenteVentasDTONegocios> gerentesVentas) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException{
        
        List<GerenteVentasDTODatos> listaGerentesVentasAgregar = new LinkedList<>();
        
        for(GerenteVentasDTONegocios auxiliarVentas: gerentesVentas){

            GerenteVentasDTODatos auxiliarVentasDTODatos = new GerenteVentasDTODatos(
                    auxiliarVentas.getNombres(),
                    auxiliarVentas.getApellidoPaterno(),
                    auxiliarVentas.getApellidoMaterno());
            
            listaGerentesVentasAgregar.add(auxiliarVentasDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarColeccionGerentesVentas(listaGerentesVentasAgregar);
            
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (ValorParametroInvalidoException ex) {
            throw new ValorParametroInvalidoNegocioException(ex.getMessage());
        }
    }

    @Override
    public List<GerenteVentasDTONegocios> recuperarTodos() {
        
        List<GerenteVentasDTONegocios> listaGerentesVentasDTONegocios = new LinkedList<>();
        
        List<GerenteVentasDTODatos> listaGerentesVentasDTODatos = administradorMongodb.recuperarTodosGerentesVentas();
        
        for(GerenteVentasDTODatos gerentesVentasDTODatos: listaGerentesVentasDTODatos){
        
            listaGerentesVentasDTONegocios.add(
                    new GerenteVentasDTONegocios(
                            new IdEntidadGenericoNegocios(gerentesVentasDTODatos.getId().getId()),
                            gerentesVentasDTODatos.getNombres(),
                            gerentesVentasDTODatos.getApellidoPaterno(),
                            gerentesVentasDTODatos.getApellidoMaterno()
                            
            ));
            
        }
        
        return listaGerentesVentasDTONegocios;
    }
}
