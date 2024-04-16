package main.ui.screens.allGroupsScreen.components;

import main.ui.App;
import main.ui.components.buttons.StyledButton;

import java.awt.*;

public class FindInStorageButton extends StyledButton {
    public FindInStorageButton() {
        super("Find in Storage");
        setPreferredSize(new Dimension(200, 50));
        addActionListener(e -> App.goToAllGroupsSearchScreen());
    }
}
