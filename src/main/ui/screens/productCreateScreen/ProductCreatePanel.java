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
import java.util.Arrays;

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
     *
     * @param productCreateScreen The screen that this panel is part of.
     */
    public ProductCreatePanel(ProductCreateScreen productCreateScreen) {
        this.productCreateScreen = productCreateScreen;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(createLabelsPanel(), BorderLayout.WEST);
        add(createFielsPanel(), BorderLayout.CENTER);
    }

    private JPanel createLabelsPanel(){
        JPanel labelsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        String[] labels = new String[] {"Name:", "Description:", "Manufacturer:", "Amount:", "Price:"};
        Arrays.stream(labels).forEach(str -> labelsPanel.add(new StyledLabel(str)));
        return labelsPanel;
    }

    private JPanel createFielsPanel(){
        JPanel fieldsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        name = new ValidatableNotifierField(ProductName::isValid, this);
        fieldsPanel.add(name);
        description = new JTextArea();
        fieldsPanel.add(description);
        manufacturer = new ValidatableNotifierField(ManufacturerName::isValid, this);
        fieldsPanel.add(manufacturer);
        amount = new ValidatableNotifierField(ProductAmount::isValid, this);
        fieldsPanel.add(amount);
        price = new ValidatableNotifierField(ProductPrice::isValid, this);
        fieldsPanel.add(price);
        return fieldsPanel;
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
     *
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
