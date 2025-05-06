package fanasina.group.fifacentralapi.database;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Datasource {
    private static final String URL = "jdbc:postgresql://localhost:5432/fifa_central";
    private static final String USER = "postgres";
    private static final String PASSWORD = "fanasina";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}