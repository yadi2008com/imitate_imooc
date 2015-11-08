package com.etc.activity;

import java.net.HttpURLConnection;
import java.net.URL;

import com.etc.activity.RegionSetActivity.ImOnClickListener;
import com.etc.activity.RegionSetActivity.MyAsynTask;
import com.etc.activity.RegionSetActivity.Re_Region1OnClickListener;
import com.etc.activity.RegionSetActivity.Re_Region2OnClickListener;
import com.etc.activity.RegionSetActivity.Re_Region3OnClickListener;
import com.etc.activity.RegionSetActivity.Re_Region4OnClickListener;
import com.example.b.R;
import com.example.b.R.layout;
import com.example.b.R.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegionSet21Activity extends Activity {
	RelativeLayout Re_Region31, Re_Region32, Re_Region33, Re_Region34;
	TextView Tv_Region31, Tv_Region32, Tv_Region33, Tv_Region34;
	int IntRegion2;
	ImageButton Im_back_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_region_set21);
		Intent intent = getIntent();
		int Region1 = intent.getIntExtra("Region1", 0);
		IntRegion2 = Region1 * 10;
		
		Im_back_title = (ImageButton) super
				.findViewById(R.id.Im_back_title);
		
		Im_back_title.setOnClickListener(new ImOnClickListener());

		Tv_Region31 = (TextView) super.findViewById(R.id.Tv_Region31);
		Tv_Region32 = (TextView) super.findViewById(R.id.Tv_Region32);
		Tv_Region33 = (TextView) super.findViewById(R.id.Tv_Region33);
		Tv_Region34 = (TextView) super.findViewById(R.id.Tv_Region34);

		Re_Region31 = (RelativeLayout) super.findViewById(R.id.Re_Region31);
		Re_Region32 = (RelativeLayout) super.findViewById(R.id.Re_Region32);
		Re_Region33 = (RelativeLayout) super.findViewById(R.id.Re_Region33);
		Re_Region34 = (RelativeLayout) super.findViewById(R.id.Re_Region34);

		Re_Region31.setOnClickListener(new Re_Region31OnClickListener());
		Re_Region32.setOnClickListener(new Re_Region32OnClickListener());
		Re_Region33.setOnClickListener(new Re_Region33OnClickListener());
		Re_Region34.setOnClickListener(new Re_Region34OnClickListener());

	}
	
	public class ImOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(RegionSet21Activity.this,
					PersonalSetActivity.class);
			startActivity(intent);

		}

	}

	public class Re_Region31OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			IntRegion2 = IntRegion2 + 1;
			new MyAsynTask().execute();

		}

	}

	public class Re_Region32OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			IntRegion2 = IntRegion2 + 2;
			new MyAsynTask().execute();

		}

	}

	public class Re_Region33OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			IntRegion2 = IntRegion2 + 3;
			new MyAsynTask().execute();

		}

	}

	public class Re_Region34OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			IntRegion2 = IntRegion2 + 4;
			new MyAsynTask().execute();

		}

	}

	public void UpdateRegion(int IntRegion2) {
		try {
			URL url = new URL(
					"http://119.29.85.27/Imooc/AndroidSetRegionServlet?id="
							+ 2 + "&Occupation=" + IntRegion2);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.getInputStream().close();
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(RegionSet21Activity.this, "web服务器连接失败",
					Toast.LENGTH_SHORT).show();
		}
	}

	public class MyAsynTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Intent intent = new Intent(RegionSet21Activity.this,
					PersonalSetActivity.class);
			startActivity(intent);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			UpdateRegion(IntRegion2);
			return null;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.region_set21, menu);
		return true;
	}

}
