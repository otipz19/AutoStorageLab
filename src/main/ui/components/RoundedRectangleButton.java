package main.ui.components;

public class RoundedRectangleButton extends RoundedButton {
    public RoundedRectangleButton(String label) {
        super(label, 20);
        setBorder(new RoundedBorder(10));
    }
}