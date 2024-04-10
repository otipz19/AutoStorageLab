package main.ui.screens.groupScreen;

import lombok.Getter;
import main.model.data.DataContext;
import main.model.dto.GroupDto;
import main.model.dto.Mapper;
import main.model.dto.ProductDto;
import main.ui.App;
import main.ui.components.editableField.DescriptionArea;
import main.ui.screens.groupScreen.components.*;
import main.ui.screens.productPanel.ProductTitleButton;
import main.ui.screens.productPanel.ProductUpdatePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class GroupScreen extends JPanel {
    @Getter
    private GroupDto group;

    private List<ProductDto> products;

    private GroupProductsSearchField searchField;
    @Getter
    private EditGroupNameField groupNameField;
    @Getter
    private DescriptionArea descriptionArea;
    private JPanel productsPanel;
    private EditGroupButton editNameBtn;
    private EditGroupButton editDescriptionBtn;
    private JButton createProductBtn;

    public GroupScreen() {
        App.getInstance().setTitle("Group Details");
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        mainPanel.setBackground(new Color(0xe9f2fb));
        mainPanel.add(createActionsPanel(), BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(createProductsPanel()), BorderLayout.CENTER);
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
        groupNameField = new EditGroupNameField();
        groupNameField.setSize(580, 50);
        groupNamePanel.add(groupNameField, BorderLayout.CENTER);
        editNameBtn = new EditGroupButton(groupNameField);
        editNameBtn.setSize(50, 50);
        groupNameField.setConnectedBtn(editNameBtn);
        groupNamePanel.add(editNameBtn, BorderLayout.EAST);
        return groupNamePanel;
    }

    private JPanel createDescriptionPanel() {
        JPanel descriptionPanel = new JPanel(new BorderLayout());
        descriptionArea = new DescriptionArea();
        descriptionArea.setSize(580, 50);
        descriptionPanel.add(descriptionArea, BorderLayout.CENTER);
        editDescriptionBtn = new EditGroupButton(descriptionArea);
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

    private JPanel createProductsPanel() {
        productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
        productsPanel.setSize(580, 500);
        return productsPanel;
    }

    public void setGroup(GroupDto groupDto) {
        this.group = groupDto;
        groupNameField.setText(groupDto.getName().getValue());
        descriptionArea.setText(groupDto.getDescription());
        loadProducts(groupDto);
    }

    private void loadProducts(GroupDto groupDto) {
        UUID groupId = DataContext.getInstance().getGroupTable().get(groupDto.getName()).getId();
        products = DataContext.getInstance().getProductTable().getByGroupId(groupId)
                .stream()
                .map(r -> Mapper.map(r, groupDto.getName()))
                .toList();
        drawProductPanels();
    }

    private void drawProductPanels(){
        for(ProductDto productDto: products){
            ProductTitleButton productTitleButton = new ProductTitleButton(productDto);
            productTitleButton.setPreferredSize(new Dimension(productsPanel.getWidth(), productTitleButton.getHeight()));
            productsPanel.add(productTitleButton);
            ProductUpdatePanel productUpdatePanel = new ProductUpdatePanel(productDto, productTitleButton);
            productUpdatePanel.setVisible(false);
            productTitleButton.setPreferredSize(new Dimension(productsPanel.getWidth(), productUpdatePanel.getHeight()));
            productsPanel.add(productUpdatePanel);
        }
    }

    public GroupDto getGroupToUpdate() {
        return new GroupDto(groupNameField.getText(), descriptionArea.getText());
    }
}