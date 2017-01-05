package com.a.eye.by.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HelloServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /*ByteBuf byteBuf = (ByteBuf) msg;
        byte[] message = new byte[byteBuf.readableBytes()];
        byteBuf.writeBytes(message);
        
        String ms = new String(message);

        System.out.println(ms);
        byteBuf.release();*/
        
        ByteBuf result = (ByteBuf) msg;
        byte[] message = new byte[result.readableBytes()];
        result.readBytes(message);
        String mess = new String(message);
        System.out.println("client say:" + mess);
        result.release();
        
        String response = "I am ok!";
        
        ByteBuf res = ctx.alloc().buffer(4 * response.length());
        
        res.writeBytes(response.getBytes());
        
        ctx.writeAndFlush(res);
        
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

}
