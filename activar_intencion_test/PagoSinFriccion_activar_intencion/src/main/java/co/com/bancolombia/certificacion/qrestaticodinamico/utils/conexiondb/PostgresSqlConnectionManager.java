package co.com.bancolombia.certificacion.qrestaticodinamico.utils.conexiondb;


import co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes;
import co.com.bancolombia.conexion.utilidades.enums.UserType;
import co.com.bancolombia.conexion.utilidades.exception.ConnectionExceptions;
import co.com.bancolombia.conexion.utilidades.properties.PropertiesManager;
import lombok.extern.log4j.Log4j2;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

@Log4j2
public class PostgresSqlConnectionManager {

    private static DataSource dataSource = null;
    private static ConnectionPoolManager connectionPoolManager = new ConnectionPoolManager();

    private PostgresSqlConnectionManager() {
    }

    public static Connection getConnection() {
        Connection conexion = null;
        Properties dbProp;
        try {
            if (null == dataSource) {
                dbProp = PropertiesManager.getDbProperties(Constantes.POSTGRE_SQ);
                if (!isNotEmptyPropertiesValues(dbProp)) {
                    throw new ConnectionExceptions();
                }
                String urlHost = String.format("%s%s:%s/%s", dbProp.getProperty("JDBC").trim(),
                        dbProp.getProperty("HOST").trim(),
                        dbProp.getProperty("PORT").trim(),
                        dbProp.getProperty("NAME_DATABASE").trim());
                UserType tipUsuario = UserType.valueOf(dbProp.getProperty("TIPO_USUARIO").trim());
                dataSource = connectionPoolManager.initConnection(urlHost, dbProp.getProperty("USER").trim(),
                        dbProp.getProperty("PASS").trim(), tipUsuario);
            }
            conexion = dataSource.getConnection();
            log.info(connectionPoolManager.connectionStatus());
            return conexion;
        } catch (SQLException var4) {
            log.error(var4.getMessage(), var4);
            throw new ConnectionExceptions("Error inesperado al conectase a la base de datos", var4);
        }
    }

    private static boolean isNotEmptyPropertiesValues(Properties dbProp) {
        AtomicBoolean flap = new AtomicBoolean(true);
        dbProp.stringPropertyNames().forEach(a -> {
            String key = a.trim();
            boolean validate = dbProp.getProperty(key).trim().isEmpty();
            if (validate) {
                log.error(String.format("En el archivo de propiedades esta vacio el campo %s", key));
            }
            flap.set(flap.get() && !validate);
        });
        return flap.get();
    }
}
