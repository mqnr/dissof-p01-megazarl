
package presentacion.interfaces;

import java.util.HashMap;


public interface IInformacionProducto {
    
    public abstract void setProducto(HashMap<String, Object> informacionProducto);
    
    public abstract void hacerVisible(boolean visible);
}
