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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import com.example.b.R;

public class MyPlanActivity extends Activity {
	private ListView listView;
	private SimpleAdapter simpleAdapter = null;

	private final String CITY_PATH_JSON = "http://119.29.85.27/Imooc/MyPlanServlet?type=json&userid=4";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_plan);

		listView = (ListView) findViewById(R.id.listView);
		new MyAsyncTask().execute(CITY_PATH_JSON);
	}

	public class MyAsyncTask extends
			AsyncTask<String, Void, List<Map<String, Object>>> {

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			String myPlanString = "";
			HttpClient httpClient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(params[0]);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = httpResponse.getEntity();
					if (httpEntity != null) {
						myPlanString = EntityUtils
								.toString(httpEntity, "utf-8");
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

				JSONObject jsonObject = new JSONObject(myPlanString);
				JSONArray jsonArray = jsonObject.getJSONArray("planBeanList");
				JSONObject jsonObject2 = new JSONObject();
				for (int i = 0; i < jsonArray.length(); i++) {
					jsonObject2 = jsonArray.getJSONObject(i);
					Map<String, Object> map = new HashMap<String, Object>();
					String plan_img = jsonObject2.getString("plan_img");
					map.put("plan_img",
							returnBitMap("http://119.29.85.27/Imooc/PlanImages/"
									+ plan_img));
					map.put("plan_name", jsonObject2.getString("plan_name"));

					list.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// 处理数据，显示到list
			simpleAdapter = new SimpleAdapter(MyPlanActivity.this, result,
					R.layout.activity_my_plan_list, new String[] { "plan_img",
							"plan_name" }, new int[] { R.id.plan_img,
							R.id.plan_name });
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
		}

	}

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
}
