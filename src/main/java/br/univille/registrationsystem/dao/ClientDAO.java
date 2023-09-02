/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2022 Gabriel Godinho
 * ------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =====LICENSE-END=====
 */
package br.univille.registrationsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.univille.registrationsystem.entity.Client;

public class ClientDAO {
    
    public final ArrayList<Client> getAll() {
        ArrayList<Client> list = new ArrayList<>();
        try {
            Connection conn = ConnectionDB.getInstance().getConn();
            String sql = "SELECT * FROM clients";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Client newClient = new Client();
                newClient.setId(rs.getLong("id"));
                newClient.setName(rs.getString("full_name"));
                newClient.setCPF(rs.getString("cpf"));
                newClient.setBirthDate(rs.getDate("birthdate"));
                list.add(newClient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public final void save(Client client) {
        try {
            Connection conn = ConnectionDB.getInstance().getConn();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (client.getId() == 0 || client.getId() == -1) {
                String insert = "INSERT INTO clients(full_name, cpf, birthdate) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(insert);
                ps.setString(1, client.getName());
                ps.setString(2, client.getCPF());
                ps.setString(3, sdf.format(client.getBirthDate()));
                ps.executeUpdate();
            } else {
                String update = "UPDATE clients SET full_name = ?, cpf = ?, birthdate = ? WHERE id = ?";
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

    public final void delete(long id) {
        try {
            Connection conn = ConnectionDB.getInstance().getConn();
            String delete = "DELETE FROM clients WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(delete);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
