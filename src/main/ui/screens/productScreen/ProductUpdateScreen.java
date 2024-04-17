package main.ui.screens.productScreen;

import lombok.Getter;
import main.model.dto.ProductDto;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;
import main.ui.components.NoLineTitledBorder;
import main.ui.components.StyledLabel;
import main.ui.components.buttons.ReturnButton;
import main.ui.components.buttons.StyledButton;
import main.ui.components.editableField.DescriptionArea;
import main.ui.components.editableField.EditableValidatableField;
import main.ui.screens.Screen;
import main.ui.screens.productScreen.components.AmountChangeListener;
import main.ui.screens.productScreen.components.AmountLabel;
import main.ui.screens.productScreen.components.EditProductBtn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class represents the panel for updating a product.
 * It extends JPanel and contains fields for product details.
 * It also contains methods for creating panels for each field,
 * setting the product DTO, and getting the updated product DTO.
 */
public class ProductUpdateScreen extends Screen {
    @Getter
    private ProductDto productDto;

    private EditableValidatableField nameField;
    private DescriptionArea description;
    private EditableValidatableField manufacturer;
    @Getter
    private AmountLabel amount;
    private EditableValidatableField price;
    @Getter
    private StyledLabel totalPrice;

    /**
     * Constructor for ProductUpdatePanel.
     * Initializes the layout, adds fields and return button.
     *
     * @param productDto the product DTO to be updated
     */
    public ProductUpdateScreen(ProductDto productDto) {
        super(productDto.getName().getValue());
        this.productDto = productDto;

        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel, BorderLayout.CENTER);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

        JPanel smallFields = new JPanel(new GridLayout(4, 1, 5, 5));
        smallFields.add(createNamePanel());
        smallFields.add(createManufacturerPanel());
        smallFields.add(createAmountPanel());
        smallFields.add(createPricePanel());

        fieldsPanel.add(smallFields);
        fieldsPanel.add(createDescriptionPanel());

        mainPanel.add(fieldsPanel, BorderLayout.CENTER);

        setProductDto(productDto);

        JButton returnButton = new ReturnButton();
        mainPanel.add(returnButton, BorderLayout.NORTH);
        mainPanel.add(createTotalPricePanel(), BorderLayout.SOUTH);
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
                amount.getValue(),
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
        amount.setValue(productDto.getAmount().getValue());
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
        JScrollPane scroll = new JScrollPane(description);
        scroll.setPreferredSize(new Dimension(580, 100));
        scroll.setMaximumSize(new Dimension(580, 200));
        EditProductBtn descriptionEditBtn = new EditProductBtn(description, this);
        var panel = formPanel("Description: ", scroll, descriptionEditBtn);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        return panel;
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
        amount = new AmountLabel();
        JPanel btnsPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton addBtn = new StyledButton("Add");
        addBtn.addActionListener(new AmountChangeListener(this, true));
        JButton removeBtn = new StyledButton("Remove");
        removeBtn.addActionListener(new AmountChangeListener(this, false));
        btnsPanel.add(addBtn);
        btnsPanel.add(removeBtn);
        JPanel amountPanel = new JPanel(new BorderLayout(5, 5));
        amountPanel.add(amount, BorderLayout.CENTER);
        amountPanel.add(btnsPanel, BorderLayout.EAST);
        return amountPanel;
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
        JPanel panel = new JPanel(new BorderLayout());
        field.setBorder(new NoLineTitledBorder(label));
        panel.add(field, BorderLayout.CENTER);
        panel.add(btn, BorderLayout.EAST);
        return panel;
    }

    private JPanel createTotalPricePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        totalPrice = new StyledLabel("");
        recalculateTotalPrice();
        panel.add(totalPrice, BorderLayout.CENTER);
        return panel;
    }

    public void recalculateTotalPrice() {
        double total = productDto.getPrice().getValue() * productDto.getAmount().getValue();
        totalPrice.setText("Total price: " + total);
    }
}
