package main.ui;

import lombok.Getter;

public class GlobalStateManager {
    @Getter
    private static boolean isGroupDeleteModeOn;

    public static void switchGroupDeleteMode(){
        isGroupDeleteModeOn = !isGroupDeleteModeOn;
    }
}
