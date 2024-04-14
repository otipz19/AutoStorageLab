package main.ui.screens.productPanel;

import lombok.Getter;
import main.controllers.ProductsController;
import main.model.dto.ProductDto;
import main.model.exceptions.DomainException;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductAmount;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;
import main.ui.App;
import main.ui.components.editableField.DescriptionArea;
import main.ui.components.editableField.EditableValidatableField;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.screens.productPanel.components.AmountChangeListener;
import main.ui.screens.productPanel.components.EditProductBtn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class represents the panel for updating a product.
 * It extends JPanel and contains fields for product details.
 * It also contains methods for creating panels for each field,
 * setting the product DTO, and getting the updated product DTO.
 */
public class ProductUpdatePanel extends JPanel {
    @Getter
    private ProductDto productDto;

    private EditableValidatableField nameField;
    private DescriptionArea description;
    private EditableValidatableField manufacturer;
    @Getter
    private JLabel amount;
    private EditableValidatableField price;

    /**
     * Constructor for ProductUpdatePanel.
     * Initializes the layout, adds fields and return button.
     *
     * @param productDto the product DTO to be updated
     */
    public ProductUpdatePanel(ProductDto productDto) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        setBackground(new Color(0xe9f2fb));

        JPanel fieldsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        fieldsPanel.add(createNamePanel());
        fieldsPanel.add(createDescriptionPanel());
        fieldsPanel.add(createManufacturerPanel());
        fieldsPanel.add(createAmountPanel());
        fieldsPanel.add(createPricePanel());
        fieldsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        add(fieldsPanel, BorderLayout.CENTER);

        setProductDto(productDto);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> App.goToGroupScreen());
        add(returnButton, BorderLayout.NORTH);
    }

    /**
     * Returns the updated product DTO.
     *
     * @return the updated product DTO
     */
    public ProductDto getProductToUpdate() {
        return new ProductDto(
                nameField.getText(),
                description.getText(),
                manufacturer.getText(),
                Integer.parseInt(amount.getText()),
                Double.parseDouble(price.getText()),
                productDto.getGroupName().getValue());
    }

    /**
     * Sets the product DTO and updates the fields.
     *
     * @param productDto the product DTO to be set
     */
    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
        nameField.setText(productDto.getName().getValue());
        description.setText(productDto.getDescription());
        manufacturer.setText(productDto.getManufacturer().getValue());
        amount.setText(Integer.toString(productDto.getAmount().getValue()));
        price.setText(Double.toString(productDto.getPrice().getValue()));
    }

    /**
     * Creates and returns the name panel.
     *
     * @return the name panel
     */
    private JPanel createNamePanel() {
        nameField = new EditableValidatableField(ProductName::isValid);
        EditProductBtn nameEditBtn = new EditProductBtn(nameField, this);
        nameField.setConnectedBtn(nameEditBtn);
        return formPanel("Name:", nameField, nameEditBtn);
    }

    /**
     * Creates and returns the description panel.
     *
     * @return the description panel
     */
    private JPanel createDescriptionPanel() {
        description = new DescriptionArea();
        EditProductBtn descriptionEditBtn = new EditProductBtn(description, this);
        return formPanel("Description: ", new JScrollPane(description), descriptionEditBtn);
    }

    /**
     * Creates and returns the manufacturer panel.
     *
     * @return the manufacturer panel
     */
    private JPanel createManufacturerPanel() {
        manufacturer = new EditableValidatableField(ManufacturerName::isValid);
        EditProductBtn btn = new EditProductBtn(manufacturer, this);
        manufacturer.setConnectedBtn(btn);
        return formPanel("Manufacturer: ", manufacturer, btn);
    }

    /**
     * Creates and returns the amount panel.
     *
     * @return the amount panel
     */
    private JPanel createAmountPanel() {
        amount = new JLabel();
        JPanel btnsPanel = new JPanel(new GridLayout(1, 2, 25, 25));
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new AmountChangeListener(this, true));
        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(new AmountChangeListener(this, false));
        btnsPanel.add(addBtn);
        btnsPanel.add(removeBtn);
        return formPanel("Amount: ", amount, btnsPanel);
    }

    /**
     * Creates and returns the price panel.
     *
     * @return the price panel
     */
    private JPanel createPricePanel() {
        price = new EditableValidatableField(ProductPrice::isValid);
        var btn = new EditProductBtn(price, this);
        price.setConnectedBtn(btn);
        return formPanel("Price: ", price, btn);
    }

    /**
     * Creates and returns a form panel with a label, field, and button.
     *
     * @param label the label for the field
     * @param field the field for input
     * @param btn   the button for actions
     * @return the form panel
     */
    private JPanel formPanel(String label, JComponent field, JComponent btn) {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel(label));
        panel.add(new JPanel());
        panel.add(field);
        panel.add(btn);
        return panel;
    }
}
