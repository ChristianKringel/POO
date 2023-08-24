package lembretesapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame {
    public Screen()
    {
        JLabel l = new JLabel();
        JButton button1 = new JButton("Sort by Date");      //3 buttons for main menu(screen)
        JButton button2 = new JButton("Alphabetical ordering");
        JButton button3 = new JButton("Add new annotation");



        //Screen code
        setVisible(true);
        setTitle("Lembretes APP 2.0");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(null);
        l.setFont(new Font("Arial", Font.PLAIN, 20));
        l.setBounds(20, 100, 90, 60);

        //Buttons code



        //Add all objects
        add(l);
        add(button1);
        add(button2);
        add(button3);
    }



/*
    @Override
    private void button1(ActionEvent actionEvent)     //For button n1
    {
        //JOptionPane.showMessageDialog(null, "VOCE CLICOU NO BOTAO PROIBIDO!!!",
            //    "Teste", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    private void button3(ActionEvent actionEvent)     //For button n2
    {
        //JOptionPane.showMessageDialog(null, "VOCE CLICOU NO BOTAO PROIBIDO!!!",
        //    "Teste", JOptionPane.ERROR_MESSAGE);
    }
    @Override
    private void button2(ActionEvent actionEvent)     //For button n3
    {
        //JOptionPane.showMessageDialog(null, "VOCE CLICOU NO BOTAO PROIBIDO!!!",
        //    "Teste", JOptionPane.ERROR_MESSAGE);
    }
    */

}
