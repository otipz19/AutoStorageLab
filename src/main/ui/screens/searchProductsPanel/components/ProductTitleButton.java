package main.ui.screens.searchProductsPanel.components;

import main.model.dto.ProductDto;
import main.ui.components.buttons.StyledButton;
import main.ui.App;
import main.ui.screens.productPanel.ProductUpdatePanel;

import java.awt.event.ActionEvent;

/**
 * This class represents a button for a product title.
 * It extends StyledButton and contains a product DTO and a product update panel.
 * It also contains methods for setting the product DTO, the product update panel, and handling click events.
 */
public class ProductTitleButton extends StyledButton {
    private ProductDto productDto;
    private ProductUpdatePanel productUpdatePanel;

    /**
     * Constructor for ProductTitleButton.
     * Initializes the button with the product name and adds a click listener.
     * @param productDto the product DTO to be displayed
     */
    public ProductTitleButton(ProductDto productDto) {
        super(productDto.getName().getValue());
        this.productDto = productDto;
        addActionListener(this::onClick);
    }

    /**
     * Sets the product update panel.
     * @param productUpdatePanel the product update panel to be set
     */
    public void setProductUpdatePanel(ProductUpdatePanel productUpdatePanel) {
        this.productUpdatePanel = productUpdatePanel;
    }

    /**
     * Sets the product DTO and updates the button text.
     * @param productDto the product DTO to be set
     */
    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
        setText(productDto.getName().getValue());
    }

    /**
     * Handles the click event.
     * Navigates to the product update screen for the product DTO.
     * @param e the action event
     */
    private void onClick(ActionEvent e) {
        App.goToProductUpdateScreen(productDto);
    }
}