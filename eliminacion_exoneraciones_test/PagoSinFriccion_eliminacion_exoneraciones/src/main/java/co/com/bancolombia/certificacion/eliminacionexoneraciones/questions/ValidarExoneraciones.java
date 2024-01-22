package co.com.bancolombia.certificacion.eliminacionexoneraciones.questions;

import co.com.bancolombia.certificacion.eliminacionexoneraciones.integrations.ConsultarVinculacion;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.constantes.Constantes;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.utilidades.GeneradorArchivosJSON;
import co.com.bancolombia.conexion.basedatos.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ValidarExoneraciones implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {

        boolean validarEstadoVincul = false;

        /*   Se obtiene la lista de vinculaciones que fueron actualizadas por el servicio de eliminar exoneraciones */
        List<Map<String,String>> listaVinculaciones = SerenityRest.lastResponse().getBody().jsonPath().getList("$");

          List<Boolean> listEstadoVincul = new ArrayList<>();

          /* Se recorren las vinculaciones actualizadas por el servicio */
        for (Map<String,String> iterator:listaVinculaciones) {

            /* Se consulta cada una de las vinculaciones actualizadas por el servicio,
            *  para obtener información sobre su actualización de campos en la base de datos */
            List<Map<String,Object>> vinculacionVencida = Consulta.ejecutar(ConsultarVinculacion.consultarVinculacionActualizada(iterator.get(Constantes.ID_VINCULATION)),
                    ConnectionManager.getPostgreSqlConnection());

            /*  Se valida si la vinculación tenia una exoneración básica vencida
            *   En caso de haberla tenido, se verifica que dicha exoneración haya sido inactivada */
            if(hasBasicExoneration(iterator.get(Constantes.ID_VINCULATION))){
                validarEstadoVincul = Constantes.FALSE.equalsIgnoreCase(vinculacionVencida.get(0).get(Constantes.EXONERATION).toString());
            }
            /*  Se valida si la vinculación tenia una exoneración personalizada vencida
             *   En caso de haberla tenido, se verifica que dicha exoneración haya sido inactivada */
            else if(hasPersonalizedExoneration(iterator.get(Constantes.ID_VINCULATION))){
                validarEstadoVincul = Constantes.FALSE.equalsIgnoreCase(vinculacionVencida.get(0).get("exoneration_commission").toString());
            }
            /*  Se valida si la vinculación tenia los dos tipos de exoneración vencidas (básica y personalizada)
             *   En caso de haberlas tenido, se verifica que dicha exoneraciones hayan sido inactivadas */
            else if(hasBothExoneration(iterator.get(Constantes.ID_VINCULATION))){
                validarEstadoVincul = Constantes.FALSE.equalsIgnoreCase(vinculacionVencida.get(0).get(Constantes.EXONERATION).toString()) &&
                        Constantes.FALSE.equalsIgnoreCase(vinculacionVencida.get(0).get("exoneration_commission").toString());
            }
            listEstadoVincul.add(validarEstadoVincul);
        }
        /*  Se valida que la totalidad de las vinculaciones que actualizó el servicio correspondan
        *   con la totalidad de las vinculaciones que se encontraron que tenian exoneraciones vencidas y que fueron
        *   almacenadas en el archivo json  */
        boolean respVinculaciones = listaVinculaciones.containsAll(getAllIdVinculations());

        /*  Se elimina el archivo json utilizado*/
        GeneradorArchivosJSON.deleteJsonFile("vinculacionesAntes.json");
        return  respVinculaciones && !listEstadoVincul.contains(false);

    }

    /***
     * Este método permite obtener la información almacenada en el archivo json
     * @return Lista de mapas con la información de las vinculaciones con exoneraciones vencidas
     */
    public static List<Map<String, Map<String,Object>>> getVinculaciones() {

        List<Map<String, Map<String,Object>>> listVinculaciones = new ArrayList<>();
        try {
            listVinculaciones = GeneradorArchivosJSON.readFileToListOfMapWithMap("vinculacionesAntes.json");
        } catch (IOException e) {
            log.error("error leyendo el archivo json", e);
        }
        return listVinculaciones;
    }

    /***
     * Este método permite obtener los id de las vinculaciones que tiene exoneraciones vencidas
     * @return lista de mapas con los id de vinculaciones
     */
    public static List<Map<String,Object>> getAllIdVinculations(){
        List<Map<String,Object>> listResultado= new ArrayList<>();
        Map<String,Object> mapResult;
        for (int i=0; i < getVinculaciones().size();i++){
            mapResult = new HashMap<>();
            mapResult.put(Constantes.ID_VINCULATION, String.join(" ", getVinculaciones().get(i).keySet()));
            listResultado.add(mapResult);
        }
        return listResultado;
    }

    /***
     * Este método evalua si la vinculación tiene ambos tipos de exoneración vencidas
     * @param idVinculacion : corresponde a la vinculación para la cual se quiere determinar si tenia exoneraciones vencidas
     * @return true, en caso de encontrar que la vinculación enviada por parámetro si tenia ambas exoneraciones vencidas
     */
    public boolean hasBothExoneration( String idVinculacion){

        for (Map<String, Map<String,Object>> iterator:getVinculaciones()) {
            if(String.join(" ", iterator.keySet()).equalsIgnoreCase(idVinculacion)) {
                return iterator.get(idVinculacion).containsKey(Constantes.EXONERATION) &&
                        iterator.get(idVinculacion).containsKey(Constantes.EXONERATION_COMMISSION);
            }
        }
        return false;
    }

    /***
     * Este método evalua si la vinculación tiene exoneración básica vencida
     * @param idVinculacion : corresponde a la vinculación para la cual se quiere determinar si tenia exoneración básica vencida
     * @return true, en caso de encontrar que la vinculación enviada por parámetro si tenia la exoneración básica vencida
     */
    public boolean hasBasicExoneration( String idVinculacion){

        for (Map<String, Map<String,Object>> iterator:getVinculaciones()) {
            if(String.join(" ", iterator.keySet()).equalsIgnoreCase(idVinculacion)){
                return iterator.get(idVinculacion).containsKey(Constantes.EXONERATION) &&
                        !iterator.get(idVinculacion).containsKey(Constantes.EXONERATION_COMMISSION);
            }
        }
        return false;
    }

    /***
     * Este método evalua si la vinculación tiene exoneración personalizada vencida
     * @param idVinculacion : corresponde a la vinculación para la cual se quiere determinar si tenia exoneración personalizada vencida
     * @return true, en caso de encontrar que la vinculación enviada por parámetro si tenia la exoneración personalizada vencida
     */
    public boolean hasPersonalizedExoneration( String idVinculacion){
        for (Map<String, Map<String,Object>> iterator:getVinculaciones()) {
            if(String.join(" ", iterator.keySet()).equalsIgnoreCase(idVinculacion)) {
                return !iterator.get(idVinculacion).containsKey(Constantes.EXONERATION) &&
                        iterator.get(idVinculacion).containsKey(Constantes.EXONERATION_COMMISSION);
            }
        }
        return false;
    }

    public static ValidarExoneraciones de(){
        return new ValidarExoneraciones();
    }
}
