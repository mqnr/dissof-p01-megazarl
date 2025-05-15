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
 * ProductoCarritoCompras.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad ProductoCarritoCompras
 *
 * @author Yuri Germán García López
 * ID: 00000252583
 * @author Luis Rafael Lagarda Encinas
 * ID: 00000252607
 * @author Manuel Romo López
 * ID: 00000253080
 *
 */

/**
 * Creación de la tabla productos_carrito_compras
 */
@Entity
@Table(name = "productos_carrito_compras")
public class ProductoCarrito implements Serializable {

    /**
     * Constructor por defecto
     */
    public ProductoCarrito() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param carritoCompras Representa un objeto de tipo carritoCompras
     * @param producto Representa un objeto de tipo producto
     */
    public ProductoCarrito(CarritoCompras carritoCompras, Producto producto) {
        this.carritoCompras = carritoCompras;
        this.producto = producto;
    }

    /**
     * Creación de la columna id_producto_carrito_compras dentro de la tabla productos_carrito_compras
     * Genera un id_producto_carrito_compras autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_carrito_compras")
    private Long id;

    /**
     * Representación de una relación Muchos a Uno entre ProductoCarrito 
     * y CarritoCompras
     */
    @ManyToOne()
    @JoinColumn(name = "id_carrito", nullable = false)
    private CarritoCompras carritoCompras;
    
    /**
     * Representación de una relación Muchos a Uno entre ProductoCarrito 
     * y producto
     */
    @ManyToOne()
    @JoinColumn(name = "producto", nullable = false)
    private Producto producto;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarritoCompras getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoCompras carritoCompras) {
        
        if(carritoCompras != null){
            this.carritoCompras = carritoCompras;
        
            if (carritoCompras.getProductosCarritoCompras() != null && !carritoCompras.getProductosCarritoCompras().contains(this)) {

                carritoCompras.getProductosCarritoCompras().add(this);

            }
        }
        
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        
        if(producto != null){
            this.producto = producto;
        
            if(producto.getProductosCarritoCompras() != null && !producto.getProductosCarritoCompras().contains(this)){

                producto.getProductosCarritoCompras().add(this);

            }
        }
        
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
        if (!(object instanceof ProductoCarrito)) {
            return false;
        }
        ProductoCarrito other = (ProductoCarrito) object;
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
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.ProductoCarritoCompras[ id=" + id + " ]";
    }
    
}