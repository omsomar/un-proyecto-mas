package co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utilidad para generar archivos json para mantener data lista para otros test
 *
 * @author ssrojas@bancolombia.com.co
 */
@Log4j2
public class GeneradorArchivosJSON {


    public static final String RUTA_ARCHIVO_PROPERTIES = System.getProperty("user.dir");
    public static final String CARPETA_ARCHIVO = File.separator
            + "src"
            + File.separator
            + "test"
            + File.separator
            + "resources"
            + File.separator;

    private GeneradorArchivosJSON() {
    }

    /**
     * permite convertir data en formato json y almacenarlo en archivo .json
     * primero verifica si ya existe un archivo con el objetivo de no eliminar data generada por otros casos en el feature
     * segundo en caso de existir extrae la data, y adiciona la nueva data
     * o en caso que el archivo sea nuevo genera la data y escribe el archivo nuevo.
     *
     * @param dataList
     * @param fileName
     * @throws IOException
     */
    public static void convertirListaAJSON(List<String> dataList, String fileName) throws IOException {
        Gson gson = new Gson();
        File file = new File(RUTA_ARCHIVO_PROPERTIES + CARPETA_ARCHIVO + fileName);
        AtomicInteger ai = new AtomicInteger();
        if (!file.exists()) {
            Map<Integer, String> mapData = new HashMap<>();
            for (String data : dataList) {
                mapData.put(ai.getAndIncrement(), data);
            }
            String dataConverted = gson.toJson(mapData, new TypeToken<HashMap<Integer, String>>() {
            }.getType());
            Files.write(dataConverted.getBytes(StandardCharsets.UTF_8), file);
        } else {
            Reader reader = Files.newReader(file, Charset.defaultCharset());
            Map<Integer, String> lista = new Gson().fromJson(reader, new TypeToken<HashMap<Integer, String>>() {
            }.getType());
            ai.addAndGet(lista.size());
            for (String data : dataList) {
                lista.put(ai.incrementAndGet(), data);
            }
            String dataConverted = gson.toJson(lista, new TypeToken<HashMap<Integer, String>>() {
            }.getType());
            Files.write(dataConverted.getBytes(StandardCharsets.UTF_8), file);
        }

    }

    public static void convertirStringAJSON(String data, String fileName) throws IOException {
        Gson gson = new Gson();
        File file = new File(RUTA_ARCHIVO_PROPERTIES + CARPETA_ARCHIVO + fileName);
        AtomicInteger ai = new AtomicInteger(1);
        if (!file.exists()) {
            Map<Integer, String> mapData = new HashMap<>();
            mapData.put(ai.getAndIncrement(), data);
            String dataConverted = gson.toJson(mapData, new TypeToken<HashMap<Integer, String>>() {
            }.getType());
            Files.write(dataConverted.getBytes(StandardCharsets.UTF_8), file);
        } else {
            Reader reader = Files.newReader(file, Charset.defaultCharset());
            Map<Integer, String> mapa = new Gson().fromJson(reader, new TypeToken<HashMap<Integer, String>>() {
            }.getType());
            ai.addAndGet(mapa.size() - 1);
            mapa.put(ai.incrementAndGet(), data);
            String dataConverted = gson.toJson(mapa, new TypeToken<HashMap<Integer, String>>() {
            }.getType());
            Files.write(dataConverted.getBytes(StandardCharsets.UTF_8), file);
        }

    }

    public static void convertirMapaAArchivo(List<Map<String, Object>> mapa, String fileName) throws IOException {
        Gson gson = new Gson();
        File file = new File(RUTA_ARCHIVO_PROPERTIES + CARPETA_ARCHIVO + fileName);

        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!file.exists()) {
            for (Map<String, Object> data : mapa) {
                mapList.add(data);
            }
            String dataConverted = gson.toJson(mapList, new TypeToken<List<Map<String, String>>>() {
            }.getType());
            Files.write(dataConverted.getBytes(StandardCharsets.UTF_8), file);
        } else {
            Reader reader = Files.newReader(file, Charset.defaultCharset());
            mapList = new Gson().fromJson(reader, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
            for (Map<String, Object> data : mapa) {
                mapList.add(data);
            }
            String dataConverted = gson.toJson(mapList, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
            Files.write(dataConverted.getBytes(StandardCharsets.UTF_8), file);
        }
    }

    /**
     * convierte la data en map tipo <integer, String>
     *
     * @param fileName
     * @return map<Integer, String>|
     * @throws FileNotFoundException
     */
    public static Map<Integer, String> readFileToMap(String fileName) throws IOException {
        File file = new File(RUTA_ARCHIVO_PROPERTIES + CARPETA_ARCHIVO + fileName);
        Reader reader = Files.newReader(file, Charset.defaultCharset());
        Map<Integer, String> result = new Gson().fromJson(reader, new TypeToken<Map<Integer, String>>() {
        }.getType());
        reader.close();
        return result;
    }

    public static List<Map<String, Object>> readFileToListOfMap(String fileName) throws IOException {
        File file = new File(RUTA_ARCHIVO_PROPERTIES + CARPETA_ARCHIVO + fileName);
        Reader reader = Files.newReader(file, Charset.defaultCharset());
        List<Map<String, Object>> result = new Gson().fromJson(reader, new TypeToken<List<Map<String, Object>>>() {
        }.getType());
        reader.close();
        return result;
    }

    public static void deleteJsonFile(String fileName) {
        try {
            Path path = Paths.get(RUTA_ARCHIVO_PROPERTIES + CARPETA_ARCHIVO + fileName);
            java.nio.file.Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("Error eliminando el archivo Json", e);
        }
    }
}
