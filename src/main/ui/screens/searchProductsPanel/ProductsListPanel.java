package main.ui.screens.searchProductsPanel;

import main.model.data.DataContext;
import main.model.dto.GroupDto;
import main.model.dto.Mapper;
import main.model.dto.ProductDto;
import main.ui.screens.searchProductsPanel.components.ProductTitlePanel;
import main.ui.screens.searchProductsPanel.components.SearchPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class ProductsListPanel extends JPanel {
    private List<ProductDto> products;

    private SearchPanel searchPanel;
    private JPanel productsPanel;

    public ProductsListPanel() {
        setLayout(new BorderLayout());
        searchPanel = new SearchPanel(this);
        add(searchPanel, BorderLayout.NORTH);
        var scroll = new JScrollPane(createProductsPanel());
        scroll.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(scroll, BorderLayout.CENTER);
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
     * Performs a search for products matching the search field text.
     */
    public void performSearch() {
        List<ProductDto> matchingProducts = searchPanel.getMatchingProducts(products);
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

    public void loadProducts(){
        products = Mapper.map(DataContext.getInstance().getProductTable().getAll());
        drawProductTitles(products);
    }

    /**
     * Loads the products for the group.
     * @param groupDto the group DTO
     */
    public void loadProducts(GroupDto groupDto) {
        UUID groupId = DataContext.getInstance().getGroupTable().get(groupDto.getName()).getId();
        products = Mapper.map(DataContext.getInstance().getProductTable().getByGroupId(groupId));
        drawProductTitles(products);
    }

    /**
     * Draws the product titles for the products.
     * @param products the products
     */
    private void drawProductTitles(List<ProductDto> products){
        productsPanel.removeAll();
        for(ProductDto productDto: products){
            ProductTitlePanel productTitlePanel = new ProductTitlePanel(productDto, this);
            productsPanel.add(productTitlePanel);
        }
    }

    public void delete(ProductTitlePanel toDelete){
        productsPanel.remove(toDelete);
        productsPanel.revalidate();
        productsPanel.repaint();
    }
}
