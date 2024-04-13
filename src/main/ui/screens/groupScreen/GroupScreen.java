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
import main.controllers.GroupsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * This class represents the screen for a group.
 * It extends JPanel and contains fields for group details and products.
 * It also contains methods for creating panels, setting the group, and handling search.
 */
public class GroupScreen extends JPanel {
    @Getter
    private GroupDto group;

    private List<ProductDto> products;

    private JButton backButton;
    private GroupProductsSearchField searchField;
    @Getter
    private EditGroupNameField groupNameField;
    @Getter
    private DescriptionArea descriptionArea;
    private JPanel productsPanel;
    private EditGroupButton editNameBtn;
    private EditGroupButton editDescriptionBtn;
    private JButton createProductBtn;
    private JLabel groupTotalPriceLabel;


    /**
     * Constructor for GroupScreen.
     * Initializes the layout, adds panels and buttons.
     */
    public GroupScreen() {
        App.getInstance().setTitle("Group Details");
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        mainPanel.setBackground(new Color(0xe9f2fb));
        mainPanel.add(createActionsPanel(), BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(createProductsPanel()), BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        JPanel returnButtonPanel = new JPanel();
        returnButtonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> App.goToAllGroupsScreen());
        returnButtonPanel.add(returnButton);

        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> performSearch());
        returnButtonPanel.add(searchBtn);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(returnButtonPanel);
        northPanel.add(createActionsPanel());

        mainPanel.add(northPanel, BorderLayout.NORTH);

        groupTotalPriceLabel = new JLabel("test");
        groupTotalPriceLabel.setBounds(58, 640, 600, 50);
        northPanel.add(groupTotalPriceLabel);
    }

    /**
     * Performs a search for products matching the search field text.
     */
    private void performSearch() {
        List<ProductDto> matchingProducts = getMatchingProducts();
        productsPanel.removeAll();
        if (matchingProducts.isEmpty()) {
            JLabel noProductsLabel = new JLabel("No products found.");
            productsPanel.add(noProductsLabel);
        } else {
            drawProductTitles(matchingProducts);
        }
        productsPanel.revalidate();
        productsPanel.repaint();
    }

    /**
     * Returns a list of products matching the search field text.
     * @return a list of matching products
     */
    private List<ProductDto> getMatchingProducts(){
        String searchText = searchField.getText().toLowerCase();
        Pattern pattern = buildRegexPatternFromSearchText(searchText);
        return products.stream()
                .filter(product -> pattern.matcher(product.getName().getValue().toLowerCase()).find())
                .toList();
    }

    /**
     * Builds a regex pattern from the search text.
     * @param searchText the search text
     * @return the regex pattern
     */
    private static Pattern buildRegexPatternFromSearchText(String searchText) {
        searchText = searchText.replaceAll("\\?", ".{1}");
        searchText = searchText.replaceAll("\\*", ".*");
        return Pattern.compile(searchText);
    }

    /**
     * Creates and returns the actions panel.
     * @return the actions panel
     */
    private JPanel createActionsPanel(){
        JPanel actionsPanel = new JPanel(new GridLayout(3, 1));
        actionsPanel.add(createGroupNamePanel());
        actionsPanel.add(createDescriptionPanel());
        actionsPanel.add(createSearchPanel());
        return actionsPanel;
    }

    /**
     * Creates and returns the group name panel.
     * @return the group name panel
     */
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

    /**
     * Creates and returns the description panel.
     * @return the description panel
     */
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

    /**
     * Creates and returns the search panel.
     * @return the search panel
     */
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

    /**
     * Creates and returns the products panel.
     * @return the products panel
     */
    private JPanel createProductsPanel() {
        productsPanel = new JPanel();
        productsPanel.setLayout(new GridLayout(10, 1));
        productsPanel.setSize(580, 500);
        return productsPanel;
    }

    /**
     * Sets the group and updates the fields and products.
     * @param groupDto the group DTO to be set
     */
    public void setGroup(GroupDto groupDto) {
        this.group = groupDto;
        groupNameField.setText(groupDto.getName().getValue());
        descriptionArea.setText(groupDto.getDescription());
        loadProducts(groupDto);
        updateGroupTotalPriceLabel();
    }

    /**
     * Loads the products for the group.
     * @param groupDto the group DTO
     */
    private void loadProducts(GroupDto groupDto) {
        UUID groupId = DataContext.getInstance().getGroupTable().get(groupDto.getName()).getId();
        products = DataContext.getInstance().getProductTable().getByGroupId(groupId)
                .stream()
                .map(r -> Mapper.map(r, groupDto.getName()))
                .toList();
        drawProductTitles(products);
    }

    /**
     * Draws the product titles for the products.
     * @param products the products
     */
    private void drawProductTitles(List<ProductDto> products){
        productsPanel.removeAll();
        for(ProductDto productDto: products){
            ProductTitleButton productTitleButton = new ProductTitleButton(productDto);
            productsPanel.add(productTitleButton);
        }
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
    public void updateGroupTotalPriceLabel() {
        double groupTotalPrice = GroupsController.calculateTotalPriceByGroup(group);
        groupTotalPriceLabel.setText("Total price of products in group: " + String.format("%.2f", groupTotalPrice));
    }
}