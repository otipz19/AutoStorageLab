package main.ui.screens.groupScreen.components;

import main.Icons;
import main.controllers.GroupsController;
import main.ui.App;
import main.ui.components.buttons.RoundedButton;

import java.awt.*;
import java.awt.event.ActionEvent;

public class EditGroupDescriptionButton extends RoundedButton {
    public EditGroupDescriptionButton() {
        super("", 10);
        setBackground(Color.WHITE);
        setIcon(Icons.buildPenIcon(20, 20));
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        var descriptionArea = App.getGroupScreen().getDescriptionArea();
        if (descriptionArea.isEditable()) {
            setIcon(Icons.buildCheckmarkIcon(20, 20));
            descriptionArea.setEditable(false);
            GroupsController.updateGroup();
        } else {
            descriptionArea.setEditable(true);
            setIcon(Icons.buildPenIcon(20, 20));
        }
    }
}
