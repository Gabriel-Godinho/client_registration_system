package br.univille.loginproject.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import br.univille.loginproject.entitys.Login;
import br.univille.loginproject.service.LoginService;
import br.univille.loginproject.view.ItWorked;
import br.univille.loginproject.view.LoginView;
import br.univille.loginproject.view.RegisterView;

public class LoginViewController implements ActionListener, KeyListener{

    private LoginService ls = new LoginService();
    private LoginView lv;

    public LoginViewController(LoginView lv) {

        this.lv = lv;
        
    }

    // Método da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {

        JComponent clicked = (JComponent)e.getSource();

        switch(clicked.getName()){
            case "register":
                registerClick();
                break;
            
            case "enter":
                enterClick();
                break;
        }  

    }

    // Método que será executado quando o botão Registrar-se for pressionado
    private void registerClick() {

        RegisterView rview = new RegisterView();

    }

    // Método que será executado quando o botão Entrar for pressionado
    private void enterClick() {

        Login username = lv.getLogin();

        if (ls.enter(username)) {
            lv.setVisible(false);
            ItWorked worked = new ItWorked();
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não registrado!", "Ops!", JOptionPane.ERROR_MESSAGE);
        }

    }

    // Métodos da interface KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            enterClick();
        }

    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}    
}
