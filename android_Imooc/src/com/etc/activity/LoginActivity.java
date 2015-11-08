package com.etc.activity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etc.bean.UsersBean;
import com.etc.util.LoginUserDBHelper;
import com.example.b.R;

public class LoginActivity extends Activity {

	private EditText email, password;
	private Button bt_login;
	private ImageButton clear;
	private TextView tw_register;
	private ImageView close;
	
	private String getEmail = null;
	private String getPassword = null;
	private static final String TABLENAME = "userinfo";
	private SQLiteDatabase db = null;
	LoginUserDBHelper dbHelper = new LoginUserDBHelper(LoginActivity.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);
		bt_login = (Button) findViewById(R.id.bt_login);
		clear = (ImageButton) findViewById(R.id.clear);
		tw_register = (TextView) findViewById(R.id.tw_register);
		close = (ImageView) findViewById(R.id.close);

		bt_login.setOnClickListener(new OnClickListenerImpl());
		clear.setOnClickListener(new ClearOnClickListenerImpl());
		tw_register.setOnClickListener(new registerOnClickListenerImpl());
		close.setOnClickListener(new CloseOnClickListenerImpl());

	}
	
	private class CloseOnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
		}
		
	}
	
	private class registerOnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(intent);
		}
		
	}

	private class ClearOnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			email.setText("");
		}

	}

	public class MyAsyncTask extends AsyncTask<String, Void, UsersBean> {

		@Override
		protected UsersBean doInBackground(String... params) {
			String loginInfo = "";
			HttpClient httpClient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(params[0]);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = httpResponse.getEntity();
					if (httpEntity != null) {
						loginInfo = EntityUtils.toString(httpEntity, "utf-8");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}

			UsersBean usersBean = new UsersBean();
			try {
				JSONObject jsonObject = new JSONObject(loginInfo);
				usersBean.setUser_id(jsonObject.getInt("user_id"));
				usersBean.setUser_email(jsonObject.getString("user_email"));
				usersBean.setUsername(jsonObject.getString("username"));
				usersBean.setUserpwd(jsonObject.getString("userpwd"));
				usersBean.setUser_job(jsonObject.getString("user_job"));
				usersBean.setUser_city(jsonObject.getString("user_city"));
				usersBean.setUser_sex(jsonObject.getString("user_sex"));
				usersBean.setUser_sign(jsonObject.getString("user_sign"));
				usersBean.setUser_image(jsonObject.getString("user_image"));

			} catch (Exception e) {
				e.printStackTrace();
			}
			return usersBean;
		}

		@Override
		protected void onPostExecute(UsersBean result) {
			if (LoginActivity.this.getEmail.equals(result.getUser_email())
					&& LoginActivity.this.getPassword.equals(result
							.getUserpwd())) {
				Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT)
						.show();

				ContentValues cv = new ContentValues();
				db = dbHelper.getWritableDatabase();
				cv.put("user_id", result.getUser_id());
				cv.put("user_email", result.getUser_email());
				cv.put("username", result.getUsername());
				cv.put("userpwd", result.getUserpwd());
				cv.put("user_job", result.getUser_job());
				cv.put("user_city", result.getUser_city());
				cv.put("user_sex", result.getUser_sex());
				cv.put("user_sign", result.getUser_sign());
				cv.put("user_image", result.getUser_image());

				LoginActivity.this.db.insert(TABLENAME, null, cv);
				LoginActivity.this.db.close();
				
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(LoginActivity.this, "邮箱或密码不正确",
						Toast.LENGTH_SHORT).show();
			}

		}
	}

	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View arg0) {

			LoginActivity.this.getEmail = email.getText().toString();
			LoginActivity.this.getPassword = password.getText().toString();
			if ("".equals(getEmail) || getEmail == null) {
				Toast.makeText(LoginActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT)
						.show();
				email.setFocusable(true);
				email.setFocusableInTouchMode(true);
				email.requestFocus();
			} else if ("".equals(getPassword) || getPassword == null) {
				Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT)
						.show();
				password.setFocusable(true);
				password.setFocusableInTouchMode(true);
				password.requestFocus();
			} else {

				String path_json = "http://119.29.85.27/Imooc/UserLoginServlet?type=json&getEmail="
						+ getEmail + "&getPassword=" + getPassword;
				new MyAsyncTask().execute(path_json);
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
