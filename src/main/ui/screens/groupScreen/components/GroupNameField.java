package main.ui.screens.groupScreen.components;

import main.model.valueObjects.GroupName;
import main.ui.App;
import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class GroupNameField extends NotEmptyValidatableFieldPanel {
    public GroupNameField(){
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
    protected void setupValidation(){
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setValidationState();
                App.getGroupScreen().getEditNameBtn().onInputChange(isValid);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setValidationState();
                App.getGroupScreen().getEditNameBtn().onInputChange(isValid);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
    }
}
