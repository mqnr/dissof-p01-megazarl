
package edu.student.itson.dissof.megazarl.objetosnegocio;


public class Proveedor {
    Integer id;
    String nombre;
    String direccionImagenProveedor;

    public Proveedor(Integer id, String nombre, String direccionImagenProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.direccionImagenProveedor = direccionImagenProveedor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionImagenProveedor() {
        return direccionImagenProveedor;
    }

    public void setDireccionImagenProveedor(String direccionImagenProveedor) {
        this.direccionImagenProveedor = direccionImagenProveedor;
    }

    
    
   
    
    
}
