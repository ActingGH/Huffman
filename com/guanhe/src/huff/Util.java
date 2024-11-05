package com.guanhe.src.huff;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

//工具类
public class Util {

    //将字符串转换成字节数组
    public static byte[] convertToByteArray(String encodedString) {
        int numBytes = (int) Math.ceil(encodedString.length() / 8.0);
        byte[] byteArray = new byte[numBytes];

        for (int i = 0; i < encodedString.length(); i++) {
            if (encodedString.charAt(i) == '1') {
                byteArray[i / 8] |= (1 << (7 - i % 8));
            }
        }

        return byteArray;
    }

    //将字节数组转换成字符串
    public static String convertToString(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        for (byte b : byteArray) {
            for (int i = 7; i >= 0; i--) {
                sb.append((b & (1 << i)) != 0 ? '1' : '0');
            }
        }
        return sb.toString();
    }

    //反序列化哈夫曼表
    public static Map<Byte, String> deserializeHuffmanTable(byte[] huffmanTableBytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(huffmanTableBytes);
             DataInputStream dis = new DataInputStream(bais)) {

            int size = dis.readInt();
            Map<Byte, String> huffmanCodes = new HashMap<>();

            for (int i = 0; i < size; i++) {
                // 读取字节值
                byte value = dis.readByte();
                // 读取编码长度
                short length = dis.readShort();
                // 读取编码
                String code = dis.readUTF();
                huffmanCodes.put(value, code);
            }

            return huffmanCodes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //序列化哈夫曼表
    public static byte[] serializeHuffmanTable(Map<Byte, String> huffmanCodes) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            DataOutputStream dos = new DataOutputStream(baos);

            // 写入哈夫曼表的大小
            dos.writeInt(huffmanCodes.size());

            for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
                // 写入字节值
                dos.writeByte(entry.getKey());
                // 写入编码长度
                dos.writeShort(entry.getValue().length());
                // 写入编码
                dos.writeUTF(entry.getValue());
            }

            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
