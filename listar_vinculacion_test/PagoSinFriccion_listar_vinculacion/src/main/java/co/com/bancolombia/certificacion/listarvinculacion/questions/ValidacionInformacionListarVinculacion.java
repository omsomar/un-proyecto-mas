package co.com.bancolombia.certificacion.listarvinculacion.questions;

import co.com.bancolombia.certificacion.listarvinculacion.utils.Convertir;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ValidacionInformacionListarVinculacion implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        String [] llavesConsulta = {"idVinculation","creatorVisitDate","creatorAdviserId","channelName","commerceName",
                "commerceActivityCode","commerceActivityName","commerceActivityDetail","commerceDocumentType",
                "commerceDocumentNumber","legalReprDocumentType","legalReprName","legalReprDocumentNumber","reasonReject",
                "technicalSupportName","technicalSupportEmail","technicalSupportPhoneNumber","apiName","commissionType",
                "exonerationStartDate","exonerationEndDate","urlNotification","informationValidation","exoneration",
                "dailyTransactionalReport","accountTransactionalReport","directNotification","multicash",
                "consolidatedDailyAccount","commissionValueFixed","commissionValuePercentage"};

        String [] valorAComparar = {"3dc0b009-28ea-4a47-bcde-d93c68620c56","2021-02-04","1221","PIG","VinTecCompu2",
                "24","Tecnologia y Telecomunicaciones","Venta de productos de Tecnologia","CC","987456415641","CC",
                "Andres","987456415","NO HAY RECHAZO","Santiago Gonzalez","santiago@example.com","3014876358","API-NAME",
                "FIJA","2021-02-04","2022-02-03","http://www.api.com","true","true","true","true","true","true","true",
                "100.0","1.0"};

        Boolean statusFlag = true;

        List<Map<String, String>> respuestaData = SerenityRest.lastResponse().getBody().jsonPath().getList("data");
        for(int i = 0; i< llavesConsulta.length; i++){
            if(!(String.valueOf(respuestaData.get(0).get(llavesConsulta[i])).equals(valorAComparar[i]))){
                statusFlag = false;
                break;
            }
        }

        String [] llavesConsultaVector = {"id_account", "id_vinculation","account_type","" +
                "[account_number","id_control_list","control_adviser_email","control_creator_date","documentType",
                "documentNumber","[controlCategory","id_contact","contact_name","contact_email","contact_phone_number",
                "id_partner","partner_name","partner_document_type","partner_document_number","accept","version","[dateTime"};

        String [] valorACompararVector = {"d7fca47c-f7ed-4988-9a39-3a59438ad17d", "3dc0b009-28ea-4a47-bcde-d93c68620c56",
                "D","123","7e3ccab6-80fa-4610-aaf5-8a12ae9871c9","adviser@mail.com","2021-02-04T14:45:49.566034",
                "CC","1085325215","25","4251c287-5701-4591-8d6b-3bc893ec6618","Juan Perez","juanpereza@example.com","73465952",
                "6ee431cd-610a-41e4-b902-57376506364d","CRISTINA ANTONIO JARAMILLO CASTILLO","CC","000001989647189","true","V1",
                "2018-07-16T14:30:00" };

        String vectoresData = SerenityRest.lastResponse().getBody().jsonPath().getList("data").toString();
        Map<String, String> pathData;
        pathData = Convertir.respuestaServicio(vectoresData);

        for(int i = 0; i< llavesConsultaVector.length; i++){
            if(!(pathData.get(llavesConsultaVector[i]).contains(valorACompararVector[i]))){
                statusFlag = false;
                break;
            }
        }

        return (statusFlag);

    }

    public static ValidacionInformacionListarVinculacion validacion() {return new ValidacionInformacionListarVinculacion(); }
}
