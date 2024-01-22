package co.com.bancolombia.certificacion.qrintencioncompra.questions.listarintencion;

import co.com.bancolombia.certificacion.qrintencioncompra.models.DataList;
import co.com.bancolombia.certificacion.qrintencioncompra.utils.Convertir;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListadoFue implements Question<Boolean> {
    private List<DataList> hope = new ArrayList<>();

    ListadoFue(List<DataList> data) {
        for (DataList row : data) {
            DataList interHope = new DataList(row.getQrId(), row.getAmount(), row.getIntentionStatus(), row.getCommerceInvoiceId(), row.getCustomerLoyaltyId(), row.getCreatedAt());
            this.hope.add(interHope);
        }
    }

    @Override
    public Boolean answeredBy(Actor actor
    ) {
        List<DataList> pathData = Convertir.respuestaServicioListGson().getData();
        if (!pathData.isEmpty()) {
            if (hope.size() != pathData.size()) {
                return false;
            }
            Collections.sort(hope);
            Collections.sort(pathData);
            for (int i = 0; i < hope.size(); i++) {
                if (!(hope.get(i).getQrId().equals(pathData.get(i).getQrId())) ||
                        !(hope.get(i).getAmount().equals(pathData.get(i).getAmount())) ||
                        !(hope.get(i).getCustomerLoyaltyId().equals(pathData.get(i).getCustomerLoyaltyId())) ||
                        !(hope.get(i).getCreatedAt().equals(pathData.get(i).getCreatedAt())) ||
                        !(hope.get(i).getIntentionStatus().equals(pathData.get(i).getIntentionStatus())) ||
                        !(hope.get(i).getCommerceInvoiceId().equals(pathData.get(i).getCommerceInvoiceId()))
                ) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static ListadoFue exitosa(List<DataList> data) {
        return new ListadoFue(data);
    }

}
