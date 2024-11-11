package com.guanhe.src.Util.serialization;

import com.guanhe.src.myHuff.MyHuffNode;

import java.io.*;

/**
 * 序列化器实现
 */
public class JdkSerializer implements Serialize {

    /**
     * 序列化 将哈夫曼树转化成字节数组
     *
     * @param tree
     * @return
     * @throws IOException
     */
    @Override
    public byte[] serialize(MyHuffNode tree) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutput objectOutput = new ObjectOutputStream(outputStream);
        objectOutput.writeObject(tree);
        objectOutput.close();
        return outputStream.toByteArray();
    }

    /**
     * 将字节数组 转化成哈夫曼树
     *
     * @param bytes
     * @return
     * @throws IOException
     */
    @Override
    public MyHuffNode deserializeTree(byte[] bytes) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try {
            return (MyHuffNode) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            objectInputStream.close();
        }

    }

    /**
     * 二进制文件字符串
     * @param s
     * @return
     * @throws IOException
     */
    @Override
    public String deserialize(String s) throws IOException {
        long count;
        count = Long.parseLong(s.substring(0, 16));
        StringBuilder sb = new StringBuilder();
        for (long i = 16; i < count + 16; i++) {
            //     sb.append(s.charAt(i));
        }
        return null;
    }

    @Override
    public int getInteger(String s) {
        String count=s.substring(0,32);
        return Integer.parseInt(count,2);
    }
}
