package co.com.bancolombia.certificacion.infologtransaccional.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Convertir {
    private Convertir() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Map<String, String>> respuestaServicioList(String response) {
        String[] elements = response.split("},");
        List<Map<String, String>> responseElements = new ArrayList<>();
        for (String element : elements) {
            response = response.replace("{", "").replace("}", "");
            String[] responseSplit = response.split(",");
            Map<String, String> respuestaPost = new HashMap<>();
            for (String key : responseSplit) {
                if (key.contains("=")) {
                    String[] elementSplit = key.split("=");
                    if (elementSplit.length == 1) {
                        respuestaPost.put(elementSplit[0].trim(), "vacio");
                    } else if (elementSplit.length == 3) {
                        respuestaPost.put(elementSplit[1].trim(), elementSplit[1 + 1].trim());
                    } else if (elementSplit.length == 4) {
                        respuestaPost.put(elementSplit[1 + 1].trim(), elementSplit[3].trim());
                    } else {
                        if (elementSplit[1] == null) {
                            elementSplit[1] = "null";
                        }
                        respuestaPost.put(elementSplit[0].trim(), elementSplit[1].trim());
                    }
                }
            }
            responseElements.add(respuestaPost);
        }
        return responseElements;
    }

    public static Map<String, String> serviceResponse(String response) {
        response = response.replace("{", "").replace("}", "");
        String[] responseSplit = response.split(",");

        Map<String, String> postResponse = new HashMap<>();
        for (String element : responseSplit) {
            String[] elementSplit = element.split(":");
            postResponse.put(elementSplit[0].trim(), elementSplit[1].trim());
        }
        return postResponse;
    }

    public static Map<String, Object> serviceResponseMap(String response) {
        response = response.replace("{", "").replace("}", "");
        String[] responseSplit = response.split(",");

        Map<String, Object> postResponse = new HashMap<>();
        for (String element : responseSplit) {
            String[] elementSplit = element.split(":");
            postResponse.put(elementSplit[0].trim(), elementSplit[1].trim());
        }
        return postResponse;
    }
}
