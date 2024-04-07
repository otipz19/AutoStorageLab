package main.ui.forms.components;

import javax.swing.*;

public class AmountSpinner extends JSpinner {
    public AmountSpinner(){
        SpinnerNumberModel model = new SpinnerNumberModel();
        model.setMinimum(0);
        setModel(model);
    }
}
