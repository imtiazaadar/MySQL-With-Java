package crud;
import java.sql.PreparedStatement;
/**
 *
 * @author Imtiaz Adar
 */
public class Delete_Item {
    private PreparedStatement statement;
    private String SQL, table;
    private int id;
    public Delete_Item(String table, int ID){
        this.table = table;
        this.id = ID;
        try{
            SQL = "DELETE FROM "+this.table+" WHERE ID = '"+this.id+"'";
            statement = new Connect().getConnection().prepareStatement(SQL);
            statement.executeUpdate();
            System.out.println("Deleted Successfully!!!");
        }
        catch(Exception e){
            System.out.println("ID Not Matched!!!");
        }
    }
}
