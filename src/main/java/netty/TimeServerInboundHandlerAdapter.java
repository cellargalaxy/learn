package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

import java.util.Date;

/**
 * Created by cellargalaxy on 18-5-9.
 */
public class TimeServerInboundHandlerAdapter extends ChannelInboundHandlerAdapter {
	public TimeServerInboundHandlerAdapter() {
		super();
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter()");
	}

	//当channel处于活动状态（连接到远程节点）被调用
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.channelActive()");
		ByteBuf byteBuf = ctx.alloc().buffer(8);
		byteBuf.writeLong(new Date().getTime());
		ChannelFuture channelFuture = ctx.writeAndFlush(byteBuf);
	}

	//当处理过程中发生异常时被调用
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.exceptionCaught()");
		ctx.close();
		cause.printStackTrace();
	}

	//该方法不允许将此ChannelHandler共享复用
	@Override
	public boolean isSharable() {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.isSharable()");
		return super.isSharable();
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.handlerAdded()");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.handlerRemoved()");
		super.handlerRemoved(ctx);
	}

	//当channel被注册到EventLoop时被调用
	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.channelRegistered()");
		super.channelRegistered(ctx);
	}

	//当channel已经被创建，但还未注册到EventLoop（或者从EventLoop中注销）被调用
	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.channelUnregistered()");
		super.channelUnregistered(ctx);
	}

	//当channel处于非活动状态（没有连接到远程节点）被调用
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.channelInactive()");
		super.channelInactive(ctx);
	}

	//当从channel读取数据时被调用
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.channelRead()");
		Time time = (Time) msg;
		System.out.println("发来的时间: " + time.createDate());
		ctx.close();
	}

	//当channel的上一个读操作完成时被调用
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.channelReadComplete()");
		super.channelReadComplete(ctx);
	}

	//当ChannelInboundHandler.fireUserEventTriggered()方法被调用时被调用
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.userEventTriggered()");
		super.userEventTriggered(ctx, evt);
	}

	//当channel的可写状态发生改变时被调用
	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.channelWritabilityChanged()");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	protected void ensureNotSharable() {
		System.out.println("调用:" + hashCode() + ": TimeServerInboundHandlerAdapter.ensureNotSharable()");
		super.ensureNotSharable();
	}
}
