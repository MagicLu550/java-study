package com.a.eye.by.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NioClient {
    
    public static void main(String[] args){
        try{
            new NioClient().startClient();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void startClient() throws InterruptedException{
        
        EventLoopGroup group = new NioEventLoopGroup();
        
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>(){
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                       ch.pipeline().addLast(new HelloClientHandler());
                }
            });
            
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8999).sync();
            
            channelFuture.channel().closeFuture().sync();
        }finally{
            group.shutdownGracefully();
        }
    }
}
