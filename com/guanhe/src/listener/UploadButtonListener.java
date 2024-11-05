package com.guanhe.src.listener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static com.guanhe.src.mydemo.myhuffman.getZip;

public class UploadButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("选择了文件: " + selectedFile.getAbsolutePath());
            try {
                getZip(selectedFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(null,"请选择压缩文件输出位置（务必选择文件夹）");
        }
        JFileChooser fileChooserTarget=new JFileChooser();
        // 设置 JFileChooser 为选择文件夹模式
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // 显示文件选择对话框
        int result2 = fileChooserTarget.showOpenDialog(null);
        if (result2 == JFileChooser.APPROVE_OPTION) {
            // 获取选择的文件夹
            java.io.File selectedFolder = fileChooser.getSelectedFile();
            // 显示选择的文件夹路径
            JOptionPane.showMessageDialog(null, "选择的文件夹: " + selectedFolder.getAbsolutePath());
        }

    }
}
