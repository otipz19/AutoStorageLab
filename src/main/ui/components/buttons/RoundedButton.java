package main.ui.components.buttons;

import main.ui.components.RoundedBorder;

import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton {
    private final int radius;

    public RoundedButton(String label, int radius) {
        super(label);
        this.radius = radius;
        setBorderPainted(false);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorder(new RoundedBorder(radius));
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        // Draw a rounded rectangle
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(g);
    }
}