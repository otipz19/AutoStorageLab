package main.ui.screens.allGroupsScreen;

import main.controllers.GroupsController;
import main.model.data.DataContext;
import main.model.dto.GroupDto;
import main.model.dto.Mapper;
import main.ui.App;
import main.ui.screens.allGroupsScreen.components.*;
import main.ui.screens.allGroupsSearchScreen.AllGroupsSearchScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AllGroupsScreen extends JPanel {
    private final List<GroupButton> groupButtons = new LinkedList<>();
    private final GroupsLabelButton groupsLabelButton;
    private JLabel totalPriceLabel;

    public AllGroupsScreen() {
        App.getInstance().setTitle("All Groups");
        setLayout(null);
        groupsLabelButton = new GroupsLabelButton();
        groupsLabelButton.setBounds(58, 58, 600, 50);
        add(groupsLabelButton);
        drawActionBtns();
        loadGroups();

        totalPriceLabel = new JLabel();
        totalPriceLabel.setBounds(58, 640, 600, 50); // Set bounds for totalPriceLabel
        add(totalPriceLabel);
        updateTotalPriceLabel();
    }

    private void drawActionBtns() {
        FindInStorageButton findInStorageButton = new FindInStorageButton();
        findInStorageButton.addActionListener(e -> App.goToAllGroupsSearchScreen());
        List<JButton> actionBtns = Arrays.asList(
                new JButton[]{
                        findInStorageButton,
                        new AddGroupButton(),
                        new DeleteGroupButton()
                }
        );
        for (int i = 0; i < actionBtns.size(); i++) {
            JButton button = actionBtns.get(i);
            button.setBounds(groupsLabelButton.getX() + groupsLabelButton.getWidth() + 80, ((App.getInstance().getHeight() / 2) - 90) + i * 60, 200, 50);
            add(button);
        }
    }

    private void loadGroups(){
        DataContext.getInstance().getGroupTable().getAll()
                .stream()
                .map(Mapper::map)
                .forEach(this::addGroup);
    }

    public void addGroup(GroupDto groupDto){
        GroupButton newGroupButton = new GroupButton(groupDto, this);
        groupButtons.add(newGroupButton);
        add(newGroupButton);
        newGroupButton.setBounds(groupsLabelButton.getX() + ((groupButtons.size() - 1) % 3) * 200, groupsLabelButton.getY() + groupsLabelButton.getHeight() + ((groupButtons.size() - 1) / 3) * 60 + 20, 200, 50);
        revalidate();
        repaint();
    }

    public void deleteGroup(GroupDto groupDto) {
        GroupButton toRemove = groupButtons.stream()
                .filter(btn -> btn.getGroupDto().getName().equals(groupDto.getName()))
                .findFirst().get();
        groupButtons.remove(toRemove);
        remove(toRemove);
        for (int i = 0; i < groupButtons.size(); i++) {
            JButton remainingButton = groupButtons.get(i);
            remainingButton.setBounds(groupsLabelButton.getX() + (i % 3) * 200, groupsLabelButton.getY() + groupsLabelButton.getHeight() + (i / 3) * 60 + 20, 200, 50);
        }
        revalidate();
        repaint();
    }

    public void updateTotalPriceLabel() {
        double totalPrice = GroupsController.getTotalPrice();
        totalPriceLabel.setText("Total price of all products: " + totalPrice);
    }
}
