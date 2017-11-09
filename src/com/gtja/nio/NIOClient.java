package com.gtja.nio;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by 14999 on 2017/11/9.
 */
public class NIOClient {
    public static void main(String[] args) throws Exception{
       // int i = 0;
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost",8888));
        socketChannel.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        ByteBuffer buffer = ByteBuffer.allocate(1024*8);

        new Thread(){
            @Override
            public void run() {
                int i = 0;
                ByteBuffer buffer1 = ByteBuffer.allocate(1024*8);
                while(true){
                    try{
                        buffer1.put(("hello" + (i++)).getBytes());
                        buffer1.flip();
                        socketChannel.write(buffer1);
                        buffer1.clear();
                        Thread.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
//        while (true){
//            selector.select();
//            SelectionKey key = selector.selectedKeys().iterator().next();
//            if(key.isReadable()){
//                while (socketChannel.read(buffer)!=-1){
//                    buffer.flip();
//                    System.out.println(new String(buffer.array(),0,buffer.limit()));
//                    buffer.clear();
//                }
//            }
//
//            if(key.isWritable()){
//                buffer.clear();
//                buffer.wrap(("hello" + i).getBytes());
//                System.out.println("发出了:" + "hello" + i);
//                buffer.flip();
//                socketChannel.write(buffer);
//                buffer.clear();
//                i++;
//            }
//            selector.selectedKeys().clear();
//        }
        while(true){
            selector.select();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (socketChannel.read(buffer)>0){
                buffer.flip();
                baos.write(buffer.array(),0,buffer.limit());
                buffer.clear();
            }
            System.out.println(new String(baos.toByteArray()));
            selector.selectedKeys().clear();
        }
    }
}
