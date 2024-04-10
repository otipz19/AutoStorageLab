package main.ui.screens.productPanel;

import main.model.dto.ProductDto;
import main.ui.components.buttons.StyledButton;

import java.awt.event.ActionEvent;

public class ProductTitleButton extends StyledButton {
    private ProductDto productDto;
    private ProductUpdatePanel productUpdatePanel;

    public ProductTitleButton(ProductDto productDto) {
        super(productDto.getName().getValue());
        this.productDto = productDto;
        addActionListener(this::onClick);
    }

    public void setProductUpdatePanel(ProductUpdatePanel productUpdatePanel) {
        this.productUpdatePanel = productUpdatePanel;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
        setText(productDto.getName().getValue());
    }

    private void onClick(ActionEvent e) {
        productUpdatePanel.setVisible(!productUpdatePanel.isVisible());
    }
}
