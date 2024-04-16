package main.ui.screens.groupCreateScreen;

import lombok.Getter;
import main.ui.App;
import main.ui.components.panels.ConfirmationPanel;
import main.ui.screens.groupCreateScreen.components.GroupCreatePanel;

import javax.swing.*;
import java.awt.*;

public class GroupCreateScreen extends JPanel {
    private GroupCreatePanel groupCreatePanel;
    @Getter
    private ConfirmationPanel confirmationPanel;

    public GroupCreateScreen() {
        App.getInstance().setTitle("Create new Group");
        setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        setBackground(new Color(0xe9f2fb));
        setLayout(new BorderLayout());
        groupCreatePanel = new GroupCreatePanel(this);
        confirmationPanel = new ConfirmationPanel(groupCreatePanel);
        confirmationPanel.setPreferredSize(new Dimension(200, 100));
        add(groupCreatePanel, BorderLayout.CENTER);
        add(confirmationPanel, BorderLayout.SOUTH);
    }
}
