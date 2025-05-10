package edu.student.itson.dissof.megazarl.administrador.sql.administradorsql.manejadorconexiones;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 * ManejadorConexiones.java
 *
 * Clase que representa el manejador de conexiones para una base de datos SQL
 * Permite obtener un {@link EntityManager} dependiendo de si se está ejecutando
 * una prueba o si se está usando el sistema real, utilizando diferentes archivos de configuración.
 * 
 * Las configuraciones son leídas desde un archivo de propiedades ubicado en:
 * META-INF/properties.txt
 * 
 * Las unidades de persistencia esperadas deben estar definidas en persistence.xml
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
    
    /**
     * Obtiene una instancia de {@link EntityManager} dependiendo de si se encuentra en modo test
     * o en ejecución normal del sistema.
     *
     * @return una instancia de {@link EntityManager} configurada según el entorno.
     */
    public static EntityManager getEntityManager(){

        EntityManagerFactory emFactory;
      
        //Se cargan las propiedades desde el archivo de configuración
        Properties propiedades = new Properties();
            try (InputStream inputStream = ManejadorConexiones.class.getClassLoader().getResourceAsStream("META-INF/properties.txt")) {
                propiedades.load(inputStream);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            //Propiedades específicas para cada unidad de persistencia
            Properties sistema_restaurante_bd_PU_Prop = new Properties();
            Properties sistema_restaurante_bd_tests_PU_Prop = new Properties();

            // Se separan las propiedades según la unidad de persistencia
            for (String key : propiedades.stringPropertyNames()) {
                if (key.startsWith("edu.student.itson.dissof_megazarl-administrador-sql_jar_1.0-SNAPSHOTPU.")) {
                   
                    String keyPropiedad = key.substring("edu.student.itson.dissof_megazarl-administrador-sql_jar_1.0-SNAPSHOTPU.".length());
                    sistema_restaurante_bd_PU_Prop.put(keyPropiedad, propiedades.getProperty(key));
                    
                } else if (key.startsWith("edu.student.itson.dissof_megazarl-administrador-sql_jar_1.0-SNAPSHOTPU2.")) {
                    String propKey = key.substring("edu.student.itson.dissof_megazarl-administrador-sql_jar_1.0-SNAPSHOTPU2.".length());
                    sistema_restaurante_bd_tests_PU_Prop.put(propKey, propiedades.getProperty(key));
                }
            }
        
        //Se selecciona la unidad de persistencia correspondiente
        if(conexionTest){
            emFactory = Persistence.createEntityManagerFactory("edu.student.itson.dissof_megazarl-administrador-sql_jar_1.0-SNAPSHOTPU2.", 
                    sistema_restaurante_bd_tests_PU_Prop);
        } else{
            emFactory = Persistence.createEntityManagerFactory("edu.student.itson.dissof_megazarl-administrador-sql_jar_1.0-SNAPSHOTPU.", 
                    sistema_restaurante_bd_PU_Prop);
        }
        
        EntityManager entityManager = emFactory.createEntityManager();
        
        //Se crea y retorna el EntityManager
        return entityManager;
    }
}