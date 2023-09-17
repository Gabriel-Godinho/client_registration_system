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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;

import br.univille.login.controller.RegisterViewController;
import br.univille.login.entitys.Login;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class RegisterView extends JDialog{

    private final JPanel PANEL_SOUTH = new JPanel();
    private final JPanel PANEL_CENTER = new JPanel();
    private final JButton CONFIRM_BUTTON = new JButton("Registrar");
    private final JButton CANCEL_BUTTON = new JButton("Cancelar");
    private final JLabel USER_LABEL = new JLabel("Nome de usuário");
    private final JLabel PASSWORD_LABEL = new JLabel("Senha");
    private final JLabel CONFIRM_PASSWORD_LABEL = new JLabel("Confirme a senha");
    private final JTextField TXT_USER = new JTextField(15);
    private final JPasswordField PASSWORD_FIELD = new JPasswordField(15);
    private final JPasswordField CONFIRM_PASSWORD_FIELD = new JPasswordField(15);
    private final RegisterViewController CONTROLLER = new RegisterViewController(this);

    public RegisterView() {
        setSize(315, 300);
        setModal(true);
        setTitle("Novo usuário");
        setLocationRelativeTo(null);
        createView();
        setVisible(true);
    }

    private void createView() {
        add(PANEL_SOUTH, "South");
        PANEL_SOUTH.add(CONFIRM_BUTTON);
        PANEL_SOUTH.add(CANCEL_BUTTON);
        PANEL_SOUTH.setBackground(new ColorUIResource(130, 188, 224));
        CONFIRM_BUTTON.setName("confirm");
        CONFIRM_BUTTON.setToolTipText("Criar novo usuário");
        CONFIRM_BUTTON.addActionListener(CONTROLLER);
        CONFIRM_BUTTON.addKeyListener(CONTROLLER);
        CANCEL_BUTTON.setName("cancel");
        CANCEL_BUTTON.addActionListener(CONTROLLER);
        CANCEL_BUTTON.setMnemonic('C');
        CONFIRM_PASSWORD_FIELD.addKeyListener(CONTROLLER);
        add(PANEL_CENTER, "Center");
        PANEL_CENTER.setBorder(BorderFactory.createTitledBorder("Novo usuário"));
        PANEL_CENTER.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new InsetsUIResource(7, 7, 7, 7);

        cons.gridx = 0;
        cons.gridy = 0;
        PANEL_CENTER.add(USER_LABEL, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        PANEL_CENTER.add(TXT_USER, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        PANEL_CENTER.add(PASSWORD_LABEL, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        PANEL_CENTER.add(PASSWORD_FIELD, cons);

        cons.gridx = 0;
        cons.gridy = 2;
        PANEL_CENTER.add(CONFIRM_PASSWORD_LABEL, cons);

        cons.gridx = 1;
        cons.gridy = 2;
        PANEL_CENTER.add(CONFIRM_PASSWORD_FIELD, cons);
    }

    public Login getLogin() {
        Login log = new Login();
        log.setUser(TXT_USER.getText());
        String pass = new String(PASSWORD_FIELD.getPassword());
        String confirmPass = new String(CONFIRM_PASSWORD_FIELD.getPassword());

        if (confirmPass.equals(pass)) {
            log.setPassword(pass);
            return log;
        } 

        return null;
    }

}
