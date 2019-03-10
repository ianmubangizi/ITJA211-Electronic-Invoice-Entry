/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ianmubangizi.electronic_invoice_entry.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public class DatabaseService {
    public Statement statement;
    private final Connection connection;

    public DatabaseService() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/orion", "root", ""
        );
    }


    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) throws SQLException {
        this.statement = connection.createStatement();
    }
}
