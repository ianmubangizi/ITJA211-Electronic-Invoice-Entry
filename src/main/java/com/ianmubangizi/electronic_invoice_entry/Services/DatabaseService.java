/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianmubangizi.electronic_invoice_entry.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class DatabaseService {

    private Statement statement;
    private final Connection connection;

    private final String dbuser = "root";
    private final String dbpassword = "root_pass"; 
    private final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private final String url =  "jdbc:mysql://localhost:3306/orion";

    public DatabaseService() throws SQLException, ClassNotFoundException {
        Class.forName(jdbc_driver);
        this.connection = DriverManager.getConnection(
            // Note, these are my Local MySQL settings, do change them  
                url, dbuser, dbpassword
        );
        setStatement(this.connection.createStatement());
    }


    public Statement getStatement() {
        return this.statement;
    }

    public void setStatement(Statement statement) throws SQLException {
        this.statement = statement;
    }

    public ResultSet getQuery(String sql) throws SQLException {
        return getStatement().executeQuery(sql);
    }

    public void updateQuery(String sql) throws SQLException {
        getStatement().executeUpdate(sql);
    }
}
