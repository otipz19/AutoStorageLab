package main.ui.forms.product;

import main.model.dto.ProductDto;
import main.model.valueObjects.ManufacturerName;
import main.model.valueObjects.ProductAmount;
import main.model.valueObjects.ProductName;
import main.model.valueObjects.ProductPrice;
import main.ui.forms.components.AmountSpinner;
import main.ui.forms.components.GroupsComboBox;
import main.ui.forms.components.PriceTextField;
import main.ui.forms.components.validatableField.NotEmptyValidatableFieldPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProductCreatePanel extends JPanel {
    private NotEmptyValidatableFieldPanel name;
    private JTextArea description;
    private NotEmptyValidatableFieldPanel manufacturer;
    private NotEmptyValidatableFieldPanel amount;
    private NotEmptyValidatableFieldPanel price;
    private GroupsComboBox group;

    public ProductCreatePanel(){
        setLayout(new GridLayout(6, 1, 5, 5));
        add(createNamePanel());
        add(createDescriptionPanel());
        add(createManufacturerPanel());
        add(createAmountPanel());
        add(createPricePanel());
        add(createGroupPanel());
    }

    private JPanel createNamePanel(){
        name = new NotEmptyValidatableFieldPanel(ProductName::isValid);
        return new GridedPanel("Name: ", name);
    }

    private JPanel createDescriptionPanel(){
        description = new JTextArea();
        return new GridedPanel("Description: ", new JScrollPane(description));
    }

    private JPanel createManufacturerPanel(){
        manufacturer = new NotEmptyValidatableFieldPanel(ManufacturerName::isValid);
        return new GridedPanel("Manufacturer: ", manufacturer);
    }

    private JPanel createAmountPanel(){
        amount = new NotEmptyValidatableFieldPanel(str -> {
            try{
                return ProductAmount.isValid(Integer.valueOf(str));
            } catch (NumberFormatException ex ){
                return false;
            }
        });
        return new GridedPanel("Amount: ", amount);
    }

    private JPanel createPricePanel(){
        price = new NotEmptyValidatableFieldPanel(str -> {
            try{
                return ProductPrice.isValid(Double.valueOf(str));
            } catch (NumberFormatException ex) {
                return false;
            }
        });
        return new GridedPanel("Price: ", price);
    }

    private JPanel createGroupPanel(){
        group = new GroupsComboBox();
        return new GridedPanel("Group: ", group);
    }

    private static class GridedPanel extends JPanel{
        public GridedPanel(String labelText, JComponent component){
            setLayout(new GridLayout(1, 2));
            add(new JLabel(labelText));
            add(component);
        }
    }

    public ProductDto getProductDto(){
        if(!name.isValid() || !manufacturer.isValid()){
            return null;
        }
        return new ProductDto(
                name.getText(),
                description.getText(),
                manufacturer.getText(),
                Integer.parseInt(amount.getText()),
                Double.parseDouble(price.getText()),
                (String)group.getSelectedItem());
    }
}
