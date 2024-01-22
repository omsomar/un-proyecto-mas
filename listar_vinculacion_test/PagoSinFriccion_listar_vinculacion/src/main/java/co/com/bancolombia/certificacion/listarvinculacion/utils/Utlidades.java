package co.com.bancolombia.certificacion.listarvinculacion.utils;



import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utlidades {

    public static List<String> obtenerFecha(String fechaEx){

        List<String> fecha = new ArrayList<>();
        String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        fecha.add(fechaActual);
        if (!fechaEx.isEmpty()) {
            String randomDate = randomDate(fechaActual, fechaEx);
            fecha.add(randomDate);
        }
        return fecha;
    }

    private static String randomDate(String beginDate, String endDate) {
        Date fechaDeInicio;
        Date fechaFinal;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            fechaDeInicio = format.parse (beginDate);
            fechaFinal = format.parse (endDate);
            if (fechaDeInicio.getTime() > fechaFinal.getTime()) {
                return beginDate;
            }
            long date = random(fechaDeInicio.getTime(), fechaFinal.getTime());
            return format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    public static int generarNumeroAl(){
        Integer numero = (int) (Math. random()*9+1);
        return numero;
    }

    public static String generadorId(){
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 10; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }
}
