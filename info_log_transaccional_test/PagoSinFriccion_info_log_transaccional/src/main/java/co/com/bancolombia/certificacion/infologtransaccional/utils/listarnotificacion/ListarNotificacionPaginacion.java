package co.com.bancolombia.certificacion.infologtransaccional.utils.listarnotificacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarNotificacionPaginacion {

    private ListarNotificacionPaginacion() {

    }

    public static List<Map<String, String>> obtenerInfoPagina(List<Map<String, Object>> resultado,
                                                              String transferVoucher,
                                                              String transferDate,
                                                              String transferAmount) {
        List<Map<String, String>> listRespuesta = new ArrayList<>();
        Map<String, String> map = null;
        for (Map<String, Object> iterator : resultado) {
            map = new HashMap<>();
            map.put("comprobante", iterator.get(transferVoucher).toString());
            map.put("fecha", iterator.get(transferDate).toString());
            map.put("cantidad", iterator.get(transferAmount).toString());
            listRespuesta.add(map);
        }
        return listRespuesta;
    }
}
