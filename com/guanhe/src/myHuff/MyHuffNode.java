package com.guanhe.src.myHuff;

import java.io.Serializable;

public class MyHuffNode implements Comparable<MyHuffNode> , Serializable {
    /**
     * 出现频率 避免文件过大无法内存溢出
     * 使用long类型存储
     */
    static String type;

    public  void setType(String type) {
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public long frency;
    /**
     * 左节点
     */
    public MyHuffNode left;
    /**
     * 右节点
     */
    public MyHuffNode right;
    /**
     * 使用byte封装类 可以内容允许使用null
     */
    public Byte node;

    MyHuffNode(Byte node, long frency) {
        if (node == null) {
            this.frency = frency;
        } else {
            this.frency = frency;
            this.node = node;
        }

    }


    /**
     * 重写了compareTo方法 在构造优先队列的时候 进行比较
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(MyHuffNode o) {
        long ans = this.frency - o.frency;
        if (ans == 0) return 0;
        else if (ans > 0) return 1;
        else return -1;
    }



}





