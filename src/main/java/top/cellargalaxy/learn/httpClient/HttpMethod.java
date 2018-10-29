package top.cellargalaxy.learn.httpClient;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cellargalaxy on 18-3-14.
 */
public class HttpMethod {
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
		getSsl2();
	}
	
	/**
	 * 在JDK7及一下会报异常，JDK8倒不会
	 * 我使用的是openjdk7，执行以下方法会轮流出现两种错误
	 * javax.net.ssl.SSLException: java.lang.RuntimeException: Unexpected error: java.security.InvalidAlgorithmParameterException: the trustAnchors parameter must be non-empty
	 * 以及
	 * javax.net.ssl.SSLException: Received fatal alert: protocol_version
	 * 对于第二个错误的协议版本应该指的是ssl版本的问题，当然我没有设置过于此相关的配置，也不知道怎么搞的，并且这个错误不一定各个https网址都会出现。
	 * 但是第一个错误也很是奇怪。这个错误跟网上和我师兄的错误都不一样。。。
	 */
	public static void getSsl0() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sex", "man"));
		HttpGet httpGet = HttpClientDeal.createHttpGet("https://hc.apache.org/", params);
		System.out.println(httpGet);
		String result = HttpClientDeal.executeHttpRequestBase(httpGet, 5000);
		System.out.println(result);
	}
	
	/**
	 * 使用httpClient访问https网站
	 *
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static void getSsl2() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException, IOException {
		//X509TrustManager好像是java自带，用来检验ssl的接口，实现这些接口，方法体为空就好
		X509TrustManager x509TrustManager = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			
			}
			
			public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			
			}
			
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};
		
		//指定为应该是最新的TLSv1.2版本
		SSLContext context = SSLContext.getInstance("TLSv1");
		//设置X509TrustManager接口
		context.init(null, new TrustManager[]{x509TrustManager}, null);
		//然后构建一个工厂对象
		SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
		
		//不知道是个什么类，看样子是分别指定了http和https的处理
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", sslConnectionSocketFactory)
				.build();
		
		//最后又创建了一个不知道什么对象，在创建httpClient时设置进去
		PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(registry);
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(manager).build();
		
		HttpGet httpGet = new HttpGet("https://hc.apache.org/");
		String result = HttpClientDeal.executeHttpRequestBase(httpClient, httpGet, 5000);
		System.out.println(result);
	}
	
	/**
	 * 使用httpClient post http网站
	 */
	public static void post() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sex", "man"));
		HttpPost httpPost = HttpClientDeal.createHttpPost("http://hc.apache.org/", params);
		System.out.println(httpPost);
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		StringEntity stringEntity = new StringEntity("{sex:'man',age:52}", "utf-8");
		stringEntity.setContentEncoding("utf-8");
		stringEntity.setContentType("application/json");
		httpPost.setEntity(stringEntity);
		System.out.println(httpPost);
		String result = HttpClientDeal.executeHttpRequestBase(httpPost, 5000);
		System.out.println(result);
	}
	
	/**
	 * 使用httpClient get http网站
	 */
	public static void get() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("sex", "man"));
		HttpGet httpGet = HttpClientDeal.createHttpGet("http://hc.apache.org/", params);
		System.out.println(httpGet);
		String result = HttpClientDeal.executeHttpRequestBase(httpGet, 5000);
		System.out.println(result);
	}
}
