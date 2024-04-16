package main.ui.components.buttons;

import main.ui.App;

import javax.swing.*;

public class ReturnButton extends StyledButton {
    public ReturnButton(){
        super("Return");
        addActionListener(e -> App.returnToPreviousScreen());
    }
}
