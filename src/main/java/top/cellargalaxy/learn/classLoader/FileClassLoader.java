package top.cellargalaxy.learn.classLoader;

import java.io.*;

/**
 * Created by cellargalaxy on 17-10-22.
 */
public class FileClassLoader extends ClassLoader{
	private String rootPath;
	
	public FileClassLoader(String rootPath) {
		this.rootPath = rootPath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println("用到FileClassLoader加载类了");
		byte[] bytes=readData(name);
		if (bytes==null) {
			throw new ClassNotFoundException();
		}else{
			return defineClass(name,bytes,0,bytes.length);
		}
	}
	
	private byte[] readData(String name){
		BufferedInputStream inputStream=null;
		try {
			inputStream=new BufferedInputStream(new FileInputStream(rootPath+ File.separator+name+".class"));
			ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
			byte[] bytes=new byte[1024];
			int len;
			while ((len = inputStream.read(bytes)) != -1) {
				byteArrayOutputStream.write(bytes,0,len);
			}
			return byteArrayOutputStream.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
