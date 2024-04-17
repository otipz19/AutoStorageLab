package main.ui.screens.allGroupsSearchScreen;

import main.ui.components.buttons.ReturnButton;
import main.ui.screens.Screen;
import main.ui.components.panels.productsListPanel.ProductsListPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Screen for searching all groups.
 * This screen contains a search field and a panel for displaying the search results.
 */
public class AllGroupsSearchScreen extends Screen {
    private ProductsListPanel productsListPanel;

    public AllGroupsSearchScreen() {
        super("Search All Groups");
        setLayout(new BorderLayout());
        add(createMainPanel(), BorderLayout.CENTER);
    }

    @Override
    public void updateState() {
        productsListPanel.loadProducts();
        productsListPanel.performSearch();
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createReturnBtnPanel(), BorderLayout.NORTH);
        mainPanel.add(createProductsListPanel(), BorderLayout.CENTER);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return mainPanel;
    }

    private JPanel createReturnBtnPanel() {
        JPanel returnBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton returnBtn = new ReturnButton();
        returnBtnPanel.add(returnBtn);
        return returnBtnPanel;
    }

    private JPanel createProductsListPanel() {
        productsListPanel = new ProductsListPanel();
        productsListPanel.loadProducts();
        return productsListPanel;
    }
}