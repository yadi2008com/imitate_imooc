package com.etc.activity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.b.R;

public class RegisterActivity extends Activity {

	private EditText username, r_email, r_password;
	private Button bt_register;
	private ImageView back;

	private String getUsername, getEmail, getPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		username = (EditText) findViewById(R.id.username);
		r_email = (EditText) findViewById(R.id.r_email);
		r_password = (EditText) findViewById(R.id.r_password);
		bt_register = (Button) findViewById(R.id.bt_register);
		back = (ImageView) findViewById(R.id.back);

		bt_register.setOnClickListener(new OnClickListenerImpl());
		back.setOnClickListener(new BackOnClickListenerImpl());
	}
	
	private class BackOnClickListenerImpl implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
			startActivity(intent);		
		}
		
	}

	public class MyAsyncTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			String result = "";
			String s = "";
			HttpClient httpClient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(params[0]);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity httpEntity = httpResponse.getEntity();
					if (httpEntity != null) {
						s = EntityUtils.toString(httpEntity, "utf-8");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				httpClient.getConnectionManager().shutdown();
			}

			try {
				JSONObject jsonObject = new JSONObject(s);
				result = jsonObject.getString("result");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if ("1".equals(result)) {
				Toast.makeText(RegisterActivity.this, "该昵称已被注册",
						Toast.LENGTH_SHORT).show();
				username.setFocusable(true);
				username.setFocusableInTouchMode(true);
				username.requestFocus();
			} else if ("2".equals(result)) {
				Toast.makeText(RegisterActivity.this, "该邮箱已被注册",
						Toast.LENGTH_SHORT).show();
				r_email.setFocusable(true);
				r_email.setFocusableInTouchMode(true);
				r_email.requestFocus();
			} else if ("3".equals(result)) {
				Toast.makeText(RegisterActivity.this, "注册成功",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(RegisterActivity.this, "注册失败",
						Toast.LENGTH_SHORT).show();
			}			
		}

	}

	private class OnClickListenerImpl implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			getUsername = username.getText().toString();
			getEmail = r_email.getText().toString();
			getPassword = r_password.getText().toString();

			if ("".equals(getUsername) || getUsername == null) {
				Toast.makeText(RegisterActivity.this, "昵称不能为空",
						Toast.LENGTH_SHORT).show();
				username.setFocusable(true);
				username.setFocusableInTouchMode(true);
				username.requestFocus();
			} else if ("".equals(getEmail) || getEmail == null) {
				Toast.makeText(RegisterActivity.this, "邮箱不能为空",
						Toast.LENGTH_SHORT).show();
				r_email.setFocusable(true);
				r_email.setFocusableInTouchMode(true);
				r_email.requestFocus();
			} else if ("".equals(getPassword) || getPassword == null) {
				Toast.makeText(RegisterActivity.this, "密码不能为空",
						Toast.LENGTH_SHORT).show();
				r_password.setFocusable(true);
				r_password.setFocusableInTouchMode(true);
				r_password.requestFocus();
			} else {
				boolean flag1 = false;
				boolean flag2 = false;
				boolean flag3 = false;

				try {
					String check = "^[a-zA-Z0-9_]{3,16}$";
					Pattern regex = Pattern.compile(check);
					Matcher matcher = regex.matcher(getUsername);
					flag1 = matcher.matches();
				} catch (Exception e) {
					flag1 = false;
				}

				try {
					String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
					Pattern regex = Pattern.compile(check);
					Matcher matcher = regex.matcher(getEmail);
					flag2 = matcher.matches();
				} catch (Exception e) {
					flag2 = false;
				}

				try {
					String check = "^\\s*[^\\s\u4e00-\u9fa5]{6,16}\\s*$";
					Pattern regex = Pattern.compile(check);
					Matcher matcher = regex.matcher(getPassword);
					flag3 = matcher.matches();
				} catch (Exception e) {
					flag3 = false;
				}

				if (flag1 == false) {
					Toast.makeText(RegisterActivity.this,
							"昵称格式不正确，昵称为3-16位，中英文、数字及下划线", Toast.LENGTH_SHORT)
							.show();
					username.setFocusable(true);
					username.setFocusableInTouchMode(true);
					username.requestFocus();
				} else if (flag2 == false) {
					Toast.makeText(RegisterActivity.this, "邮箱格式不正确",
							Toast.LENGTH_SHORT).show();
					r_email.setFocusable(true);
					r_email.setFocusableInTouchMode(true);
					r_email.requestFocus();
				} else if (flag3 == false) {
					Toast.makeText(RegisterActivity.this,
							"密码格式不正确，6-16位密码，任意字符，不能用空格", Toast.LENGTH_SHORT)
							.show();
					r_password.setFocusable(true);
					r_password.setFocusableInTouchMode(true);
					r_password.requestFocus();
				} else {
					String path_json = "http://119.29.85.27/Imooc/UserRegisterServlet?type=json&getUsername="
							+ getUsername
							+ "&getEmail="
							+ getEmail
							+ "&getPassword=" + getPassword;
					new MyAsyncTask().execute(path_json);
				}
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.register, menu);
		return super.onCreateOptionsMenu(menu);
	}

}
