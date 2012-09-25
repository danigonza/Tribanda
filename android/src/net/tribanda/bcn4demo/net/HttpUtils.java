package net.tribanda.bcn4demo.net;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.List;

import net.tribanda.bcn4demo.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	public static String Get(String url) throws Exception
	{
		HttpGet post = new HttpGet(url);
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		String responseText = EntityUtils.toString(entity);
		
		return responseText;
	}

	public static String Post(String url, String json) throws Exception
	{
		HttpPost post = new HttpPost(url);
		
		List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("json", json));
		
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		String responseText = EntityUtils.toString(entity);
		
		return responseText;
	}
}
