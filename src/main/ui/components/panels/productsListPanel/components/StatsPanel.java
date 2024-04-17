package main.ui.components.panels.productsListPanel.components;

import lombok.Getter;
import main.controllers.GroupsController;
import main.model.dto.GroupDto;
import main.ui.components.StyledLabel;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
    @Getter
    private StyledLabel groupTotalAmountLabel;
    @Getter
    private StyledLabel groupTotalPriceLabel;

    public StatsPanel() {
        setLayout(new GridLayout(1, 2));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        groupTotalAmountLabel = new StyledLabel("");
        add(groupTotalAmountLabel);
        groupTotalPriceLabel = new StyledLabel("");
        add(groupTotalPriceLabel);
    }

    /**
     * @param group Pass null to load stats of products in all groups
     */
    public void updateStatsLabels(GroupDto group) {
        double groupTotalPrice = group != null ?
                GroupsController.calculateTotalPriceByGroup(group) :
                GroupsController.getTotalPrice();
        groupTotalPriceLabel.setText("Total price of products: " + String.format("%.2f", groupTotalPrice));
        int totalAmount = group != null ?
                GroupsController.calculateTotalProductAmountByGroup(group) :
                GroupsController.getTotalAmount();
        groupTotalAmountLabel.setText("Total amount of products: " + totalAmount);
    }
}
