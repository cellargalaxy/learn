package top.cellargalaxy.learn.classLoader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cellargalaxy on 17-10-22.
 */
public class UrlClassLoader extends ClassLoader{
	private String urlPath;
	
	public UrlClassLoader(String urlPath) {
		this.urlPath = urlPath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println("用到UrlClassLoader加载类了");
		byte[] bytes=readData(name);
		if (bytes==null) {
			throw new ClassNotFoundException();
		}else{
			return defineClass(name,bytes,0,bytes.length);
		}
	}
	
	private byte[] readData(String name){
		try {
			URL url=new URL(urlPath+"/"+name.substring(name.lastIndexOf('.')+1,name.length())+".class");
			InputStream inputStream=new BufferedInputStream(url.openStream());
			ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
			byte[] bytes=new byte[1024];
			int len;
			while ((len = inputStream.read(bytes)) != -1) {
				byteArrayOutputStream.write(bytes,0,len);
			}
			return byteArrayOutputStream.toByteArray();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
