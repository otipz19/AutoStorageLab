package main.ui;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.ui.screens.allGroupsScreen.AllGroupsScreen;
import main.ui.screens.groupCreateScreen.GroupCreatePanel;
import main.ui.screens.groupCreateScreen.GroupCreateScreen;
import main.ui.screens.groupScreen.GroupScreen;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    @Getter
    private static App instance;

    private AllGroupsScreen allGroupsScreen;
    private GroupScreen groupScreen;
    private GroupCreateScreen groupCreateScreen;

    public App(){
        instance = this;
        configApp();
        allGroupsScreen = new AllGroupsScreen();
        add(allGroupsScreen);
        groupScreen = new GroupScreen();
    }

    public static AllGroupsScreen getAllGroupsScreen(){
        return instance.allGroupsScreen;
    }

    public static GroupScreen getGroupScreen(){
        return instance.groupScreen;
    }

    public static void goToGroupScreen(GroupDto groupDto){
        instance.remove(instance.allGroupsScreen);
        instance.groupScreen.setGroup(groupDto);
        instance.add(instance.groupScreen);
        instance.revalidate();
        instance.repaint();
    }

    public static void goToAllGroupsScreen(){
        instance.remove(instance.groupScreen);
        instance.remove(instance.groupCreateScreen);
        instance.add(instance.allGroupsScreen);
        instance.revalidate();
        instance.repaint();
    }

    public static void goToGroupCreateScreen(){
        instance.remove(instance.allGroupsScreen);
        instance.groupCreateScreen = new GroupCreateScreen();
        instance.add(instance.groupCreateScreen);
        instance.revalidate();
        instance.repaint();
    }

    private void configApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Auto Storage Labe");
        setSize(1000, 750);
        getContentPane().setBackground(new Color(0xe9f2fb));
//        setResizable(false);
        setLocationRelativeTo(null);
    }
}