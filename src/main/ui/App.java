package main.ui;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.model.dto.ProductDto;
import main.ui.screens.allGroupsScreen.AllGroupsScreen;
import main.ui.screens.groupCreateScreen.GroupCreateScreen;
import main.ui.screens.groupScreen.GroupScreen;
import main.ui.screens.productCreateScreen.ProductCreateScreen;
import main.ui.screens.productPanel.ProductUpdatePanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class App extends JFrame {
    @Getter
    private static App instance;

    private static class ScreensMap extends HashMap<String, JPanel>{
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
//        GroupScreen groupScreen = (GroupScreen) instance.screens.get(GroupScreen.class.getName());
        GroupScreen groupScreen = new GroupScreen();
        groupScreen.setGroup(groupDto);
        goToScreen(groupScreen);
    }

    public static void goToAllGroupsScreen(){
        instance.removeAllScreens();
//        AllGroupsScreen allGroupsScreen = (AllGroupsScreen) instance.screens.get(AllGroupsScreen.class.getName());
        AllGroupsScreen allGroupsScreen = new AllGroupsScreen();
        goToScreen(allGroupsScreen);
    }

    public static void goToGroupCreateScreen(){
        instance.removeAllScreens();
        GroupCreateScreen groupCreateScreen = new GroupCreateScreen();
        goToScreen(groupCreateScreen);
    }

    public static void goToProductCreateScreen(GroupDto groupDto){
        instance.removeAllScreens();
        ProductCreateScreen productCreateScreen = new ProductCreateScreen(groupDto);
        goToScreen(productCreateScreen);
    }

    public static void goToProductUpdateScreen(ProductDto productDto){
        instance.removeAllScreens();
        ProductUpdatePanel productUpdatePanel = new ProductUpdatePanel(productDto);
        goToScreen(productUpdatePanel);
    }

    private static void goToScreen(JPanel panel){
        instance.screens.addScreen(panel);
        instance.add(panel);
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
        setTitle("Auto Storage Lab");
        setSize(1000, 750);
        getContentPane().setBackground(new Color(0xe9f2fb));
//        setResizable(false);
        setLocationRelativeTo(null);
    }
}
