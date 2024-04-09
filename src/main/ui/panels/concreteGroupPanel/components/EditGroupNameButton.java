package main.ui.panels.concreteGroupPanel.components;

import main.Icons;
import main.controllers.GroupsController;
import main.ui.App;
import main.ui.components.buttons.RoundedButton;

import java.awt.*;
import java.awt.event.ActionEvent;

public class EditGroupNameButton extends RoundedButton {
    public EditGroupNameButton() {
        super("", 10);
        setBackground(Color.WHITE);
        setIcon(Icons.buildPenIcon(20, 20));
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        var groupNameField = App.getConcreteGroupPanel().getGroupNameField();
        if (groupNameField.isEditable()) {
            setIcon(Icons.buildPenIcon(20, 20));
            groupNameField.setEditable(false);
            GroupsController.updateGroup();
        } else {
            groupNameField.setEditable(true);
            setIcon(Icons.buildCheckmarkIcon(20, 20));
        }
    }
}
