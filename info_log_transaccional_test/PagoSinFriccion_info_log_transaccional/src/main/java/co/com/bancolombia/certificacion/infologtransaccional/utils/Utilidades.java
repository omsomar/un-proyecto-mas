package co.com.bancolombia.certificacion.infologtransaccional.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilidades {

    private Utilidades() {

    }

    public static final String generarFecha() {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar cal = Calendar.getInstance();
        final Date preToPreMonthDate = cal.getTime();
        return format.format(preToPreMonthDate);
    }

    public static final String generarFechaAnnioAtras() {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.YEAR, -1);
        cal.add(Calendar.DATE, -1);

        final Date preToPreMonthDate = cal.getTime();
        return format.format(preToPreMonthDate);
    }
}
