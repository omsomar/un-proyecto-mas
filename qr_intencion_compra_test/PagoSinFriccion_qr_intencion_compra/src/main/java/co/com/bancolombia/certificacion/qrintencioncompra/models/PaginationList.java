package co.com.bancolombia.certificacion.qrintencioncompra.models;

public class PaginationList {
    private final int totalPages;
    private final int totalElements;

    public PaginationList(int totalPages, int totalElements) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }
}
