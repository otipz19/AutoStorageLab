package main.ui.forms.components;

import main.model.data.DataContext;

import javax.swing.*;

public class GroupsComboBox extends JComboBox<String> {
    public GroupsComboBox(){
        DataContext.getInstance().getGroupTable().getAll()
                .stream()
                .map(r -> r.getName().getValue())
                .forEach(this::addItem);
    }
}
