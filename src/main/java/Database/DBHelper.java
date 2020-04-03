package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;

public class DBHelper {

    public DBHelper() {

        System.out.println("Testing connection to PostgreSQL JDBC");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
    }

    public Connection connection(String URL, String PORT, String DB_NAME, String LOGIN, String PASSWORD) throws Exception {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL + PORT + DB_NAME,
                    LOGIN, PASSWORD);
            System.out.println("PostgreSQL JDBC Driver successfully connected");
        } catch (SQLDataException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}

