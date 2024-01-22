package co.com.bancolombia.certificacion.qrintencioncompra.utils;

import co.com.bancolombia.certificacion.qrintencioncompra.models.ResponseList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.serenitybdd.rest.SerenityRest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes.FORMATO_FECHA;

public class Convertir {
    private Convertir() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Map<String, String>> respuestaServicioListArreglar(String response) {
        String[] elements = response.split("},");
        List<Map<String, String>> responseElements = new ArrayList<>();
        for (String element : elements) {
            response = response.replace("{", "").replace("}", "");
            String[] responseSplit = response.split(",");
            Map<String, String> respuestaPost = new HashMap<>();
            for (String key : responseSplit) {
                if (key.contains("=")) {
                    String[] elementSplit = key.split("=");
                    switch (elementSplit.length) {
                        case 3:
                            respuestaPost.put(elementSplit[1].trim(), elementSplit[2].trim());
                            break;
                        case 4:
                            respuestaPost.put(elementSplit[2].trim(), elementSplit[3].trim());
                            break;
                        case 2:
                            respuestaPost.put(elementSplit[0].trim(), elementSplit[1].trim());
                            break;
                        default:
                            respuestaPost.put(elementSplit[0].trim(), "");
                            break;
                    }
                }
            }
            responseElements.add(respuestaPost);
        }
        return responseElements;
    }

    public static ResponseList respuestaServicioListGson() {
        Gson gson = new GsonBuilder().setDateFormat(FORMATO_FECHA).create();
        return gson.fromJson(SerenityRest.lastResponse().asString(), ResponseList.class);

    }

    public static Map<String, String> serviceResponse(String response) {
        response = response.replace("{", "").replace("}", "");
        String[] responseSplit = response.split(",");

        Map<String, String> postResponse = new HashMap<>();
        for (String element : responseSplit) {
            String[] elementSplit = element.split("=");
            postResponse.put(elementSplit[0].trim(), elementSplit[1].trim());
        }
        return postResponse;
    }

}
