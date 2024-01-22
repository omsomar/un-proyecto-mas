package co.com.bancolombia.certificacion.infologtransaccional.exceptions;

public class RespuestaFallidaNoConocidaExceptions extends AssertionError {
    private static final String CODIGO_ERROR_NO_CONOCIDO = "El codigo de Error no conocido o no existe.";

    public RespuestaFallidaNoConocidaExceptions(String message, Throwable cause) {
        super(CODIGO_ERROR_NO_CONOCIDO, cause);
    }

    public RespuestaFallidaNoConocidaExceptions() {
        super(CODIGO_ERROR_NO_CONOCIDO);
    }
}
