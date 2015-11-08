package com.etc.activity;

import java.net.HttpURLConnection;
import java.net.URL;

import com.etc.activity.OccupationSetActivity.ImOnClickListener;
import com.etc.activity.OccupationSetActivity.MyAsynTask;
import com.etc.activity.OccupationSetActivity.Re_Occu1OnClickListener;
import com.etc.activity.OccupationSetActivity.Re_Occu2OnClickListener;
import com.etc.activity.OccupationSetActivity.Re_Occu3OnClickListener;
import com.etc.activity.OccupationSetActivity.Re_Occu4OnClickListener;
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

public class RegionSetActivity extends Activity {
	TextView Tv_Region1, Tv_Region2, Tv_Region3, Tv_Region4;
	RelativeLayout Re_Region1, Re_Region2, Re_Region3, Re_Region4;
	int Region1;
	ImageButton Im_back_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_region_set);
		
		Im_back_title = (ImageButton) super
				.findViewById(R.id.Im_back_title);
		
		Im_back_title.setOnClickListener(new ImOnClickListener());

		Tv_Region1 = (TextView) super.findViewById(R.id.Tv_Region1);
		Tv_Region2 = (TextView) super.findViewById(R.id.Tv_Region2);
		Tv_Region3 = (TextView) super.findViewById(R.id.Tv_Region3);
		Tv_Region4 = (TextView) super.findViewById(R.id.Tv_Region4);

		Re_Region1 = (RelativeLayout) super.findViewById(R.id.Re_Region1);
		Re_Region2 = (RelativeLayout) super.findViewById(R.id.Re_Region2);
		Re_Region3 = (RelativeLayout) super.findViewById(R.id.Re_Region3);
		Re_Region4 = (RelativeLayout) super.findViewById(R.id.Re_Region4);

		Re_Region1.setOnClickListener(new Re_Region1OnClickListener());
		Re_Region2.setOnClickListener(new Re_Region2OnClickListener());
		Re_Region3.setOnClickListener(new Re_Region3OnClickListener());
		Re_Region4.setOnClickListener(new Re_Region4OnClickListener());

	}
	
	public class ImOnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(RegionSetActivity.this,
					PersonalSetActivity.class);
			startActivity(intent);

		}

	}

	public class Re_Region1OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Region1 = 1;
			new MyAsynTask().execute();

		}

	}

	public class Re_Region2OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Region1 = 2;
			new MyAsynTask().execute();
		}

	}

	public class Re_Region3OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Region1 = 3;
			Intent intent = new Intent(RegionSetActivity.this,
					RegionSet21Activity.class);
			intent.putExtra("Region1", Region1);
			System.out.println(Region1 + "--------------------------");
			startActivity(intent);
		}

	}

	public class Re_Region4OnClickListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Region1 = 4;
			Intent intent = new Intent(RegionSetActivity.this,
					RegionSet22Activity.class);
			intent.putExtra("Region1", Region1);
			startActivity(intent);
		}

	}

	public void UpdateRegion(int Region1) {
		try {
			URL url = new URL(
					"http://119.29.85.27/Imooc/AndroidSetRegionServlet?id="
							+ 2 + "&Occupation=" + Region1);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.getInputStream().close();
		} catch (Exception e) {
			// TODO: handle exception
			Toast.makeText(RegionSetActivity.this, "web服务器连接失败",
					Toast.LENGTH_SHORT).show();
		}
	}

	public class MyAsynTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Intent intent = new Intent(RegionSetActivity.this,
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
			UpdateRegion(Region1);
			return null;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.region_set, menu);
		return true;
	}

}
