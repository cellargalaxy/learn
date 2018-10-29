package top.cellargalaxy.learn. netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

import java.util.Date;

/**
 * Created by cellargalaxy on 18-5-9.
 */
public class TimeClientInboundHandlerAdapter extends ChannelInboundHandlerAdapter {
	public TimeClientInboundHandlerAdapter() {
		super();
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter()");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.channelRead()");
//		ByteBuf byteBuf = (ByteBuf) msg;//如果没有TimeDecoder这个先前处理，则直接转型为ByteBuf
//		try {
//			long ll = byteBuf.readLong();
//			System.out.println("发来的时间: " + new Date(ll));
//		} finally {
//			byteBuf.release();//在ByteBuf读操作之后，据说是个重要的操作，要在finally里确保执行
//		}

		Time time = (Time) msg;//如果有TimeDecoder这个先前处理，就可以直接转型为Time了
		System.out.println("发来的时间: " + time.createDate());

		ByteBuf byteBuf = ctx.alloc().buffer(8);//创建一个ByteBuf，大小为8个字节，装下一个long
		byteBuf.writeLong(new Date().getTime());//往byteBuf写数据
		//往管道(?)里写数据。可以用ctx.write(Object)和ctx.flush()代替
		//ctx.write(Object)并不会往管道里写数据，而是只是把数据存放在缓存里，所以需要ctx.flush()
		//当然由于是非阻塞IO，这个方法执行后不代表数据已经发送，所以返回一个ChannelFuture对象
		ChannelFuture channelFuture = ctx.writeAndFlush(byteBuf);
		//给channelFuture添加一个监听器，当数据发送完成就关闭管道
		channelFuture.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture channelFuture) throws Exception {
				ctx.close();
			}
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.exceptionCaught()");
		ctx.close();//发送异常就关闭管道
		cause.printStackTrace();//打印异常
	}

	@Override
	public boolean isSharable() {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.isSharable()");
		return super.isSharable();
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.handlerAdded()");
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.handlerRemoved()");
		super.handlerRemoved(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.channelRegistered()");
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.channelUnregistered()");
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.channelActive()");
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.channelInactive()");
		super.channelInactive(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.channelReadComplete()");
		super.channelReadComplete(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.userEventTriggered()");
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.channelWritabilityChanged()");
		super.channelWritabilityChanged(ctx);
	}

	@Override
	protected void ensureNotSharable() {
		System.out.println("调用:" + hashCode() + ": TimeClientInboundHandlerAdapter.ensureNotSharable()");
		super.ensureNotSharable();
	}
}
