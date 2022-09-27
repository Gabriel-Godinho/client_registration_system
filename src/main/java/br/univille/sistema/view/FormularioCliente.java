package br.univille.sistema.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.text.MaskFormatter;

import br.univille.sistema.controller.FormularioClienteController;
import br.univille.sistema.entity.Cliente;
import br.univille.sistema.exceptions.ValorInvalidoException;

import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.GridBagConstraints;

public class FormularioCliente extends JDialog {
    
    private JPanel panelCenter = new JPanel();
    private JPanel panelButton = new JPanel();
    private JButton ok = new JButton("OK");
    private JButton cancelar = new JButton("Cancelar");
    private JTextField txtid = new JTextField(8);
    private JTextField txtNome = new JTextField(20);
    private JTextField txtCpf = new JTextField(10);
    private JFormattedTextField txtBirth = new JFormattedTextField(12);
    //private MaskFormatter maskCpf;
    private MaskFormatter maskBirth;

    private FormularioClienteController control = new FormularioClienteController(this);
    private Cliente cliente = new Cliente();   
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormularioCliente(Cliente cliente) {
        
        this.cliente = cliente;
        txtid.setText(String.valueOf(cliente.getId()));
        txtNome.setText(cliente.getName());
        txtCpf.setText(cliente.getCPF());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (cliente.getBirthDate() == null){
        } else {
            txtBirth.setText(sdf.format(cliente.getBirthDate()));
        }
        
        setSize(500, 250);
        setTitle("Formulário");
        setModal(true); // Bloquear o usuário
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
    }

    private void createPanel() {
        add(panelButton, "South");
        panelButton.add(ok);
        ok.setName("ok");
        this.getRootPane().setDefaultButton(ok);
        ok.addActionListener(control);
        ok.addKeyListener(control);
        panelButton.add(cancelar);
        cancelar.setName("cancelar");
        cancelar.addActionListener(control);
        cancelar.setMnemonic('C');
        txtBirth.addKeyListener(control);

        panelButton.setBackground(new ColorUIResource(187, 217, 194));        
        
        add(panelCenter, "Center");
        panelCenter.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
        panelCenter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new InsetsUIResource(5, 5, 5, 5);

        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 0; // Linha 0
        // gbc.weightx = 0.20; // Largura em percentual
        panelCenter.add(new JLabel("ID:"), gbc);
         gbc.gridx = 1; // Coluna 1
        gbc.gridy = 0; // Linha 0
        // gbc.weightx = 0.80; // Largura em percentual
        panelCenter.add(txtid, gbc); 

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCenter.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCenter.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelCenter.add(new JLabel("CPF:"), gbc);
        
        // Foi necessário retirar a máscara
        /*try {
            maskCpf = new MaskFormatter("###.###.###-##");
            txtCpf = new JFormattedTextField(maskCpf);
        } catch (ParseException e) {
            e.printStackTrace();
        } */
                
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelCenter.add(txtCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panelCenter.add(new JLabel("Data de nascimento:"), gbc);

        try {
            maskBirth = new MaskFormatter("##/##/####");
            txtBirth = new JFormattedTextField(maskBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        gbc.gridx = 1;
        gbc.gridy = 3;
        panelCenter.add(txtBirth, gbc);
    }

    public Cliente update(Cliente cliente) throws ValorInvalidoException{
        // Atualiza com os dados da tela (binding)
        
        try {
            cliente.setId(Long.parseLong(txtid.getText()));
        } catch (NumberFormatException e) {
            throw new ValorInvalidoException("O Campo ID deve conter somente números!", e, "ID");
        }
               
        cliente.setName(txtNome.getText());
        cliente.setCPF(txtCpf.getText());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            cliente.setBirthDate(sdf.parse(txtBirth.getText()));
        } catch (ParseException e) {
            throw new ValorInvalidoException("Data inválida!", e, "DATA DE NASCIMENTO");
        }

        return cliente;
    }
}
