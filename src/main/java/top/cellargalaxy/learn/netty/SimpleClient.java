package top.cellargalaxy.learn. netty;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
/**
 * Created by cellargalaxy on 18-5-9.
 */
public class SimpleClient {
	public void connect(String host, int port) throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new SimpleClientHandler());
				}
			});

			ChannelFuture f = b.connect(host, port).sync();

			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		SimpleClient client=new SimpleClient();
		client.connect("127.0.0.1", 9999);
	}
}