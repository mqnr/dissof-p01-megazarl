package edu.student.itson.dissof.megazarl.administradorsql.clasesmapeadas;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * ProductoInventario.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad ProductoInventario
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
 * Creación de la tabla productos_inventario
 */
@Entity
@Table(name = "productos_inventario")
public class ProductoInventario implements Serializable {

    /**
     * Constructor por defecto
     */
    public ProductoInventario() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param producto Representa los productos que se tienen en el inventario
     * @param sucursal Representa la sucursal asociada
     */
    public ProductoInventario(Producto producto, Sucursal sucursal) {
        this.producto = producto;
        this.sucursal = sucursal;
    }

    /**
     * Creación de la columna id_producto_inventario dentro de la tabla productos_inventario
     * Genera un id_producto_inventario autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_inventario")
    private Long id;

    /**
     * Representación de una relación Muchos a Muchos entre productos_inventario
     * y producto
     */
    @ManyToOne()
    @JoinColumn(name = "id_producto", nullable = true)
    private Producto producto;
    
    /**
     * Representación de una relación Muchos a Muchos entre productos_inventario
     * y sucursal
     */
    @ManyToOne()
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;
    
    /**
     * Getters y Setters para cada atributo de la clase
     */
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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
        if (!(object instanceof ProductoInventario)) {
            return false;
        }
        ProductoInventario other = (ProductoInventario) object;
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
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.ProductoInventario[ id=" + id + " ]";
    }
    
}