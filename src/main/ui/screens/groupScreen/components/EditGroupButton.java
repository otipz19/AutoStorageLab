package main.ui.screens.groupScreen.components;

import main.controllers.GroupsController;
import main.ui.components.editableField.EditableFieldButton;
import main.ui.components.editableField.EditableValidatableField;
import main.ui.components.editableField.IEditableConnectedField;

import java.awt.*;

public class EditGroupButton extends EditableFieldButton {
    public EditGroupButton(IEditableConnectedField connectedField) {
        super(connectedField, "", 10);
        setBackground(Color.WHITE);
    }

    @Override
    protected void afterClick() {
        GroupsController.updateGroup();
    }
}
