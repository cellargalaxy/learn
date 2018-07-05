package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

/**
 * Created by cellargalaxy on 18-5-9.
 */
public class TimeClientOutboundHandlerAdapter extends ChannelOutboundHandlerAdapter {
	public TimeClientOutboundHandlerAdapter() {
		super();
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter()");
	}

	@Override
	public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.bind()");
		super.bind(ctx, localAddress, promise);
	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.connect()");
		super.connect(ctx, remoteAddress, localAddress, promise);
	}

	@Override
	public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.disconnect()");
		super.disconnect(ctx, promise);
	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.close()");
		super.close(ctx, promise);
	}

	@Override
	public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.deregister()");
		super.deregister(ctx, promise);
	}

	@Override
	public void read(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.read()");
		super.read(ctx);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.write()");
		super.write(ctx, msg, promise);
	}

	@Override
	public void flush(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.flush()");
		super.flush(ctx);
	}

	@Override
	protected void ensureNotSharable() {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.ensureNotSharable()");
		super.ensureNotSharable();
	}

	@Override
	public boolean isSharable() {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.isSharable()");
		return super.isSharable();
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.handlerAdded()");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.handlerRemoved()");
		super.handlerRemoved(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientOutboundHandlerAdapter.exceptionCaught()");
		super.exceptionCaught(ctx, cause);
	}
}
