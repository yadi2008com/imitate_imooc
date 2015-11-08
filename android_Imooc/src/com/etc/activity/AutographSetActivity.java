package com.etc.activity;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AutographSetActivity extends Activity {

	EditText edittext;
	ImageButton Im_autograph_title;
	Button bt_autograph_title;
	String autographString = "";
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autograph_set);

		edittext = (EditText) super.findViewById(R.id.autograph_Ed_autograph);
		Im_autograph_title = (ImageButton) super
				.findViewById(R.id.Im_autograph_title);
		bt_autograph_title = (Button) super
				.findViewById(R.id.bt_autograph_title);

		Im_autograph_title.setOnClickListener(new ImOnClickListener());
		bt_autograph_title.setOnClickListener(new btOnClickListener());
		progressDialog = new ProgressDialog(AutographSetActivity.this);
		progressDialog.setTitle("签名");
		progressDialog.setMessage("月儿拼命更新中...");
		progressDialog.setCancelable(true);
		progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
	}

	public class ImOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(AutographSetActivity.this,
					PersonalSetActivity.class);
			startActivity(intent);

		}

	}

	public class btOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			autographString = edittext.getText().toString();
			new MyAsynTask().execute();
		}
	}

	public void Updateautograph(String autographString) {
		String result = null;
		try {
			HttpPost httpRequest = new HttpPost(
					"http://119.29.85.27/Imooc/AndroidSetAutographServlet?id=" + 2);
			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("autographString",
					autographString.toString()));
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
			Toast.makeText(AutographSetActivity.this, result,
					Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public class MyAsynTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			Intent intent = new Intent(AutographSetActivity.this,
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
			Updateautograph(autographString);
			return null;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.autograph_set, menu);
		return true;
	}

}
