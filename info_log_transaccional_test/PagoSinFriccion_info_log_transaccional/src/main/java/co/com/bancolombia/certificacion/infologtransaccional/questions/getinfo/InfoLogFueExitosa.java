package co.com.bancolombia.certificacion.infologtransaccional.questions.getinfo;

import co.com.bancolombia.certificacion.infologtransaccional.exceptions.OpcionNoValidaExceptions;
import co.com.bancolombia.certificacion.infologtransaccional.models.getinfo.DataFeature;
import co.com.bancolombia.certificacion.infologtransaccional.utils.Convertir;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.*;

public class InfoLogFueExitosa extends DataFeature implements Question<Boolean> {
    String respServicio;

    public InfoLogFueExitosa(DataFeature dataFeature) {
        this.escenario = dataFeature.getEscenario();
        this.peticion = dataFeature.getPeticion();
    }

    public static InfoLogFueExitosa consultaFueExitosa(DataFeature dataFeature) {
        return new InfoLogFueExitosa(dataFeature);
    }

    public static boolean equalLists(List<String> one, List<String> two) {
        if (one == null && two == null) {
            return true;
        }
        if ((one == null && two != null) || one != null && two == null || one.size() != two.size()) {
            return false;
        }
        one = new ArrayList<>(one);
        two = new ArrayList<>(two);
        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }

    public static boolean isListContainValue(List<String> arraylist) {
        for (String str : arraylist) {
            if (str.equals("")) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean validatePag;
        Map<String, String> pathPag;


        String pagination = SerenityRest.lastResponse().getBody().jsonPath().get("pagination").toString();
        pathPag = Convertir.respuestaServicioList(pagination).get(0);
        validatePag = (pathPag.containsKey("totalPages") && !"".equals(pathPag.get("totalPages")))
                && (pathPag.containsKey("totalElements") && !"".equals(pathPag.get("totalElements")));

        if ("getAllInfo".equals(peticion)) {
            respServicio = SerenityRest.lastResponse().getBody().jsonPath().getList("data").get(0).toString();
            pathPag = Convertir.respuestaServicioList(respServicio).get(0);

            return pathPag.containsKey("status") && !"".equals(pathPag.get("status"));

        } else if ("getInfo".equals(peticion)) {
            return validateRespGetInfo() && validatePag;

        } else throw new OpcionNoValidaExceptions();
    }


    private boolean validateRespGetInfo() {
        Map<String, String> pathData;

        respServicio = SerenityRest.lastResponse().getBody().jsonPath().getList("data").get(0).toString();
        pathData = Convertir.respuestaServicioList(respServicio).get(0);
        List<String> keyRequerido = Arrays.asList("transactionDateTime", "destinationDocumentType", "transactionStatus"
                , "paymentReference", "originAccountNumber", "description", "qrId", "destinationAccountNumber"
                , "transactionDate", "reference", "transactionType", "destinationAccountType", "idLogTrxQr"
                , "originDocumentNumber", "destinationDocumentNumber", "qrType", "[description", "id"
                , "transactionValue", "value", "trackingNumber", "originName", "qrPointOfSale", "qrCashRegister"
                , "qrCashRegisterSerial", "qrSeller", "vinculation");

        if (!"camposObligatorios".equals(escenario)) {
            keyRequerido = new ArrayList<>(keyRequerido);
            if ("camposObligatoriosOpcionales".equals(escenario)) {
                keyRequerido.add("channelQr");
                keyRequerido.add("transactionChannel");
                keyRequerido.remove("qrType");
                keyRequerido.remove("vinculation");
            } else if ("todosLosCampos".equals(escenario)) {
                keyRequerido.add("transactionCommission");
                keyRequerido.add("transactionIVA");
            } else throw new OpcionNoValidaExceptions();
        }

        List<String> keyResponse = new ArrayList<>(pathData.keySet());
        List<String> valueResponse = new ArrayList<>(pathData.values());
        return keyResponse.containsAll(keyRequerido) && isListContainValue(valueResponse);
    }
}
