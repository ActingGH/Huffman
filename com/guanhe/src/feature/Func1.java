package com.guanhe.src.feature;

import com.guanhe.src.Util.convert.ConvertImpl;
import com.guanhe.src.Util.convert.ConvertInterface;
import com.guanhe.src.Util.serialization.HessianSerializer;
import com.guanhe.src.Util.serialization.Serialize;
import com.guanhe.src.myHuff.MyHuffNode;
import com.guanhe.src.myHuff.Myhuffman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

public class Func1 {


    /**
     *输出哈夫曼编码后的文件和秘钥
     * @param inputPath 文件路径
     * @throws IOException 抛出异常
     */
    public static String commonFunc1(String inputPath) throws IOException {
        File file =new File(inputPath);
        ConvertInterface convert=new ConvertImpl();
        Myhuffman myhuffman=new Myhuffman();
        Serialize serialize=new HessianSerializer();
        if(!file.exists()){
            System.out.println("文件不存在");
            throw new RuntimeException("文件不存在");
        }
        String[] name=inputPath.split("\\.");
        String fileName=name[0];
        String fileFormat=name[1];
        String huffmanString = myhuffman.huffmanFun(convert.fileToByte(inputPath));
        String targetName=fileName+"."+"huffman-gh";
        try (FileOutputStream fos = new FileOutputStream(targetName)) {
            // 将字节数组写入文件
            fos.write(convert.binToByte(huffmanString));
            System.out.println("字节数组已成功写入文件: " + targetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myhuffman.myHuffNode.setType(fileFormat);
        String codeName=fileName+"."+"huffman-code";
        try (FileOutputStream fos = new FileOutputStream(codeName)) {
            fos.write(serialize.serialize(myhuffman.myHuffNode));
            System.out.println("秘钥已成功写入文件: " + codeName);
        }catch(IOException e){
            e.printStackTrace();
        }
        return "哈夫曼编码数组已成功写入文件: " + targetName+"\n"+"秘钥已成功写入文件: " + codeName;
    }

    public static String commonFunc2(String filePath, String codePath) throws IOException {
        ConvertInterface convert=new ConvertImpl();
        Serialize serialize=new HessianSerializer();
        Myhuffman myhuffman=new Myhuffman();
        MyHuffNode myHuffNode = serialize.deserializeTree(convert.fileToByte(codePath));
        byte[] bytes = convert.huffmanbinTobyte(convert.byteToBin(convert.fileToByte(filePath)), myHuffNode);
        String targetName=filePath.split("\\.")[0]+"new"+"."+myHuffNode.getType();
        try (FileOutputStream fos = new FileOutputStream(targetName)) {
            fos.write(bytes);
            System.out.println("成功恢复文件："+targetName);
        }
        return "成功恢复文件："+targetName;
    }

    public static void main(String[] args) throws IOException {
        commonFunc1("E:\\desk\\座次表.xlsx");

        commonFunc2("E:\\desk\\座次表.huffman-gh","E:\\desk\\座次表.huffman-code");
    }

    public static String cmdMo(String s) throws IOException {
        ConvertInterface convert = new ConvertImpl();
        Myhuffman myhuffman = new Myhuffman();
        StringBuilder output = new StringBuilder();

        output.append("原始字节：\n");
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            output.append(b).append(" ");
        }
        output.append("\n");

        output.append("二进制：\n");
        String temp = convert.byteToBin(bytes);
        output.append(temp).append("\n");

        output.append("哈夫曼编码二进制：\n");
        String ans = myhuffman.huffman(bytes);
        output.append(ans).append("\n");

        output.append("哈夫曼表：\n");
        output.append("二进制编码 ：字节\n");
        Map<Byte, String> huffmanTable = Myhuffman.buildTable(myhuffman.getHuffNode());
        for (Map.Entry<Byte, String> entry : huffmanTable.entrySet()) {
            output.append(entry.getValue()).append(" : ").append(entry.getKey()).append("\n");
        }

        output.append("根据哈夫曼树恢复的字节：\n");
        byte[] result = convert.huffmanbinTobyte(ans, myhuffman.getHuffNode());
        for (byte b : result) {
            output.append(b).append(" ");
        }

        return output.toString();
    }


}
