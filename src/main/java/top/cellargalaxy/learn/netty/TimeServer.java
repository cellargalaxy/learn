package top.cellargalaxy.learn. netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;

/**
 * Created by cellargalaxy on 18-5-9.
 */
public class TimeServer {
	public static void main(String[] args) throws InterruptedException {
		//用于处理连接的接收
		EventLoopGroup boss = new NioEventLoopGroup();
		//用于处理连接数据的收发
		EventLoopGroup worker = new NioEventLoopGroup();
		try {
			//主类，服务端的是ServerBootstrap
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			//设置两个EventLoopGroup
			serverBootstrap.group(boss, worker);
			//所创建的隧道为NioServerSocketChannel，实现ServerSocketChannel接口
			serverBootstrap.channel(NioServerSocketChannel.class);
			//ServerSocketChannel的最大连接数
			serverBootstrap.option(ChannelOption.SO_BACKLOG, 16);
			//ServerSocketChannel接收的SocketChannel是长连接
			serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			//ServerSocketChannel接收的SocketChannel的初始化操作
			serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					//我们的初始化操作是给这个SocketChannel添加Handler，下面添加的Handler是有一定顺序的
					socketChannel.pipeline().addLast(
							new TimeDecoder(), //先把数据转变的Time
							new TimeServerInboundHandlerAdapter(), //再处理Time
							new TimeServerOutboundHandlerAdapter());
				}
			});
			//绑定端口
			ChannelFuture channelFuture = serverBootstrap.bind(3210).sync();
			//这个不懂什么意思
			channelFuture.channel().closeFuture().sync();
		} finally {//关闭EventLoopGroup
			Future bossFuture = boss.shutdownGracefully();
			Future workerFuture = worker.shutdownGracefully();
		}
	}
}
