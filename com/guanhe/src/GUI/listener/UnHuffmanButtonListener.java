package com.guanhe.src.GUI.listener;

import com.guanhe.src.feature.Func1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class UnHuffmanButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,"请要解密解压的文件 *.huffman-gh");
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        String path1=null;

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("选择了文件: " + selectedFile.getAbsolutePath());
            path1=selectedFile.getAbsolutePath();
        }
        JOptionPane.showMessageDialog(null,"请选择秘钥 *.huffman-code");
        result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("选择了文件: " + selectedFile.getAbsolutePath());

            try {
                String ans= Func1.commonFunc2(path1,selectedFile.getAbsolutePath());
                JOptionPane.showMessageDialog(null,ans);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        }

    }
}
