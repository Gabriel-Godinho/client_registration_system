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
package br.univille.sistema.view;

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

import br.univille.sistema.controller.FormularioClienteController;
import br.univille.sistema.entity.Cliente;
import br.univille.sistema.exceptions.ValorInvalidoException;

import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.GridBagConstraints;

public class FormularioCliente extends JDialog {
    
    private JPanel panelCenter = new JPanel();
    private JPanel panelButton = new JPanel();
    private JButton ok = new JButton("OK");
    private JButton cancelar = new JButton("Cancelar");
    private JTextField txtNome = new JTextField(20);
    private JTextField txtCpf = new JTextField(10);
    private JFormattedTextField txtBirth = new JFormattedTextField(12);
    private MaskFormatter maskBirth;
    private FormularioClienteController control = new FormularioClienteController(this);
    private Cliente cliente = new Cliente();   
    
    public Cliente getCliente() {

        return cliente;

    }

    public void setCliente(Cliente cliente) {

        this.cliente = cliente;

    }

    public FormularioCliente(Cliente cliente) {
        
        this.cliente = cliente;
        txtNome.setText(cliente.getName());
        txtCpf.setText(cliente.getCPF());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (cliente.getBirthDate() == null){
        } else {
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
            maskBirth = new MaskFormatter("##/##/####");
            txtBirth = new JFormattedTextField(maskBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelCenter.add(txtBirth, gbc);

    }

    public Cliente update(Cliente cliente) throws ValorInvalidoException{
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
        cliente.setName(txtNome.getText());
        cliente.setCPF(txtCpf.getText());
        
        try {
            cliente.setBirthDate(sdf.parse(txtBirth.getText()));
        } catch (ParseException e) {
            throw new ValorInvalidoException("Data inválida!", e, "DATA DE NASCIMENTO");
        }

        return cliente;

    }

}
