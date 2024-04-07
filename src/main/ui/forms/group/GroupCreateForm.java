package main.ui.forms.group;

import main.model.dto.GroupDto;

import javax.swing.*;
import java.awt.*;

public class GroupCreateForm {
    /**
     * @return null if form was cancelled or input was invalid
     */
    public static GroupDto createGroup() {
        GroupCreatePanel panel = new GroupCreatePanel();
        panel.setPreferredSize(new Dimension(450, 200));
        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Create Group",
                JOptionPane.OK_CANCEL_OPTION
        );
        if (result == JOptionPane.OK_OPTION) {
            return panel.getGroupDto();
        }
        return null;
    }
}
