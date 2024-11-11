package com.guanhe.src.Util.serialization;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.guanhe.src.myHuff.MyHuffNode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class HessianSerializer implements Serialize{
    /**
     * 哈夫曼树 -》 字节数组
     * @param tree
     * @return
     * @throws IOException
     */
    @Override
    public byte[] serialize(MyHuffNode tree) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Hessian2Output ho = new Hessian2Output(baos);
            ho.writeObject(tree);
            ho.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] serialize(Map<Byte,String> table) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Hessian2Output ho = new Hessian2Output(baos);
            ho.writeObject(table);
            ho.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Map<Byte, String> deserializeTable(byte[] bytes) throws IOException {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            Hessian2Input hi = new Hessian2Input(bais);
            return (Map<Byte,String>) hi.readObject(Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public MyHuffNode deserializeTree(byte[] bytes) throws IOException {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            Hessian2Input hi = new Hessian2Input(bais);
            return (MyHuffNode) hi.readObject(MyHuffNode.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

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
