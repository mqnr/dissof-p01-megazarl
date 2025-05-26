package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.administradorproveedores.excepciones.ProveedoresPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorclientes.excepciones.ClientesPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdDireccionInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasPersistenciaException;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.administradorsucursales.excepciones.SucursalesPersistenciaException;
import edu.student.itson.dissof.megazarl.direcciones.IAdministradorDirecciones;
import edu.student.itson.dissof.megazarl.dto.negocios.ClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.DireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.PaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.ProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.SucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.IdClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdDireccionDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdProveedorDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.IdSucursalDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoMatrizClienteDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoProveedorMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoSucursalMatrizDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSeleccionPaqueteriaDTONegocios;
import edu.student.itson.dissof.megazarl.dto.negocios.identidad.IdEntidadGenericoNegocios;
import edu.student.itson.dissof.megazarl.mapas.IAdministradorMapas;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.FormatoIdInvalidoNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.ParametroNuloNegocioException;
import edu.student.itson.dissof.megazarl.objetosnegocio.excepciones.RegistroInexistenteNegocioException;
import java.util.LinkedList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class AdministradorPaqueterias implements IAdministradorPaqueterias {
    
    private final IAdministradorClientes administradorClientes;
    private final IAdministradorProveedores administradorProveedores;
    private final IAdministradorSucursales administradorSucursales;
    private final IAdministradorDirecciones administradorDirecciones;
    private final IAdministradorMapas administradorMapas;
    
    private final DireccionDTONegocios DIRECCION_DEFECTO_PAQUETERIA;
    
    public AdministradorPaqueterias(
            IAdministradorClientes administradorClientes,
            IAdministradorSucursales administradorSucursales,
            IAdministradorProveedores administradorProveedores,
            IAdministradorDirecciones administradorDirecciones,
            IAdministradorMapas administradorMapas,
            DireccionDTONegocios direccionDefectoPaqueteria){
        
        this.administradorClientes = administradorClientes;
        this.administradorSucursales = administradorSucursales;
        this.administradorProveedores = administradorProveedores;
        this.administradorMapas = administradorMapas;
        this.administradorDirecciones = administradorDirecciones;
        this.DIRECCION_DEFECTO_PAQUETERIA = direccionDefectoPaqueteria;
        
    }

    @Override
    public PaqueteriaDTONegocios obtenerPaqueteria(IdPaqueteriaDTONegocios idPaqueteriaDTONegocios) throws PaqueteriasPersistenciaException{
        try {
            return Paqueteria.recuperarPorId(idPaqueteriaDTONegocios);
        } catch (FormatoIdInvalidoNegocioException | RegistroInexistenteNegocioException | ParametroNuloNegocioException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
    }

    @Override
    public List<InformacionSeleccionPaqueteriaDTONegocios> obtenerPaqueterias() {
        
        List<InformacionSeleccionPaqueteriaDTONegocios> listaInformacionSeleccionPaqueteriaDTONegocios = new LinkedList<>();
        
        for(PaqueteriaDTONegocios paqueteria : Paqueteria.recuperarTodos()){
            listaInformacionSeleccionPaqueteriaDTONegocios.add(
                    new InformacionSeleccionPaqueteriaDTONegocios(
                                paqueteria.getId(),
                                paqueteria.getDireccionImagenPaqueteria()
                    )   
            );
        }
        
        return listaInformacionSeleccionPaqueteriaDTONegocios;
    }

    @Override
    public float obtenerCostoEnvioSucursalMatriz(InformacionEnvioProductoSucursalMatrizDTONegocios informacionEnvioProductoSucursalMatrizDTONegocios)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdSucursalInvalidoException,
            PaqueteriasIdDireccionInvalidoException,
            PaqueteriasPersistenciaException{
        
        // Se valida la paqueteria
        IdEntidadGenericoNegocios idPaqueteria = informacionEnvioProductoSucursalMatrizDTONegocios.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTONegocios paqueteria = obtenerPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida la sucursal
        IdEntidadGenericoNegocios idSucursal = informacionEnvioProductoSucursalMatrizDTONegocios.getIdSucursal();
        
        try {
            if(!administradorSucursales.validarSucursal(new IdSucursalDTONegocios(idSucursal))){
                throw new PaqueteriasIdSucursalInvalidoException("El ID de sucursal es inválido.");
            }
        } catch (SucursalesPersistenciaException ex) {
           throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        SucursalDTONegocios sucursal;
        try {
            sucursal = administradorSucursales.obtenerSucursal(new IdSucursalDTONegocios(idSucursal));
        } catch (SucursalesPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        if(sucursal == null){
            throw new PaqueteriasIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        SucursalDTONegocios sucursalMatriz;
        try {
            sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        } catch (SucursalesPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoSucursalMatrizDTONegocios.getPesoKgTotal();
        
        DireccionDTONegocios direccionSucursal = sucursalMatriz.getDireccion();
        
        if(direccionSucursal == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la sucursal es inválido.");
        }
        
        DireccionDTONegocios direccionMatriz = sucursalMatriz.getDireccion();
        
        if(direccionMatriz == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        
        String numeroSucursal = direccionSucursal.getNumero();
        String calleSucursal = direccionSucursal.getCalle();
        String coloniaSucursal = direccionSucursal.getColonia();
        String codigoPostalSucursal = direccionSucursal.getCodigoPostal();
        
        String numeroMatriz = direccionMatriz.getNumero();
        String calleMatriz = direccionMatriz.getCalle();
        String coloniaMatriz = direccionMatriz.getColonia();
        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO = 
                new DatosTiempoTrasladoUbicacionesDTO(
                        numeroSucursal, 
                        calleSucursal,
                        coloniaSucursal,
                        codigoPostalSucursal,
                        numeroMatriz, 
                        calleMatriz, 
                        coloniaMatriz,
                        codigoPostalMatriz);
        
        TiempoTrasladoDTO tiempoTrasladoDTO = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoUbicacionesDTO);
        
        float tiempoTraslado = tiempoTrasladoDTO.getTiempoTraslado();

        // Se regresa el costo del envío del producto
        return (float) (cobroKg * pesoKgProducto) + (cobroHora * tiempoTraslado);
    }
    
    @Override
    public float obtenerCostoEnvioMatrizCliente(InformacionEnvioProductoMatrizClienteDTONegocios informacionEnvioProductoMatrizClienteDTONegocios)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdClienteInvalidoException,
            PaqueteriasIdDireccionInvalidoException,
            PaqueteriasPersistenciaException{
        
        // Se valida la paqueteria
        IdEntidadGenericoNegocios idPaqueteria = informacionEnvioProductoMatrizClienteDTONegocios.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTONegocios paqueteria = obtenerPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida el cliente
        IdEntidadGenericoNegocios idCliente = informacionEnvioProductoMatrizClienteDTONegocios.getIdCliente();
        
        try {
            if(!administradorClientes.validarCliente(new IdClienteDTONegocios(idCliente))){
                throw new PaqueteriasIdClienteInvalidoException("El ID de cliente es inválido.");
            }
        } catch (ClientesPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        ClienteDTONegocios cliente;
        try {
            cliente = administradorClientes.obtenerCliente(new IdClienteDTONegocios(idCliente));
        } catch (ClientesPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        if(cliente == null){
            throw new PaqueteriasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        SucursalDTONegocios sucursalMatriz;
        try {
            sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        } catch (SucursalesPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoMatrizClienteDTONegocios.getPesoKgTotal();
        
        DireccionDTONegocios direccionEnvioCliente = cliente.getDireccionEnvio();
        
        
        DireccionDTONegocios direccionMatriz = sucursalMatriz.getDireccion();
        
        if(direccionMatriz == null){
            throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de Matriz es inválido.");
        }
        
        String numeroCliente = direccionEnvioCliente.getNumero();
        String calleCliente = direccionEnvioCliente.getCalle();
        String coloniaCliente = direccionEnvioCliente.getColonia();
        String codigoPostalCliente = direccionEnvioCliente.getCodigoPostal();
        
        String numeroMatriz = direccionMatriz.getNumero();
        String calleMatriz = direccionMatriz.getCalle();
        String coloniaMatriz = direccionMatriz.getColonia();
        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO = 
                new DatosTiempoTrasladoUbicacionesDTO(
                        numeroCliente, 
                        calleCliente,
                        coloniaCliente,
                        codigoPostalCliente,
                        numeroMatriz, 
                        calleMatriz, 
                        coloniaMatriz,
                        codigoPostalMatriz);
        
        TiempoTrasladoDTO tiempoTrasladoDTO = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoUbicacionesDTO);
        
        float tiempoTraslado = tiempoTrasladoDTO.getTiempoTraslado();

        // Se regresa el costo del envío del producto
        return (float) (cobroKg * pesoKgProducto) + (cobroHora * tiempoTraslado);
    }
    
    
    @Override
    public float obtenerCostoEnvioProveedorMatriz(InformacionEnvioProductoProveedorMatrizDTONegocios informacionEnvioProductoProveedorMatrizDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdProveedorInvalidoException,
            PaqueteriasIdDireccionInvalidoException,
            PaqueteriasPersistenciaException{
        
        // Se valida la paqueteria
        IdEntidadGenericoNegocios idPaqueteria = informacionEnvioProductoProveedorMatrizDTO.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTONegocios paqueteria = obtenerPaqueteria(new IdPaqueteriaDTONegocios(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida el cliente
        IdEntidadGenericoNegocios idProveedor = informacionEnvioProductoProveedorMatrizDTO.getIdProveedor();
        
        try {
            if(!administradorProveedores.validarProveedor(new IdProveedorDTONegocios(idProveedor))){
                throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
            }
        } catch (ProveedoresPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        ProveedorDTONegocios proveedor;
        try {
            proveedor = administradorProveedores.obtenerProveedor(new IdProveedorDTONegocios(idProveedor));
        } catch (ProveedoresPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        if(proveedor == null){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        SucursalDTONegocios sucursalMatriz;
        try {
            sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        } catch (SucursalesPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoProveedorMatrizDTO.getPesoKgTotal();
        
        DireccionDTONegocios direccionProveedor = proveedor.getDireccion();
        
        DireccionDTONegocios direccionMatriz = sucursalMatriz.getDireccion();
        
        DireccionDTONegocios direccionPaqueteria = paqueteria.getDireccion();
        
        String numeroProveedor = direccionProveedor.getNumero();
        String calleProveedor = direccionProveedor.getCalle();
        String coloniaProveedor = direccionProveedor.getColonia();
        String codigoPostalProveedor = direccionProveedor.getCodigoPostal();
        
        String numeroMatriz = direccionMatriz.getNumero();
        String calleMatriz = direccionMatriz.getCalle();
        String coloniaMatriz = direccionMatriz.getColonia();
        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoPaqueteriaProveedorDTO = 
                new DatosTiempoTrasladoUbicacionesDTO(
                        numeroProveedor, 
                        calleProveedor,
                        coloniaProveedor,
                        codigoPostalProveedor,
                        numeroMatriz, 
                        calleMatriz, 
                        coloniaMatriz,
                        codigoPostalMatriz);
        
        TiempoTrasladoDTO tiempoTrasladoDTO = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoPaqueteriaProveedorDTO);
        
        float tiempoTraslado = tiempoTrasladoDTO.getTiempoTraslado();
        
        String numeroPaqueteria = direccionPaqueteria.getNumero();
        String callePaqueteria = direccionPaqueteria.getCalle();
        String coloniaPaqueteria = direccionPaqueteria.getColonia();
        String codigoPostalPaqueteria = direccionPaqueteria.getCodigoPostal();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoProveedorMatrizDTO = 
                new DatosTiempoTrasladoUbicacionesDTO(
                        numeroPaqueteria, 
                        callePaqueteria,
                        coloniaPaqueteria,
                        codigoPostalPaqueteria,
                        numeroProveedor, 
                        calleProveedor, 
                        coloniaProveedor,
                        codigoPostalProveedor);
        
        tiempoTraslado +=
                administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoProveedorMatrizDTO).getTiempoTraslado();
        
        
        // Se regresa el costo del envío del producto
        return (float) (cobroKg * pesoKgProducto) + (cobroHora * tiempoTraslado);
    }
    
    /**
     * Implementación del método obtenerTiempoEnvioMatrizEstimado(), de la interfaz
     * {@link IAdministradorPaqueterias}.
     * @param idProveedorPaqueteriaDTO Objeto IdProveedorPaqueteriaDTO que contiene el ID del
     * proveedor que ofrece los productos y la paquetería que envía los productos.
     * @return Objeto Float que representa el tiempo máximo de envío a Matriz en horas
     * de las paqueterías registradas, null si no existen paqueterías registradas.
     */
    @Override
    public Float obtenerTiempoEnvioMatrizEstimado(IdProveedorDTONegocios idProveedorDTONegocios) 
            throws PaqueteriasIdProveedorInvalidoException,
            PaqueteriasIdDireccionInvalidoException,
            PaqueteriasPersistenciaException{
        
        try {
            // Se valida el ID de proveedor.
            if(!administradorProveedores.validarProveedor(idProveedorDTONegocios)){
                throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
            }
        } catch (ProveedoresPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        ProveedorDTONegocios proveedor;
        try {
            proveedor = administradorProveedores.obtenerProveedor(idProveedorDTONegocios);
        } catch (ProveedoresPersistenciaException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        if(proveedor == null){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        DireccionDTONegocios direccionProveedor = proveedor.getDireccion();

        SucursalDTONegocios sucursalMatriz;
        try {
            sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        } catch (SucursalesPersistenciaException ex) {
             throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        DireccionDTONegocios direccionMatriz = sucursalMatriz.getDireccion();
        
        String codigoPostalProveedor = direccionProveedor.getCodigoPostal();
        String coloniaProveedor = direccionProveedor.getColonia();
        String calleProveedor = direccionProveedor.getCalle();
        String numeroProveedor = direccionProveedor.getNumero();
        
        String codigoPostalMatriz = direccionMatriz.getCodigoPostal();
        String coloniaMatriz = direccionMatriz.getColonia();
        String calleMatriz = direccionMatriz.getCalle();
        String numeroMatriz = direccionMatriz.getNumero();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoProveedorMatriz
                = new DatosTiempoTrasladoUbicacionesDTO(
                        numeroProveedor, 
                        calleProveedor, 
                        coloniaProveedor, 
                        codigoPostalProveedor, 
                        numeroMatriz,
                        calleMatriz, 
                        coloniaMatriz, 
                        codigoPostalMatriz);
                
        float tiempoTrasladoProveedorMatriz = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoProveedorMatriz).getTiempoTraslado();
        
        List<PaqueteriaDTONegocios> listaPaqueterias = Paqueteria.recuperarTodos();
        
        DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoPaqueteriaProveedor;
        
        float tiempoTrasladoMaximoPaqueteriaProveedor = 0;
        
        if(listaPaqueterias.isEmpty()){
            String codigoPostalPaqueteria = DIRECCION_DEFECTO_PAQUETERIA.getCodigoPostal();
            String coloniaPaqueteria = DIRECCION_DEFECTO_PAQUETERIA.getColonia();
            String callePaqueteria = DIRECCION_DEFECTO_PAQUETERIA.getCalle();
            String numeroPaqueteria = DIRECCION_DEFECTO_PAQUETERIA.getNumero();
            
            datosTiempoTrasladoPaqueteriaProveedor 
                    = new DatosTiempoTrasladoUbicacionesDTO(
                            numeroPaqueteria, 
                            callePaqueteria,
                            coloniaPaqueteria,
                            codigoPostalPaqueteria,
                            numeroProveedor,
                            calleProveedor,
                            coloniaProveedor,
                            codigoPostalProveedor);
            
            float tiempoTrasladoPaqueteriaProveedor 
                    = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoPaqueteriaProveedor).getTiempoTraslado();
            
            tiempoTrasladoMaximoPaqueteriaProveedor = tiempoTrasladoPaqueteriaProveedor;
            
        } else{
            
            for(PaqueteriaDTONegocios paqueteria: listaPaqueterias){
                
                DireccionDTONegocios direccionPaqueteria = paqueteria.getDireccion();

                if(direccionPaqueteria == null){
                    throw new PaqueteriasIdDireccionInvalidoException("El ID de la dirección de la paquetería es inválido.");
                }
        
                String codigoPostalPaqueteria = direccionPaqueteria.getCodigoPostal();
                String coloniaPaqueteria = direccionPaqueteria.getColonia();
                String callePaqueteria = direccionPaqueteria.getCalle();
                String numeroPaqueteria = direccionPaqueteria.getNumero();

                 datosTiempoTrasladoPaqueteriaProveedor 
                        = new DatosTiempoTrasladoUbicacionesDTO(
                                numeroPaqueteria, 
                                callePaqueteria,
                                coloniaPaqueteria,
                                codigoPostalPaqueteria,
                                numeroProveedor,
                                calleProveedor,
                                coloniaProveedor,
                                codigoPostalProveedor);

                float tiempoTrasladoPaqueteriaProveedor 
                        = administradorMapas.calcularTiempoTraslado(datosTiempoTrasladoPaqueteriaProveedor).getTiempoTraslado();

                

                if(tiempoTrasladoPaqueteriaProveedor > tiempoTrasladoMaximoPaqueteriaProveedor){
                    tiempoTrasladoMaximoPaqueteriaProveedor = tiempoTrasladoPaqueteriaProveedor;
                }
            
            }
            
        }
        
        return tiempoTrasladoMaximoPaqueteriaProveedor + tiempoTrasladoProveedorMatriz;
        
    }
    
    @Override
    public boolean validarPaqueteria(IdPaqueteriaDTONegocios idPaqueteriaDTO) throws PaqueteriasPersistenciaException {
        
        try {
            if(idPaqueteriaDTO == null || idPaqueteriaDTO.getIdPaqueteria() == null || !Paqueteria.existePorId(idPaqueteriaDTO)){
                return false;
            }
        } catch (ParametroNuloNegocioException | FormatoIdInvalidoNegocioException ex) {
            throw new PaqueteriasPersistenciaException(ex.getMessage());
        }
        
        return true;
    }

}
