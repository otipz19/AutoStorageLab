package main.ui.screens.allGroupsScreen.components;

import main.controllers.GroupsController;
import main.ui.components.buttons.StyledButton;

import java.awt.*;
import java.awt.event.ActionEvent;

public class DeleteGroupButton extends StyledButton {
    public DeleteGroupButton() {
        super("Delete Group");
        addActionListener(this::onClick);
        setPreferredSize(new Dimension(200, 50));
    }

    private void onClick(ActionEvent e){
        GroupsController.switchGroupDeleteMode();
        if (GroupsController.isGroupDeleteModeOn()) {
            setBackground(Color.decode("#334E88"));
        } else {
            setBackground(Color.WHITE);
        }
    }
}
