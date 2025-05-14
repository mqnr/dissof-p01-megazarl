package edu.student.itson.dissof.megazarl.dto.infraestructura;

import java.util.Objects;
/**
 * GerenteVentasDTO.java
 *
 * Clase DTO que representa los datos de un gerente de ventas de la empresa.
 * 
 * @author Yuri Germán García López
 * ID: 00000252583
 */
public class GerenteVentasDTO {
    
    /**
     * Objeto Long que representa el ID del gerente de ventas.
     */
    private Long id;
    
    /**
     * Objeto String que representa los nombres del gerente de ventas.
     */
    private String nombres;
    
    /**
     * Objeto String que representa el apellido paterno del gerente de ventas.
     */
    private String apellidoPaterno;
    
    /**
     * Objeto String que representa el apellido materno del gerente de ventas.
     */
    private String apellidoMaterno;

    /**
     * Consutructor que permite instanciar un objeto de tipo GerenteVentasDTO, que recibe
     * un ID de gerente de ventas.
     * @param id                    Objeto de tipo id que representa el id del gerente de ventas.
     * @param nombres               Objeto String que representa los nombres del gerente de ventas.
     * @param apellidoPaterno       Objeto String que representa el apellido paterno del gerente de ventas.
     * @param apellidoMaterno       Objeto String que representa el apellido materno del gerente de ventas.
     */
    public GerenteVentasDTO(Long id, String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Consutructor que permite instanciar un objeto de tipo GerenteVentasDTO.
     * @param nombres               Objeto String que representa los nombres del gerente de ventas.
     * @param apellidoPaterno       Objeto String que representa el apellido paterno del gerente de ventas.
     * @param apellidoMaterno       Objeto String que representa el apellido materno del gerente de ventas.
     */
    public GerenteVentasDTO(String nombres, String apellidoPaterno, String apellidoMaterno) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Método que permite obtener los nombres del gerente de ventas.
     * @return Objeto String que representa los nombres del gerente de ventas.
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @return 
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Método que permite obtener el apellido paterno del gerente de ventas.
     * @return Objeto String que representa el apellido paterno del gerente de ventas.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Método que permite obtener el apellido matenro del gerente de ventas.
     * @return Objeto String que representa el apellido materno del gerente de ventas.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Mètodo que permite establecer el ID del gerente de ventas.
     * @param id Objeto Long que representa el ID del gerente de ventas.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el hash code del gerente de ventas, a partir de su ID.
     * @return Dato int que representa el hash code del gerente de ventas.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que permite verificar si el objeto del parámetro es igual 
     * a este gerente de ventas, basándose en su ID.
     * @param obj Objeto a determinar si es igual a este gerente de ventas.
     * @return Dato boolean, true si el objeto es igual al gerente de ventas, false en
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
        final GerenteVentasDTO other = (GerenteVentasDTO) obj;
        return Objects.equals(this.id, other.id);
    }
    
}