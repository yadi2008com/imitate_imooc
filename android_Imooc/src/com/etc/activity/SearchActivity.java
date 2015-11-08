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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.etc.dao.SearchCourseDao;
import com.example.b.R;

public class SearchActivity extends Activity {
	private ListView search_list = null;
	private SimpleAdapter simpleAdapter = null;
	private ImageButton search_search_button = null, search_back_image = null;
	private EditText search_title = null;
	private TextView search_tv = null, search_delete_history = null;
	private final String TEXT1 = "历史记录", TEXT2 = "相关课程", TEXT3 = "搜索结果";
	private ImageView no_course_icon = null;// 没有课程是显示暂无课程
	private TextView no_course_text = null;// 没有课程是显示暂无课程

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		initView();// 实例化空间
		showHistory();// 显示搜索历史记录
		initListener();// 实例化监听
	}

	/**
	 * @功能 显示历史记录
	 */
	public void showHistory() {
		no_course_icon.setVisibility(View.GONE);
		no_course_text.setVisibility(View.GONE);
		List<String> course_title_list = null;
		SearchCourseDao searchCourseDao = new SearchCourseDao(
				SearchActivity.this);
		course_title_list = searchCourseDao.fetchSearchCourseTitle();
		if (course_title_list != null) {
			search_list.setAdapter(new ArrayAdapter<String>(
					SearchActivity.this, R.layout.search_list_item,
					course_title_list));
		}
		if (0 != course_title_list.size()) {
			search_delete_history.setVisibility(View.VISIBLE);
		} else {
			search_delete_history.setVisibility(View.GONE);
		}
	}

	/**
	 * @功能 实例化控件
	 */
	private void initView() {
		search_list = (ListView) findViewById(R.id.search_list);
		search_search_button = (ImageButton) findViewById(R.id.search_search_button);
		search_title = (EditText) findViewById(R.id.search_title);
		search_back_image = (ImageButton) findViewById(R.id.search_back_image);
		search_tv = (TextView) findViewById(R.id.search_tv);
		search_delete_history = (TextView) findViewById(R.id.search_delete_history);
		no_course_icon = (ImageView) findViewById(R.id.no_course_icon);
		no_course_text = (TextView) findViewById(R.id.no_course_text);
	}

	/**
	 * @功能 实例化监听
	 */
	private void initListener() {
		searchButtonOnClickListener();// 点击搜索按钮的事件，显示搜索的课程和添加历史记录
		search_title.addTextChangedListener(watcher);// 搜索框内容变化是的监听事件
		backButtonOnClickListener();// 返回主界面
		deleteHistoryOnClickListener();// 清空搜索历史
		listViewOnItemClickListener();// listView的每一项点击事件
	}

	/**
	 * @功能 listView的每一项点击事件
	 */
	private void listViewOnItemClickListener() {
		search_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String text = (String) search_tv.getText();
				String cour_title = null;
				if (TEXT1.equals(text)) {
					cour_title = (String) parent.getItemAtPosition(position);
					search_title.setText(cour_title);
					buttonClickShowCourInfo(cour_title);// 点击搜索显示搜索结果
				} else if (TEXT2.equals(text)) {
					HashMap<String, Object> map = (HashMap<String, Object>) parent
							.getItemAtPosition(position);
					cour_title = (String) map.get("cour_title");
					search_title.setText(cour_title);
					buttonClickShowCourInfo(cour_title);// 点击搜索显示搜索结果
				} else if (TEXT3.equals(text)) {
					HashMap<String, Object> map = (HashMap<String, Object>) parent
							.getItemAtPosition(position);
					cour_title = (String) map.get("cour_title");
					Toast.makeText(SearchActivity.this, cour_title, 0).show();
				}
			}
		});
	}

	/**
	 * @功能 点击搜索显示搜索结果
	 * @param cour_title
	 */
	private void buttonClickShowCourInfo(String cour_title) {
		search_tv.setText(TEXT3);
		String CITY_PATH_JSON = "http://119.29.85.27/Imooc/CourseSelectServlet?type=json&cour_title="
				+ cour_title;
		if (!"".equals(cour_title)) {
			new MyAsyncTask().execute(CITY_PATH_JSON);
		}
	}

	/**
	 * @功能 清空搜索历史
	 */
	private void deleteHistoryOnClickListener() {
		search_delete_history.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				SearchCourseDao searchCourseDao = new SearchCourseDao(
						SearchActivity.this);
				searchCourseDao.deleteSearchCourseTitle();
				showHistory();
			}
		});
	}

	/**
	 * @功能 返回主界面
	 */
	private void backButtonOnClickListener() {
		search_back_image.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(SearchActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * @功能 点击搜索按钮的事件，显示搜索的课程和添加历史记录
	 */
	private void searchButtonOnClickListener() {
		search_search_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				search_delete_history.setVisibility(View.GONE);
				search_tv.setText(TEXT3);
				String cour_title = search_title.getText().toString();
				buttonClickShowCourInfo(cour_title);// 点击搜索显示搜索结果
				// 添加历史记录
				if (!"".equals(cour_title)) {
					SearchCourseDao searchCourseDao = new SearchCourseDao(
							SearchActivity.this);
					List<String> course_title_list = new ArrayList<String>();
					course_title_list = searchCourseDao
							.fetchSearchCourseTitle();
					boolean flag = true;
					for (String course_title : course_title_list) {
						if (cour_title.equals(course_title)) {
							flag = false;
						}
					}
					if (flag) {
						searchCourseDao.insertSearchCourseTitle(cour_title);
					}
				}
			}
		});
	}

	/**
	 * @功能 搜索框内容变化是的监听事件
	 */
	private TextWatcher watcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			String cour_title = search_title.getText().toString();
			if ("".equals(cour_title)) {
				// 显示历史记录
				search_tv.setText(TEXT1);
				showHistory();
			} else {
				// 非空显示相关课程
				search_delete_history.setVisibility(View.GONE);
				no_course_icon.setVisibility(View.GONE);
				no_course_text.setVisibility(View.GONE);
				search_tv.setText(TEXT2);
				String CITY_PATH_JSON = "http://119.29.85.27/Imooc/CourseSelectServlet?type=json&cour_title="
						+ cour_title;
				new MyAsyncTask1().execute(CITY_PATH_JSON);
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void afterTextChanged(Editable arg0) {
		}
	};

	/**
	 * @名称 doInBackgroundImpl
	 * @功能 封装MyAsyncTask的doInBackground()方法
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> doInBackgroundImpl(String... params) {
		String courseString = "";
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpPost httpPost = new HttpPost(params[0]);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					courseString = EntityUtils.toString(httpEntity, "utf-8");
					System.out.println("courseString" + courseString);// ----------------------
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
				map.put("cour_duration", jsonObject2.getString("cour_duration"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @功能 显示所搜出来的课程
	 * @author 郝宝亮
	 * 
	 */
	public class MyAsyncTask extends
			AsyncTask<String, Void, List<Map<String, Object>>> {
		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			List<Map<String, Object>> list = doInBackgroundImpl(params);
			return list;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// 处理数据，显示到list（符合条件的所有课程的信息）
			if (0 != result.size()) {
				no_course_icon.setVisibility(View.GONE);
				no_course_text.setVisibility(View.GONE);
			} else {
				no_course_icon.setVisibility(View.VISIBLE);
				no_course_text.setVisibility(View.VISIBLE);
			}
			simpleAdapter = new SimpleAdapter(SearchActivity.this, result,
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
			search_list.setAdapter(simpleAdapter);
		}
	}

	/**
	 * @功能 显示相关课程
	 * @author 郝宝亮
	 * 
	 */
	public class MyAsyncTask1 extends
			AsyncTask<String, Void, List<Map<String, Object>>> {
		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			List<Map<String, Object>> list = doInBackgroundImpl(params);
			return list;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// 相关课程的课程名
			simpleAdapter = new SimpleAdapter(SearchActivity.this, result,
					R.layout.search_list_item_coursetitle,
					new String[] { "cour_title" },
					new int[] { R.id.search_list_item1 });
			search_list.setAdapter(simpleAdapter);
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
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}
}
