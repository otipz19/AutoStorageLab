package main.ui.components;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class NoLineTitledBorder extends TitledBorder {
    public NoLineTitledBorder(String title) {
        super(title);
        setBorder(BorderFactory.createEmptyBorder());
    }
}
