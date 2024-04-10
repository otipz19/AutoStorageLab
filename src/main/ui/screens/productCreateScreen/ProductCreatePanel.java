package main.ui.screens.productCreateScreen;

import main.controllers.BaseController;
import main.controllers.ProductsController;
import main.model.dto.ProductDto;
import main.model.exceptions.DomainException;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductAmount;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;
import main.ui.App;
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
        return new GridedPanel("Name: ", name);
    }

    private JPanel createDescriptionPanel() {
        description = new JTextArea();
        return new GridedPanel("Description: ", new JScrollPane(description));
    }

    private JPanel createManufacturerPanel() {
        manufacturer = new ValidatableNotifierField(ManufacturerName::isValid, this);
        return new GridedPanel("Manufacturer: ", manufacturer);
    }

    private JPanel createAmountPanel() {
        amount = new ValidatableNotifierField(str -> {
            try {
                return ProductAmount.isValid(Integer.valueOf(str));
            } catch (NumberFormatException ex) {
                return false;
            }
        }, this);
        return new GridedPanel("Amount: ", amount);
    }

    private JPanel createPricePanel() {
        price = new ValidatableNotifierField(str -> {
            try {
                return ProductPrice.isValid(Double.valueOf(str));
            } catch (NumberFormatException ex) {
                return false;
            }
        }, this);
        return new GridedPanel("Price: ", price);
    }

    /**
     * Creates a new product with the input from the fields.
     * If the input is invalid, it shows an error message and does not create the product.
     */
    @Override
    public void create() {
        try {
            ProductsController.createProduct(getProductDto());
            App.goToAllGroupsScreen();
        } catch (InvalidFormInputException ex) {
            BaseController.showExceptionMessage(ex);
        }
    }


    private static class GridedPanel extends JPanel {
        public GridedPanel(String labelText, JComponent component) {
            setLayout(new GridLayout(1, 2));
            add(new JLabel(labelText));
            add(component);
        }
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
