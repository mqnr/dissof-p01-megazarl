
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.AuxiliarVentasDTODatos;
import edu.student.itson.dissof.dto.datos.IdAuxiliarVentasDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.AuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdAuxiliarVentasDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioAuxiliarVentas;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositorioAuxiliarVentasEnMongodb implements RepositorioAuxiliarVentas{

    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioAuxiliarVentasEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    @Override
    public AuxiliarVentasDTONegocios recuperarPorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException{
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idAuxiliarVentasDTO.getIdAuxiliarVentas().getId());
        
        IdAuxiliarVentasDTODatos idAuxiliarVentasDTODatos = new IdAuxiliarVentasDTODatos(idEntidadGenericoDatos);
        
        AuxiliarVentasDTODatos auxiliarVentasDTODatos;
        
        try {
            
            auxiliarVentasDTODatos = administradorMongodb.recuperarAuxiliarVentasPorId(idAuxiliarVentasDTODatos);


            AuxiliarVentasDTONegocios auxiliarVentasDTONegocios 
                    = new AuxiliarVentasDTONegocios(
                            new IdEntidadGenericoNegocios(auxiliarVentasDTODatos.getId().getId()), 
                            auxiliarVentasDTODatos.getNombres(),
                            auxiliarVentasDTODatos.getApellidoPaterno(),
                            auxiliarVentasDTODatos.getApellidoMaterno());

            return auxiliarVentasDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
    }

    @Override
    public boolean existePorId(IdAuxiliarVentasDTONegocios idAuxiliarVentasDTO) 
            throws ParametroNuloNegocioException,
            FormatoIdInvalidoNegocioException  {
        
        IdAuxiliarVentasDTODatos idAuxiliarVentasDTODatos = new IdAuxiliarVentasDTODatos(
                        new IdEntidadGenericoDatos(idAuxiliarVentasDTO.getIdAuxiliarVentas().getId()));
        
        try {
            return administradorMongodb.existeAuxiliarVentasPorId(idAuxiliarVentasDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
    }

    @Override
    public void agregar(AuxiliarVentasDTONegocios auxiliarVentasDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        AuxiliarVentasDTODatos auxiliarVentasDTODatos = new AuxiliarVentasDTODatos(
                auxiliarVentasDTONegocios.getNombres(),
                auxiliarVentasDTONegocios.getApellidoPaterno(),
                auxiliarVentasDTONegocios.getApellidoMaterno());
        
        try {
            administradorMongodb.agregarAuxiliarVentas(auxiliarVentasDTODatos);
            
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
    public void agregar(Collection<AuxiliarVentasDTONegocios> auxiliaresVentas) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException{
        
        List<AuxiliarVentasDTODatos> listaAuxiliaresVentasAgregar = new LinkedList<>();
        
        for(AuxiliarVentasDTONegocios auxiliarVentas: auxiliaresVentas){

            AuxiliarVentasDTODatos auxiliarVentasDTODatos = new AuxiliarVentasDTODatos(
                    auxiliarVentas.getNombres(),
                    auxiliarVentas.getApellidoPaterno(),
                    auxiliarVentas.getApellidoMaterno());
            
            listaAuxiliaresVentasAgregar.add(auxiliarVentasDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarColeccionAuxiliaresVentas(listaAuxiliaresVentasAgregar);
            
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
    public List<AuxiliarVentasDTONegocios> recuperarTodos() {
        
        List<AuxiliarVentasDTONegocios> listaAuxiliaresVentasDTONegocios = new LinkedList<>();
        
        List<AuxiliarVentasDTODatos> listaAuxiliaresVentasDTODatos = administradorMongodb.recuperarTodosAuxiliaresVentas();
        
        for(AuxiliarVentasDTODatos auxiliarVentasDTODatos: listaAuxiliaresVentasDTODatos){
        
            listaAuxiliaresVentasDTONegocios.add(
                    new AuxiliarVentasDTONegocios(
                            new IdEntidadGenericoNegocios(auxiliarVentasDTODatos.getId().getId()),
                            auxiliarVentasDTODatos.getNombres(),
                            auxiliarVentasDTODatos.getApellidoPaterno(),
                            auxiliarVentasDTODatos.getApellidoMaterno()
                            
            ));
            
        }
        
        return listaAuxiliaresVentasDTONegocios;
    }
    
}
