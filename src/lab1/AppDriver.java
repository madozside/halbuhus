package lab1;

import java.util.Scanner;

public class AppDriver {





    public static void main(String[] args) {
        DateChecker checkDate;
        System.out.println("Hello to your todo list:\n" +
                "Please enter your username:");

        Scanner userNameInput = new Scanner(System.in);
        String userName = userNameInput.nextLine()+".txt";

        FileHandler test = new FileHandler(userName);
        test.start();

        System.out.println(test.todoList.keySet());




        //appGUI appList = new appGUI();

    }
}
