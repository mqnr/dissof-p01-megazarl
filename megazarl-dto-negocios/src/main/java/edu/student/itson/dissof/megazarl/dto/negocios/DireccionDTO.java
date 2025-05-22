package edu.student.itson.dissof.megazarl.dto.negocios;

import java.util.Objects;

/**
 * DireccionDTO.java
 *
 * Clase DTO que representa una dirección completa.
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
public class DireccionDTO{
    
    /**
     * Objeto Long que representa el ID de la dirección.
     */
    private Long id;
    
    /**
     * Objeto String que representa el estado de la dirección.
     */
    private String estado;
    
    /**
     * Objeto String que representa la ciudad de la dirección.
     */
    private String ciudad;
    
    /**
     * Objeto String que representa el Código Postal de la dirección.
     */
    private String codigoPostal;
    
    /**
     * Objeto String que rerpesenta la colonia de la dirección.
     */
    private String colonia;
    
    /**
     * Objeto String que representa la calle de la dirección.
     */
    private String calle;
    
    /**
     * Objeto String que representa el número de la dirección.
     */
    private String numero;

    /**
     * Constructor de la clase que recibe los parámetros necesarios para crear
     * una dirección, incluyendo el ID.
     * @param id            Objeto Long que representa el ID de la dirección.
     * @param estado        Objeto String que representa el estado de la dirección.
     * @param ciudad        Objeto String que representa la ciudad de la dirección.
     * @param codigoPostal  Objeto String que representa el Código Postal de la dirección.
     * @param colonia       Objeto String que rerpesenta la colonia de la dirección.
     * @param calle         Objeto String que representa la calle de la dirección.
     * @param numero        Objeto String que representa el número de la dirección.
     */
    public DireccionDTO(
            Long id,
            String estado,
            String ciudad,
            String codigoPostal, 
            String colonia, 
            String calle, 
            String numero) {
        
        this.id = id;
        this.estado = estado;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }
    
    /**
     * Constructor de la clase que recibe los parámetros necesarios para crear
     * una dirección, sin inculir ID.
     * @param estado        Objeto String que representa el estado de la dirección.
     * @param ciudad        Objeto String que representa la ciudad de la dirección.
     * @param codigoPostal  Objeto String que representa el Código Postal de la dirección.
     * @param colonia       Objeto String que rerpesenta la colonia de la dirección.
     * @param calle         Objeto String que representa la calle de la dirección.
     * @param numero        Objeto String que representa el número de la dirección.
     */
    public DireccionDTO(
            String estado,
            String ciudad,
            String codigoPostal, 
            String colonia, 
            String calle, 
            String numero) {
        
        this.estado = estado;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }
    
    /**
     * Constructor de la clase que recibe los parámetros necesarios para crear
     * una dirección, pero solo aquellos que no se derivan del Código Postal.
     * @param codigoPostal  Objeto String que representa el Código Postal de la dirección.
     * @param colonia       Objeto String que rerpesenta la colonia de la dirección.
     * @param calle         Objeto String que representa la calle de la dirección.
     * @param numero        Objeto String que representa el número de la dirección.
     */
    public DireccionDTO(
            String codigoPostal, 
            String colonia, 
            String calle, 
            String numero) {
        
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }
    
    /**
     * Constructor de la clase que recibe los parámetros necesarios para crear
     * una dirección, incluyendo el ID.
     * @param id            Objeto Long que representa el ID de la dirección.
     * @param codigoPostal  Objeto String que representa el Código Postal de la dirección.
     * @param colonia       Objeto String que rerpesenta la colonia de la dirección.
     * @param calle         Objeto String que representa la calle de la dirección.
     * @param numero        Objeto String que representa el número de la dirección.
     */
    public DireccionDTO(
            Long id,
            String codigoPostal, 
            String colonia, 
            String calle, 
            String numero) {
        
        this.id = id;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }
    

    /**
     * Mètodo que permite obtener el ID de la dirección.
     * @return Objeto Long que representa el ID de la dirección.
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que permite obtener el estado de la dirección.
     * @return Objeto String que representa el estado de la dirección.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Método que permite obtener la ciudad de la dirección.
     * @return  Objeto String que representa la ciudad de la dirección.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Método que permite obtener el Código Postal de la dirección.
     * @return Objeto String que representa el Código Postal de la dirección. 
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Método que permite obtener la colonia de la dirección.
     * @return Objeto String que rerpesenta la colonia de la dirección.
     */
    public String getColonia() {
        return colonia;
    }
    
    /**
     * Método que permite obtener la calle de la dirección.
     * @return Objeto String que representa la calle de la dirección.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Método que permite obtener el número de la dirección.
     * @return Objeto String que representa el número de la dirección.
     */
    public String getNumero() {
        return numero;
    }

    
    /**
     * Método que permite establecer el ID de la dirección.
     * @param id Objeto Long que representa el ID de la dirección.
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Método que permite establecer el estado de la dirección.
     * @param estado Objeto String que representa el estado de la dirección.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Método que permite establecer la ciudad de la dirección.
     * @param ciudad Objeto String que representa la ciudad de la dirección.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    /**
     * Método que permite obtener el hash code de la dirección, a partir de su ID.
     * @return Dato int que representa el hash code de la dirección.
     */
    @Override    
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a esta dirección, basándose en su ID.
     * @param obj Objeto a determinar si es igual a esta dirección.
     * @return Dato boolean, true si el objeto es igual a la dirección, false en
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
        final DireccionDTO other = (DireccionDTO) obj;
        return Objects.equals(this.id, other.id);
    }

}



