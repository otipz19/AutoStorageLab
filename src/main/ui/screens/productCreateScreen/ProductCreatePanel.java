package main.ui.screens.productCreateScreen;

import main.controllers.BaseController;
import main.controllers.ProductsController;
import main.model.dto.ProductDto;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductAmount;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;
import main.ui.App;
import main.ui.components.StyledLabel;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.screens.ICreationPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for creating a new product.
 * This panel contains fields for the product's name, description, manufacturer, amount, and price.
 * It validates the input and enables the confirmation button when the input is valid.
 */
public class ProductCreatePanel extends JPanel implements ICreationPanel {
    /**
     * The screen that this panel is part of.
     */
    private final ProductCreateScreen productCreateScreen;

    /**
     * The fields for the product's name, description, manufacturer, amount, and price.
     */
    private ValidatableNotifierField name;
    private JTextArea description;
    private ValidatableNotifierField manufacturer;
    private ValidatableNotifierField amount;
    private ValidatableNotifierField price;
    /**
     * Constructs a new ProductCreatePanel.
     * @param productCreateScreen The screen that this panel is part of.
     */
    public ProductCreatePanel(ProductCreateScreen productCreateScreen) {
        this.productCreateScreen = productCreateScreen;
        setLayout(new GridLayout(5, 1, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(createNamePanel());
        add(createDescriptionPanel());
        add(createManufacturerPanel());
        add(createAmountPanel());
        add(createPricePanel());
    }

    private JPanel createNamePanel() {
        name = new ValidatableNotifierField(ProductName::isValid, this);
        return formPanel("Name: ", name);
    }

    private JPanel createDescriptionPanel() {
        description = new JTextArea();
        return formPanel("Description: ", new JScrollPane(description));
    }

    private JPanel createManufacturerPanel() {
        manufacturer = new ValidatableNotifierField(ManufacturerName::isValid, this);
        return formPanel("Manufacturer: ", manufacturer);
    }

    private JPanel createAmountPanel() {
        amount = new ValidatableNotifierField(ProductAmount::isValid, this);
        return formPanel("Amount: ", amount);
    }

    private JPanel createPricePanel() {
        price = new ValidatableNotifierField(ProductPrice::isValid, this);
        return formPanel("Price: ", price);
    }

    private JPanel formPanel(String label, JComponent component){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new StyledLabel(label), BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creates a new product with the input from the fields.
     * If the input is invalid, it shows an error message and does not create the product.
     */
    @Override
    public void create() {
        try {
            ProductsController.createProduct(getProductDto());
            App.goToGroupScreen(productCreateScreen.getGroup()); // Navigate to the GroupScreen for the group of the created product
        } catch (InvalidFormInputException ex) {
            BaseController.showExceptionMessage(ex);
        }
    }

    @Override
    public void cancel() {
        App.returnToPreviousScreen();
    }

    /**
     * Returns a ProductDto with the input from the fields.
     * @return A ProductDto with the input from the fields.
     * @throws InvalidFormInputException If the input is invalid.
     */
    public ProductDto getProductDto() {
        try {
            return new ProductDto(
                    name.getText(),
                    description.getText(),
                    manufacturer.getText(),
                    Integer.parseInt(amount.getText()),
                    Double.parseDouble(price.getText()),
                    productCreateScreen.getGroup().getName().getValue());
        } catch (Exception ex) {
            throw new InvalidFormInputException(ex);
        }
    }


    /**
     * Called when the input in a field changes.
     * Checks if the input in all fields is valid and enables or disables the confirmation button accordingly.
     */
    public void onInputChange() {
        boolean isValid = name.isInputValid() && manufacturer.isInputValid() && amount.isInputValid() && price.isInputValid();
        productCreateScreen.getConfirmationPanel().getConfirmBtn().setEnabled(isValid);
    }
}
