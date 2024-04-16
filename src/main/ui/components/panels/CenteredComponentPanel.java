package main.ui.components.panels;

import javax.swing.*;

public class CenteredComponentPanel extends JPanel {
    public CenteredComponentPanel(JComponent component) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());
        add(createHorizontalPanel(component));
        add(Box.createVerticalGlue());
    }

    private JPanel createHorizontalPanel(JComponent component) {
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));
        horizontalPanel.add(Box.createHorizontalGlue());
        horizontalPanel.add(component);
        horizontalPanel.add(Box.createHorizontalGlue());
        return horizontalPanel;
    }
}
