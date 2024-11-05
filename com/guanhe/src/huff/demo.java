package com.guanhe.src.huff;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class demo {
    //文件转化成字节数组
    public static byte[] getFileBytes() throws IOException {
        return Files.readAllBytes(Paths.get(PATH_IN));
    }

    //字节数组输出文件
    public static void writeBytes(byte[] bytes) throws IOException {
        FileOutputStream fos = new FileOutputStream(PATH_OUT);
        fos.write(bytes);
        System.out.println("\n字节数组已成功写入文件: " + PATH_OUT);
    }

    private static String PATH_IN = "E:\\desk\\压缩我.xlsx";
    private static String PATH_OUT = "E:\\desk\\解压.xlsx";

    public static void main(String[] args) throws IOException {


        File file=new File(PATH_IN);

       // byte[] data = getFileBytes();

        Path path = Paths.get(PATH_IN);
        FileChannel fileChannel = FileChannel.open(path);
            long fileSize = fileChannel.size();
            ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
            fileChannel.read(buffer);
            buffer.flip();
            byte[] data = buffer.array();
            // 处理字节数组

      //  byte[] data="nihao hahahhahanihao   jsfiejif909hdhf   sdpofusdijf \0sdklfhsd ".getBytes();
        // 构建哈夫曼树

        HuffmanNode root = HuffmanTreeBuilder.buildHuffmanTree(data);

        // 生成哈夫曼编码表
        Map<Character, String> huffmanCodes = HuffmanCodeGenerator.generateHuffmanCodes(root);

        // 编码字节数组
        String encodedData = HuffmanEncoder.encode(data, huffmanCodes);

        System.out.println("原始字节数组: " + Util.convertToString(data));
        System.out.println("哈夫曼编码: " + encodedData);

        // 从哈夫曼编码表重建哈夫曼树
        HuffmanNode rootFromCodes = HuffmanTreeBuilder.buildHuffmanTreeFromCodes(huffmanCodes);
        // 解码二进制字符串
        byte[] decodedData = HuffmanDecoder.decode(encodedData, rootFromCodes);

        System.out.println("解码后的字节数组: " + Util.convertToString(decodedData));

    }
}
