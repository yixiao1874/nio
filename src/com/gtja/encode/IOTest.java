package com.gtja.encode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class IOTest {
    public static void main(String[] args) throws Exception{
        //write();
        String str = "这是一串中文字符";
//        byte[] b = str.getBytes("UTF-8");
//        for(byte b1:b){
//            System.out.print(b1 + "  ");
//        }
//        String newStr = new String(b,"UTF-8");
//        System.out.println(newStr);
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(str);
        System.out.println(byteBuffer);
        CharBuffer charBuffer = charset.decode(byteBuffer);
        System.out.println(charBuffer);
    }

    public static void write() throws Exception{
        String file = "e:/stream.txt";
        String charset = "UTF-8";
        FileOutputStream outputStream = new FileOutputStream(file);
        //OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        outputStream.write("这是要保存的中文字符".getBytes());
        outputStream.close();

        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream);
        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[64];
        int count = 0;
        while((count = reader.read(buf))>0){
            buffer.append(buf,0,count);
        }
        System.out.println(buffer);
        reader.close();
    }
}
