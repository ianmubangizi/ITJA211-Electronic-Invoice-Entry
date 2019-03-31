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

    /**
     * [Note] These are my Local MySQL settings, do change them mysql-connector
     * should be downloaded from maven repo using pom.xml
     */
    private final String dbuser = "root";
    private final String dbpassword = "Kueyf2J5Z4Vb"; // use [ ""; ] for no password 
    private final String jdbc_driver = "com.mysql.cj.jdbc.Driver"; // use [ "com.mysql.jdbc.Driver"; ] for older mysql-connector
    private final String url = "jdbc:mysql://localhost:3306/orion";
    private static DatabaseService service = null;

    private DatabaseService() {
        setDbService();
        service = this;
    }

    /**
     *
     * @return
     */
    public static DatabaseService databaseService() {
        if (service == null)
            new DatabaseService();
        return service;
    }

    /**
     *
     */
    public void setDbService() {
        try {
            Class.forName(jdbc_driver);
            this.connection = DriverManager.getConnection(url, dbuser, dbpassword);
            setStatement(this.connection.createStatement());
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    /**
     *
     * @return
     */
    public Statement getStatement() {
        if (this.statement == null) {
            try {
                this.statement = this.connection.createStatement();
            } catch (SQLException e) {
            }
        }
        return this.statement;
    }

    /**
     *
     * @param statement
     */
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     *
     * @param sql
     * @return
     */
    public ResultSet getQuery(String sql) {
        try {
            return getStatement().executeQuery(sql);
        } catch (SQLException e) {
        }
        return null;
    }

    /**
     *
     * @param sql
     */
    public void updateQuery(String sql) {
        try {
            getStatement().executeUpdate(sql);
            getStatement().closeOnCompletion();
        } catch (SQLException e) {
        }
    }
}
