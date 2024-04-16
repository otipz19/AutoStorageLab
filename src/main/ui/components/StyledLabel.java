package main.ui.components;

import main.ui.components.buttons.StyledButton;

import javax.swing.*;
import java.awt.*;

public class StyledLabel extends StyledButton {
    public StyledLabel(String label) {
        super(label);
        setEnabled(false);
        setForeground(new Color(0x203a54));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 10));
    }
}
