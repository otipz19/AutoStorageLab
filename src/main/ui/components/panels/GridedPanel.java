package main.ui.components.panels;

import javax.swing.*;
import java.awt.*;

public class GridedPanel extends JPanel {
    public GridedPanel(String labelText, JComponent component) {
        setLayout(new GridLayout(1, 2));
        add(new JLabel(labelText));
        add(component);
    }
}