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
package br.univille.loginproject.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;

import br.univille.loginproject.controller.LoginViewController;
import br.univille.loginproject.entitys.Login;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/* Construção da tela principal de login*/

public class LoginView extends JFrame{

    private JPanel south = new JPanel();
    private JPanel center = new JPanel();
    private JLabel userLabel = new JLabel("Usuário");
    private JLabel passLabel = new JLabel("Senha");
    private JTextField userField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton registerButton = new JButton("Registrar-se");
    private JButton enterButton = new JButton("Entrar");
    private LoginViewController control = new LoginViewController(this);
    
    public LoginView() {

        setSize(315, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Login");
        createView();
        setVisible(true);
        
    }

    private void createView() {

        add(south, "South");
        south.setBackground(new ColorUIResource(130, 188, 224));
        south.add(registerButton);
        south.add(enterButton);
        
        registerButton.setToolTipText("Cadastre um novo usuário (ALT + N)");
        registerButton.setMnemonic('N');
        registerButton.setName("register");
        registerButton.addActionListener(control);
        enterButton.setToolTipText("Entre com um usuário existente (ALT + E)");
        enterButton.setMnemonic('E');
        enterButton.setName("enter");
        enterButton.addActionListener(control);
        enterButton.addKeyListener(control);
        passwordField.addKeyListener(control);

        add(center, "Center");
        center.setBorder(BorderFactory.createTitledBorder("Login"));
        
        center.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        // cons.fill = GridBagConstraints.HORIZONTAL;
        cons.insets = new InsetsUIResource(7, 7, 7, 7);

        cons.gridx = 0;
        cons.gridy = 0;
        center.add(userLabel, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        center.add(userField, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        center.add(passLabel, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        center.add(passwordField, cons);

    }
    
    /**
     * Método responsável por guardar os dados digitados nos campos de texto
     * dentro de uma nova instância da classe Login.
     * O método retornará o objeto criado a partir da instância da classe Login com os dados 
     * vindos dos campos de texto.
     * @return
     */
    public Login getLogin() {

        Login log = new Login();
        
        log.setUser(userField.getText());
        String pass = new String(passwordField.getPassword());
        log.setPassword(pass);

        return log;
        
    }
}
