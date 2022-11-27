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
    
    private final JPanel panelCenter = new JPanel();
    private final JPanel panelButton = new JPanel();
    private final JButton ok = new JButton("OK");
    private final JButton cancelar = new JButton("Cancelar");
    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtCpf = new JTextField(10);
    private JFormattedTextField txtBirth = new JFormattedTextField(12);
    private final ClientFormController control = new ClientFormController(this);
    private Client cliente = new Client();
    
    public Client getCliente() {

        return cliente;

    }

    public ClientForm(Client cliente) {
        
        this.cliente = cliente;
        txtNome.setText(cliente.getName());
        txtCpf.setText(cliente.getCPF());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (cliente.getBirthDate() != null){
            txtBirth.setText(sdf.format(cliente.getBirthDate()));
        }
        
        setSize(500, 250);
        setTitle("Formulário");
        setModal(true); // Bloquear o usuário
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);

    }

    private void createPanel() {

        add(panelButton, "South");
        panelButton.add(ok);
        ok.setName("ok");
        this.getRootPane().setDefaultButton(ok);
        ok.addActionListener(control);
        ok.addKeyListener(control);
        panelButton.add(cancelar);
        cancelar.setName("cancelar");
        cancelar.addActionListener(control);
        cancelar.setMnemonic('C');
        txtBirth.addKeyListener(control);

        panelButton.setBackground(new ColorUIResource(187, 217, 194));        
        
        add(panelCenter, "Center");
        panelCenter.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
        panelCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new InsetsUIResource(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCenter.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCenter.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCenter.add(new JLabel("CPF:"), gbc);
                
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelCenter.add(txtCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCenter.add(new JLabel("Data de nascimento:"), gbc);

        try {
            MaskFormatter maskBirth = new MaskFormatter("##/##/####");
            txtBirth = new JFormattedTextField(maskBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelCenter.add(txtBirth, gbc);

    }

    public Client update(Client cliente) throws InvalidValueException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
        cliente.setName(txtNome.getText());
        cliente.setCPF(txtCpf.getText());
        
        try {
            cliente.setBirthDate(sdf.parse(txtBirth.getText()));
        } catch (ParseException e) {
            throw new InvalidValueException("Data inválida!", e);
        }

        return cliente;

    }

}
