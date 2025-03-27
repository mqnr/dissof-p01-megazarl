
package edu.student.itson.dissof.administradorproveedores;


public class FAdministradorProveedores implements IAdministradorProveedores{

    @Override
    public String obtenerDireccionImagenProveedor(Integer idProveedor) {
        
        String direccionImagenProveedor;
        
        if(idProveedor == 10){
            direccionImagenProveedor = "/seminis.png";
        } else if(idProveedor == 20){
            direccionImagenProveedor = "/harrisMoran.png";
        } else if(idProveedor == 30){
            direccionImagenProveedor = "/enzaZaden.png";
        } else{
            direccionImagenProveedor = null;
        }
        
        return direccionImagenProveedor;
    }
    
    
    public String obtenerNombreProveedor(Integer idProveedor){
        
        String nombreProveedor;
        if(idProveedor == 10){
            
            nombreProveedor = "Seminis";
            
        } else if(idProveedor == 20){
            
            nombreProveedor = "Harris Moran";
            
        } else if(idProveedor == 30){
            
            nombreProveedor = "Enza Zaden";
            
        } else{
            
            nombreProveedor = null;
            
        }
        
        return nombreProveedor;
    }
    
    
}
