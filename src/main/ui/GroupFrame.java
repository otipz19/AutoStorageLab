// GroupFrame.java
package main.ui;

import main.model.data.DataContext;
import main.model.dto.GroupDto;
import main.model.dto.Mapper;
import main.model.exceptions.DomainException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class GroupFrame extends JFrame {
    private GroupDto group;

    private JTextField searchField;
    private JTextField groupNameField;
    private JTextArea descriptionArea;
    private JPanel searchResultsPanel;
    private JButton editNameBtn;
    private JButton editDescriptionBtn;
    private JButton createProductBtn;

    public GroupFrame(GroupDto group) {
        this.group = group;
        setTitle("Group Details");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Set layout manager to null

        groupNameField = new JTextField(group.getName().getValue());
        groupNameField.setBounds(158, 58, 580, 50); // Adjusted x-coordinate
        groupNameField.setBackground(Color.WHITE);
        groupNameField.setForeground(new Color(0x203a54));
        groupNameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        groupNameField.setHorizontalAlignment(SwingConstants.CENTER);
        groupNameField.setEditable(false);
        add(groupNameField);

        descriptionArea = new JTextArea(group.getDescription());
        descriptionArea.setBounds(158, 108, 580, 50); // Adjusted x-coordinate
        descriptionArea.setEditable(false);
        add(descriptionArea);

        searchField = new JTextField();
        searchField.setBounds(158, 158, 580, 50);
        add(searchField);

        ImageIcon penIcon = new ImageIcon("src/main/UI/pen.png");
        ImageIcon checkIcon = new ImageIcon("src/main/UI/checkmark.png");

        /// Add new square button next to group name
        editNameBtn = new SquareButton("", 10);
        editNameBtn.setBounds(748, 58, 50, 50);
        editNameBtn.setBackground(Color.WHITE);
        editNameBtn.setIcon(new ImageIcon(penIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); // Resize the pen icon
        editNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (groupNameField.isEditable()) {
                    groupNameField.setEditable(false);
                    editNameBtn.setIcon(new ImageIcon(penIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                    updateGroup();
                } else {
                    groupNameField.setEditable(true);
                    editNameBtn.setIcon(new ImageIcon(checkIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                }
            }
        });
        add(editNameBtn);

        // Add new square button next to group description
        editDescriptionBtn = new SquareButton("", 10);
        editDescriptionBtn.setBounds(748, 108, 50, 50);
        editDescriptionBtn.setBackground(Color.WHITE);
        editDescriptionBtn.setIcon(new ImageIcon(penIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        editDescriptionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (descriptionArea.isEditable()) {
                    descriptionArea.setEditable(false);
                    editDescriptionBtn.setIcon(new ImageIcon(penIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                    updateGroup();
                } else {
                    descriptionArea.setEditable(true);
                    editDescriptionBtn.setIcon(new ImageIcon(checkIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
                }
            }
        });
        add(editDescriptionBtn);

        createProductBtn = new SquareButton("", 10);
        createProductBtn.setBounds(748, 158, 50, 50);
        createProductBtn.setBackground(Color.WHITE);
        ImageIcon plusIcon = new ImageIcon("src/main/UI/plus.png");
        createProductBtn.setIcon(new ImageIcon(plusIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH))); // Resize the plus icon
        add(createProductBtn);

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

    private void updateGroup() {
        try {
            UUID groupId = DataContext.getInstance().getGroupTable().get(group.getName()).getId();
            GroupDto toUpdate = new GroupDto(groupNameField.getText(), descriptionArea.getText());
            DataContext.getInstance().getGroupTable().update(groupId, toUpdate);
            group = Mapper.map(DataContext.getInstance().getGroupTable().get(groupId));
        } catch (DomainException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius); // Draw a rounded rectangle
        super.paintComponent(g);
    }
}