package main.ui.screens.allGroupsScreen;

import main.controllers.GroupsController;
import main.model.data.DataContext;
import main.model.dto.GroupDto;
import main.model.dto.Mapper;
import main.ui.App;
import main.ui.components.StyledLabel;
import main.ui.components.panels.CenteredComponentPanel;
import main.ui.screens.Screen;
import main.ui.screens.allGroupsScreen.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AllGroupsScreen extends Screen {
    private final List<GroupButton> groupButtons = new LinkedList<>();
    private JPanel groupsPanel;
    private StyledLabel totalPriceLabel;

    public AllGroupsScreen() {
        super("All Groups");
        setLayout(new BorderLayout());
        add(createMainPanel(), BorderLayout.CENTER);

        updateTotalPriceLabel();
        loadGroups();
    }

    private JPanel createMainPanel(){
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.add(createDataPanel(), BorderLayout.CENTER);
        mainPanel.add(createActionsPanel(), BorderLayout.EAST);
        return mainPanel;
    }

    private JPanel createDataPanel(){
        JPanel dataPanel = new JPanel(new BorderLayout(20, 20));
        dataPanel.add(new GroupsLabel(), BorderLayout.NORTH);
        dataPanel.add(createGroupsPanel(), BorderLayout.CENTER);
        totalPriceLabel = new StyledLabel("");
        dataPanel.add(totalPriceLabel, BorderLayout.SOUTH);
        return dataPanel;
    }

    private JScrollPane createGroupsPanel(){
        groupsPanel = new JPanel(new GridLayout(10, 3, 5, 5));
        var scroll = new JScrollPane(groupsPanel);
        scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        return scroll;
    }

    private JPanel createActionsPanel(){
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        actionsPanel.add(Box.createVerticalGlue());
        actionsPanel.add(createBtnsPanel());
        actionsPanel.add(Box.createVerticalGlue());
        return actionsPanel;
    }

    private JPanel createBtnsPanel(){
        JPanel btnsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        btnsPanel.add(new CenteredComponentPanel(new FindInStorageButton()));
        btnsPanel.add(new CenteredComponentPanel(new AddGroupButton()));
        btnsPanel.add(new CenteredComponentPanel(new DeleteGroupButton()));
        return btnsPanel;
    }

    private void loadGroups(){
        DataContext.getInstance().getGroupTable().getAll()
                .stream()
                .map(Mapper::map)
                .sorted(Comparator.comparing(g -> g.getName().getValue()))
                .forEach(this::addGroup);
    }

    public void addGroup(GroupDto groupDto){
        GroupButton newGroupButton = new GroupButton(groupDto, this);
        groupButtons.add(newGroupButton);
        groupsPanel.add(newGroupButton);
    }

    public void deleteGroup(GroupDto groupDto) {
        GroupButton toRemove = groupButtons.stream()
                .filter(btn -> btn.getGroupDto().getName().equals(groupDto.getName()))
                .findFirst().get();
        groupButtons.remove(toRemove);
        groupsPanel.remove(toRemove);
        groupsPanel.revalidate();
        groupsPanel.repaint();
    }

    public void updateTotalPriceLabel() {
        double totalPrice = GroupsController.getTotalPrice();
        totalPriceLabel.setText("Total price of all products: " + totalPrice);
    }
}
