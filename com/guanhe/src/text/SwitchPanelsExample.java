package com.guanhe.src.text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchPanelsExample extends JFrame {

    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JButton switchButton;

    public SwitchPanelsExample() {
        // 初始化主窗口
        setTitle("Switch Panels Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 初始化面板
        panel1 = createPanel("Panel 1");
        panel2 = createPanel("Panel 2");

        // 初始化按钮
        switchButton = new JButton("Switch Panel");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanel();
            }
        });

        // 初始化主面板
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel1, BorderLayout.CENTER);
        mainPanel.add(switchButton, BorderLayout.SOUTH);

        // 设置主窗口的内容面板
        setContentPane(mainPanel);
    }

    private JPanel createPanel(String text) {
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel(text, SwingConstants.CENTER));
        panel.add(new JTextField());
        panel.add(new JButton("Button in " + text));
        return panel;
    }

    private void switchPanel() {
        if (mainPanel.getComponent(0) == panel1) {
            mainPanel.remove(panel1);
            mainPanel.add(panel2, BorderLayout.CENTER);
        } else {
            mainPanel.remove(panel2);
            mainPanel.add(panel1, BorderLayout.CENTER);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwitchPanelsExample().setVisible(true);
            }
        });
    }
}
