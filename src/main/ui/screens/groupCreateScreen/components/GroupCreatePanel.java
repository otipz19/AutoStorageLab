package main.ui.screens.groupCreateScreen.components;

import main.controllers.BaseController;
import main.controllers.GroupsController;
import main.model.dto.GroupDto;
import main.model.exceptions.DomainException;
import main.ui.App;
import main.ui.components.StyledLabel;
import main.ui.components.panels.CenteredComponentPanel;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.screens.ICreationPanel;
import main.ui.screens.groupCreateScreen.GroupCreateScreen;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GroupCreatePanel extends JPanel implements ICreationPanel {
    private final GroupCreateScreen parent;
    private GroupNameField name;
    private JTextArea description;

    public GroupCreatePanel(GroupCreateScreen parent) {
        this.parent = parent;
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout(50, 10));
        add(createLabelsPanel(), BorderLayout.WEST);
        add(createFieldsPanel(), BorderLayout.CENTER);
    }

    private JPanel createLabelsPanel(){
        JPanel labelsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        String[] labels = new String[] {"Name:", "Description:"};
        Arrays.stream(labels).forEach(str -> {
            var label = new StyledLabel(str);
            label.setPreferredSize(new Dimension(150, 50));
            labelsPanel.add(new CenteredComponentPanel(label));
        });
        return labelsPanel;
    }

    private JPanel createFieldsPanel(){
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        name = new GroupNameField(this);
        panel.add(name);
        description = new JTextArea();
        panel.add(new JScrollPane(description));
        return panel;
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
            App.returnToPreviousScreen();
        } catch (InvalidFormInputException ex) {
            BaseController.showExceptionMessage(ex);
        }
    }

    @Override
    public void cancel() {
        App.returnToPreviousScreen();
    }

    public void onInputChange(boolean isValid) {
        parent.getConfirmationPanel().getConfirmBtn().setEnabled(isValid);
    }
}
