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
import javax.swing.*;



/**
 *
 * @author Christian
 */
public class Screen extends JFrame{
    private List<Lembrete> notes;
    private JFrame frame;
    private JPanel notePanel;
<<<<<<< HEAD
    BlocoDeLembretes b = new BlocoDeLembretes();
=======
    private LocalDateTime date = LocalDateTime.now();

>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01

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
<<<<<<< HEAD

=======
                BlocoDeLembretes b = new BlocoDeLembretes();
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
                b.order(notes); // Pass the list of notes to be sorted
                updateNoteList(); // Update the UI with sorted notes
            }
        });
<<<<<<< HEAD

        JButton sortTitle = new JButton("Sort by title");
        sortTitle.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                b.orderTitle(notes); // Pass the list of notes to be sorted
                updateNoteList(); // Update the UI with sorted notes
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(sortDate);
        buttonPanel.add(sortTitle);
=======
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(sortDate);
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01

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
<<<<<<< HEAD
        Calendar c = Calendar.getInstance();
        JDialog dialog = new JDialog(frame, "Add Note", true);
        JDialog date = new JDialog(frame, "Add date", true);
        date.setResizable(true);
=======
        JDialog dialog = new JDialog(frame, "Add Note", true);
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
        JTextField titleTextField = new JTextField(20);
        JTextArea contentTextArea = new JTextArea(30, 30);
        JScrollPane scrollPane = new JScrollPane(contentTextArea);
        JCheckBox data = new JCheckBox("Digitar data manualmente(Caso contrario usa do sistema)");

<<<<<<< HEAD

=======
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
        //Date parameters
        JTextField dayTextField = new JTextField(2);
        JTextField monthTextField = new JTextField(2);
        JTextField yearTextField = new JTextField(4);
        JPanel datePanel = new JPanel();
<<<<<<< HEAD
        datePanel.setLayout(new GridLayout(4, 2));

        dialog.add(data);
        //Action for save button
        //datePanel.add(data);
        JButton saveButton = new JButton("Save");
        JButton saveButtonDate = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonPanelDate = new JPanel();


        datePanel.add(saveButtonDate);
        datePanel.add(cancelButton);
        datePanel.add(new JLabel("Day:"));
        datePanel.add(dayTextField);
        datePanel.add(new JLabel("Month:"));
        datePanel.add(monthTextField);
        datePanel.add(new JLabel("Year:"));
        datePanel.add(yearTextField);
        date.add(datePanel);
        datePanel.setVisible(true);

        //Date conditions
        data.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {

            if(data.isSelected())
                date.setVisible(true);
            else
                date.setVisible(false);
            }
        });

        saveButtonDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dayText = dayTextField.getText();
                String monthText = monthTextField.getText();
                String yearText = yearTextField.getText();

                if (dayText.isEmpty() || monthText.isEmpty() || yearText.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Date cannot be empty",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method, as the input is invalid
                }

                int day = Integer.parseInt(dayText);
                int month = Integer.parseInt(monthText);
                int year = Integer.parseInt(yearText);

                if(day < 1 || day > 31 || (month == 2 && (day > 29 || (day > 28 && !isLeapYear(year)))) || month < 1 || month > 12 || year == 0)
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid date",
                            "Error", JOptionPane.ERROR_MESSAGE);
                else
                    date.dispose();
            }
        });

=======
        datePanel.add(new JLabel("Day:"));
        datePanel.add(dayTextField);
        datePanel.add(new JLabel("Month:"));
        datePanel.add(monthTextField);
        datePanel.add(new JLabel("Year:"));
        datePanel.add(yearTextField);

        //Action for save button
        JButton saveButton = new JButton("Save");
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
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

<<<<<<< HEAD
                        if(data.isSelected()){

                            // Create a Data object with the entered date

                            int day = Integer.parseInt(dayTextField.getText());
                            int month = Integer.parseInt(monthTextField.getText());
                            int year = Integer.parseInt(yearTextField.getText());
                            Data date = new Data(day, month, year);
                            notes.add(new Lembrete(title, content, date));

                        }
                else{
                            Data date = new Data(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
                            notes.add(new Lembrete(title, content, date));
                        }


                    //notes.add(new Lembrete(title, content, date));

                updateNoteList();
=======

                    int day = Integer.parseInt(dayTextField.getText());
                    int month = Integer.parseInt(monthTextField.getText());
                    int year = Integer.parseInt(yearTextField.getText());

                    // Create a Data object with the entered date
                    Data date = new Data(day, month, year);

                    notes.add(new Lembrete(title, content, date));
                updateNoteList();                                                              
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
                dialog.dispose();
                }
            }
        });

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
<<<<<<< HEAD
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(new JLabel("Title: "), BorderLayout.WEST);
        titlePanel.add(titleTextField, BorderLayout.CENTER);
        dialog.add(titlePanel);

// Add content label and text area
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(new JLabel("Content: "), BorderLayout.WEST);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        dialog.add(contentPanel);
=======
        dialog.add(datePanel);
        dialog.add(new JLabel("Title:"));
        dialog.add(titleTextField);
        dialog.add(new JLabel("Content:"));
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
        dialog.add(scrollPane);
        //dialog.add(datePanel);
        dialog.add(buttonPanel);
<<<<<<< HEAD

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        // dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
=======
        dialog.setLayout(new GridLayout(4, 2));
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
        dialog.pack();
        dialog.setVisible(true);
        updateNoteList();
        dialog.dispose();
    }


    //UPDATE LIST AFTER SAVE
    private void updateNoteList() {         //Att all time
        notePanel.removeAll();
<<<<<<< HEAD
        for (Lembrete note : notes) {       //For all notes
=======
        for (Lembrete note : notes) {       //Para todas notas que existem .. 
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
            JButton noteButton = new JButton(note.getTitle());  //Button of title, if click this, open the details of that note
            noteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showNoteDetailDialog(note);
                }
            });
        noteButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align the button
        noteButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, noteButton.getPreferredSize().height)); // Set maximum width
            notePanel.add(noteButton);  //Add a panel contains the title
        }
        notePanel.revalidate();             //Refresh layout
        notePanel.repaint();                //Show alterations
    }


        //NOTE DETAIL FRAME
        private void showNoteDetailDialog(Lembrete note) {         //Detail a note that user click             
<<<<<<< HEAD
       /* JDialog dialog = new JDialog(frame, "Note Detail", true);
        dialog.setResizable(true);
        dialog.setLayout(new GridLayout(4, 2));*/
            JDialog dialog = new JDialog(frame, "Note Detail", true);
            dialog.setResizable(true);
            dialog.setLayout(new BorderLayout());

            // Título
            JButton titleLabel = new JButton(note.getTitle());
            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centraliza o título
            titlePanel.add(titleLabel);

            // Data
            JLabel dateLabel = new JLabel("Data: " + note.getData());

            // Conteúdo
            JPanel contentPanel = new JPanel();
            JLabel contentLabel = new JLabel("Content: ");
            JTextArea contentTextArea = new JTextArea(note.getDesc(), 10, 30); // Ajuste o número de linhas conforme necessário
            contentTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(contentTextArea);
            contentPanel.add(contentLabel, BorderLayout.EAST);
            contentPanel.add(contentTextArea, BorderLayout.WEST);
            //JScrollPane scrollPane = new JScrollPane(contentTextArea);
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Lembrete noteToRemove = note; //noteToRemove receive a open note

                    for (Lembrete l : notes) {
                        if (l.equals(noteToRemove)) {
                            notes.remove(l);
                            break;
                        }
                    }
                    dialog.dispose();
                    updateNoteList();
                }
            });
=======
        JDialog dialog = new JDialog(frame, "Note Detail", true);
        dialog.setResizable(true);
        JTextArea contentTextArea = new JTextArea(note.getDesc(), 40, 30);
        contentTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contentTextArea);
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01

        JButton closeButton = new JButton("Close");
        JButton editButton = new JButton("Edit note");
           //dialog.add(titleButton, BorderLayout.NORTH);

<<<<<<< HEAD
            //JButton titleButton = new JButton(note.getTitle());
            //JPanel titlePanel = new JPanel();
            //titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinha o botão à esquerda ##############
            //titlePanel.add(titleButton);
            //titleButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleButton.getPreferredSize().height));

=======
        JButton titleButton = new JButton(note.getTitle());
            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinha o botão à esquerda ##############
            titlePanel.add(titleButton);
            titleButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleButton.getPreferredSize().height));
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01

        //close button for show note detail
        //Its possible to use that button or the "x" on the right top 
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(removeButton);
            buttonPanel.add(closeButton);

            JPanel datePanel = new JPanel();
            //contentPanel.setLayout(new BorderLayout());
            datePanel.add(dateLabel);
            contentPanel.add(scrollPane, BorderLayout.CENTER);

            dialog.add(datePanel);
            dialog.add(titlePanel, BorderLayout.NORTH);
            dialog.add(contentPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);

            dialog.pack();
            dialog.setVisible(true);
        /* JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton, BorderLayout.WEST);
        buttonPanel.add(removeButton, BorderLayout.EAST);

        //JPanel titlePanel = new JPanel();
        //titlePanel.add(titleButton, BorderLayout.NORTH);
<<<<<<< HEAD
        //dialog.add(new JLabel("Title: " + note.getTitle()));        //Dialog for the frame ##############

=======
        //dialog.add(new JLabel("Title: " + note.getTitle()));        //Dialog for the frame ###############
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
        dialog.add(new JLabel("Date: " + note.getData()));
        dialog.add(new JLabel("Content:"));
        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(scrollPane);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        //dialog.add(removeButton);

<<<<<<< HEAD
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
=======
        dialog.setLayout(new GridLayout(4, 1));
>>>>>>> 8528216b0b7cb4bd73d42a5667c7454a97472a01
        //dialog.add(contentPanel);
        titlePanel.setVisible(true);
        dialog.pack();
        dialog.setVisible(true);
        */

    }
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
/*      JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(new JLabel("Title: "), BorderLayout.WEST);
        titlePanel.add(titleTextField, BorderLayout.CENTER);
        dialog.add(titlePanel);
 */
//add(addButton, BorderLayout.SOUTH);