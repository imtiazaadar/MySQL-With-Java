package crud;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Imtiaz Adar
 */
public class Insert_Item {
    private String SQL, table;
    private int ID, Phone;
    private String Full_Name, Password, Address;
    private PreparedStatement statement;
    public Insert_Item(String table, int ID, String Full_Name, String Password, int Phone, String Address) throws SQLException{
        this.table = table;
        this.ID = ID;
        this.Full_Name = Full_Name;
        this.Password = Password;
        this.Phone = Phone;
        this.Address = Address;
        SQL = "INSERT INTO "+this.table+"(ID, FULL_NAME, PASSWORD, PHONE, ADDRESS) VALUES"
                + "('"+ID+"', '"+Full_Name+"', '"+Password+"', '"+Phone+"', '"+Address+"')";
        statement = new Connect().getConnection().prepareStatement(SQL);
        try{
            statement.executeUpdate();
            System.out.println("Data Inserted Successfully!!!");
        }
        catch(Exception e){
            System.out.println("Data Not Inserted!!!");
        }
    }
}
