package main.ui.screens.groupScreen;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.ui.components.buttons.ReturnButton;
import main.ui.components.editableField.DescriptionArea;
import main.ui.screens.Screen;
import main.ui.screens.groupScreen.components.CreateProductButton;
import main.ui.screens.groupScreen.components.EditGroupButton;
import main.ui.screens.groupScreen.components.EditGroupNameField;
import main.ui.components.panels.productsListPanel.ProductsListPanel;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the screen for a group.
 * It extends JPanel and contains fields for group details and products.
 * It also contains methods for creating panels, setting the group, and handling search.
 */
public class GroupScreen extends Screen {
    @Getter
    private GroupDto group;

    @Getter
    private EditGroupNameField groupNameField;
    @Getter
    private DescriptionArea descriptionArea;
    private EditGroupButton editNameBtn;
    private EditGroupButton editDescriptionBtn;
    private JButton createProductBtn;

    private ProductsListPanel productsListPanel;


    /**
     * Constructor for GroupScreen.
     * Initializes the layout, adds panels and buttons.
     */
    public GroupScreen(GroupDto groupDto) {
        super("Group Details");
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        productsListPanel = new ProductsListPanel();
        mainPanel.add(createActionsPanel(), BorderLayout.NORTH);
        mainPanel.add(productsListPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        setGroup(groupDto);
    }

    /**
     * Creates and returns the actions panel.
     * @return the actions panel
     */
    private JPanel createActionsPanel(){
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BorderLayout(5, 5));
        JPanel smallPanel = new JPanel();
        smallPanel.setLayout(new BoxLayout(smallPanel, BoxLayout.Y_AXIS));
        smallPanel.add(createReturnBtnPanel());
        smallPanel.add(createGroupNamePanel());
        actionsPanel.add(smallPanel, BorderLayout.NORTH);
        actionsPanel.add(createDescriptionPanel(), BorderLayout.CENTER);
        return actionsPanel;
    }

    private JPanel createReturnBtnPanel(){
        JPanel returnBtnPanel = new JPanel();
        returnBtnPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton returnBtn = new ReturnButton();
        returnBtnPanel.add(returnBtn);
        createProductBtn = new CreateProductButton(this);
        createProductBtn.setSize(50, 50);
        returnBtnPanel.add(createProductBtn);
        return returnBtnPanel;
    }

    /**
     * Creates and returns the group name panel.
     * @return the group name panel
     */
    private JPanel createGroupNamePanel() {
        JPanel groupNamePanel = new JPanel(new BorderLayout(5, 5));
        groupNameField = new EditGroupNameField();
        groupNameField.setSize(580, 50);
        groupNamePanel.add(groupNameField, BorderLayout.CENTER);
        editNameBtn = new EditGroupButton(groupNameField, this);
        editNameBtn.setSize(50, 50);
        groupNameField.setConnectedBtn(editNameBtn);
        groupNamePanel.add(editNameBtn, BorderLayout.EAST);
        return groupNamePanel;
    }

    /**
     * Creates and returns the description panel.
     * @return the description panel
     */
    private JPanel createDescriptionPanel() {
        JPanel descriptionPanel = new JPanel(new BorderLayout(5, 5));
        descriptionArea = new DescriptionArea();
        JScrollPane scroll = new JScrollPane(descriptionArea);
        scroll.setPreferredSize(new Dimension(580, 100));
        scroll.setMaximumSize(new Dimension(580, 200));
        descriptionPanel.add(scroll, BorderLayout.CENTER);
        editDescriptionBtn = new EditGroupButton(descriptionArea, this);
        editDescriptionBtn.setSize(50, 50);
        descriptionPanel.add(editDescriptionBtn, BorderLayout.EAST);
        return descriptionPanel;
    }

    /**
     * Sets the group and updates the fields and products.
     * @param groupDto the group DTO to be set
     */
    public void setGroup(GroupDto groupDto) {
        this.group = groupDto;
        groupNameField.setText(groupDto.getName().getValue());
        descriptionArea.setText(groupDto.getDescription());
        productsListPanel.loadProducts(groupDto);
    }

    /**
     * Returns the updated group DTO.
     * @return the updated group DTO
     */
    public GroupDto getGroupToUpdate() {
        return new GroupDto(groupNameField.getText(), descriptionArea.getText());
    }

    /**
     * Updates the group total price label.
     */


    @Override
    public void updateState(){
        setGroup(group);
    }
}