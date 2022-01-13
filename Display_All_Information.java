package crud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Imtiaz Adar
 */
public class Display_All_Information {
    private String SQL, table;
    private PreparedStatement statement;
    private ResultSet result;
    public Display_All_Information(String table){
        this.table = table;
        SQL = "SELECT * FROM "+this.table;
        try{
            statement = new Connect().getConnection().prepareStatement(SQL);
            result = statement.executeQuery();
            while(result.next()){
                int id = result.getInt("ID");
                String name = result.getString("FULL_NAME");
                String password = result.getString("PASSWORD");
                int phone = result.getInt("PHONE");
                String address = result.getString("ADDRESS");
                System.out.println("|| " + id + " || " + name + " || " + password + " || " + phone + " || " + address + " ||");
            }
        }
        catch(Exception e){
            System.out.println("ERROR!!!");
        }
    }
}
