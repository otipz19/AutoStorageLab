package main.controllers;

import lombok.Getter;
import main.model.data.DataContext;
import main.model.data.records.ProductRecord;
import main.model.dto.GroupDto;
import main.model.exceptions.DomainException;
import main.ui.App;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class GroupsController extends BaseController {
    @Getter
    private static boolean isGroupDeleteModeOn;

    public static void switchGroupDeleteMode() {
        isGroupDeleteModeOn = !isGroupDeleteModeOn;
    }

    public static void createGroup(GroupDto groupDto) {
        try {
            DataContext.getInstance().getGroupTable().create(groupDto);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
        }
    }

    public static void deleteGroup(GroupDto groupDto) {
        try {
            DataContext.getInstance().getGroupTable().delete(groupDto.getName());
            App.getAllGroupsScreen().deleteGroup(groupDto);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
        }
    }

    public static void updateGroup() {
        try {
            var groupPanel = App.getGroupScreen();
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

    public static double getTotalPrice() {
        return DataContext.getInstance().getProductTable().calculateTotalPrice();
    }

    public static double calculateTotalPriceByGroup(UUID groupId) {
        List<ProductRecord> records = DataContext.getInstance().getProductTable().getByGroupId(groupId);
        return records.stream()
                .mapToDouble(record -> record.getPrice().getValue() * record.getAmount().getValue())
                .sum();
    }


}
