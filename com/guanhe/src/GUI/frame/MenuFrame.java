package com.guanhe.src.GUI.frame;

import com.guanhe.src.GUI.listener.UnHuffmanButtonListener;
import com.guanhe.src.GUI.listener.UploadButtonListener;
import com.guanhe.src.feature.Func1;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;

public class MenuFrame extends JFrame {
    JPanel root;
    JButton unzipButton, zipButton, exampleButton, closeButton;
    JTextArea resultTextArea;

    public MenuFrame() throws IOException {
        root = new BackgroundPanel("E:\\desk\\MyFileToZip\\com\\guanhe\\src\\img\\back2.jpg");
        setContentPane(root);
        setLayout(null);

        // 四个按钮设置
        unzipButton = createButton("普通解压", 425, 400);
        zipButton = createButton("普通压缩", 125, 400);
        exampleButton = createButton();
        closeButton = createButton("关闭", 1200, 10);
        // 添加一个按钮来触发输入框和结果显示
        closeButton.addActionListener(e -> {
            setSize(800, 600);
        });
        exampleButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "请输入要要编码的字符串:");
            if (!input.isBlank() && input != null && !input.isEmpty()) {
                String result = null; // 处理输入并生成结果
                try {
                    result = processInput(input);
                    setSize(1400, 600);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                resultTextArea.setText(result);

            } else JOptionPane.showMessageDialog(this, "禁止输入空格");
        });

        // 添加点击事件
        zipButton.addActionListener(new UploadButtonListener());
        unzipButton.addActionListener(new UnHuffmanButtonListener());


        // 将按钮添加到面板容器中
        root.add(unzipButton);
        root.add(zipButton);
        root.add(exampleButton);
        root.add(closeButton);
        // 添加说明文档
        root.add(createText());

        // 添加软件标题
        root.add(createTitle());
        root.add(createImageLabel());

        // 添加结果显示区域
        resultTextArea = createResultTextArea();
        root.add(resultTextArea);

        // 设置窗口风格
        setContentPane(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 390, 800, 600);
        setVisible(true);
        setWhiteBorder();
        setTitle("可视化文档压缩");


    }

    private void setWhiteBorder() {
        LineBorder whiteBorder = new LineBorder(Color.WHITE, 2);
        getRootPane().setBorder(whiteBorder);
    }

    public static void main(String args[]) throws IOException {
        new MenuFrame();
    }

    public static JButton createButton() {
        JButton button = new JButton("字符串模式-原理解析");
        button.setBounds(125, 475, 450, 50);
        Font font = new Font("宋体", Font.PLAIN, 20);
        button.setFont(font);
        Color background = new Color(18, 163, 136);
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        return button;
    }

    public static JButton createButton(String text, int x, int y) {
        JButton tempButton = new JButton(text);
        tempButton.setBounds(x, y, 150, 50);
        Font font = new Font("宋体", Font.PLAIN, 20);
        tempButton.setFont(font);
        Color background = new Color(18, 163, 136);
        tempButton.setBackground(background);
        tempButton.setForeground(Color.WHITE);
        return tempButton;
    }

    public static JTextArea createText() {
        JTextArea textArea = new JTextArea("Author：23级1班 管贺\n" +
                "项目简介:\n" +
                "该项目由Java语言开发\n图形化界面使用swing\n文档压缩使用哈夫曼编码算法压缩\n序列化哈夫曼树使用Hessian\n" +
                "支持macos和windows上运行\n" +
                "github:https://github.com/ActingGH/Huffman");
        textArea.setBounds(350, 110, 400, 300);
        textArea.setFont(new Font("黑体", Font.PLAIN, 24));
        textArea.setForeground(Color.BLACK);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        return textArea;
    }

    public static JLabel createTitle() {
        JLabel title = new JLabel("基于哈夫曼编码的java图形化文档压缩软件");
        title.setBounds(50, 30, 700, 40);
        Font font = new Font("黑体", Font.PLAIN, 35);
        title.setFont(font);
        return title;
    }

    public static JLabel createImageLabel() {
        ImageIcon imageIcon =  new ImageIcon(MenuFrame.class.getResource("/img.png"));
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(50, 110, 250, 250);
        return imageLabel;
    }

    public static JTextArea createResultTextArea() {
        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setBounds(800, 50, 500, 500);
        resultTextArea.setFont(new Font("宋体", Font.PLAIN, 20));
        resultTextArea.setForeground(Color.BLACK);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setOpaque(true);
        resultTextArea.setEditable(false);
        resultTextArea.setBorder(new LineBorder(Color.BLACK, 1));
        return resultTextArea;
    }

    private String processInput(String input) throws IOException {
        // 这里可以添加你的处理逻辑
        return Func1.cmdMo(input);
    }
}
