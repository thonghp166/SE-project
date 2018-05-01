package com.Database;

import com.util.TypeEnum;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author dgbttn
 *
 * interface for user to connect and work with Database
 */

public interface Database {
    // The unique Database
    public static final Database instance = new DatabaseControl();

    // get the unique Database
    public static Database getInstance() {
        return instance;
    }

    /**
     * Connect to the Database
     */
    public void ConnectToDatabase();

    /**
     * Disconnect to the Database
     */
    public void Disconnect();

    /**
     * create a table in the Datavbase
     * @param tableName name of the table
     * @param columnName list of columns' name of the table
     * @param columnType list of columns' type of the table
     */
    public void createTable(String tableName, LinkedList<String> columnName, LinkedList<String> columnType);

    /**
     * delete a table in Database with name
     * @param tableName name of the table
     */
    public void dropTable(String tableName);

    public void insertDataToTable(String tableName, HashMap<String, Pair<Integer, String>> data);

    /**
     * get the columns' type of a table under chart type
     * @param tableName name of the table
     * @return list of types
     */
    public ArrayList<TypeEnum> getColumnTypes(String tableName);

    
}