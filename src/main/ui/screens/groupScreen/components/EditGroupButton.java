package main.ui.screens.groupScreen.components;

import main.controllers.GroupsController;
import main.ui.components.editableField.EditableFieldButton;
import main.ui.components.editableField.EditableValidatableField;
import main.ui.components.editableField.IEditableConnectedField;
import main.ui.screens.groupScreen.GroupScreen;

import java.awt.*;

public class EditGroupButton extends EditableFieldButton {
    private final GroupScreen parent;

    public EditGroupButton(IEditableConnectedField connectedField, GroupScreen parent) {
        super(connectedField, "", 10);
        this.parent = parent;
        setBackground(Color.WHITE);
    }

    @Override
    protected void afterClick() {
        GroupsController.updateGroup(parent);
    }
}
