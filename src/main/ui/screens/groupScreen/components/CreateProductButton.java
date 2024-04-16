package main.ui.screens.groupScreen.components;

import main.Icons;
import main.ui.App;
import main.ui.components.buttons.RoundedButton;
import main.ui.components.buttons.StyledButton;
import main.ui.screens.groupScreen.GroupScreen;

import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateProductButton extends StyledButton {
    private GroupScreen parent;

    public CreateProductButton(GroupScreen parent) {
        super("Create new product");
        this.parent = parent;
        setBackground(Color.WHITE);
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        App.goToProductCreateScreen(parent.getGroup());
    }
}
