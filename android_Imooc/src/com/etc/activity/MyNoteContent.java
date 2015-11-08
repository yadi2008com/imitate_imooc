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

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b.R;

public class MyNoteContent extends Activity {
	private TextView tv_coursetitle_show, tv_note_content, tv_note_time = null;
	private ImageView img_delect = null;
	private Intent intent;
	String cour_title = null;
	private List<Map<String, Object>> simpleList = null;
	private myAdpater myAdpater = null;
	private ListView listView = null;
	private int course_id = 0;
	private String path = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_note_content);
		this.tv_note_content = (TextView) findViewById(R.id.tv_note_content);
		this.tv_coursetitle_show = (TextView) findViewById(R.id.tv_coursetitle_show);
		this.img_delect = (ImageView) findViewById(R.id.img_delect);
		this.tv_note_time = (TextView) findViewById(R.id.tv_note_time);
		// 接收数据
		intent = getIntent();
		course_id = intent.getIntExtra("course_id", 0);
		cour_title = intent.getStringExtra("cour_title");
		path = "http://119.29.85.27/Imooc/MyNoteServlet?type=json&user_id=4&course_id="
				+ course_id;
		this.listView = (ListView) findViewById(R.id.notecontentlist);
		new MyAsyncTask().execute(path);// 实例化listView从PC端加载数据
		// this.img_delect.setOnClickListener(new
		// Img_delectOnClickListenerImpl());// 删除的监听
		this.listView
				.setOnItemClickListener(new listViewOnItemClickListenerImpl());// 设置每一项的点击事件
	}

	/**
	 * @功能 设置每一项的点击事件
	 * @author 许建皓
	 * 
	 */
	private class listViewOnItemClickListenerImpl implements
			OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(MyNoteContent.this, "视频播放", 1).show();
			// 点击每一项进入视频播放
		}
	}

	/**
	 * @功能 删除笔记
	 * @author 许建皓
	 */
	public void img_delectClick(Object mynotes_id) {
		try {
			final int mynotes_idInt = Integer.parseInt(mynotes_id.toString());
			// 弹框
			Dialog dialog = new AlertDialog.Builder(MyNoteContent.this)
					.setMessage("删除笔记？")
					.setPositiveButton("删除",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
									Thread thread = new Thread(new Runnable() {
										@Override
										public void run() {
											deleteMyNotes(mynotes_idInt);
											new MyAsyncTask().execute(path);// 删除后重新加载
										}
									});
									thread.start();
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).create();
			dialog.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除mynote
	private void deleteMyNotes(final int mynotes_idInt) {
		String urlPath = "http://119.29.85.27/Imooc/MyNoteDeleteServlet?mynotes_idInt="
				+ mynotes_idInt;
		String OtherOpinionJsonString = "";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(urlPath);
		try {
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					OtherOpinionJsonString = EntityUtils.toString(httpEntity,
							"utf-8");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public class MyAsyncTask extends
			AsyncTask<String, Void, List<Map<String, Object>>> {

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

			try {

				JSONObject jsonObject = new JSONObject(noteString);
				JSONArray jsonArray = jsonObject.getJSONArray("notebeanlist");
				JSONObject jsonObject2 = new JSONObject();
				simpleList = new ArrayList<Map<String, Object>>();

				for (int i = 0; i < jsonArray.length(); i++) {
					jsonObject2 = jsonArray.getJSONObject(i);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("mynotes_id", jsonObject2.getInt("mynotes_id"));
					map.put("mynote_content",
							jsonObject2.getString("mynote_content"));
					simpleList.add(map);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return simpleList;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			myAdpater = new myAdpater(MyNoteContent.this, result,
					R.layout.notecontentlist,
					new String[] { "mynote_content" },
					new int[] { R.id.tv_note_content, });
			listView.setAdapter(myAdpater);
			MyNoteContent.this.tv_coursetitle_show.setText(cour_title);
		}

	}

	/**
	 * @功能 每一项删除的事件
	 * @author 许建皓
	 * 
	 */
	private class myAdpater extends SimpleAdapter {
		public myAdpater(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
		}

		// 每一项删除的事件
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			if (null == convertView) {
				convertView = LayoutInflater.from(MyNoteContent.this).inflate(
						R.layout.notecontentlist, null);
			}
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.img_delect);
			imageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Map<String, Object> map = (Map<String, Object>) myAdpater
							.getItem(position);
					img_delectClick(map.get("mynotes_id"));
				}
			});
			return super.getView(position, convertView, parent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_note_content, menu);
		return true;
	}

}
