package br.univille.loginproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.univille.loginproject.entitys.Login;

public class LoginDAO {
    
    /**
     * Método responsável por identificar se o usuário 
     * e a senha digitados na tela principal já existem no banco de dados.
     * Caso o usuário e a senha existam no banco, o método 
     * retornará true. Caso não, retornará false.
     * @param login
     * @return
     */
    public boolean enter(Login login) {      
        try {
            Connection connec = ConnectionDB.getInstance().getConn();
            String sql = "SELECT * FROM logins WHERE username = ? AND pass = ?";
            PreparedStatement ps = connec.prepareStatement(sql);
            ps.setString(1, login.getUser());
            ps.setString(2, login.getPassword());
           
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Método responsável por inserir o usuário
     * e a senha digitados na tela de registro dentro do banco de dados.
     * @param login
    */
    public void register(Login login) {
        try {
            Connection connec = ConnectionDB.getInstance().getConn();
            String sql = "INSERT INTO logins(username, pass) VALUES (?, ?)";
            PreparedStatement ps = connec.prepareStatement(sql);           
            ps.setString(1, login.getUser());
            ps.setString(2, login.getPassword());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }

    /**
     * Método responsável por analisar se o nome de usuário
     * digitado na tela de registro já existe no banco de dados.
     * Caso exista, o método retorna true. Caso não, retornará false.
     * @param user
     * @return
     */
    public boolean compare(String user) {
        try {
            Connection connec = ConnectionDB.getInstance().getConn();
            String sql = "SELECT * FROM logins WHERE username = ?";
            PreparedStatement ps = connec.prepareStatement(sql);
            ps.setString(1, user);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
