package com.guanhe.src.huff;

import org.w3c.dom.Node;

import java.util.*;


import java.util.PriorityQueue;

public class HuffmanTreeBuilder {
    public static HuffmanNode buildHuffmanTree(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        // 统计每个字符的频率
        int[] frequency = new int[256];
        for (byte b : data) {
            frequency[b & 0xFF]++;
        }

        // 创建优先队列（最小堆）
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            if (frequency[i] > 0) {
                priorityQueue.add(new HuffmanNode((char) i, frequency[i]));
            }
        }

        // 构建哈夫曼树
        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            priorityQueue.add(parent);
        }

        return priorityQueue.poll();
    }

    public static HuffmanNode buildHuffmanTreeFromCodes(Map<Character, String> huffmanCodes) {
        if (huffmanCodes == null || huffmanCodes.isEmpty()) {
            return null;
        }

        // 创建一个虚拟根节点
        HuffmanNode root = new HuffmanNode('\0', 0);

        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            char character = entry.getKey();
            String code = entry.getValue();
            HuffmanNode node = root;

            for (char bit : code.toCharArray()) {
                if (bit == '0') {
                    if (node.left == null) {
                        node.left = new HuffmanNode('\0', 0);
                    }
                    node = node.left;
                } else if (bit == '1') {
                    if (node.right == null) {
                        node.right = new HuffmanNode('\0', 0);
                    }
                    node = node.right;
                }
            }
            node.character = character;
        }

        return root;
    }
}
