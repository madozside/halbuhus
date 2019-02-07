/**
 * Carroll University
 * CSC341-A-SP2019
 * Hassan Albuhussain
 * Lab 1 - To Do List App
 */


package lab1;

import java.util.Scanner;

/**
 * This the main console operator
 */
public class AppDriver {


    public static void main(String[] args) {
        System.out.println("Hello to your todo list:\n" +
                "Please enter your username:");

        Scanner userNameInput = new Scanner(System.in);
        String userName = userNameInput.nextLine() + ".txt";
        //Handle the file name
        FileHandler userFile = new FileHandler(userName);
        userFile.start();


        boolean leave;

        do {
            leave = false;
            System.out.println("Please choose from those options:\n" +
                    "[1] to show all the dates from your list\n" +
                    "[2] to show one date from the list\n" +
                    "[3] to add a date to your list\n" +
                    "[4] to update a list\n" +
                    "[5] to save all the changes\n" +
                    "[0] to exit");
            Scanner caseInput = new Scanner(System.in);
            int in = caseInput.nextInt();

            switch (in) {
                case 1:
                    System.out.println(userFile.todoList.keySet());
                    break;
                case 2:
                    System.out.println("Please enter the date from the list: \n" + userFile.todoList.keySet());
                    Scanner dateInput = new Scanner(System.in);
                    String date = dateInput.nextLine();
                    if (!date.isEmpty()) {
                        String textFormat = userFile.searchList(date);
                        System.out.println("=============\nOn " + date + " you have:\n" + textFormat.replace("|", "\n") + "\n=============");
                    }
                    break;
                case 3:
                    System.out.println("Please type the date as the following format [dd/MM/yyyy]: ");
                    Scanner scanDate = new Scanner(System.in);
                    String addDate = scanDate.nextLine();
                    System.out.println("Please type your todo list and use | to separate each item: ");
                    Scanner scanList = new Scanner(System.in);
                    String addList = scanList.nextLine();
                    userFile.todoList.put(addDate, addList);
                    break;
                case 4:
                    System.out.println("Available dates in your list: " + userFile.todoList.keySet());
                    System.out.println("Please type the date as the following format [dd/MM/yyyy]: ");
                    Scanner scanUpdate = new Scanner(System.in);
                    String updateDate = scanUpdate.nextLine();
                    if (!updateDate.isEmpty()) {
                        System.out.println("Your todo list for [" + updateDate + "]:");
                        String textFormat = (String) userFile.todoList.get(updateDate);
                        System.out.println("===========\n" + textFormat + "\n===========");

                        System.out.println("Please type your updated list separate each item with | :");
                        Scanner updateScan = new Scanner(System.in);
                        String updateList = updateScan.nextLine();
                        userFile.todoList.put(updateDate, updateList);

                        break;

                    }
                case 5:
                    userFile.save();
                    break;
                case 0:
                    leave = true;
                    break;
            }

        } while (!leave);


    }
}
