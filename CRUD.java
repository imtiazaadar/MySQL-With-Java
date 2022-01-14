package mysqljava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
/*
 * Author : Imtiaz Adar
 */
public class CRUD {
	// Connection
	public static Connection getConnection(String database) throws SQLException {
		String host = "jdbc:mysql://127.0.0.1/"+database;
		String user = "root";
		String pass = "";
		Connection connection = (Connection)DriverManager.getConnection(host, user, pass);
		return connection;
	}
	// Create Table
	public static void CreateTable(String table_name, String var, String data, int space, String nullornot, String sen, Connection con) throws SQLException {
		String SQL = "CREATE Table "+table_name+"("+sen+")";
		PreparedStatement statement = con.prepareStatement(SQL);
		statement.executeUpdate();
	}
	// Primary Key
	public static void PrimaryKey(String table_name, String var, Connection con) throws SQLException {
		String SQL = "ALTER TABLE "+table_name+" ADD PRIMARY KEY("+var+")";
		PreparedStatement statement = con.prepareStatement(SQL);
		statement.executeUpdate();
	}
	// Insert Item
	public static void InsertItem(String table_name, String sen, Connection con) throws SQLException {
		String SQL = "INSERT INTO "+table_name+" VALUES"+sen+"";
		PreparedStatement statement = con.prepareStatement(SQL);
		statement.executeUpdate();
	}
	// Own Sql
	public static void OwnSql(String table_name, String which, String sen, Connection con) throws SQLException {
		String SQL = "SELECT "+which+" FROM "+table_name+" WHERE "+sen;
		System.out.println(SQL);
		PreparedStatement statement = con.prepareStatement(SQL);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			String ans = result.getString(which);
			System.out.print(ans + " ");
		}
		System.out.println();
	}
	// Display Table
	public static void DisplayAll(String table_name, Connection con) throws SQLException {
		String SQL = "SELECT * FROM "+table_name;
		PreparedStatement statement = con.prepareStatement(SQL);
		ResultSet result = statement.executeQuery();
		ResultSetMetaData metadata = (ResultSetMetaData) result.getMetaData();
		int count = metadata.getColumnCount();
		String vals = "";
		String[] column_names = new String[count];
		for(int i = 1; i <= count; i++) {
			column_names[i - 1] = metadata.getColumnLabel(i);
			vals += column_names[i - 1] + " ";
		}
		String[] columns = vals.split(" ");
		for(int i = 0; i < columns.length; i++) {
			System.out.print(columns[i] + "");
			if(i < columns.length - 1)
				System.out.print(" || ");
		}
		System.out.println();
		while(result.next()) {
			for(int i = 0; i < columns.length; i++) {
				String ans = result.getString(columns[i]);
				System.out.print(ans + "");
				if(i < columns.length - 1)
					System.out.print(" || ");
			}
			System.out.println();
		}
	}
	// Update
	public static void UpdateItem(String table_name, String upv, String upe, String w, String eq, Connection con) throws SQLException {
		String SQL = "UPDATE "+table_name+" SET "+upv+"='"+upe+"' WHERE "+w+"='"+eq+"'";
		PreparedStatement statement = con.prepareStatement(SQL);
		statement.executeUpdate();
	}
	// Delete
	public static void DeleteItem(String table_name, String wh, String e, Connection con) throws SQLException {
		String SQL = "DELETE FROM "+table_name+" WHERE "+wh+"='"+e+"'";
		PreparedStatement statement = con.prepareStatement(SQL);
		statement.executeUpdate();	
	}
	public static void main(String[] args) throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter The Name Of Database : ");
		String database = scan.nextLine();
		Connection con = getConnection(database);
		if(con != null) {
			System.out.println("Connected");
		}
		else System.out.println("Not Connected");
		System.out.println("Enter The Name Of Table : ");
		String table_name = scan.nextLine();
		int choice;
		boolean status = true;
		while(status) {
			System.out.println("ENTER YOUR CHOICE\n1. Create Table\n2. Insert\n3. Update\n4. Delete\n5. Own Query\n"
					+ "6. Display Table\n7. Exit\n");
			choice = scan.nextInt();
			switch(choice) {
			case 1:
				System.out.println("\n!!! CREATING TABLE !!!\t");
				System.out.println("Rows : ");
				int how = scan.nextInt();
				String sen = "";
				String var = null, data = null, nullornot = null;
				int space = 0;
				for(int i = 0; i < how; i++) {
					System.out.println("Variable Name : ");
					var = scan.next();
					System.out.println("Data Type : ");
					data = scan.next();
					System.out.println("Space : ");
					space = scan.nextInt();
					System.out.println("Null Or Not Null: ");
					nullornot = scan.nextLine();
					scan.nextLine();
					System.out.println();
					sen += var+" "+data+"("+space+") "+nullornot+",";
				}
				try {
					StringBuilder sb = new StringBuilder(sen);
					sb.deleteCharAt(sb.length() - 1);
					String sen1 = sb.toString();
					CreateTable(table_name, var, data, space, nullornot, sen1, con);
					System.out.println("Which One will be the Primary Key : ");
					String primary = scan.next();
					PrimaryKey(table_name, primary, con);
					System.out.println("Table Created Successfully !!!");
				}
				catch(Exception e) {
					System.out.println("ERROR !!!");
				}
				System.out.println();
				break;
			case 2:
				System.out.println("\t!!! INSERTING !!!\t");
				System.out.println("Rows : ");
				int rows = scan.nextInt();
				String SQL = "SELECT * FROM "+table_name;
				PreparedStatement statement = con.prepareStatement(SQL);
				ResultSet result = statement.executeQuery();
				ResultSetMetaData metadata = (ResultSetMetaData) result.getMetaData();
				int count = metadata.getColumnCount();
				String vals = "";
				String[] column_names = new String[count];
				for(int i = 1; i <= count; i++) {
					column_names[i - 1] = metadata.getColumnLabel(i);
					vals += column_names[i - 1] + " ";
				}
				String[] columns = vals.split(" ");
				String senn = "";
				scan.nextLine();
				for(int i = 0; i < columns.length; i++) {
					System.out.print(columns[i] + "\t");
				}
				System.out.println();
				try {
					for(int i = 0; i < rows; i++) {
						String insertstr = "(";
						for(int j = 0; j < count; j++) {
							System.out.println("Enter Data Type : ");
							String datatype = scan.nextLine();
							if(datatype.equals("INT") || datatype.equals("int")) {
								System.out.println("Enter : ");
								String sent = scan.nextLine();
								insertstr += sent+",";
							}
							else if(datatype.equals("VARCHAR") || datatype.equals("varchar")) {
								System.out.println("Enter : ");
								String sent = scan.nextLine();
								insertstr += "'"+sent+"',";
							}
							else if(datatype.equals("DATE") || datatype.equals("date")) {
								System.out.println("Enter Year : ");
								String year = scan.nextLine();
								System.out.println("Enter Month : ");
								String mon = scan.nextLine();
								System.out.println("Enter Day : ");
								String day = scan.nextLine();
								insertstr += "'"+year+"'-'"+mon+"'-'"+day+"',";
							}
						}
						insertstr += ")";
						StringBuilder sbb = new StringBuilder(insertstr);
						sbb.deleteCharAt(sbb.length() - 2);
						String newstr = sbb.toString();
						InsertItem(table_name, newstr, con);
						System.out.println("Inserted Successfully !!!");
					}
				}
				catch(Exception e) {
					System.out.println("ERROR !!!");
				}
				System.out.println();
				break;
			case 3:
				System.out.println("\t!!! UPDATING !!!\t");
				scan.nextLine();
				System.out.println("Enter Update Variable : ");
				String update = scan.nextLine();
				System.out.println("Enter Update Equals : ");
				String upeq = scan.nextLine();
				System.out.println("Where : ");
				String wh = scan.nextLine();
				System.out.println("Equals : ");
				String eqQ = scan.nextLine();
				try {
					UpdateItem(table_name, update, upeq, wh, eqQ, con);
					System.out.println("Updated Successfully !!!");
				}
				catch(Exception e) {
					System.out.println("ERROR !!!");
				}
				System.out.println();
				break;
			case 4:
				System.out.println("\t!!! DELETING !!!\t");
				scan.nextLine();
				System.out.println("Where : ");
				String whe = scan.nextLine();
				System.out.println("Equals : ");
				String eqqq = scan.nextLine();
				try {
					DeleteItem(table_name, whe, eqqq, con);
					System.out.println("Deleted Successfully !!!");
				}
				catch(Exception e) {
					System.out.println("ERROR !!!");
				}
				System.out.println();
				break;
			case 5:
				System.out.println("\t!!! OWN QUERY !!!\t");
				String in = "";
				String SQL1 = null, SQL2 = null;
				System.out.println("Conditions : ");
				int conditions = scan.nextInt();
				scan.nextLine();
				for(int i = 0; i < conditions; i++) {
					System.out.println("Select : ");
					SQL1 = scan.nextLine();
					System.out.println("Where Variable : ");
					String where = scan.nextLine();
					System.out.println("Data Type : ");
					String datat = scan.nextLine();
					if(datat.equals("INT") || datat.equals("int")) {
						System.out.println("Variable Equals : ");
						String eq = scan.nextLine();
						in += where+"='"+eq+"' AND ";
					}
					else if(datat.equals("VARCHAR") || datat.equals("varchar")) {
						System.out.println("Variable Equals : ");
						String eq = scan.nextLine();
						in += where+"='"+eq+"' AND ";
					}
					else if(datat.equals("DATE") || datat.equals("date")) {
						System.out.println("Enter Year : ");
						String year = scan.nextLine();
						System.out.println("Enter Month : ");
						String mon = scan.nextLine();
						System.out.println("Enter Day : ");
						String day = scan.nextLine();
						in += where+"='"+year+"'-'"+mon+"'-'"+day+"' AND ";
					}
				}
				try {
					StringBuilder s = new StringBuilder(in);
					s.delete(s.length() - 5, s.length() - 1);
					String ss = s.toString();
					OwnSql(table_name, SQL1, ss, con);
					System.out.println("Successfully Done !!!");
				}
				catch(Exception e) {
					System.out.println("ERROR !!!");
				}
				System.out.println();
				break;
			case 6:
				System.out.println("\t!!! DISPLAYING TABLE !!!\t\n");
				try {
					DisplayAll(table_name, con);
					System.out.println("\nSuccessful !!!");
				}
				catch(Exception e) {
					System.out.println("ERROR !!!");
				}
				System.out.println();
				break;
			case 7:
				status = false;
				System.out.println();
				break;
			}
		}
	}
}
