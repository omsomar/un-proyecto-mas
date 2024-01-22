package co.com.bancolombia.certificacion.listarvinculacion.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Convertir {

    private Convertir() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, String> respuestaServicio(String response) {
        response = response.replace("{", "").replace("}", "");
        String[] responseSplit = response.split(",");

        Map<String, String> respuestaPost = new HashMap<>();
        for (String element : responseSplit) {
            if (element.contains("=")) {
                String[] elementSplit = element.split("=");
                if (element.contains("=]")) {
                    respuestaPost.put(elementSplit[0].trim(), elementSplit[1].trim());
                } else if (elementSplit.length == 3) {
                    respuestaPost.put(elementSplit[1].trim(), elementSplit[2].trim());
                } else if (elementSplit.length == 4) {
                    respuestaPost.put(elementSplit[2].trim(), elementSplit[3].trim());
                } else
                    respuestaPost.put(elementSplit[0].trim(), elementSplit[1].trim());
            }
        }
        return respuestaPost;
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
                    if (elementSplit.length == 3) {
                        respuestaPost.put(elementSplit[1].trim(), elementSplit[2].trim());
                    } else if (elementSplit.length == 4) {
                        respuestaPost.put(elementSplit[2].trim(), elementSplit[3].trim());
                    } else
                        respuestaPost.put(elementSplit[0].trim(), elementSplit[1].trim());
                }
            }
            responseElements.add(respuestaPost);
        }
        return responseElements;
    }
}
