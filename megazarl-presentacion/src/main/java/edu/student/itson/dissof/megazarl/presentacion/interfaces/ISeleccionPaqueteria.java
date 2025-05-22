package edu.student.itson.dissof.megazarl.presentacion.interfaces;

import java.util.HashMap;

public interface ISeleccionPaqueteria {

    public abstract void setPaqueterias(HashMap<Object, String> datosPaqueterias);
    
    public abstract void setEnvioGratis(boolean envioGratis);
    
    public abstract void setNumeroEnvio(String numeroEnvio);
    
    public abstract void setColoniaEnvio(String coloniaEnvio);
    
    public abstract void setCodigoPostalEnvio(String codigoPostalEnvio);
       
    public abstract void setCalleEnvio(String coloniaEnvio);

}
