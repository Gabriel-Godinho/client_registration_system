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
package br.univille.registrationsystem.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import br.univille.registrationsystem.entity.Client;
import br.univille.registrationsystem.service.ClientService;
import br.univille.registrationsystem.view.ClientForm;
import br.univille.registrationsystem.view.ClientList;

public class ClientListController implements ActionListener{

    private ClientService service = new ClientService();
    private ClientList list;
    
    public ClientListController(ClientList list) {

        this.list = list;

    }

    public ArrayList<Client> getAllClients() {

        return service.getAllClients();

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent clickedButton = (JComponent)e.getSource();

        switch (clickedButton.getName()) {
            case "new":
                btnNewClick();
                break;

            case "update":
                btnUpdateClick();
                break;
            
            case "delete":
                btnDeleteClick();
                break;
            case "exit":
                btnExitClick();
                break;
        }

    }
    
    private void btnNewClick() {

        Client newClient = new Client();
        ClientForm form = new ClientForm(newClient);

        if (newClient.getId() != -1) {
            service.save(newClient);
        }

        form.getX();

        list.updateTable();

    }

    private void btnUpdateClick() {

        int index = list.rowNumber();

        if (index >= 0) {
            Client client = this.getAllClients().get(index);
            ClientForm form = new ClientForm(client);

            service.save(client);
            list.updateTable();
            form.getX();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cliente para alterar!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void btnDeleteClick() {     

        int index = list.rowNumber();
        
        if (index >= 0) {
            Client client = this.getAllClients().get(index);

            int response = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                service.remove(client);
                list.updateTable();
                JOptionPane.showMessageDialog(null, "Cliente removido!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cliente para excluir!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }
    
    private void btnExitClick() {
        int response = JOptionPane.showConfirmDialog(null, "Deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (response == JOptionPane.YES_OPTION)
            System.exit(0);

    }

}
