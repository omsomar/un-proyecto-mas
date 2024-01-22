package co.com.bancolombia.certificacion.infologtransaccional.exceptions;

public class OpcionNoValidaExceptions extends AssertionError {
    private static final String OPCION_NO_VALIDA = "La Opcion Escogida no es valida, por favor escoja uan opcion valida.";

    public OpcionNoValidaExceptions(String message, Throwable cause) {
        super(OPCION_NO_VALIDA, cause);
    }

    public OpcionNoValidaExceptions() {
        super(OPCION_NO_VALIDA);
    }
}
