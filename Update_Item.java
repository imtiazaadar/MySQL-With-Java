package crud;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Imtiaz Adar
 */
public class Update_Item {
    private String SQL;
    private PreparedStatement statement;
    private int phone;
    private String newval;
    private String newval1, table;
    public Update_Item(String table, int ID, String choice, String newvalue) throws SQLException{
        this.table = table;
        if(choice.equals("PHONE")){
            try{
                newval = choice;
                phone = Integer.parseInt(newvalue);
                SQL = "UPDATE "+this.table+" SET " + newval + " = '"+phone+"' WHERE ID='"+ID+"'";
            }
            catch(Exception e1){
                e1.printStackTrace();
            }
        }
        else{
            newval1 = choice;
            SQL = "UPDATE "+this.table+" SET " + newval1 + " = '"+newvalue+"' WHERE ID='"+ID+"'";
        }
        try{
            statement = new Connect().getConnection().prepareStatement(SQL);
            statement.executeUpdate();
            System.out.println("Updated Successfully");
        }
        catch(Exception e){
            System.out.println("ERROR!!!");
        }
    }
}
