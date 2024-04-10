package main.ui.screens.groupScreen.components;

import main.Icons;
import main.ui.components.buttons.RoundedButton;

import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateProductButton extends RoundedButton {
    public CreateProductButton() {
        super("", 10);
        setBackground(Color.WHITE);
        setIcon(Icons.buildPlusIcon(20, 20));
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        //TODO: to be implemented
    }
}
