
package edu.student.itson.dissof.megazarl.administrador.mysql.manejadorconexiones;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ManejadorConexiones {
    
    /**
     * Indica si la conexión actual es para pruebas
     * Si es {@code true}, se usará la configuración de pruebas.
     */
    private static boolean conexionTest = false;
    
     /**
     * Establece el modo de conexión como test o normal
     *
     * @param conexionTestActiva {@code true} si se desea activar la conexión de pruebas,
     *                            {@code false} para conexión normal.
     */
    public static void setConexionTest(boolean conexionTestActiva){
        conexionTest = conexionTestActiva;
    }
    
    public static EntityManager getEntityManager() {
        EntityManagerFactory emFactory;

        Properties propiedades = new Properties();
        try (InputStream inputStream = ManejadorConexiones.class.getClassLoader()
                .getResourceAsStream("META-INF/properties.txt")) {
            propiedades.load(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Properties propsPU = new Properties();
        Properties propsPUTest = new Properties();

        for (String key : propiedades.stringPropertyNames()) {
            if (key.startsWith("edu.student.itson.dissof_megazarl-administrador-mysql_jar_1.0-SNAPSHOTPU.")) {
                String cleanKey = key.substring("edu.student.itson.dissof_megazarl-administrador-mysql_jar_1.0-SNAPSHOTPU.".length());
                propsPU.put(cleanKey, propiedades.getProperty(key));
            } else if (key.startsWith("edu.student.itson.dissof_megazarl-administrador-mysql_jar_1.0-SNAPSHOTPU2.")) {
                String cleanKey = key.substring("edu.student.itson.dissof_megazarl-administrador-mysql_jar_1.0-SNAPSHOTPU2.".length());
                propsPUTest.put(cleanKey, propiedades.getProperty(key));
            }
        }

        if (conexionTest) {
            emFactory = Persistence.createEntityManagerFactory(
                "edu.student.itson.dissof_megazarl-administrador-mysql_jar_1.0-SNAPSHOTPU2",
                propsPUTest);
        } else {
            emFactory = Persistence.createEntityManagerFactory(
                "edu.student.itson.dissof_megazarl-administrador-mysql_jar_1.0-SNAPSHOTPU",
                propsPU);
        }

        return emFactory.createEntityManager();
    }
    
    
    
    /**
     * Obtiene una instancia de {@link EntityManager} dependiendo de si se encuentra en modo test
     * o en ejecución normal del sistema.
     *
     * @return una instancia de {@link EntityManager} configurada según el entorno.
     */
    public static EntityManager getEntityManager(){

        EntityManagerFactory emFactory;
        
        
        if(conexionTest){
            
            Map<String, String> propiedades = new HashMap<>();
            propiedades.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/sistema_megazarl_bd_tests?zeroDateTimeBehavior=CONVERT_TO_NULL");
            propiedades.put("javax.persistence.jdbc.user", "root");
            propiedades.put("javax.persistence.jdbc.password", "mroMSQL1147-");

            emFactory = Persistence.createEntityManagerFactory("edu.student.itson.dissof_megazarl-administrador-mysql_jar_1.0-SNAPSHOTPU", propiedades);
        } else{
            
            Map<String, String> propiedades = new HashMap<>();
            propiedades.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/sistema_megazarl_bd?zeroDateTimeBehavior=CONVERT_TO_NULL");
            propiedades.put("javax.persistence.jdbc.user", "root");
            propiedades.put("javax.persistence.jdbc.password", "mroMSQL1147-");
            
            emFactory = Persistence.createEntityManagerFactory("edu.student.itson.dissof_megazarl-administrador-mysql_jar_1.0-SNAPSHOTPU", propiedades);
        }
        
        
        EntityManager entityManager = emFactory.createEntityManager();
        
        //Se crea y retorna el EntityManager
        return entityManager;
    }
}