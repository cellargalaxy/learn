package httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by cellargalaxy on 18-3-14.
 */
public class HttpClientDeal {
	//使用HttpClients的静态方法创建一个CloseableHttpClient对象。可以理解httpClient就是个浏览器一样，通过httpClient这个浏览器发送http请求
	private static final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	/**
	 * 创建一个HttpGet请求的对象，
	 * @param url 请求地址
	 * @param nameValuePairs 请求参数，key-value参数封装在NameValuePair对象里
	 * @return
	 */
	public static final HttpGet createHttpGet(String url, List<NameValuePair> nameValuePairs) {
		try {
			if (url == null) {
				return null;
			}
			HttpGet httpGet = new HttpGet(url);
			if (nameValuePairs != null && nameValuePairs.size() > 0) {
				//将参数再封装到UrlEncodedFormEntity的实体对象里，并使用EntityUtils的静态方法转变成url问号后面的key=value。
				//如果有中文什么的也会编码好的。关于UrlEncodedFormEntity类在源码里再说
				String paramsString = EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
				httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + paramsString));
			}
			return httpGet;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 跟createHttpGet一样
	 * @param url
	 * @param nameValuePairs
	 * @return
	 */
	public static final HttpPost createHttpPost(String url, List<NameValuePair> nameValuePairs) {
		try {
			if (url == null) {
				return null;
			}
			HttpPost httpPost = new HttpPost(url);
			if (nameValuePairs != null && nameValuePairs.size() > 0) {
				String paramsString = EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
				httpPost.setURI(new URI(httpPost.getURI().toString() + "?" + paramsString));
			}
			return httpPost;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final String executeHttpRequestBase(HttpRequestBase httpRequestBase, int timeout) {
		return executeHttpRequestBase(httpClient, httpRequestBase, timeout);
	}
	
	/**
	 * 使用httpClient请求httpRequestBase对象，并设置其请求的超时时间。
	 * httpRequestBase在源码里再说，httpRequestBase是HttpGet和HttpPost的父类
	 * @param httpClient
	 * @param httpRequestBase
	 * @param timeout
	 * @return
	 */
	public static final String executeHttpRequestBase(CloseableHttpClient httpClient, HttpRequestBase httpRequestBase, int timeout) {
		try {
			//创建请求配置对象，设置超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
			httpRequestBase.setConfig(requestConfig);//将配置对象设置到请求对象里
			HttpResponse httpResponse = httpClient.execute(httpRequestBase);//请求，并返回响应对象
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {//如果响应是200
				HttpEntity entity = httpResponse.getEntity();//获取响应实体
				return EntityUtils.toString(entity, "utf-8");//使用EntityUtils的静态方法对实体内容进行编码解析
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
