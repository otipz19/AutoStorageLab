package main.ui;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.ui.panels.allGroupsPanel.AllGroupsPanel;
import main.ui.panels.concreteGroupPanel.ConcreteGroupPanel;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    @Getter
    private static App instance;

    private final AllGroupsPanel allGroupsPanel;
    private final ConcreteGroupPanel concreteGroupPanel;

    public App(){
        instance = this;
        configApp();
        allGroupsPanel = new AllGroupsPanel();
        add(allGroupsPanel);
        concreteGroupPanel = new ConcreteGroupPanel();
    }

    public static AllGroupsPanel getAllGroupsPanel(){
        return instance.allGroupsPanel;
    }

    public static ConcreteGroupPanel getConcreteGroupPanel(){
        return instance.concreteGroupPanel;
    }

    public static void goToConcreteGroupPanel(GroupDto groupDto){
        instance.remove(instance.allGroupsPanel);
        instance.concreteGroupPanel.setGroup(groupDto);
        instance.add(instance.concreteGroupPanel);
        instance.revalidate();
        instance.repaint();
    }

    public static void goToAllGroupsPanel(){
        instance.remove(instance.concreteGroupPanel);
        instance.add(instance.allGroupsPanel);
        instance.revalidate();
        instance.repaint();
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
