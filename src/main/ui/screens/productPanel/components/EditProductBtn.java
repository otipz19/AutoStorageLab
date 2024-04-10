package main.ui.screens.productPanel.components;

import main.controllers.ProductsController;
import main.ui.components.editableField.EditableFieldButton;
import main.ui.components.editableField.IEditableConnectedField;
import main.ui.screens.productPanel.ProductUpdatePanel;

public class EditProductBtn extends EditableFieldButton {
    private final ProductUpdatePanel productUpdatePanel;

    public EditProductBtn(IEditableConnectedField connectedField, ProductUpdatePanel productUpdatePanel) {
        super(connectedField, "", 10);
        this.productUpdatePanel = productUpdatePanel;
    }

    @Override
    protected void afterClick() {
        ProductsController.updateProduct(productUpdatePanel);
    }
}
