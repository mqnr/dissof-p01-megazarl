package edu.student.itson.dissof.megazarl.administradorsql.clasesmapeadas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Proveedor.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad Proveedor
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Vladimir Iván Mendoza Baypoli
 * ID: 00000252758
 * @author Manuel Romo López
 * ID: 00000253080
 *
 */

/**
 * Creación de la tabla provedores
 */
@Entity
@Table(name = "proveedores")
public class Proveedor implements Serializable {
    
    /**
     * Constructor por defecto
     */
    public Proveedor() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param nombre Representa el nombre del proveedor
     * @param telefono Representa el teléfono del proveedor
     * @param direccionImagen Representa la direccion de la imagen asociada al proveedor
     * @param direccion Representa la dirección asociada al proveedor
     */
    public Proveedor(String nombre, String telefono, String direccionImagen, Direccion direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccionImagen = direccionImagen;
        this.direccion = direccion;
    }

    /**
     * Creación de la columna id_proveedor dentro de la tabla proveedores
     * Genera un id_proveedor autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Long id;

    /**
     * Creación de la columna nombre dentro de la tabla proveedores
     * NO admite valores nulos
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    /**
     * Creación de la columna telefono dentro de la tabla proveedores
     * NO admite valores nulos
     */
    @Column(name = "telefono", nullable = false)
    private String telefono;
    
    /**
     * Creación de la columna direccion_imagen dentro de la tabla proveedores
     * NO admite valores nulos
     */
    @Column(name = "direccion_imagen", nullable = false)
    private String direccionImagen;
    
    /**
     * Representación de una relación 1 a 1 entre proveedor
     * y direccion
     */
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;

    /**
     * Representación de una relación 1 a Muchos entre proveedor 
     * y productos
     */
    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos = new ArrayList();
    
    /**
     * Getters y Setters para cada atributo de la clase 
     */
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Método que genera hashCode para objetos de la clase entidad CarritoCompras
     * @return hash de los objetos de la clase
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Método que compara objetos de la clase a partir de su id
     * @param object Representa un objeto de la clase a comparar
     * @return valor booleano para identificar coincidencias
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Método toString que concatena e imprime los objetos de la clase
     * @return String con los atributos del carrito de compras
     */
    @Override
    public String toString() {
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.Proveedor[ id=" + id + " ]";
    }
    
}