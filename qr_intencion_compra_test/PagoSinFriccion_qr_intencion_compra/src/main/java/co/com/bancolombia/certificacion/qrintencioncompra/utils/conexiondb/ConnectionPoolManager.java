package co.com.bancolombia.certificacion.qrintencioncompra.utils.conexiondb;


import co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes;
import co.com.bancolombia.conexion.utilidades.enums.UserType;
import co.com.bancolombia.conexion.utilidades.exception.ConnectionExceptions;
import co.com.bancolombia.conexion.utilidades.properties.PropertiesManager;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static co.com.bancolombia.conexion.utilidades.ByteUtils.getByte;
import static co.com.bancolombia.conexion.utilidades.constants.Constants.USUARIO_CONEXION;

/**
 * The type Connection pool manager.
 */
@Log4j2
class ConnectionPoolManager {

    private GenericObjectPool connectionPool = null;

    /**
     * Init connection data source.
     *
     * @param urlHost        the url host
     * @param userConnection the user connection
     * @param userPassword   the user password
     * @param userType       the user type
     * @return the data source
     */
    DataSource initConnection(String urlHost, String userConnection, String userPassword,
                              UserType userType) {
        ConnectionFactory connectionFactory;
        Properties dbProp = PropertiesManager.getDbProperties(Constantes.POSTGRE_SQ);
        try {
            Class.forName("com.p6spy.engine.spy.P6SpyDriver");
            if (USUARIO_CONEXION.equalsIgnoreCase(userType.getValor())) {
                // Crea un objeto ConnectionFactory que será utilizado por el pool para crear el objeto de conexión
                connectionFactory = new DriverManagerConnectionFactory(urlHost, userConnection,
                        new String(getByte(userPassword), StandardCharsets.UTF_8));
            } else {
                connectionFactory = new DriverManagerConnectionFactory(urlHost, userConnection, userPassword);
            }
        } catch (ClassNotFoundException ex) {
            log.error(ex.getMessage(), ex);
            throw new ConnectionExceptions(ex.getMessage());
        }
        // Crea una instancia de GenericObjectPool que contiene nuestro conjunto de conexiones
        connectionPool = new GenericObjectPool();
        connectionPool.setMaxActive(Integer.parseInt(dbProp.getProperty("QUANTITY").trim()));
        // Crea un PoolableConnectionFactory que envolverá el objeto de conexión Creado por ConnectionFactory
        // para agregar funcionalidad de agrupación de objetos!
        new PoolableConnectionFactory(connectionFactory, connectionPool, null, null,
                false, true);
        return new PoolingDataSource(connectionPool);
    }

    /**
     * Connection status string.
     *
     * @return the string
     */
    String connectionStatus() {
        return String.format("Conexiones Max.: %s Activas: %d Abiertas: %d", connectionPool.getMaxActive(),
                connectionPool.getNumActive(), connectionPool.getNumIdle());
    }
}

