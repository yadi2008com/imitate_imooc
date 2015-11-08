package com.etc.activity;

import java.net.HttpURLConnection;
import java.net.URL;

import com.etc.activity.RegionSet21Activity.MyAsynTask;
import com.etc.activity.RegionSet21Activity.Re_Region31OnClickListener;
import com.etc.activity.RegionSet21Activity.Re_Region32OnClickListener;
import com.etc.activity.RegionSet21Activity.Re_Region33OnClickListener;
import com.etc.activity.RegionSet21Activity.Re_Region34OnClickListener;
import com.etc.activity.RegionSetActivity.ImOnClickListener;
import com.example.b.R;
import com.example.b.R.layout;
import com.example.b.R.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegionSet22Activity extends Activity {
	RelativeLayout Re_Region41,Re_Region42,Re_Region43,Re_Region44;
	TextView Tv_Region41,Tv_Region42,Tv_Region43,Tv_Region44;
	int IntRegion2;
	ImageButton Im_back_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_region_set22);
		Intent intent=getIntent();
		IntRegion2=intent.getIntExtra("Region1", 0)*10;
		
		Im_back_title = (ImageButton) super
				.findViewById(R.id.Im_back_title);
		
		Im_back_title.setOnClickListener(new ImOnClickListener());
		
		
		Tv_Region41 = (TextView) super.findViewById(R.id.Tv_Region41);
		Tv_Region42 = (TextView) super.findViewById(R.id.Tv_Region42);
		Tv_Region43 = (TextView) super.findViewById(R.id.Tv_Region43);
		Tv_Region44 = (TextView) super.findViewById(R.id.Tv_Region44);



		Re_Region41 = (RelativeLayout) super.findViewById(R.id.Re_Region41);
		Re_Region42 = (RelativeLayout) super.findViewById(R.id.Re_Region42);
		Re_Region43 = (RelativeLayout) super.findViewById(R.id.Re_Region43);
		Re_Region44 = (RelativeLayout) super.findViewById(R.id.Re_Region44);


		Re_Region41.setOnClickListener(new Re_Region41OnClickListener());
		Re_Region42.setOnClickListener(new Re_Region42OnClickListener());
		Re_Region43.setOnClickListener(new Re_Region43OnClickListener());
		Re_Region44.setOnClickListener(new Re_Region44OnClickListener());
	}
	
	public class ImOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(RegionSet22Activity.this,
					PersonalSetActivity.class);
			startActivity(intent);

		}

	}
	
	
	public class Re_Region41OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			IntRegion2 = IntRegion2+1;
			new MyAsynTask().execute();

		}

	}
	
	public class Re_Region42OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			IntRegion2 = IntRegion2+2;
			new MyAsynTask().execute();

		}

	}
	
	public class Re_Region43OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			IntRegion2 = IntRegion2+3;
			new MyAsynTask().execute();

		}

	}
	
	public class Re_Region44OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			IntRegion2 = IntRegion2+4;
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
			Toast.makeText(RegionSet22Activity.this, "web服务器连接失败",
					Toast.LENGTH_SHORT).show();
		}
	}
	

	public class MyAsynTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);		
			Intent intent = new Intent(RegionSet22Activity.this,
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
		getMenuInflater().inflate(R.menu.region_set22, menu);
		return true;
	}

}
