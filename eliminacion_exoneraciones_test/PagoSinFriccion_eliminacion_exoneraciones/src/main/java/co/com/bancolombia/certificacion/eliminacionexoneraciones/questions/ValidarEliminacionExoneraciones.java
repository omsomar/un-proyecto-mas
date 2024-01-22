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
import java.util.List;
import java.util.Map;

@Log4j2
public class ValidarEliminacionExoneraciones implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        boolean validarEliminarExoneracion=false;
        List<Boolean> listaResultado= new ArrayList<>();

        List<Map<String,Object>> listaServicio = SerenityRest.lastResponse().getBody().jsonPath().getList("$");

        for (Map<String,Map<String,Object>> iterator:getVinculaciones("vinculacionesVencidas.json")) {

            List<Map<String,Object>> vinculacionBd = Consulta.ejecutar(ConsultarVinculacion.consultarVinculacionActualizada(String.join(",",iterator.keySet())),
                    ConnectionManager.getPostgreSqlConnection());

            Map<String, Object> infoVinculacion= iterator.get(String.join("", iterator.keySet()));

            /* tiene exoneración básica */
           if(infoVinculacion.containsKey(Constantes.EXONERATION) && !infoVinculacion.containsKey(Constantes.EXONERATION_COMMISSION)){
               validarEliminarExoneracion =  isVinculation(listaServicio, String.join("",iterator.keySet())) &&
                       Constantes.FALSE.equalsIgnoreCase(vinculacionBd.get(0).get(Constantes.EXONERATION).toString());
           }
           /* tiene exoneración personalizada */
           else if(!infoVinculacion.containsKey(Constantes.EXONERATION) && infoVinculacion.containsKey(Constantes.EXONERATION_COMMISSION)){
               validarEliminarExoneracion = isVinculation(listaServicio, String.join("",iterator.keySet())) &&
                       Constantes.FALSE.equalsIgnoreCase(vinculacionBd.get(0).get("exoneration_commission").toString());

           }
           /* tiene exoneración básica y personalizada */
           else if(infoVinculacion.containsKey(Constantes.EXONERATION) && infoVinculacion.containsKey(Constantes.EXONERATION_COMMISSION)){
               validarEliminarExoneracion = isVinculation(listaServicio, String.join("",iterator.keySet())) &&
                       Constantes.FALSE.equalsIgnoreCase(vinculacionBd.get(0).get(Constantes.EXONERATION).toString()) &&
                       Constantes.FALSE.equalsIgnoreCase(vinculacionBd.get(0).get("exoneration_commission").toString());
           }

           listaResultado.add(validarEliminarExoneracion);
        }
        GeneradorArchivosJSON.deleteJsonFile("vinculacionesVigentes.json");
        GeneradorArchivosJSON.deleteJsonFile("vinculacionesVencidas.json");
        return !listaResultado.contains(false);
    }

    public boolean isVinculation(List<Map<String,Object>> lista, String elemento){
        for (Map<String,Object> iterator: lista) {
            if(iterator.containsValue(elemento)){
                return true;
            }
        } return false;
    }
    public static List<Map<String, Map<String,Object>>> getVinculaciones(String nombreArchivo) {

        List<Map<String, Map<String,Object>>> listVinculaciones = new ArrayList<>();
        try {
            listVinculaciones = GeneradorArchivosJSON.readFileToListOfMapWithMap(nombreArchivo);
        } catch (IOException e) {
            log.error("error leyendo el archivo json", e);
        }
        return listVinculaciones;
    }

    public static ValidarEliminacionExoneraciones de(){
        return new ValidarEliminacionExoneraciones();
    }
}
