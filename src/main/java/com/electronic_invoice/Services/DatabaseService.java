package com.electronic_invoice.Services;

import java.sql.*;

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
    private final String user = "root";
    private final String password = "Kueyf2J5Z4Vb"; // use [ ""; ] for no password
    private final String jdbc_driver = "com.mysql.cj.jdbc.Driver"; // use [ "com.mysql.jdbc.Driver"; ] for older mysql-connector-java
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
    private void setDbService() {
        try {
            Class.forName(jdbc_driver);
            this.connection = DriverManager.getConnection(url, user, password);
            setStatement(this.connection.createStatement());
        } catch (ClassNotFoundException | SQLException ignored) {

        }
    }

    /**
     *
     * @return
     */
    private Statement getStatement() {
        if (this.statement == null) {
            try {
                this.statement = this.connection.createStatement();
            } catch (SQLException ignored) {
            }
        }
        return this.statement;
    }

    /**
     *
     * @param statement
     */
    private void setStatement(Statement statement) {
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
        } catch (SQLException ignored) {
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
        } catch (SQLException ignored) {
        }
    }
}
