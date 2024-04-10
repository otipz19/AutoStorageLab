package main.ui.screens.groupScreen;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.model.valueObjects.GroupName;
import main.ui.App;
import main.ui.components.validatableField.NotEmptyValidatableFieldPanel;
import main.ui.components.validatableField.ValidatableFieldPanel;
import main.ui.screens.groupScreen.components.*;

import javax.swing.*;
import java.awt.*;

public class GroupScreen extends JPanel {
    @Getter
    private GroupDto group;

    private GroupProductsSearchField searchField;
    @Getter
    private GroupNameField groupNameField;
    @Getter
    private GroupDescriptionArea descriptionArea;
    private JPanel searchResultsPanel;
    private EditGroupNameButton editNameBtn;
    private EditGroupDescriptionButton editDescriptionBtn;
    private JButton createProductBtn;

    public GroupScreen() {
        App.getInstance().setTitle("Group Details");
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        mainPanel.setBackground(new Color(0xe9f2fb));
        mainPanel.add(createActionsPanel(), BorderLayout.NORTH);
        mainPanel.add(createSearchResultPanel(), BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createActionsPanel(){
        JPanel actionsPanel = new JPanel(new GridLayout(3, 1));
        actionsPanel.add(createGroupNamePanel());
        actionsPanel.add(createDescriptionPanel());
        actionsPanel.add(createSearchPanel());
        return actionsPanel;
    }

    private JPanel createGroupNamePanel() {
        JPanel groupNamePanel = new JPanel(new BorderLayout());
        groupNameField = new GroupNameField();
        groupNameField.setSize(580, 50);
        groupNamePanel.add(groupNameField, BorderLayout.CENTER);
        editNameBtn = new EditGroupNameButton();
        editNameBtn.setSize(50, 50);
        groupNamePanel.add(editNameBtn, BorderLayout.EAST);
        return groupNamePanel;
    }

    private JPanel createDescriptionPanel() {
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionArea = new GroupDescriptionArea();
        descriptionArea.setSize(580, 50);
        descriptionPanel.add(descriptionArea, BorderLayout.CENTER);
        editDescriptionBtn = new EditGroupDescriptionButton();
        editDescriptionBtn.setSize(50, 50);
        descriptionPanel.add(editDescriptionBtn, BorderLayout.EAST);
        return descriptionPanel;
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new GroupProductsSearchField();
        searchField.setSize(580, 50);
        searchPanel.add(searchField, BorderLayout.CENTER);
        createProductBtn = new CreateProductButton();
        createProductBtn.setSize(50, 50);
        searchPanel.add(createProductBtn, BorderLayout.EAST);
        return searchPanel;
    }

    private JPanel createSearchResultPanel() {
        searchResultsPanel = new JPanel();
        searchResultsPanel.setSize(580, 500);
        return searchResultsPanel;
    }

    public void setGroup(GroupDto groupDto) {
        this.group = groupDto;
        groupNameField.setText(groupDto.getName().getValue());
        descriptionArea.setText(groupDto.getDescription());
    }

    public GroupDto getGroupToUpdate() {
        return new GroupDto(groupNameField.getText(), descriptionArea.getText());
    }

    public void setValidationState(){
        groupNameField.setValidationState();
    }
}