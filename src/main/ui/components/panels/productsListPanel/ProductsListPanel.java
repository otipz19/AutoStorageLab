package main.ui.components.panels.productsListPanel;

import main.model.data.DataContext;
import main.model.dto.GroupDto;
import main.model.dto.Mapper;
import main.model.dto.ProductDto;
import main.ui.components.StyledLabel;
import main.ui.components.panels.productsListPanel.components.ProductTitlePanel;
import main.ui.components.panels.productsListPanel.components.SearchPanel;
import main.ui.components.panels.productsListPanel.components.StatsPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class ProductsListPanel extends JPanel {
    private List<ProductDto> products;
    private GroupDto group;

    private SearchPanel searchPanel;
    private JPanel productsPanel;
    private StatsPanel statsPanel;

    public ProductsListPanel() {
        setLayout(new BorderLayout());
        searchPanel = new SearchPanel(this);
        add(searchPanel, BorderLayout.NORTH);
        var scroll = new JScrollPane(createProductsPanel());
        scroll.setBorder(new EmptyBorder(10, 0, 0, 0));
        add(scroll, BorderLayout.CENTER);
        statsPanel = new StatsPanel();
        add(statsPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates and returns the products panel.
     *
     * @return the products panel
     */
    private JPanel createProductsPanel() {
        productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel, BoxLayout.Y_AXIS));
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
        statsPanel.updateStatsLabels(matchingProducts);
    }

    public void loadProducts() {
        products = Mapper.map(DataContext.getInstance().getProductTable().getAll());
        drawProductTitles(products);
        statsPanel.updateStatsLabels(products);
    }

    /**
     * Loads the products for the group.
     *
     * @param groupDto the group DTO
     */
    public void loadProducts(GroupDto groupDto) {
        group = groupDto;
        UUID groupId = DataContext.getInstance().getGroupTable().get(groupDto.getName()).getId();
        products = Mapper.map(DataContext.getInstance().getProductTable().getByGroupId(groupId));
        drawProductTitles(products);
        statsPanel.updateStatsLabels(products);
    }

    /**
     * Draws the product titles for the products.
     *
     * @param products the products
     */
    private void drawProductTitles(List<ProductDto> products) {
        productsPanel.removeAll();
        products.stream().sorted(Comparator.comparing(p -> p.getName().getValue())).forEach(productDto -> {
            ProductTitlePanel productTitlePanel = new ProductTitlePanel(productDto, this);
            productsPanel.add(productTitlePanel);
        });
        if (products.size() < 10) {
            int emptyBodiesCount = 10 - products.size();
            for (int i = 0; i < emptyBodiesCount; i++) {
                JPanel emptyPanel = new JPanel();
                emptyPanel.setPreferredSize(new Dimension(productsPanel.getWidth(), productsPanel.getHeight() / 10));
                productsPanel.add(emptyPanel);
            }
        }
    }

    public void delete(ProductTitlePanel toDelete, ProductDto productDto) {
        products.remove(productDto);
        productsPanel.remove(toDelete);
        productsPanel.revalidate();
        productsPanel.repaint();
        statsPanel.updateStatsLabels(products);
    }
}
