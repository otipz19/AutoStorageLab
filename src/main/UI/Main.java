package main.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lab 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setVisible(true);
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
            frame.add(button);
        }

        String[][] groupNames = {{"Group 1", "Group 2", "Group 3"}, {"Group 4", "Group 5", "Group 6"}, {"Group 7", "Group 8", "Group 9"}};
        for (int i = 0; i < groupNames.length; i++) {
            for (int j = 0; j < groupNames[i].length; j++) {
                JButton button = new RoundedButton(groupNames[i][j]);
                button.setFont(new Font("Arial", Font.PLAIN, 20));
                button.setBounds(groupButton.getX() + i * 200, groupButton.getY() + groupButton.getHeight() + j * 60 + 20, 200, 50);
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
                button.setOpaque(false);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                button.setContentAreaFilled(false);
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setVerticalAlignment(SwingConstants.CENTER);
                button.setFocusPainted(false);
                final int finalJ = j;
                final int finalI = i;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        new GroupFrame(groupNames[finalI][finalJ], "Group Description");
                    }
                });
                frame.add(button);
            }
        }

        frame.revalidate();
        frame.repaint();
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