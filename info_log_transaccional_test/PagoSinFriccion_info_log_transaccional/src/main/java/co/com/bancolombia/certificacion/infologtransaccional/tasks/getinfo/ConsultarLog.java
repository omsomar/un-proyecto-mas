package co.com.bancolombia.certificacion.infologtransaccional.tasks.getinfo;

import co.com.bancolombia.certificacion.infologtransaccional.exceptions.OpcionNoValidaExceptions;
import co.com.bancolombia.certificacion.infologtransaccional.interactions.Get;
import co.com.bancolombia.certificacion.infologtransaccional.models.getinfo.DataFeature;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

import static co.com.bancolombia.certificacion.infologtransaccional.utils.Constantes.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultarLog extends DataFeature implements Task {

    public ConsultarLog(DataFeature dataFeature) {
        this.escenario = dataFeature.getEscenario();
        this.servicio = dataFeature.getServicio();
        this.canal = dataFeature.getCanal();
        this.numRastreo = dataFeature.getNumRastreo();
        this.tipoCtaDestino = dataFeature.getTipoCtaDestino();
        this.numCtaDestino = dataFeature.getNumCtaDestino();
        this.tipoDocDestino = dataFeature.getTipoDocDestino();
        this.numDocDestino = dataFeature.getNumDocDestino();
        this.pointOfSale = dataFeature.getPointOfSale();
        this.cashRegister = dataFeature.getCashRegister();
        this.cashRegisterSerial = dataFeature.getCashRegisterSerial();
        this.seller = dataFeature.getSeller();
        this.estado = dataFeature.getEstado();
        this.numeroPagina = dataFeature.getNumeroPagina();
        this.tamanoPagina = dataFeature.getTamanoPagina();
        this.peticion = dataFeature.getPeticion();
    }

    public static ConsultarLog transaccional(DataFeature dataFeature) {
        return instrumented(ConsultarLog.class, dataFeature);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, String> params = new HashMap<>();

        switch (escenario) {
            case "camposObligatorios":
                params.put(PAGINATION_KEY, numeroPagina);
                params.put(PAGINATION_SIZE, tamanoPagina);
                break;
            case "camposObligatoriosOpcionales":
                params.put(PAGINATION_KEY, numeroPagina);
                params.put(PAGINATION_SIZE, tamanoPagina);
                if (GET_ALL_INFO.equals(peticion)) {
                    params.put("status", estado);
                } else {
                    params.put("trackingNumber", numRastreo);
                }
                break;
            case "camposFaltantes":
                params.put(PAGINATION_SIZE, tamanoPagina);
                break;
            default:
                params.put("channelQr", canal);
                params.put("trackingNumber", numRastreo);
                params.put("destinationAccountType", tipoCtaDestino);
                params.put("destinationAccountNumber", numCtaDestino);
                params.put("destinationDocumentType", tipoDocDestino);
                params.put("destinationDocumentNumber", numDocDestino);
                params.put("qrPointOfSale", pointOfSale);
                params.put("qrCashRegister", cashRegister);
                params.put("startDate","2020-12-08");
                if (!"".equals(cashRegisterSerial)) {
                    params.put("qrCashRegisterSerial", cashRegisterSerial);
                }
                params.put("qrSeller", seller);
                params.put(PAGINATION_KEY, numeroPagina);
                params.put(PAGINATION_SIZE, tamanoPagina);
                if (GET_ALL_INFO.equals(peticion)) {
                    params.put("status", estado);
                }
                break;
        }
        if ("getInfo".equals(peticion)) {
            servicio = GET_INFO_LOG_TRX;
        } else if (GET_ALL_INFO.equals(peticion)) {
            servicio = GET_ALL_INFO_LOG_TRX;
        } else throw new OpcionNoValidaExceptions();

        actor.attemptsTo(Get.resource(servicio)
                .with(requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                        .params(params)));
    }
}
