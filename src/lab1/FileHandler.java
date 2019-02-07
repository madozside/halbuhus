/**
 * Carroll University
 * CSC341-A-SP2019
 * Hassan Albuhussain
 * Lab 1 - To Do List App
 */

package lab1;


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * The main file handler and list operator
 * @param <K> the key will always be the date
 * @param <V> the value is the list input
 */
public class FileHandler<K extends Comparable<? super K>, V> {
    public HashMap<String, String> todoList;
    private File file;

    /**
     * Construcer method to init the file given by the user
     * @param fileName passing the file name
     */
    public FileHandler(String fileName) {

        todoList = new HashMap<>();
        file = new File(fileName);


    }

    /**
     * A method to call read file and confirm
     * if the file is not empty
     * Else, will create one with the given name
     * @return true is the file is loaded
     */
    public boolean start() {

        if (file.length() == 0) {

            LocalDate convertedCurrentTime = LocalDate.now();
            String currentDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(convertedCurrentTime);
            todoList.put(currentDate,"Created new todo list!");
        }
        readFile(file);
        return true;
    }

    /**
     * A method to read a given file and valid it
     * @param sourceFile user file
     */
    private void readFile(File sourceFile) {
        try (BufferedReader bf = new BufferedReader(new FileReader(sourceFile))) {

            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] tokens = line.split(":", 2);
                if (tokens.length >= 2) {
                    todoList.put(tokens[0], tokens[1]);
                } else {
                    System.err.println("Invalid formatting in source file: "
                            + line);
                }
            }

        } catch (IOException e) {
            System.err.println("File Not Found");
        }
    }

    /**
     *  A search method to look for the values
     *  In our case is the to do list
     *  Key should be passed to this method
     *  as the correct date format
     * @param date the key
     * @return the value
     */
    public String searchList(String date) {
        if (todoList.get(date) == null) {
            return "Not found";
        }
        return todoList.get(date).toString();
    }

    /**
     * A save method to save
     * and update the current file
     */
    public void save() {
        BufferedWriter bw = null;
        PrintWriter fw = null;


        try {

            fw = new PrintWriter(file);
            bw = new BufferedWriter(fw);
            for (Map.Entry<String, String> entry : todoList.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue());
                bw.newLine();

            }
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }

        }
    }


}
