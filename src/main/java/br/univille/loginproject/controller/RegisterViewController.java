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
package br.univille.loginproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import br.univille.loginproject.entitys.Login;
import br.univille.loginproject.service.LoginService;
import br.univille.loginproject.view.RegisterView;

public class RegisterViewController implements ActionListener, KeyListener{

    private final RegisterView rv;
    private final LoginService service = new LoginService();

    public RegisterViewController(RegisterView rv) {

        this.rv = rv;

    }

    // Método da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent clicked = (JComponent)e.getSource();

        switch (clicked.getName()) {
            case "confirm":
                confirmButton();
                break;

            case "cancel":
                cancelButton();
                break;
        }   

    }

    // Método que será executado quando o botão Registrar for pressionado
    private void confirmButton() {

        Login login = rv.getLogin();

        try {
            if (service.compare(login.getUser())) {
                JOptionPane.showMessageDialog(null, "Usuário já existente!", "Ops!", JOptionPane.ERROR_MESSAGE);
            } else if (login.getUser().equals("") || login.getPassword().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Ops!", JOptionPane.INFORMATION_MESSAGE);
            } else if (login != null) {
                service.register(login);
                JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                rv.dispose();
            }
        } catch (NullPointerException e) {           
            JOptionPane.showMessageDialog(null, "As senhas não são iguais!", "Ops!", JOptionPane.ERROR_MESSAGE);       
        }

    }

    // Método que será executado quando o botão Cancelar for pressionado
    private void cancelButton() {

        rv.dispose();

    }

    // Métodos da interface KeyListener
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            confirmButton();       
        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}    
}
