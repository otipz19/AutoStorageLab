package main.ui.forms.components;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;

public class PriceTextField extends JFormattedTextField {
    public PriceTextField() {
        DecimalFormat format = new DecimalFormat("#.##");
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0);
        formatter.setAllowsInvalid(false);
    }
}
