package co.com.bancolombia.certificacion.qrestaticodinamico.utils.body;

import co.com.bancolombia.certificacion.qrestaticodinamico.models.postintencion.*;

public class BodyResquestPost {

    private BodyResquestPost() {
    }

    public static IntentionDetail intentionDetail(ActivarIntencionModel activarIntencionModel) {
        IntentionDetail intentionDetail = new IntentionDetail();
        intentionDetail.setQrId(activarIntencionModel.getQrId());
        intentionDetail.setReference(activarIntencionModel.getReference());
        intentionDetail.setAmount(activarIntencionModel.getAmount());
        return intentionDetail;
    }

    public static Invoice invoice(ActivarIntencionModel activarIntencionModel) {
        Invoice invoice = new Invoice();
        invoice.setCommerceInvoiceId(activarIntencionModel.getCommerceInvoiceId());
        return invoice;
    }

    public static Customer customer(ActivarIntencionModel activarIntencionModel) {
        Customer customer = new Customer();
        customer.setCustomerLoyaltyId(activarIntencionModel.getCustomerLoyaltyId());
        return customer;
    }

    public static Data data(ActivarIntencionModel activarIntencionModel) {
        Data data = new Data();
        data.setIntentionDetail(intentionDetail(activarIntencionModel));
        data.setInvoice(invoice(activarIntencionModel));
        data.setCustomer(customer(activarIntencionModel));
        return data;
    }

    public static DataRequest dataRequest(ActivarIntencionModel activarIntencionModel) {
        DataRequest dataRequest = new DataRequest();
        dataRequest.setData(data(activarIntencionModel));
        return dataRequest;
    }

    public static Data dataCamposObligatorios(ActivarIntencionModel activarIntencionModel) {

        Data data = new Data();
        data.setIntentionDetail(intentionDetail(activarIntencionModel));
        return data;
    }

    public static DataRequest dataRequestCamposObligatorios(ActivarIntencionModel activarIntencionModel) {
        DataRequest dataRequest = new DataRequest();
        dataRequest.setData(dataCamposObligatorios(activarIntencionModel));
        return dataRequest;
    }

    public static DataRequest todosLosCampos(String valorIntencion) {
        ActivarIntencionModel activarIntencionModel = new ActivarIntencionModel();
        activarIntencionModel.todoLosCampos(valorIntencion);
        return dataRequest(activarIntencionModel);
    }

    public static DataRequest camposObligatorios(String valorIntencion) {
        ActivarIntencionModel activarIntencionModel = new ActivarIntencionModel();
        activarIntencionModel.camposObligatorios(valorIntencion);
        return dataRequestCamposObligatorios(activarIntencionModel);
    }

    public static DataRequest camposIncompletos(String campo) {
        ActivarIntencionModel activarIntencionModel = new ActivarIntencionModel();
        activarIntencionModel.camposIncompletos(campo);
        return dataRequestCamposObligatorios(activarIntencionModel);
    }

    public static DataRequest camposIncorrectos(String campoIncorrecto) {
        ActivarIntencionModel activarIntencionModel = new ActivarIntencionModel();
        activarIntencionModel.camposIncorrectos(campoIncorrecto);
        return dataRequest(activarIntencionModel);
    }

    public static DataRequest camposVacio() {
        ActivarIntencionModel activarIntencionModel = new ActivarIntencionModel();
        activarIntencionModel.camposVacios();
        return dataRequest(activarIntencionModel);
    }

    public static DataRequest logitudMaxima(String campoIncorrecto) {
        ActivarIntencionModel activarIntencionModel = new ActivarIntencionModel();
        activarIntencionModel.longitudMaxima(campoIncorrecto);
        return dataRequest(activarIntencionModel);
    }

    public static DataRequest intencionesConsecutivas(String valorIntencion) {
        ActivarIntencionModel activarIntencionModel = new ActivarIntencionModel();
        activarIntencionModel.intencionesConsecutivas(valorIntencion);
        return dataRequestCamposObligatorios(activarIntencionModel);
    }

    public static DataRequest todosLosCamposDecimales(String valorIntencion) {
        ActivarIntencionModel activarIntencionModel = new ActivarIntencionModel();
        activarIntencionModel.todosLosCamposDecimales(valorIntencion);
        return dataRequest(activarIntencionModel);
    }
}
