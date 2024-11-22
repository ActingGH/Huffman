package com.guanhe.src.myHuff;

import com.guanhe.src.Util.convert.ConvertImpl;
import com.guanhe.src.Util.convert.ConvertInterface;
import com.guanhe.src.Util.serialization.HessianSerializer;

import java.io.*;
import java.util.*;

public class Myhuffman {


    /**
     * 哈夫曼树
     */
    public  static MyHuffNode huffNode;

    public MyHuffNode myHuffNode;
    /**
     * 返回哈夫曼树
     * @return
     */
    public MyHuffNode getHuffNode() {
        return huffNode;
    }

    public static byte[] toHuffmanBype(String source) throws IOException {
        ConvertInterface convert=new ConvertImpl();
        HessianSerializer serializer=new HessianSerializer();
        byte[] originBytes=convert.fileToByte(source);
        String ans=Myhuffman.huffman(originBytes);
        return  convert.binToByte(ans);
    }



    public static MyHuffNode tableToTree(Map<Byte,String> table){
        // 创建一个虚拟根节点
        MyHuffNode root = new MyHuffNode((byte) 0, 0);
        // 遍历哈夫曼表，构建哈夫曼树
        for (Map.Entry<Byte, String> entry : table.entrySet()) {
            byte data = entry.getKey();
            String code = entry.getValue();
            insertCode(root, data, code);
        }
        return root;

    }

    private static void insertCode(MyHuffNode root, byte data, String code) {
        MyHuffNode current = root;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                if (current.left == null) {
                    current.left = new MyHuffNode((byte) 0, 0);
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new MyHuffNode((byte) 0, 0);
                }
                current = current.right;
            }
        }
        current.node = data;
    }

    /**
     * 根据哈夫曼树和二进制还原成字节数组
     * @param s 二进制字符串
     * @param root 哈夫曼树
     * @return 字节数组
     */
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


    /**
     * 将字节数组 哈夫曼编码
     * @param bytes
     * @return 二进制编码字符串
     */
    public static String huffman(byte[] bytes) {
        MyHuffNode myHuffNode = buildeTree(bytes);
        huffNode = myHuffNode;
        Map<Byte, String> byteStringMap = buildTable(myHuffNode);
        String encode = encode(bytes, byteStringMap);
        return encode;
    }

    public  String huffmanFun(byte[] bytes) {
        MyHuffNode myHuffNode = buildeTree(bytes);
        this.myHuffNode = myHuffNode;
        Map<Byte, String> byteStringMap = buildTable(myHuffNode);
        String encode = encode(bytes, byteStringMap);
        return encode;
    }


    /**
     *构建哈夫曼树
     */
    public static  MyHuffNode buildeTree(byte[] data) {
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


    /**
     * 根据哈夫曼树构建哈夫曼表
     * @param tree
     * @return
     */
    public static Map<Byte, String> buildTable(MyHuffNode tree) {
        Map<Byte, String> huffmanTable = new HashMap<>();
        generateTable(tree, "", huffmanTable);
        return huffmanTable;
    }

    //构建哈夫曼表
    public static void generateTable(MyHuffNode root, String s, Map<Byte, String> table) {
        if (root == null) return;
        if (root.node != null) {
            table.put(root.node, s);
        }
        generateTable(root.left, s + "0", table);
        generateTable(root.right, s + "1", table);
    }

    /**
     * 根据哈夫曼表将字节数组转化为二进制字符串
     *
     * @param bytes 字节数组
     * @param table 哈夫曼表
     * @return
     */
    public static String encode(byte[] bytes, Map<Byte, String> table) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String s = table.get(b);
            sb.append(s);
        }
        return sb.toString();
    }


}
