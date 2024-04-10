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
        setLayout(null);
        setBackground(new Color(0xe9f2fb));

        groupNameField = new GroupNameField();
        groupNameField.setBounds(158, 58, 580, 50);
        add(groupNameField);

        descriptionArea = new GroupDescriptionArea();
        descriptionArea.setBounds(158, 108, 580, 50);
        add(descriptionArea);

        searchField = new GroupProductsSearchField();
        searchField.setBounds(158, 158, 580, 50);
        add(searchField);

        editNameBtn = new EditGroupNameButton();
        editNameBtn.setBounds(748, 58, 50, 50);
        add(editNameBtn);

        editDescriptionBtn = new EditGroupDescriptionButton();
        editDescriptionBtn.setBounds(748, 108, 50, 50);
        add(editDescriptionBtn);

        createProductBtn = new CreateProductButton();
        createProductBtn.setBounds(748, 158, 50, 50);
        add(createProductBtn);

        searchResultsPanel = new JPanel();
        searchResultsPanel.setBounds(158, 208, 580, 500);
        add(searchResultsPanel);
    }

    public void setGroup(GroupDto groupDto){
        this.group = groupDto;
        groupNameField.setText(groupDto.getName().getValue());
        descriptionArea.setText(groupDto.getDescription());
    }

    public GroupDto getGroupToUpdate(){
        return new GroupDto(groupNameField.getText(), descriptionArea.getText());
    }

    public void setValidationState(){
        groupNameField.setValidationState();
    }
}