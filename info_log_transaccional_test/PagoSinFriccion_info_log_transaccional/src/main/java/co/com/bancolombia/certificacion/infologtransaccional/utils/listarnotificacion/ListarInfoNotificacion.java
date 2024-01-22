package co.com.bancolombia.certificacion.infologtransaccional.utils.listarnotificacion;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;

import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class ListarInfoNotificacion {



    private ListarInfoNotificacion() {

    }

    public static Boolean obtenerInfoServicio(List<Map<String, Object>> listaMap, String transferDate,
                                              String transferAmount, String clientInformation, String accountNumber,
                                              String documentType, String customerLoyaltyId,
                                              String destinationAccountNumber, String transferReference,
                                              String transferCodeResponse) {

        boolean respuesta = false;
        String lista = null;
        JsonObject listResp = null;

        for (int i = 0; i < listaMap.size(); i++) {
            lista = new Gson().toJson(listaMap.get(i).get(clientInformation));
            listResp = new Gson().fromJson(lista, JsonObject.class);
            respuesta = !listaMap.get(i).get(transferDate).toString().isEmpty() &&
                    !listaMap.get(i).get(transferAmount).toString().isEmpty() &&
                    !listResp.get(accountNumber).getAsString().isEmpty() &&
                    !listResp.get(documentType).getAsString().isEmpty() &&
                    listaMap.get(i).containsKey(customerLoyaltyId) &&
                    !listaMap.get(i).get(destinationAccountNumber).toString().isEmpty() &&
                    listaMap.get(i).containsKey(transferReference) &&
                    listaMap.get(i).containsKey(transferCodeResponse);
        }
        return respuesta;
    }

    public static List<Map<String, String>> obtenerListInfo(List<Map<String, Object>> listaMap, String qrInfo,
                                                            String idQR, String transferVoucher, String transferDate,
                                                            String clientInformation, String documentNumber, String documentType,
                                                            String accountNumber, String accountType,
                                                            String commerceInvoiceId, String responseNotification,
                                                            String code, String destinationAccountType, String destinationDocumentNumber,
                                                            String destinationDocumentType, String urlNotification, String destinationAccountNumber) {

        List<Map<String, String>> respuesta = new ArrayList<>();
        Map<String, String> map = null;
        String lista = null;
        JsonObject listResp = null;

        for (int i = 0; i < listaMap.size(); i++) {
            map = new HashMap<>();
            lista = new Gson().toJson(listaMap.get(i));
            listResp = new Gson().fromJson(lista, JsonObject.class);
            String respQr = listResp.getAsJsonObject(qrInfo).get(idQR).getAsString();
            String respComprobante = listResp.get(transferVoucher).getAsString();
            String respClientNumber = listResp.getAsJsonObject(clientInformation).get(documentNumber).getAsString();
            String respClientType = listResp.getAsJsonObject(clientInformation).get(documentType).getAsString();
            String respClientAccountNumber = listResp.getAsJsonObject(clientInformation).get(accountNumber).getAsString();
            String respClientAccountType = listResp.getAsJsonObject(clientInformation).get(accountType).getAsString();
            String respNotif = listResp.getAsJsonObject(responseNotification).get(code).getAsString();
            String respFecha = listResp.get(transferDate).getAsString();
            String destinationANumber = listResp.get(destinationAccountNumber).getAsString();
            String destinationAType = listResp.get(destinationAccountType).getAsString();
            String destinationDNumber = listResp.get(destinationDocumentNumber).getAsString();
            String destinationDType = listResp.get(destinationDocumentType).getAsString();
            String urlNoti = listResp.getAsJsonObject(responseNotification).get(urlNotification).getAsString();
            map.put("idQr", respQr);
            map.put("comprobante", respComprobante);
            map.put("numDocCliente", respClientNumber);
            map.put("TypeDocCliente", respClientType);
            map.put("NumberAccountCliente", respClientAccountNumber);
            map.put("TypeAccountCliente", respClientAccountType);
            map.put("fecha", respFecha);
            map.put("codigoNotificacion", respNotif);
            map.put("destinationAccountNumber", destinationANumber);
            map.put("destinationAccountType", destinationAType);
            map.put("destinationDocumentNumber", destinationDNumber);
            map.put("destinationDocumentType", destinationDType);
            map.put("responseNotification", urlNoti.replace("[","").replace("]",""));
            if (listResp.get(commerceInvoiceId) != null) {
                map.put("idVentaComercio", listResp.get(commerceInvoiceId).getAsString());
            }
            respuesta.add(map);
        }
        return respuesta;
    }

    public static List<Map<String, String>> obtenerListInfoBd(List<Map<String, Object>> listaMap, String trackingNumber,
                                                              String date, String qrId, String infoTrx, String origin,
                                                              String document,String product, String type,
                                                              String documentNumber, String documentType,String number,
                                                              String qrDetail, String purchaseIntention,
                                                              String commerceInvoiceId, String responseCode,
                                                              String destinationDocumentType, String destinationDocumentNumber,
                                                              String destinationAccountType, String destinationAccountNumber,
                                                              String notificationUrl) {
        List<Map<String, String>> respuesta1 = new ArrayList<>();
        Map<String, String> map1 = null;

        for (int i = 0; i < listaMap.size(); i++) {

            try {
                map1 = new HashMap<>();
                JsonObject listResp = new Gson().fromJson(listaMap.get(i).get(infoTrx).toString(), JsonObject.class);
                String documentoNumber = listResp.getAsJsonObject(origin).getAsJsonObject(document).get(documentNumber).getAsString();
                String documentoType = listResp.getAsJsonObject(origin).getAsJsonObject(document).get(documentType).getAsString();
                String accountNumber = listResp.getAsJsonObject(origin).getAsJsonObject(product).get(number).getAsString();
                String accountType = listResp.getAsJsonObject(origin).getAsJsonObject(product).get(type).getAsString();
                String fecha = listResp.get(date).getAsString();
                String urlNotification = listResp.get(notificationUrl).getAsString();
                map1.put("idQr", listaMap.get(i).get(qrId).toString());
                map1.put("comprobante", listaMap.get(i).get(trackingNumber).toString());
                map1.put("numDocCliente", documentoNumber);
                map1.put("TypeDocCliente", documentoType);
                map1.put("NumberAccountCliente", accountNumber);
                map1.put("TypeAccountCliente", accountType);
                map1.put("fecha", fecha);
                map1.put("codigoNotificacion", listaMap.get(i).get(responseCode).toString());
                map1.put("destinationDocumentType", listaMap.get(i).get(destinationDocumentType).toString());
                map1.put("destinationDocumentNumber", listaMap.get(i).get(destinationDocumentNumber).toString());
                map1.put("destinationAccountType", listaMap.get(i).get(destinationAccountType).toString());
                map1.put("destinationAccountNumber", listaMap.get(i).get(destinationAccountNumber).toString());
                map1.put("responseNotification", urlNotification);
                if (listResp.getAsJsonObject(qrDetail).getAsJsonObject(purchaseIntention) != null &&
                        !listResp.getAsJsonObject(qrDetail).getAsJsonObject(purchaseIntention).get(commerceInvoiceId).isJsonNull()) {
                    map1.put("idVentaComercio", listResp.getAsJsonObject(qrDetail).getAsJsonObject(purchaseIntention).get(commerceInvoiceId).getAsString());
                }
                respuesta1.add(map1);
            } catch (FileSystemNotFoundException e) {
                log.error("context", e);
            }
        }
        return respuesta1;
    }
}
