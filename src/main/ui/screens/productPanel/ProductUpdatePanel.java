package main.ui.screens.productPanel;

import lombok.Getter;
import main.model.dto.ProductDto;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductAmount;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;
import main.ui.App;
import main.ui.components.editableField.DescriptionArea;
import main.ui.components.editableField.EditableValidatableField;
import main.ui.screens.productPanel.components.EditProductBtn;

import javax.swing.*;
import java.awt.*;

public class ProductUpdatePanel extends JPanel {
    @Getter
    private ProductDto productDto;

    private EditableValidatableField nameField;
    private DescriptionArea description;
    private EditableValidatableField manufacturer;
    private EditableValidatableField amount;
    private EditableValidatableField price;

    public ProductUpdatePanel(ProductDto productDto) {
        setLayout(new BorderLayout());

        JPanel fieldsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        fieldsPanel.add(createNamePanel());
        fieldsPanel.add(createDescriptionPanel());
        fieldsPanel.add(createManufacturerPanel());
        fieldsPanel.add(createAmountPanel());
        fieldsPanel.add(createPricePanel());

        add(fieldsPanel, BorderLayout.CENTER);

        setProductDto(productDto);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> App.goToGroupScreen());
        add(returnButton, BorderLayout.NORTH);
    }

    public ProductDto getProductToUpdate() {
        return new ProductDto(
                nameField.getText(),
                description.getText(),
                manufacturer.getText(),
                Integer.parseInt(amount.getText()),
                Double.parseDouble(price.getText()),
                productDto.getGroupName().getValue());
    }

    public void setProductDto(ProductDto productDto){
        this.productDto = productDto;
        nameField.setText(productDto.getName().getValue());
        description.setText(productDto.getDescription());
        manufacturer.setText(productDto.getManufacturer().getValue());
        amount.setText(Integer.toString(productDto.getAmount().getValue()));
        price.setText(Double.toString(productDto.getPrice().getValue()));
    }

    private JPanel createNamePanel() {
        nameField = new EditableValidatableField(ProductName::isValid);
        EditProductBtn nameEditBtn = new EditProductBtn(nameField, this);
        nameField.setConnectedBtn(nameEditBtn);
        return formPanel("Name:", nameField, nameEditBtn);
    }

    private JPanel createDescriptionPanel() {
        description = new DescriptionArea();
        EditProductBtn descriptionEditBtn = new EditProductBtn(description, this);
        return formPanel("Description: ", new JScrollPane(description), descriptionEditBtn);
    }

    private JPanel createManufacturerPanel() {
        manufacturer = new EditableValidatableField(ManufacturerName::isValid);
        EditProductBtn btn = new EditProductBtn(manufacturer, this);
        manufacturer.setConnectedBtn(btn);
        return formPanel("Manufacturer: ", manufacturer, btn);
    }

    private JPanel createAmountPanel() {
        amount = new EditableValidatableField(ProductAmount::isValid);
        EditProductBtn btn = new EditProductBtn(amount, this);
        amount.setConnectedBtn(btn);
        return formPanel("Amount: ", amount, btn);
    }

    private JPanel createPricePanel() {
        price = new EditableValidatableField(ProductPrice::isValid);
        var btn = new EditProductBtn(price, this);
        price.setConnectedBtn(btn);
        return formPanel("Price: ", price, btn);
    }

    private JPanel formPanel(String label, JComponent field, JComponent btn) {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel(label));
        panel.add(new JPanel());
        panel.add(field);
        panel.add(btn);
        return panel;
    }
}
