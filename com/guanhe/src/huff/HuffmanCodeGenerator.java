package com.guanhe.src.huff;

import java.util.Map;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class HuffmanCodeGenerator {
    private static void generateCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.character != '\0') {
               huffmanCodes.put(node.character, code);
        }else{
            huffmanCodes.put((char)0, code);
        }

        generateCodes(node.left, code + "0", huffmanCodes);
        generateCodes(node.right, code + "1", huffmanCodes);
    }

    public static Map<Character, String> generateHuffmanCodes(HuffmanNode root) {
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);
        return huffmanCodes;
    }
}
