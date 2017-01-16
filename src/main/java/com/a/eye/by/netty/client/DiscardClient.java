package com.a.eye.by.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;

import com.a.eye.by.netty.codec.MyEncode;

public class DiscardClient {

    private static Bootstrap bootstrap;

    // private static PooledByteBufAllocator allocator = new PooledByteBufAllocator();

    static {
        try {

            EventLoopGroup workerGroup = new NioEventLoopGroup();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new MyEncode());
                    ch.pipeline().addLast(new DiscardClientHandler());
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start() {
        ChannelFuture future = bootstrap.connect("127.0.0.1", 9888);
        future.channel().writeAndFlush("hello");
        try {
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
