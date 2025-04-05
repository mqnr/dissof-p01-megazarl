package edu.student.itson.dissof.megazarl.objetosnegocio;

import java.util.List;
import java.util.Objects;

/**
 * Producto.java
 *
 * Clase que representa un producto (semilla) disponible para venta en el sistema,
 * con toda su información detallada, proveedor asociado e inventario disponible.
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
public class ProductoON {

    private Integer id;
    private String nombre;
    private String variedad;
    private String descripcion;
    private Integer milesSemillas;
    private Double precio;
    private Double pesoKg;
    private String direccionImagenProducto;
    private ProveedorON proveedor;
    private List<ProductoInventario> listaProductoInventario;

    /**
     * Constructor que inicializa un producto con todos sus atributos.
     * @param id Objeto Integer que representa el ID único del producto.
     * @param nombre Objeto String que representa el nombre del producto.
     * @param variedad Objeto String que representa la variedad de la semilla.
     * @param descripcion Objeto String que representa la descripción detallada del producto.
     * @param milesSemillas Objeto Integer que representa la cantidad de miles de semillas por unidad.
     * @param precio Objeto Double que representa el precio unitario del producto.
     * @param pesoKg Objeto Double que representa el peso en kilogramos del producto.
     * @param direccionImagenProducto Objeto String que representa la ruta de la imagen del producto.
     * @param proveedor Objeto Proveedor que representa el proveedor del producto.
     * @param listaProductoInventario Objeto List que contiene las unidades en inventario de este producto.
     */
    public ProductoON(Integer id, String nombre, String variedad, String descripcion, Integer milesSemillas, Double precio, Double pesoKg, String direccionImagenProducto, ProveedorON proveedor, List<ProductoInventario> listaProductoInventario) {
        this.id = id;
        this.nombre = nombre;
        this.variedad = variedad;
        this.descripcion = descripcion;
        this.milesSemillas = milesSemillas;
        this.precio = precio;
        this.pesoKg = pesoKg;
        this.direccionImagenProducto = direccionImagenProducto;
        this.proveedor = proveedor;
        this.listaProductoInventario = listaProductoInventario;
    }

    /**
     * Método que permite obtener el ID del producto.
     * @return Objeto Integer que representa el ID único del producto.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que permite establecer el ID del producto.
     * @param id Objeto Integer que representa el ID único del producto.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el nombre del producto.
     * @return Objeto String que representa el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite establecer el nombre del producto.
     * @param nombre Objeto String que representa el nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que permite obtener la variedad de la semilla.
     * @return Objeto String que representa la variedad de la semilla.
     */
    public String getVariedad() {
        return variedad;
    }

    /**
     * Método que permite establecer la variedad de la semilla.
     * @param variedad Objeto String que representa la variedad de la semilla.
     */
    public void setVariedad(String variedad) {
        this.variedad = variedad;
    }

    /**
     * Método que permite obtener la descripción detallada del producto.
     * @return Objeto String que representa la descripción detallada del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Método que permite establecer la descripción detallada del producto.
     * @param descripcion Objeto String que representa la descripción detallada del producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método que permite obtener la cantidad de miles de semillas por unidad.
     * @return Objeto Integer que representa la cantidad de miles de semillas por unidad.
     */
    public Integer getMilesSemillas() {
        return milesSemillas;
    }

    /**
     * Método que permite establecer la cantidad de miles de semillas por unidad.
     * @param milesSemillas Objeto Integer que representa la cantidad de miles de semillas por unidad.
     */
    public void setMilesSemillas(Integer milesSemillas) {
        this.milesSemillas = milesSemillas;
    }

    /**
     * Método que permite obtener el precio unitario del producto.
     * @return Objeto Double que representa el precio unitario del producto.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Método que permite establecer el precio unitario del producto.
     * @param precio Objeto Double que representa el precio unitario del producto.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Método que permite obtener el peso en kilogramos del producto.
     * @return Objeto Double que representa el peso en kilogramos del producto.
     */
    public Double getPesoKg() {
        return pesoKg;
    }

    /**
     * Método que permite establecer el peso en kilogramos del producto.
     * @param pesoKg Objeto Double que representa el peso en kilogramos del producto.
     */
    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    /**
     * Método que permite obtener la ruta de la imagen del producto.
     * @return Objeto String que representa la ruta de la imagen del producto.
     */
    public String getDireccionImagenProducto() {
        return direccionImagenProducto;
    }

    /**
     * Método que permite establecer la ruta de la imagen del producto.
     * @param direccionImagenProducto Objeto String que representa la ruta de la imagen del producto.
     */
    public void setDireccionImagenProducto(String direccionImagenProducto) {
        this.direccionImagenProducto = direccionImagenProducto;
    }

    /**
     * Método que permite obtener el proveedor del producto.
     * @return Objeto Proveedor que representa el proveedor del producto.
     */
    public ProveedorON getProveedor() {
        return proveedor;
    }

    /**
     * Método que permite establecer el proveedor del producto.
     * @param proveedor Objeto Proveedor que representa el proveedor del producto.
     */
    public void setProveedor(ProveedorON proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Método que permite obtener las unidades en inventario de este producto.
     * @return Objeto List que contiene las unidades en inventario de este producto.
     */
    public List<ProductoInventario> getListaProductoInventario() {
        return listaProductoInventario;
    }

    /**
     * Método que permite establecer las unidades en inventario de este producto.
     * @param listaProductoInventario Objeto List que contiene las unidades en inventario de este producto.
     */
    public void setListaProductoInventario(List<ProductoInventario> listaProductoInventario) {
        this.listaProductoInventario = listaProductoInventario;
    }

    /**
     * Método que calcula el código hash del objeto, basado en su ID.
     * @return Valor int que representa el código hash del producto.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Método que compara este producto con otro objeto para determinar igualdad.
     * @param obj Objeto a comparar con este producto.
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
        final ProductoON other = (ProductoON) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
