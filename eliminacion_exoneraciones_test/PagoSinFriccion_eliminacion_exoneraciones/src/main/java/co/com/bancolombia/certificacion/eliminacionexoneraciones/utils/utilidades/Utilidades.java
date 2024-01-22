package co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilidades {

    private Utilidades(){

    }

    /***
     * Este método permite obtener la fecha actual en formato "yyyy-MM-dd"
     * @return  fecha actual
     */
    public static final String generarFecha() {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar cal = Calendar.getInstance();
        final Date preToPreMonthDate = cal.getTime();
        return format.format(preToPreMonthDate);
    }

    /***
     * Este método permite cambiar el formato para la fecha ingresada
     * @param fecha Corresponde a la fecha a la cual se requiere cambiar el formato
     * @return fecha con formato cambiado
     * @throws ParseException
     */
    public static Date cambiarFormato(String fecha) throws ParseException {
        DateFormat formatoFecha = new SimpleDateFormat("yyyyy-MM-dd");
        return formatoFecha.parse(fecha);
    }
}
