package main.ui.components.productCreation;

import javax.swing.*;

public class AmountSpinner extends JSpinner {
    public AmountSpinner(){
        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setMinimum(0);
        setModel(model);
    }
}
