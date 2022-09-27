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
