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

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.ColorUIResource;

import br.univille.registrationsystem.controller.ClientListController;
import br.univille.registrationsystem.model.ClientTableModel;

public class ClientList extends JFrame{

    private final JPanel panelSouth = new JPanel();
    private final JButton btnNew = new JButton("Novo");
    private final JButton btnUpdate = new JButton("Alterar");
    private final JButton btnDelete = new JButton("Excluir");
    private final JButton btnExit = new JButton("Sair do sistema");
    private final ClientListController controller = new ClientListController(this);
    private final ClientTableModel model = new ClientTableModel(controller); // Instância do controlador;
    private final JTable tabel = new JTable(model);
    
    public ClientList() {
        setSize(900, 700);
        setTitle("Listagem de Clientes");
        setLocationRelativeTo(null); // Cria a janela no meio da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createPanel();
        setVisible(true);
    }

    private void createPanel() {
        panelSouth.setLayout(new FlowLayout(FlowLayout.LEFT)); // FlowLayout - Coloca os componentes lado a lado
        add(panelSouth, "South");
        panelSouth.setBackground(new ColorUIResource(130, 188, 224));
        panelSouth.add(btnNew);
        panelSouth.add(btnUpdate);
        panelSouth.add(btnDelete);
        panelSouth.add(btnExit);

        // Configuração dos botões
        btnNew.addActionListener(controller);
        btnNew.setName("new");
        btnNew.setMnemonic('N');
        btnNew.setToolTipText("Criar um novo cliente (ALT + N)");
        btnUpdate.addActionListener(controller);
        btnUpdate.setName("update");
        btnUpdate.setMnemonic('A');
        btnUpdate.setToolTipText("Alterar um cliente (ALT + A)");
        btnDelete.addActionListener(controller);
        btnDelete.setName("delete");
        btnDelete.setMnemonic('E');
        btnDelete.setToolTipText("Excluir um cliente (ALT + E)");
        btnExit.addActionListener(controller);
        btnExit.setName("exit");
        btnExit.setToolTipText("Sair do programa");

        // Barra de rolagem VERTICAL
        JScrollPane panelCenter = new JScrollPane(tabel);
        panelCenter.setBackground(Color.lightGray);
        panelCenter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(panelCenter, "Center");
    }

    public void updateTable() {
        model.fireTableDataChanged();
    }

    public int rowNumber() {
        return tabel.getSelectedRow();
    }
}
