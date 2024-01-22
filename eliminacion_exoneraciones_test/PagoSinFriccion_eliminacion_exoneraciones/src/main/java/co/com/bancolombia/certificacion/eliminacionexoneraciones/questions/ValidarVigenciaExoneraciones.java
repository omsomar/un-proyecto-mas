package co.com.bancolombia.certificacion.eliminacionexoneraciones.questions;

import co.com.bancolombia.certificacion.eliminacionexoneraciones.integrations.ConsultarVinculacion;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.constantes.Constantes;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.utilidades.GeneradorArchivosJSON;
import co.com.bancolombia.conexion.basedatos.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class ValidarVigenciaExoneraciones implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {

        List<Boolean> listValidarExonVigente = new ArrayList<>();
        boolean validarExonVigente = false;

        for (Map<String, Map<String, Object>> iterator: getVinculaciones("vinculacionesVigentes.json")) {

            List<Map<String,Object>> infoVinculacion = Consulta.ejecutar(ConsultarVinculacion.consultarVinculacionActualizada(String.join(",",iterator.keySet())),
                    ConnectionManager.getPostgreSqlConnection());

            /* vinculación solo tiene exoneracion básica vigente*/
            if( iterator.get(String.join("",iterator.keySet())).containsKey(Constantes.EXONERATION) && !iterator.get(String.join("",iterator.keySet())).containsKey(Constantes.EXONERATION_COMMISSION)){
                validarExonVigente =    Constantes.TRUE.equalsIgnoreCase(infoVinculacion.get(0).get(Constantes.EXONERATION).toString());
            }
            /* vinculación solo tiene exoneracion personalizada vigente*/
            else if( !iterator.get(String.join("",iterator.keySet())).containsKey(Constantes.EXONERATION) && iterator.get(String.join("",iterator.keySet())).containsKey(Constantes.EXONERATION_COMMISSION)){

                validarExonVigente =    Constantes.TRUE.equalsIgnoreCase(infoVinculacion.get(0).get("exoneration_commission").toString());
            }
            /* vinculación tiene ambos tipos de exoneración (básica y personalizada) vigentes*/
            else if( iterator.get(String.join("",iterator.keySet())).containsKey(Constantes.EXONERATION) && iterator.get(String.join("",iterator.keySet())).containsKey(Constantes.EXONERATION_COMMISSION)){
                validarExonVigente =    Constantes.TRUE.equalsIgnoreCase(infoVinculacion.get(0).get(Constantes.EXONERATION).toString()) &&
                                        Constantes.TRUE.equalsIgnoreCase(infoVinculacion.get(0).get("exoneration_commission").toString());
            }
            listValidarExonVigente.add(validarExonVigente);
        }

        return !listValidarExonVigente.contains(false);
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
    public static ValidarVigenciaExoneraciones de(){
        return new ValidarVigenciaExoneraciones();
    }
}
