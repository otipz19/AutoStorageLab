package main.ui.screens.allGroupsSearchScreen;

import main.model.data.DataContext;
import main.model.dto.Mapper;
import main.model.dto.ProductDto;
import main.ui.App;
import main.ui.screens.groupScreen.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Screen for searching all groups.
 * This screen contains a search field and a panel for displaying the search results.
 */
public class AllGroupsSearchScreen extends JPanel {
    /**
     * The list of all products.
     */
    private List<ProductDto> allProducts;

    /**
     * The search field.
     */
    private GroupProductsSearchField searchField;
    /**
     * The panel for displaying the search results.
     */
    private JPanel searchResultsPanel;
    /**
     * The button for creating a new product.
     */
    private JButton createProductBtn;

    /**
     * Constructs a new AllGroupsSearchScreen.
     * Initializes the screen and loads all products.
     */
    public AllGroupsSearchScreen() {
        App.getInstance().setTitle("Search All Groups");
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        mainPanel.setBackground(new Color(0xe9f2fb));
        mainPanel.add(createSearchPanel(), BorderLayout.NORTH);
        mainPanel.add(createSearchResultPanel(), BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        allProducts = DataContext.getInstance().getProductTable().getAll()
                .stream()
                .map(r -> Mapper.map(r, null))
                .toList();
    }

    /**
     * Performs a search with the text from the search field.
     * Displays the products whose names contain the search text in the search results panel.
     */
    private void performSearch() {
        String searchText = searchField.getText().toLowerCase();
        List<ProductDto> matchingProducts = allProducts.stream()
                .filter(product -> product.getName().getValue().toLowerCase().contains(searchText))
                .collect(Collectors.toList());

        searchResultsPanel.removeAll();
        for (ProductDto product : matchingProducts) {
            JLabel productLabel = new JLabel(product.getName().getValue());
            searchResultsPanel.add(productLabel);
        }
        searchResultsPanel.revalidate();
        searchResultsPanel.repaint();
    }

    /**
     * Creates the search panel.
     * The search panel contains the search field and the create product button.
     * @return The created search panel.
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
     * Creates the search results panel.
     * @return The created search results panel.
     */
    private JPanel createSearchResultPanel() {
        searchResultsPanel = new JPanel();
        searchResultsPanel.setSize(580, 500);
        return searchResultsPanel;
    }
}