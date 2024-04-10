package main.ui.screens.allGroupsScreen.components;

import main.ui.components.buttons.StyledButton;

import javax.swing.*;
import java.awt.*;

public class GroupsLabelButton extends StyledButton {
    public GroupsLabelButton() {
        super("Groups");
        setEnabled(false);
        setForeground(new Color(0x203a54));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
