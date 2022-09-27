package br.univille.sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import br.univille.sistema.entity.Cliente;

public class ClienteDAO {
    
    public ArrayList<Cliente> getAll() {

        ArrayList<Cliente> list = new ArrayList<>();
        
        try {
            Connection conn = ConexaoDB.getInstance().getConn();
            String sql = "SELECT * FROM cliente";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Cliente newClient = new Cliente();
                newClient.setId(rs.getLong("id"));
                newClient.setName(rs.getString("nome"));
                newClient.setCPF(rs.getString("cpf"));
                newClient.setBirthDate(rs.getDate("datanascimento"));
                list.add(newClient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    public void save(Cliente client) {

        try {
            Connection conn = ConexaoDB.getInstance().getConn();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (client.getId() == 0) {
                String insert = "INSERT INTO cliente(nome, cpf, datanascimento) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(insert);
                ps.setString(1, client.getName());
                ps.setString(2, client.getCPF());
                ps.setString(3, sdf.format(client.getBirthDate()));
                ps.executeUpdate();
            } else {
                String update = "UPDATE cliente SET nome = ?, cpf = ?, datanascimento = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(update);
                ps.setString(1, client.getName());
                ps.setString(2, client.getCPF());
                ps.setString(3, sdf.format(client.getBirthDate()));
                ps.setLong(4, client.getId());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public Cliente getClienteById(long id){

        Cliente client = new Cliente();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 
        try {
            Connection conn = ConexaoDB.getInstance().getConn();
            String sql = "SELECT * FROM cliente WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Hidratação do objeto
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("nome"));
                client.setCPF(rs.getString("cpf"));
                client.setBirthDate(sdf.parse(rs.getString("datanascimento")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void delete(long id) {

        try {
            Connection conn = ConexaoDB.getInstance().getConn();
            String delete = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
