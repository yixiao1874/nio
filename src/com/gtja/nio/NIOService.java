package com.gtja.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * Created by 14999 on 2017/11/9.
 */
public class NIOService {
    public static void main(String[] args) throws  Exception{
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.configureBlocking(false);

        ssc.bind(new InetSocketAddress("localhost",8888));
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer buff = ByteBuffer.allocate(1024);
        SelectableChannel sc = null;
        while (true){
            selector.select();
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                if(key.isAcceptable()){
                    sc = key.channel();
                    //SocketChannel socketChannel = ssc.accept();
                    SocketChannel socketChannel = ((ServerSocketChannel)sc).accept();
                    System.out.println("有连接进来了");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
                if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    //新加
                    byte[] helloBytes = "hello".getBytes();
                    buff.put(helloBytes,0,helloBytes.length);

                    while (socketChannel.read(buff)!=0){
                        buff.flip();
                        //System.out.println(new String(buff.array(),0,buff.limit()));
                        socketChannel.write(buff);
                        buff.clear();
                    }
                }
            }
            selector.selectedKeys().clear();
        }

    }
}
