package main.ui.forms.product;

import main.model.dto.ProductDto;

import javax.swing.*;
import java.awt.*;

public class ProductCreateForm {
    public static ProductDto createProduct(){
        ProductCreatePanel panel = new ProductCreatePanel();
        panel.setPreferredSize(new Dimension(450, 300));
        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Create Group",
                JOptionPane.OK_CANCEL_OPTION
        );
        if (result == JOptionPane.OK_OPTION) {
            return panel.getProductDto();
        }
        return null;
    }
}
