package main.controllers;

import javax.swing.*;

/**
 * BaseController is a utility class that provides common functionality across different controllers.
 * Currently, it provides a method to display exception messages in a dialog box.
 */
public class BaseController {

    /**
     * Displays a dialog box with the message from the provided exception.
     *
     * @param ex the exception whose message is to be displayed in the dialog box
     */
    public static void showExceptionMessage(Exception ex) {
        JOptionPane.showMessageDialog(
                null,
                ex.getMessage(),
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }
}