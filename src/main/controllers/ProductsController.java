package main.controllers;

import main.model.data.DataContext;
import main.model.dto.ProductDto;
import main.model.exceptions.DomainException;

public class ProductsController extends BaseController {
    public static void createProduct(ProductDto productDto) {
        try {
            DataContext.getInstance().getProductTable().create(productDto);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
        }
    }
}
