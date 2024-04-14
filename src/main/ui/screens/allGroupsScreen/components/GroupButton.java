package main.ui.screens.allGroupsScreen.components;

import lombok.Getter;
import main.controllers.GroupsController;
import main.model.dto.GroupDto;
import main.ui.App;
import main.ui.components.buttons.StyledButton;
import main.ui.screens.allGroupsScreen.AllGroupsScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GroupButton extends StyledButton {
    @Getter
    private final GroupDto groupDto;

    private final AllGroupsScreen parent;

    public GroupButton(GroupDto groupDto, AllGroupsScreen parent) {
        super(groupDto.getName().getValue());
        this.parent = parent;
        this.groupDto = groupDto;
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addActionListener(this::onClick);
    }

    private void onClick(ActionEvent e){
        if (GroupsController.isGroupDeleteModeOn()) {
            GroupsController.deleteGroup(groupDto, parent);
        } else {
            App.goToGroupScreen(groupDto);
        }
    }
}
