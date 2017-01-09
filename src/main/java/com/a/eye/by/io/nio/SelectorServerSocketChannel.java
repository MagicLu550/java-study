package com.a.eye.by.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class SelectorServerSocketChannel {

    public static void startServer() {

        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(9888));

            Selector selector = Selector.open();

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int select = selector.select();
                if (select > 0) {
                    for (SelectionKey key : selector.selectedKeys()) {
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                            ByteBuffer buf = ByteBuffer.allocate(48);
                            int size = socketChannel.read(buf);
                            while (size > 0) {
                                buf.flip();
                                Charset charset = Charset.forName("UTF-8");
                                System.out.println(charset.decode(buf).toString());
                                size = socketChannel.read(buf);
                            }
                            buf.clear();
                            
                            ByteBuffer response = ByteBuffer.wrap("您好!我已经收到了您的请求!".getBytes("UTF-8"));
                            socketChannel.write(response);
                            socketChannel.close();
                            selector.selectedKeys().remove(key);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        startServer();
    }

}
