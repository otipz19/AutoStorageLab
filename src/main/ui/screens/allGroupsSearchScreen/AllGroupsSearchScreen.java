package main.ui.screens.allGroupsSearchScreen;

import main.ui.App;
import main.ui.screens.searchProductsPanel.ProductsListPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Screen for searching all groups.
 * This screen contains a search field and a panel for displaying the search results.
 */
public class AllGroupsSearchScreen extends JPanel {
    public AllGroupsSearchScreen() {
        App.getInstance().setTitle("Search All Groups");
        setLayout(new BorderLayout());
        add(createMainPanel(), BorderLayout.CENTER);
    }

    private JPanel createMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        mainPanel.setBackground(new Color(0xe9f2fb));
        mainPanel.add(createReturnBtnPanel(), BorderLayout.NORTH);
        mainPanel.add(createProductsListPanel(), BorderLayout.CENTER);
        return mainPanel;
    }

    private JPanel createReturnBtnPanel() {
        JPanel returnBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton returnBtn = new JButton("Return");
        returnBtn.addActionListener(e -> App.goToAllGroupsScreen());
        returnBtnPanel.add(returnBtn);
        return returnBtnPanel;
    }

    private JPanel createProductsListPanel(){
        ProductsListPanel productsListPanel = new ProductsListPanel();
        productsListPanel.loadProducts();
        return productsListPanel;
    }
}