package com.a.eye.by.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

public class AioClient {

    public static void startClient() {
        try {
            final AsynchronousSocketChannel asySocketChannel = AsynchronousSocketChannel.open();

            Future<?> future = asySocketChannel.connect(new InetSocketAddress("127.0.0.1", 9888));
            future.get();

            ByteBuffer buf = ByteBuffer.wrap("hello world".getBytes());

            asySocketChannel.write(buf);
            buf.clear();
            asySocketChannel.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startClient();
    }
}
