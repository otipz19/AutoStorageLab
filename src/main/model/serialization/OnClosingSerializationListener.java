package main.model.serialization;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OnClosingSerializationListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        DataSerializer.save();
    }
}
