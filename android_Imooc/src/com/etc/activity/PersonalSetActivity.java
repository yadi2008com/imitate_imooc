package com.etc.activity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etc.bean.UsersBean;
import com.etc.dao.SelectUserInfoDao;
import com.example.b.R;

public class PersonalSetActivity extends Activity {
	ImageView Im_avator;
	TextView Tv_occupation1, Tv_autograph1, Tv_nickname1, Tv_sex1, Tv_region1,
			Tv_mail1;
	RelativeLayout Re_avator, Re_occupation, Re_autograph, Re_nickname, Re_sex,
			Re_region, Re_mail;
	private ProgressDialog progressDialog;
	UsersBean usersBean = new UsersBean();
	private String UserBeanList_PATH_JSON = null;
	private String Image_Path = "";
	String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_set);
		// 查找登录的用户ID
		SelectUserInfoDao selectUserInfoDao = new SelectUserInfoDao(
				PersonalSetActivity.this);
		usersBean = selectUserInfoDao.SelectLoginUser();
		UserBeanList_PATH_JSON = "http://119.29.85.27/Imooc/AndroidSetProfileServlet?id="
				+ usersBean.getUser_id();
		// 查找登录的用户ID
		Tv_occupation1 = (TextView) super.findViewById(R.id.Tv_occupation1);
		Tv_autograph1 = (TextView) super.findViewById(R.id.Tv_autograph1);
		Tv_nickname1 = (TextView) super.findViewById(R.id.Tv_nickname1);
		Tv_sex1 = (TextView) super.findViewById(R.id.Tv_sex1);
		Tv_region1 = (TextView) super.findViewById(R.id.Tv_region1);
		Tv_mail1 = (TextView) super.findViewById(R.id.Tv_mail1);
		Im_avator = (ImageView) super.findViewById(R.id.Im_avator);

		Re_avator = (RelativeLayout) super.findViewById(R.id.Re_avator);
		Re_occupation = (RelativeLayout) super.findViewById(R.id.Re_occupation);
		Re_autograph = (RelativeLayout) super.findViewById(R.id.Re_autograph);
		Re_nickname = (RelativeLayout) super.findViewById(R.id.Re_nickname);
		Re_sex = (RelativeLayout) super.findViewById(R.id.Re_sex);
		Re_region = (RelativeLayout) super.findViewById(R.id.Re_region);
		Re_mail = (RelativeLayout) super.findViewById(R.id.Re_mail);

		Re_occupation.setOnClickListener(new OccupationOnClickListener());
		Re_autograph.setOnClickListener(new autographOnClickListener());
		Re_nickname.setOnClickListener(new nicknameOnClickListener());
		Re_sex.setOnClickListener(new sexOnClickListener());
		Re_region.setOnClickListener(new regionOnClickListener());

		progressDialog = new ProgressDialog(PersonalSetActivity.this);
		progressDialog.setTitle("个人资料");
		progressDialog.setMessage("看啥   更新呢...");
		progressDialog.setCancelable(true);
		progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);

		new MyAsynTask1().execute(UserBeanList_PATH_JSON);
		// Intent intent=getIntent();
		// if(!(intent.getStringExtra("result")).equals("")&&(intent.getStringExtra("result"))!=null){
		// result=intent.getStringExtra("result");
		// Toast.makeText(PersonalSetActivity.this, result, Toast.LENGTH_SHORT)
		// .show();
		// }
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new MyAsynTask1().execute(UserBeanList_PATH_JSON);

	}

	public class MyAsynTask1 extends AsyncTask<String, Void, JSONObject> {
		Bitmap bm;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog.show();
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
				Image_Path = "http://119.29.85.27/Imooc/AvatorImages/"
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
				Tv_mail1.setText(result.getString("user_email"));
				Tv_nickname1.setText(result.getString("username"));
				Tv_autograph1.setText(result.getString("user_sign"));
				Tv_occupation1.setText(result.getString("user_job"));
				Tv_region1.setText(result.getString("user_city"));
				Tv_sex1.setText(result.getString("user_sex"));
				Im_avator.setImageBitmap(bm);
				progressDialog.dismiss();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public class OccupationOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(PersonalSetActivity.this,
					OccupationSetActivity.class);
			startActivity(intent);
		}

	}

	public class autographOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(PersonalSetActivity.this,
					AutographSetActivity.class);
			startActivity(intent);
		}

	}

	public class nicknameOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(PersonalSetActivity.this,
					NicknameSetActivity.class);
			startActivity(intent);
		}

	}

	public class sexOnClickListener implements OnClickListener {
		String SelectString;
		final String[] arraysex = new String[] { "男", "女" };
		int SelectedSexIndex;

		public class MyAsynTask2 extends AsyncTask<String, Void, String> {

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				onResume();
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();

			}

			@Override
			protected String doInBackground(String... arg0) {
				// TODO Auto-generated method stub
				UpdateSex(SelectString);
				return null;
			}

		}

		public String UpdateSex(String SelectString) {

			try {
				HttpPost httpRequest = new HttpPost(
						"http://119.29.85.27/Imooc/AndroidSetSexServlet?id=" + usersBean.getUser_id());
				HttpClient httpClient = new DefaultHttpClient();
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("SelectString", SelectString
						.toString()));
				httpRequest.setEntity(new UrlEncodedFormEntity(params,
						HTTP.UTF_8));
				HttpResponse httpResponse = httpClient.execute(httpRequest);

				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					// 利用字节数组流和包装的绑定数据
					byte[] data = new byte[2048];
					// 先把从服务端来的数据转化成字节数组
					data = EntityUtils.toByteArray((HttpEntity) httpResponse
							.getEntity());
					// 再创建字节数组输入流对象
					ByteArrayInputStream bais = new ByteArrayInputStream(data);
					// 绑定字节流和数据包装流
					DataInputStream dis = new DataInputStream(bais);
					// 将字节数组中的数据还原成原来的各种数据类型，代码如下：
					result = new String(dis.readUTF());
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return result;

		}

		Dialog alertDialog = new AlertDialog.Builder(PersonalSetActivity.this)
				.setTitle("性别")
				.setSingleChoiceItems(arraysex, 1,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int which) {
								// TODO Auto-generated method stub
								SelectedSexIndex = which;
								System.out.println(SelectedSexIndex);
								switch (SelectedSexIndex) {
								case 0:
									SelectString = "男";
									break;
								case 1:
									SelectString = "女";
									break;
								}
								new MyAsynTask2().execute();
								alertDialog.dismiss();
							}
						}).create();

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			alertDialog.show();
		}

	}

	public class regionOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(PersonalSetActivity.this,
					RegionSetActivity.class);
			startActivity(intent);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal_set, menu);
		return true;
	}

}
