package com.guanhe.src.text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputDialogExample extends JFrame {

    private JTextField textField;
    private JButton inputButton;
    private JLabel displayLabel;

    public InputDialogExample() {
        // 初始化主窗口
        setTitle("Input Dialog Example");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 初始化组件
        textField = new JTextField(20);
        inputButton = new JButton("Enter Text");
        displayLabel = new JLabel("No input yet", SwingConstants.CENTER);

        // 设置按钮的监听器
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInputDialog();
            }
        });

        // 初始化布局
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(textField);
        panel.add(inputButton);
        panel.add(displayLabel);

        // 设置主窗口的内容面板
        setContentPane(panel);
    }

    private void showInputDialog() {
        // 弹出输入对话框
        String input = JOptionPane.showInputDialog(this, "Enter your text:", "Input Dialog", JOptionPane.QUESTION_MESSAGE);

        // 检查用户是否取消了对话框
        if (input != null && !input.trim().isEmpty()) {
            // 更新标签显示用户输入的内容
            displayLabel.setText("You entered: " + input);
        } else {
            displayLabel.setText("No input provided");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InputDialogExample().setVisible(true);
            }
        });
    }
}
