package com.etc.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etc.activity.LoginActivity;
import com.etc.activity.MyCourseActivity;
import com.etc.activity.MyNoteActivity;
import com.etc.activity.MyNoteOriginal;
import com.etc.activity.MyPlanActivity;

import com.etc.activity.PersonalSetActivity;
import com.etc.activity.MyNoteActivity.MyPagerAdapter;
import com.etc.activity.PersonalSetActivity.MyAsynTask1;
import com.etc.activity.download.DownloadActivity;
import com.etc.activity.setting.MySettingActivity;
import com.etc.bean.MyCourseBean;
import com.etc.bean.UsersBean;
import com.etc.dao.MyCourseDao;
import com.etc.dao.MyCourseDaoImpl;
import com.etc.dao.SelectUserInfoDao;
import com.example.b.R;

public class LeftMenu {
	private String Image_Path = null;
	private Context context;

	private LinearLayout all_course = null;// 左侧菜单的子项--所有课程
	private LinearLayout my_course = null;// 左侧菜单的子项--我的课程
	private LinearLayout download = null;// 左侧菜单的子项--离线缓存
	private LinearLayout message = null;// 左侧菜单的子项--我的消息
	private LinearLayout my_plan = null;// 左侧菜单的子项--我的计划
	private LinearLayout mynote = null;// 左侧菜单的子项--我的笔记
	private LinearLayout article = null;// 左侧菜单的子项--文章
	private LinearLayout setting = null;// 左侧菜单的子项--设置
	private RelativeLayout user_info = null;// 左侧菜单的子项--用户信息

	private ImageView all_course_img, my_course_img, download_img, message_img,
			mynote_img, article_img, setting_img, my_plan_img;
	private TextView all_course_title, my_course_title, download_title,
			message_title, mynote_title, article_title, setting_title,
			my_plan_title;

	private Intent intent = null;// 左侧菜单项单击后跳转各个Activity
	private DrawerLayout mDrawerLayout = null;// 左侧抽式菜单
	private ImageView user_image = null;
	private TextView username = null;
	private TextView user_job = null;
	private UsersBean usersBean = null;

	public LeftMenu(Context context) {
		this.context = context;
	}

	/**
	 * 实例化左侧菜单的子项
	 */
	public void initLeftMenuItem(View left_drawer) {
		SelectUserInfoDao selectUserInfoDao = new SelectUserInfoDao(context);
		usersBean = selectUserInfoDao.SelectLoginUser();
		String UserBeanList_PATH_JSON = "http://192.168.207.61:8080/Imooc/AndroidSetProfileServlet?id="
				+ usersBean.getUser_id();
		new MyAsynTask1().execute(UserBeanList_PATH_JSON);
		// 实例化左侧菜单的子项RelativeLayout
		all_course = (LinearLayout) left_drawer.findViewById(R.id.all_course);
		my_course = (LinearLayout) left_drawer.findViewById(R.id.my_course);
		download = (LinearLayout) left_drawer.findViewById(R.id.download);
		message = (LinearLayout) left_drawer.findViewById(R.id.message);
		my_plan = (LinearLayout) left_drawer.findViewById(R.id.my_plan);
		mynote = (LinearLayout) left_drawer.findViewById(R.id.mynote);
		article = (LinearLayout) left_drawer.findViewById(R.id.article);
		setting = (LinearLayout) left_drawer.findViewById(R.id.setting);
		user_info = (RelativeLayout) left_drawer.findViewById(R.id.user_info);

		// 实例化左侧菜单的子项ImageView
		all_course_img = (ImageView) left_drawer
				.findViewById(R.id.all_course_img);
		my_course_img = (ImageView) left_drawer
				.findViewById(R.id.my_course_img);
		download_img = (ImageView) left_drawer.findViewById(R.id.download_img);
		message_img = (ImageView) left_drawer.findViewById(R.id.message_img);
		mynote_img = (ImageView) left_drawer.findViewById(R.id.mynote_img);
		my_plan_img = (ImageView) left_drawer.findViewById(R.id.my_plan_img);
		article_img = (ImageView) left_drawer.findViewById(R.id.article_img);
		setting_img = (ImageView) left_drawer.findViewById(R.id.setting_img);
		// 实例化左侧菜单的子项TextView
		all_course_title = (TextView) left_drawer
				.findViewById(R.id.all_course_title);
		my_course_title = (TextView) left_drawer
				.findViewById(R.id.my_course_title);
		download_title = (TextView) left_drawer
				.findViewById(R.id.download_title);
		message_title = (TextView) left_drawer.findViewById(R.id.message_title);
		my_plan_title = (TextView) left_drawer.findViewById(R.id.my_plan_title);
		mynote_title = (TextView) left_drawer.findViewById(R.id.mynote_title);
		article_title = (TextView) left_drawer.findViewById(R.id.article_title);
		setting_title = (TextView) left_drawer.findViewById(R.id.setting_title);
		// 头部登录的用户
		user_image = (ImageView) left_drawer.findViewById(R.id.user_image);
		username = (TextView) left_drawer.findViewById(R.id.username);
		user_job = (TextView) left_drawer.findViewById(R.id.user_job);
	}

	/**
	 * 左侧菜单的单击事件
	 */
	public void leftMenuItemSetOnclickListener() {
		all_course.setOnClickListener(new leftMenuOnclickListenerImpl());
		my_course.setOnClickListener(new leftMenuOnclickListenerImpl());
		download.setOnClickListener(new leftMenuOnclickListenerImpl());
		message.setOnClickListener(new leftMenuOnclickListenerImpl());
		my_plan.setOnClickListener(new leftMenuOnclickListenerImpl());
		mynote.setOnClickListener(new leftMenuOnclickListenerImpl());
		article.setOnClickListener(new leftMenuOnclickListenerImpl());
		setting.setOnClickListener(new leftMenuOnclickListenerImpl());
		user_info.setOnClickListener(new leftMenuOnclickListenerImpl());
	}

	/**
	 * 
	 * @author 郝宝亮
	 * @功能 左侧菜单的单击事件的具体实现
	 */
	public class leftMenuOnclickListenerImpl implements OnClickListener {
		@Override
		public void onClick(View view) {
			leftMenuNormal();
			switch (view.getId()) {
			case R.id.all_course:
				all_course.setBackgroundColor(context.getResources().getColor(
						R.color.leftMenu_Item_selected_grey));
				all_course_img
						.setImageResource(R.drawable.sliding_allcourse_icon_pressed);
				all_course_title.setTextColor(context.getResources().getColor(
						R.color.leftMenu_ItemText_selected_Red));
				break;
			case R.id.my_course:
				my_course.setBackgroundColor(context.getResources().getColor(
						R.color.leftMenu_Item_selected_grey));
				my_course_img
						.setImageResource(R.drawable.sliding_mycourse_icon_pressed);
				my_course_title.setTextColor(context.getResources().getColor(
						R.color.leftMenu_ItemText_selected_Red));
				intent = new Intent(context, MyCourseActivity.class);
				context.startActivity(intent);
				break;
			case R.id.download:
				download.setBackgroundColor(context.getResources().getColor(
						R.color.leftMenu_Item_selected_grey));
				download_img
						.setImageResource(R.drawable.sliding_download_icon_pressed);
				download_title.setTextColor(context.getResources().getColor(
						R.color.leftMenu_ItemText_selected_Red));

				intent = new Intent(context, DownloadActivity.class);
				context.startActivity(intent);

				break;
			case R.id.message:
				message.setBackgroundColor(context.getResources().getColor(
						R.color.leftMenu_Item_selected_grey));
				message_img
						.setImageResource(R.drawable.sliding_message_icon_pressed);
				message_title.setTextColor(context.getResources().getColor(
						R.color.leftMenu_ItemText_selected_Red));
				break;
			case R.id.my_plan:
				my_plan.setBackgroundColor(context.getResources().getColor(
						R.color.leftMenu_Item_selected_grey));
				my_plan_img
						.setImageResource(R.drawable.sliding_mycourse_icon_pressed);
				my_plan_title.setTextColor(context.getResources().getColor(
						R.color.leftMenu_ItemText_selected_Red));

				intent = new Intent(context, MyPlanActivity.class);
				context.startActivity(intent);

				break;
			case R.id.mynote:
				mynote.setBackgroundColor(context.getResources().getColor(
						R.color.leftMenu_Item_selected_grey));
				mynote_img
						.setImageResource(R.drawable.sliding_mynote_icon_pressed);
				mynote_title.setTextColor(context.getResources().getColor(
						R.color.leftMenu_ItemText_selected_Red));

				intent = new Intent(context, MyNoteActivity.class);
				context.startActivity(intent);

				break;
			case R.id.article:
				article.setBackgroundColor(context.getResources().getColor(
						R.color.leftMenu_Item_selected_grey));
				article_img
						.setImageResource(R.drawable.sliding_article_icon_pressed);
				article_title.setTextColor(context.getResources().getColor(
						R.color.leftMenu_ItemText_selected_Red));
				break;
			case R.id.setting:
				setting.setBackgroundColor(context.getResources().getColor(
						R.color.leftMenu_Item_selected_grey));
				setting_img
						.setImageResource(R.drawable.sliding_setting_icon_pressed);
				setting_title.setTextColor(context.getResources().getColor(
						R.color.leftMenu_ItemText_selected_Red));
				intent = new Intent(context, MySettingActivity.class);
				context.startActivity(intent);
				break;
			case R.id.user_info:
				if (0 == usersBean.getUser_id()) {
					intent = new Intent(context, LoginActivity.class);
					context.startActivity(intent);
				} else {
					intent = new Intent(context, PersonalSetActivity.class);
					context.startActivity(intent);
				}
				break;
			default:
				break;
			}
		}

		/**
		 * 初始化左侧菜单子项的样式
		 */
		public void leftMenuNormal() {
			// 初始化左侧菜单子项的样式--Layout
			all_course.setBackgroundColor(context.getResources().getColor(
					R.color.leftMenu_Item_selected_white));
			my_course.setBackgroundColor(context.getResources().getColor(
					R.color.leftMenu_Item_selected_white));
			download.setBackgroundColor(context.getResources().getColor(
					R.color.leftMenu_Item_selected_white));
			message.setBackgroundColor(context.getResources().getColor(
					R.color.leftMenu_Item_selected_white));
			my_plan.setBackgroundColor(context.getResources().getColor(
					R.color.leftMenu_Item_selected_white));
			mynote.setBackgroundColor(context.getResources().getColor(
					R.color.leftMenu_Item_selected_white));
			article.setBackgroundColor(context.getResources().getColor(
					R.color.leftMenu_Item_selected_white));
			setting.setBackgroundColor(context.getResources().getColor(
					R.color.leftMenu_Item_selected_white));
			// 初始化左侧菜单子项的样式--ImageView
			all_course_img
					.setImageResource(R.drawable.sliding_allcourse_icon_normal);
			my_course_img
					.setImageResource(R.drawable.sliding_mycourse_icon_normal);
			download_img
					.setImageResource(R.drawable.sliding_download_icon_normal);
			message_img
					.setImageResource(R.drawable.sliding_message_icon_normal);
			my_plan_img
					.setImageResource(R.drawable.sliding_mycourse_icon_normal);
			mynote_img.setImageResource(R.drawable.sliding_mynote_icon_normal);
			article_img
					.setImageResource(R.drawable.sliding_article_icon_normal);
			setting_img
					.setImageResource(R.drawable.sliding_setting_icon_normal);
			// 初始化左侧菜单子项的样式--TextView
			all_course_title.setTextColor(context.getResources().getColor(
					R.color.leftMenu_ItemText_selected_Nromal));
			my_course_title.setTextColor(context.getResources().getColor(
					R.color.leftMenu_ItemText_selected_Nromal));
			download_title.setTextColor(context.getResources().getColor(
					R.color.leftMenu_ItemText_selected_Nromal));
			message_title.setTextColor(context.getResources().getColor(
					R.color.leftMenu_ItemText_selected_Nromal));
			my_plan_title.setTextColor(context.getResources().getColor(
					R.color.leftMenu_ItemText_selected_Nromal));
			mynote_title.setTextColor(context.getResources().getColor(
					R.color.leftMenu_ItemText_selected_Nromal));
			article_title.setTextColor(context.getResources().getColor(
					R.color.leftMenu_ItemText_selected_Nromal));
			setting_title.setTextColor(context.getResources().getColor(
					R.color.leftMenu_ItemText_selected_Nromal));
		}

	}

	public class MyAsynTask1 extends AsyncTask<String, Void, JSONObject> {
		Bitmap bm;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected JSONObject doInBackground(String... params) {
			// TODO Auto-generated method stub
			String usersBeanString = "";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(params[0]);
			JSONObject usersBeanObject = new JSONObject();

			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = httpResponse.getEntity();
					if (httpEntity != null) {
						usersBeanString = EntityUtils.toString(httpEntity,
								"utf-8");
					}
				}

				JSONObject mapObject = new JSONObject(usersBeanString);
				usersBeanObject = mapObject.getJSONObject("usersBean");
				Image_Path = "http://192.168.207.61:8080/Imooc/AvatorImages/"
						+ usersBeanObject.getString("user_image");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
					.penaltyLog().penaltyDeath().build());

			try {
				byte data[] = this.getUrlData();
				bm = BitmapFactory.decodeByteArray(data, 0, data.length);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			finally {
				httpClient.getConnectionManager().shutdown();

			}
			return usersBeanObject;

		}

		private byte[] getUrlData() throws Exception {
			// TODO Auto-generated method stub
			ByteArrayOutputStream bos = null;
			try {
				URL url = new URL(Image_Path);
				bos = new ByteArrayOutputStream();
				byte data[] = new byte[4096];
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				InputStream input = conn.getInputStream();
				int len = 0;
				while ((len = input.read(data)) != -1) {
					bos.write(data, 0, len);
				}
				bos.close();
				return bos.toByteArray();
			} catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				if (0 != usersBean.getUser_id()) {
					username.setText(result.getString("username"));
					user_job.setText(result.getString("user_job"));
					user_image.setImageBitmap(bm);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
