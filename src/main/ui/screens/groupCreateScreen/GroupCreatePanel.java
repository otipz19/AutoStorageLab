package main.ui.screens.groupCreateScreen;

import main.controllers.GroupsController;
import main.model.dto.GroupDto;
import main.model.exceptions.DomainException;
import main.ui.App;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.screens.ICreationPanel;

import javax.swing.*;
import java.awt.*;

public class GroupCreatePanel extends JPanel implements ICreationPanel {
    private final GroupCreateScreen parent;
    private GroupNameField name;
    private JTextArea description;

    public GroupCreatePanel(GroupCreateScreen parent) {
        this.parent = parent;
        setLayout(new GridLayout(2, 2));
        add(new JLabel("Name: "));
        name = new GroupNameField(this);
        add(name);
        add(new JLabel("Description: "));
        description = new JTextArea();
        add(new JScrollPane(description));
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
    }

    /**
     * @throws InvalidFormInputException if input is invalid
     */
    public GroupDto getGroupDto() {
        try {
            return new GroupDto(name.getText(), description.getText());
        } catch (DomainException e) {
            throw new InvalidFormInputException(e);
        }
    }

    @Override
    public void create() {
        GroupsController.createGroup(getGroupDto());
        App.goToAllGroupsScreen();
    }

    public void onInputChange(boolean isValid) {
        parent.getConfirmationPanel().getConfirmBtn().setEnabled(isValid);
    }
}
