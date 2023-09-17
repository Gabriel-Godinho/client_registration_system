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
package br.univille.login.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;

import br.univille.login.controller.LoginViewController;
import br.univille.login.entitys.Login;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class LoginView extends JFrame{

    private final JPanel PANEL_SOUTH = new JPanel();
    private final JPanel PANEL_CENTER = new JPanel();
    private final JLabel USER_LABEL = new JLabel("Usuário");
    private final JLabel PASSWORD_LABEL = new JLabel("Senha");
    private final JTextField USER_FIELD = new JTextField(20);
    private final JPasswordField PASSWORD_FIELD = new JPasswordField(20);
    private final JButton REGISTER_BUTTON = new JButton("Registrar-se");
    private final JButton ENTER_BUTTON = new JButton("Entrar");
    private final LoginViewController CONTROLLER = new LoginViewController(this);
    
    public LoginView() {
        setSize(315, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Login");
        createView();
        setVisible(true);
    }

    private void createView() {
        add(PANEL_SOUTH, "South");
        PANEL_SOUTH.setBackground(new ColorUIResource(130, 188, 224));
        PANEL_SOUTH.add(REGISTER_BUTTON);
        PANEL_SOUTH.add(ENTER_BUTTON);
        REGISTER_BUTTON.setToolTipText("Cadastre um novo usuário (ALT + N)");
        REGISTER_BUTTON.setMnemonic('N');
        REGISTER_BUTTON.setName("register");
        REGISTER_BUTTON.addActionListener(CONTROLLER);
        ENTER_BUTTON.setToolTipText("Entre com um usuário existente (ALT + E)");
        ENTER_BUTTON.setMnemonic('E');
        ENTER_BUTTON.setName("enter");
        ENTER_BUTTON.addActionListener(CONTROLLER);
        ENTER_BUTTON.addKeyListener(CONTROLLER);
        PASSWORD_FIELD.addKeyListener(CONTROLLER);
        add(PANEL_CENTER, "Center");
        PANEL_CENTER.setBorder(BorderFactory.createTitledBorder("Login"));
        PANEL_CENTER.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        // cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new InsetsUIResource(7, 7, 7, 7);

        cons.gridx = 0;
        cons.gridy = 0;
        PANEL_CENTER.add(USER_LABEL, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        PANEL_CENTER.add(USER_FIELD, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        PANEL_CENTER.add(PASSWORD_LABEL, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        PANEL_CENTER.add(PASSWORD_FIELD, cons);
    }

    public Login getLogin() {
        Login log = new Login();
        log.setUser(USER_FIELD.getText());
        String pass = new String(PASSWORD_FIELD.getPassword());
        log.setPassword(pass);

        return log;
    }

}
