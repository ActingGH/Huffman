package com.guanhe.src;

import com.caucho.hessian.io.Serializer;
import com.guanhe.src.Util.convert.ConvertImpl;
import com.guanhe.src.Util.convert.ConvertInterface;
import com.guanhe.src.Util.serialization.HessianSerializer;
import com.guanhe.src.Util.serialization.JdkSerializer;
import com.guanhe.src.Util.serialization.Serialize;
import com.guanhe.src.myHuff.MyHuffNode;
import com.guanhe.src.myHuff.Myhuffman;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static com.guanhe.src.myHuff.Myhuffman.*;

/**
 * 启动类
 */
public class Application {


    public static void main(String[] args) throws IOException {

        demo2();

    }

    private static void demo2() throws IOException {
        ConvertInterface convert = new ConvertImpl();
        Myhuffman myhuffman = new Myhuffman();
        HessianSerializer serialize=new HessianSerializer();
        System.out.println("原始字节：");
        //   byte[] bytes = convert.stringToByte(s);
        byte[] bytes= convert.fileToByte("E:\\desk\\党章.docx");
        for (byte b : bytes) {
            System.out.print(b + " ");
        }
        String target="E:\\desk\\党章.huff";
        System.out.println("\n二进制：");
        String temp = convert.byteToBin(bytes);
        System.out.println(temp);

        System.out.println("哈夫曼编码二进制：");
        String ans = Myhuffman.huffman(bytes);
        System.out.println(ans);
        byte[] binToByte = convert.binToByte(ans);
        try {
            // 使用try-with-resources语句确保资源关闭
            try (FileOutputStream fos = new FileOutputStream(target)) {
                // 将字节数组写入文件
                fos.write(binToByte);
                System.out.println("二进制字符串已成功写入文件: " + target);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("写入文件时发生错误: " + e.getMessage());
        }




        Map<Byte,String> map=Myhuffman.buildTable(huffNode);
        System.out.println(map);

        byte[] serialize1 = serialize.serialize(map);
        for(byte b:serialize1){
            System.out.print(b+" ");
        }

        String s = convert.byteToBin(serialize1);
        System.out.println("\n"+s.length());
    }

    private static void demo3() throws IOException {
        String source="E:\\desk\\党章.docx";
        String target="E:\\desk\\党章.huff";
        ConvertInterface convert = new ConvertImpl();
        Myhuffman myhuffman = new Myhuffman();
        HessianSerializer serialize=new HessianSerializer();
        byte[] sourceBytes = convert.fileToByte(source);
        String huffmanBin = Myhuffman.huffman(sourceBytes);
        byte[] huffmanBytes = convert.binToByte(huffmanBin);
        try {
            // 使用try-with-resources语句确保资源关闭
            try (FileOutputStream fos = new FileOutputStream(target)) {
                // 将字节数组写入文件
                fos.write(huffmanBytes);
                System.out.println("二进制字符串已成功写入文件: " + target);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("写入文件时发生错误: " + e.getMessage());
        }

    }

    private static void demo1() throws IOException {
        ConvertInterface convert = new ConvertImpl();
        Myhuffman myhuffman = new Myhuffman();
        Serialize serialize=new JdkSerializer();

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        System.out.println("原始字节：");
          byte[] bytes = convert.stringToByte(s);
    //    byte[] bytes= convert.fileToByte("E:\\desk\\党章.docx");
        for (byte b : bytes) {
            System.out.print(b + " ");
        }


        System.out.println("\n二进制：");
        String temp = convert.byteToBin(bytes);
        System.out.println(temp);


        System.out.println("哈夫曼编码二进制：");
        String ans = myhuffman.huffman(bytes);
        System.out.println(ans);


        System.out.println("根据哈夫曼树恢复的字节：");
        byte[] result = convert.huffmanbinTobyte(ans, myhuffman.getHuffNode());
        for (byte b : result) {
            System.out.print(b + " ");
        }

        //序列化哈希树
        byte[] haffumanTreeBytes = serialize.serialize(myhuffman.getHuffNode());
        System.out.println();
        System.out.println("哈夫曼树序列化：");
        for (byte b : haffumanTreeBytes) {
            System.out.print(b + " ");
        }


        //哈夫曼树序列化后的二进制字节数组
        System.out.println();
        System.out.println("哈夫曼树序列化后的二进制字节数组：");
        String huffmanTreeBin= convert.byteToBin(haffumanTreeBytes);
        System.out.println(huffmanTreeBin);


        System.out.print("计算哈夫曼树二进制的位数：");
        int countTreeBin=huffmanTreeBin.length();
        System.out.println(countTreeBin);

        //第一个字节存储哈夫曼的二进制位数
        System.out.println("序列化之后的：");
        String storage=convert.buildPrefix(countTreeBin,huffmanTreeBin,ans);
        System.out.println(storage);

        System.out.println("解析后的前32位的整数类型：");

        int count= serialize.getInteger(storage);
        System.out.println(count);


        System.out.println("解析之后的哈夫曼树的二进制：");
        String treeBin=storage.substring(32,count+32);
        System.out.println(treeBin);

        byte[] binToByte = convert.binToByte(treeBin);
        for(int i=0;i<binToByte.length;i++){
            System.out.print(binToByte[i]+" ");
        }

        MyHuffNode myHuffNode = serialize.deserializeTree(binToByte);
        MyHuffNode huffNode=myhuffman.getHuffNode();
        System.out.println("\n是否一致："+myHuffNode.equals(huffNode));

        System.out.println("根据饭反序列化后的哈夫曼树和二进制字符串解析原始字节数组");
        byte[] aByte = myhuffman.toByte(storage.substring(count + 32), myHuffNode);
        for(byte b:aByte){
            System.out.print(b+" ");
        }

        System.out.println("原始字节数组二进制位数：");
        System.out.println(temp.length());

        System.out.println("哈夫曼压缩后的二进制位数：");
        System.out.println(ans.length());

        System.out.println("加入哈夫曼树的的二进制位数");
        System.out.println(storage.length());
    }
}
