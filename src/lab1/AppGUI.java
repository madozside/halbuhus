/**
 * Carroll University
 * CSC341-A-SP2019
 * Hassan Albuhussain
 * Lab 1 - To Do List App
 */


package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Set;

/**
 * Main app using GUI
 */
public class AppGUI extends JPanel {


    static FileHandler fileInput;
    static JComboBox datesDropList;

    public static void main(String[] args) {

        //User input
        String userFile = JOptionPane.showInputDialog(null, "Please Enter The File Name");


        //File handler to deal with the data inputs from the given file
        fileInput = new FileHandler(userFile + ".txt");
        fileInput.start();
        dropdownList();

        //Dropdown list that contains all the dates were list the given file


        JPanel datePanel = new JPanel();
        JLabel dateFormat = new JLabel("Please note that the date format should be: dd/MM/YYYY");
        dateFormat.setForeground(Color.lightGray);
        JLabel dateLabel = new JLabel("Enter a date:");
        JTextField date = new JTextField(10);

        datePanel.add(dateLabel);
        datePanel.add(date);

        JPanel controlPanel = new JPanel();
        JButton updateBTN = new JButton("Update");
        controlPanel.add(updateBTN);


        JPanel textArea = new JPanel();
        JLabel listLable = new JLabel("Your list:");
        JTextArea textUpdates = new JTextArea(5, 30);
        textArea.add(listLable);
        textArea.add(textUpdates);

        JButton saveBTN = new JButton("Save");
        JButton infoBTN = new JButton("?");

        JLabel saveNote = new JLabel("Don't Forgot to Save Your Work");
        saveNote.setForeground(Color.RED);
        JPanel appPanel = new JPanel(new FlowLayout());
        appPanel.add(dateFormat);
        appPanel.add(datesDropList);
        appPanel.add(datePanel);
        appPanel.add(controlPanel);
        appPanel.add(textArea);
        appPanel.add(saveNote);
        appPanel.add(saveBTN);
        appPanel.add(infoBTN);

        //Item listener to detect any updates to the dropdown list
        ItemListener listSelect = new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String s = (String) datesDropList.getSelectedItem();
                    date.setText(s);
                    String h = fileInput.searchList(s);
                    String replaceForLine = h.replace("|", "\n");
                    textUpdates.setText(replaceForLine);
                }


            }
        };

        //Action listener to preform the updates to the given dates
        ActionListener updateAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = date.getText();
                DateChecker dateChecker = new DateChecker(userInput);
                String textInput = textUpdates.getText();
                fileInput.todoList.put(userInput, textInput.replace("\n"," | "));
                datesDropList.addItem(date.getText());


            }
        };

        ActionListener infoAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Created by Hassan Albuhussain\n" +
                        "Carroll University 2019\n" +
                        "halbuhus@pio.carrollu.edu");
            }
        };

        //Action listener to the save button to save any updates to the file
        ActionListener saveAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileInput.save();
            }
        };

        //A method to create a new to do list and write it to the text area
        if (!datesDropList.getSelectedItem().toString().isEmpty()) {
            String newDate = datesDropList.getSelectedItem().toString();
            String h = fileInput.searchList(newDate);
            String replaceForLine = h.replace("|", "\n");
            textUpdates.setText(replaceForLine);

        }

        //adding the actions/functions to buttons and dropdown list
        saveBTN.addActionListener((saveAction));
        updateBTN.addActionListener((updateAction));
        datesDropList.addItemListener(listSelect);
        infoBTN.addActionListener(infoAction);

        //Finalize the GUI
        JFrame frame = new JFrame("Todo List");
        frame.setContentPane(appPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setVisible(true);
        frame.setResizable(false);


    }

    /**
     * A method to handle the drop list in the GUI
     */
    private static void dropdownList() {
        String[] datesList = new String[fileInput.todoList.size()];
        int i = 0;
        Set<String> set = fileInput.todoList.keySet();
        for (String s : set)
            datesList[i++] = (String) s;
        datesDropList = new JComboBox(datesList);


    }


}
