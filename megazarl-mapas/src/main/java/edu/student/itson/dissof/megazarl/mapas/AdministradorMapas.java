
package edu.student.itson.dissof.megazarl.mapas;

import edu.student.itson.dissof.megazarl.dto.infraestructura.DatosTiempoTrasladoUbicacionesDTO;
import edu.student.itson.dissof.megazarl.dto.infraestructura.TiempoTrasladoDTO;


public class AdministradorMapas implements IAdministradorMapas{

    @Override
    public TiempoTrasladoDTO calcularTiempoTraslado(DatosTiempoTrasladoUbicacionesDTO datosTiempoTrasladoUbicacionesDTO) {
        
        String numeroA = datosTiempoTrasladoUbicacionesDTO.getNumeroA();
        String calleA = datosTiempoTrasladoUbicacionesDTO.getCalleA();
        String coloniaA = datosTiempoTrasladoUbicacionesDTO.getColoniaA();
        String codigoPostalA = datosTiempoTrasladoUbicacionesDTO.getCodigoPostalA();

        String numeroB = datosTiempoTrasladoUbicacionesDTO.getNumeroB();
        String calleB = datosTiempoTrasladoUbicacionesDTO.getCalleB();
        String coloniaB = datosTiempoTrasladoUbicacionesDTO.getColoniaB();
        String codigoPostalB = datosTiempoTrasladoUbicacionesDTO.getCodigoPostalB();
        
        
        if(
            // Dirección de matriz simulada
            numeroB.equals("1000")
            && calleB.equals("José Alberto Healy Noriega")
            && coloniaB.equals("Parque Industrial Hermosillo Norte")
            && codigoPostalB.equals("83118")){
            
            if(
                numeroA.equals("1000")
                && calleA.equals("José Alberto Healy Noriega")
                && coloniaA.equals("Parque Industrial Hermosillo Norte")
                && codigoPostalA.equals("83118")){
                
                return new TiempoTrasladoDTO(0F);
                
                
            }
            
            // Direcciones de cliente simuladas
            if(
                numeroA.equals("1586")
                && calleA.equals("Guerrero")
                && coloniaA.equals("Ciudad Obregón Centro")
                && codigoPostalA.equals("85000")){
                
                return new TiempoTrasladoDTO(3F);
                
                
            }
            
            
            // Direcciones de sucursales simuladas
            if(
                numeroA.equals("123")
                && calleA.equals("Río Mocorito")
                && coloniaA.equals("San Rafael")
                && codigoPostalA.equals("83557")){
                
                return new TiempoTrasladoDTO(5F);
                
                
            }
            
            if(
                numeroA.equals("5695")
                && calleA.equals("44")
                && coloniaA.equals("Bicentenario")
                && codigoPostalA.equals("84269")){
                
                return new TiempoTrasladoDTO(5F);
                
                
            }
            
            if(
                numeroA.equals("200")
                && calleA.equals("Guerrero")
                && coloniaA.equals("Ciudad Obregón Centro")
                && codigoPostalA.equals("85000")){
                
                return new TiempoTrasladoDTO(3F);
                
                
            }
            
            // Direcciones de paquetería simuladas:
            
            // Dirección por defecto
            if(
                numeroA.equals("8455")
                && calleA.equals("Génova")
                && coloniaA.equals("Montecarlo")
                && codigoPostalA.equals("85136")){
                
                return new TiempoTrasladoDTO(3F);
                
            }
            
            if(
                numeroA.equals("1247")
                && calleA.equals("Las Ceibas")
                && coloniaA.equals("Fuentes del Mezquital")
                && codigoPostalA.equals("83240")){
                
                return new TiempoTrasladoDTO(0.5F);
                
                
            }
            
            if(
                numeroA.equals("257")
                && calleA.equals("Cristobal Colón")
                && coloniaA.equals("Los Viñedos")
                && codigoPostalA.equals("83120")){
                
                return new TiempoTrasladoDTO(0.5F);
                
                
            }
            
            if(
                numeroA.equals("659")
                && calleA.equals("Parroquia")
                && coloniaA.equals("Villa Satélite")
                && codigoPostalA.equals("83200")){
                
                return new TiempoTrasladoDTO(0.4F);
                
                
            }
            
            if(
                numeroA.equals("708")
                && calleA.equals("De los Panaderos")
                && coloniaA.equals("La Verbena")
                && codigoPostalA.equals("83288")){
                
                return new TiempoTrasladoDTO(0.6F);
                
                
            }
            
            if(
                numeroA.equals("1646")
                && calleA.equals("Real de San Pablo")
                && coloniaA.equals("La Candelaria")
                && codigoPostalA.equals("83280")){
                
                return new TiempoTrasladoDTO(0.7F);
                
                
            }
            
            // Direcciones de proveedores simuladas:
            if(
                numeroA.equals("3000")
                && calleA.equals("Jiquilpan")
                && coloniaA.equals("Zona Industrial")
                && codigoPostalA.equals("81255")){
                
                return new TiempoTrasladoDTO(5.4F);
                
                
            }
            
            if(
                numeroA.equals("168")
                && calleA.equals("De Las Misiones Norte")
                && coloniaA.equals("Venustiano Carranza")
                && codigoPostalA.equals("21394")){
                
                return new TiempoTrasladoDTO(7.5F);
                
                
            }
            
            if(
                numeroA.equals("450")
                && calleA.equals("Carretera Federal 15D")
                && coloniaA.equals("Parque Industrial El Trébol")
                && codigoPostalA.equals("80393")){
                
                return new TiempoTrasladoDTO(8F);
                
                
            }
            
            if(
                numeroA.equals("1485")
                && calleA.equals("Paseos de Aura")
                && coloniaA.equals("Parque Industrial Chihuahua Sur")
                && codigoPostalA.equals("31385")){
                
                return new TiempoTrasladoDTO(11F);
                
                
            }
            
            if(
                numeroA.equals("102")
                && calleA.equals("Blvd. Campestre")
                && coloniaA.equals("Lomas del Campestre")
                && codigoPostalA.equals("37150")){
                
                return new TiempoTrasladoDTO(18F);
                
            }
            
        }
        
        // Direcciones de proveedores
        if(
            numeroB.equals("3000")
            && calleB.equals("Jiquilpan")
            && coloniaB.equals("Zona Industrial")
            && codigoPostalB.equals("81255")){

            if(
                numeroA.equals("8455")
                && calleA.equals("Génova")
                && coloniaA.equals("Montecarlo")
                && codigoPostalA.equals("85136")){

                return new TiempoTrasladoDTO(4.3F);

            }
            
            if(
                numeroA.equals("1247")
                && calleA.equals("Las Ceibas")
                && coloniaA.equals("Fuentes del Mezquital")
                && codigoPostalA.equals("83240")){
                
                return new TiempoTrasladoDTO(5.5F);
                
                
            }
            
            if(
                numeroA.equals("257")
                && calleA.equals("Cristobal Colón")
                && coloniaA.equals("Los Viñedos")
                && codigoPostalA.equals("83120")){
                
                return new TiempoTrasladoDTO(5.5F);
                
                
            }
            
            if(
                numeroA.equals("659")
                && calleA.equals("Parroquia")
                && coloniaA.equals("Villa Satélite")
                && codigoPostalA.equals("83200")){
                
                return new TiempoTrasladoDTO(5.4F);
                
                
            }
            
            if(
                numeroA.equals("708")
                && calleA.equals("De los Panaderos")
                && coloniaA.equals("La Verbena")
                && codigoPostalA.equals("83288")){
                
                return new TiempoTrasladoDTO(5.6F);
                
                
            }
            
            if(
                numeroA.equals("1646")
                && calleA.equals("Real de San Pablo")
                && coloniaA.equals("La Candelaria")
                && codigoPostalA.equals("83280")){
                
                return new TiempoTrasladoDTO(5.7F);
                
                
            }
                
                
        }

        
        if(
            numeroB.equals("168")
            && calleB.equals("De Las Misiones Norte")
            && coloniaB.equals("Venustiano Carranza")
            && codigoPostalB.equals("21394")){

            if(
                numeroA.equals("8455")
                && calleA.equals("Génova")
                && coloniaA.equals("Montecarlo")
                && codigoPostalA.equals("85136")){

                return new TiempoTrasladoDTO(10F);

            }
            
            if(
                numeroA.equals("1247")
                && calleA.equals("Las Ceibas")
                && coloniaA.equals("Fuentes del Mezquital")
                && codigoPostalA.equals("83240")){
                
                return new TiempoTrasladoDTO(7.5F);
                
                
            }
            
            if(
                numeroA.equals("257")
                && calleA.equals("Cristobal Colón")
                && coloniaA.equals("Los Viñedos")
                && codigoPostalA.equals("83120")){
                
                return new TiempoTrasladoDTO(7.5F);
                
                
            }
            
            if(
                numeroA.equals("659")
                && calleA.equals("Parroquia")
                && coloniaA.equals("Villa Satélite")
                && codigoPostalA.equals("83200")){
                
                return new TiempoTrasladoDTO(7.4F);
                
                
            }
            
            if(
                numeroA.equals("708")
                && calleA.equals("De los Panaderos")
                && coloniaA.equals("La Verbena")
                && codigoPostalA.equals("83288")){
                
                return new TiempoTrasladoDTO(7.6F);
                
                
            }
            
            if(
                numeroA.equals("1646")
                && calleA.equals("Real de San Pablo")
                && coloniaA.equals("La Candelaria")
                && codigoPostalA.equals("83280")){
                
                return new TiempoTrasladoDTO(7.7F);
                
                
            }


        }

        if(
            numeroB.equals("450")
            && calleB.equals("Carretera Federal 15D")
            && coloniaB.equals("Parque Industrial El Trébol")
            && codigoPostalB.equals("80393")){

            if(
                numeroA.equals("8455")
                && calleA.equals("Génova")
                && coloniaA.equals("Montecarlo")
                && codigoPostalA.equals("85136")){

                return new TiempoTrasladoDTO(5.4F);

            }
            
            if(
                numeroA.equals("1247")
                && calleA.equals("Las Ceibas")
                && coloniaA.equals("Fuentes del Mezquital")
                && codigoPostalA.equals("83240")){
                
                return new TiempoTrasladoDTO(8F);
                
                
            }
            
            if(
                numeroA.equals("257")
                && calleA.equals("Cristobal Colón")
                && coloniaA.equals("Los Viñedos")
                && codigoPostalA.equals("83120")){
                
                return new TiempoTrasladoDTO(8F);
                
                
            }
            
            if(
                numeroA.equals("659")
                && calleA.equals("Parroquia")
                && coloniaA.equals("Villa Satélite")
                && codigoPostalA.equals("83200")){
                
                return new TiempoTrasladoDTO(7.9F);
                
                
            }
            
            if(
                numeroA.equals("708")
                && calleA.equals("De los Panaderos")
                && coloniaA.equals("La Verbena")
                && codigoPostalA.equals("83288")){
                
                return new TiempoTrasladoDTO(8.1F);
                
                
            }
            
            if(
                numeroA.equals("1646")
                && calleA.equals("Real de San Pablo")
                && coloniaA.equals("La Candelaria")
                && codigoPostalA.equals("83280")){
                
                return new TiempoTrasladoDTO(8.2F);
                
                
            }


        }

        if(
            numeroB.equals("1485")
            && calleB.equals("Paseos de Aura")
            && coloniaB.equals("Parque Industrial Chihuahua Sur")
            && codigoPostalB.equals("31385")){

            if(
                numeroA.equals("8455")
                && calleA.equals("Génova")
                && coloniaA.equals("Montecarlo")
                && codigoPostalA.equals("85136")){

                return new TiempoTrasladoDTO(10F);

            }
            
            if(
                numeroA.equals("1247")
                && calleA.equals("Las Ceibas")
                && coloniaA.equals("Fuentes del Mezquital")
                && codigoPostalA.equals("83240")){
                
                return new TiempoTrasladoDTO(11F);
                
                
            }
            
            if(
                numeroA.equals("257")
                && calleA.equals("Cristobal Colón")
                && coloniaA.equals("Los Viñedos")
                && codigoPostalA.equals("83120")){
                
                return new TiempoTrasladoDTO(11F);
                
                
            }
            
            if(
                numeroA.equals("659")
                && calleA.equals("Parroquia")
                && coloniaA.equals("Villa Satélite")
                && codigoPostalA.equals("83200")){
                
                return new TiempoTrasladoDTO(10.9F);
                
                
            }
            
            if(
                numeroA.equals("708")
                && calleA.equals("De los Panaderos")
                && coloniaA.equals("La Verbena")
                && codigoPostalA.equals("83288")){
                
                return new TiempoTrasladoDTO(11.1F);
                
                
            }
            
            if(
                numeroA.equals("1646")
                && calleA.equals("Real de San Pablo")
                && coloniaA.equals("La Candelaria")
                && codigoPostalA.equals("83280")){
                
                return new TiempoTrasladoDTO(11.2F);
                
                
            }


        }

        if(
            numeroB.equals("102")
            && calleB.equals("Blvd. Campestre")
            && coloniaB.equals("Lomas del Campestre")
            && codigoPostalB.equals("37150")){

            if(
                numeroA.equals("8455")
                && calleA.equals("Génova")
                && coloniaA.equals("Montecarlo")
                && codigoPostalA.equals("85136")){

                return new TiempoTrasladoDTO(15F);

            }
            
            if(
                numeroA.equals("1247")
                && calleA.equals("Las Ceibas")
                && coloniaA.equals("Fuentes del Mezquital")
                && codigoPostalA.equals("83240")){
                
                return new TiempoTrasladoDTO(18F);
                
                
            }
            
            if(
                numeroA.equals("257")
                && calleA.equals("Cristobal Colón")
                && coloniaA.equals("Los Viñedos")
                && codigoPostalA.equals("83120")){
                
                return new TiempoTrasladoDTO(18F);
                
                
            }
            
            if(
                numeroA.equals("659")
                && calleA.equals("Parroquia")
                && coloniaA.equals("Villa Satélite")
                && codigoPostalA.equals("83200")){
                
                return new TiempoTrasladoDTO(17.9F);
                
                
            }
            
            if(
                numeroA.equals("708")
                && calleA.equals("De los Panaderos")
                && coloniaA.equals("La Verbena")
                && codigoPostalA.equals("83288")){
                
                return new TiempoTrasladoDTO(18.1F);
                
                
            }
            
            if(
                numeroA.equals("1646")
                && calleA.equals("Real de San Pablo")
                && coloniaA.equals("La Candelaria")
                && codigoPostalA.equals("83280")){
                
                return new TiempoTrasladoDTO(18.2F);
                
                
            }

        }
        
        return null;
        
    }
    
}
