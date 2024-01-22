package co.com.bancolombia.certificacion.vinculacion.utils.planavanzado;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManejarFecha {

    private ManejarFecha(){

    }
    /***
     * Este método permite cambiar el formato para la fecha ingresada
     * @param fecha Corresponde a la fecha a la cual se requiere cambiar el formato
     * @return fecha con formato cambiado
     * @throws ParseException
     */
    public static Date cambiarFormatoAFecha(String fecha) throws ParseException {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.parse(fecha);
    }

    public static String cambiarFechaAString(Date fecha) {
        SimpleDateFormat fechaNueva = new SimpleDateFormat("yyyy-MM-dd");
        return fechaNueva.format(fecha);
    }
}
