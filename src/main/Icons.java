package main;

import javax.swing.*;
import java.awt.*;

/**
 * The Icons class provides methods for creating and resizing icons used in the application.
 */
public class Icons {
    private static final String PEN_IMAGE_PATH = "images/pen.png";
    private static final String CHECKMARK_IMAGE_PATH = "images/checkmark.png";
    private static final String PLUS_IMAGE_PATH = "images/plus.png";
    private static final String CROSS_IMAGE_PATH = "images/cross.png";

    /**
     * Creates and returns a pen icon of the specified width and height.
     *
     * @param width the width of the icon
     * @param height the height of the icon
     * @return the created pen icon
     */
    public static ImageIcon buildPenIcon(int width, int height){
        return buildIcon(PEN_IMAGE_PATH, width, height);
    }

    /**
     * Creates and returns a checkmark icon of the specified width and height.
     *
     * @param width the width of the icon
     * @param height the height of the icon
     * @return the created checkmark icon
     */
    public static ImageIcon buildCheckmarkIcon(int width, int height){
        return buildIcon(CHECKMARK_IMAGE_PATH, width, height);
    }

    /**
     * Creates and returns a plus icon of the specified width and height.
     *
     * @param width the width of the icon
     * @param height the height of the icon
     * @return the created plus icon
     */
    public static ImageIcon buildPlusIcon(int width, int height){
        return buildIcon(PLUS_IMAGE_PATH, width, height);
    }

    /**
     * Creates and returns a cross icon of the specified width and height.
     *
     * @param width the width of the icon
     * @param height the height of the icon
     * @return the created cross icon
     */
    public static ImageIcon buildCrossIcon(int width, int height){
        return buildIcon(CROSS_IMAGE_PATH, width, height);
    }

    /**
     * Creates and returns an icon of the specified width and height.
     *
     * @param name the name of the icon file
     * @param width the width of the icon
     * @param height the height of the icon
     * @return the created icon
     */
    private static ImageIcon buildIcon(String name, int width, int height){
        ImageIcon base = new ImageIcon(name);
        Image resized = base.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resized);
    }
}