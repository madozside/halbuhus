/**
 * Carroll University
 * CSC341-A-SP2019
 * Hassan Albuhussain
 * Lab 1 - To Do List App
 */


package lab1;

import javax.swing.*;

/**
 * A date format checker to make sure the key value is always in date format
 */
public class DateChecker {

    private static final String ValidPattern =
            "([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
    private String newDate;

    /**
     * Check if the given date will match the correct format
     *
     * @param userDate the given date
     */
    public DateChecker(String userDate) {
        if (!userDate.matches(ValidPattern)) {
            JOptionPane.showMessageDialog(null, userDate + " is a wrong input!", "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Wrong Date");
        }
        newDate = userDate;
    }

    @Override
    public String toString() {
        return newDate;
    }


}
