package main.ui.screens.groupScreen.components;

import main.model.valueObjects.GroupName;
import main.ui.App;
import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;

import javax.swing.*;
import java.awt.*;

public class EditGroupNameField extends NotEmptyValidatableFieldPanel {
    public EditGroupNameField() {
        super(GroupName::isValid);
        setBackground(new Color(0xe9f2fb));
        configInputField();
    }

    private void configInputField() {
        field.setBackground(Color.WHITE);
        field.setForeground(new Color(0x203a54));
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setEditable(false);
    }

    @Override
    protected void afterValidation() {
        if(App.getGroupScreen() != null && App.getGroupScreen().getEditNameBtn() != null){
            App.getGroupScreen().getEditNameBtn().onInputChange(isValid);
        }
    }
}
