/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.electronic_invoice.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ian Mubangizi <io@ianmubangizi.com>
 */
public final class DatabaseService {

    private Statement statement;
    private Connection connection;

    // [Note], these are my Local MySQL settings, do change them
    private final String dbuser = "root";
    private final String dbpassword = "Kueyf2J5Z4Vb";
    private final String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/orion";

    public DatabaseService(){
        setDbService();
    }

    public void setDbService() {
        try {
            Class.forName(jdbc_driver);
            this.connection = DriverManager.getConnection(
                    url, dbuser, dbpassword);
            setStatement(this.connection.createStatement());
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    public Statement getStatement() {
        if (this.statement == null) {
            try {
                this.statement = this.connection.createStatement();
            } catch (SQLException e) {
            }
        }
        return this.statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getQuery(String sql) {
        try {
            return getStatement().executeQuery(sql);
        } catch (SQLException e) {
        }
        return null;
    }

    public void updateQuery(String sql) {
        try {
            getStatement().executeUpdate(sql);
            getStatement().closeOnCompletion();
        } catch (SQLException e) {
        }
    }
}
