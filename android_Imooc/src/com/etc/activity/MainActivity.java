package com.etc.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import com.etc.activity.RefreshableView.PullToRefreshListener;
import com.etc.util.LeftMenu;
import com.etc.util.RightMenu;
import com.etc.util.Title;
import com.example.b.R;

public class MainActivity extends Activity {
	View convertView;
	RefreshableView refreshableView;

	public static int[] picture = { R.drawable.imooc1, R.drawable.imooc2,
			R.drawable.imooc3, R.drawable.imooc4, R.drawable.imooc5 };
	private MyGallery pictureGallery = null;
	private int index = 0;
	private ListView listView = null;
	List<String> imagelist = new ArrayList<String>();
	List<String> imagelist2 = new ArrayList<String>();
	StringBuffer imageNameString = new StringBuffer();

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private SimpleAdapter simpleAdapter = null;

	private final String CITY_PATH_JSON = "http://119.29.85.27/Imooc/CourseShowNewServlet?type=json";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);

		// 封装title+leftMenu
		View titleView = findViewById(R.id.title);
		View menu_layout = findViewById(R.id.menu_layout);
		Title title = new Title(MainActivity.this);
		title.init(titleView, menu_layout);
		title.initListener();

		View left_drawer = findViewById(R.id.left_drawer);
		LeftMenu leftMenu = new LeftMenu(MainActivity.this);
		leftMenu.initLeftMenuItem(left_drawer);// 实例化左侧菜单的子项
		leftMenu.leftMenuItemSetOnclickListener();// 为左侧菜单的子项添加点击事件
		// 封装title+leftMenu
        
		//封装右侧菜单
		View right_drawer = findViewById(R.id.right_drawer);
		RightMenu rightMenu = new RightMenu(MainActivity.this);
		rightMenu.initRightMenuItem(right_drawer);// 实例化左侧菜单的子项
		rightMenu.rightMenuItemSetOnclickListener();
		listView = (ListView) super.findViewById(R.id.classlist);

		this.pictureGallery = (MyGallery) findViewById(R.id.mygallery);
		ImageAdapter adapter = new ImageAdapter(this);
		this.pictureGallery.setAdapter(adapter);
		Timer timer = new Timer();
		timer.schedule(task, 3000, 3000);

		// 执行josn接收数据
		new MyAsyncTask().execute(CITY_PATH_JSON);
		refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);
	}

	// 下拉刷新
	/**
	 * @功能 接收json数据
	 * @author 派大星
	 * 
	 */
	public class MyAsyncTask extends
			AsyncTask<String, Void, List<Map<String, Object>>> {
		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			String courseString = "";
			HttpClient httpClient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(params[0]);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = httpResponse.getEntity();
					if (httpEntity != null) {
						courseString = EntityUtils.toString(httpEntity, "utf-8");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}
			// 添加到simpleadmept的list

			try {
				JSONObject jsonObject = new JSONObject(courseString);
				JSONArray jsonArray = jsonObject.getJSONArray("courseBeans");
				JSONObject jsonObject2 = new JSONObject();
				for (int i = 0; i < jsonArray.length(); i++) {
					jsonObject2 = jsonArray.getJSONObject(i);

					Map<String, Object> map = new HashMap<String, Object>();
					String cour_image = jsonObject2.getString("cour_image");
					map.put("images",
							returnBitMap("http://119.29.85.27/Imooc/CourseImages/"
									+ cour_image));
					map.put("cour_title", jsonObject2.getString("cour_title"));

					map.put("cour_hot", jsonObject2.getInt("cour_hot"));
					map.put("cour_duration",
							jsonObject2.getString("cour_duration"));
					// 视频链接地址
					map.put("cour_url", jsonObject2.getString("cour_url"));
					// 视频简介
					map.put("cour_content", jsonObject2.getString("cour_content"));
					map.put("course_id", jsonObject2.getString("course_id"));
					list.add(map);
				}
				System.out.println("aaa" + imagelist);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// 处理数据，显示到list
			simpleAdapter = new SimpleAdapter(MainActivity.this, result,
					R.layout.classlist, new String[] { "images", "cour_title",
							"cour_hot", "cour_duration" }, new int[] {
							R.id.image, R.id.title, R.id.num, R.id.time });

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
			// 点击listview进行视频的播放
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					@SuppressWarnings("unchecked")
					Map<String, String> map = (Map<String, String>) MainActivity.this.simpleAdapter
							.getItem(position);
                    String course_id=map.get("course_id");
					String cour_url = map.get("cour_url");
					String cour_title = map.get("cour_title");
					String cour_content = map.get("cour_content");
					Intent intent = new Intent();
					intent.putExtra("cour_url", cour_url);
					intent.putExtra("cour_title", cour_title);
					intent.putExtra("cour_content", cour_content);
					intent.putExtra("course_id", course_id);
					intent.setClass(MainActivity.this, VideoPlayerActivity.class);
					MainActivity.this.startActivity(intent);
				}
			});
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

	private TimerTask task = new TimerTask() {
		@Override
		public void run() {
			Message message = new Message();
			message.what = 2;
			index = pictureGallery.getSelectedItemPosition();
			index++;
			handler.sendMessage(message);
		}
	};

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 2:
				pictureGallery.setSelection(index);
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 自定义图片显示适配器
	 * 
	 */
	class ImageAdapter extends BaseAdapter {
		private Context context;

		public ImageAdapter(Context context) {
			this.context = context;
		}

		public int getCount() {
			return Integer.MAX_VALUE;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		@SuppressWarnings("deprecation")
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView = new ImageView(context);
			imageView.setImageResource(picture[position % picture.length]);
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new Gallery.LayoutParams(
					Gallery.LayoutParams.FILL_PARENT,
					Gallery.LayoutParams.FILL_PARENT));
			return imageView;
		}
	}

	// 退出程序
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			showTips();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void showTips() {
		AlertDialog alertDialog = new AlertDialog.Builder(this)
				.setMessage("确定退出慕课网？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						android.os.Process.killProcess(android.os.Process
								.myPid());
						System.exit(0);

					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub

					}
				}).create();
		alertDialog.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
