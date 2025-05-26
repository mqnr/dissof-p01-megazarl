
package edu.student.itson.dissof.megazarl.objetosnegocio.repositorios.mongodb;

import com.mycompany.megazarl.administrador.mongodb.IAdministradorMongodb;
import com.mycompany.megazarl.administrador.mongodb.excepciones.FormatoIdInvalidoException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ParametroNuloException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.RegistroInexistenteException;
import com.mycompany.megazarl.administrador.mongodb.excepciones.ValorParametroInvalidoException;
import edu.student.itson.dissof.dto.datos.ActualizacionClienteDTODatos;
import edu.student.itson.dissof.dto.datos.ClienteDTODatos;
import edu.student.itson.dissof.dto.datos.DireccionDTODatos;
import edu.student.itson.dissof.dto.datos.IdClienteDTODatos;
import edu.student.itson.dissof.megazarl.dto.datos.identidad.IdEntidadGenericoDatos;
import edu.student.itson.dissof.megazarl.dto.negocios.ActualizacionClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.NuevoClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ValorParametroInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.interfaces.RepositorioCliente;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class RepositorioClienteEnMongodb implements RepositorioCliente{

    private final IAdministradorMongodb administradorMongodb;
    
    public RepositorioClienteEnMongodb(IAdministradorMongodb administradorMongodb){
        this.administradorMongodb = administradorMongodb;
    }
    
    @Override
    public ClienteDTONegocios recuperarPorId(IdClienteDTONegocios idClieteDTO) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ParametroNuloNegocioException{
        
        IdEntidadGenericoDatos idEntidadGenericoDatos = new IdEntidadGenericoDatos(idClieteDTO.getIdCliente().getId());
        
        IdClienteDTODatos idClienteDTODatos = new IdClienteDTODatos(idEntidadGenericoDatos);
        
        ClienteDTODatos clienteDTODatos;
        
        try {
            
            clienteDTODatos = administradorMongodb.recuperarClientePorId(idClienteDTODatos);


            ClienteDTONegocios clienteDTONegocios 
                    = new ClienteDTONegocios(
                            new IdEntidadGenericoNegocios(clienteDTODatos.getId().getId()), 
                            clienteDTODatos.getNombres(),
                            clienteDTODatos.getApellidoPaterno(),
                            clienteDTODatos.getApellidoMaterno(),
                            clienteDTODatos.getTelefono(),
                            clienteDTODatos.getCorreoElectronico(),
                            new DireccionDTONegocios(
                                    new IdEntidadGenericoNegocios(clienteDTODatos.getDireccionEnvio().getId()),
                                    clienteDTODatos.getDireccionEnvio().getCodigoPostal(),
                                    clienteDTODatos.getDireccionEnvio().getColonia(),
                                    clienteDTODatos.getDireccionEnvio().getCalle(),
                                    clienteDTODatos.getDireccionEnvio().getNumero()
                                    
                            ));


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
    public boolean existePorId(IdClienteDTONegocios idClienteDTONegocios) 
            throws ParametroNuloNegocioException, 
            FormatoIdInvalidoNegocioException{
        
        IdClienteDTODatos idClienteDTODatos = new IdClienteDTODatos(
                        new IdEntidadGenericoDatos(idClienteDTONegocios.getIdCliente().getId()));
        
        try {
            return administradorMongodb.existeClientePorId(idClienteDTODatos);
        } catch (ParametroNuloException ex) {
            throw new ParametroNuloNegocioException(ex.getMessage());
        } catch (FormatoIdInvalidoException ex) {
            throw new FormatoIdInvalidoNegocioException(ex.getMessage());
        }
    }

    @Override
    public void agregar(ClienteDTONegocios clienteDTONegocios)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException{
        
            ClienteDTODatos clienteDTODatos = new ClienteDTODatos(
                    clienteDTONegocios.getNombres(),
                    clienteDTONegocios.getApellidoPaterno(),
                    clienteDTONegocios.getApellidoMaterno(),
                    clienteDTONegocios.getTelefono(),
                    clienteDTONegocios.getCorreoElectronico(),
                    clienteDTONegocios.getHashContrasenia());
                
        
        try {
            administradorMongodb.agregarCliente(clienteDTODatos);
            
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
    public void agregar(Collection<ClienteDTONegocios> clientes)
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException {
        
        List<ClienteDTODatos> listaClientesAgregar = new LinkedList<>();
        
        for(ClienteDTONegocios clienteDTONegocios: clientes){

            ClienteDTODatos clienteDTODatos = new ClienteDTODatos(
                clienteDTONegocios.getNombres(),
                clienteDTONegocios.getApellidoPaterno(),
                clienteDTONegocios.getApellidoMaterno(),
                clienteDTONegocios.getTelefono(),
                clienteDTONegocios.getCorreoElectronico(),
                clienteDTONegocios.getHashContrasenia(),
                new DireccionDTODatos(
                        clienteDTONegocios.getDireccionEnvio().getCodigoPostal(),
                        clienteDTONegocios.getDireccionEnvio().getColonia(),
                        clienteDTONegocios.getDireccionEnvio().getCalle(),
                        clienteDTONegocios.getDireccionEnvio().getNumero()));
            
            listaClientesAgregar.add(clienteDTODatos);
            
        }
        
        try {
            administradorMongodb.agregarColeccionClientes(listaClientesAgregar);
            
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
    public ClienteDTONegocios actualizar(ActualizacionClienteDTONegocios actualizacionClienteDTONegocios) 
            throws FormatoIdInvalidoNegocioException,
            RegistroInexistenteNegocioException,
            ValorParametroInvalidoNegocioException,
            ParametroNuloNegocioException{
        
        ActualizacionClienteDTODatos actualizacionClienteDTODatos 
                = new ActualizacionClienteDTODatos(new IdEntidadGenericoDatos(actualizacionClienteDTONegocios.getId()));
        
        actualizacionClienteDTODatos.setDireccionEnvio(
                new DireccionDTODatos(
                        new IdEntidadGenericoDatos(actualizacionClienteDTONegocios.getDireccionEnvio().getId()), 
                        actualizacionClienteDTONegocios.getDireccionEnvio().getCodigoPostal(),
                        actualizacionClienteDTONegocios.getDireccionEnvio().getColonia(),
                        actualizacionClienteDTONegocios.getDireccionEnvio().getCalle(),
                        actualizacionClienteDTONegocios.getDireccionEnvio().getNumero()
                )
        );

        ClienteDTODatos clienteDTODatos;
        try {
            
            clienteDTODatos = administradorMongodb.actualizarCliente(actualizacionClienteDTODatos);

            ClienteDTONegocios clienteDTONegocios = new ClienteDTONegocios(
                    new IdEntidadGenericoNegocios(clienteDTODatos.getId().getId()),
                    clienteDTODatos.getNombres(),
                    clienteDTODatos.getApellidoPaterno(),
                    clienteDTODatos.getApellidoMaterno(),
                    clienteDTODatos.getTelefono(),
                    clienteDTODatos.getCorreoElectronico(),
                    new DireccionDTONegocios(
                            new IdEntidadGenericoNegocios(clienteDTODatos.getDireccionEnvio().getId()),
                            clienteDTODatos.getDireccionEnvio().getCodigoPostal(),
                            clienteDTODatos.getDireccionEnvio().getColonia(),
                            clienteDTODatos.getDireccionEnvio().getCalle(),
                            clienteDTODatos.getDireccionEnvio().getNumero()
                    )
            );

            return clienteDTONegocios;
        
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
    public List<ClienteDTONegocios> recuperarTodos() {
        
        List<ClienteDTONegocios> listaClientesDTONegocios = new LinkedList<>();

        List<ClienteDTODatos> listaClientesDTODatos = administradorMongodb.recuperarTodosClientes();

        for(ClienteDTODatos clienteDTODatos: listaClientesDTODatos){

            listaClientesDTONegocios.add(
                    new ClienteDTONegocios(
                            new IdEntidadGenericoNegocios(clienteDTODatos.getId().getId()),
                            clienteDTODatos.getNombres(),
                            clienteDTODatos.getApellidoPaterno(),
                            clienteDTODatos.getApellidoMaterno(),
                            clienteDTODatos.getTelefono(),
                            clienteDTODatos.getCorreoElectronico(),
                            clienteDTODatos.getDireccionEnvio() != null ? new DireccionDTONegocios(
                                    new IdEntidadGenericoNegocios(clienteDTODatos.getDireccionEnvio().getId()),
                                    clienteDTODatos.getDireccionEnvio().getCodigoPostal(),
                                    clienteDTODatos.getDireccionEnvio().getColonia(),
                                    clienteDTODatos.getDireccionEnvio().getCalle(),
                                    clienteDTODatos.getDireccionEnvio().getNumero()
                            ) : null
                    )
            );
        }

        return listaClientesDTONegocios;
    }

}
