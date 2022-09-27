package br.univille.loginproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    
    // SINGLETON
    private Connection conn;
    private String connection = "jdbc:mariadb://localhost/loginsistem";
    private String user = "root";
    private String pass = "univille";
    private static ConnectionDB instance;

    private ConnectionDB() throws SQLException {
        this.conn = DriverManager.getConnection(connection, user, pass);
    }

    public static ConnectionDB getInstance() throws SQLException {
        if (instance == null)
            instance = new ConnectionDB();
        
        return instance;
    }

    public Connection getConn() {
        return this.conn;
    }
}
