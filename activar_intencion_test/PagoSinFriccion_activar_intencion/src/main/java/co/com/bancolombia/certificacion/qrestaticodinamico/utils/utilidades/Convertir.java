package co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades;

import java.util.HashMap;
import java.util.Map;

public class Convertir {
    private static final int INDEX = 3;

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
                } else if (elementSplit.length == INDEX) {
                    respuestaPost.put(elementSplit[1].trim(), elementSplit[INDEX - 1].trim());
                } else if (elementSplit.length == INDEX + 1) {
                    respuestaPost.put(elementSplit[INDEX - 1].trim(), elementSplit[INDEX].trim());
                } else {
                    respuestaPost.put(elementSplit[0].trim(), elementSplit[1].trim());
                }
            }
        }
        return respuestaPost;
    }
}
