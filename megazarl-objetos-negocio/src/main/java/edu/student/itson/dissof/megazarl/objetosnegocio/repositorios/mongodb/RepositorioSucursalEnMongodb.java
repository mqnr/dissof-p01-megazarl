
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.dto.datos.IdSucursalDTODatos;
import edu.student.itson.dissof.dto.datos.SucursalDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioSucursal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositorioSucursalEnMongodb implements RepositorioSucursal{
    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioSucursalEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    @Override
    public SucursalDTONegocios recuperarPorId(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            ParametroNuloNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException{
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idSucursalDTONegocios.getIdSucursal().getId());
        
        IdSucursalDTODatos idSucursalDTODatos = new IdSucursalDTODatos(idEntidadGenericoDatos);
        
        SucursalDTODatos sucursalDTODatos;
        
        try {
            
            sucursalDTODatos = administradorMongodb.recuperarSucursalPorId(idSucursalDTODatos);


            SucursalDTONegocios sucursalDTONegocios 
                    = new SucursalDTONegocios(
                            new IdEntidadGenericoNegocios(sucursalDTODatos.getId().getId()), 
                            sucursalDTODatos.getEsMatriz(),
                            new DireccionDTONegocios(
                                    new IdEntidadGenericoNegocios(sucursalDTODatos.getDireccion().getId()),
                                    sucursalDTODatos.getDireccion().getCodigoPostal(),
                                    sucursalDTODatos.getDireccion().getColonia(),
                                    sucursalDTODatos.getDireccion().getCalle(),
                                    sucursalDTODatos.getDireccion().getNumero()
                                    
                            ));


            return sucursalDTONegocios;
        
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());     
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        }
    }
    
    @Override
    public SucursalDTONegocios obtenerSucursalMatriz() throws RegistroInexistenteNegocioException{
        
        SucursalDTODatos sucursalDTODatos;
        try {
            sucursalDTODatos = administradorMongodb.obtenerSucursalMatriz();
        } catch (RegistroInexistenteException ex) {
            throw new RegistroInexistenteNegocioException(ex.getMessage());
        }
        
        SucursalDTONegocios sucursalDTONegocios = new SucursalDTONegocios(
                new IdEntidadGenericoNegocios(sucursalDTODatos.getId().getId()),
                sucursalDTODatos.getEsMatriz(), 
                new DireccionDTONegocios(
                        new IdEntidadGenericoNegocios(sucursalDTODatos.getDireccion().getId().getId()),
                        sucursalDTODatos.getDireccion().getCodigoPostal(),
                        sucursalDTODatos.getDireccion().getColonia(),
                        sucursalDTODatos.getDireccion().getCalle(),
                        sucursalDTODatos.getDireccion().getNumero()
                    )
        );
        
        return sucursalDTONegocios;
        
    }

    @Override
    public boolean existePorId(IdSucursalDTONegocios idSucursalDTONegocios) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException
            
    {
        
        IdSucursalDTODatos idSucursalDTODatos = new IdSucursalDTODatos(
                        new IdEntidadGenericoDatos(idSucursalDTONegocios.getIdSucursal().getId()));
        
        try {
            return administradorMongodb.existeSucursalPorId(idSucursalDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
    }

    @Override
    public void agregar(SucursalDTONegocios sucursalDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException{
        
        SucursalDTODatos sucursalDTODatos = new SucursalDTODatos(
                sucursalDTONegocios.getEsMatriz(),
                new DireccionDTODatos(
                        sucursalDTONegocios.getDireccion().getCodigoPostal(),
                        sucursalDTONegocios.getDireccion().getColonia(),
                        sucursalDTONegocios.getDireccion().getCalle(),
                        sucursalDTONegocios.getDireccion().getNumero()));
        
        try {
            administradorMongodb.agregarSucursal(sucursalDTODatos);
            
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
    public void agregar(Collection<SucursalDTONegocios> sucursales)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        List<SucursalDTODatos> listaSucursalesAgregar = new LinkedList<>();
        
        for(SucursalDTONegocios sucursalDTONegocios: sucursales){

            SucursalDTODatos sucursalDTODatos = new SucursalDTODatos(
                sucursalDTONegocios.getEsMatriz(),
                new DireccionDTODatos(
                        sucursalDTONegocios.getDireccion().getCodigoPostal(),
                        sucursalDTONegocios.getDireccion().getColonia(),
                        sucursalDTONegocios.getDireccion().getCalle(),
                        sucursalDTONegocios.getDireccion().getNumero()));
            
            listaSucursalesAgregar.add(sucursalDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarColeccionSucursales(listaSucursalesAgregar);
            
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
    public List<SucursalDTONegocios> recuperarTodos() {
        
        List<SucursalDTONegocios> listaSucursalesDTONegocios = new LinkedList<>();
        
        List<SucursalDTODatos> listaSucursalesDTODatos = administradorMongodb.recuperarTodosSucursales();
        
        for(SucursalDTODatos sucursalDTODatos: listaSucursalesDTODatos){
            
            listaSucursalesDTONegocios.add(
                    new SucursalDTONegocios(
                            new IdEntidadGenericoNegocios(sucursalDTODatos.getId().getId()), 
                            sucursalDTODatos.getEsMatriz(),
                            new DireccionDTONegocios(
                                    new IdEntidadGenericoNegocios(sucursalDTODatos.getDireccion().getId().getId()),
                                    sucursalDTODatos.getDireccion().getCodigoPostal(),
                                    sucursalDTODatos.getDireccion().getColonia(),
                                    sucursalDTODatos.getDireccion().getCalle(),
                                    sucursalDTODatos.getDireccion().getNumero()
                                    
                            ))
            );
        }
        
        return listaSucursalesDTONegocios;
    }

}
