package com.guanhe.src.exe;


public class MyHuffNode implements Comparable<MyHuffNode> {


    MyHuffNode left;
    MyHuffNode right;


    Byte node;

    MyHuffNode(Byte node, long frency) {
        if (node == null) {
            this.frency = frency;
        } else {
            this.frency = frency;
            this.node = node;
        }

    }


    long frency;



    @Override
    public int compareTo(MyHuffNode o) {
        long ans = this.frency - o.frency;
        if (ans == 0) return 0;
        else if (ans > 0) return 1;
        else return -1;
    }
}





