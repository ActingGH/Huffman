package com.guanhe.src.GUI.listener;

import com.guanhe.src.feature.Func1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class UploadButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("选择了文件: " + selectedFile.getAbsolutePath());
            try {
                   String ans= Func1.commonFunc1(selectedFile.getAbsolutePath());
                 JOptionPane.showMessageDialog(null,ans);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }

    }
}
