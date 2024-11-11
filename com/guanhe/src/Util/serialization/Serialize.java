package com.guanhe.src.Util.serialization;

import com.guanhe.src.myHuff.MyHuffNode;

import java.io.IOException;

/**
 * 仅用于序列化哈夫曼树
 */
public interface Serialize {
    byte[] serialize(MyHuffNode tree) throws IOException;

    MyHuffNode deserializeTree(byte[] bytes) throws IOException;


    String deserialize(String s) throws IOException;

    int getInteger(String s);
}
