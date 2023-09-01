/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lembretesapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class ScreenTest extends javax.swing.JFrame {
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Christian
 */
public class ScreenT extends JFrame {
    private List<Lembrete> notes;
    private JFrame frame;
    private JPanel notePanel;

    BlocoDeLembretes b = new BlocoDeLembretes();

    private LocalDateTime date = LocalDateTime.now();


    public ScreenT() {
        notes = new ArrayList<>();
        notePanel = new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));

        JButton addButton = new JButton("Add Note");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddNoteDialog();
            }
        });
        JButton sortDate = new JButton("Sort by date");
        sortDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BlocoDeLembretes b = new BlocoDeLembretes();

                b.order(notes); // Pass the list of notes to be sorted
                updateNoteList(); // Update the UI with sorted notes
            }
        });


        JButton sortTitle = new JButton("Sort by title");
        sortTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.orderTitle(notes); // Pass the list of notes to be sorted
                updateNoteList(); // Update the UI with sorted notes
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(addButton);
        buttonPanel.add(sortDate);
        buttonPanel.add(sortTitle);

        /*JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(sortDate);  */


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

        Calendar c = Calendar.getInstance();
        JDialog dialog = new JDialog(frame, "Add Note", true);
        JDialog date = new JDialog(frame, "Add date", true);
        date.setResizable(true);

        //JDialog dialog = new JDialog(frame, "Add Note", true);

        JTextField titleTextField = new JTextField(20);
        JTextArea contentTextArea = new JTextArea(30, 30);
        JScrollPane scrollPane = new JScrollPane(contentTextArea);
        JCheckBox data = new JCheckBox("Digitar data manualmente(Caso contrario usa do sistema)");


        //Date parameters
        JTextField dayTextField = new JTextField(2);
        JTextField monthTextField = new JTextField(2);
        JTextField yearTextField = new JTextField(4);
        JPanel datePanel = new JPanel();

        datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        //datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.Y_AXIS));

        dialog.add(data);
        //Action for save button
        //datePanel.add(data);
        JButton saveButton = new JButton("Salvar");
        JButton saveButtonDate = new JButton("Salvar");
        JButton cancelButton = new JButton("Cancelar");
        JPanel buttonDatePanel = new JPanel();
        buttonDatePanel.add(saveButtonDate, BorderLayout.EAST);
        buttonDatePanel.add(cancelButton, BorderLayout.WEST);

        datePanel.add(buttonDatePanel);

        /*JPanel dayPanel = new JPanel();
        dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.X_AXIS));
        dayPanel.add(new JLabel("Dia:"));
        dayPanel.add(dayTextField);

        JPanel monthPanel = new JPanel();
        monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.X_AXIS));
        monthPanel.add(new JLabel("Mês:"));
        monthPanel.add(monthTextField);

        JPanel yearPanel = new JPanel();
        yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.X_AXIS));
        yearPanel.add(new JLabel("Ano:"));
        yearPanel.add(yearTextField);
    */
        datePanel.add(new JLabel("Dia:"));
        datePanel.add(dayTextField);
        datePanel.add(new JLabel("Mês:"));
        datePanel.add(monthTextField);
        datePanel.add(new JLabel("Ano:"));
        datePanel.add(yearTextField);
        datePanel.add(buttonDatePanel);
        /*datePanel.add(dayPanel);
        datePanel.add(monthPanel);
        datePanel.add(yearPanel);
        datePanel.add(buttonDatePanel); */
        date.add(datePanel);
        //datePanel.setVisible(true);

        //Date conditions
        data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (data.isSelected())
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

                if (day < 1 || day > 31 || (month == 2 && (day > 29 || (day > 28 && !isLeapYear(year)))) || month < 1 || month > 12 || year == 0)
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid date",
                            "Error", JOptionPane.ERROR_MESSAGE);
                else
                    date.dispose();
            }
        });

        //Action for save button
        //JButton saveButton = new JButton("Save");

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

                    if (data.isSelected()) {
                        int day = Integer.parseInt(dayTextField.getText());
                        int month = Integer.parseInt(monthTextField.getText());
                        int year = Integer.parseInt(yearTextField.getText());
                        Data date = new Data(day, month, year);
                        notes.add(new Lembrete(title, content, date));
                    }
                    else {
                        Data date = new Data(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
                        notes.add(new Lembrete(title, content, date));
                        updateNoteList();
                        dialog.dispose();
                    }
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
        dialog.add(new JLabel("Title:"));
        dialog.add(titleTextField);
        dialog.add(new JLabel("Content:"));
        dialog.add(scrollPane);
        dialog.add(buttonPanel);

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        // dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.setLayout(new GridLayout(4, 2));
        dialog.pack();
        dialog.setVisible(true);
        updateNoteList();
        dialog.dispose();
    }


    //UPDATE LIST AFTER SAVE
    private void updateNoteList() {         //Att all time
        notePanel.removeAll();
        for (Lembrete note : notes) {       //For all notes

                   //Para todas notas que existem ..

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
        private void showNoteDetailDialog (Lembrete note){         //Detail a note that user click

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

            //JDialog dialog = new JDialog(frame, "Note Detail", true);
            dialog.setResizable(true);
            //JTextArea contentTextArea = new JTextArea(note.getDesc(), 40, 30);
            contentTextArea.setEditable(false);
            //JScrollPane scrollPane = new JScrollPane(contentTextArea);


            JButton closeButton = new JButton("Close");
            JButton editButton = new JButton("Edit note");
            //dialog.add(titleButton, BorderLayout.NORTH);


            //JButton titleButton = new JButton(note.getTitle());
            //JPanel titlePanel = new JPanel();
            //titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Alinha o botão à esquerda ##############
            //titlePanel.add(titleButton);
            //titleButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, titleButton.getPreferredSize().height));


            JButton titleButton = new JButton(note.getTitle());
            //JPanel titlePanel = new JPanel();
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


        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

        dialog.setLayout(new GridLayout(4, 1));

        //dialog.add(contentPanel);
        titlePanel.setVisible(true);
        dialog.pack();
        dialog.setVisible(true);
        */

        }
      private boolean isLeapYear ( int year){
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

    /**
     * Creates new form NewJFrame
     */
    public ScreenTest() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScreenTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScreenTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScreenTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScreenTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ScreenTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame jFrame1;
    // End of variables declaration//GEN-END:variables
}
