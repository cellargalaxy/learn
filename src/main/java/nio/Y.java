package nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by cellargalaxy on 18-4-17.
 */
public class Y {
	private static final int BUF_SIZE = 1024;
	private static final int PORT = 8787;
	private static final int TIMEOUT = 3000;
	
	public static void main(String[] args) throws IOException {
		server2();
	}
	
	public static void selector() throws IOException {
		Selector selector = Selector.open();
		
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while (true) {
			if (selector.select(TIMEOUT) == 0) {
				System.out.println("==");
				continue;
			}
			
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				if (selectionKey.isAcceptable()) {
					ServerSocketChannel serverSocket = (ServerSocketChannel) selectionKey.channel();
					SocketChannel socketChannel = serverSocket.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
				}
				if (selectionKey.isReadable()) {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					long bytesRead = socketChannel.read(byteBuffer);
					while (bytesRead > 0) {
						byteBuffer.flip();
						while (byteBuffer.hasRemaining()) {
							System.out.print((char) byteBuffer.get());
						}
						System.out.println();
						byteBuffer.clear();
						bytesRead = socketChannel.read(byteBuffer);
					}
					if (bytesRead == -1) {
						socketChannel.close();
					}
				}
				if (selectionKey.isWritable() && selectionKey.isValid()) {
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					byteBuffer.flip();
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					while (byteBuffer.hasRemaining()) {
						socketChannel.write(byteBuffer);
					}
					byteBuffer.compact();
				}
				if (selectionKey.isConnectable()) {
					System.out.println("isConnectable = true");
				}
				iterator.remove();
			}
		}
	}
	
	
	public static final void server2() throws IOException {
		Selector selector = Selector.open();
		
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(8787));
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while (true) {
			if (selector.select(3000) == 0) {
				System.out.println("==");
				continue;
			}
			
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				if (selectionKey.isAcceptable()) {
					ServerSocketChannel serverSocket = (ServerSocketChannel) selectionKey.channel();
					SocketChannel socketChannel = serverSocket.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(1024));
				}
				if (selectionKey.isReadable()) {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					byteBuffer.clear();
					int len;
					while ((len = socketChannel.read(byteBuffer)) > 0) {
						byteBuffer.flip();
						System.out.print(new String(byteBuffer.array(), 0, len));
						byteBuffer.clear();
					}
					if (len == -1) {
						socketChannel.close();
						System.out.println("close");
					}else {
						byteBuffer.clear();
						byteBuffer.put("i an server.\n".getBytes());
						byteBuffer.flip();
						while (byteBuffer.hasRemaining()) {
							System.out.println("write1: " + socketChannel.write(byteBuffer));
						}
					}
				}
				if (selectionKey.isWritable() && selectionKey.isValid()) {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
					byteBuffer.flip();
					while (byteBuffer.hasRemaining()) {
						System.out.println("write2: " + socketChannel.write(byteBuffer));
					}
				}
				if (selectionKey.isConnectable()) {
					System.out.println("isConnectable");
				}
				iterator.remove();
			}
		}
	}
	
	public static void server1() {
		ServerSocket serverSocket = null;
		InputStream in = null;
		try {
			serverSocket = new ServerSocket(8080);
			int recvMsgSize = 0;
			byte[] recvBuf = new byte[1024];
			while (true) {
				Socket clntSocket = serverSocket.accept();
				SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
				System.out.println("Handling client at " + clientAddress);
				in = clntSocket.getInputStream();
				while ((recvMsgSize = in.read(recvBuf)) != -1) {
					byte[] temp = new byte[recvMsgSize];
					System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
					System.out.println(new String(temp));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void server0() throws IOException {
		ServerSocket serverSocket = new ServerSocket(8787);
		Socket socket = serverSocket.accept();
		SocketAddress socketAddress = socket.getRemoteSocketAddress();
		System.out.println("Handling client at " + socketAddress);
		InputStream in = socket.getInputStream();
		byte[] bytes = new byte[1024];
		int len;
		StringBuilder stringBuilder = new StringBuilder();
		while (stringBuilder.length() < 34 && (len = in.read(bytes)) != -1) {
			stringBuilder.append(new String(bytes, 0, len));
//			System.out.println("??>>" + len + "?" + stringBuilder.length() + " : " + new String(bytes, 0, len));
		}
		System.out.println(stringBuilder.toString());
		
		OutputStream outputStream = socket.getOutputStream();
		for (int i = 0; i < 10; i++) {
			outputStream.write(("data:" + i + "\n").getBytes());
		}
		socket.close();
		serverSocket.close();
	}
}
