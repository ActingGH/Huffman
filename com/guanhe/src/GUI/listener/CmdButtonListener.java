package com.guanhe.src.GUI.listener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


//用户点击样例按钮时执行该方法
public class CmdButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        Runtime runtime = Runtime.getRuntime();

        try {
            String filePath = "\"E:\\desk\\Myexe\\HuffmaDemo.exe\"";

            // 对于 Windows 系统，直接执行
            Runtime.getRuntime().exec(filePath);
            System.out.println("应用程序已启动: " + filePath);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}
