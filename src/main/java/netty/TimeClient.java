package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;

/**
 * Created by cellargalaxy on 18-5-9.
 */
public class TimeClient {
	public static void main(String[] args) throws InterruptedException {
		EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
		try {
			//主类，客户端的是Bootstrap
			Bootstrap bootstrap = new Bootstrap();
			//设置EventLoopGroup，这时候boss和worker都是同一个EventLoopGroup
			bootstrap.group(eventLoopGroup);
			//创建的隧道是NioSocketChannel，实现SocketChannel接口
			bootstrap.channel(NioSocketChannel.class);
			//长连接
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			//创建的SocketChannel的初始化操作
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					//我们的初始化操作是给这个SocketChannel添加Handler，下面添加的Handler是有一定顺序的
					socketChannel.pipeline().addLast(
							new TimeDecoder(), //先把数据转变的Time
							new TimeClientInboundHandlerAdapter(), //再处理Time
							new TimeClientOutboundHandlerAdapter());
				}
			});
			//连接到某个地址
			ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 3210).sync();
			//这个不懂什么意思
			channelFuture.channel().closeFuture().sync();
		} finally {//关闭EventLoopGroup
			Future future = eventLoopGroup.shutdownGracefully();
		}
	}
}
