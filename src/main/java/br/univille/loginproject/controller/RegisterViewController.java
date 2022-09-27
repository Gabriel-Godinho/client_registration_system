package br.univille.loginproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import br.univille.loginproject.entitys.Login;
import br.univille.loginproject.service.LoginService;
import br.univille.loginproject.view.RegisterView;

public class RegisterViewController implements ActionListener, KeyListener{

    private RegisterView rv;
    private LoginService service = new LoginService();

    public RegisterViewController(RegisterView rv) {

        this.rv = rv;

    }

    // Método da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent clicked = (JComponent)e.getSource();

        switch (clicked.getName()) {
            case "confirm":
                confirmButton();
                break;

            case "cancel":
                cancelButton();
                break;
        }   

    }

    // Método que será executado quando o botão Registrar for pressionado
    private void confirmButton() {

        Login login = rv.getLogin();

        try {
            if (service.compare(login.getUser())) {
                JOptionPane.showMessageDialog(null, "Usuário já existente!", "Ops!", JOptionPane.ERROR_MESSAGE);
            } else if (login.getUser().equals("") || login.getPassword().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Ops!", JOptionPane.INFORMATION_MESSAGE);
            } else if (!login.equals(null)) {
                service.register(login);
                JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                rv.dispose();
            }
        } catch (NullPointerException e) {           
            JOptionPane.showMessageDialog(null, "As senhas não são iguais!", "Ops!", JOptionPane.ERROR_MESSAGE);       
        }

    }

    // Método que será executado quando o botão Cancelar for pressionado
    private void cancelButton() {

        rv.dispose();

    }

    // Métodos da interface KeyListener
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            confirmButton();       
        }
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}    
}