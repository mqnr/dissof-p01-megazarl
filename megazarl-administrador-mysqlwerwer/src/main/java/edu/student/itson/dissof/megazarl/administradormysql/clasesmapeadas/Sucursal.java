package edu.student.itson.dissof.megazarl.administradormysql.clasesmapeadas;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * Sucursal.java
 *
 * Clase ENTITIY que representa el mapeo de la entidad Sucursal
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
 * Creación de la tabla sucursales
 */
@Entity
@Table(name = "sucursales")
public class Sucursal implements Serializable {

    /**
     * Constructor por defecto
     */
    public Sucursal() {
    }

    /**
     * Constructor con referencia a todos sus atributos
     * @param id Representa el id de la sucursal
     * @param esMatriz Representa un valor booleana que identifica si es una matriz o no
     */
    public Sucursal(Long id, Boolean esMatriz) {
        this.id = id;
        this.esMatriz = esMatriz;
    }

    /**
     * Creación de la columna id_sucursal dentro de la tabla sucursales
     * Genera un id_sucursal autoincremental
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long id;

    /**
     * Creación de la columna es_matriz dentro de la tabla sucursales
     */
    @Column(name = "es_matriz")
    private Boolean esMatriz;
    
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_direccion", nullable = false)
    private Direccion direccion;
    
    /**
     * Representación de una relación 1 a 1 entre sucursal y
     * productosInventario
     */
    @OneToMany(mappedBy = "sucursal")
    private List<ProductoInventario> listaProductosInventario = new ArrayList();
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEsMatriz() {
        return esMatriz;
    }

    public void setEsMatriz(Boolean esMatriz) {
        this.esMatriz = esMatriz;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    public List<ProductoInventario> getProductosInventario() {
        return listaProductosInventario;
    }

    public void agregarProductoInventario(ProductoInventario productoInventario) {
        
        if(productoInventario != null
                && listaProductosInventario != null 
                && !listaProductosInventario.contains(productoInventario)){
            
            listaProductosInventario.add(productoInventario);
            
            if(productoInventario.getSucursal() == null){
                productoInventario.setSucursal(this);
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
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
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
        return "edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.clasesmapeadas.Sucursal[ id=" + id + " ]";
    }
    
}
