/*-
 * =====LICENSE-START=====
 * Java 11 Application
 * ------
 * Copyright (C) 2020 - 2022 Organization Name
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
package br.univille.login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import br.univille.login.entitys.Login;
import br.univille.login.service.LoginService;
import br.univille.login.view.LoginView;
import br.univille.login.view.RegisterView;
import br.univille.registrationsystem.view.ClientList;

public class LoginViewController implements ActionListener, KeyListener{

    private final LoginService ls = new LoginService();
    private final LoginView lv;

    public LoginViewController(LoginView lv) {

        this.lv = lv;
        
    }

    // Método da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent clicked = (JComponent)e.getSource();

        switch(clicked.getName()){
            case "register":
                registerClick();
                break;
            case "enter":
                enterClick();
                break;
        }
    }

    // Método que será executado quando o botão Registrar-se for pressionado
    private void registerClick() {
        RegisterView rview = new RegisterView();
        System.out.println(rview.getX());
    }

    // Método que será executado quando o botão Entrar for pressionado
    private void enterClick() {
        Login username = lv.getLogin();

        if (ls.enter(username)) {
            lv.setVisible(false);
            ClientList list = new ClientList();
            System.out.println(list.getX());
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não registrado!", "Ops!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Métodos da interface KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterClick();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}    
}
