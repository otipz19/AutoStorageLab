package main.controllers;

import lombok.Getter;
import main.model.data.DataContext;
import main.model.data.records.ProductRecord;
import main.model.dto.GroupDto;
import main.model.dto.ProductDto;
import main.model.exceptions.DomainException;
import main.ui.App;
import main.ui.screens.allGroupsScreen.AllGroupsScreen;
import main.ui.screens.groupScreen.GroupScreen;

import javax.swing.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public static void deleteGroup(GroupDto groupDto, AllGroupsScreen allGroupsScreen) {
        try {
            DataContext.getInstance().getGroupTable().delete(groupDto.getName());
            allGroupsScreen.deleteGroup(groupDto);
        } catch (DomainException ex) {
            showExceptionMessage(ex);
        }
    }

    /**
     * Updates a group.
     */
    public static void updateGroup(GroupScreen groupScreen) {
        try {
            var groupTable = DataContext.getInstance().getGroupTable();
            GroupDto oldGroup = groupScreen.getGroup();
            GroupDto toUpdate = groupScreen.getGroupToUpdate();
            UUID groupId = groupTable.get(oldGroup.getName()).getId();
            groupTable.update(groupId, toUpdate);
            groupScreen.setGroup(toUpdate);
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

    public static int getTotalAmount(){
        return DataContext.getInstance().getProductTable().getAll().stream()
                .map(p -> p.getAmount().getValue())
                .mapToInt(i -> i)
                .sum();
    }

    /**
     * Calculates the total price of all products in a group.
     *
     * @param group the group for which the total price is to be calculated
     * @return the total price of all products in the group
     */
    public static double calculateTotalPriceByGroup(GroupDto group) {
        UUID id = getId(group);
        List<ProductRecord> records = DataContext.getInstance().getProductTable().getByGroupId(id);
        return records.stream()
                .mapToDouble(record -> record.getPrice().getValue() * record.getAmount().getValue())
                .sum();
    }

    public static int calculateTotalProductAmountByGroup(GroupDto group){
        UUID id = getId(group);
        return DataContext.getInstance().getProductTable().getByGroupId(id).stream()
                .map(p -> p.getAmount().getValue())
                .mapToInt(i -> i)
                .sum();
    }

    private static UUID getId(GroupDto group) {
        return DataContext.getInstance().getGroupTable().get(group.getName()).getId();
    }
}
