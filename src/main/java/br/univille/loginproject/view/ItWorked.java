package br.univille.loginproject.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

/* Construção da tela final */

public class ItWorked extends JFrame {

    private JPanel center = new JPanel();

    public ItWorked() {

        setSize(550, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("It worked!");
        createView();
        setVisible(true);
    }

    private void createView() {

        add(center, "Center");

        ImageIcon image = new ImageIcon(getClass().getResource("./img/1qfbpz.jpg"));
        JLabel label = new JLabel(image);
        
        center.add(label);
        center.setBackground(new ColorUIResource(130, 188, 224));
    }
}
