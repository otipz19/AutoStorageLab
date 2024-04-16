package main.ui.screens.productCreateScreen;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.ui.App;
import main.ui.components.panels.ConfirmationPanel;
import main.ui.screens.Screen;

import javax.swing.*;
import java.awt.*;

public class ProductCreateScreen extends Screen {
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
        super("Create new Product");
        this.group = group;
        setLayout(new BorderLayout());
        productCreatePanel = new ProductCreatePanel(this);
        confirmationPanel = new ConfirmationPanel(productCreatePanel);
        confirmationPanel.setPreferredSize(new Dimension(200, 100));
        add(productCreatePanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);
    }
}
