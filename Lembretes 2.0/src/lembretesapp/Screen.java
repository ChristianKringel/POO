/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lembretesapp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 *
 * @author Christian
 */
public class Screen extends JFrame{
    private List<Lembrete> notes;
    private JFrame frame;
    private JPanel notePanel;
    private LocalDateTime date = LocalDateTime.now();


    public Screen(){
    notes = new ArrayList<>();
        notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add Note");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { showAddNoteDialog();
            }
        });
        JButton sortDate = new JButton("Sort by date");
        sortDate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BlocoDeLembretes b = new BlocoDeLembretes();
                b.order(notes); // Pass the list of notes to be sorted
                updateNoteList(); // Update the UI with sorted notes
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(sortDate);

        add(notePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        //add(sortDate, BorderLayout.SOUTH);
        add(notePanel, BorderLayout.CENTER);
        //add(addButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(800, 500);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Abrir a janela principal maximizada
        setVisible(true);
    }


    private void showAddNoteDialog() {      //Frame para quando clicar no add nova nota
        JDialog dialog = new JDialog(frame, "Add Note", true);
        JTextField titleTextField = new JTextField(20);
        JTextArea contentTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(contentTextArea);

        //Date parameters
        JTextField dayTextField = new JTextField(2);
        JTextField monthTextField = new JTextField(2);
        JTextField yearTextField = new JTextField(4);
        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Day:"));
        datePanel.add(dayTextField);
        datePanel.add(new JLabel("Month:"));
        datePanel.add(monthTextField);
        datePanel.add(new JLabel("Year:"));
        datePanel.add(yearTextField);

        //Action for save button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(titleTextField.getText().isEmpty())            //Isn't permitted a empty note
                {
                    JOptionPane.showMessageDialog(new JFrame(), "You need to digit a title", //Granted to digit a title
                    "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                String title = titleTextField.getText();                        //title receive textField title
                String content = contentTextArea.getText();                     //String receive textField content


                    int day = Integer.parseInt(dayTextField.getText());
                    int month = Integer.parseInt(monthTextField.getText());
                    int year = Integer.parseInt(yearTextField.getText());

                    // Create a Data object with the entered date
                    Data date = new Data(day, month, year);

                    notes.add(new Lembrete(title, content, date));
                updateNoteList();                                                              
                dialog.dispose();
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {               //Action for cancel button
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });


        //Adds
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        dialog.add(datePanel);
        dialog.add(new JLabel("Title:"));
        dialog.add(titleTextField);
        dialog.add(new JLabel("Content:"));
        dialog.add(scrollPane);
        dialog.add(buttonPanel);
        dialog.setLayout(new GridLayout(4, 2));
        dialog.pack();
        dialog.setVisible(true);
    }


    //UPDATE LIST AFTER SAVE
    private void updateNoteList() {         //Atualiza toda vez que adiciona um novo lembrete 
        notePanel.removeAll();
        for (Lembrete note : notes) {       //Para todas notas que existem .. 
            JButton noteButton = new JButton(note.getTitle());  //Button of title, if click this, open the details of that note
            noteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showNoteDetailDialog(note);
                }
            });
        noteButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align the button
        noteButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, noteButton.getPreferredSize().height)); // Set maximum width
            notePanel.add(noteButton);  //Adiciona no painel o novo botao contendo o titulo
        }
        notePanel.revalidate();             //Atualiza o layout
        notePanel.repaint();                //Repaint do componente, para mostrar alterações
    }


        //NOTE DETAIL FRAME
        private void showNoteDetailDialog(Lembrete note) {         //Detail a note that user click             
        JDialog dialog = new JDialog(frame, "Note Detail", true);
        dialog.setResizable(true);
        JTextArea contentTextArea = new JTextArea(note.getDesc(), 40, 30);
        contentTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contentTextArea);

        JButton closeButton = new JButton("Close");
        JButton editButton = new JButton("Edit note");
           //dialog.add(titleButton, BorderLayout.NORTH);

        JButton titleButton = new JButton(note.getTitle());
            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinha o botão à esquerda ##############
            titlePanel.add(titleButton);
            titleButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleButton.getPreferredSize().height));

        //close button for show note detail
        //Its possible to use that button or the "x" on the right top 
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
            
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        //JPanel titlePanel = new JPanel();
        //titlePanel.add(titleButton, BorderLayout.NORTH);
        //dialog.add(new JLabel("Title: " + note.getTitle()));        //Dialog for the frame ###############
        dialog.add(new JLabel("Date: " + note.getData()));
        dialog.add(new JLabel("Content:"));
        dialog.add(scrollPane);
        dialog.add(buttonPanel);

        dialog.setLayout(new GridLayout(4, 1));
        //dialog.add(contentPanel);
        titlePanel.setVisible(true);
        dialog.pack();
        dialog.setVisible(true);
    }
}

//add(addButton, BorderLayout.SOUTH);