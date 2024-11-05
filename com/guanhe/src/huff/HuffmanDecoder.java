package com.guanhe.src.huff;

public class HuffmanDecoder {
    public static byte[] decode(String encodedData, HuffmanNode root) {
        if (root == null) {
            return new byte[0];
        }

        StringBuilder decodedData = new StringBuilder();
        HuffmanNode node = root;
        if(node==null){
            return new byte[0];
        }

        for (char bit : encodedData.toCharArray()) {
            if (bit == '0') {

                node = node.left;
            } else if (bit == '1') {

                node = node.right;
            }

            if (node !=null && node.character != '\0') {
                decodedData.append(node.character);
                node = root;
            }
        }

        // 将字符数组转换为字节数组
        byte[] result = new byte[decodedData.length()];
        for (int i = 0; i < decodedData.length(); i++) {
            result[i] = (byte) decodedData.charAt(i);
        }

        return result;
    }
}
