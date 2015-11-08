package com.etc.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.etc.activity.MainActivity.MyAsyncTask;
import com.etc.activity.RefreshableView.PullToRefreshListener;
import com.example.b.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.VideoView;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class VideoPlayerActivity extends Activity {
	private TabHost host = null;
	private int[] LayRes = new int[] { R.id.tab_content, R.id.tab_plan };
	private String[] titleList = new String[] { "课程简介", "课程计划" };
	private TextView tv_content, tv_title, tv_show;
	private VideoView videoView = null;
	private SimpleAdapter simpleAdapter = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		this.tv_show = (TextView) findViewById(R.id.tv_show);
		this.tv_content = (TextView) findViewById(R.id.tv_content);
		this.tv_title = (TextView) findViewById(R.id.tv_title);
		this.videoView = (VideoView) findViewById(R.id.videoview);
		Intent intent = getIntent();
		String cour_url = intent.getStringExtra("cour_url");
		String cour_content = intent.getStringExtra("cour_content");
		String cour_title = intent.getStringExtra("cour_title");
		String course_id = intent.getStringExtra("course_id");
		System.out.println(course_id);
		int id=Integer.parseInt(course_id);
		this.host = (TabHost) super.findViewById(R.id.tabhost);
		this.host.setup();
		for (int x = 0; x < LayRes.length; x++) {
			TabSpec spec = this.host.newTabSpec("tab" + x);
			spec.setIndicator(titleList[x]);
			spec.setContent(LayRes[x]);
			this.host.addTab(spec);
		}
		this.host.setCurrentTab(0);
		this.tv_title.setText(cour_title);
		this.tv_content.setText(cour_content);
		// 视频播放
		String path = "http://119.29.85.27/Imooc/CourseSource/" + cour_url;
		Uri uri = Uri.parse(path);
		videoView.setMediaController(new MediaController(
				VideoPlayerActivity.this));
		videoView.setVideoURI(uri);
		videoView.requestFocus();
		// 视频计划
		String path2 = "http://119.29.85.27/Imooc/CourseToPlanServlet?id="+id;
		// 接收数据
		new MyAsyncTask().execute(path2);
	}

	public class MyAsyncTask extends
			AsyncTask<String, Void, Map<String, Map<String, List>>> {

		@Override
		protected void onPostExecute(Map<String, Map<String, List>> result) {
			super.onPostExecute(result);
			
			Map<String, List> map = new HashMap<String, List>();
			map = result.get("plandetail");
			List<Map<String,String>> objects = map.get("section");
			List<Map<String,String>> objects2 = map.get("chapter");
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < objects2.size(); i++) {
				stringBuffer.append(objects2.get(i).get("chap_name").toString()+"\n");
				for (int j = 0; j < objects.size(); j++) {
                    if(objects.get(j).get("chap_id")==objects2.get(i).get("chapter_id"))
                    {
                    	stringBuffer.append("\t"+objects.get(j).get("sect_name").toString()+"\n");
                    }
				}
			}
			VideoPlayerActivity.this.tv_show.setText(stringBuffer);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Map<String, Map<String, List>> doInBackground(
				String... params) {
			Map<String, Map<String, List>> data = new HashMap<String, Map<String, List>>();
			String plandetail = "";
			HttpClient httpClient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(params[0]);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = httpResponse.getEntity();
					if (httpEntity != null) {
						plandetail = EntityUtils.toString(httpEntity, "utf-8");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}
			try {
				data = JSON.parseObject(plandetail,
						new TypeReference<Map<String, Map<String, List>>>() {
						});
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(data);
			return data;
		}
	}
}
