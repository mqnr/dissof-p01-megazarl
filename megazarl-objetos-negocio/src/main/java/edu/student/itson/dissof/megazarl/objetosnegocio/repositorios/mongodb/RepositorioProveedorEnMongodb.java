
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.dto.datos.IdProveedorDTODatos;
import edu.student.itson.dissof.dto.datos.ProveedorDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioProveedor;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositorioProveedorEnMongodb implements RepositorioProveedor{
    
    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioProveedorEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    @Override
    public ProveedorDTONegocios recuperarPorId(IdProveedorDTONegocios idProveedorDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            ParametroNuloNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException{
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idProveedorDTONegocios.getIdProveedor().getId());
        
        IdProveedorDTODatos idProveedorDTODatos = new IdProveedorDTODatos(idEntidadGenericoDatos);
        
        ProveedorDTODatos proveedorDTODatos;
        
        try {
            
            proveedorDTODatos = administradorMongodb.recuperarProveedorPorId(idProveedorDTODatos);


            ProveedorDTONegocios proveedorDTONegocios 
                    = new ProveedorDTONegocios(
                            new IdEntidadGenericoNegocios(proveedorDTODatos.getId().getId()), 
                            proveedorDTODatos.getNombre(),
                            proveedorDTODatos.getTelefono(),
                            proveedorDTODatos.getCorreoElectronico(),
                            proveedorDTODatos.getDireccionImagen(),
                            new DireccionDTONegocios(
                                    new IdEntidadGenericoNegocios(proveedorDTODatos.getDireccion().getId()),
                                    proveedorDTODatos.getDireccion().getCodigoPostal(),
                                    proveedorDTODatos.getDireccion().getColonia(),
                                    proveedorDTODatos.getDireccion().getCalle(),
                                    proveedorDTODatos.getDireccion().getNumero()
                                    
                            ));


            return proveedorDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
    }

    @Override
    public boolean existePorId(IdProveedorDTONegocios idProveedorDTONegocios) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException
            
    {
        
        IdProveedorDTODatos idProveedorDTODatos = new IdProveedorDTODatos(
                        new IdEntidadGenericoDatos(idProveedorDTONegocios.getIdProveedor().getId()));
        
        try {
            return administradorMongodb.existeProveedorPorId(idProveedorDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
    }

    @Override
    public void agregar(ProveedorDTONegocios proveedorDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException{
        
        ProveedorDTODatos proveedorDTODatos = new ProveedorDTODatos(
                proveedorDTONegocios.getNombre(),
                proveedorDTONegocios.getTelefono(),
                proveedorDTONegocios.getCorreoElectronico(),
                proveedorDTONegocios.getDireccionImagen(),
                new DireccionDTODatos(
                        proveedorDTONegocios.getDireccion().getCodigoPostal(),
                        proveedorDTONegocios.getDireccion().getColonia(),
                        proveedorDTONegocios.getDireccion().getCalle(),
                        proveedorDTONegocios.getDireccion().getNumero()));
        
        try {
            administradorMongodb.agregarProveedor(proveedorDTODatos);
            
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
    public void agregar(Collection<ProveedorDTONegocios> proveedores)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        List<ProveedorDTODatos> listaProveedoresAgregar = new LinkedList<>();
        
        for(ProveedorDTONegocios proveedorDTONegocios: proveedores){

            ProveedorDTODatos proveedorDTODatos = new ProveedorDTODatos(
                proveedorDTONegocios.getNombre(),
                proveedorDTONegocios.getTelefono(),
                proveedorDTONegocios.getCorreoElectronico(),
                proveedorDTONegocios.getDireccionImagen(),
                new DireccionDTODatos(
                        proveedorDTONegocios.getDireccion().getCodigoPostal(),
                        proveedorDTONegocios.getDireccion().getColonia(),
                        proveedorDTONegocios.getDireccion().getCalle(),
                        proveedorDTONegocios.getDireccion().getNumero()));
            
            listaProveedoresAgregar.add(proveedorDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarColeccionProveedores(listaProveedoresAgregar);
            
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
    public List<ProveedorDTONegocios> recuperarTodos() {
        
    List<ProveedorDTONegocios> listaClientesDTONegocios = new LinkedList<>();
        
        List<ProveedorDTODatos> listaProveedoresDTODatos = administradorMongodb.recuperarTodosProveedores();
        
        for(ProveedorDTODatos proveedorDTODatos: listaProveedoresDTODatos){
            
            listaClientesDTONegocios.add(
                    new ProveedorDTONegocios(
                            new IdEntidadGenericoNegocios(proveedorDTODatos.getId().getId()), 
                            proveedorDTODatos.getNombre(),
                            proveedorDTODatos.getTelefono(),
                            proveedorDTODatos.getCorreoElectronico(),
                            proveedorDTODatos.getDireccionImagen(),
                            new DireccionDTONegocios(
                                    new IdEntidadGenericoNegocios(proveedorDTODatos.getDireccion().getId().getId()),
                                    proveedorDTODatos.getDireccion().getCodigoPostal(),
                                    proveedorDTODatos.getDireccion().getColonia(),
                                    proveedorDTODatos.getDireccion().getCalle(),
                                    proveedorDTODatos.getDireccion().getNumero()
                                    
                            ))
            );
        }
        
        return listaClientesDTONegocios;
    }

}


