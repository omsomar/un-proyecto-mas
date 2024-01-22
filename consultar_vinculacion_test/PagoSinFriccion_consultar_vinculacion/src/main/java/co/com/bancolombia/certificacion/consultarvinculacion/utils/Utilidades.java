package co.com.bancolombia.certificacion.consultarvinculacion.utils;

import lombok.extern.log4j.Log4j2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Log4j2
public class Utilidades {


    private Utilidades() {

    }

    public static final String generarFecha() {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar cal = Calendar.getInstance();
        final Date preToPreMonthDate = cal.getTime();
        return format.format(preToPreMonthDate);
    }

    public static final String generarFechaAnnioAtras() {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.YEAR, -1);
        cal.add(Calendar.DATE, -1);

        final Date preToPreMonthDate = cal.getTime();
        return format.format(preToPreMonthDate);
    }

    public static final String cambiarFormatoFecha(String fecha) {
        String formatoAnterior = "dd/MM/yyyy";
        String formatoNuevo = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(formatoAnterior);
        Date preToPreMonthDate = null;
        try {
            preToPreMonthDate = format.parse(fecha);
            format.applyPattern(formatoNuevo);
        } catch (ParseException e) {
            log.error(e);
        }
        return format.format(preToPreMonthDate);
    }

    public static String cambiarFormato(String fecha) {
        final String oldFormat26Caracteres = "yyyy-MM-dd HH:mm:ss.nnnnnn";
        final String oldFormat25Caracteres = "yyyy-MM-dd HH:mm:ss.nnnnn";
        final String newFormat26Caracteres = "yyyy-MM-dd'T'HH:mm:ss.nnnnnn";
        final String newFormat25Caracteres = "yyyy-MM-dd'T'HH:mm:ss.nnnnn";
        DateTimeFormatter formatter;
        DateTimeFormatter outFormatter;
        LocalDateTime ldt;
        if(fecha.length()>25){
            formatter = DateTimeFormatter.ofPattern(
                    oldFormat26Caracteres);
            ldt = LocalDateTime.parse(fecha, formatter);
            outFormatter = DateTimeFormatter.ofPattern(
                    newFormat26Caracteres);
        }else{
            formatter = DateTimeFormatter.ofPattern(
                    oldFormat25Caracteres);
            ldt = LocalDateTime.parse(fecha, formatter);
            outFormatter = DateTimeFormatter.ofPattern(
                   newFormat25Caracteres);
        }
        return outFormatter.format(ldt);
    }
}
