
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
   
    private static final String BASE_DATOS = "sistema_megazarl_bd";
   
    public static MongoDatabase obtenerBaseDatos(){

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
       
        MongoClientSettings configuraciones = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .build();

        MongoClient cliente = MongoClients.create(configuraciones);

        MongoDatabase baseDatos = cliente.getDatabase(BASE_DATOS);
        
        return baseDatos;
    }
           
}
