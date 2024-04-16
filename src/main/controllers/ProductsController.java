package main.controllers;

import main.model.data.DataContext;
import main.model.dto.ProductDto;
import main.model.exceptions.DomainException;
import main.model.valueObjects.ProductName;
import main.ui.screens.productScreen.ProductUpdateScreen;

import java.util.UUID;

/**
 * The ProductsController class provides methods for managing products in the application.
 * It extends the BaseController class to inherit common controller functionalities.
 */
public class ProductsController extends BaseController {

    /**
     * Creates a new product.
     *
     * @param productDto the data transfer object representing the product to be created
     */
    public static void createProduct(ProductDto productDto) {
        try {
            DataContext.getInstance().getProductTable().create(productDto);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
        }
    }

    /**
     * Updates a product.
     *
     * @param productUpdateScreen the panel containing the product details to be updated
     */
    public static void updateProduct(ProductUpdateScreen productUpdateScreen) {
        try {
            ProductName oldName = productUpdateScreen.getProductDto().getName();
            ProductDto toUpdate = productUpdateScreen.getProductToUpdate();
            UUID productId = DataContext.getInstance().getProductTable().get(oldName).getId();
            DataContext.getInstance().getProductTable().update(productId, toUpdate);
            productUpdateScreen.setProductDto(toUpdate);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
            productUpdateScreen.setProductDto(productUpdateScreen.getProductDto());
        }
    }

    public static void deleteProduct(ProductDto productDto) {
        try {
            DataContext.getInstance().getProductTable().delete(productDto.getName());
        } catch (DomainException e){
            showExceptionMessage(e);
        }
    }
}
