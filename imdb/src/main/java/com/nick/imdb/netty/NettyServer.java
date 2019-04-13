package com.nick.imdb.netty;

import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Nick Yuan
 * @date 2019/4/9
 * @mood shitty
 */
public class NettyServer {
    public void run() throws InterruptedException {
        EventLoopGroup boss=new NioEventLoopGroup();
        EventLoopGroup worker=new NioEventLoopGroup();
        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(boss,worker).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MyDecoder(),new NettyHandler());
                    }
                })
                .childOption(ChannelOption.SO_KEEPALIVE,true);
        bootstrap.bind(8091).sync();
    }

    public static void main(String[] args) throws Exception{
        new NettyServer().run();
    }
}
