package main.controllers;

import main.model.data.DataContext;
import main.model.dto.GroupDto;
import main.model.exceptions.DomainException;
import main.ui.App;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.forms.group.GroupCreateForm;

import javax.swing.*;
import java.util.UUID;

public class GroupsController {
    public static void createGroup(){
        try{
            GroupDto newGroup = GroupCreateForm.createGroup();
            if (newGroup != null) {
                DataContext.getInstance().getGroupTable().create(newGroup);
                App.getAllGroupsPanel().addGroup(newGroup);
            }
        } catch (DomainException | InvalidFormInputException ex){
            showExceptionMessage(ex);
        }
    }

    public static void deleteGroup(GroupDto groupDto){
        try{
            DataContext.getInstance().getGroupTable().delete(groupDto.getName());
            App.getAllGroupsPanel().deleteGroup(groupDto);
        } catch (DomainException ex){
            showExceptionMessage(ex);
        }
    }

    public static void updateGroup(){
        try {
            var groupPanel = App.getConcreteGroupPanel();
            var groupTable = DataContext.getInstance().getGroupTable();
            GroupDto oldGroup = groupPanel.getGroup();
            GroupDto toUpdate = groupPanel.getGroupToUpdate();
            UUID groupId = groupTable.get(oldGroup.getName()).getId();
            groupTable.update(groupId, toUpdate);
            groupPanel.setGroup(toUpdate);
        } catch (DomainException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static void showExceptionMessage(RuntimeException ex) {
        JOptionPane.showMessageDialog(
                null,
                ex.getMessage(),
                "ERROR",
                JOptionPane.ERROR_MESSAGE);
    }
}
