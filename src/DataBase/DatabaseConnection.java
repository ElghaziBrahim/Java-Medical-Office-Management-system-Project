package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection instance;

    private DatabaseConnection() {}

    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabinet_medicale", "root", "");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static void closeConnection() throws SQLException {
        if (instance != null && !instance.isClosed()) {
            instance.close();
        }
    }
}
