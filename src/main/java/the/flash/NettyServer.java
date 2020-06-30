package the.flash;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

public class NettyServer {

    private static final int BEGIN_PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        serverBootstrap
                // bossGroup表示监听端口，accept 新连接的线程组，workerGroup表示处理每一条连接的数据读写的线程组
                .group(boosGroup, workerGroup)
                // 指定我们服务端的 IO 模型为NIO; 想指定 IO 模型为 BIO，那么这里配置上OioServerSocketChannel.class
                .channel(NioServerSocketChannel.class)
                // NioServerSocketChannel指定一些自定义属性，然后我们可以通过channel.attr()取出这个属性
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                // childAttr可以给每一条连接指定自定义属性
                .childAttr(clientKey, "clientValue")
                // 给服务端channel设置一些属性
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 给每条连接设置一些TCP底层相关的属性
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 定义后续每条连接的数据读写，NioSocketChannelNetty 对 NIO 类型的连接的抽象
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        System.out.println(ch.attr(clientKey).get());
                    }
                });


        bind(serverBootstrap, BEGIN_PORT);
    }

    /**
     * 绑定端口
     *
     * @param serverBootstrap
     * @param port
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        // 绑定端口
        serverBootstrap.bind(port).addListener(future -> {
            // 绑定端口回调
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
                // 绑定失败继续绑定下一个端口
                bind(serverBootstrap, port + 1);
            }
        });
    }
}
