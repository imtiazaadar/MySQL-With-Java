package mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author Imtiaz Adar
 */
public class PlayWithSql {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String URL = "jdbc:mysql://127.0.0.1/sqlwithjava";
        String user = "root";
        String pass = "";
        //Class.forName("com.mysql.jdbc.Driver");
        String create_table = "CREATE TABLE My_Info(ID INT(5) NOT NULL, NAME VARCHAR(30) NOT NULL, PASSWORD VARCHAR(30) NOT NULL)";
        String insert = "INSERT INTO My_Info (ID, NAME, PASSWORD) VALUES('"+2+"','Imtiaz Ahmed', 'imad')";
        String select = "SELECT * FROM My_Info";
        String update = "UPDATE My_Info SET NAME = 'IMTIAZ BHAI' WHERE ID=2";
        Connection connection = (Connection)DriverManager.getConnection(URL, user, pass);
        if(connection != null) System.out.println("Connected to the database!!!");
        else System.out.println("Sorry, there is something wrong!!!");
        try{
            PreparedStatement statement = connection.prepareStatement(select);
//            statement.executeUpdate();
//            System.out.println("Inserted Successfully!!!");
//            System.out.println("Table created successfully!!!");
            ResultSet result = statement.executeQuery();
            System.out.println("ALL RECORDS FROM THE TABLE\n");
            while(result.next()){
                int id = result.getInt("ID");
                String name = result.getString("NAME");
                String password = result.getString("PASSWORD");
                System.out.println(id + " " + name + " " + password);
            }
        }
        catch(Exception e){
            System.out.println("Error!!!");
           // System.out.println("Table cannot be created!!!");
        }
    }
}
