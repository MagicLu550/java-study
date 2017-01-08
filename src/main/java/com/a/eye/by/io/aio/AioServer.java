package com.a.eye.by.io.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

public class AioServer {

    private static CountDownLatch latch;

    public static void startServer() {
        try {
            final AsynchronousServerSocketChannel asyServerSocketChannel = AsynchronousServerSocketChannel.open();

            asyServerSocketChannel.bind(new InetSocketAddress(9888));

            latch = new CountDownLatch(1);

            asyServerSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

                public void completed(AsynchronousSocketChannel result, Void attachment) {
                    asyServerSocketChannel.accept(attachment, this);
                    operate(result);
                }

                public void failed(Throwable exc, Void attachment) {
                    latch.countDown();
                }

            });

            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void operate(AsynchronousSocketChannel ch) {
        try {
            ByteBuffer buf = ByteBuffer.allocate(48);
            int size;
            size = ch.read(buf).get();
            while (size > 0) {
                buf.flip();
                Charset charset = Charset.forName("UTF-8");
                System.out.println(charset.newDecoder().decode(buf).toString());
                size = ch.read(buf).get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startServer();
    }

}
