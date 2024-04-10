// GroupFrame.java
package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupFrame extends JFrame {
    private JTextField searchField;
    private JTextField groupNameField;
    private JTextArea descriptionArea;
    private JButton groupNameButton;
    private JPanel searchResultsPanel;
    private JButton squareButton1;
    private JButton squareButton2;
    private JButton squareButton3;

    public GroupFrame(String groupName, String groupDescription) {
        setTitle("Group Details");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Set layout manager to null

        groupNameField = new JTextField(groupName);
        groupNameField.setBounds(158, 58, 580, 50); // Adjusted x-coordinate
        groupNameField.setBackground(Color.WHITE);
        groupNameField.setForeground(new Color(0x203a54));
        groupNameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        groupNameField.setHorizontalAlignment(SwingConstants.CENTER);
        groupNameField.setEditable(false);
        add(groupNameField);

        descriptionArea = new JTextArea(groupDescription);
        descriptionArea.setBounds(158, 108, 580, 50); // Adjusted x-coordinate
        descriptionArea.setEditable(false);
        add(descriptionArea);

        searchField = new JTextField();
        searchField.setBounds(158, 158, 580, 50);
        add(searchField);

        ImageIcon penIcon = new ImageIcon("src/main/UI/pen.png");
        ImageIcon checkIcon = new ImageIcon("src/main/UI/checkmark.png");

        /// Add new square button next to group name
        squareButton1 = new SquareButton("", 10);
        squareButton1.setBounds(748, 58, 50, 50);
        squareButton1.setBackground(Color.WHITE);
        squareButton1.setIcon(new ImageIcon(penIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); // Resize the pen icon
        squareButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (groupNameField.isEditable()) {
                    groupNameField.setEditable(false);
                    squareButton1.setIcon(new ImageIcon(penIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                } else {
                    groupNameField.setEditable(true);
                    squareButton1.setIcon(new ImageIcon(checkIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                }
            }
        });
        add(squareButton1);

        // Add new square button next to group description
        squareButton2 = new SquareButton("", 10);
        squareButton2.setBounds(748, 108, 50, 50);
        squareButton2.setBackground(Color.WHITE);
        squareButton2.setIcon(new ImageIcon(penIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        squareButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (descriptionArea.isEditable()) {
                    descriptionArea.setEditable(false);
                    squareButton2.setIcon(new ImageIcon(penIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                } else {
                    descriptionArea.setEditable(true);
                    squareButton2.setIcon(new ImageIcon(checkIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                }
            }
        });
        add(squareButton2);

        squareButton3 = new SquareButton("", 10);
        squareButton3.setBounds(748, 158, 50, 50);
        squareButton3.setBackground(Color.WHITE);
        ImageIcon plusIcon = new ImageIcon("src/main/UI/plus.png");
        squareButton3.setIcon(new ImageIcon(plusIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); // Resize the plus icon
        add(squareButton3);

        searchResultsPanel = new JPanel();
        searchResultsPanel.setBounds(158, 208, 580, 500);
        add(searchResultsPanel);

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        getContentPane().setBackground(new Color(0xe9f2fb));

        setVisible(true);
    }
}

class SquareButton extends JButton {
    private int radius;

    public SquareButton(String label, int radius) {
        super(label);
        this.radius = radius; // Initialize the radius
        setBorderPainted(false);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorder(new RoundedBorder(radius));
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius); // Draw a rounded rectangle
        super.paintComponent(g);
    }
} 