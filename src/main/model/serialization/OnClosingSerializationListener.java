package main.model.serialization;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The OnClosingSerializationListener class is a custom WindowAdapter that saves the application state when the window is closing.
 */
public class OnClosingSerializationListener extends WindowAdapter {
    /**
     * This method is called when the window is in the process of being closed.
     * It calls the save method of the DataSerializer class to save the current state of the application.
     *
     * @param e the window event
     */
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        DataSerializer.save();
    }
}