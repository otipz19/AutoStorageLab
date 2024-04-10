package main.ui.screens.groupScreen.components;

import main.model.valueObjects.GroupName;
import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;

import javax.swing.*;
import java.awt.*;

public class GroupNameField extends NotEmptyValidatableFieldPanel {
    public GroupNameField(){
        super(GroupName::isValid);
        field.setBackground(Color.WHITE);
        field.setForeground(new Color(0x203a54));
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        field.setHorizontalAlignment(SwingConstants.CENTER);
        field.setEditable(false);
        setBackground(new Color(0xe9f2fb));
    }
}
