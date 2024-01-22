package co.com.bancolombia.certificacion.qrintencioncompra.models;


import java.util.List;

public class ResponseList {
    private final PaginationList pagination;
    private final List<DataList> data;

    public ResponseList(PaginationList paginationListObject, List<DataList> data) {
        this.pagination = paginationListObject;
        this.data = data;
    }

    public PaginationList getPagination() {
        return pagination;
    }

    public List<DataList> getData() {
        return data;
    }
}
