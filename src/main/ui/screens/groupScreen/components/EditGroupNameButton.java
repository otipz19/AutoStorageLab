package main.ui.screens.groupScreen.components;

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

    public void onInputChange(boolean isValid){
        var groupNameField = App.getGroupScreen().getGroupNameField();
        if(groupNameField.isEditable()){
            if(!isValid){
                setIcon(Icons.buildCrossIcon(20, 20));
                setEnabled(false);
            } else{
                setIcon(Icons.buildCheckmarkIcon(20, 20));
                setEnabled(true);
            }
        }
    }

    private void onClick(ActionEvent e){
        var groupNameField = App.getGroupScreen().getGroupNameField();
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
