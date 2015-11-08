package com.etc.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class OtherOpinionInfoUtils {
	public static String getOtherOpinionJsonStringByPageno(int pageno){
		String OtherOpinionJsonString="";
		String urlPath = "http://10.0.2.2:8080/Imooc/OtherOpinionGetServlet?pageno="+pageno;
		HttpClient httpClient=new DefaultHttpClient();
		HttpPost httpPost=new HttpPost(urlPath);
		try {
			HttpResponse httpResponse=httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				HttpEntity httpEntity=httpResponse.getEntity();
				if(httpEntity!=null){
					OtherOpinionJsonString=EntityUtils.toString(httpEntity,"utf-8");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
		
		
		return OtherOpinionJsonString;
	}
}
