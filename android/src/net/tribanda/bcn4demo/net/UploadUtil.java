package net.tribanda.bcn4demo.net;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import net.tribanda.bcn4demo.Constants;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class UploadUtil {
	public static String uploadVideo(InputStream is) throws Exception
	{
         HttpClient httpClient = new DefaultHttpClient();
         HttpPost postRequest = new HttpPost(Constants.UPLOAD_URL);
         byte[] data = IOUtils.toByteArray(is);
         InputStreamBody isb = new InputStreamBody(new ByteArrayInputStream(data),"upload");
         MultipartEntity multipartContent = new MultipartEntity();
         multipartContent.addPart("upload", isb);
         postRequest.setEntity(multipartContent);
         HttpResponse res = httpClient.execute(postRequest);
         HttpEntity entity = res.getEntity();
         String path = Constants.SERVER_NAME + "/" + EntityUtils.toString(entity);
         entity.getContent().close();
         
         return path;
	}
}
