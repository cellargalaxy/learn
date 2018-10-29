package top.cellargalaxy.learn. nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by cellargalaxy on 18-4-17.
 * https://blog.csdn.net/u013256816/article/details/51457215
 */
public class Test {
	public static void main(String[] args) throws IOException, InterruptedException {
//		fileWrite();
//		fileRead();
		client0();
	}
	
	public static void client() {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
			
			if (socketChannel.finishConnect()) {
				int i = 0;
				while (true) {
					TimeUnit.SECONDS.sleep(1);
					String info = "I'm " + i++ + "-th information from client";
					buffer.clear();
					buffer.put(info.getBytes());
					buffer.flip();
					while (buffer.hasRemaining()) {
						System.out.println(buffer);
						socketChannel.write(buffer);
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socketChannel != null) {
					socketChannel.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final void client0() throws IOException, InterruptedException {
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8787));
		socketChannel.configureBlocking(false);
		ByteBuffer byteBuffer = ByteBuffer.allocate(64);
		
		if (socketChannel.finishConnect()) {
			byteBuffer.put("GET / HTTP/1.1\nHost:www.baidu.com\n".getBytes());
			byteBuffer.flip();
			while (byteBuffer.hasRemaining()) {
				System.out.println("write: " + socketChannel.write(byteBuffer));
				TimeUnit.SECONDS.sleep(1);
			}
			
			byteBuffer.clear();
			int len;
			while (true) {
				while ((len = socketChannel.read(byteBuffer)) > 0) {
//					System.out.println("已经读取的byte数：" + len);
					byteBuffer.flip();
					System.out.print(new String(byteBuffer.array(), 0, len));
					byteBuffer.clear();
				}
				if (len == -1) {
					System.out.println("close");
					break;
				}
				TimeUnit.SECONDS.sleep(1);
			}
		}
		
		socketChannel.close();
	}
	
	public static final void fileRead() throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("/home/cellargalaxy/nio.txt", "rw");
		FileChannel fileChannel = randomAccessFile.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		
		int len;
		while ((len = fileChannel.read(byteBuffer)) != -1) {
			byteBuffer.flip();
			System.out.print(new String(byteBuffer.array(), 0, len));
			byteBuffer.clear();
		}
		
		fileChannel.close();
		randomAccessFile.close();
	}
	
	public static final void fileWrite() throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("/home/cellargalaxy/nio.txt", "rw");
		FileChannel fileChannel = randomAccessFile.getChannel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(128);
		
		byteBuffer.put((UUID.randomUUID().toString() + "\n").getBytes());
		byteBuffer.put((UUID.randomUUID().toString() + "\n").getBytes());
		byteBuffer.put((UUID.randomUUID().toString() + "\n").getBytes());
		byteBuffer.flip();
		while (byteBuffer.hasRemaining()) {
			fileChannel.write(byteBuffer);
		}
		
		fileChannel.close();
		randomAccessFile.close();
	}
}
