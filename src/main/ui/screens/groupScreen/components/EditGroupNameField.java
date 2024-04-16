package main.ui.screens.groupScreen.components;

import main.model.valueObjects.GroupName;
import main.ui.App;
import main.ui.components.editableField.EditableValidatableField;
import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;

import javax.swing.*;
import java.awt.*;

public class EditGroupNameField extends EditableValidatableField {
    public EditGroupNameField() {
        super(GroupName::isValid);
        setBackground(new Color(0xe9f2fb));
        configInputField();
    }

    private void configInputField() {
        field.setBackground(Color.WHITE);
        field.setForeground(new Color(0x203a54));
//        field.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setEditable(false);
    }
}
