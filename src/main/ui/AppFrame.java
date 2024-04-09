package main.ui;

import lombok.Getter;
import main.ui.panels.GroupsPanel;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    @Getter
    private static AppFrame instance;

    private GroupsPanel groupsPanel;

    public AppFrame(){
        instance = this;
        configApp();
        groupsPanel = new GroupsPanel();
        add(groupsPanel);
    }

    public static GroupsPanel getGroupsPanel(){
        return instance.groupsPanel;
    }

    private void configApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Auto Storage Labe");
        setSize(1000, 750);
        getContentPane().setBackground(new Color(0xe9f2fb));
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
