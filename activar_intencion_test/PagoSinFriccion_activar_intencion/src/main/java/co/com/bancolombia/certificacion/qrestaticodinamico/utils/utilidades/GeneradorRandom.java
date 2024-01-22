package co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades;

import lombok.extern.log4j.Log4j2;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

@Log4j2
public class GeneradorRandom {


    private GeneradorRandom() {
    }

    public static String randomTrackingNumberGenerator() {
        try {
            String prefix = "PT-";
            String sufix = "suc-cali#18norte";
            return prefix + Math.abs(SecureRandom.getInstanceStrong().nextInt()) + sufix;
        } catch (NoSuchAlgorithmException e) {

            log.error("Error calculando el número random", e);
        }
        return null;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
