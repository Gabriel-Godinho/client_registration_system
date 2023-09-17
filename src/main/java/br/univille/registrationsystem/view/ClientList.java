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

    private final JPanel PANEL_SOUTH = new JPanel();
    private final JButton BTN_NEW = new JButton("Novo");
    private final JButton BTN_UPDATE = new JButton("Alterar");
    private final JButton BTN_DELETE = new JButton("Excluir");
    private final JButton BTN_EXIT = new JButton("Sair do sistema");
    private final ClientListController CONTROLLER = new ClientListController(this);
    private final ClientTableModel TABLE_MODEL = new ClientTableModel(CONTROLLER);
    private final JTable TABLE = new JTable(TABLE_MODEL);
    
    public ClientList() {
        setSize(900, 700);
        setTitle("Listagem de Clientes");
        setLocationRelativeTo(null); // Cria a janela no meio da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createPanel();
        setVisible(true);
    }

    private void createPanel() {
        PANEL_SOUTH.setLayout(new FlowLayout(FlowLayout.LEFT)); // FlowLayout - Coloca os componentes lado a lado
        add(PANEL_SOUTH, "South");
        PANEL_SOUTH.setBackground(new ColorUIResource(130, 188, 224));
        PANEL_SOUTH.add(BTN_NEW);
        PANEL_SOUTH.add(BTN_UPDATE);
        PANEL_SOUTH.add(BTN_DELETE);
        PANEL_SOUTH.add(BTN_EXIT);

        BTN_NEW.addActionListener(CONTROLLER);
        BTN_NEW.setName("new");
        BTN_NEW.setMnemonic('N');
        BTN_NEW.setToolTipText("Criar um novo cliente (ALT + N)");
        BTN_UPDATE.addActionListener(CONTROLLER);
        BTN_UPDATE.setName("update");
        BTN_UPDATE.setMnemonic('A');
        BTN_UPDATE.setToolTipText("Alterar um cliente (ALT + A)");
        BTN_DELETE.addActionListener(CONTROLLER);
        BTN_DELETE.setName("delete");
        BTN_DELETE.setMnemonic('E');
        BTN_DELETE.setToolTipText("Excluir um cliente (ALT + E)");
        BTN_EXIT.addActionListener(CONTROLLER);
        BTN_EXIT.setName("exit");
        BTN_EXIT.setToolTipText("Sair do programa");

        JScrollPane panelCenter = new JScrollPane(TABLE);
        panelCenter.setBackground(Color.lightGray);
        panelCenter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(panelCenter, "Center");
    }

    public void updateTable() {
        TABLE_MODEL.fireTableDataChanged();
    }

    public int rowNumber() {
        return TABLE.getSelectedRow();
    }

}
