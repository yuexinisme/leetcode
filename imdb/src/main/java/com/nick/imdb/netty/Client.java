package com.nick.imdb.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Nick Yuan
 * @date 2019/4/9
 * @mood shitty
 */
public class Client {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boss=new NioEventLoopGroup();
        Bootstrap strap=new Bootstrap();
        strap.group(boss)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyClient());
                    }
                })
                .option(ChannelOption.SO_KEEPALIVE,true);
        strap.connect("localhost",8091).sync();
    }
}
