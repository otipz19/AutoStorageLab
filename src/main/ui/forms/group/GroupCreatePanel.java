package main.ui.forms.group;

import main.model.dto.GroupDto;
import main.model.exceptions.DomainException;
import main.model.valueObjects.GroupName;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.forms.components.validatableField.NotEmptyValidatableFieldPanel;

import javax.swing.*;
import java.awt.*;

public class GroupCreatePanel extends JPanel {
    private NotEmptyValidatableFieldPanel name;
    private JTextArea description;

    public GroupCreatePanel(){
        this("", "");
    }

    public GroupCreatePanel(String name, String description){
        setLayout(new GridLayout(2, 1));
        add(createNamePanel(name));
        add(createDescriptionPanel(description));
    }


    private JPanel createNamePanel(String value){
        JPanel panel = new JPanel(new GridLayout(1, 2));
        add(new JLabel("Name: "));
        name = new NotEmptyValidatableFieldPanel(GroupName::isValid);
        add(name);
        return panel;
    }

    private JPanel createDescriptionPanel(String value){
        JPanel panel = new JPanel(new GridLayout(1, 2));
        add(new JLabel("Description: "));
        description = new JTextArea(value);
        add(new JScrollPane(description));
        return panel;
    }

    /**
     * @throws InvalidFormInputException if input is invalid
     */
    public GroupDto getGroupDto(){
        try{
            return new GroupDto(name.getText(), description.getText());
        } catch (DomainException e){
            throw new InvalidFormInputException(e);
        }
    }
}
