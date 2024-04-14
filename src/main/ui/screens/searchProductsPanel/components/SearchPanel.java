package main.ui.screens.searchProductsPanel.components;

import main.model.dto.ProductDto;
import main.ui.screens.searchProductsPanel.ProductsListPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.regex.Pattern;

public class SearchPanel extends JPanel {
    private ProductsListPanel parent;
    private JTextField searchField;

    public SearchPanel(ProductsListPanel parent){
        this.parent = parent;
        setLayout(new BorderLayout());
        searchField = new JTextField();
        searchField.setSize(580, 50);
        add(searchField, BorderLayout.CENTER);
        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(e -> parent.performSearch());
        add(searchBtn, BorderLayout.EAST);
    }

    /**
     * Returns a list of products matching the search field text.
     * @return a list of matching products
     */
    public List<ProductDto> getMatchingProducts(List<ProductDto> products){
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
}
