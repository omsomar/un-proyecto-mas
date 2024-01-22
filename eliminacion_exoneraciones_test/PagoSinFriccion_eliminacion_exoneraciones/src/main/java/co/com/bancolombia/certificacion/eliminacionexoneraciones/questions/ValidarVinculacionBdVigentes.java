package co.com.bancolombia.certificacion.eliminacionexoneraciones.questions;

import co.com.bancolombia.certificacion.eliminacionexoneraciones.integrations.ConsultarVinculacion;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.constantes.Constantes;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.utilidades.GeneradorArchivosJSON;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.utilidades.Utilidades;
import co.com.bancolombia.conexion.basedatos.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.io.IOException;
import java.util.*;

/***
 * Esta clase permite evaluar cuales son las vinculaciones que tienen exoneración vigente
 */
@Log4j2
public class ValidarVinculacionBdVigentes implements Question<Boolean> {

    @SneakyThrows
    @Override
    public Boolean answeredBy(Actor actor) {
        /* se obtiene las vinculaciones tienen exoneración activa pero q aún no están vencidas, comparadas con la fecha actual*/
        List<Map<String, Object>>  respuestaBdPostgres = Consulta.ejecutar(ConsultarVinculacion.consultarVinculacionVigente(Utilidades.generarFecha()),
                ConnectionManager.getPostgreSqlConnection());

        List<Map<String,Map<String, Object>>> listVigentes = new ArrayList<>();
        Map<String,Map<String,Object>> infoMapVigente;
        Map<String,Object> mapVigente;

        List<Map<String,Map<String,Object>>> listVencidas = new ArrayList<>();
        Map<String,Map<String,Object>> infoMapVencida;
        Map<String,Object> mapVencida;

        Date fechaActual = Utilidades.cambiarFormato(Utilidades.generarFecha());
        /* se hace recorrido por lista de vinculaciones vigentes*/
        for (Map<String,Object> iterator:respuestaBdPostgres) {

            infoMapVigente = new HashMap<>();
            mapVigente = new HashMap<>();
            infoMapVencida = new HashMap<>();
            mapVencida = new HashMap<>();

            if(iterator.get(Constantes.EXONERATION)!= null &&
                iterator.get("exoneration_end_date") != null){

                Date fechaFinExoneracion = Utilidades.cambiarFormato(iterator.get("exoneration_end_date").toString());
                if("true".equalsIgnoreCase(iterator.get(Constantes.EXONERATION).toString()) &&
                        (fechaFinExoneracion.compareTo(fechaActual) >= 0)){
                    mapVigente.put(Constantes.EXONERATION, iterator.get(Constantes.EXONERATION));
                }
                if("true".equalsIgnoreCase(iterator.get(Constantes.EXONERATION).toString()) &&
                        (fechaFinExoneracion.compareTo(fechaActual) < 0)){
                    mapVencida.put(Constantes.EXONERATION, iterator.get(Constantes.EXONERATION));
                }
            }

            if(iterator.get(Constantes.EXONERATION_COMMISSION_BD) != null &&
                iterator.get("exoneration_commission_end_date") != null){
                Date fechaFinExoneracionPers = Utilidades.cambiarFormato(iterator.get("exoneration_commission_end_date").toString());
                if("true".equalsIgnoreCase(iterator.get(Constantes.EXONERATION_COMMISSION_BD).toString()) &&
                        (fechaFinExoneracionPers.compareTo(fechaActual) >= 0)) {
                    mapVigente.put("exonerationCommission", iterator.get(Constantes.EXONERATION_COMMISSION_BD));
                }
                if("true".equalsIgnoreCase(iterator.get(Constantes.EXONERATION_COMMISSION_BD).toString()) &&
                        (fechaFinExoneracionPers.compareTo(fechaActual) < 0)){
                    mapVencida.put("exonerationCommission", iterator.get(Constantes.EXONERATION_COMMISSION_BD));
                }
            }

            if(!mapVigente.isEmpty()){
                infoMapVigente.put(iterator.get("id_vinculation").toString(), mapVigente);
                listVigentes.add(infoMapVigente);
            }
            if(!mapVencida.isEmpty()){
                infoMapVencida.put(iterator.get("id_vinculation").toString(), mapVencida);
                listVencidas.add(infoMapVencida);
            }
        }
        try {

            GeneradorArchivosJSON.convertirMapaAArchivo(listVigentes, "vinculacionesVigentes.json");
            GeneradorArchivosJSON.convertirMapaAArchivo(listVencidas, "vinculacionesVencidas.json");

        } catch (IOException e) {
            log.error("Error escribiendo el archivo Json", e);
        }

        return !listVigentes.isEmpty();

    }

    public static ValidarVinculacionBdVigentes con(){
        return new ValidarVinculacionBdVigentes();
    }


}
