package co.com.bancolombia.certificacion.eliminacionexoneraciones.questions;

import co.com.bancolombia.certificacion.eliminacionexoneraciones.integrations.ConsultarVinculacion;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.models.CamposEliminarExoneracion;
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
 * Esta clase permite evaluar cuales son las vinculaciones que tienen exoneración vencida
 */
@Log4j2
public class ValidarVinculacionBd implements Question<Boolean> {

    private String fecha;
    private String escenario;

    public ValidarVinculacionBd(CamposEliminarExoneracion filtro){
        this.fecha = filtro.getFecha();
        this.escenario = filtro.getEscenario();
    }

    @SneakyThrows
    @Override
    public Boolean answeredBy(Actor actor) {

        List<Map<String, Object>> respuestaBdPostgres;

        /*se crean variables tipo date para posteriormente cambiar su formato y validar si la vinculación obtenida en el query tiene una exoneración vencida
        * fechaActual: corresponde a la fecha actual, cuando en el archivo feature no se envia una fecha
        * fechaFiltro: corresponde a la fecha enviada como filtro en la feature*/
        Date fechaActual= new Date();
        Date fechaFiltro = new Date();

        /*  se valida si la fecha enviada para el caso de prueba está vacia o es "" para poder hacer consulta a bd
        *   En este caso se hace una consulta a la bd para conocer las vinculaciones con exoneración vencida,
        *   filtrando dicha consulta a bd por la fecha actual   */
        if(this.fecha.isEmpty() || "".equalsIgnoreCase(this.fecha)){

            respuestaBdPostgres = Consulta.ejecutar(ConsultarVinculacion.consultarVinculacionVencidas(Utilidades.generarFecha()),
                    ConnectionManager.getPostgreSqlConnection());

            /*se debe covertir la fecha actual para que sea de tipo date*/
            fechaActual = Utilidades.cambiarFormato(Utilidades.generarFecha());
        }
        /*  Se valida si para el caso de prueba si se envia una fecha de filtro
        *   En este caso se hace una consulta a la bd para conocer las vinculaciones con exoneraciones vencidas,
        *   filtrando dicha consulta a bd por la fecha enviada en la feature    */
        else{
            respuestaBdPostgres = Consulta.ejecutar(ConsultarVinculacion.consultarVinculacionVencidas(this.fecha),
                    ConnectionManager.getPostgreSqlConnection());

            /* se debe convertir la fecha de filtro para sea de tipo date*/
            fechaFiltro = Utilidades.cambiarFormato(this.fecha);
        }

        List<Map<String, Map<String, Object>>> vinculaciones = new ArrayList<>();
        Map<String, Map<String, Object>> infoVinculVencidas;
        Map<String, Object> exoneracionMap;

        /*  Se recorre las vinculaciones obtenidas en la consulta a la bd,
        *   Es decir, las vinculaciones que tienen exoneraciones vencidas   */
        for (Map<String,Object> iterator:respuestaBdPostgres) {
            infoVinculVencidas = new HashMap<>();
            exoneracionMap = new HashMap<>();

            if(iterator.get(Constantes.EXONERATION) != null &&
                iterator.get("exoneration_end_date") != null){
                /* se debe convertir la fecha de fin de la exoneración a tipo Date*/
                Date fechaFinExonerBasica = Utilidades.cambiarFormato(iterator.get("exoneration_end_date").toString());

                /* Cuando el escenario de prueba recibe una fecha de filtro para eliminar las exoneraciones:
                * Se valida si la vinculación tiene exoneración básica activa y su fecha de vencimiento ya se cumplió
                * En caso de que la vinculación ya se encuentre vencida se guarda el valor en un mapa, para posteriormente
                * guardarlo en un archivo json */
                if("conFecha".equalsIgnoreCase(this.escenario) &&
                        "true".equalsIgnoreCase(iterator.get(Constantes.EXONERATION).toString()) &&
                        (fechaFinExonerBasica.compareTo(fechaFiltro) < 0)){
                            addMapExoneration(exoneracionMap, Constantes.EXONERATION, iterator.get(Constantes.EXONERATION));
                }
                /* Cuando el escenario de prueba NO recibe una fecha de filtro para eliminar las exoneraciones:
                 * Se valida si la vinculación tiene exoneración básica activa y su fecha de vencimiento con respecto a la fecha actual ya se cumplió
                 * En caso de que la vinculación ya se encuentre vencida se guarda el valor en un mapa, para posteriormente
                 * guardarlo en un archivo json */
                else if("sinFecha".equalsIgnoreCase(this.escenario) &&
                        "true".equalsIgnoreCase(iterator.get(Constantes.EXONERATION).toString()) &&
                        (fechaFinExonerBasica.compareTo(fechaActual) < 0))   {
                            addMapExoneration(exoneracionMap, Constantes.EXONERATION, iterator.get(Constantes.EXONERATION));
                }
            }
            if(iterator.get(Constantes.EXONERATION_COMMISSION_BD) != null &&
                iterator.get("exoneration_commission_end_date") != null) {
                /* se debe convertir la fecha de fin de la exoneración a tipo Date*/
                Date fechaFinExonerPersonalizada = Utilidades.cambiarFormato(iterator.get("exoneration_commission_end_date").toString());

                /* Cuando el escenario de prueba recibe una fecha de filtro para eliminar las exoneraciones:
                 * Se valida si la vinculación tiene exoneración personalizada activa y su fecha de vencimiento ya se cumplió
                 * En caso de que la vinculación ya se encuentre vencida se guarda el valor en un mapa, para posteriormente
                 * guardarlo en un archivo json */
                if("conFecha".equalsIgnoreCase(this.escenario) &&
                        "true".equalsIgnoreCase(iterator.get(Constantes.EXONERATION_COMMISSION_BD).toString()) &&
                        (fechaFinExonerPersonalizada.compareTo(fechaFiltro) < 0)){
                    addMapExoneration(exoneracionMap, Constantes.EXONERATION_COMMISSION, iterator.get(Constantes.EXONERATION_COMMISSION_BD));
                }
                /* Cuando el escenario de prueba NO recibe una fecha de filtro para eliminar las exoneraciones:
                 * Se valida si la vinculación tiene exoneración personalizada activa y su fecha de vencimiento con respecto a la fecha actual ya se cumplió
                 * En caso de que la vinculación ya se encuentre vencida se guarda el valor en un mapa, para posteriormente
                 * guardarlo en un archivo json */
                else if("sinFecha".equalsIgnoreCase(this.escenario) &&
                        "true".equalsIgnoreCase(iterator.get(Constantes.EXONERATION_COMMISSION_BD).toString()) &&
                        (fechaFinExonerPersonalizada.compareTo(fechaActual) < 0)){
                    addMapExoneration(exoneracionMap, Constantes.EXONERATION_COMMISSION, iterator.get(Constantes.EXONERATION_COMMISSION_BD));
                }
            }
            infoVinculVencidas.put(iterator.get("id_vinculation").toString(), exoneracionMap);
            vinculaciones.add(infoVinculVencidas);
        }

        try {
            /*   Se guarda un archivo json con las vinculaciones que tienen exoneraciones vencidas
            *   Se asocia a cada vinculación cual fue su tipo de vinculación vencida,
            *   teniendo en cuenta que una vinculación puede tener vencida solo un tipo de exoneración, o ambos tipos de exoneración */
            GeneradorArchivosJSON.convertirMapaAArchivo(vinculaciones, "vinculacionesAntes.json");
        } catch (IOException e) {
            log.error("Error escribiendo el archivo Json", e);
        }

        return !respuestaBdPostgres.isEmpty();
    }

    public void addMapExoneration(Map<String,Object> map, String llaveMap, Object valorMap){
        map.put(llaveMap,valorMap);
    }

    public static ValidarVinculacionBd con(CamposEliminarExoneracion filtro){
        return new ValidarVinculacionBd(filtro);
    }


}
