package main.controllers;

import main.model.data.DataContext;
import main.model.dto.GroupDto;
import main.model.exceptions.DomainException;
import main.ui.AppFrame;
import main.ui.exceptions.InvalidFormInputException;
import main.ui.forms.group.GroupCreateForm;

import javax.swing.*;

public class GroupsController {
    public static void createGroup(){
        try{
            GroupDto newGroup = GroupCreateForm.createGroup();
            if (newGroup != null) {
                DataContext.getInstance().getGroupTable().create(newGroup);
                AppFrame.getGroupsPanel().addGroup(newGroup);
            }
        } catch (DomainException | InvalidFormInputException ex){
            showExceptionMessage(ex);
        }
    }

    public static void deleteGroup(GroupDto groupDto){
        try{
            DataContext.getInstance().getGroupTable().delete(groupDto.getName());
            AppFrame.getGroupsPanel().deleteGroup(groupDto);
        } catch (DomainException ex){
            showExceptionMessage(ex);
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
