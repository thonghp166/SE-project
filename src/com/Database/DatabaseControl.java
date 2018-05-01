package com.Database;

/**
 * @author dgbttn
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.util.TypeEnum;
import com.util.TypeUtils;
import javafx.util.Pair;


public class DatabaseControl implements Database {
    private static String DB_URL = "jdbc:mysql://localhost:3306/testjdbc";
    private static String USER_NAME = "root";
    private static String PASSWORD = "0918273645";

    private static Connection connection = null;

    DatabaseControl() {}

    /**
     * get the connection to the DB
     * @return the connection
     */
    public Connection getConnectToDatabase() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Connect successfully!");
        } catch (Exception ex) {
            System.out.println("Connect failed!");
            ex.printStackTrace();
        }
        return conn;
    }

    @Override
    public void ConnectToDatabase() {
        connection = getConnectToDatabase();
    }

    @Override
    public void Disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable(String tableName, LinkedList<String> columnName, LinkedList<String> columnType)  {
        if (columnName.size()!=columnType.size()) {
            System.out.println("Table " + tableName + "is invalid!");
            return;
        }

        if (columnName.size()*columnType.size() == 0) {
            System.out.println("Table " + tableName + "is empty!");
            return;
        }

        try {
            Statement stmt = connection.createStatement();
            String name, type;
            name = columnName.get(0);
            type = TypeUtils.getSQLType(columnType.get(0));

            String Command = "CREATE TABLE IF NOT EXISTS `" + tableName + "` ( ";

            if (type==null) {
                System.out.println("Type " + type + "is invalid");
                return;
            }

            Command += "\n `" + name + "` "+ type + "(30) NOT NULL ";
            Command += (type == "VARCHAR")? "COLLATE utf8_vietnamese_ci": "";

            for (int i=1; i<columnName.size(); i++) {
                name = columnName.get(i);
                type = TypeUtils.getSQLType(columnType.get(i));
                if (type==null) {
                    System.out.println("Type " + type + "is invalid");
                    return;
                }

                Command += ", \n `" + name + "` " + type + "(30) NOT NULL ";
                Command += (type == "VARCHAR")? "COLLATE utf8_vietnamese_ci": "";
            }

            Command += ") \n ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci; ";
            //System.out.println(Command);

            stmt.executeUpdate(Command);
            System.out.println("Create Table Successfully!");

        } catch (SQLException e) {
            System.out.println("Create Statement Failed!");
            e.printStackTrace();
        }

    }

    @Override
    public void dropTable(String tableName) {
        try {
            Statement stmt = connection.createStatement();
            String Command = "DROP TABLE IF EXISTS`" + tableName + "` ;";
            stmt.executeUpdate(Command);
        } catch (SQLException e) {
            System.out.println("Create Statement Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public void insertDataToTable(String tableName, HashMap<String, Pair<Integer, String>> data) {
        try {
            Statement stmt = connection.createStatement();
            String Command = "INSERT INTO `" + tableName + "` (";
        } catch (SQLException e) {
            System.out.println("Create Statement Failed!");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<TypeEnum> getColumnTypes(String tableName) {
        ArrayList<TypeEnum> columnTypes = new ArrayList<>();
        String selectAll = "SELECT * FROM `"+tableName +"` ;";

        try {
            PreparedStatement stmt = connection.prepareStatement(selectAll);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i=1; i<=rsmd.getColumnCount(); i++)
                columnTypes.add(TypeUtils.getChartType(rsmd.getColumnTypeName(i)));
        } catch (SQLException e) {
            System.out.println("Create Statement Failed!");
            e.printStackTrace();
        }
        return columnTypes;
    }


}