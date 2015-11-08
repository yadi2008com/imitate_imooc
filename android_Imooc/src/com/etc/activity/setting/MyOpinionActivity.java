package com.etc.activity.setting;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.etc.bean.MyOpinionBean;
import com.etc.dao.MyOpinionDao;
import com.etc.dao.MyOpinionDaoImpl;
import com.etc.util.MyOpinionSQLiteOpenHelper;
import com.etc.util.NetUtils;
import com.etc.util.OtherOpinionInfoUtils;
import com.example.b.R;

public class MyOpinionActivity extends Activity {
	private RelativeLayout rl_checked, rl_my, rl_other;
	private TextView tv_check_opinion, tv_my_opinion, tv_other_opinion;
	private View v_a, v_b, v_c;
	private EditText et_address, et_opinion;
	private Button bt_checked_opinion;
	private ListView lv_other_opinion, lv_my_opinion; // 数据列表显示条
	private List<MyOpinionBean> other_opinion_data = new ArrayList<MyOpinionBean>();// 显示的数据
	private SimpleAdapter other_opinion_adapter, my_opinion_adapter;// 适配器
	private View footer;// 页脚
	private int maxPage = 5;// 总共显示5页数据
	private int pageno = 1;// 当前的页数
	private static boolean loadfinish = true;// 加载是否完成标志位
	private List<Map<String, String>> otherOpinionMaps = new ArrayList<Map<String, String>>();
	private String TAG = "ListViewActivity";// 日志信息的TAG

	private final int msgFlag = 0x1234;// 信息的标识位

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_opinion);
		rl_checked = (RelativeLayout) findViewById(R.id.rl_checkd);
		rl_my = (RelativeLayout) findViewById(R.id.rl_my);
		rl_other = (RelativeLayout) findViewById(R.id.rl_other);
		v_a = (View) findViewById(R.id.v_a);
		v_b = (View) findViewById(R.id.v_b);
		v_c = (View) findViewById(R.id.v_c);
		bt_checked_opinion = (Button) findViewById(R.id.bt_checked_opinion);
		et_address = (EditText) findViewById(R.id.et_address);
		et_opinion = (EditText) findViewById(R.id.et_opinion);
		lv_other_opinion = (ListView) findViewById(R.id.lv_other_opinion);
		lv_my_opinion = (ListView) findViewById(R.id.lv_my_opinion);
		tv_check_opinion = (TextView) findViewById(R.id.tv_check_opinion);
		tv_my_opinion = (TextView) findViewById(R.id.tv_my_opinion);
		tv_other_opinion = (TextView) findViewById(R.id.tv_other_opinion);

		et_address.setOnFocusChangeListener(new EditTextOnFocusListener());
		et_opinion.setOnFocusChangeListener(new EditTextOnFocusListener());
		tv_my_opinion.setOnClickListener(new MyopinionOnClickListenerImpl());
		tv_check_opinion
				.setOnClickListener(new CheckedopinionOnClickListenerImpl());
		tv_other_opinion
				.setOnClickListener(new OtheropinionOnClickListenerImpl());
		bt_checked_opinion.setOnClickListener(new BtCheckOnclickListenerImpl());
		// 获得footer页面
		footer = getLayoutInflater().inflate(R.layout.footer, null);
	}

	// 异步操作从 服务器 获取信息列表
	class GetOtherOpinionInfoAsyncTask extends

	AsyncTask<Object, Void, String> {
		String urlPath = "http://119.29.85.27/Imooc/OtherOpinionGetServlet";
		URL url;

		@Override
		protected String doInBackground(Object... object) {
			String result = "";
			try {
				url = new URL(urlPath);
				String content = JSON.toJSONString(object);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				// 设置User-Agent: Fiddler
				conn.setRequestProperty("ser-Agent", "Fiddler");
				// 设置contentType
				conn.setRequestProperty("Content-Type", "application/json");

				OutputStream os = conn.getOutputStream();

				os.write(content.getBytes());
				OutputStreamWriter writer = new OutputStreamWriter(os);
				os.flush();
				os.close();
				// 服务器返回的响应码
				int code = conn.getResponseCode();
				if (code == 200) {
					// 等于200了,下面呢我们就可以获取服务器的数据了
					// 在这里我们已经连接上了，也获得了服务器的数据了，
					// 那么我们接着就是解析服务器传递过来的数据，
					// 现在我们开始解析服务器传递过来的参数,
					InputStream is = conn.getInputStream();
					String resultget = NetUtils.readString(is);
					if (resultget.toString().equals("true")) {
						result = "提交成功";
					} else {
						result = "提交失败了";
					}
				} else {
					result = "提交失败";
				}
			} catch (Exception e) {
				e.printStackTrace();

			}

			return result;
		}
	}

	// 提交反馈信息给服务器
	class BtCheckOnclickListenerImpl implements OnClickListener {

		public void onClick(View view) {
			if (et_opinion.getText().toString().trim().equals("")
					|| et_opinion.getText().toString().equals("骚年，哪有不爽，来喷吧！")) {
				return;
			} else {
				String opin_content = et_opinion.getText().toString();
				String opin_useraddress = et_address.getText().toString();
				if (opin_useraddress.equals("请留下QQ、邮箱或手机号、方便答疑解惑")) {
					opin_useraddress = "";
				}
				try {
					MyOpinionBean myOpinionBean = new MyOpinionBean();
					myOpinionBean.setOpin_content(opin_content);
					myOpinionBean.setOpin_useraddress(opin_useraddress);
					MyOpinionCheckedAsyncTask asyncTask = new MyOpinionCheckedAsyncTask();
					asyncTask.execute(myOpinionBean);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

	}

	// 用来异步提交数据给服务器
	class MyOpinionCheckedAsyncTask extends

	AsyncTask<Object, Void, String> {
		String urlPath = "http://119.29.85.27/Imooc/OpinionAddServlet";
		URL url;

		@Override
		protected String doInBackground(Object... object) {
			String result = "";
			try {
				url = new URL(urlPath);
				String content = JSON.toJSONString(object);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(5000);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				// 设置User-Agent: Fiddler
				conn.setRequestProperty("ser-Agent", "Fiddler");
				// 设置contentType
				conn.setRequestProperty("Content-Type", "application/json");

				OutputStream os = conn.getOutputStream();

				os.write(content.getBytes());
				OutputStreamWriter writer = new OutputStreamWriter(os);
				os.flush();
				os.close();
				// 服务器返回的响应码
				int code = conn.getResponseCode();
				if (code == 200) {
					// 等于200了,下面呢我们就可以获取服务器的数据了
					// 在这里我们已经连接上了，也获得了服务器的数据了，
					// 那么我们接着就是解析服务器传递过来的数据，
					// 现在我们开始解析服务器传递过来的参数,
					InputStream is = conn.getInputStream();
					String resultget = NetUtils.readString(is);
					if (resultget.toString().equals("true")) {
						result = "提交成功";
						MyOpinionActivity.this.saveMyOpinionToSQLite(object[0]);
					} else {
						result = "提交失败了";
					}
				} else {
					result = "提交失败";
				}
			} catch (Exception e) {
				e.printStackTrace();

			}

			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Toast.makeText(MyOpinionActivity.this, result, Toast.LENGTH_LONG)
					.show();

		}

	}

	// 当提交我的反馈给服务器成功后，把我的反馈保存到SQLite中
	public void saveMyOpinionToSQLite(Object object) {
		MyOpinionBean myOpinionBean = (MyOpinionBean) object;
		MyOpinionDao myOpinionDao = new MyOpinionDaoImpl(MyOpinionActivity.this);
		long result = myOpinionDao.insertMyOpinion(myOpinionBean);
		System.out.println(result);
	}

	// 得到焦点时清空输入框中的文字
	class EditTextOnFocusListener implements OnFocusChangeListener {

		@Override
		public void onFocusChange(View view, boolean hasFocus) {
			EditText editText = (EditText) view;
			if (hasFocus) {
				if (("骚年，哪有不爽，来喷吧！").equals(editText.getText().toString())
						|| ("请留下QQ、邮箱或手机号、方便答疑解惑").equals(editText.getText()
								.toString())) {
					editText.setText("");
					editText.setTextColor(Color.parseColor("#000000"));
				}
			} else {
				if (("").equals(editText.getText().toString())) {
					if (editText.getId() == R.id.et_address) {
						editText.setText("请留下QQ、邮箱或手机号、方便答疑解惑");
						editText.setTextColor(Color.parseColor("#999999"));
					} else {
						editText.setText("骚年，哪有不爽，来喷吧！");
						editText.setTextColor(Color.parseColor("#999999"));
					}
				}
			}
		}

	}

	class MyopinionOnClickListenerImpl implements
			android.view.View.OnClickListener {

		@Override
		public void onClick(View arg0) {

			tv_check_opinion.setTextColor(Color.parseColor("#555555"));
			tv_my_opinion.setTextColor(Color.parseColor("#cc0000"));
			tv_other_opinion.setTextColor(Color.parseColor("#555555"));
			v_a.setBackgroundColor(Color.parseColor("#555555"));
			v_b.setBackgroundColor(Color.parseColor("#cc0000"));
			v_c.setBackgroundColor(Color.parseColor("#555555"));

			rl_checked.setVisibility(4);
			rl_my.setVisibility(0);
			rl_other.setVisibility(4);
			MyOpinionActivity.this.initMyOpinion();
		}

	}

	class CheckedopinionOnClickListenerImpl implements
			android.view.View.OnClickListener {

		@Override
		public void onClick(View arg0) {

			tv_check_opinion.setTextColor(Color.parseColor("#cc0000"));
			tv_my_opinion.setTextColor(Color.parseColor("#555555"));
			tv_other_opinion.setTextColor(Color.parseColor("#555555"));
			v_a.setBackgroundColor(Color.parseColor("#cc0000"));
			v_b.setBackgroundColor(Color.parseColor("#555555"));
			v_c.setBackgroundColor(Color.parseColor("#555555"));

			rl_checked.setVisibility(0);
			rl_my.setVisibility(4);
			rl_other.setVisibility(4);
		}

	}

	class OtheropinionOnClickListenerImpl implements
			android.view.View.OnClickListener {

		@Override
		public void onClick(View arg0) {

			tv_check_opinion.setTextColor(Color.parseColor("#555555"));
			tv_my_opinion.setTextColor(Color.parseColor("#555555"));
			tv_other_opinion.setTextColor(Color.parseColor("#cc0000"));
			v_a.setBackgroundColor(Color.parseColor("#555555"));
			v_b.setBackgroundColor(Color.parseColor("#555555"));
			v_c.setBackgroundColor(Color.parseColor("#cc0000"));

			rl_checked.setVisibility(4);
			rl_my.setVisibility(4);
			rl_other.setVisibility(0);

			MyOpinionActivity.this.initOtherOpinion();
		}

	}

	public void initOtherOpinion() {

		// 添加滑动事件监听器
		lv_other_opinion.setOnScrollListener(new OtherOpinionScrollListener());

		// 开启一条新线程，从服务器获取第一页的数据，从服务器实现分页，当无数据时返回空
		new Thread(new Runnable() {
			public void run() {
				// 先休眠一会
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 动态取得数据

				String OtherOpinionJsonString = OtherOpinionInfoUtils
						.getOtherOpinionJsonStringByPageno(1);
				pageno = 1;
				// 发送消息
				Message msg = new Message();
				msg.what = 1;
				msg.getData().putString("OtherOpinionJsonString",
						OtherOpinionJsonString);
				handler.sendMessage(msg);
			}
		}).start();

	}

	// 当点击我的反馈时，从SQLite来获取数据，实例化我的反馈页面。
	public void initMyOpinion() {
		// 从数据库得到数据
		MyOpinionDao myOpinionDao = new MyOpinionDaoImpl(MyOpinionActivity.this);
		List<MyOpinionBean> myOpinionBeans = myOpinionDao.fetchAllMyOpinion();
		if (myOpinionBeans.isEmpty()) {
			Toast.makeText(MyOpinionActivity.this, "您还没有进行过意见反馈",
					Toast.LENGTH_LONG).show();
			return;
		}
		List<Map<String, String>> maps = new ArrayList<Map<String, String>>();
		// 要往适配器中添加数据，先将list<MyOpinionBean>类型的数据转换为list<map<String>,<String>>类型
		for (MyOpinionBean myOpinionBean : myOpinionBeans) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("opin_date", myOpinionBean.getOpin_date().toLocaleString());
			map.put("opin_content", myOpinionBean.getOpin_content());
			maps.add(map);
		}
		my_opinion_adapter = new SimpleAdapter(MyOpinionActivity.this, maps,
				R.layout.my_opinion_list_item, new String[] { "opin_date",
						"opin_content" }, new int[] { R.id.tv_my_opin_date,
						R.id.tv_my_opin_content });
		lv_my_opinion.setAdapter(my_opinion_adapter);

	}

	// 在主线程中更新界面
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				// 从msg中取得json数据
				String OtherOpinionJsonString = msg.getData().getString(
						"OtherOpinionJsonString");
				// 由于下面的到的评论信息是list<MyOpinionBean>类型的，
				// 所以其不能直接添加到simpleAdapter适配器中
				// 即，要将其装换为list<map<String,String>>的格式
				try {
					// 当没有数据时，提示，当前还有用户进行评论
					other_opinion_data = JSON.parseArray(
							OtherOpinionJsonString, MyOpinionBean.class);
				} catch (Exception e) {
					return;
				}

				// 格式转换
				otherOpinionMaps = new ArrayList<Map<String, String>>();
				for (MyOpinionBean myOpinionBean : other_opinion_data) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("tv_opin_date", myOpinionBean.getOpin_date()
							.toLocaleString());
					map.put("tv_opin_content", myOpinionBean.getOpin_content());
					map.put("tv_opin_replycontent",
							myOpinionBean.getOpin_replycontent());
					otherOpinionMaps.add(map);
				}

				other_opinion_adapter = new SimpleAdapter(
						MyOpinionActivity.this, otherOpinionMaps,
						R.layout.opinion_list_item, new String[] {
								"tv_opin_date", "tv_opin_content",
								"tv_opin_replycontent" }, new int[] {
								R.id.tv_opin_date, R.id.tv_opin_content,
								R.id.tv_opin_replycontent });

				// 在数据加载之前 显示一个提示的footer
				lv_other_opinion.addFooterView(footer);
				// 把适配器与listView绑定
				lv_other_opinion.setAdapter(other_opinion_adapter);
				// 显示数据完成后 取消footer提示
				lv_other_opinion.removeFooterView(footer);
				// 把加载完成标志位设为true
				loadfinish = true;
				break;
			case 2:
				// 从msg中取得json数据
				OtherOpinionJsonString = msg.getData().getString(
						"OtherOpinionJsonString");

				// 判断是否有数据，没有数据时，返回false，则不更新listView
				if (!OtherOpinionJsonString.toString().equals("false")) {
					other_opinion_data = JSON.parseArray(
							OtherOpinionJsonString, MyOpinionBean.class);

					// 格式转换
					for (MyOpinionBean myOpinionBean : other_opinion_data) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("tv_opin_date", myOpinionBean.getOpin_date()
								.toLocaleString());
						map.put("tv_opin_content",
								myOpinionBean.getOpin_content());
						map.put("tv_opin_replycontent",
								myOpinionBean.getOpin_replycontent());
						otherOpinionMaps.add(map);
					}
					// other_opinion_adapter.notifyDataSetChanged();//
					// 如果存在 页脚 则去掉页脚
					if (lv_other_opinion.getFooterViewsCount() > 0) {
						lv_other_opinion.removeFooterView(footer);
					}
					// 把加载完成标志位设为true
					loadfinish = true;

				} else {
					if (lv_other_opinion.getFooterViewsCount() > 0) {
						lv_other_opinion.removeFooterView(footer);
					}
					
					// 把加载完成标志位设为true
					loadfinish = true;
				}
				break;

			}
		}
	};

	class OtherOpinionScrollListener implements OnScrollListener {

		@Override
		public synchronized void onScroll(AbsListView view,
				int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			int lastItemid = lv_other_opinion.getLastVisiblePosition();// 获取当前屏幕最后的Item的Id
			if ((lastItemid + 1) == totalItemCount) {// 达到数据的最后一条记录
				if (totalItemCount > 0) {
					if (loadfinish) {
						loadfinish = false;// 进来后 马上改变状态
						lv_other_opinion.addFooterView(footer);// 添加页脚
						// 开启一条新线程，从服务器获取第一页的数据，从服务器实现分页，当无数据时返回空
						new Thread(new Runnable() {
							public void run() {
								// 先休眠一会
								try {
									Thread.sleep(3000);
								} catch (Exception e) {
									e.printStackTrace();
								}
								// 动态取得数据

								String OtherOpinionJsonString = OtherOpinionInfoUtils
										.getOtherOpinionJsonStringByPageno(++pageno);

								// 发送消息
								Message msg = new Message();
								msg.what = 2;
								msg.getData().putString(
										"OtherOpinionJsonString",
										OtherOpinionJsonString);
								handler.sendMessage(msg);
							}
						}).start();
					}
				}
			}

		}

		@Override
		public void onScrollStateChanged(AbsListView arg0, int arg1) {

		}

	}

}
