package edu.student.itson.dissof.megazarl.objetosnegocio;

/**
 * Proveedor.java
 *
 * Clase que representa un proveedor de semillas registrado en el sistema,
 * con su información de identificación y logotipo asociado.
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
public class ProveedorON {
    Integer id;
    String nombre;
    String direccionImagenProveedor;

    /**
     * Constructor que inicializa un proveedor con todos sus atributos.
     * @param id Objeto Integer que representa el ID único del proveedor.
     * @param nombre Objeto String que representa el nombre del proveedor.
     * @param direccionImagenProveedor Objeto String que representa la ruta de la imagen del logotipo del proveedor.
     */
    public ProveedorON(Integer id, String nombre, String direccionImagenProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.direccionImagenProveedor = direccionImagenProveedor;
    }

    /**
     * Método que permite obtener el ID del proveedor.
     * @return Objeto Integer que representa el ID único del proveedor.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Método que permite establecer el ID del proveedor.
     * @param id Objeto Integer que representa el ID único del proveedor.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Método que permite obtener el nombre del proveedor.
     * @return Objeto String que representa el nombre del proveedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que permite establecer el nombre del proveedor.
     * @param nombre Objeto String que representa el nombre del proveedor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que permite obtener la ruta de la imagen del logotipo del proveedor.
     * @return Objeto String que representa la ruta de la imagen del logotipo del proveedor.
     */
    public String getDireccionImagenProveedor() {
        return direccionImagenProveedor;
    }

    /**
     * Método que permite establecer la ruta de la imagen del logotipo del proveedor.
     * @param direccionImagenProveedor Objeto String que representa la ruta de la imagen del logotipo del proveedor.
     */
    public void setDireccionImagenProveedor(String direccionImagenProveedor) {
        this.direccionImagenProveedor = direccionImagenProveedor;
    }
}
