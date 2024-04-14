package main.ui.screens.groupCreateScreen.components;

import main.controllers.BaseController;
import main.controllers.GroupsController;
import main.model.dto.GroupDto;
import main.model.exceptions.DomainException;
import main.ui.App;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.screens.ICreationPanel;
import main.ui.screens.groupCreateScreen.GroupCreateScreen;

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
     * @throws DomainException if input is invalid
     */
    public GroupDto getGroupDto() {
        try {
            return new GroupDto(name.getText(), description.getText());
        } catch (Exception ex) {
            throw new InvalidFormInputException(ex);
        }
    }

    @Override
    public void create() {
        try {
            GroupsController.createGroup(getGroupDto());
            App.goToAllGroupsScreen();
        } catch (InvalidFormInputException ex) {
            BaseController.showExceptionMessage(ex);
        }
    }

    @Override
    public void cancel() {
        App.goToAllGroupsScreen();
    }

    public void onInputChange(boolean isValid) {
        parent.getConfirmationPanel().getConfirmBtn().setEnabled(isValid);
    }
}
