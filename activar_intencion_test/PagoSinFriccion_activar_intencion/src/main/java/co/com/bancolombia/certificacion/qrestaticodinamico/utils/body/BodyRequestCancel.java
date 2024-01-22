package co.com.bancolombia.certificacion.qrestaticodinamico.utils.body;

import co.com.bancolombia.certificacion.qrestaticodinamico.models.cancelintencion.CancelModel;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.cancelintencion.Data;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.cancelintencion.DataRequest;

public class BodyRequestCancel {

    private BodyRequestCancel() {

    }

    public static Data data(CancelModel cancelModel) {
        Data data = new Data();
        data.setQrIntentionId(cancelModel.getQrIntentionId());
        data.setReason(cancelModel.getReason());
        return data;
    }

    public static DataRequest dataRequest(CancelModel cancelModel) {
        DataRequest dataRequest = new DataRequest();
        dataRequest.setData(data(cancelModel));
        return dataRequest;
    }

    public static DataRequest todosLosCampos(String parametro) {
        CancelModel cancelModel = new CancelModel();
        cancelModel.todosLosCampos(parametro);
        return dataRequest(cancelModel);
    }

    public static DataRequest camposObligatorios() {
        CancelModel cancelModel = new CancelModel();
        cancelModel.camposObligatorios();
        return dataRequest(cancelModel);
    }

    public static DataRequest camposIncompletos() {
        CancelModel cancelModel = new CancelModel();
        cancelModel.camposIncompletos();
        return dataRequest(cancelModel);
    }

    public static DataRequest camposIncorrectos(String parametro) {
        CancelModel cancelModel = new CancelModel();
        cancelModel.camposIncorrectos(parametro);
        return dataRequest(cancelModel);
    }

    public static DataRequest inexistente() {
        CancelModel cancelModel = new CancelModel();
        cancelModel.inexistente();
        return dataRequest(cancelModel);
    }
}
