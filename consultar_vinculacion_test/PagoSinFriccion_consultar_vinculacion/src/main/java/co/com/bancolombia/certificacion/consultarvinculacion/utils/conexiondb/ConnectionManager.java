package co.com.bancolombia.certificacion.consultarvinculacion.utils.conexiondb;

import java.sql.Connection;

public class ConnectionManager {
    private ConnectionManager() {
    }

    public static Connection getPostgreSqlConnection() {
        return PostgresSqlConnectionManager.getConnection();
    }
}
