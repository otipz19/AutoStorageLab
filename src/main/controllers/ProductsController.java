package main.controllers;

import main.model.data.DataContext;
import main.model.dto.ProductDto;
import main.model.exceptions.DomainException;
import main.model.valueObjects.ProductName;
import main.ui.screens.productPanel.ProductUpdatePanel;

import java.util.UUID;

public class ProductsController extends BaseController {
    public static void createProduct(ProductDto productDto) {
        try {
            DataContext.getInstance().getProductTable().create(productDto);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
        }
    }

    public static void updateProduct(ProductUpdatePanel productUpdatePanel) {
        try {
            ProductName oldName = productUpdatePanel.getProductDto().getName();
            ProductDto toUpdate = productUpdatePanel.getProductToUpdate();
            UUID productId = DataContext.getInstance().getProductTable().get(oldName).getId();
            DataContext.getInstance().getProductTable().update(productId, toUpdate);
            productUpdatePanel.setProductDto(toUpdate);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
            productUpdatePanel.setProductDto(productUpdatePanel.getProductDto());
        }
    }
}
