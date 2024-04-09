package main.ui.components.buttons;

import main.controllers.GroupsController;

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
