package main.ui.components.panels;

import lombok.Getter;
import main.ui.App;
import main.ui.components.buttons.StyledButton;
import main.ui.screens.ICreationPanel;

import javax.swing.*;

public class ConfirmationPanel extends JPanel {
    private final ICreationPanel creationPanel;
    @Getter
    private StyledButton confirmBtn;
    private StyledButton cancelBtn;

    public ConfirmationPanel(ICreationPanel creationPanel) {
        this.creationPanel = creationPanel;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(Box.createHorizontalGlue());
        add(createConfirmBtn());
        add(createCancelBtn());
        add(Box.createHorizontalGlue());
    }

    private JPanel createConfirmBtn() {
        confirmBtn = new StyledButton("Confirm");
        confirmBtn.addActionListener(e -> creationPanel.create());
        confirmBtn.setEnabled(false);
        return putBtnInPanel(confirmBtn);
    }

    private JPanel createCancelBtn() {
        cancelBtn = new StyledButton("Cancel");
        cancelBtn.addActionListener(e -> App.goToAllGroupsScreen());
        return putBtnInPanel(cancelBtn);
    }

    private JPanel putBtnInPanel(JButton btn){
        JPanel panel = new JPanel();
        panel.add(btn);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }
}
