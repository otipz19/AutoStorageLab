package main.ui.screens.allGroupsScreen.components;

import main.ui.components.buttons.StyledButton;
import main.ui.App;

import java.awt.*;
import java.awt.event.ActionEvent;

public class AddGroupButton extends StyledButton {
    public AddGroupButton() {
        super("Add Group");
        addActionListener(this::onClick);
        setPreferredSize(new Dimension(200, 50));
    }

    private void onClick(ActionEvent e){
        App.goToGroupCreateScreen();
    }
}
