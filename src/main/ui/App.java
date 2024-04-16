package main.ui;

import lombok.Getter;
import main.model.dto.GroupDto;
import main.model.dto.ProductDto;
import main.ui.screens.Screen;
import main.ui.screens.allGroupsScreen.AllGroupsScreen;
import main.ui.screens.allGroupsSearchScreen.AllGroupsSearchScreen;
import main.ui.screens.groupCreateScreen.GroupCreateScreen;
import main.ui.screens.groupScreen.GroupScreen;
import main.ui.screens.productCreateScreen.ProductCreateScreen;
import main.ui.screens.productScreen.ProductUpdateScreen;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Stack;

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
    private class ScreensMap extends HashMap<String, Screen> {
        /**
         * Adds a screen to the map.
         * @param screen The screen to add.
         */
        public void addScreen(Screen screen) {
            put(screen.getClass().getName(), screen);
        }
    }

    /**
     * The map of screens.
     */
    private final ScreensMap screens = new ScreensMap();

    private final Stack<Screen> navigationStack = new Stack<>();

    /**
     * Constructor.
     * Initializes the application and adds the initial screens.
     */
    public App() {
        instance = this;
        configApp();
        goToScreen(new AllGroupsScreen());
    }

    /**
     * Navigates to the GroupScreen for a specific group.
     * @param groupDto The group to navigate to.
     */
    public static void goToGroupScreen(GroupDto groupDto) {
        goToScreen(new GroupScreen(groupDto));
    }

    /**
     * Navigates to the AllGroupsScreen.
     */
    public static void goToAllGroupsScreen() {
        goToScreen(new AllGroupsScreen());
    }

    /**
     * Navigates to the AllGroupsSearchScreen.
     */
    public static void goToAllGroupsSearchScreen() {
        goToScreen(new AllGroupsSearchScreen());
    }

    /**
     * Navigates to the GroupCreateScreen.
     */
    public static void goToGroupCreateScreen() {
        goToScreen(new GroupCreateScreen());
    }

    /**
     * Navigates to the ProductCreateScreen for a specific group.
     * @param groupDto The group for which to create a product.
     */
    public static void goToProductCreateScreen(GroupDto groupDto) {
        goToScreen(new ProductCreateScreen(groupDto));
    }

    public static void goToProductUpdateScreen(ProductDto productDto){
        goToScreen(new ProductUpdateScreen(productDto));
    }

    public static void returnToPreviousScreen(){
        Screen screen = new AllGroupsScreen();
        if(instance.navigationStack.size() > 1){
            instance.navigationStack.pop();
            screen = instance.navigationStack.peek();
        }
        screen.updateState();
        goToScreen(screen);
        instance.navigationStack.pop();
    }

    private static void goToScreen(Screen screen){
        instance.navigationStack.push(screen);
        instance.removeAllScreens();
        instance.screens.addScreen(screen);
        instance.add(screen);
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
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
