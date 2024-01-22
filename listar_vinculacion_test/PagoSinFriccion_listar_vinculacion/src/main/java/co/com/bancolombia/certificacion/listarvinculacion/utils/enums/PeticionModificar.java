package co.com.bancolombia.certificacion.listarvinculacion.utils.enums;

import co.com.bancolombia.certificacion.listarvinculacion.models.Data;
import co.com.bancolombia.certificacion.listarvinculacion.utils.BodyRequest;

import java.util.HashMap;
import java.util.Map;

public enum PeticionModificar {

    TODOS("todosLosCampos",  BodyRequest::todosLosCampos);

    public static final Map<String, PeticionModificar> bodyRequestModificar;

    static {
        bodyRequestModificar =  new HashMap<>();
        for (PeticionModificar peticionModificar : PeticionModificar.values()){
            bodyRequestModificar.put(peticionModificar.body, peticionModificar);
        }
    }
    private final String body;
    private final ValidateModificar validateModificar;

    PeticionModificar(String body, ValidateModificar validateModificar){
        this.body = body;
        this.validateModificar = validateModificar;
    }
    public Data getValidateRequest(BodyRequest bodyRequest){
        return this.validateModificar.doValidation(bodyRequest);
    }
    public static PeticionModificar peticionBankInfo(String body){
        return bodyRequestModificar.get(body);
    }
}

interface ValidateModificar {
    Data doValidation(BodyRequest bodyRequest);
}