package co.com.bancolombia.certificacion.listarvinculacion.utils.conexiondb;

import java.sql.Connection;

public class ConnectionManager {
    public ConnectionManager() {
    }

    public static Connection getPostgreSqlConnection() {
        return PostgresSqlConnectionManager.getConnection();
    }
}
