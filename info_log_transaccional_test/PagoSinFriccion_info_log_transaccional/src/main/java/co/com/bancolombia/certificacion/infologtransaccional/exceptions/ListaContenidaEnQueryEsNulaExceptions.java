package co.com.bancolombia.certificacion.infologtransaccional.exceptions;

public class ListaContenidaEnQueryEsNulaExceptions extends AssertionError {
    private static final String QUERY_LISTA_NULA = "La lista que contiene el query es Nula o Vacia.";

    public ListaContenidaEnQueryEsNulaExceptions(String message, Throwable cause) {
        super(QUERY_LISTA_NULA, cause);
    }

    public ListaContenidaEnQueryEsNulaExceptions() {
        super(QUERY_LISTA_NULA);
    }
}
