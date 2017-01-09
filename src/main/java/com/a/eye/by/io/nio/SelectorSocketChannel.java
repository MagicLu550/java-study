package com.a.eye.by.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class SelectorSocketChannel {

    public static void startClient() {

        try {

            SocketChannel socketChannel = SocketChannel.open();
            
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 9888));
            
            socketChannel.configureBlocking(false);
            
            Selector selector = Selector.open();

            socketChannel.register(selector, SelectionKey.OP_READ);

            new ClientThread(selector).start();

            ByteBuffer byteBuffer = ByteBuffer.wrap("你好，我是客户端".getBytes("UTF-8"));
            socketChannel.write(byteBuffer);

            byteBuffer.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        startClient();
    }

}

class ClientThread extends Thread {

    private Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    public void run() {
        try {
            while (selector.select() > 0) {
                for (SelectionKey key : selector.selectedKeys()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    ByteBuffer buf = ByteBuffer.allocate(48);
                    int size = socketChannel.read(buf);
                    while (size > 0) {
                        buf.flip();
                        Charset charset = Charset.forName("UTF-8");
                        System.out.println(charset.decode(buf).toString());
                        size = socketChannel.read(buf);
                    }
                    selector.selectedKeys().remove(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
