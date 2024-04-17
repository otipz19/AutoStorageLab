package main.ui.components.panels.productsListPanel.components;

import main.Icons;
import main.controllers.ProductsController;
import main.model.dto.ProductDto;
import main.ui.components.buttons.RoundedButton;
import main.ui.components.buttons.StyledButton;
import main.ui.components.panels.productsListPanel.ProductsListPanel;

import javax.swing.*;
import java.awt.*;

public class ProductTitlePanel extends JPanel {
    public ProductTitlePanel(ProductDto productDto, ProductsListPanel parent) {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        ProductTitleButton productTitleBtn = new ProductTitleButton(productDto);
        add(productTitleBtn, BorderLayout.CENTER);
        JButton deleteBtn = new RoundedButton("", 10);
        deleteBtn.setBackground(Color.WHITE);
        deleteBtn.setIcon(Icons.buildCrossIcon(20, 20));
        deleteBtn.addActionListener(e -> {
            ProductsController.deleteProduct(productDto);
            parent.delete(this);
        });
        add(deleteBtn, BorderLayout.EAST);
    }
}
