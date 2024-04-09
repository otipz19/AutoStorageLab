package main.ui.screens.allGroupsScreen.components;

import main.controllers.GroupsController;
import main.ui.components.buttons.StyledButton;

import java.awt.event.ActionEvent;

public class AddGroupButton extends StyledButton {
    public AddGroupButton() {
        super("Add Group");
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        GroupsController.createGroup();
    }
}
