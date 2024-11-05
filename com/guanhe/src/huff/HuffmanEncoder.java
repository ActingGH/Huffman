package com.guanhe.src.huff;

import java.util.Map;

public class HuffmanEncoder {
    public static String encode(byte[] data, Map<Character, String> huffmanCodes) {
        StringBuilder encodedData = new StringBuilder();
        for (byte b : data) {
            if(b==0){
                encodedData.append(huffmanCodes.get((char)0));
            }else{
                char c = (char) (b & 0xFF);
                encodedData.append(huffmanCodes.get(c));
            }

        }
        return encodedData.toString();
    }
}