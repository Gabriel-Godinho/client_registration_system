package br.univille.sistema.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    // Singleton -> Design pattern
    // refactoring guru design patterns;
    private Connection conn;
    private String stringConexao = "jdbc:mariadb://localhost/dbsistema";
    private String user = "root";
    private String pass = "univille";
    private static ConexaoDB instance;
    
    private ConexaoDB() throws SQLException{
        this.conn = DriverManager.getConnection(stringConexao, user, pass);
    }

    public static ConexaoDB getInstance() throws SQLException{
        if(instance == null)
            instance = new ConexaoDB();
        
        return instance;
    }

    public Connection getConn() {
        return this.conn;
    }
}
