package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 * ClienteDTO.java
 * 
 * Clase DTO que representa los datos de un cliente de la empresa.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 * @author Martín Zamorano Acuña
 * ID: 00000251923
 * 
 */
public class ClienteDTO{
    
    /**
     * Objeto Long que representa el ID del cliente.
     */
    private Long id;
    
    /**
     * Objeto String que representa los nombres del cliente.
     */
    private String nombres;
    
    /**
     * Objeto String que representa el apellido paterno del cliente.
     */
    private String apellidoPaterno;
    
    /**
     * Objeto String que representa el apellido materno del cliente.
     */
    private String apellidoMaterno;
    
    /**
     * Objeto String que representa el telefono del cliente.
     */
    private String telefono;
    
    /**
     * Objeto String que representa el correo electrónico del cliente.
     */
    private String correoElectronico;
    
    /**
     * Objeto DireccionDTO que representa la dirección de envío del cliente.
     */
    private DireccionDTO direccionEnvio;
    
    /**
     * Objeto {@literal List<CarritoComprasDTO>} que representa la lista de carritos
     * de compra vigentes y no vigentes que tiene o ha tenido.
     */
    private List<CarritoComprasDTO> listaCarritosCompras = new LinkedList<>();

    /**
     * Consutructor que permite instanciar un objeto de tipo ClienteDTO, que recibe
     * un ID de cliente.
     * @param id                    Ojeto Long que representa el ID de cliente.s
     * @param nombres               Objeto String que representa los nombres del cliente.
     * @param apellidoPaterno       Objeto String que representa el apellido paterno del cliente.
     * @param apellidoMaterno       Objeto String que representa el apellido materno del cliente.
     * @param telefono              Objeto String que representa el telefono del cliente.
     * @param correoElectronico     Objeto String que representa el correo electrónico del cliente.
     * @param direccionEnvio        Objecto DireccionDTO que representa la dirección de envío del cliente.
     */
    public ClienteDTO(
            Long id,
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno,
            String telefono, 
            String correoElectronico,
            DireccionDTO direccionEnvio) {
        
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccionEnvio = direccionEnvio;
    }
    
    /**
     * Consutructor que permite instanciar un objeto de tipo ClienteDTO.
     * @param nombres               Objeto String que representa los nombres del cliente.
     * @param apellidoPaterno       Objeto String que representa el apellido paterno del cliente.
     * @param apellidoMaterno       Objeto String que representa el apellido materno del cliente.
     * @param telefono              Objeto String que representa el telefono del cliente.
     * @param correoElectronico     Objeto String que representa el correo electrónico del cliente.
     * @param direccionEnvio        Objecto DireccionDTO que representa la dirección de envío del cliente.
     */
    public ClienteDTO(
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno,
            String telefono, 
            String correoElectronico,
            DireccionDTO direccionEnvio) {
        
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccionEnvio = direccionEnvio;
    }
    
    /**
     * Consutructor que permite instanciar un objeto de tipo ClienteDTO, sin ID ni dirección
     * de envío.
     * @param nombres               Objeto String que representa los nombres del cliente.
     * @param apellidoPaterno       Objeto String que representa el apellido paterno del cliente.
     * @param apellidoMaterno       Objeto String que representa el apellido materno del cliente.
     * @param telefono              Objeto String que representa el telefono del cliente.
     * @param correoElectronico     Objeto String que representa el correo electrónico del cliente.
     */
    public ClienteDTO(
            String nombres,
            String apellidoPaterno, 
            String apellidoMaterno,
            String telefono, 
            String correoElectronico) {
        
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    /**
     * Métdo que permit obtener el ID del cliente.
     * @return Objeto Long que representa el ID del cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obtener los nombres del cliente.
     * @return Objeto String que representa los nombres del cliente.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Método que permite obtener el apellido paterno del cliente.
     * @return Objeto String que representa el apellido paterno del cliente.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Método que permite obtener el apellido matenro del cliente.
     * @return Objeto String que representa el apellido materno del cliente.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Método que permite obtener el teléfono del cliente.
     * @return Objeto String que representa el teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Método que permite obtener el correo electrónico del cliente.
     * @return Objeto String que representa el correo electrónico del cliente.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Método que permite obtener la dirección de envío del cliente.
     * @return Objeto DireccionDTO que representa la dirección de envío del cliente.
     */
    public DireccionDTO getDireccionEnvio() {
        return direccionEnvio;
    }

    /**
     * Método que permite obtener la lista de carritos de compras del cliente.
     * @return Objeto {@literal List<CarritoComprasDTO>} que representa la lista
     * de carritos de compra que ha tenido el cliente.
     */
    public List<CarritoComprasDTO> getListaCarritosCompras() {
        return listaCarritosCompras;
    }

    /**
     * Mètodo que permite establecer el ID del cliente.
     * @param id Objeto Long que representa el ID del cliente.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    

    /**
     * Método que permite obtener el hash code del cliente, a partir de su ID.
     * @return Dato int que representa el hash code del cliente.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a este cliente, basándose en su ID.
     * @param obj Objeto a determinar si es igual a este cliente.
     * @return Dato boolean, true si el objeto es igual al cliente, false en
     * caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClienteDTO other = (ClienteDTO) obj;
        return Objects.equals(this.id, other.id);
    }

}