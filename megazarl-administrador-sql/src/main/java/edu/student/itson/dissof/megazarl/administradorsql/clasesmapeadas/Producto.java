package edu.student.itson.dissof.megazarl.administradorsql.clasesmapeadas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * Producto.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad Producto 
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

/**
 * Creación de la tabla productos
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    /**
     * Constructor por defecto
     */
    public Producto() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param nombre Representa el nombre del producto
     * @param variedad Representa la variedad de productos con la que se cuenta
     * @param descripcion Representa la descripción de cada producto
     * @param milesSemillas Representa la cantidad de semillas en miles
     * @param precio Representa el precio de cada producto
     * @param pesoKg Representa el peso en KG de cada producto
     * @param direccionImagen Representa la dirección de la imagen relacionada con el producto
     * @param proveedor Representa el proveedor de cada producto
     */
    public Producto(String nombre, String variedad, String descripcion, String milesSemillas, Double precio, Double pesoKg, String direccionImagen, Proveedor proveedor) {
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagen = direccionImagen;
        this.proveedor = proveedor;
    }

    /**
     * Creación de la columna id_producto dentro de la tabla productos
     * Genera un id_producto autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long id;

    /**
     * Creación de la columna nombre dentro de la tabla productos
     * NO admite valores nulos
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    /**
     * Creación de la columna variedad dentro de la tabla productos
     * NO admite valores nulos
     */
    @Column(name = "variedad", nullable = false)
    private String variedad;
    
    /**
     * Creación de la columna descripcion dentro de la tabla productos
     * NO admite valores nulos
     */
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    
    /**
     * Creación de la columna miles_semillas dentro de la tabla productos
     * NO admite valores nulos
     */
    @Column(name = "miles_semillas", nullable = false)
    private String milesSemillas;
    
    /**
     * Creación de la columna precio dentro de la tabla productos
     * NO admite valores nulos
     */
    @Column(name = "precio", nullable = false)
    private Double precio;
    
    /**
     * Creación de la columna peso_kg dentro de la tabla productos
     * NO admite valores nulos
     */
    @Column(name = "peso_kg", nullable = false)
    private Double pesoKg;
    
    /**
     * Creación de la columna direccion_imagen dentro de la tabla productos
     * NO admite valores nulos
     */
    @Column(name = "direccion_imagen", nullable = false)
    private String direccionImagen;
    
    /**
     * Representación de una relación Muchos a 1 entre producto
     * y proveedor
     */
    @ManyToOne()
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;
    
    /**
     * Representación de una relación Uno a Muchos entre producto y
     * productosInventario
     */
    @OneToMany(mappedBy = "producto")
    private List<ProductoInventario> productosInventario = new ArrayList();
    
    /**
     * Representación de una relación Uno a Muchos entre producto y 
     * productosCarritoCompras
     */
    @OneToMany(mappedBy = "producto")
    private List<ProductoCarrito> productoCarritoCompras = new ArrayList();
    
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

    public String getVariedad() {
        return variedad;
    }

    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMilesSemillas() {
        return milesSemillas;
    }

    public void setMilesSemillas(String milesSemillas) {
        this.milesSemillas = milesSemillas;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public String getDireccionImagen() {
        return direccionImagen;
    }

    public void setDireccionImagen(String direccionImagen) {
        this.direccionImagen = direccionImagen;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<ProductoInventario> getProductosInventario() {
        return productosInventario;
    }

    public void setProductosInventario(List<ProductoInventario> productosInventario) {
        this.productosInventario = productosInventario;
    }

    public List<ProductoCarrito> getProductoCarritoCompras() {
        return productoCarritoCompras;
    }

    public void setProductoCarritoCompras(List<ProductoCarrito> productoCarritoCompras) {
        this.productoCarritoCompras = productoCarritoCompras;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
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
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.Producto[ id=" + id + " ]";
    }
    
}