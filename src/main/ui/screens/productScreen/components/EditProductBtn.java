package main.ui.screens.productScreen.components;

import main.controllers.ProductsController;
import main.ui.components.editableField.EditableFieldButton;
import main.ui.components.editableField.IEditableConnectedField;
import main.ui.screens.productScreen.ProductUpdateScreen;

public class EditProductBtn extends EditableFieldButton {
    private final ProductUpdateScreen productUpdateScreen;

    public EditProductBtn(IEditableConnectedField connectedField, ProductUpdateScreen productUpdateScreen) {
        super(connectedField, "", 10);
        this.productUpdateScreen = productUpdateScreen;
    }

    @Override
    protected void afterClick() {
        ProductsController.updateProduct(productUpdateScreen);
        productUpdateScreen.recalculateTotalPrice();
    }
}
