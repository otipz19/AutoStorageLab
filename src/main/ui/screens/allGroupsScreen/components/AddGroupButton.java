package main.ui.screens.allGroupsScreen.components;

import main.ui.components.buttons.StyledButton;
import main.ui.App;

import java.awt.event.ActionEvent;

public class AddGroupButton extends StyledButton {
    public AddGroupButton() {
        super("Add Group");
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        App.goToGroupCreateScreen();
    }
}
