package main;

import javax.swing.*;
import java.awt.*;

public class Icons {
    private static final String PEN_IMAGE_PATH = "images/pen.png";
    private static final String CHECKMARK_IMAGE_PATH = "images/checkmark.png";
    private static final String PLUS_IMAGE_PATH = "images/plus.png";

    public static ImageIcon buildPenIcon(int width, int height){
        return buildIcon(PEN_IMAGE_PATH, width, height);
    }

    public static ImageIcon buildCheckmarkIcon(int width, int height){
        return buildIcon(CHECKMARK_IMAGE_PATH, width, height);
    }

    public static ImageIcon buildPlusIcon(int width, int height){
        return buildIcon(PLUS_IMAGE_PATH, width, height);
    }

    private static ImageIcon buildIcon(String name, int width, int height){
        ImageIcon base = new ImageIcon(name);
        Image resized = base.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }
}
