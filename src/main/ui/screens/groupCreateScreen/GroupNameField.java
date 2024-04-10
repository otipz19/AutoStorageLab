package main.ui.screens.groupCreateScreen;

import main.model.valueObjects.GroupName;
import main.ui.App;
import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class GroupNameField extends NotEmptyValidatableFieldPanel {
    private GroupCreatePanel groupCreatePanel;

    public GroupNameField(GroupCreatePanel groupCreatePanel) {
        super(GroupName::isValid);
        this.groupCreatePanel = groupCreatePanel;
        setBackground(new Color(0xe9f2fb));
        configInputField();
    }

    private void configInputField() {
        field.setBackground(Color.WHITE);
        field.setForeground(new Color(0x203a54));
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        field.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void afterValidation() {
        groupCreatePanel.onInputChange(isValid);
    }
}
