
package com.mycompany.megazarl.administrador.mongodb.manejadorconexiones;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;


public class ManejadorConexiones {
   
    private static final String BASE_DATOS = "SistemaVentas";
   
    public static MongoDatabase obtenerBaseDatos(){
        //Configuraciones del mapeador automático de mongo
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
       
        // Asignar la configuración del mapeador con la conexión, para que nuestras clases POJO sean reconocidas en automático
        MongoClientSettings configuraciones = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();
        
        //Conexión a mongoDB; mongodb://localhost:27017
        MongoClient cliente = MongoClients.create(configuraciones);
        //Acceso a la base de datos
        MongoDatabase baseDatos = cliente.getDatabase(BASE_DATOS);
        return baseDatos;
    }
           
}
