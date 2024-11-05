package com.guanhe.src.mydemo;

public class MyHuffNode implements Comparable<MyHuffNode> {


    Byte node;

    MyHuffNode(Byte node,long frency){
        if(node==null){
            this.frency=frency;
        }else{
            this.frency=frency;
            this.node=node;
        }

    }




    long  frency;
    MyHuffNode left;
    MyHuffNode right;


    @Override
    public int compareTo(MyHuffNode o) {
        long ans= this.frency-o.frency;
        if(ans==0) return 0;
        else if(ans>0) return 1;
        else return -1;
    }
}





