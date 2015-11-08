package com.etc.activity;

import com.example.b.R;
import com.example.b.R.layout;
import com.example.b.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SexSetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sex_set);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sex_set, menu);
		return true;
	}

}
