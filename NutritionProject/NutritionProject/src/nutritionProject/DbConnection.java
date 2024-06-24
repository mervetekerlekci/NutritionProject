package nutritionProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/nutrition";
    private String username = "root";
    private String password = "123456";

    private DbConnection() {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new DbConnection();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
