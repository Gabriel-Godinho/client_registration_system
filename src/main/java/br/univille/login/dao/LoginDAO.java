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
package br.univille.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.univille.connection.ConnectionDB;
import br.univille.login.entitys.Login;

public class LoginDAO {

    public final boolean enter(Login login) {
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

    public final void register(Login login) {
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

    public final boolean compare(String user) {
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
