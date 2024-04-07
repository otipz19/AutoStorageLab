package main.ui.forms;

import javax.swing.*;

public class GroupCreateDemo extends JFrame {
    public GroupCreateDemo(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Group Create Demo");
        setSize(500, 500);
        setLocationRelativeTo(null);
        GroupCreateForm.createGroup();
    }

    public static void main(String[] args) {
        new GroupCreateDemo().setVisible(true);
    }
}
