package main.ui;

import main.model.serialization.DataSerializer;

public class Main {

    /**
     * Main method to start the application
     * @param args
     */
    public static void main(String[] args) {
        initData();
        App app = new App();
        app.addWindowListener(DataSerializer.ON_CLOSING_SERIALIZATION_LISTENER);
        app.setVisible(true);
    }

    private static void initData(){
        DataSerializer.load();
    }
}