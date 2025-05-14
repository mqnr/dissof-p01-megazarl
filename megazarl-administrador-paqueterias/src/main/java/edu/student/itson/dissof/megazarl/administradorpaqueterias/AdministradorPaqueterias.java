package edu.student.itson.dissof.megazarl.administradorpaqueterias;

import edu.student.itson.dissof.administradorproveedores.IAdministradorProveedores;
import edu.student.itson.dissof.megazarl.administradorclientes.IAdministradorClientes;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdClienteInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdPaqueteriaInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdProveedorInvalidoException;
import edu.student.itson.dissof.megazarl.administradorpaqueterias.excepciones.PaqueteriasIdSucursalInvalidoException;
import edu.student.itson.dissof.megazarl.administradorsucursales.IAdministradorSucursales;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.DireccionDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.PaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.ProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.SucursalDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdClienteDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdProveedorDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.IdSucursalDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoMatrizClienteDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoProveedorMatrizDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionEnvioProductoSucursalMatrizDTO;
import edu.student.itson.dissof.megazarl.dto.negocios.InformacionSeleccionPaqueteriaDTO;
import edu.student.itson.dissof.megazarl.mapas.IAdministradorMapas;
import edu.student.itson.dissof.megazarl.objetosnegocio.Paqueteria;

import java.util.List;

class AdministradorPaqueterias implements IAdministradorPaqueterias {
    
    private final IAdministradorClientes administradorClientes;
    private final IAdministradorProveedores administradorProveedores;
    private final IAdministradorSucursales administradorSucursales;
    private final IAdministradorMapas administradorMapas;
    
    private final DireccionDTO DIRECCION_DEFECTO_PAQUETERIA;
    
    public AdministradorPaqueterias(
            IAdministradorClientes administradorClientes,
            IAdministradorSucursales administradorSucursales,
            IAdministradorProveedores administradorProveedores,
            IAdministradorMapas administradorMapas,
            DireccionDTO direccionDefectoPaqueteria){
        
        this.administradorClientes = administradorClientes;
        this.administradorSucursales = administradorSucursales;
        this.administradorProveedores = administradorProveedores;
        this.administradorMapas = administradorMapas;
        this.DIRECCION_DEFECTO_PAQUETERIA = direccionDefectoPaqueteria;
        
    }

    @Override
    public PaqueteriaDTO obtenerPaqueteria(IdPaqueteriaDTO idPaqueteriaDTO) {
        return Paqueteria.recuperarPorId(idPaqueteriaDTO);
    }

    @Override
    public List<InformacionSeleccionPaqueteriaDTO> obtenerPaqueterias() {
        return Paqueteria.stream().map(
                paqueteria ->
                        new InformacionSeleccionPaqueteriaDTO(
                                paqueteria.getId(),
                                paqueteria.getDireccionImagenPaqueteria()
                        )
        ).toList();
    }

    @Override
    public float obtenerCostoEnvioSucursalMatriz(InformacionEnvioProductoSucursalMatrizDTO informacionEnvioProductoSucursalMatrizDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdSucursalInvalidoException{
        
        // Se valida la paqueteria
        Long idPaqueteria = informacionEnvioProductoSucursalMatrizDTO.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTO(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTO paqueteria = obtenerPaqueteria(new IdPaqueteriaDTO(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida la sucursal
        Long idSucursal = informacionEnvioProductoSucursalMatrizDTO.getIdSucursal();
        
        if(!administradorSucursales.validarSucursal(new IdSucursalDTO(idSucursal))){
            throw new PaqueteriasIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        SucursalDTO sucursal = administradorSucursales.obtenerSucursal(new IdSucursalDTO(idSucursal));
        
        if(sucursal == null){
            throw new PaqueteriasIdSucursalInvalidoException("El ID de sucursal es inválido.");
        }
        
        SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoSucursalMatrizDTO.getPesoKgTotal();
        
        String numeroSucursal = sucursal.getDireccion().getNumero();
        String calleSucursal = sucursal.getDireccion().getCalle();
        String coloniaSucursal = sucursal.getDireccion().getColonia();
        String codigoPostalSucursal = sucursal.getDireccion().getCodigoPostal();
        
        String numeroMatriz = sucursalMatriz.getDireccion().getNumero();
        String calleMatriz = sucursalMatriz.getDireccion().getCalle();
        String coloniaMatriz = sucursalMatriz.getDireccion().getColonia();
        String codigoPostalMatriz = sucursalMatriz.getDireccion().getCodigoPostal();
        
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
    public float obtenerCostoEnvioMatrizCliente(InformacionEnvioProductoMatrizClienteDTO informacionEnvioProductoMatrizClienteDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdClienteInvalidoException{
        
        // Se valida la paqueteria
        Long idPaqueteria = informacionEnvioProductoMatrizClienteDTO.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTO(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTO paqueteria = obtenerPaqueteria(new IdPaqueteriaDTO(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida el cliente
        Long idCliente = informacionEnvioProductoMatrizClienteDTO.getIdCliente();
        
        if(!administradorClientes.validarCliente(new IdClienteDTO(idCliente))){
            throw new PaqueteriasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        ClienteDTO cliente = administradorClientes.obtenerCliente(new IdClienteDTO(idCliente));
        
        if(cliente == null){
            throw new PaqueteriasIdClienteInvalidoException("El ID de cliente es inválido.");
        }
        
        SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoMatrizClienteDTO.getPesoKgTotal();
        
        String numeroCliente = cliente.getDireccionEnvio().getNumero();
        String calleCliente = cliente.getDireccionEnvio().getCalle();
        String coloniaCliente = cliente.getDireccionEnvio().getColonia();
        String codigoPostalCliente = cliente.getDireccionEnvio().getCodigoPostal();
        
        String numeroMatriz = sucursalMatriz.getDireccion().getNumero();
        String calleMatriz = sucursalMatriz.getDireccion().getCalle();
        String coloniaMatriz = sucursalMatriz.getDireccion().getColonia();
        String codigoPostalMatriz = sucursalMatriz.getDireccion().getCodigoPostal();
        
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
    public float obtenerCostoEnvioProveedorMatriz(InformacionEnvioProductoProveedorMatrizDTO informacionEnvioProductoProveedorMatrizDTO)
            throws PaqueteriasIdPaqueteriaInvalidoException,
            PaqueteriasIdProveedorInvalidoException{
        
        // Se valida la paqueteria
        Long idPaqueteria = informacionEnvioProductoProveedorMatrizDTO.getIdPaqueteria();

        if(!validarPaqueteria(new IdPaqueteriaDTO(idPaqueteria))){
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        PaqueteriaDTO paqueteria = obtenerPaqueteria(new IdPaqueteriaDTO(idPaqueteria));
        
        if (paqueteria == null) {
            throw new PaqueteriasIdPaqueteriaInvalidoException("El ID de paquetería es inválido.");
        }
        
        // Se valida el cliente
        Long idProveedor = informacionEnvioProductoProveedorMatrizDTO.getIdProveedor();
        
        if(!administradorProveedores.validarProveedor(new IdProveedorDTO(idProveedor))){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        ProveedorDTO proveedor = administradorProveedores.obtenerProveedor(new IdProveedorDTO(idProveedor));
        
        if(proveedor == null){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        Float cobroKg = paqueteria.getCobroKg();
        Float cobroHora = paqueteria.getCobroHora();

        // Se obtiene el peso del peso en Kg del producto de la DTO del parámetro y su tiempo de envío
        Double pesoKgProducto = informacionEnvioProductoProveedorMatrizDTO.getPesoKgTotal();
        
        String numeroProveedor = proveedor.getDireccion().getNumero();
        String calleProveedor = proveedor.getDireccion().getCalle();
        String coloniaProveedor = proveedor.getDireccion().getColonia();
        String codigoPostalProveedor = proveedor.getDireccion().getCodigoPostal();
        
        String numeroMatriz = sucursalMatriz.getDireccion().getNumero();
        String calleMatriz = sucursalMatriz.getDireccion().getCalle();
        String coloniaMatriz = sucursalMatriz.getDireccion().getColonia();
        String codigoPostalMatriz = sucursalMatriz.getDireccion().getCodigoPostal();
        
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
        
        String numeroPaqueteria = paqueteria.getDireccion().getNumero();
        String callePaqueteria = paqueteria.getDireccion().getCalle();
        String coloniaPaqueteria = paqueteria.getDireccion().getColonia();
        String codigoPostalPaqueteria = paqueteria.getDireccion().getCodigoPostal();
        
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
    public Float obtenerTiempoEnvioMatrizEstimado(IdProveedorDTO idProveedorDTO) 
            throws PaqueteriasIdProveedorInvalidoException{
        
        // Se valida el ID de proveedor.
        if(!administradorProveedores.validarProveedor(idProveedorDTO)){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        ProveedorDTO proveedor = administradorProveedores.obtenerProveedor(idProveedorDTO);
        
        if(proveedor == null){
            throw new PaqueteriasIdProveedorInvalidoException("El ID de proveedor es inválido.");
        }
        
        SucursalDTO sucursalMatriz = administradorSucursales.obtenerSucursalMatriz();
        
        String codigoPostalProveedor = proveedor.getDireccion().getCodigoPostal();
        String coloniaProveedor = proveedor.getDireccion().getColonia();
        String calleProveedor = proveedor.getDireccion().getCalle();
        String numeroProveedor = proveedor.getDireccion().getNumero();
        
        String codigoPostalMatriz = sucursalMatriz.getDireccion().getCodigoPostal();
        String coloniaMatriz = sucursalMatriz.getDireccion().getColonia();
        String calleMatriz = sucursalMatriz.getDireccion().getCalle();
        String numeroMatriz = sucursalMatriz.getDireccion().getNumero();
        
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
        
        List<PaqueteriaDTO> listaPaqueterias = Paqueteria.recuperarTodos();
        
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
            
            for(PaqueteriaDTO paqueteria: listaPaqueterias){
            
                String codigoPostalPaqueteria = paqueteria.getDireccion().getCodigoPostal();
                String coloniaPaqueteria = paqueteria.getDireccion().getColonia();
                String callePaqueteria = paqueteria.getDireccion().getCalle();
                String numeroPaqueteria = paqueteria.getDireccion().getNumero();

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
    public boolean validarPaqueteria(IdPaqueteriaDTO idPaqueteriaDTO) {
        
        if(idPaqueteriaDTO == null || idPaqueteriaDTO.getIdPaqueteria() == null || !Paqueteria.existePorId(idPaqueteriaDTO)){
            return false;
        }
        
        return true;
    }

}
