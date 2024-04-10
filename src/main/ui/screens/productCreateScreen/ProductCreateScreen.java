package main.ui.screens.productCreateScreen;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.ui.App;
import main.ui.components.panels.ConfirmationPanel;

import javax.swing.*;
import java.awt.*;

public class ProductCreateScreen extends JPanel {
    @Getter
    private final GroupDto group;

    private ProductCreatePanel productCreatePanel;
    @Getter
    private ConfirmationPanel confirmationPanel;

    /**
     * Constructs a new ProductCreateScreen.
     * @param group The group that the product belongs to.
     */
    public ProductCreateScreen(GroupDto group) {
        this.group = group;
        App.getInstance().setTitle("Create new Product");
        setLayout(new BorderLayout());
        productCreatePanel = new ProductCreatePanel(this);
        confirmationPanel = new ConfirmationPanel(productCreatePanel);
        confirmationPanel.setPreferredSize(new Dimension(200, 100));
        add(productCreatePanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);
    }
}
