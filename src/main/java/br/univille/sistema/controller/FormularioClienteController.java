package br.univille.sistema.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import br.univille.sistema.entity.Cliente;
import br.univille.sistema.exceptions.ValorInvalidoException;
import br.univille.sistema.view.FormularioCliente;

public class FormularioClienteController implements ActionListener, KeyListener{

    private FormularioCliente formcli;
    
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

        int response = JOptionPane.showConfirmDialog(null, "Tem certeza?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (response == JOptionPane.YES_OPTION) {
            try {
                var cliente = formcli.getCliente();
                formcli.update(cliente);
                this.valid(cliente);      
                formcli.dispose();
            } catch (ValorInvalidoException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void cancelarClick() {

        formcli.dispose();

    }

    private void valid(Cliente cliente) throws ValorInvalidoException{
        
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
