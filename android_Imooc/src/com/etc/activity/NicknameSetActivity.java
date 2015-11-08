package com.etc.activity;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.etc.activity.AutographSetActivity.ImOnClickListener;
import com.etc.activity.AutographSetActivity.MyAsynTask;
import com.etc.activity.AutographSetActivity.btOnClickListener;
import com.example.b.R;
import com.example.b.R.layout;
import com.example.b.R.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NicknameSetActivity extends Activity {
	EditText edittext;
	ImageButton Im_back_title;
	Button bt_nickname_title;
	String nicknameString = "";

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nickname_set);

		edittext = (EditText) super.findViewById(R.id.nickname_Ed_nickname);
		Im_back_title = (ImageButton) super
				.findViewById(R.id.Im_back_title);
		bt_nickname_title = (Button) super.findViewById(R.id.bt_nickname_title);

		Im_back_title.setOnClickListener(new ImOnClickListener());
		bt_nickname_title.setOnClickListener(new btOnClickListener());

		progressDialog = new ProgressDialog(NicknameSetActivity.this);
		progressDialog.setTitle("昵称");
		progressDialog.setMessage("月儿拼命更新中...");
		progressDialog.setCancelable(true);
		progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
	}

	public class MyAsynTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();			
			Intent intent = new Intent(NicknameSetActivity.this,
					PersonalSetActivity.class);
			startActivity(intent);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressDialog.show();

		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			UpdateNickname(nicknameString);
			return null;
		}

	}

	public class ImOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(NicknameSetActivity.this,
					PersonalSetActivity.class);
			startActivity(intent);

		}

	}

	public class btOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			nicknameString = edittext.getText().toString();
			new MyAsynTask().execute();
		}
	}

	public void UpdateNickname(String nicknameString) {
		String result = null;
		try {
			HttpPost httpRequest = new HttpPost(
					"http://119.29.85.27/Imooc/AndroidSetNicknameServlet?id=" + 2);
			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("nicknameString", nicknameString.toString()));
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
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
			Toast.makeText(NicknameSetActivity.this, result, Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nickname_set, menu);
		return true;
	}

}
