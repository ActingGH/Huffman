package com.guanhe.src.Util.convert;

import com.guanhe.src.myHuff.MyHuffNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ConvertInterface {

    byte[] fileToByte(String filePath) throws IOException;

    String byteToBin(byte[] bytes);

    byte[] binToByte(String s);

    byte[] stringToByte(String string);

    byte[] huffmanbinTobyte(String s, MyHuffNode root);

    String buildPrefix(int count,String HuffmanTreeBin,String s);
}
