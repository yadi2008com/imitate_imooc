package com.etc.activity;

import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etc.activity.NicknameSetActivity.ImOnClickListener;
import com.etc.activity.NicknameSetActivity.MyAsynTask;
import com.example.b.R;

public class OccupationSetActivity extends Activity {

	TextView Tv_Occu1, Tv_Occu2, Tv_Occu3, Tv_Occu4, Tv_Occu5, Tv_Occu6,
			Tv_Occu7;
	RelativeLayout Re_Occu1, Re_Occu2, Re_Occu3, Re_Occu4, Re_Occu5, Re_Occu6,
			Re_Occu7;
	int Occupation;
	ImageButton Im_back_title;
	
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_occupation_set);
		
		Im_back_title = (ImageButton) super
				.findViewById(R.id.Im_back_title);
		
		Im_back_title.setOnClickListener(new ImOnClickListener());

		Tv_Occu1 = (TextView) super.findViewById(R.id.Tv_Occu1);
		Tv_Occu2 = (TextView) super.findViewById(R.id.Tv_Occu2);
		Tv_Occu3 = (TextView) super.findViewById(R.id.Tv_Occu3);
		Tv_Occu4 = (TextView) super.findViewById(R.id.Tv_Occu4);
		Tv_Occu5 = (TextView) super.findViewById(R.id.Tv_Occu5);
		Tv_Occu6 = (TextView) super.findViewById(R.id.Tv_Occu6);
		Tv_Occu7 = (TextView) super.findViewById(R.id.Tv_Occu7);

		Re_Occu1 = (RelativeLayout) super.findViewById(R.id.Re_Occu1);
		Re_Occu2 = (RelativeLayout) super.findViewById(R.id.Re_Occu2);
		Re_Occu3 = (RelativeLayout) super.findViewById(R.id.Re_Occu3);
		Re_Occu4 = (RelativeLayout) super.findViewById(R.id.Re_Occu4);
		Re_Occu5 = (RelativeLayout) super.findViewById(R.id.Re_Occu5);
		Re_Occu6 = (RelativeLayout) super.findViewById(R.id.Re_Occu6);
		Re_Occu7 = (RelativeLayout) super.findViewById(R.id.Re_Occu7);

		Re_Occu1.setOnClickListener(new Re_Occu1OnClickListener());
		Re_Occu2.setOnClickListener(new Re_Occu2OnClickListener());
		Re_Occu3.setOnClickListener(new Re_Occu3OnClickListener());
		Re_Occu4.setOnClickListener(new Re_Occu4OnClickListener());
		Re_Occu5.setOnClickListener(new Re_Occu5OnClickListener());
		Re_Occu6.setOnClickListener(new Re_Occu6OnClickListener());
		Re_Occu7.setOnClickListener(new Re_Occu7OnClickListener());
		
		progressDialog = new ProgressDialog(OccupationSetActivity.this);
		progressDialog.setTitle("昵称");
		progressDialog.setMessage("月儿拼命更新中...");
		progressDialog.setCancelable(true);
		progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);

	}
	
	public class ImOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(OccupationSetActivity.this,
					PersonalSetActivity.class);
			startActivity(intent);

		}

	}

	public class Re_Occu1OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Occupation = 1;
			new MyAsynTask().execute();
		}
	}

	public class Re_Occu2OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Occupation = 2;
			new MyAsynTask().execute();
		}
	}

	public class Re_Occu3OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Occupation = 3;
			new MyAsynTask().execute();
		}
	}

	public class Re_Occu4OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Occupation = 4;
			new MyAsynTask().execute();
		}
	}

	public class Re_Occu5OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Occupation = 5;
			new MyAsynTask().execute();
		}
	}

	public class Re_Occu6OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Occupation = 6;
			new MyAsynTask().execute();
		}
	}

	public class Re_Occu7OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Occupation = 7;
			new MyAsynTask().execute();
		}
	}

	public void UpdateOccupation(int Occupation) {
		try {
			URL url = new URL(
					"http://119.29.85.27/Imooc/AndroidSetOccupationServlet?id="
							+ 2 + "&Occupation=" + Occupation);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.getInputStream().close();
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(OccupationSetActivity.this, "web服务器连接失败",
					Toast.LENGTH_SHORT).show();
		}
	}
	

	public class MyAsynTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressDialog.dismiss();
			Intent intent = new Intent(OccupationSetActivity.this,
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
			UpdateOccupation(Occupation);
			return null;
		}

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.occupation_set, menu);
		return true;
	}

}
