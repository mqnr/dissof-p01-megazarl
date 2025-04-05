package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.Objects;

/**
 * ProductoInventario.java
 *
 * Clase que representa una unidad de un producto en el inventario de una sucursal
 * específica, indicando si está apartado o disponible para su venta.
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
public class ProductoInventario {

    private Integer id;
    private Producto producto;
    private Sucursal sucursal;
    private Boolean apartado;

    /**
     * Constructor que inicializa un producto en inventario sin asociarlo a un producto específico.
     * @param id Objeto Integer que representa el ID único del producto en inventario.
     * @param sucursal Objeto Sucursal que representa la sucursal donde se encuentra este producto.
     * @param apartado Objeto Boolean que indica si el producto está apartado (true) o disponible (false).
     */
    public ProductoInventario(Integer id, Sucursal sucursal, Boolean apartado) {
        this.id = id;
        this.sucursal = sucursal;
        this.apartado = apartado;
    }

    /**
     * Método que permite obtener el ID del producto en inventario.
     * @return Objeto Integer que representa el ID único del producto en inventario.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que permite establecer el ID del producto en inventario.
     * @param id Objeto Integer que representa el ID único del producto en inventario.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el producto asociado a este inventario.
     * @return Objeto Producto que representa el producto asociado a este inventario.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Método que permite establecer el producto asociado a este inventario.
     * @param producto Objeto Producto que representa el producto asociado a este inventario.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Método que permite obtener la sucursal donde se encuentra este producto.
     * @return Objeto Sucursal que representa la sucursal donde se encuentra este producto.
     */
    public Sucursal getSucursal() {
        return sucursal;
    }

    /**
     * Método que permite establecer la sucursal donde se encuentra este producto.
     * @param sucursal Objeto Sucursal que representa la sucursal donde se encuentra este producto.
     */
    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * Método que permite saber si el producto está apartado.
     * @return Objeto Boolean que indica si el producto está apartado (true) o disponible (false).
     */
    public Boolean getApartado() {
        return apartado;
    }

    /**
     * Método que permite establecer si el producto está apartado.
     * @param apartado Objeto Boolean que indica si el producto está apartado (true) o disponible (false).
     */
    public void setApartado(Boolean apartado) {
        this.apartado = apartado;
    }

    /**
     * Método que calcula el código hash del objeto, basado en su ID.
     * @return Valor int que representa el código hash del producto en inventario.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que compara este producto en inventario con otro objeto para determinar igualdad.
     * @param obj Objeto a comparar con este producto en inventario.
     * @return true si los objetos son iguales, false en caso contrario.
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
        final ProductoInventario other = (ProductoInventario) obj;
        return Objects.equals(this.id, other.id);
    }
}
