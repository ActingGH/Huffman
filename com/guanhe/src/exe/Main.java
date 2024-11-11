package com.guanhe.src.exe;

import java.io.*;
import java.util.*;

public class Main {

    private static MyHuffNode huffNode;

    private static Map<Byte,String> huffMap;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("-------模拟哈夫曼编码-------------");
        System.out.println("请输入字符串样例：");
        String s = sc.nextLine();


        System.out.println("原始字节：");
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            System.out.print(b + " ");
        }


        System.out.println("\n二进制：");
        String temp = ByteToBin(bytes);
        System.out.println(temp);


        System.out.println("哈夫曼编码二进制：");
        String ans = huffman(bytes);
        System.out.println(ans);


        System.out.println("哈夫曼表：");
        System.out.println("二进制编码 ：字节 ");
        for(Map.Entry<Byte,String> entry:huffMap.entrySet()){
            System.out.println(entry.getValue()+" : "+entry.getKey());
        }


        System.out.println("根据哈夫曼树恢复的字节：");
        byte[] result = toByte(ans, huffNode);
        for (byte b : result) {
            System.out.print(b + " ");
        }
        while (true){

        }
    }

    //根据哈夫曼树和二进制还原成字节数组
    public static byte[] toByte(String s, MyHuffNode root) {
        List<Byte> list = new ArrayList<>();
        MyHuffNode node = root;
        if (node == null) return new byte[0];

        for (int i = 0; i < s.length(); i++) {
            char x = s.charAt(i);
            if (x == '0') {
                node = node.left;
            } else if (x == '1') {
                node = node.right;
            }
            if (node != null && node.node != null) {
                list.add(node.node);
                node = root;
            }
        }
        byte[] ans = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;

    }

    private static String ByteToBin(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int unsignedByte = b & 0xFF;

            // 将无符号整数转换为二进制字符串
            String binaryString = Integer.toBinaryString(unsignedByte);

            // 确保二进制字符串的长度为 8 位，不足 8 位的前面补 0
            while (binaryString.length() < 8) {
                binaryString = "0" + binaryString;
            }
            sb.append(binaryString);

        }
        return sb.toString();
    }

    public static String huffman(byte[] bytes) {
        MyHuffNode myHuffNode = buildeTree(bytes);
        huffNode = myHuffNode;
        Map<Byte, String> byteStringMap = buildTable(myHuffNode);
        String encode = encode(bytes, byteStringMap);
        return encode;
    }


    //构建哈夫曼树
    public static MyHuffNode buildeTree(byte[] data) {
        if (data == null || data.length == 0) return null;
        int[] frequency = new int[256];
        for (byte b : data) {
            frequency[b & 0xFF]++;
        }

        //创建优先队列
        PriorityQueue<MyHuffNode> priorityQueue =
                new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            if (frequency[i] > 0) {
                priorityQueue.add(new MyHuffNode
                        ((byte) i, frequency[i]));
            }
        }
        while (priorityQueue.size() > 1) {
            MyHuffNode l = priorityQueue.poll(); //左节点
            MyHuffNode r = priorityQueue.poll();
            MyHuffNode p = new MyHuffNode(null, l.frency + r.frency);
            p.left = l;
            p.right = r;
            priorityQueue.add(p);
        }

        return priorityQueue.poll();

    }

    //构建哈夫曼表
    public static Map<Byte, String> buildTable(MyHuffNode tree) {
        Map<Byte, String> huffmanTable = new HashMap<>();
        generateTable(tree, "", huffmanTable);
        huffMap=huffmanTable;
        return huffmanTable;
    }

    public static void generateTable(MyHuffNode root, String s, Map<Byte, String> table) {
        if (root == null) return;
        if (root.node != null) {
            table.put(root.node, s);
        }
        generateTable(root.left, s + "0", table);
        generateTable(root.right, s + "1", table);
    }

    //根据哈夫曼表转换成二进制数字
    public static String encode(byte[] bytes, Map<Byte, String> table) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String s = table.get(b);
            sb.append(s);
        }
        return sb.toString();
    }


}
