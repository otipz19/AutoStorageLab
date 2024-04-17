package main.ui.screens.productScreen.components;

import main.ui.components.StyledLabel;

public class AmountLabel extends StyledLabel {
    private int value;

    public AmountLabel() {
        super("");
    }

    public void setValue(int value){
        this.value = value;
        super.setText("Amount: " + value);
    }

    public int getValue(){
        return value;
    }
}
