package main.ui.components.buttons;

import lombok.Getter;
import main.controllers.GroupsController;
import main.model.dto.GroupDto;
import main.ui.GlobalStateManager;
import main.ui.GroupFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GroupButton extends StyledButton {
    @Getter
    private final GroupDto groupDto;

    public GroupButton(GroupDto groupDto) {
        super(groupDto.getName().getValue());
        this.groupDto = groupDto;
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        if (GlobalStateManager.isGroupDeleteModeOn()) {
            GroupsController.deleteGroup(groupDto);
        } else {
            new GroupFrame(groupDto);
        }
    }
}
