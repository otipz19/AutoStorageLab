package main.ui.forms.product;

import main.ui.forms.components.AmountSpinner;
import main.ui.forms.components.PriceTextField;
import main.ui.forms.components.validatableField.NotEmptyValidatableFieldPanel;

import javax.swing.*;

public class ProductCreatePanel extends JPanel {
    private NotEmptyValidatableFieldPanel name;
    private JTextArea description;
    private NotEmptyValidatableFieldPanel manufacturer;
    private AmountSpinner amount;
    private PriceTextField price;

    public ProductCreatePanel(){
        amount = new AmountSpinner();
    }
}
