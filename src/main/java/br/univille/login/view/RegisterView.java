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

    private final JPanel south = new JPanel();
    private final JPanel center = new JPanel();
    private final JButton confirmButton = new JButton("Registrar");
    private final JButton cancelButton = new JButton("Cancelar");
    private final JLabel userLabel = new JLabel("Nome de usuário");
    private final JLabel passLabel = new JLabel("Senha");
    private final JLabel confirmpassLabel = new JLabel("Confirme a senha");
    private final JTextField usertTextField = new JTextField(15);
    private final JPasswordField pField = new JPasswordField(15);
    private final JPasswordField confirmpField = new JPasswordField(15);
    private final RegisterViewController control = new RegisterViewController(this);

    public RegisterView() {
        setSize(315, 300);
        setModal(true);
        setTitle("Novo usuário");
        setLocationRelativeTo(null);
        createView();
        setVisible(true);
    }

    private void createView() {
        add(south, "South");
        south.add(confirmButton);
        south.add(cancelButton);
        south.setBackground(new ColorUIResource(130, 188, 224));
        confirmButton.setName("confirm");
        confirmButton.setToolTipText("Criar novo usuário");
        confirmButton.addActionListener(control);
        confirmButton.addKeyListener(control);
        cancelButton.setName("cancel");
        cancelButton.addActionListener(control);
        cancelButton.setMnemonic('C');
        confirmpField.addKeyListener(control);
        add(center, "Center");
        center.setBorder(BorderFactory.createTitledBorder("Novo usuário"));
        center.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new InsetsUIResource(7, 7, 7, 7);

        cons.gridx = 0;
        cons.gridy = 0;
        center.add(userLabel, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        center.add(usertTextField, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        center.add(passLabel, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        center.add(pField, cons);

        cons.gridx = 0;
        cons.gridy = 2;
        center.add(confirmpassLabel, cons);

        cons.gridx = 1;
        cons.gridy = 2;
        center.add(confirmpField, cons);
    }

    public Login getLogin() {
        Login log = new Login();
        log.setUser(usertTextField.getText());
        String pass = new String(pField.getPassword());
        String confirmPass = new String(confirmpField.getPassword());

        if (confirmPass.equals(pass)) {
            log.setPassword(pass);
            return log;
        } 

        return null;
    }

}
