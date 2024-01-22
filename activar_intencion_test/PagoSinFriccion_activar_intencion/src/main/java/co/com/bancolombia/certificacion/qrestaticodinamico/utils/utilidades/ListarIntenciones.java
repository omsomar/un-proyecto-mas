package co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListarIntenciones {
    private ListarIntenciones() {
    }

    public static List<String> getListByCondition(List<Map<String, Object>> listaParam, String valorParam) {
        List<String> resultado = new ArrayList<>();
        for (Map<String, Object> iterator : listaParam)
            resultado.add(iterator.get(valorParam).toString());
        return resultado;
    }
}
