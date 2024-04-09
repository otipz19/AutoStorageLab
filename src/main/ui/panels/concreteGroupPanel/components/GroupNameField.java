package main.ui.panels.concreteGroupPanel.components;

import javax.swing.*;
import java.awt.*;

public class GroupNameField extends JTextField {
    public GroupNameField(){
        setBackground(Color.WHITE);
        setForeground(new Color(0x203a54));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setHorizontalAlignment(SwingConstants.CENTER);
        setEditable(false);
    }
}
