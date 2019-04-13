package com.nick.imdb.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author Nick Yuan
 * @date 2019/4/9
 * @mood shitty
 */
public class NettyHandler extends ChannelInboundHandlerAdapter {
    private ByteBuf buf;
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        buf = ctx.alloc().buffer(4,44); // (1)
        //(initial capacity, max capacity) 一个参数则为initial capacity, meaning that the capacity can be
        //extended
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        buf.release(); // (1)
        buf = null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg;
        buf.writeBytes(m); // (2)

            System.out.println(buf.readableBytes());// (3)
            while(buf.isReadable()){
                System.out.print((char)buf.readByte());

            }
            System.out.println("!!!!!!!");
        //    ctx.close();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}

