package main.ui.components.panels.productsListPanel.components;

import main.controllers.ProductsController;
import main.model.dto.ProductDto;
import main.ui.components.buttons.StyledButton;
import main.ui.components.panels.productsListPanel.ProductsListPanel;

import javax.swing.*;
import java.awt.*;

public class ProductTitlePanel extends JPanel {
    public ProductTitlePanel(ProductDto productDto, ProductsListPanel parent) {
        setLayout(new BorderLayout());
        ProductTitleButton productTitleBtn = new ProductTitleButton(productDto);
        add(productTitleBtn, BorderLayout.CENTER);
        JButton deleteBtn = new StyledButton("Delete");
        deleteBtn.addActionListener(e -> {
            ProductsController.deleteProduct(productDto);
            parent.delete(this);
        });
        add(deleteBtn, BorderLayout.EAST);
    }
}
