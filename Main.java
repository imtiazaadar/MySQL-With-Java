package crud;

import java.sql.SQLException;
import java.util.Scanner;
/**
 *
 * @author Imtiaz Adar
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        Connect con = new Connect();
        System.out.println(con.isConnected());
        System.out.println("Enter The Name Of Table : ");
        String nameoftable = scan.nextLine();
        //Create_Table create = new Create_Table(nameoftable);
//        System.out.println("!!! INSERTING !!!");
//        System.out.println("Enter Your ID : ");
//        int ID = scan.nextInt();
//        scan.nextLine();
//        System.out.println("Enter Your Full Name : ");
//        String fullname = scan.nextLine();
//        System.out.println("Enter Your Password : ");
//        String password = scan.nextLine();
//        System.out.println("Enter Your Phone Number : ");
//        int phone = scan.nextInt();
//        scan.nextLine();
//        System.out.println("Enter Your Address : ");
//        String address = scan.nextLine();
//        Insert_Item insert = new Insert_Item(nameoftable, ID, fullname, password, phone, address);
//        System.out.println("!!! UPDATING !!!");
//        System.out.println("Enter Which You Want To Update [FULL_NAME, PASSWORD, PHONE, ADDRESS] : ");
//        String which = scan.nextLine().toUpperCase();
//        String val = "";
//        System.out.println("Enter ID : ");
//        int ID = scan.nextInt();
//        if(which.equals("FULL_NAME")){
//            val = "FULL_NAME";
//        }
//        else if(which.equals("PASSWORD")){
//            val = "PASSWORD";
//        }
//        else if(which.equals("PHONE")){
//            val = "PHONE";
//        }
//        else if(which.equals("ADDRESS")){
//            val = "ADDRESS";
//        }
//        else{
//            System.out.println("Enter again!");
//        }
//        scan.nextLine();
//        System.out.println("Enter New " + val + " : ");
//        String newval = scan.nextLine();
//        Update_Item update = new Update_Item(nameoftable, ID, val, newval);
//        System.out.println("!!! DELETING !!!");
//        System.out.println("ENTER YOUR ID : ");
//        int ID = scan.nextInt();
//        Delete_Item delete = new Delete_Item(nameoftable, ID);
        System.out.println("!!! DISPLAYING ALL INFORMATIONS !!!");
        System.out.println("|| ID  ||  FULL_NAME  ||  PASSWORD  ||  PHONE  ||  ADDRESS  ||");
        Display_All_Information display = new Display_All_Information(nameoftable);
    }
}
