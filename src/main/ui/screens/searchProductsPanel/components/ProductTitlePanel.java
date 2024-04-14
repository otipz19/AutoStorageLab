package main.ui.screens.searchProductsPanel.components;

import main.controllers.ProductsController;
import main.model.dto.ProductDto;
import main.ui.screens.searchProductsPanel.ProductsListPanel;

import javax.swing.*;
import java.awt.*;

public class ProductTitlePanel extends JPanel {
    public ProductTitlePanel(ProductDto productDto, ProductsListPanel parent) {
        setLayout(new BorderLayout());
        ProductTitleButton productTitleBtn = new ProductTitleButton(productDto);
        add(productTitleBtn, BorderLayout.CENTER);
        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> {
            ProductsController.deleteProduct(productDto);
            parent.delete(this);
        });
        add(deleteBtn, BorderLayout.EAST);
    }
}
