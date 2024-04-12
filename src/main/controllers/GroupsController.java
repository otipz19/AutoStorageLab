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

/**
 * The GroupsController class provides methods for managing groups in the application.
 * It extends the BaseController class to inherit common controller functionalities.
 */
public class GroupsController extends BaseController {
    @Getter
    private static boolean isGroupDeleteModeOn;

    /**
     * Toggles the group delete mode on and off.
     */
    public static void switchGroupDeleteMode() {
        isGroupDeleteModeOn = !isGroupDeleteModeOn;
    }

    /**
     * Creates a new group.
     *
     * @param groupDto the data transfer object representing the group to be created
     */
    public static void createGroup(GroupDto groupDto) {
        try {
            DataContext.getInstance().getGroupTable().create(groupDto);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
        }
    }

    /**
     * Deletes a group.
     *
     * @param groupDto the data transfer object representing the group to be deleted
     */
    public static void deleteGroup(GroupDto groupDto) {
        try {
            DataContext.getInstance().getGroupTable().delete(groupDto.getName());
            App.getAllGroupsScreen().deleteGroup(groupDto);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
        }
    }

    /**
     * Updates a group.
     */
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

    /**
     * Calculates the total price of all products.
     *
     * @return the total price of all products
     */
    public static double getTotalPrice() {
        return DataContext.getInstance().getProductTable().calculateTotalPrice();
    }

    /**
     * Calculates the total price of all products in a group.
     *
     * @param group the group for which the total price is to be calculated
     * @return the total price of all products in the group
     */
    public static double calculateTotalPriceByGroup(GroupDto group) {
        UUID id = DataContext.getInstance().getGroupTable().get(group.getName()).getId();
        List<ProductRecord> records = DataContext.getInstance().getProductTable().getByGroupId(id);
        return records.stream()
                .mapToDouble(record -> record.getPrice().getValue() * record.getAmount().getValue())
                .sum();
    }


}
