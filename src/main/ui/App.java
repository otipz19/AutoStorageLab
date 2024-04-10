package main.ui;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.ui.screens.allGroupsScreen.AllGroupsScreen;
import main.ui.screens.groupCreateScreen.GroupCreateScreen;
import main.ui.screens.groupScreen.GroupScreen;
import main.ui.screens.productCreateScreen.ProductCreateScreen;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame {
    @Getter
    private static App instance;


    private class ScreensMap extends HashMap<String, JPanel>{
        public void addScreen(JPanel screen){
            put(screen.getClass().getName(), screen);
        }
    }

    private final ScreensMap screens = new ScreensMap();

    public App(){
        instance = this;
        configApp();
        screens.addScreen(new AllGroupsScreen());
        add(screens.get(AllGroupsScreen.class.getName()));
        screens.addScreen(new GroupScreen());
    }

    public static AllGroupsScreen getAllGroupsScreen(){
        return (AllGroupsScreen) instance.screens.get(AllGroupsScreen.class.getName());
    }

    public static GroupScreen getGroupScreen(){
        return (GroupScreen) instance.screens.get(GroupScreen.class.getName());
    }

    public static void goToGroupScreen(GroupDto groupDto){
        instance.removeAllScreens();
        GroupScreen groupScreen = (GroupScreen) instance.screens.get(GroupScreen.class.getName());
        groupScreen.setGroup(groupDto);
        instance.add(groupScreen);
        instance.revalidate();
        instance.repaint();
    }

    public static void goToAllGroupsScreen(){
        instance.removeAllScreens();
        AllGroupsScreen allGroupsScreen = (AllGroupsScreen) instance.screens.get(AllGroupsScreen.class.getName());
        instance.add(allGroupsScreen);
        instance.revalidate();
        instance.repaint();
    }

    public static void goToGroupCreateScreen(){
        instance.removeAllScreens();
        GroupCreateScreen groupCreateScreen = new GroupCreateScreen();
        instance.screens.addScreen(groupCreateScreen);
        instance.add(groupCreateScreen);
        instance.revalidate();
        instance.repaint();
    }

    public static void goToProductCreateScreen(GroupDto groupDto){
        instance.removeAllScreens();
        ProductCreateScreen productCreateScreen = new ProductCreateScreen(groupDto);
        instance.screens.addScreen(productCreateScreen);
        instance.add(productCreateScreen);
        instance.revalidate();
        instance.repaint();
    }

    private void removeAllScreens(){
        for(JPanel screen : screens.values()){
            remove(screen);
        }
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
