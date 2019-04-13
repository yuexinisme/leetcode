package com.nick.imdb.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author Nick Yuan
 * @date 2019/4/9
 * @mood shitty
 */
public class NettyClient extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { //连接成功后激发
        //服务器和客户端都是如此
        ByteBuf buffer = ctx.alloc().buffer(44);
       for(int i=1;i<6;i++){
           StringBuilder builder=new StringBuilder();
           for(int j=0;j<i;j++){
               builder.append(i);
           }
           buffer.writeCharSequence(builder.toString(),Charset.defaultCharset());
           ctx.writeAndFlush(buffer);
       }
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf= (ByteBuf) msg;
        while(buf.isReadable()){
            char c = (char)buf.readByte();
            System.out.print(c+" ");
        }
    }

    public static void main(String[] args) throws Exception{
        Socket socket=new Socket();
        socket.connect(new InetSocketAddress("localhost",8091));
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer=new PrintWriter(outputStream);


        for(int i=1;i<10;i++){
            StringBuilder builder=new StringBuilder();

                builder.append(i);

            writer.write(builder.toString());
            writer.flush();
            Thread.sleep(1000);
        }

    }
}
