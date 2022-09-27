package br.univille.loginproject.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;

import br.univille.loginproject.controller.RegisterViewController;
import br.univille.loginproject.entitys.Login;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/* Construção da tela de registro de novo usuário*/

public class RegisterView extends JDialog{

    private JPanel south = new JPanel();
    private JPanel center = new JPanel();
    private JButton confirmButton = new JButton("Registrar");
    private JButton cancelButton = new JButton("Cancelar");
    private JLabel userLabel = new JLabel("Nome de usuário");
    private JLabel passLabel = new JLabel("Senha");
    private JLabel confirmpassLabel = new JLabel("Confirme a senha");
    private JTextField usertTextField = new JTextField(15);
    private JPasswordField pField = new JPasswordField(15);
    private JPasswordField confirmpField = new JPasswordField(15);
    private RegisterViewController control = new RegisterViewController(this);

    public RegisterView() {

        setSize(315, 300);
        setModal(true);
        setTitle("Novo usuário");
        setLocationRelativeTo(null);
        createView();
        setVisible(true);

    }

    private void createView() {

        add(south, "South");
        south.add(confirmButton);
        south.add(cancelButton);
        south.setBackground(new ColorUIResource(130, 188, 224));
        confirmButton.setName("confirm");
        confirmButton.setToolTipText("Criar novo usuário");
        confirmButton.addActionListener(control);
        confirmButton.addKeyListener(control);
        cancelButton.setName("cancel");
        cancelButton.addActionListener(control);
        cancelButton.setMnemonic('C');
        confirmpField.addKeyListener(control);
        
        add(center, "Center");
        center.setBorder(BorderFactory.createTitledBorder("Novo usuário"));
        center.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.insets = new InsetsUIResource(7, 7, 7, 7);

        cons.gridx = 0;
        cons.gridy = 0;
        center.add(userLabel, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        center.add(usertTextField, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        center.add(passLabel, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        center.add(pField, cons);

        cons.gridx = 0;
        cons.gridy = 2;
        center.add(confirmpassLabel, cons);

        cons.gridx = 1;
        cons.gridy = 2;
        center.add(confirmpField, cons);

    }
    
    /**
     * Método responsável por guardar os dados inseridos nos campos de texto 
     * dentro de uma nova instância da classe Login.
     * Se a senha inserida no campo Senha for igual à senha inserida no campo Confirmar Senha,
     * o método retornará o objeto criado a partir da instância da classe Login com os valores 
     * inseridos pelo usuário nos campos de texto.
     * Caso contrário, o método retornará null. 
     * @return
     */
    public Login getLogin() {

        Login log = new Login();

        log.setUser(usertTextField.getText());

        String pass = new String(pField.getPassword());
        String confirmPass = new String(confirmpField.getPassword());

        if (confirmPass.equals(pass)) {
            log.setPassword(pass);
            return log;
        } 
            
        return null;
                   
    }
}
