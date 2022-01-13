package crud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Imtiaz Adar
 */
public class Connect {
    private String database;
    private Connection connection;
    public Connect() throws SQLException{
        this.database = "CRUD";
        String HOST = "jdbc:mysql://127.0.0.1/" + database;
        String USER = "root";
        String PASSWORD = "";
        connection = (Connection) DriverManager.getConnection(HOST, USER, PASSWORD);
    }
    public Connection getConnection(){
        return this.connection;
    }
    public String isConnected(){
        if(this.connection != null)
            return "Connected!!!";
        return "Not Connected, Try Again!!!";
    }
}
