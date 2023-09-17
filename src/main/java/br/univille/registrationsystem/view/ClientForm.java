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
package br.univille.registrationsystem.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.text.MaskFormatter;

import br.univille.registrationsystem.controller.ClientFormController;
import br.univille.registrationsystem.entity.Client;
import br.univille.registrationsystem.exceptions.InvalidValueException;

import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.GridBagConstraints;

public class ClientForm extends JDialog {
    
    private final JPanel PANEL_CENTER = new JPanel();
    private final JPanel PANEL_BUTTON = new JPanel();
    private final JButton OK_BUTTON = new JButton("OK");
    private final JButton CANCEL_BUTTON = new JButton("Cancelar");
    private final JTextField TXT_NOME = new JTextField(20);
    private final JTextField TXT_CPF = new JTextField(10);
    private JFormattedTextField txtBirth = new JFormattedTextField(12);
    private final ClientFormController CONTROL = new ClientFormController(this);
    private Client cliente = new Client();
    
    public Client getCliente() {
        return cliente;
    }

    public ClientForm(Client cliente) {
        this.cliente = cliente;
        TXT_NOME.setText(cliente.getName());
        TXT_CPF.setText(cliente.getCPF());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (cliente.getBirthDate() != null){
            txtBirth.setText(sdf.format(cliente.getBirthDate()));
        }
        
        setSize(500, 250);
        setTitle("Formulário");
        setModal(true);
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
    }

    private void createPanel() {
        add(PANEL_BUTTON, "South");
        PANEL_BUTTON.add(OK_BUTTON);
        OK_BUTTON.setName("ok");
        this.getRootPane().setDefaultButton(OK_BUTTON);
        OK_BUTTON.addActionListener(CONTROL);
        OK_BUTTON.addKeyListener(CONTROL);
        PANEL_BUTTON.add(CANCEL_BUTTON);
        CANCEL_BUTTON.setName("cancel");
        CANCEL_BUTTON.addActionListener(CONTROL);
        CANCEL_BUTTON.setMnemonic('C');
        txtBirth.addKeyListener(CONTROL);
        PANEL_BUTTON.setBackground(new ColorUIResource(130, 188, 224));
        add(PANEL_CENTER, "Center");
        PANEL_CENTER.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
        PANEL_CENTER.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new InsetsUIResource(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        PANEL_CENTER.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        PANEL_CENTER.add(TXT_NOME, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        PANEL_CENTER.add(new JLabel("CPF:"), gbc);
                
        gbc.gridx = 1;
        gbc.gridy = 2;
        PANEL_CENTER.add(TXT_CPF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        PANEL_CENTER.add(new JLabel("Data de nascimento:"), gbc);

        try {
            MaskFormatter maskBirth = new MaskFormatter("##/##/####");
            txtBirth = new JFormattedTextField(maskBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        gbc.gridx = 1;
        gbc.gridy = 3;
        PANEL_CENTER.add(txtBirth, gbc);
    }

    public Client update(Client cliente) throws InvalidValueException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        cliente.setName(TXT_NOME.getText());
        cliente.setCPF(TXT_CPF.getText());
        
        try {
            cliente.setBirthDate(sdf.parse(txtBirth.getText()));
        } catch (ParseException e) {
            throw new InvalidValueException("Data inválida!", e);
        }

        return cliente;
    }
}
