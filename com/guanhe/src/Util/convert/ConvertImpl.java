package com.guanhe.src.Util.convert;

import com.guanhe.src.myHuff.MyHuffNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertImpl implements ConvertInterface{

    @Override
    public byte[] fileToByte(String filePath) throws IOException {
         File file=new File(filePath);
         InputStream inputStream=new FileInputStream(file);
         byte[] bytes = inputStream.readAllBytes();
         return bytes;
    }

    @Override
    public String byteToBin(byte[] bytes) {
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

    @Override
    public byte[] binToByte(String s) {
        int length = s.length();
        byte[] bytes = new byte[length / 8 + (length % 8 == 0 ? 0 : 1)];
        for (int i = 0; i < length; i += 8) {
            // 获取 8 位二进制子字符串
            int end = Math.min(i + 8, length);
            String byteStr = s.substring(i, end);
            // 将二进制字符串转换为 int
            int intValue = Integer.parseInt(byteStr, 2);
            // 将 int 转换为 byte
            byte byteValue = (byte) intValue;
            // 将 byte 值存储到数组中
            if (i / 8 < bytes.length) { // 防止数组越界
                bytes[i / 8] = byteValue;
            }
        }
        return bytes;
    }

    @Override
    public byte[] stringToByte(String string) {
        byte[] bytes=string.getBytes();
        return bytes;
    }

    @Override
    public byte[] huffmanbinTobyte(String s,MyHuffNode root) {
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

    @Override
    public String buildPrefix(int count, String HuffmanTreeBin, String s) {
        String binaryString = to32BitBinaryString(count);
        System.out.println(binaryString);
        return binaryString+HuffmanTreeBin+s;
    }

    private static String to32BitBinaryString(int number) {
        StringBuilder sb = new StringBuilder(Integer.toBinaryString(number));
        while (sb.length() < 32) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }


}
