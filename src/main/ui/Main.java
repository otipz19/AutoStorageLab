package main.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.model.dto.GroupDto;

public class Main {
    private static boolean deleteMode = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.getContentPane().setBackground(new Color(0xe9f2fb));
        frame.setResizable(false);
        frame.setLayout(null);

        JButton groupButton = new RoundedButton("Groups");
        groupButton.setContentAreaFilled(true);
        groupButton.setFont(new Font("Arial", Font.BOLD, 20));
        groupButton.setBounds(58, 58, 600, 50);
        groupButton.setEnabled(false);
        groupButton.setBackground(Color.WHITE);
        groupButton.setForeground(new Color(0x203a54));
        groupButton.setOpaque(false);
        groupButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        groupButton.setContentAreaFilled(false);
        groupButton.setHorizontalAlignment(SwingConstants.CENTER);
        groupButton.setVerticalAlignment(SwingConstants.CENTER);
        frame.add(groupButton);

        String[] buttonNames = {"Find in storage", "Add group", "Delete group"};
        for (int i = 0; i < buttonNames.length; i++) {
            JButton button = new RoundedButton(buttonNames[i]);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setBounds(groupButton.getX() + groupButton.getWidth() + 80, ((frame.getHeight()/2)-90) + i * 60, 200, 50);
            button.setBackground(Color.WHITE);
            button.setForeground(new Color(0x628eba));
            button.setOpaque(false);
            button.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            button.setContentAreaFilled(false);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.CENTER);
            button.setFocusPainted(false);
            if (buttonNames[i].equals("Delete group")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deleteMode = !deleteMode;
                        if (deleteMode) {
                            button.setBackground(Color.decode("#334E88"));
                        } else {
                            button.setBackground(Color.WHITE);
                        }
                    }
                });
            }
            frame.add(button);
        }

        List<GroupDto> groups = Arrays.asList(
                new GroupDto("Groupa", "Description 1"),
                new GroupDto("Groupaa", "Description 2"),
                new GroupDto("Groupaaa", "Description 3"),
                new GroupDto("Groupb", "Description 4"),
                new GroupDto("Groupbb", "Description 5"),
                new GroupDto("Groupbbb", "Description 6"),
                new GroupDto("Groupc", "Description 7"),
                new GroupDto("Groupcc", "Description 8"),
                new GroupDto("Groupccc", "Description 9")
        );

        List<JButton> groupButtons = new ArrayList<>();
        for (int i = 0; i < groups.size(); i++) {
            JButton button = new RoundedButton(groups.get(i).getName().getValue());
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.setBounds(groupButton.getX() + (i % 3) * 200, groupButton.getY() + groupButton.getHeight() + (i / 3) * 60 + 20, 200, 50);
            button.setBackground(Color.WHITE);
            button.setForeground(Color.BLACK);
            button.setOpaque(false);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            button.setContentAreaFilled(false);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.CENTER);
            button.setFocusPainted(false);
            final int index = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (deleteMode) {
                        frame.remove(button);
                        groupButtons.remove(button);
                        for (int i = 0; i < groupButtons.size(); i++) {
                            JButton remainingButton = groupButtons.get(i);
                            remainingButton.setBounds(groupButton.getX() + (i % 3) * 200, groupButton.getY() + groupButton.getHeight() + (i / 3) * 60 + 20, 200, 50);
                        }
                        frame.revalidate();
                        frame.repaint();
                    } else {
                        frame.dispose();
                        new GroupFrame(groups.get(index).getName().getValue(), groups.get(index).getDescription());
                    }
                }
            });
            groupButtons.add(button);
            frame.add(button);
        }

        frame.setVisible(true);
    }
}

class RoundedButton extends JButton {
    private Shape shape;

    public RoundedButton(String label) {
        super(label);
        setBorderPainted(false);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorder(new RoundedBorder(10));
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        super.paintComponent(g);
    }
}

class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}