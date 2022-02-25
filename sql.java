package database;

import java.sql.*;
import java.util.Scanner;

/**
 * Author: Imtiaz Adar
 * Database
 */
public class sql {
    // checking connection
    public static Connection getConnection(String host, String user, String pass) throws SQLException{
        Connection connection = (Connection) DriverManager.getConnection(host, user, pass);
        return connection;
    }
    // create table
    public static void createTable(Connection con, String table, String sen) throws SQLException{
        String SQL = "CREATE TABLE " + table + " (" + sen + ")";
        PreparedStatement statement = con.prepareStatement(SQL);
        statement.executeUpdate();
    }
    // primary key
    public static void addPrimaryKey(Connection con, String table, String key) throws SQLException{
        String SQL = "ALTER TABLE " + table + " ADD PRIMARY KEY(" + key + ")";
        PreparedStatement statement = con.prepareStatement(SQL);
        statement.executeUpdate();
    }
    // insert
    public static void insertIntoTable(Connection con, String table, String sen) throws SQLException{
        String SQL = "INSERT INTO " + table + " VALUES(" + sen + ")";
        PreparedStatement statement = con.prepareStatement(SQL);
        statement.executeUpdate();
    }
    // update
    public static void updateInformation(Connection con, String table, String setwhere, String setv, String where, String upd) throws SQLException{
        String SQL = "UPDATE " + table + " SET " + setwhere + "='" + setv + "'" + " WHERE " + where + "='" + upd + "'";
        System.out.println(SQL);
        PreparedStatement statement = con.prepareStatement(SQL);
        statement.executeUpdate();
    }
    // delete
    public static void deleteItem(Connection con, String table, String where, String del) throws SQLException{
        String SQL = "DELETE FROM " + table + " WHERE " + where + "='" + del + "'";
        PreparedStatement statement = con.prepareStatement(SQL);
        statement.executeUpdate();
    }
    // own sql
    public static void ownSql(Connection con, String table, String where, String value) throws SQLException{
        String SQL = "SELECT " + where + " FROM " + table + " WHERE " + value;
        PreparedStatement statement = con.prepareStatement(SQL);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            String val = result.getString(where);
            System.out.print(val + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter database : ");
        String DATABASE = scan.nextLine();
        String HOST = "jdbc:mysql://127.0.0.1/" + DATABASE;
        String USER = "root";
        String PASS = "";
        Connection CONN = getConnection(HOST, USER, PASS);
        if(CONN != null){
            System.out.println("CONNECTED");
        }
        else System.out.println("NOT CONNECTED");

//        createTable(conn, "mydata", "ID INT(5) NOT NULL, NAME VARCHAR(30) NOT NULL, PHONE VARCHAR(12)" +
//                "NOT NULL, PASSWORD VARCHAR(20) NOT NULL");
        //insertIntoTable(conn, "mydata", "2, 'IMTIAZ ADAR', '01778767775', 'IAADAR'");
        //updateInformation(conn, "mydata", "NAME", "IMTIAZ", "ID", "2");
        //deleteItem(CONN, "mydata", "ID", "2");
        ownSql(CONN, "mydata", "PASSWORD", "ID=2");
    }
}
