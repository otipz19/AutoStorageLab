package main.controllers;

import javax.swing.*;

public class BaseController {
    public static void showExceptionMessage(RuntimeException ex) {
        JOptionPane.showMessageDialog(
                null,
                ex.getMessage(),
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }
}