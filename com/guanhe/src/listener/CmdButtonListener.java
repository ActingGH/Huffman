package com.guanhe.src.listener;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;


//用户点击样例按钮时执行该方法
public class CmdButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 根据操作系统选择合适的命令
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("windows")) {
                Runtime.getRuntime().exec("cmd.exe");
            } else if (osName.contains("mac")) {
                Runtime.getRuntime().exec("open -a Terminal");
            } else {
                //JOptionPane.showMessageDialog(frame, "不支持的操作系统");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("12346");
        }
    }
}
