package com.etc.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.etc.bean.UsersBean;
import com.etc.dao.SearchCourseDao;
import com.etc.dao.SelectUserInfoDao;
import com.example.b.R;

public class MyNoteOriginal extends Activity {
	private LinearLayout linout_note = null;
	private Intent intent;
	private ListView listView = null;
	private SimpleAdapter simpleAdapter = null;
	private List<Map<String, Object>> simpleList = null;
	private TextView tv_pagnum = null;
	private TextView tv_notenum = null;
	int pagnum = 0;
	private ImageView iv_course = null;
	private String Image_Path = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_note_original);
		tv_pagnum = (TextView) findViewById(R.id.tv_pagnum);
		iv_course = (ImageView) findViewById(R.id.iv_course);
		tv_notenum = (TextView) findViewById(R.id.tv_notenum);
		linout_note = (LinearLayout) findViewById(R.id.linout_note);
		UsersBean usersBean = new UsersBean();
		SelectUserInfoDao selectUserInfoDao = new SelectUserInfoDao(
				MyNoteOriginal.this);
		usersBean = selectUserInfoDao.SelectLoginUser();

		String path = "http://119.29.85.27/Imooc/MyNoteTitleServlet?user_id="
				+ usersBean.getUser_id();

		this.listView = (ListView) findViewById(R.id.notelist);
		new MyAsyncTask().execute(path);

		this.listView
				.setOnItemClickListener(new ListViewOnItemClickListenerImpl());
	}

	public class MyAsyncTask extends
			AsyncTask<String, Void, List<Map<String, Object>>> {
		// Bitmap bm = null;

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			String noteString = "";
			HttpClient httpClient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(params[0]);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = httpResponse.getEntity();
					if (httpEntity != null) {
						noteString = EntityUtils.toString(httpEntity, "utf-8");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}
			// 添加到simpleadmept的list
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			try {
				JSONObject jsonObject = new JSONObject(noteString);
				JSONArray jsonArray = jsonObject.getJSONArray("myNoteList");
				JSONObject jsonObject2 = new JSONObject();
				for (int i = 0; i < jsonArray.length(); i++) {
					jsonObject2 = jsonArray.getJSONObject(i);
					Map<String, Object> map = new HashMap<String, Object>();
					String cour_image = jsonObject2.getString("cour_image");
					map.put("images",
							returnBitMap("http://192.168.207.55:8080/Imooc/CourseImages/"
									+ cour_image));
					map.put("course_id", jsonObject2.getInt("course_id"));
					map.put("cour_title", jsonObject2.getString("cour_title"));
					map.put("count(mynotes_id)",
							jsonObject2.getInt("count(mynotes_id)"));
					pagnum += jsonObject2.getInt("count(mynotes_id)");// 共有几条笔记
					list.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			simpleAdapter = new SimpleAdapter(MyNoteOriginal.this, result,
					R.layout.notelist, new String[] { "images", "cour_title",
							"count(mynotes_id)" }, new int[] { R.id.iv_course,
							R.id.tv_coursetitle_list, R.id.tv_notenum });
			simpleAdapter.setViewBinder(new ViewBinder() {
				public boolean setViewValue(View view, Object data,
						String textRepresentation) {

					if (view instanceof ImageView && data instanceof Bitmap) {
						ImageView iv = (ImageView) view;
						iv.setImageBitmap((Bitmap) data);
						return true;
					} else {
						return false;
					}
				}
			});
			listView.setAdapter(simpleAdapter);
			tv_pagnum.setText("" + pagnum);

		}
	}

	private class ListViewOnItemClickListenerImpl implements
			OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Map<String, Object> map = (Map<String, Object>) MyNoteOriginal.this.simpleAdapter
					.getItem(position);
			Intent intent = new Intent(MyNoteOriginal.this, MyNoteContent.class);
			intent.putExtra("mynotes_id", (Integer) map.get("mynotes_id"));
			intent.putExtra("course_id", (Integer) map.get("course_id"));
			intent.putExtra("cour_title", (String) map.get("cour_title"));
			startActivity(intent);
		}
	}

	// 获取网络图片

	public Bitmap returnBitMap(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_note_original, menu);
		return true;
	}

}
