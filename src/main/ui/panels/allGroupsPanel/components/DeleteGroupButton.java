package main.ui.panels.allGroupsPanel.components;

import main.ui.GlobalStateManager;
import main.ui.components.buttons.StyledButton;

import java.awt.*;
import java.awt.event.ActionEvent;

public class DeleteGroupButton extends StyledButton {
    public DeleteGroupButton() {
        super("Delete Group");
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        GlobalStateManager.switchGroupDeleteMode();
        if (GlobalStateManager.isGroupDeleteModeOn()) {
            setBackground(Color.decode("#334E88"));
        } else {
            setBackground(Color.WHITE);
        }
    }
}
