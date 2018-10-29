package top.cellargalaxy.learn. netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by cellargalaxy on 18-5-9.
 */
public class TimeDecoder extends ByteToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
		if (byteBuf.readableBytes() < 8) {//不够8个字节时直接返回
			return;
		}
		list.add(new Time(byteBuf.readLong()));//够8个字节就new一个Time
	}
}
