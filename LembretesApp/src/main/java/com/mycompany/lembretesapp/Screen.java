/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lembretesapp;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;
import javax.swing.*;



/**
 *
 * @author Christian
 */
public class Screen extends JFrame {
    private List<Lembrete> notes;   //Main list
    private JFrame frame;           //Main frame/Window
    private JPanel notePanel;

    BlocoDeLembretes b = new BlocoDeLembretes();




    public Screen() {
        notes = new ArrayList<>();
        notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));

        /* ########################################################### */
        //All buttons and your functions
        //Declare buttons
        JButton sortDate = new JButton("Sort by date");
        JButton addButton = new JButton("Add Note");
        JButton sortTitle = new JButton("Sort by title");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddNoteDialog();
            }
        });

        sortDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BlocoDeLembretes b = new BlocoDeLembretes();

                b.order(notes); // Pass the list of notes to be sorted
                updateNoteList(); // Update the UI with sorted notes
            }
        });

        sortTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.orderTitle(notes); // Pass the list of notes to be sorted
                updateNoteList(); // Update the UI with sorted notes
            }
        });

        //Button panel to organize the layout better
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(sortDate);
        buttonPanel.add(sortTitle);

        add(notePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(notePanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); //Open main window maximized
        setVisible(true);
    }



    /* ########################################################### */
    private void showAddNoteDialog() {      //Frame para quando clicar no add nova nota

        Calendar c = Calendar.getInstance();    //For get the system date

        JDialog dialog = new JDialog(frame, "Add Note", true);
        JDialog date = new JDialog(frame, "Add date", true);
        dialog.setResizable(true);
        date.setResizable(false);
        date.setSize(600,180);

        //TextFields
        JTextField titleTextField = new JTextField(20);
        JTextArea contentTextArea = new JTextArea(30, 30);
        JScrollPane scrollPane = new JScrollPane(contentTextArea);

        //Checkbox for custom date
        JCheckBox data = new JCheckBox("Enter date manually (Otherwise, use the system)");


        //Date parameters
        JTextField dayTextField = new JTextField(2);
        JTextField monthTextField = new JTextField(2);
        JTextField yearTextField = new JTextField(4);
        JPanel datePanel = new JPanel();

        datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));

        //Declaring the buttons
        JButton saveButton = new JButton("Save");
        JButton saveButtonDate = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        JPanel buttonDatePanel = new JPanel();
        buttonDatePanel.setLayout(new GridLayout(1, 2));
        buttonDatePanel.add(saveButtonDate, BorderLayout.WEST);
        buttonDatePanel.add(cancelButton, BorderLayout.EAST);

        //Fields of date window
        datePanel.add(new JLabel("Day:"));
        datePanel.add(dayTextField);
        datePanel.add(new JLabel("Month:"));
        datePanel.add(monthTextField);
        datePanel.add(new JLabel("Year:"));
        datePanel.add(yearTextField);
        datePanel.add(buttonDatePanel);
        dialog.add(data);
        date.add(datePanel);
        date.pack();



        /* ########################################################### */
        //All buttons and your functions

        //Date button action
        data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                date.setVisible(data.isSelected());
            }
        });

        //Button to save a custom date
        saveButtonDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dayText = dayTextField.getText();        //How to convert types
                String monthText = monthTextField.getText();
                String yearText = yearTextField.getText();

                if (dayText.isEmpty() || monthText.isEmpty() || yearText.isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Date cannot be empty", //Date cannot be empty message
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                else if(!checkDigit(dayText) || !checkDigit(monthText) || !checkDigit(yearText)) //Text if date is digit or char
                        JOptionPane.showMessageDialog(new JFrame(), "Please enter a digit, not a char",
                                "Error", JOptionPane.ERROR_MESSAGE);
                else {
                    
                    //Date fields receive the textFields content
                    int day = Integer.parseInt(dayText);
                    int month = Integer.parseInt(monthText);
                    int year = Integer.parseInt(yearText);
                    
                    
                    //if (day < 1 || day > 31 || (month == 2 && (day > 29 || (day > 28 && !isLeapYear(year)))) || month < 1 || month > 12 || year == 0)
                    if(!validDate(day, month, year))
                        JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid date",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    else
                        date.dispose();
                }
            }
        });

        //To not allow the date window to be closed and there is no date
        date.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Desmarque a checkBox de data
                data.setSelected(false);
            }
        });

        //Button for save the note in the list
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleTextField.getText().isEmpty())            //Isn't permitted a empty note
                {
                    JOptionPane.showMessageDialog(new JFrame(), "You need to digit a title", //Granted to digit a title
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String title = titleTextField.getText();                        //title receive textField title
                    String content = contentTextArea.getText();                     //String receive textField content

                    //If user insert manually date, receive the parameters
                    if (data.isSelected()) {

                        int day = Integer.parseInt(dayTextField.getText());
                        int month = Integer.parseInt(monthTextField.getText());
                        int year = Integer.parseInt(yearTextField.getText());
                        Data date = new Data(day, month, year);
                        notes.add(new Lembrete(title, content, date));
                        dialog.dispose();
                    }
                    else {
                        //If the user not insert manually the date, use date of system
                        Data date = new Data(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
                        notes.add(new Lembrete(title, content, date));
                        updateNoteList();
                        dialog.dispose();
                    }
                }
            }
        });

        //Button for cancel the add that list
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        // ########################################################### //
        // Panels and adds for that window

       //Panel for titles fields
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(new JLabel("Title: "), BorderLayout.WEST);
        titlePanel.add(titleTextField, BorderLayout.CENTER);
        titlePanel.add(data, BorderLayout.NORTH);


        //Panel for contents fields
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JLabel("Content: "), BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        //Button panel, add buttons in a separated panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        //Add in the main window(showNoteDialog)
        dialog.setLayout(new BorderLayout());
        dialog.add(titlePanel, BorderLayout.NORTH);
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setSize(800, 500);
        updateNoteList();   //Refresh note list
        dialog.dispose();
    }



    /* ########################################################### */
    //UPDATE LIST AFTER SAVE
    private void updateNoteList() {         //Att all time
        notePanel.removeAll();
        for (Lembrete note : notes) {       //For all notes
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


        /* ########################################################### */
        //NOTE DETAIL FRAME
        private void showNoteDetailDialog (Lembrete note){         //Detail a note that user click

            //New window with detail of that note
            JDialog dialog = new JDialog(frame, "Note Detail", true);
            dialog.setResizable(true);
            dialog.setLayout(new BorderLayout());

            // Date and Title
            JLabel dateLabel = new JLabel("Data: " + note.getData());
            JButton titleLabel = new JButton(note.getTitle());
            JPanel titleDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            titleDatePanel.add(titleLabel);
            titleDatePanel.add(dateLabel);

            // Content
            JLabel contentLabel = new JLabel("Content: ");
            JTextArea contentTextArea = new JTextArea(note.getDesc());
            contentTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(contentTextArea);
            JPanel contentPanel = new JPanel(new BorderLayout());
            contentPanel.add(contentLabel, BorderLayout.NORTH);
            contentPanel.add(scrollPane, BorderLayout.CENTER);

            JCheckBox editContent = new JCheckBox("Edit content");

            editContent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    contentTextArea.setEditable(editContent.isSelected());
                }
            });


            /* ########################################################### */
            //All buttons and your functions
            JButton cancelButton = new JButton("Cancel");
            JButton removeButton = new JButton("Delete");
            JButton saveButton = new JButton("Save");

            //Remove button - delete a note
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int response = JOptionPane.showConfirmDialog(
                            dialog,
                            "Are you sure you want to delete this note??", //Dialog for confirmation the remove
                            "Confirmation", //Title for the box
                            JOptionPane.YES_NO_OPTION //Options yes and not
                    );
                    //If the yes has selected, delete that note
                    if (response == JOptionPane.YES_OPTION) {
                        for (Lembrete l : notes) {
                            if (l.equals(note)) {
                                notes.remove(l);
                                dialog.dispose();
                                updateNoteList();
                                break;
                            }
                        }
                    }

                }
            });


            //close button for show note detail
            //Its possible to use that button or the "x" on the right top
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });

            //Save button for edits
            //If edit something save that changes
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    note.setDesc(contentTextArea.getText());
                    JOptionPane.showMessageDialog(new JFrame(), "Your changes have been saved!",
                            "Saved Changes", JOptionPane.WARNING_MESSAGE);
                    dialog.dispose();
                }
            });



            //Button Panel
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(removeButton);
            buttonPanel.add(cancelButton);
            buttonPanel.add(editContent);
            buttonPanel.add(saveButton);
            /* ########################################################### */
            //Add all components to the main window
            dialog.setResizable(true);
            dialog.add(titleDatePanel, BorderLayout.NORTH);
            dialog.add(contentPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.pack();
            dialog.setVisible(true);

        }

        //Function to test if it's a leap year
      private boolean isLeapYear (int year){
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /* ########################################################### */
    //Check if the date is valid
    private boolean validDate(int day, int month, int year)
    {
        return day >= 1 && day <= 31 && month >= 1 && month <= 12 &&
                (day <= 30 || month != 4) &&
                (day <= 30 || month != 6) &&
                (day <= 30 || month != 9) &&
                (day <= 30 || month != 11) &&
                (month != 2 || (day <= 29 && (day <= 28 || isLeapYear(year))))
                && year != 0;
    }
    private boolean checkDigit(String test)
    {
        int count = 0; 
        for(int i = 0; i < test.length(); i++)
        {
            if(Character.isDigit(test.charAt(i)))
                count++;
        }
        if(count != 0)
            return true;
        else
            return false;
    }
}