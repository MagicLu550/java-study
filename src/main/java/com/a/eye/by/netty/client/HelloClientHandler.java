package com.a.eye.by.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HelloClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String request = "are you OK ?";
        ByteBuf byteBuf = ctx.alloc().buffer(request.length() * 4);
        byteBuf.writeBytes(request.getBytes());
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] messBytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(messBytes);
        String message = new String(messBytes);
        System.out.println(message);
        byteBuf.release();
    }

}
