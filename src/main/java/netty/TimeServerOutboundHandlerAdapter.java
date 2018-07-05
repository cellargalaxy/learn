package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

/**
 * Created by cellargalaxy on 18-5-9.
 */
public class TimeServerOutboundHandlerAdapter extends ChannelOutboundHandlerAdapter {
	public TimeServerOutboundHandlerAdapter() {
		super();
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter()");
	}

	//当请求将Channel绑定到一个地址时被调用
	//ChannelPromise是ChannelFuture的一个子接口，定义了如setSuccess(),setFailure()等方法
	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.bind()");
		super.bind(ctx, localAddress, promise);
	}

	//当请求将Channel连接到远程节点时被调用
	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.connect()");
		super.connect(ctx, remoteAddress, localAddress, promise);
	}

	//当请求将Channel从远程节点断开时被调用
	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.disconnect()");
		super.disconnect(ctx, promise);
	}

	//当请求关闭Channel时被调用
	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.close()");
		super.close(ctx, promise);
	}

	//当请求将Channel从它的EventLoop中注销时被调用
	@Override
	public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.deregister()");
		super.deregister(ctx, promise);
	}

	//当请求从Channel读取数据时被调用
	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.read()");
		super.read(ctx);
	}

	//当请求通过Channel将数据写到远程节点时被调用
	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.write()");
		super.write(ctx, msg, promise);
	}

	//当请求通过Channel将缓冲中的数据冲刷到远程节点时被调用
	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.flush()");
		super.flush(ctx);
	}

	@Override
	protected void ensureNotSharable() {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.ensureNotSharable()");
		super.ensureNotSharable();
	}

	//该方法不允许将此ChannelHandler共享复用
	@Override
	public boolean isSharable() {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.isSharable()");
		return super.isSharable();
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.handlerAdded()");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.handlerRemoved()");
		super.handlerRemoved(ctx);
	}

	//当处理过程中发生异常时被调用
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerOutboundHandlerAdapter.exceptionCaught()");
		super.exceptionCaught(ctx, cause);
	}
}
