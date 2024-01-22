package co.com.bancolombia.certificacion.qrestaticodinamico.exceptions;

public class ErrorExceptions extends AssertionError {
    public static final String ERROR_EXCEL = "Error al leer el archivo de excel:";

    public ErrorExceptions(String message) {
        super(message);
    }
}
