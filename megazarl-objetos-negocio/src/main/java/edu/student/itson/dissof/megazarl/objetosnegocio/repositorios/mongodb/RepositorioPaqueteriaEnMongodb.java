
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.dto.datos.IdPaqueteriaDTODatos;
import edu.student.itson.dissof.dto.datos.PaqueteriaDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioPaqueteria;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class RepositorioPaqueteriaEnMongodb implements RepositorioPaqueteria{

    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioPaqueteriaEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    @Override
    public PaqueteriaDTONegocios recuperarPorId(IdPaqueteriaDTONegocios idPaquteriaDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException {
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idPaquteriaDTONegocios.getIdPaqueteria().getId());
        
        IdPaqueteriaDTODatos idPaqueteriaDTODatos = new IdPaqueteriaDTODatos(idEntidadGenericoDatos);
        
        PaqueteriaDTODatos paqueteriaDTODatos;
        
        try {
            
            paqueteriaDTODatos = administradorMongodb.recuperarPaqueteriaPorId(idPaqueteriaDTODatos);


            PaqueteriaDTONegocios clienteDTONegocios 
                    = new PaqueteriaDTONegocios(
                            new IdEntidadGenericoNegocios(paqueteriaDTODatos.getId().getId()),
                            paqueteriaDTODatos.getNombre(),
                            paqueteriaDTODatos.getCobroKg(),
                            paqueteriaDTODatos.getCobroHora(),
                            paqueteriaDTODatos.getDireccionImagen(),
                            new DireccionDTONegocios(
                                    new IdEntidadGenericoNegocios(paqueteriaDTODatos.getDireccion().getId()),
                                    paqueteriaDTODatos.getDireccion().getCodigoPostal(),
                                    paqueteriaDTODatos.getDireccion().getColonia(),
                                    paqueteriaDTODatos.getDireccion().getCalle(),
                                    paqueteriaDTODatos.getDireccion().getNumero()
                            )
                    );


            return clienteDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
        
        
    }

    @Override
    public boolean existePorId(IdPaqueteriaDTONegocios idPaqueteriaDTONegocios) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException {
        
        IdPaqueteriaDTODatos idPaqueteriaDTODatos = new IdPaqueteriaDTODatos(
                        new IdEntidadGenericoDatos(idPaqueteriaDTONegocios.getIdPaqueteria().getId()));
        
        try {
            return administradorMongodb.existePaqueteriaPorId(idPaqueteriaDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
    }

    @Override
    public void agregar(PaqueteriaDTONegocios paqueteriaDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException, 
            ParametroNuloNegocioException,
            ValorParametroInvalidoNegocioException {
        
        PaqueteriaDTODatos paqueteriaDTODatos = new PaqueteriaDTODatos(
                paqueteriaDTONegocios.getNombre(),
                paqueteriaDTONegocios.getCobroKg(),
                paqueteriaDTONegocios.getCobroHora(),
                paqueteriaDTONegocios.getDireccionImagenPaqueteria(),
                new DireccionDTODatos(
                        paqueteriaDTONegocios.getDireccion().getCodigoPostal(),
                        paqueteriaDTONegocios.getDireccion().getColonia(),
                        paqueteriaDTONegocios.getDireccion().getCalle(),
                        paqueteriaDTONegocios.getDireccion().getNumero()));
        
        try {
            administradorMongodb.agregarPaqueteria(paqueteriaDTODatos);
            
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
    public void agregar(Collection<PaqueteriaDTONegocios> paqueteriasDTONegocios) throws FormatoIdInvalidoNegocioException, RegistroInexistenteNegocioException, ParametroNuloNegocioException, ValorParametroInvalidoNegocioException {
        
        List<PaqueteriaDTODatos> listaPaqueteriasAgregar = new LinkedList<>();
        
        for(PaqueteriaDTONegocios paqueteriaDTONegocios: paqueteriasDTONegocios){

            PaqueteriaDTODatos paqueteriaDTODatos = new PaqueteriaDTODatos(
                paqueteriaDTONegocios.getNombre(),
                paqueteriaDTONegocios.getCobroKg(),
                paqueteriaDTONegocios.getCobroHora(),
                paqueteriaDTONegocios.getDireccionImagenPaqueteria(),
                new DireccionDTODatos(
                        paqueteriaDTONegocios.getDireccion().getCodigoPostal(),
                        paqueteriaDTONegocios.getDireccion().getColonia(),
                        paqueteriaDTONegocios.getDireccion().getCalle(),
                        paqueteriaDTONegocios.getDireccion().getNumero()));
            
            listaPaqueteriasAgregar.add(paqueteriaDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarColeccionPaqueterias(listaPaqueteriasAgregar);
            
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
    public List<PaqueteriaDTONegocios> recuperarTodos() {
        List<PaqueteriaDTONegocios> listaPaqueteriasDTONegocios = new LinkedList<>();

        List<PaqueteriaDTODatos> listaPaqueteriasDTODatos = administradorMongodb.recuperarTodosPaqueterias();

        for(PaqueteriaDTODatos paqueteriaDTODatos: listaPaqueteriasDTODatos){

            listaPaqueteriasDTONegocios.add(
                    new PaqueteriaDTONegocios(
                            new IdEntidadGenericoNegocios(paqueteriaDTODatos.getId().getId()),
                            paqueteriaDTODatos.getNombre(),
                            paqueteriaDTODatos.getCobroKg(),
                            paqueteriaDTODatos.getCobroHora(),
                            paqueteriaDTODatos.getDireccionImagen(),
                            new DireccionDTONegocios(
                                    new IdEntidadGenericoNegocios(paqueteriaDTODatos.getDireccion().getId()),
                                    paqueteriaDTODatos.getDireccion().getCodigoPostal(),
                                    paqueteriaDTODatos.getDireccion().getColonia(),
                                    paqueteriaDTODatos.getDireccion().getCalle(),
                                    paqueteriaDTODatos.getDireccion().getNumero()
                            )
                    )
            );
        }

        return listaPaqueteriasDTONegocios;
    }
 
}
