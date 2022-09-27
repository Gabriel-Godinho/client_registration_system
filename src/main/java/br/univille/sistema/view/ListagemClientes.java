package br.univille.sistema.view;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.ColorUIResource;

import br.univille.sistema.controller.ListagemClienteController;
import br.univille.sistema.model.TabelaClienteModel;

public class ListagemClientes extends JFrame{

    private JScrollPane panelCenter;
    private JPanel panelSouth = new JPanel();
    private JButton btnNovo = new JButton("Novo");
    private JButton btnAlterar = new JButton("Alterar");
    private JButton btnExcluir = new JButton("Excluir");
    private ListagemClienteController controller = new ListagemClienteController(this);
    private TabelaClienteModel model = new TabelaClienteModel(controller); // Instância do controlador;
    private JTable tabel = new JTable(model);
    
    public ListagemClientes() {
        setSize(900, 700);
        setTitle("Listagem de Clientes");
        setLocationRelativeTo(null); // Cria a janela no meio da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createPanel(); // Método
        setVisible(true);
    }

    public void updateTable() {
        model.fireTableDataChanged();
    }

    public int rowNumber() {
        return tabel.getSelectedRow();
    }

    private void createPanel() {
        panelSouth.setLayout(new FlowLayout(FlowLayout.LEFT)); // FlowLayout - altera a posição do Layout
        add(panelSouth, "South");
        panelSouth.setBackground(new ColorUIResource(187, 217, 194));
        panelSouth.add(btnNovo);
        panelSouth.add(btnAlterar);
        panelSouth.add(btnExcluir);

        // Adição do ActionListener aos botões
        btnNovo.addActionListener(controller);
        btnNovo.setName("btnNovo");
        btnNovo.setMnemonic('N');
        btnNovo.setToolTipText("Criar um novo cliente");
        btnAlterar.addActionListener(controller);
        btnAlterar.setName("btnAlterar");
        btnAlterar.setMnemonic('A');
        btnAlterar.setToolTipText("Alterar um cliente");
        btnExcluir.addActionListener(controller);
        btnExcluir.setName("btnExcluir");
        btnExcluir.setMnemonic('E');
        btnExcluir.setToolTipText("Excluir um cliente");

        // Barra de rolagem horizontal e vertical
        panelCenter = new JScrollPane(tabel);
        panelCenter.setBackground(Color.lightGray);
        //panelCenter.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelCenter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(panelCenter, "Center");
    }
}
