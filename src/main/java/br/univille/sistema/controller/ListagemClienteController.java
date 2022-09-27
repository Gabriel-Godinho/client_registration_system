package br.univille.sistema.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import br.univille.sistema.entity.Cliente;
import br.univille.sistema.service.ClientService;
import br.univille.sistema.view.FormularioCliente;
import br.univille.sistema.view.ListagemClientes;

public /* interface */ class ListagemClienteController implements ActionListener{    
    // INTERFACE - componente da liguagem orientada a objetos responsável pela padronização entre duas classes
    // Interface é responsável por ligar duas classes através de uma "assinatura"
    // Para uma classe utilizar o ActionListener é obrigatório ter o método abaixo escrito em 1995
    // Funciona como um contrato onde é necessário ter uma assinatura

    private ClientService service = new ClientService();

    private ListagemClientes list;
    
    public ListagemClienteController(ListagemClientes list) {

        this.list = list;

    }

    public ArrayList<Cliente> getAllClientes() {

        return service.getAllClientes();

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent clickedButton = (JComponent)e.getSource();

        switch (clickedButton.getName()) {
            case "btnNovo":
                btnNovoClick();
                break;

            case "btnAlterar":
                btnAlterarClick();
                break;
            
            case "btnExcluir":
                btnExcluirClick();
                break;
        }

    }
    
    private void btnNovoClick() {

        Cliente novoCliente = new Cliente();
        FormularioCliente form = new FormularioCliente(novoCliente);

        if (novoCliente.getId() != -1) {
            service.save(novoCliente);
        }
        form.getX();

        list.updateTable();

    }

    private void btnAlterarClick() {

        int index = list.rowNumber();

        if (index >= 0) {
            Cliente cliente = this.getAllClientes().get(index);
            FormularioCliente form = new FormularioCliente(cliente);
            service.save(cliente);
            list.updateTable();
            form.getX();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cliente para alterar!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void btnExcluirClick() {     

        int index = list.rowNumber();
        
        if (index >= 0) {
            Cliente cliente = this.getAllClientes().get(index);
            int response = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                service.remove(cliente);
                list.updateTable();
                JOptionPane.showMessageDialog(null, "Cliente removido!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um cliente para excluir!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }  

}
