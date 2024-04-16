package main.ui.screens;

import main.ui.App;

import javax.swing.*;
import java.awt.*;

public abstract class Screen extends JPanel {
    private String title;

    public Screen(String title){
        App.getInstance().setTitle(title);
        setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        setBackground(new Color(0xe9f2fb));
    }

    public void updateState(){

    }
}
