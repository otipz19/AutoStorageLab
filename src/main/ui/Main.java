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
import main.ui.forms.group.GroupCreateForm;

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
        List<GroupDto> groups = new ArrayList<>();
        List<JButton> groupButtons = new ArrayList<>();

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
            } else if (buttonNames[i].equals("Add group")) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GroupDto newGroup = GroupCreateForm.createGroup();
                        if (newGroup != null) {
                            groups.add(newGroup);
                            JButton newGroupButton = new RoundedButton(newGroup.getName().getValue());
                            newGroupButton.setFont(new Font("Arial", Font.PLAIN, 20));
                            newGroupButton.setBounds(groupButton.getX() + ((groups.size() - 1) % 3) * 200, groupButton.getY() + groupButton.getHeight() + ((groups.size() - 1) / 3) * 60 + 20, 200, 50);
                            newGroupButton.setBackground(Color.WHITE);
                            newGroupButton.setForeground(Color.BLACK);
                            newGroupButton.setOpaque(false);
                            newGroupButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                            newGroupButton.setContentAreaFilled(false);
                            newGroupButton.setHorizontalAlignment(SwingConstants.CENTER);
                            newGroupButton.setVerticalAlignment(SwingConstants.CENTER);
                            newGroupButton.setFocusPainted(false);
                            final int index = groups.size() - 1;
                            newGroupButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (deleteMode) {
                                        frame.remove(newGroupButton);
                                        groupButtons.remove(newGroupButton);
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
                            groupButtons.add(newGroupButton);
                            frame.add(newGroupButton);
                            frame.revalidate();
                            frame.repaint();
                        }
                    }
                });
            }

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