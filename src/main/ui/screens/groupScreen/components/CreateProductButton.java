package main.ui.screens.groupScreen.components;

import main.Icons;
import main.ui.App;
import main.ui.components.buttons.RoundedButton;
import main.ui.screens.groupScreen.GroupScreen;

import java.awt.*;
import java.awt.event.ActionEvent;

public class CreateProductButton extends RoundedButton {
    private GroupScreen parent;

    public CreateProductButton(GroupScreen parent) {
        super("", 10);
        this.parent = parent;
        setBackground(Color.WHITE);
        setIcon(Icons.buildPlusIcon(20, 20));
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        App.goToProductCreateScreen(parent.getGroup());
    }
}
