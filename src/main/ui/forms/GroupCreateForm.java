package main.ui.forms;

import main.model.dto.GroupDto;

import javax.swing.*;

public class GroupCreateForm {
    /**
     * @return null if form was cancelled or input was invalid
     */
    public static GroupDto createGroup() {
        GroupCreatePanel panel = new GroupCreatePanel();
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
