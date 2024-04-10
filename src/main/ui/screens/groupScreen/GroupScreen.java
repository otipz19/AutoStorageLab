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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

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

    public GroupScreen() {
        App.getInstance().setTitle("Group Details");
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        mainPanel.setBackground(new Color(0xe9f2fb));
        mainPanel.add(createActionsPanel(), BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(createProductsPanel()), BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
    }


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

    private List<ProductDto> getMatchingProducts(){
        String searchText = searchField.getText().toLowerCase();
        if(searchText.isEmpty()){
            return products;
        }
        Pattern pattern = buildRegexPatternFromSearchText(searchText);
        return products.stream()
                .filter(product -> pattern.matcher(product.getName().getValue().toLowerCase()).matches())
                .toList();
    }

    private static Pattern buildRegexPatternFromSearchText(String searchText) {
        searchText = searchText.replaceAll("\\?", ".{1}");
        searchText = searchText.replaceAll("\\*", ".*");
        Pattern pattern = Pattern.compile(searchText);
        return pattern;
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
        productsPanel.setLayout(new GridLayout(10, 1));
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
        drawProductTitles(products);
    }

    private void drawProductTitles(List<ProductDto> products){
        for(ProductDto productDto: products){
            ProductTitleButton productTitleButton = new ProductTitleButton(productDto);
            productsPanel.add(productTitleButton);
        }
    }

    public GroupDto getGroupToUpdate() {
        return new GroupDto(groupNameField.getText(), descriptionArea.getText());
    }
}