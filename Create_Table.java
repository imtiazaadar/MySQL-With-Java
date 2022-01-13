package crud;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Imtiaz Adar
 */
public class Create_Table {
    private String SQL, name;
    public Create_Table(String name) throws SQLException{
        this.name = name;
        SQL = "CREATE TABLE "+this.name+"(ID INT(10) NOT NULL PRIMARY KEY, FULL_NAME VARCHAR(30) NOT NULL, PASSWORD VARCHAR(40) NOT NULL, PHONE INT(15) NOT NULL, ADDRESS VARCHAR(50) NOT NULL)";
        PreparedStatement statement = new Connect().getConnection().prepareStatement(SQL);
        try{
            statement.executeUpdate();
            System.out.println("Table Has Created!!");
        }
        catch(Exception e){
            System.out.println("Table Has Not Created!!!");
        }
    }
}
