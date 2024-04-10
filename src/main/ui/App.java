package main.ui;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.model.dto.ProductDto;
import main.ui.screens.allGroupsScreen.AllGroupsScreen;
import main.ui.screens.allGroupsSearchScreen.AllGroupsSearchScreen;
import main.ui.screens.groupCreateScreen.GroupCreateScreen;
import main.ui.screens.groupScreen.GroupScreen;
import main.ui.screens.productCreateScreen.ProductCreateScreen;
import main.ui.screens.productPanel.ProductUpdatePanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class of the application.
 * This class is responsible for managing the application's screens and navigation between them.
 */
public class App extends JFrame {
    /**
     * Singleton instance of the App class.
     */
    @Getter
    private static App instance;

    /**
     * Map of screens.
     * This map stores all the screens used in the application, with the screen's class name as the key.
     */
    private class ScreensMap extends HashMap<String, JPanel> {
        /**
         * Adds a screen to the map.
         * @param screen The screen to add.
         */
        public void addScreen(JPanel screen) {
            put(screen.getClass().getName(), screen);
        }
    }

    /**
     * The map of screens.
     */
    private final ScreensMap screens = new ScreensMap();

    /**
     * Constructor.
     * Initializes the application and adds the initial screens.
     */
    public App() {
        instance = this;
        configApp();
        screens.addScreen(new AllGroupsScreen());
        add(screens.get(AllGroupsScreen.class.getName()));
        screens.addScreen(new GroupScreen());
        screens.addScreen(new AllGroupsSearchScreen());
    }

    /**
     * Returns the AllGroupsScreen.
     * @return The AllGroupsScreen.
     */
    public static AllGroupsScreen getAllGroupsScreen() {
        return (AllGroupsScreen) instance.screens.get(AllGroupsScreen.class.getName());
    }

    /**
     * Returns the GroupScreen.
     * @return The GroupScreen.
     */
    public static GroupScreen getGroupScreen() {
        return (GroupScreen) instance.screens.get(GroupScreen.class.getName());
    }

    /**
     * Navigates to the GroupScreen for a specific group.
     * @param groupDto The group to display in the GroupScreen.
     */
    public static void goToGroupScreen(GroupDto groupDto) {
        instance.removeAllScreens();
        GroupScreen groupScreen = new GroupScreen();
        groupScreen.setGroup(groupDto);
        goToScreen(groupScreen);
    }

    /**
     * Navigates to the AllGroupsScreen.
     */
    public static void goToAllGroupsScreen() {
        instance.removeAllScreens();
        AllGroupsScreen allGroupsScreen = new AllGroupsScreen();
        goToScreen(allGroupsScreen);
    }

    /**
     * Navigates to the AllGroupsSearchScreen.
     */
    public static void goToAllGroupsSearchScreen() {
        instance.removeAllScreens();
        AllGroupsSearchScreen allGroupsSearchScreen = (AllGroupsSearchScreen) instance.screens.get(AllGroupsSearchScreen.class.getName());
        instance.add(allGroupsSearchScreen);
        instance.revalidate();
        instance.repaint();
    }

    /**
     * Navigates to the GroupCreateScreen.
     */
    public static void goToGroupCreateScreen() {
        instance.removeAllScreens();
        GroupCreateScreen groupCreateScreen = new GroupCreateScreen();
        goToScreen(groupCreateScreen);
    }

    /**
     * Navigates to the ProductCreateScreen for a specific group.
     * @param groupDto The group for which to create a product.
     */
    public static void goToProductCreateScreen(GroupDto groupDto) {
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

    /**
     * Removes all screens from the JFrame.
     */
    private void removeAllScreens() {
        for (JPanel screen : screens.values()) {
            remove(screen);
        }
    }

    /**
     * Configures the application's settings.
     */
    private void configApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Auto Storage Lab");
        setSize(1000, 750);
        getContentPane().setBackground(new Color(0xe9f2fb));
//        setResizable(false);
        setLocationRelativeTo(null);
    }
}
