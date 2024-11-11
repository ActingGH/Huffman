package com.guanhe.src.GUI.frame;

import com.guanhe.src.GUI.listener.CmdButtonListener;
import com.guanhe.src.GUI.listener.UploadButtonListener;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MenuFrame extends JFrame {       //创建新类
    JPanel root;

    static int heigth=550;
    static int width=785;
    static int centerX=260;
    static int centerY=350;


    JButton unzipButton, zipButton, zipWithCodeButton, unzipWithCodeButton, exampleButton;


    public MenuFrame() throws IOException {

        // 创建自定义 JPanel
        root = new BackgroundPanel("E:\\desk\\MyFileToZip\\com\\guanhe\\src\\img\\back2.jpg");

        setContentPane(root);
        setLayout(null);

        //四个按钮设置
        unzipButton = createButton("普通解压",centerX+175 , centerY-25);
        zipButton = createButton("普通压缩", centerX-125, centerY+35);
        zipWithCodeButton = createButton("加密压缩", centerX+175, centerY+35);
        unzipWithCodeButton = createButton("解密解压", centerX-125, centerY-25);
        exampleButton = createButton();
        //添加点击事件
        zipButton.addActionListener(new UploadButtonListener());
        exampleButton.addActionListener(new CmdButtonListener());
        //将按钮添加到面板容器中
        root.add(unzipButton);
        root.add(zipButton);
        root.add(zipWithCodeButton);
        root.add(unzipWithCodeButton);
        root.add(exampleButton);


        //添加说明文档
      //  root.add(createText());
        //添加软件标题
       // root.add(createTitle());

        //设置窗口风格
        setContentPane(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 390,width , heigth);
        setVisible(true);
//        setTitle("可视化文档压缩");
    }

    //main方法
    public static void main(String args[]) throws IOException {
        new MenuFrame();
    }


    //命令行按钮设置
    public static JButton createButton() {
        JButton button = new JButton("命令行模式-原理解析");
        button.setBounds(centerX-125, centerY+95, 450, 50);    //设置按钮显示位置和大小
        Font font = new Font("宋体", Font.PLAIN, 20);
        button.setFont(font);
        Color background = new Color(18, 163, 136);
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        return button;
    }

    //四个常规按钮设置
    public static JButton createButton(String text, int x, int y) {
        JButton tempButton = new JButton(text);
        //按钮添加文字
        //定义显示文本内容的按钮
        tempButton.setBounds(x, y, 150, 50);    //设置按钮显示位置和大小
        Font font = new Font("宋体", Font.PLAIN, 20);
        tempButton.setFont(font);
        Color background = new Color(18, 163, 136);
        tempButton.setBackground(background);
        tempButton.setForeground(Color.WHITE);
        return tempButton;
    }

    public static JLabel createTitleReadme() {
        JLabel title = new JLabel("Author：23级1班 管贺\n" +
                "项目简介:\n" +
                "该项目由纯Java语言编写\n图形化界面使用swing\n文档压缩使用哈夫曼编码算法压缩\n" +
                "支持macos和windows上运行\n" +
                "特别声明，项目已上传到github");

        title.setBounds(350,110,700,40);
        Font font = new Font("黑体", Font.PLAIN, 20);
        title.setFont(font);
        return title;
    }

    //说明文档设置
    public static JTextArea createText() {
        JTextArea textArea = new JTextArea("Author：23级1班 管贺\n" +
                "项目简介:\n" +
                "该项目由纯Java语言编写\n图形化界面使用swing\n文档压缩使用哈夫曼编码算法压缩\n" +
                "支持macos和windows上运行\n" +
                "特别声明，项目已上传到github");
        textArea.setBounds(350, 110, 400, 200); // 设置位置和大小
        textArea.setFont(new Font("黑体", Font.PLAIN, 24)); // 设置字体

        textArea.setForeground(Color.BLACK); // 设置前景色（文字颜色）
        textArea.setLineWrap(true); // 启用自动换行
        textArea.setWrapStyleWord(true); // 按单词换行
        textArea.setOpaque(false);
        textArea.setEditable(false);
        return textArea;
    }

    //软件标题设置
    public static JLabel createTitle(){
        JLabel title = new JLabel("基于哈夫曼编码的java图形化文档压缩软件");
        title.setBounds(50,30,700,40);
        Font font = new Font("黑体", Font.PLAIN, 35);
        title.setFont(font);
        return title;

    }
}
