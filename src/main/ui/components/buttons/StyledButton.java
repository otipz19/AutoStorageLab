package main.ui.components.buttons;

import main.ui.components.RoundedBorder;

import javax.swing.*;
import java.awt.*;

public class StyledButton extends RoundedButton {
    public StyledButton(String label) {
        super(label, 20);
        setBorder(new RoundedBorder(10));
        setFont(new Font("Arial", Font.PLAIN, 20));
        setBackground(Color.WHITE);
        setForeground(new Color(0x628eba));
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        setContentAreaFilled(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setFocusPainted(false);
    }
}