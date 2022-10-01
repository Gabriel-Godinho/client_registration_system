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
package br.univille.sistema.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import br.univille.sistema.entity.Cliente;
import br.univille.sistema.exceptions.ValorInvalidoException;
import br.univille.sistema.service.ClientService;
import br.univille.sistema.view.FormularioCliente;

public class FormularioClienteController implements ActionListener, KeyListener{

    private FormularioCliente formcli;
    private ClientService service = new ClientService();
    
    public FormularioClienteController(FormularioCliente formcli) {

        this.formcli = formcli;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent clicked = (JComponent)e.getSource();

        switch (clicked.getName()) {
            case "ok":
                okClick();               
                break;
        
            case "cancelar":
                cancelarClick();
                break;
        }    

    }
    
    private void okClick() {
        
        try {
            var client = formcli.getCliente();
            formcli.update(client);
            validation(client);
            service.save(client);      
            formcli.dispose();
        } catch (ValorInvalidoException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void cancelarClick() {

        formcli.dispose();

    }

    private void validation(Cliente cliente) throws ValorInvalidoException{
        
        if (cliente.getName() == null || cliente.getName().isEmpty()) {
            throw new ValorInvalidoException("O nome não pode ser deixado em branco!", null, "NOME");
        }
        if (cliente.getCPF().isEmpty()) {
            throw new ValorInvalidoException("O CPF não pode ser deixado em branco!", null, "CPF");
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            okClick();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
