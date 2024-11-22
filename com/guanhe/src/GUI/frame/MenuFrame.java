package com.guanhe.src.GUI.frame;

import com.guanhe.src.GUI.listener.CmdButtonListener;
import com.guanhe.src.GUI.listener.UnHuffmanButtonListener;
import com.guanhe.src.GUI.listener.UploadButtonListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;

public class MenuFrame extends JFrame {       //创建新类
    JPanel root;



    JButton unzipButton, zipButton, exampleButton;


    public MenuFrame() throws IOException {

        // 创建自定义 JPanel
        root = new BackgroundPanel("E:\\desk\\MyFileToZip\\com\\guanhe\\src\\img\\back2.jpg");

        setContentPane(root);
        setLayout(null);

        //四个按钮设置
        unzipButton = createButton("普通解压",425 , 400);
        zipButton = createButton("普通压缩", 125, 400);
        exampleButton = createButton();
        //添加点击事件
        zipButton.addActionListener(new UploadButtonListener());
        exampleButton.addActionListener(new CmdButtonListener());
        unzipButton.addActionListener(new UnHuffmanButtonListener());
        //将按钮添加到面板容器中
        root.add(unzipButton);
        root.add(zipButton);
        root.add(exampleButton);


        //添加说明文档
        root.add(createText());
        //添加软件标题
        root.add(createTitle());
        root.add(createImageLabel());

        //设置窗口风格
        setContentPane(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 390,800 , 600);
        setVisible(true);
        setWhiteBorder();
//        setTitle("可视化文档压缩");
    }

    private void setWhiteBorder(){
        LineBorder whiteBorder = new LineBorder(Color.WHITE, 2);
        // 获取 JRootPane 并设置其边框
        getRootPane().setBorder(whiteBorder);

    }

    //main方法
    public static void main(String args[]) throws IOException {
        new MenuFrame();
    }


    //命令行按钮设置
    public static JButton createButton() {
        JButton button = new JButton("命令行模式-原理解析");
        button.setBounds(125, 475, 450, 50);    //设置按钮显示位置和大小
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


    //说明文档设置
    public static JTextArea createText() {
        JTextArea textArea = new JTextArea("Author：23级1班 管贺\n" +
                "项目简介:\n" +
                "该项目由Java语言开发\n图形化界面使用swing\n文档压缩使用哈夫曼编码算法压缩\n序列化哈夫曼树使用Hessian\n" +
                "支持macos和windows上运行\n" +
                "github:https://github.com/ActingGH/Huffman");
        textArea.setBounds(350, 110, 400, 300); // 设置位置和大小
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


    // 图片设置
    public static JLabel createImageLabel() {
        // 创建 ImageIcon 对象，从文件中加载图片
        ImageIcon imageIcon = new ImageIcon("com/guanhe/src/GUI/img/img.png");

        // 创建 JLabel 对象，并设置图标
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(50, 110, 250, 250); // 设置图片显示位置和大小

        return imageLabel;
    }
}
