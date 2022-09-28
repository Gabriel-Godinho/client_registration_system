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
package br.univille.sistema.model;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import br.univille.sistema.controller.ListagemClienteController;

public class TabelaClienteModel extends AbstractTableModel{
    // Classes abstratas são classes que ao mesmo tempo são interfaces; classe e interface ao mesmo tempo

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private ListagemClienteController controller;

    public TabelaClienteModel(ListagemClienteController controller) {
        this.controller = controller;
    }

    @Override
    public int getRowCount() {        
        return controller.getAllClientes().size();
    }

    @Override
    public int getColumnCount() {        
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {       
        var umCliente = controller.getAllClientes().get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return umCliente.getId();
            
            case 1:
                return umCliente.getName();

            case 2:
                return umCliente.getCPF();

            case 3:
                return sdf.format(umCliente.getBirthDate());
            
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return new String[]{"Código", "Nome", "CPF", "Data de Nascimento"}[column];
    }    
}
